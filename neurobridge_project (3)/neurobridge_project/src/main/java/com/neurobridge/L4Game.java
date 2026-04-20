/*package com.neurobridge;


import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class L4Game extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(40);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30, 50, 50, 50));
        root.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));

        // Title
        Text title = new Text("Select a Level");
        title.setFont(Font.font("Segoe Script", 42));
        title.setFill(Color.DARKSLATEBLUE);
        VBox.setMargin(title, new Insets(10, 0, 10, 0));

        // Level Buttons (All Unlocked Now)
        HBox levelBox = new HBox(40);
        levelBox.setAlignment(Pos.CENTER);
        levelBox.getChildren().addAll(
                createLevelButton("Level 1", true),
                createLevelButton("Level 2", true),
                createLevelButton("Level 3", true),
                createLevelButton("Level 4", true)   // ✅ Now Unlocked
        );

        // Bottom Decorative Image
        ImageView bottomImage = new ImageView(new Image(getClass().getResource("/Assets/Images/GameBrain.png").toExternalForm()));
        bottomImage.setFitWidth(250);
        bottomImage.setPreserveRatio(true);
        VBox.setMargin(bottomImage, new Insets(60, 0, 0, 0));

        root.getChildren().addAll(title, levelBox, bottomImage);

        Scene scene = new Scene(root, 1550, 800);
        stage.setScene(scene);
        stage.setTitle("Game Levels");
        stage.show();
    }

    private VBox createLevelButton(String levelName, boolean isUnlocked) {
        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(150);

        ImageView icon = new ImageView();
        icon.setFitWidth(80);
        icon.setFitHeight(80);

        String imagePath = isUnlocked ? "/Assets/Images/unlock.png" : "/Assets/Images/lock.png";
        Image img = new Image(getClass().getResource(imagePath).toExternalForm());
        icon.setImage(img);

        if (isUnlocked) {
            ScaleTransition scale = new ScaleTransition(Duration.seconds(1.5), icon);
            scale.setFromX(1.0);
            scale.setFromY(1.0);
            scale.setToX(1.15);
            scale.setToY(1.15);
            scale.setAutoReverse(true);
            scale.setCycleCount(ScaleTransition.INDEFINITE);
            scale.play();
        }

        Text label = new Text(levelName);
        label.setFont(Font.font("Verdana", 18));
        label.setFill(isUnlocked ? Color.DARKGREEN : Color.GRAY);

        Button button = new Button(isUnlocked ? "Play" : "Locked");
        button.setDisable(!isUnlocked);
        button.setStyle(
                "-fx-background-color: " + (isUnlocked ? "#81C784" : "#e0e0e0") + ";" +
                        "-fx-text-fill: #000;" +
                        "-fx-background-radius: 25;" +
                        "-fx-padding: 6 20 6 20;" +
                        "-fx-font-weight: bold;"
        );

        if (isUnlocked) {
            button.setOnMouseEntered(e -> button.setStyle(
                    "-fx-background-color: #66BB6A;" +
                            "-fx-text-fill: white;" +
                            "-fx-background-radius: 25;" +
                            "-fx-padding: 6 20 6 20;" +
                            "-fx-font-weight: bold;"
            ));
            button.setOnMouseExited(e -> button.setStyle(
                    "-fx-background-color: #81C784;" +
                            "-fx-text-fill: black;" +
                            "-fx-background-radius: 25;" +
                            "-fx-padding: 6 20 6 20;" +
                            "-fx-font-weight: bold;"
            ));
        }

        button.setEffect(new DropShadow(5, Color.GRAY));

        button.setOnAction(e -> {
            if (isUnlocked) {
                System.out.println(levelName + " clicked.");
                try {
                    Stage currentStage = (Stage) button.getScene().getWindow();
                    switch (levelName) {
                        case "Level 1":
                            new Level1().start(new Stage());
                            break;
                        case "Level 2":
                            new Level2().start(new Stage());
                            break;
                        case "Level 3":
                            new Level3().start(new Stage());
                            break;
                        case "Level 4":
                            new Level4().start(new Stage());  // ✅ You must create Level4 class
                            break;
                        default:
                            System.out.println("No screen defined for " + levelName);
                            return;
                    }
                    currentStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        box.getChildren().addAll(icon, label, button);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class L4Game extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main layout container
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));

        // Background color
        root.setStyle("-fx-background-color: #FFF8F0;");

        // Title
        Label titleLabel = new Label("A Letter to You");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKSLATEBLUE);
        titleLabel.setPadding(new Insets(10, 0, 20, 0));

        // Letter content
        String letter = """
                Dear Patient ,

                I want you to know that you are deeply loved and cherished.
                You may forget moments, faces, or words—but what remains in your heart is forever.

                You have lived a beautiful life full of stories, kindness, and strength.
                Even on the days that feel cloudy, your smile brings light to others.

                You are never alone. Your family, your caregivers, and your friends
                are all here with you—holding your hand, guiding your steps, and celebrating your presence.

                No matter what changes, remember this: You are enough. You matter. You are loved.

                With all my heart,
                Someone who loves you 💛
                """;

        TextArea letterArea = new TextArea(letter);
        letterArea.setWrapText(true);
        letterArea.setFont(Font.font("Serif", FontPosture.ITALIC, 20));
        letterArea.setEditable(false);
        letterArea.setStyle("-fx-control-inner-background: #FFF8F0; -fx-text-fill: #333333; -fx-border-color: transparent;");
        letterArea.setPrefHeight(400);
        letterArea.setFocusTraversable(false);

        // ScrollPane in case the letter is long
        ScrollPane scrollPane = new ScrollPane(letterArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #FFF8F0;");

        // Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #8A2BE2; -fx-background-radius: 10;");
        backButton.setPadding(new Insets(10, 20, 10, 20));
        backButton.setOnAction(e -> primaryStage.close()); // You can replace this with actual navigation

        VBox centerBox = new VBox(20, titleLabel, scrollPane);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(10));

        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
        root.setCenter(centerBox);
        root.setBottom(backButton);
        BorderPane.setMargin(backButton, new Insets(20, 0, 0, 10));

        // Final scene
        Scene scene = new Scene(root, 1500, 800);
        primaryStage.setTitle("Level 4 - A Letter to You");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Run this as a standalone JavaFX app
    public static void main(String[] args) {
        launch(args);
    }
} */
package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class L4Game extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main layout container
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(30));

        // Background color
        root.setStyle("-fx-background-color: #FFF8F0;");

        // Title
        Label titleLabel = new Label("A Letter to You");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.DARKSLATEBLUE);
        titleLabel.setPadding(new Insets(10, 0, 20, 0));

        // Letter content
        String letter = """
                Dear Patient ,

                I want you to know that you are deeply loved and cherished.
                You may forget moments, faces, or words—but what remains in your heart is forever.

                You have lived a beautiful life full of stories, kindness, and strength.
                Even on the days that feel cloudy, your smile brings light to others.

                You are never alone. Your family, your caregivers, and your friends
                are all here with you—holding your hand, guiding your steps, and celebrating your presence.

                No matter what changes, remember this: You are enough. You matter. You are loved.

                With all my heart,
                Someone who loves you 💛
                """;

        TextArea letterArea = new TextArea(letter);
        letterArea.setWrapText(true);
        letterArea.setFont(Font.font("Serif", FontPosture.ITALIC, 20));
        letterArea.setEditable(false);
        letterArea.setStyle("-fx-control-inner-background: #FFF8F0; -fx-text-fill: #333333; -fx-border-color: transparent;");
        letterArea.setPrefHeight(400);
        letterArea.setFocusTraversable(false);

        // ScrollPane
        ScrollPane scrollPane = new ScrollPane(letterArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #FFF8F0;");

        // Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #8A2BE2; -fx-background-radius: 10;");
        backButton.setPadding(new Insets(10, 20, 10, 20));
        backButton.setOnAction(e -> {
            try {
                new DashboardScreen().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox centerBox = new VBox(20, titleLabel, scrollPane);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(10));

        BorderPane.setAlignment(backButton, Pos.CENTER_LEFT);
        root.setCenter(centerBox);
        root.setBottom(backButton);
        BorderPane.setMargin(backButton, new Insets(20, 0, 0, 10));

        // Scene
        Scene scene = new Scene(root, 1500, 800);
        primaryStage.setTitle("Level 4 - A Letter to You");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


