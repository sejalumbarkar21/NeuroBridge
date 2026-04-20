package com.neurobridge;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level3QuizIntro extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        VBox root = new VBox(40);
        root.setPadding(new Insets(60, 40, 60, 40));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #FFF2B2;");

        // Monster image
        Image monsterImage = new Image(getClass().getResource("/Assets/Images/QuizBrain.png").toExternalForm());
        ImageView monsterImageView = new ImageView(monsterImage);
        monsterImageView.setFitHeight(250);
        monsterImageView.setPreserveRatio(true);

        // === Animations ===

        // Bounce effect
        TranslateTransition bounce = new TranslateTransition(Duration.seconds(1), monsterImageView);
        bounce.setFromY(-100);
        bounce.setToY(0);
        bounce.setInterpolator(Interpolator.EASE_OUT);
        bounce.play();

        // Gentle sway loop
        RotateTransition sway = new RotateTransition(Duration.seconds(2), monsterImageView);
        sway.setByAngle(5);
        sway.setAutoReverse(true);
        sway.setCycleCount(Animation.INDEFINITE);
        sway.play();

        // Heading
        Label heading = new Label("Let’s Begin a Gentle Memory Quiz");
        heading.setFont(Font.font("Verdana", FontWeight.BOLD, 44));
        heading.setTextFill(Color.DARKSLATEGRAY);

        // Subtext
        Label subtext = new Label(
            "Welcome! This quiz is created with love to help you\n" +
            "recall precious memories, familiar faces, and joyful moments.\n" +
            "Take your time — no pressure, just gentle fun."
        );
        subtext.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        subtext.setTextAlignment(TextAlignment.CENTER);
        subtext.setWrapText(true);
        subtext.setTextFill(Color.BLACK);

        // Start Button
        Button startButton = new Button("Let’s Start the Game");
        startButton.setStyle("-fx-background-color: #FF3D75; -fx-text-fill: white; -fx-font-size: 24px; -fx-font-weight: bold;");
        startButton.setPadding(new Insets(15, 50, 15, 50));

        // Optional: Add scale animation on hover
        startButton.setOnMouseEntered(e -> startButton.setScaleX(1.1));
        startButton.setOnMouseExited(e -> startButton.setScaleX(1.0));
        startButton.setOnMouseEntered(e -> startButton.setScaleY(1.1));
        startButton.setOnMouseExited(e -> startButton.setScaleY(1.0));

       startButton.setOnAction(e -> {
    try {
        Level3Quiz level3Quiz = new Level3Quiz(); // Ensure Level3Quiz extends Application or has a `start(Stage)` method
        Stage quizStage = new Stage();
        level3Quiz.start(quizStage);
        
        // Optional: Close the current window
        ((Stage) startButton.getScene().getWindow()).close();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    });


        // Content container
        VBox contentBox = new VBox(40, monsterImageView, heading, subtext, startButton);
        contentBox.setAlignment(Pos.CENTER);
        contentBox.setPadding(new Insets(10, 20, 10, 20));

        root.getChildren().add(contentBox);

        Scene scene = new Scene(root, 1500, 800);
        primaryStage.setTitle("Memory Quiz - Alzheimer's Support");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
