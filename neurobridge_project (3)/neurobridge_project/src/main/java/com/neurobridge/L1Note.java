package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class L1Note extends Application {

    private VBox resultBox;
    private Button nextButton;
    private Button backButton;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));

        // Background (optional: plain or image)
        root.setStyle(
            "-fx-background-color: #fdf6e3;" // light notepad-like color
        );

        // Title
        Text title = new Text("Reflection - Write your thoughts");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setFill(Color.DARKBLUE);

        // Notepad-like TextArea
        TextArea textArea = new TextArea();
        textArea.setPromptText("Write what you remember or felt after watching the video...");
        textArea.setWrapText(true);
        textArea.setPrefSize(600, 300);
        textArea.setStyle(
            "-fx-control-inner-background: #fffff0;" +
            "-fx-font-size: 14px;" +
            "-fx-border-color: #b0a080;"
        );

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px;");
        
        resultBox = new VBox(10);
        resultBox.setAlignment(Pos.CENTER);
        resultBox.setPadding(new Insets(20));
        
        nextButton = new Button("Next ➡");
        backButton = new Button("⬅ Back");

        styleButton(nextButton);
        styleButton(backButton);

        submitButton.setOnAction(e -> {
            String userText = textArea.getText().toLowerCase();
            int matchedPoints = 0;

            if (userText.contains("friend")) matchedPoints++;
            if (userText.contains("memory")) matchedPoints++;
            if (userText.contains("childhood")) matchedPoints++;
            if (userText.contains("photo")) matchedPoints++;

            if (userText.contains("granny")) matchedPoints++;

            resultBox.getChildren().clear();

            if (matchedPoints >= 3) {
                showResult("Level Cleared! 🌟", "/Assets/Images/LevelClear.png", true, matchedPoints);
            } else {
                showResult("Level not cleared.\nTry to reflect on the video more carefully.", "/Assets/Images/tryagain.png", false, matchedPoints);
            }
        });

        VBox centerBox = new VBox(20, title, textArea, submitButton, resultBox);
        centerBox.setAlignment(Pos.TOP_CENTER);
        root.setCenter(centerBox);

        HBox navButtons = new HBox(15);
        navButtons.setAlignment(Pos.CENTER_RIGHT);
        navButtons.setPadding(new Insets(15));
        navButtons.getChildren().add(backButton);

        backButton.setOnAction(e -> {
            try {
                new Level1().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nextButton.setOnAction(e -> {
            System.out.println("Proceeding back to Level Selection...");
            try {
                new L2Game().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        root.setBottom(navButtons);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Level 1 - Reflection");
        primaryStage.show();
    }

    private void showResult(String message, String imagePath, boolean isCleared, int score) {
        Text resultText = new Text(message + "\n\nYour Score: " + score + "/4");
        resultText.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, 18));
        resultText.setFill(isCleared ? Color.FORESTGREEN : Color.FIREBRICK);
        resultText.setTextAlignment(TextAlignment.CENTER);

        ImageView imageView;
        try {
            imageView = new ImageView(new Image(imagePath));
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
        } catch (Exception e) {
            imageView = new ImageView();
        }

        resultBox.getChildren().addAll(resultText, imageView);

        HBox navButtons = (HBox) ((BorderPane) resultBox.getScene().getRoot()).getBottom();
        navButtons.getChildren().clear();
        navButtons.getChildren().add(backButton);
        if (isCleared) {
            navButtons.getChildren().add(nextButton);
        }
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #3e6cb7; -fx-text-fill: white; -fx-font-size: 14px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #5b8bd8; -fx-text-fill: white; -fx-font-size: 14px;"));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
