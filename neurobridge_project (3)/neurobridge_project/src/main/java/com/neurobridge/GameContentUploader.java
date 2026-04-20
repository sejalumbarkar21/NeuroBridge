package com.neurobridge;



import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.cloud.StorageClient;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Blob;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



import java.io.*;
import java.net.URLEncoder;
import java.util.*;

public class GameContentUploader extends Application {

    private TextField patientIdField;
    private ComboBox<String> levelBox;
    private TextField keywordsField, personOrObjectField;
    private TextArea memoryTextArea;
    private File selectedFile;
    private Label fileLabel;

    private static final String BUCKET_NAME = "myfxproject-96640.firebasestorage.app";
    private static final String CREDENTIAL_PATH = "firebase-service-account.json";

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeFirebase();

        Label title = new Label("🧠 Upload Memory Game Content");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        Label subtitle = new Label("Family members can upload memories for each game level");
        subtitle.setStyle("-fx-font-size: 16px; -fx-text-fill: #eeeeee;");

        VBox titleBox = new VBox(5, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        patientIdField = new TextField();
        patientIdField.setPromptText("👤 Enter Patient ID");
        patientIdField.setStyle("-fx-background-radius: 10;");

        levelBox = new ComboBox<>();
        levelBox.getItems().addAll("Level 1", "Level 2", "Level 3");
        levelBox.setValue("Level 1");
        levelBox.setPrefWidth(300);
        levelBox.setStyle("-fx-background-radius: 10;");

        memoryTextArea = new TextArea();
        memoryTextArea.setPromptText("📝 Enter memory text (Level 1 only)");
        memoryTextArea.setPrefRowCount(4);
        memoryTextArea.setStyle("-fx-background-radius: 10;");

        keywordsField = new TextField();
        keywordsField.setPromptText("🔑 Keywords (comma-separated)");
        keywordsField.setStyle("-fx-background-radius: 10;");

        personOrObjectField = new TextField();
        personOrObjectField.setPromptText("👤 Person/Object name (L2/L3)");
        personOrObjectField.setStyle("-fx-background-radius: 10;");

        Button chooseFileBtn = new Button("📁 Choose File");
        chooseFileBtn.setStyle(buttonStyle("#ffc8dd"));
        fileLabel = new Label("No file selected");
        fileLabel.setStyle("-fx-text-fill: #888888;");

        chooseFileBtn.setOnAction(e -> chooseFile(primaryStage));

        HBox fileRow = new HBox(10, chooseFileBtn, fileLabel);
        fileRow.setAlignment(Pos.CENTER_LEFT);

        Button uploadBtn = new Button("🚀 Upload");
        uploadBtn.setStyle(buttonStyle("#cdb4db"));
        uploadBtn.setOnAction(e -> uploadToFirebase());

        VBox form = new VBox(14,
                new Label("👤 Patient ID:"), patientIdField,
                new Label("🎯 Select Level:"), levelBox,
                new Label("📌 Keywords:"), keywordsField,
                new Label("📝 Memory Text:"), memoryTextArea,
                new Label("👤 Person/Object:"), personOrObjectField,
                fileRow,
                uploadBtn
        );
        form.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        form.setPadding(new Insets(30));
        form.setAlignment(Pos.CENTER_LEFT);
        form.setMaxWidth(600);
        form.setEffect(new DropShadow(12, Color.rgb(0, 0, 0, 0.1)));

        
        Button backButton = new Button("← Back");
        backButton.setStyle(buttonStyle("#bde0fe"));
        backButton.setOnAction(e -> {
        try {
            new FamilyDashboardScreen().start(new Stage());
            ((Stage) backButton.getScene().getWindow()).close();
            } catch (Exception ex) {
           ex.printStackTrace();
       }
});    

        Button nextButton = new Button("Next");
        nextButton.setStyle(buttonStyle("#bde0fe"));
        nextButton.setOnAction(e -> {
        try {
            new QuizUploader().start(new Stage());
            ((Stage) nextButton.getScene().getWindow()).close();
            } catch (Exception ex) {
           ex.printStackTrace();
       }
});


        VBox card = new VBox(30, backButton,nextButton,titleBox, form);
        card.setAlignment(Pos.TOP_CENTER);

        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        };
        BackgroundFill bgFill = new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );

        StackPane root = new StackPane(card);
        root.setBackground(new Background(bgFill));
        root.setPadding(new Insets(60));

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("NeuroBridge - Game Content Upload");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void uploadToFirebase() {
    String patientId = patientIdField.getText().trim();
    if (patientId.isEmpty()) {
        showAlert("Please enter a valid Patient ID.");
        return;
    }

    String level = levelBox.getValue().toLowerCase().replace(" ", "_");
    String keywords = keywordsField.getText().trim();
    String memoryText = memoryTextArea.getText().trim();
    String personOrObject = personOrObjectField.getText().trim();

    Map<String, Object> entry = new HashMap<>();
    entry.put("keywords", Arrays.asList(keywords.split(",\\s*")));
    entry.put("timestamp", new Date());

    ProgressIndicator progress = new ProgressIndicator();
    Stage loadingStage = new Stage();
    VBox loadingBox = new VBox(progress, new Label("Uploading..."));
    loadingBox.setAlignment(Pos.CENTER);
    loadingBox.setPadding(new Insets(20));
    loadingStage.setScene(new Scene(loadingBox, 200, 100));
    loadingStage.setTitle("Uploading...");
    loadingStage.show();

    new Thread(() -> {
        try {
            Firestore db = FirestoreClient.getFirestore();

            DocumentReference docRef = db.collection("memory_game_data").document(patientId);

            // Ensure document exists
            docRef.set(new HashMap<>(), SetOptions.merge());

            if (!memoryText.isEmpty()) {
                entry.put("type", "text");
                entry.put("text", memoryText);
            } else if (selectedFile != null) {
                String fileType = getFileType(selectedFile); // "image" or "video"
                entry.put("type", fileType);

                String folder = fileType.equals("video") ? "videos/" : "images/";
                String fileName = UUID.randomUUID() + "_" + selectedFile.getName();
                String filePath = folder + fileName;

                Bucket bucket = StorageClient.getInstance().bucket();
                Blob blob = bucket.create(filePath, new FileInputStream(selectedFile), getContentType(selectedFile));

                String encodedPath = URLEncoder.encode(blob.getName(), "UTF-8");
                String downloadUrl = "https://firebasestorage.googleapis.com/v0/b/" + bucket.getName() +
                        "/o/" + encodedPath + "?alt=media";
                entry.put("url", downloadUrl);
            } else {
                throw new Exception("Please enter memory text or select a file.");
            }

            if (level.equals("level_2")) entry.put("personName", personOrObject);
            if (level.equals("level_3")) entry.put("object", personOrObject);

            docRef.update(level, FieldValue.arrayUnion(entry)).get();

            Platform.runLater(() -> {
                loadingStage.close();
                showAlert("✅ Upload successful!");
                patientIdField.clear();
                memoryTextArea.clear();
                keywordsField.clear();
                personOrObjectField.clear();
                fileLabel.setText("No file selected");
                selectedFile = null;
            });

        } catch (Exception ex) {
            ex.printStackTrace();
            Platform.runLater(() -> {
                loadingStage.close();
                showAlert("❌ Upload failed: " + ex.getMessage());
            });
        }
    }).start();
}

    private void chooseFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            fileLabel.setText(selectedFile.getName());
        }
    }

    private String getFileType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".mp4") || name.endsWith(".mov")) return "video";
        if (name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png")) return "image";
        return "file";
    }

    private String getContentType(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".mp4")) return "video/mp4";
        if (name.endsWith(".mov")) return "video/quicktime";
        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) return "image/jpeg";
        if (name.endsWith(".png")) return "image/png";
        return "application/octet-stream";
    }

    private void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream(CREDENTIAL_PATH);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(BUCKET_NAME)
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("NeuroBridge");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private String buttonStyle(String color) {
        return "-fx-background-color: " + color + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 18;" +
                "-fx-padding: 10 20;" +
                "-fx-cursor: hand;";
    }

    public static void main(String[] args) {
        launch(args);
    }
}