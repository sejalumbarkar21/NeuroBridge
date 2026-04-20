package com.neurobridge;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;

public class Relax extends Application {

    private MediaPlayer mediaPlayer;
    private Timeline timer;
    private int elapsedSeconds = 0;

    public static String selectedTitle = "Relax";
    public static String selectedAudioPath = "/Assets/Images/relax.mp3";
    public static String selectedImagePath = "/Assets/Images/med1.png";
    public static String selectedQuote = "Let go of your thoughts, and breathe.";

    private static final Map<String, String> titleQuoteMap = new HashMap<>();

    static {
        titleQuoteMap.put("Relax", "Let go of your thoughts, and breathe.");
        titleQuoteMap.put("Calm", "Peace begins with a deep breath.");
        titleQuoteMap.put("Focus", "Center your mind, sharpen your vision.");
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #9575CD, #CE93D8);");

        // 🔙 Back Button (top-left)
        HBox backBox = new HBox();
        Button backBtn = new Button("← Back");
        backBtn.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
        backBtn.setStyle("""
            -fx-background-color: transparent;
            -fx-text-fill: white;
            -fx-font-size: 18px;
            -fx-cursor: hand;
        """);
        backBtn.setOnAction(e -> {
            try {
                new MeditationModule().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        backBox.getChildren().add(backBtn);
        backBox.setAlignment(Pos.TOP_LEFT);
        backBox.setPadding(new Insets(0, 0, 10, 10));
        root.getChildren().add(backBox);

        // 🌸 Top Image
        try {
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(selectedImagePath)));
            imageView.setFitHeight(300);
            imageView.setPreserveRatio(true);
            root.getChildren().add(imageView);
        } catch (Exception e) {
            showError("Top image not found: " + selectedImagePath);
        }

        // 🧘‍♀️ Title
        Label titleLabel = new Label(selectedTitle);
        titleLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.WHITE);

        // 🧠 Quote
        Label quoteLabel = new Label(titleQuoteMap.getOrDefault(selectedTitle, selectedQuote));
        quoteLabel.setFont(Font.font("Poppins", FontWeight.NORMAL, 22));
        quoteLabel.setTextFill(Color.LAVENDER);
        quoteLabel.setWrapText(true);
        quoteLabel.setTextAlignment(TextAlignment.CENTER);
        quoteLabel.setMaxWidth(700);
        quoteLabel.setLineSpacing(5);
        quoteLabel.setTranslateX(150); // ✅ Shifted right by 40 pixels

        // ⏱ Timer
        Label timerLabel = new Label("00:00");
        timerLabel.setFont(Font.font("Poppins", FontWeight.BOLD, 28));
        timerLabel.setTextFill(Color.WHITE);

        // 🎧 Start/Stop
        Button startBtn = new Button("Start");
        Button stopBtn = new Button("Stop");
        startBtn.setStyle(buttonStyle());
        stopBtn.setStyle(buttonStyle());

        // 🎶 MediaPlayer setup
        try {
            String path = getClass().getResource(selectedAudioPath).toExternalForm();
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
        } catch (Exception e) {
            showError("Audio file not found: " + selectedAudioPath);
            return;
        }

        // ▶ Start
        startBtn.setOnAction(e -> {
            mediaPlayer.play();
            startTimer(timerLabel);
        });

        // ⏹ Stop
        stopBtn.setOnAction(e -> {
            mediaPlayer.stop();
            stopTimer(timerLabel);

            int mins = elapsedSeconds / 60;
            int secs = elapsedSeconds % 60;
            String timeMessage = String.format("You meditated for %02d minutes and %02d seconds.", mins, secs);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Meditation Complete");
            alert.setHeaderText("Well Done!");
            alert.setContentText(timeMessage);
            alert.showAndWait();
        });

        // Buttons
        HBox controlBox = new HBox(30, startBtn, stopBtn);
        controlBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox(20, titleLabel, quoteLabel, timerLabel, controlBox);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(30));
        contentBox.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 25;");

        root.getChildren().add(contentBox);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Meditation Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startTimer(Label label) {
        stopTimer(label);
        elapsedSeconds = 0;

        timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            elapsedSeconds++;
            int mins = elapsedSeconds / 60;
            int secs = elapsedSeconds % 60;
            label.setText(String.format("%02d:%02d", mins, secs));
        }));
        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    private void stopTimer(Label label) {
        if (timer != null) timer.stop();
        label.setText("00:00");
    }

    private void showError(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    private String buttonStyle() {
        return """
            -fx-background-color: white;
            -fx-text-fill: #6A1B9A;
            -fx-font-size: 18px;
            -fx-font-weight: bold;
            -fx-background-radius: 30;
            -fx-padding: 12 30;
        """;
    }

    public static void launchPlayer(String title, String audioPath, String imagePath) {
        selectedTitle = title;
        selectedAudioPath = audioPath;
        selectedImagePath = imagePath;
        selectedQuote = titleQuoteMap.getOrDefault(title, "Breathe and be present.");
        launch();
    }
}