package com.neurobridge;


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

public class Level3B extends Application {

    private Label scoreLabel = new Label(); // Added score label

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));
        root.setStyle(
            "-fx-background-image: url('/Assets/Images/BG7.jpeg');" +
            "-fx-background-size: cover;" +
            "-fx-background-repeat: no-repeat;" +
            "-fx-background-position: center center;"
        );

        // Title and Quote
        Text title = new Text("Level 3 - Echoes of Belongings");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setFill(Color.BLACK);

        Text quote = new Text("“An object from the past can reopen doors to forgotten stories.”");
        quote.setFont(Font.font("Georgia", FontPosture.ITALIC, 20));
        quote.setFill(Color.DARKSLATEGRAY);

        VBox topBox = new VBox(15, title, quote);
        topBox.setAlignment(Pos.TOP_LEFT);
        BorderPane.setAlignment(topBox, Pos.TOP_LEFT);
        root.setTop(topBox);

        // Main Image
        Image image = new Image("/Assets/Images/watch.jpeg"); // replace as needed
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        // Label + TextArea
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
        centerBox.setPadding(new Insets(80, 20, 20, 20)); // shifted downward
        root.setCenter(centerBox);

        // Buttons
        Button backButton = new Button("⬅ Back");
        Button submitButton = new Button("Check");
        Button nextButton = new Button("Next ➡");
        nextButton.setDisable(true);

        styleButton(backButton);
        styleButton(submitButton);
        styleButton(nextButton);

        // Result feedback
        Label resultLabel = new Label();
        resultLabel.setTextFill(Color.DARKGREEN);
        resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        resultLabel.setVisible(false);

        scoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        scoreLabel.setTextFill(Color.DARKBLUE);
        scoreLabel.setVisible(false);

        ImageView statusImage = new ImageView();
        statusImage.setFitHeight(80);
        statusImage.setFitWidth(80);
        statusImage.setVisible(false);

        // Check logic with score
        submitButton.setOnAction(e -> {
            String input = povInput.getText().toLowerCase();
            int score = 0;

            boolean keyword1 = input.contains("watch");
            boolean keyword2 = input.contains("gift");

            if (keyword1) score++;
            if (keyword2) score++;

            scoreLabel.setText("Score: " + score + "/2");
            scoreLabel.setVisible(true);

            if (score >= 2) {
                resultLabel.setText("Level Cleared! A heartfelt observation.");
                statusImage.setImage(new Image("file:src/main/resources/Images/Assets/levelcleared.png"));
                nextButton.setDisable(false);
            } else {
                resultLabel.setText("Try Again: Mention at least two meaningful aspects.");
                statusImage.setImage(new Image("file:src/main/resources/Images/Assets/tryagain.png"));
                nextButton.setDisable(true);
            }

            resultLabel.setVisible(true);
            statusImage.setVisible(true);
        });

        // Back and Next actions
        backButton.setOnAction(e -> {
            try {
                new Level3().start(new Stage()); // Adjust as per your flow
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nextButton.setOnAction(e -> {
            try {
                new L4Game().start(new Stage()); // Adjust to next level class
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox resultBox = new VBox(10, scoreLabel, resultLabel, statusImage);
        resultBox.setAlignment(Pos.CENTER);

        HBox navButtons = new HBox(15, backButton, submitButton, nextButton);
        navButtons.setAlignment(Pos.CENTER_RIGHT);
        navButtons.setPadding(new Insets(20));

        VBox bottomBox = new VBox(10, resultBox, navButtons);
        bottomBox.setAlignment(Pos.CENTER_RIGHT);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Level 3B - Echoes of Belongings (Part 2)");
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3e6cb7; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px; -fx-background-radius: 6;"));
    }
}