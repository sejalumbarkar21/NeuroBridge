/*package com.neurobridge;



import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class Level2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.initialize();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));
        root.setStyle(
                "-fx-background-image: url('/Assets/Images/BG7.jpeg');" +
                "-fx-background-size: cover;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;"
        );

        Text title = new Text("Level 2 - Faces of Love");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setFill(Color.BLACK);

        Text quote = new Text("“Even when faces fade, love remains.”");
        quote.setFont(Font.font("Georgia", FontPosture.ITALIC, 20));
        quote.setFill(Color.DARKSLATEGRAY);

        VBox topBox = new VBox(15, title, quote);
        topBox.setAlignment(Pos.TOP_LEFT);
        root.setTop(topBox);

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        root.setCenter(scrollPane);

        /*String patientId = UserSession.getInstance().getPatientId();
        Firestore db = FirebaseInitializer.getFirestore();

        try {
            DocumentSnapshot snapshot = db.collection("memory_game_data").document(patientId).get().get();*/
       /*  String patientId = UserSession.getInstance().getPatientId();

             if (patientId == null || patientId.trim().isEmpty()) {
                 contentBox.getChildren().add(new Label("Error: Missing or invalid patient ID."));
                 System.err.println("Error: Patient ID is null or empty.");
                 return;
            }

        Firestore db = FirebaseInitializer.getFirestore();

             try {
                 DocumentSnapshot snapshot = db.collection("memory_game_data").document(patientId).get().get();


            if (snapshot.exists()) {
                List<Object> level2Data = (List<Object>) snapshot.get("level_2");

                if (level2Data != null) {
                    for (Object obj : level2Data) {
                        if (obj instanceof Map) {
                            Map<String, Object> data = (Map<String, Object>) obj;
                            if ("image".equals(data.get("type"))) {
                                String imageUrl = (String) data.get("url");
                                List<String> keywords = (List<String>) data.get("keywords");

                                ImageView imageView = new ImageView(new Image(imageUrl, 400, 300, true, true));
                                TextArea inputArea = new TextArea();
                                inputArea.setPromptText("Who is in this image? What do you remember?");
                                inputArea.setWrapText(true);
                                inputArea.setPrefRowCount(3);

                                Label feedbackLabel = new Label();

                                Button checkButton = new Button("Check");
                                checkButton.setOnAction(e -> {
                                    String userText = inputArea.getText().toLowerCase();
                                    int matches = 0;
                                    for (String keyword : keywords) {
                                        if (userText.contains(keyword.toLowerCase())) {
                                            matches++;
                                        }
                                    }
                                    if (matches >= 2) {
                                        feedbackLabel.setText("✅ Great! You remembered enough.");
                                        feedbackLabel.setTextFill(Color.GREEN);
                                    } else {
                                        feedbackLabel.setText("❌ Try again. Think about this person more deeply.");
                                        feedbackLabel.setTextFill(Color.RED);
                                    }
                                });

                                VBox imageBox = new VBox(10, imageView, inputArea, checkButton, feedbackLabel);
                                imageBox.setPadding(new Insets(15));
                                imageBox.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 10;");
                                imageBox.setAlignment(Pos.CENTER);
                                contentBox.getChildren().add(imageBox);
                            }
                        }
                    }
                } else {
                    contentBox.getChildren().add(new Label("No data found for Level 2."));
                }
            } else {
                contentBox.getChildren().add(new Label("No document found for patient: " + patientId));
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            contentBox.getChildren().add(new Label("Error loading Level 2 data."));
        }

        Button backButton = new Button("⬅ Back");
        Button nextButton = new Button("Next ➡");

        styleButton(backButton);
        styleButton(nextButton);

        backButton.setOnAction(e -> {
            try {
                new MemoryGames().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nextButton.setOnAction(e -> {
            try {
                new L3Game().start(new Stage()); // Replace with Level2B() if needed
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox navButtons = new HBox(15, backButton, nextButton);
        navButtons.setAlignment(Pos.CENTER_RIGHT);
        navButtons.setPadding(new Insets(20));
        root.setBottom(navButtons);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Level 2 - Faces of Love");
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3e6cb7; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
    }
}*/
package com.neurobridge;

