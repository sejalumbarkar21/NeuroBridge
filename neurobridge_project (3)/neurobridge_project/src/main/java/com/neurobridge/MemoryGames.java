package com.neurobridge;


import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MemoryGames extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Background image
        BackgroundImage bgImage = new BackgroundImage(
                new Image("/Assets/Images/bg.png", 1550, 800, false, true),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );

        // Center content: title and quote shifted left
        VBox centerContent = new VBox(20);
        centerContent.setAlignment(Pos.CENTER_LEFT);
        centerContent.setPadding(new Insets(100, 0, 0, 100)); // More left shift

        // Title
        Text title = new Text("Whispers of Memory");
        title.setFont(Font.font("Segoe Script", 48));
        title.setFill(Color.BLACK);
        title.setEffect(new DropShadow(10, Color.DARKSLATEBLUE));

        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", 14));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #4a154b; -fx-background-radius: 10;");
        backButton.setOnAction(e -> {
            try {
                new DashboardScreen().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Animate title
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), title);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(2), title);
        scaleIn.setFromX(0.8);
        scaleIn.setFromY(0.8);
        scaleIn.setToX(1.0);
        scaleIn.setToY(1.0);

        new ParallelTransition(fadeIn, scaleIn).play();

        // Quote - also shifted left with VBox
        Text quote = new Text("\"Even when memories fade, love remembers.\"");
        quote.setFont(Font.font("Verdana", 20));
        quote.setFill(Color.PURPLE);
        quote.setEffect(new DropShadow(3, Color.DARKSLATEGRAY));

        centerContent.getChildren().addAll(title, quote);

        // Start Button - bottom center
        Button startButton = new Button("Start");
        startButton.setFont(Font.font("Arial", 18));
        startButton.setStyle("-fx-background-color: #ffffffcc; -fx-text-fill: #333; -fx-background-radius: 20;");
        startButton.setOnAction(e -> {
            new L1Game().start(new Stage());
            primaryStage.close();
        });

        HBox buttonBox = new HBox(startButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(30));

        // Combine in BorderPane
        BorderPane root = new BorderPane();
        root.setCenter(centerContent);
        root.setBottom(buttonBox);
        root.setBackground(new Background(bgImage));

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Whispers of Memory");
        primaryStage.setScene(scene);
        primaryStage.show();

        //new Level1().start(new Stage());
    }

    
}
