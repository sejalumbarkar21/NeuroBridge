package com.neurobridge;


import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.neurobridge.FirebaseInitializer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Level2B extends Application {

    private String imageUrl = "";
    private List<String> keywords;
    private ImageView imageView = new ImageView();

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.initialize();
        fetchLevel2BData();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));
        root.setStyle(
            "-fx-background-image: url('/Assets/Images/BG7.jpeg');" +
            "-fx-background-size: cover;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-background-position: center center;"
        );

        Text title = new Text("Level 2B - Faces of Love (Part 2)");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setFill(Color.BLACK);

        Text quote = new Text("“Love is remembered in smiles, not in memories.”");
        quote.setFont(Font.font("Georgia", FontPosture.ITALIC, 20));
        quote.setFill(Color.DARKSLATEGRAY);

        VBox topBox = new VBox(15, title, quote);
        topBox.setAlignment(Pos.TOP_LEFT);
        root.setTop(topBox);

        imageView.setFitWidth(400);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        Label povLabel = new Label("Describe your point of view about this image:");
        povLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 16));
        povLabel.setTextFill(Color.DARKSLATEBLUE);

        TextArea povInput = new TextArea();
        povInput.setPromptText("Write your thoughts...");
        povInput.setWrapText(true);
        povInput.setPrefRowCount(6);
        povInput.setMaxWidth(600);
        povInput.setStyle(
            "-fx-control-inner-background: lavender;" +
            "-fx-font-size: 14px;" +
            "-fx-border-color: #8e9ed1;" +
            "-fx-border-radius: 6;" +
            "-fx-background-radius: 6;"
        );

        VBox centerBox = new VBox(20, imageView, povLabel, povInput);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(80, 20, 20, 20));
        root.setCenter(centerBox);

        Button backButton = new Button("⬅ Back");
        Button submitButton = new Button("Check");
        Button nextButton = new Button("Next ➡");
        nextButton.setDisable(true);
        styleButton(backButton);
        styleButton(submitButton);
        styleButton(nextButton);

        Label resultLabel = new Label();
        resultLabel.setTextFill(Color.DARKGREEN);
        resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        resultLabel.setVisible(false);

        ImageView statusImage = new ImageView();
        statusImage.setFitHeight(80);
        statusImage.setFitWidth(80);
        statusImage.setVisible(false);

        submitButton.setOnAction(e -> {
            String input = povInput.getText().toLowerCase();
            int score = 0;

            if (keywords != null) {
                for (String kw : keywords) {
                    if (input.contains(kw.toLowerCase())) {
                        score++;
                    }
                }
            }

            if (score >= 2) {
                resultLabel.setText("✅ Level Cleared! Score: " + score + " / " + (keywords != null ? keywords.size() : "?"));
                statusImage.setImage(new Image("/Assets/Images/LevelClear.png"));
                nextButton.setDisable(false);
            } else {
                resultLabel.setText("❌ Try Again: Mention at least 2 specific keywords.\nScore: " + score);
                statusImage.setImage(new Image("/Assets/Images/tryagain.png"));
                nextButton.setDisable(true);
            }

            resultLabel.setVisible(true);
            statusImage.setVisible(true);
        });

        backButton.setOnAction(e -> {
            try {
                new Level2().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        nextButton.setOnAction(e -> {
            try {
                new L3Game().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) { ex.printStackTrace(); }
        });

        VBox resultBox = new VBox(10, resultLabel, statusImage);
        resultBox.setAlignment(Pos.CENTER);

        HBox navButtons = new HBox(15, backButton, submitButton, nextButton);
        navButtons.setAlignment(Pos.CENTER_RIGHT);
        navButtons.setPadding(new Insets(20));

        VBox bottomBox = new VBox(10, resultBox, navButtons);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Level 2B - Faces of Love (Part 2)");
        primaryStage.show();
    }

    private void fetchLevel2BData() {
        Firestore db = FirestoreClient.getFirestore();
        String patientId = "1"; // Replace with session-based ID

        DocumentReference docRef = db.collection("memory_game_data").document(patientId);
        ApiFuture<DocumentSnapshot> future = docRef.get();

        new Thread(() -> {
            try {
                DocumentSnapshot doc = future.get();
                if (doc.exists() && doc.contains("level_2b")) {
                    Object level2bRaw = doc.get("level_2b");

                    if (level2bRaw instanceof Map) {
                        Map<String, Object> levelB = (Map<String, Object>) level2bRaw;
                        this.imageUrl = (String) levelB.getOrDefault("imageUrl", "");
                        this.keywords = (List<String>) levelB.get("keywords");

                        Platform.runLater(() -> {
                            if (imageUrl != null && !imageUrl.isEmpty()) {
                                imageView.setImage(new Image(imageUrl, true));
                            }
                        });

                    } else {
                        System.out.println("⚠ level_2b is not a Map. Type: " + level2bRaw.getClass());
                    }
                } else {
                    System.out.println("⚠ No level_2b data found for patient ID: " + patientId);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void styleButton(Button btn) {
        btn.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #3e6cb7; -fx-text-fill: white;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white;"));
    }
}