import com.google.cloud.firestore.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Level2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.initialize();

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));
        root.setStyle(
                "-fx-background-image: url('/Assets/Images/BG7.jpeg');" +
                "-fx-background-size: cover;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center center;"
        );

        Text title = new Text("Level 2 - Faces of Love");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setFill(Color.BLACK);

        Text quote = new Text("“Even when faces fade, love remains.”");
        quote.setFont(Font.font("Georgia", FontPosture.ITALIC, 20));
        quote.setFill(Color.DARKSLATEGRAY);

        VBox topBox = new VBox(15, title, quote);
        topBox.setAlignment(Pos.TOP_LEFT);
        root.setTop(topBox);

        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        root.setCenter(scrollPane);

        String patientId = UserSession.getInstance().getPatientId();

        if (patientId == null || patientId.trim().isEmpty()) {
            Label errorLabel = new Label("⚠ Error: Missing or invalid patient ID.");
            errorLabel.setTextFill(Color.RED);
            errorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            contentBox.getChildren().add(errorLabel);
            System.err.println("Error: Patient ID is null or empty.");
        } else {
            Firestore db = FirebaseInitializer.getFirestore();

            try {
                DocumentSnapshot snapshot = db.collection("memory_game_data").document(patientId).get().get();

                if (snapshot.exists()) {
                    List<Object> level2Data = (List<Object>) snapshot.get("level_2");

                    if (level2Data != null) {
                        for (Object obj : level2Data) {
                            if (obj instanceof Map) {
                                Map<String, Object> data = (Map<String, Object>) obj;
                                if ("image".equals(data.get("type"))) {
                                    String imageUrl = (String) data.get("url");
                                    List<String> keywords = (List<String>) data.get("keywords");

                                    ImageView imageView = new ImageView(new Image(imageUrl, 500, 400, true, true));
                                    TextArea inputArea = new TextArea();
                                    inputArea.setPromptText("Who is in this image? What do you remember?");
                                    inputArea.setWrapText(true);
                                    inputArea.setPrefRowCount(3);

                                    Label feedbackLabel = new Label();

                                    Button checkButton = new Button("Check");
                                    checkButton.setOnAction(e -> {
                                        String userText = inputArea.getText().toLowerCase();
                                        int matches = 0;
                                        for (String keyword : keywords) {
                                            if (userText.contains(keyword.toLowerCase())) {
                                                matches++;
                                            }
                                        }
                                        if (matches >= 2) {
                                            feedbackLabel.setText("✅ Great! You remembered enough.");
                                            feedbackLabel.setTextFill(Color.GREEN);
                                        } else {
                                            feedbackLabel.setText("❌ Try again. Think about this person more deeply.");
                                            feedbackLabel.setTextFill(Color.RED);
                                        }
                                    });

                                    VBox imageBox = new VBox(10, imageView, inputArea, checkButton, feedbackLabel);
                                    imageBox.setPadding(new Insets(15));
                                    imageBox.setStyle("-fx-background-color: rgba(255,255,255,0.8); -fx-background-radius: 10;");
                                    imageBox.setAlignment(Pos.CENTER);
                                    contentBox.getChildren().add(imageBox);
                                }
                            }
                        }
                    } else {
                        contentBox.getChildren().add(new Label("No data found for Level 2."));
                    }
                } else {
                    contentBox.getChildren().add(new Label("No document found for patient: " + patientId));
                }

            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                contentBox.getChildren().add(new Label("Error loading Level 2 data."));
            }
        }

        // Navigation Buttons
        Button backButton = new Button("⬅ Back");
        Button nextButton = new Button("Next ➡");

        styleButton(backButton);
        styleButton(nextButton);

        backButton.setOnAction(e -> {
            try {
                new MemoryGames().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nextButton.setOnAction(e -> {
            try {
                new L3Game().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox navButtons = new HBox(15, backButton, nextButton);
        navButtons.setAlignment(Pos.CENTER_RIGHT);
        navButtons.setPadding(new Insets(20));
        root.setBottom(navButtons);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Level 2 - Faces of Love");
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3e6cb7; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
    }
}
