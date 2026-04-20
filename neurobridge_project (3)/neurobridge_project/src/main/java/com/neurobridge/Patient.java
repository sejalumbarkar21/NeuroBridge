package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Patient extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        // Title
        Text title = new Text("My Activities");
        title.setFont(Font.font("Arial", 24));
        title.setFill(Color.web("#333"));

        // Search bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefHeight(35);
        searchField.setMaxWidth(300);
        searchField.setStyle("-fx-background-radius: 10; -fx-background-color: #f8f9fa; -fx-border-color: #ced4da;");

        // Tabs
        HBox tabBar = new HBox(20);
        tabBar.setAlignment(Pos.CENTER);
        String[] tabs = {"Daily", "Weekly"};

        for (String tab : tabs) {
            Button btn = new Button(tab);
            btn.setPrefWidth(90);
            btn.setPrefHeight(35);
            btn.setStyle(
                "-fx-background-color: #dcf3ff;" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 30;" +
                "-fx-cursor: hand;"
            );

            btn.setOnMouseEntered(e -> {
                btn.setScaleX(1.05);
                btn.setScaleY(1.05);
                btn.setStyle(
                    "-fx-background-color: #ffe5ef;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 30;" +
                    "-fx-cursor: hand;"
                );
            });

            btn.setOnMouseExited(e -> {
                btn.setScaleX(1);
                btn.setScaleY(1);
                btn.setStyle(
                    "-fx-background-color: #dcf3ff;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-weight: bold;" +
                    "-fx-background-radius: 30;" +
                    "-fx-cursor: hand;"
                );
            });

            tabBar.getChildren().add(btn);
        }

        // Cards
        GridPane cards = new GridPane();
        cards.setHgap(20);
        cards.setVgap(20);
        cards.setAlignment(Pos.CENTER);
        cards.setPadding(new Insets(10));

        cards.add(createCard("Cognitive Score", "72", "points", "#824DFF"), 0, 0);
        cards.add(createCard("Sleep", "7.5", "hours", "#D236FF"), 1, 0);
        cards.add(createCard("Mood", "Stable", "", "#49C5B6"), 0, 1);
        cards.add(createCard("Physical Activity", "25", "mins", "#FF6C88"), 1, 1);

        // Info section
        VBox infoSection = new VBox(15);
        infoSection.setAlignment(Pos.CENTER);
        infoSection.getChildren().addAll(
            createInfoTile("Medication Logs"),
            createInfoTile("Cognitive Exercises"),
            createInfoTile("Caregiver Notes")
        );

        // Add all to root
        root.getChildren().addAll(title, searchField, tabBar, cards, infoSection);

        // Wrap root in a StackPane and apply gradient background
        StackPane backgroundPane = new StackPane();
        backgroundPane.getChildren().add(root);
        backgroundPane.setPadding(new Insets(40));
        backgroundPane.setBackground(new Background(new BackgroundFill(
            new LinearGradient(
                0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ec3c8b")),   // top-left pink
                new Stop(1, Color.web("#7c67f0"))    // bottom-right purple
            ),
            CornerRadii.EMPTY, Insets.EMPTY
        )));

        Scene scene = new Scene(backgroundPane, 1550, 700);
        primaryStage.setTitle("My Progress");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCard(String title, String value, String unit, String color) {
        VBox box = new VBox(5);
        box.setPadding(new Insets(15));
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 10, 0, 0, 5);");
        box.setPrefSize(180, 120);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 14));
        titleLabel.setTextFill(Color.web("#333"));

        Label valueLabel = new Label(value + (unit.isEmpty() ? "" : " " + unit));
        valueLabel.setFont(Font.font("Arial", 24));
        valueLabel.setTextFill(Color.web(color));

        // Hover effect
        box.setOnMouseEntered(e -> {
            box.setScaleX(1.03);
            box.setScaleY(1.03);
            box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0, 0, 8);");
        });

        box.setOnMouseExited(e -> {
            box.setScaleX(1);
            box.setScaleY(1);
            box.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 10, 0, 0, 5);");
        });

        box.getChildren().addAll(titleLabel, valueLabel);
        return box;
    }

    private HBox createInfoTile(String labelText) {
        HBox tile = new HBox();
        tile.setPadding(new Insets(15));
        tile.setSpacing(10);
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 15;");
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.setMaxWidth(400);

        Label label = new Label(labelText);
        label.setFont(Font.font("Arial", 16));
        label.setTextFill(Color.BLACK);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label arrow = new Label(">");
        arrow.setTextFill(Color.GREY);

        tile.getChildren().addAll(label, spacer, arrow);
        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
