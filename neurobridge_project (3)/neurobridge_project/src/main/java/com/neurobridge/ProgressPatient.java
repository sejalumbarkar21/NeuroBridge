package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProgressPatient extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(40));

        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#ff00cc")),
                new Stop(1, Color.web("#333399"))
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        VBox mainContent = new VBox(30);
        mainContent.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(mainContent, 70.0);
        AnchorPane.setLeftAnchor(mainContent, 0.0);
        AnchorPane.setRightAnchor(mainContent, 0.0);

        Label greeting = new Label("My Activity");
        greeting.setFont(Font.font("Arial", 36));
        greeting.setTextFill(Color.WHITE);
        greeting.setStyle("-fx-font-weight: bold;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 4, 0.3, 0, 2);");

        VBox greetingBox = new VBox(5, greeting);
        greetingBox.setAlignment(Pos.CENTER);

        VBox squareContainer = new VBox();
        squareContainer.setPadding(new Insets(40));
        squareContainer.setSpacing(30);
        squareContainer.setAlignment(Pos.CENTER);
        squareContainer.setPrefSize(500, 400);
        squareContainer.setMaxSize(500, 400);
        squareContainer.setBackground(new Background(new BackgroundFill(Color.web("#ffffff30"), new CornerRadii(20), Insets.EMPTY)));
        squareContainer.setEffect(new DropShadow(20, Color.rgb(0, 0, 0, 0.2)));

        GridPane cardGrid = new GridPane();
        cardGrid.setHgap(30);
        cardGrid.setVgap(30);
        cardGrid.setAlignment(Pos.CENTER);

        cardGrid.add(createCard("Score Point", "15"), 0, 0);
        cardGrid.add(createCard("Level Reaction", "72%"), 1, 0);
        cardGrid.add(createCard("Game Track", "4 Games"), 0, 1);
        cardGrid.add(createCard("App Usage Time", "1.5 Hrs"), 1, 1);

        squareContainer.getChildren().add(cardGrid);
        mainContent.getChildren().addAll(greetingBox, squareContainer);
        root.getChildren().add(mainContent);

        // Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", 14));
        backButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-weight: bold;");
        backButton.setOnAction(e -> {
            new DashboardScreen().start(primaryStage); // Return to dashboard
        });

        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        root.getChildren().add(backButton);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("My Progress - Patient");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefSize(200, 100);

        String baseStyle = "-fx-background-color: white;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #eeeeee;" +
                "-fx-border-radius: 15;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 6, 0, 0, 4);";

        String hoverStyle = "-fx-background-color: #e0f7ff;" +
                "-fx-background-radius: 15;" +
                "-fx-border-color: #007acc;" +
                "-fx-border-radius: 15;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 6);";

        card.setStyle(baseStyle);
        card.setOnMouseEntered(e -> card.setStyle(hoverStyle));
        card.setOnMouseExited(e -> card.setStyle(baseStyle));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 16));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setStyle("-fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", 20));
        valueLabel.setTextFill(Color.BLACK);
        valueLabel.setStyle("-fx-font-weight: bold;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

