package com.neurobridge;



import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Level1 extends Application {

    // Removed the hardcoded patientId
    private final String patientId = UserSession.getInstance().getPatientId();

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.initialize(); // Firebase setup

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));

        root.setStyle(
            "-fx-background-image: url('/Assets/Images/BG7.jpeg');" +
            "-fx-background-size: cover;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-background-position: center center;"
        );

        Text title = new Text("Level 1 - Knowing Myself");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setFill(Color.BLACK);

        Text quote = new Text("“Even when memories fade, the light within you still shines bright.”");
        quote.setFont(Font.font("Georgia", FontPosture.ITALIC, 20));
        quote.setFill(Color.DARKSLATEGRAY);

        VBox topBox = new VBox(15, title, quote);
        topBox.setAlignment(Pos.TOP_LEFT);
        root.setTop(topBox);

        StackPane centerPane = new StackPane();
        centerPane.setPrefSize(600, 400);
        centerPane.setAlignment(Pos.CENTER);

        /*Region videoPlaceholder = new Region();
        videoPlaceholder.setPrefSize(500, 300);
        videoPlaceholder.setStyle(
            "-fx-background-color: rgba(255,255,255,0.6);" +
            "-fx-border-color: #5b8bd8;" +
            "-fx-border-width: 3;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;"
        );
        centerPane.getChildren().add(videoPlaceholder);*/

        Text watchText = new Text("▶ Watch your beautiful video");
        watchText.setFont(Font.font("Verdana", FontWeight.MEDIUM, 16));
        watchText.setFill(Color.DARKBLUE);
        watchText.setUnderline(true);

        watchText.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Fetching video from Firestore...");

            Firestore db = FirebaseInitializer.getFirestore();
            try {
                DocumentSnapshot snapshot = db.collection("memory_game_data")
                                              .document(patientId)
                                              .get()
                                              .get(); // blocking

                if (snapshot.exists()) {
                    List<Object> level1Data = (List<Object>) snapshot.get("level_1");
                    if (level1Data != null) {
                        for (Object obj : level1Data) {
                            if (obj instanceof Map) {
                                Map<String, Object> map = (Map<String, Object>) obj;
                                if ("video".equals(map.get("type"))) {
                                    String videoUrl = (String) map.get("url");
                                    playVideo(videoUrl);
                                    return;
                                }
                            }
                        }
                        System.out.println("No video found in level_1.");
                    } else {
                        System.out.println("level_1 field is empty.");
                    }
                } else {
                    System.out.println("Document not found for patient: " + patientId);
                }
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        });

        VBox centerBox = new VBox(25, centerPane, watchText);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        Button backButton = new Button("⬅ Back");
        Button nextButton = new Button("Next ➡");

        styleButton(backButton);
        styleButton(nextButton);

        backButton.setOnAction(e -> {
            try {
                new L1Game().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nextButton.setOnAction(e -> {
            try {
                new L1Note().start(new Stage());
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
        primaryStage.setTitle("Level 1 - Self Reflection");
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3e6cb7; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
    }

    private void playVideo(String videoUrl) {
        Media media = new Media(videoUrl);
        MediaPlayer player = new MediaPlayer(media);
        MediaView view = new MediaView(player);

        StackPane videoRoot = new StackPane(view);
        Scene scene = new Scene(videoRoot, 900, 800);

        Stage videoStage = new Stage();
        videoStage.setTitle("Level 1 - Video Playback");
        videoStage.setScene(scene);
        videoStage.show();

        player.play();
    }
}