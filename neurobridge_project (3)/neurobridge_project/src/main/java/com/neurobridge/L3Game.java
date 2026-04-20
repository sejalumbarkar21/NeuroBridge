package com.neurobridge;


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

public class L3Game extends Application {

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

        // Level Buttons (Level 3 is now unlocked)
        HBox levelBox = new HBox(40);
        levelBox.setAlignment(Pos.CENTER);
        levelBox.getChildren().addAll(
                createLevelButton("Level 1", true),
                createLevelButton("Level 2", true),
                createLevelButton("Level 3", true),     // ✅ Now Unlocked
                createLevelButton("Level 4", false)
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
                            new Level3QuizIntro().start(new Stage());  // ✅ Must exist
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
}