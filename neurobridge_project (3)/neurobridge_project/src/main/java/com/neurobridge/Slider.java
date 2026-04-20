package com.neurobridge;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class Slider extends Application {

    private final String[] imageFiles = {
        "/Assets/Images/1.1.png",
        "/Assets/Images/1.2.png",
        "/Assets/Images/1.3.png",
        "/Assets/Images/1.4.png",
        "/Assets/Images/1.6.png"
    };

    private final List<ImageView> imageViews = new ArrayList<>();
    private final Circle[] indicators = new Circle[imageFiles.length];
    private HBox imageBox;
    private Timeline timeline;

    @Override
    public void start(Stage stage) {
        // Image container
        imageBox = new HBox(40);
        imageBox.setAlignment(Pos.CENTER);

        // Dots for indicators
        HBox dots = new HBox(8);
        dots.setAlignment(Pos.CENTER);
        for (int i = 0; i < imageFiles.length; i++) {
            indicators[i] = new Circle(5, i == imageFiles.length / 2 ? Color.DARKGRAY : Color.LIGHTGRAY);
            dots.getChildren().add(indicators[i]);
        }

        // Sign Up button
        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setStyle("-fx-background-color: #ff4e99; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 20px;");
        signUpBtn.setPrefWidth(160);
        signUpBtn.setOnAction(e -> System.out.println("Sign Up clicked!"));

        // Content layout
        VBox content = new VBox(30);
        content.setAlignment(Pos.CENTER);
        content.getChildren().addAll(imageBox, dots, signUpBtn);

        // Gradient background
        Stop[] stops = new Stop[] {
            //new Stop(0, Color.web("#ff4e99")), // pink
            new Stop(1, Color.web("#53489bff"))  // purple
        };
        LinearGradient gradient = new LinearGradient(
            0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops
        );
        Background gradientBackground = new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY));

        StackPane root = new StackPane(content);
        root.setBackground(gradientBackground);

        Scene scene = new Scene(root, 1000, 800, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());

        // Load images
        for (String path : imageFiles) {
            Image image = loadImageFromResources(path);
            if (image != null) {
                ImageView iv = new ImageView(image);
                iv.setFitWidth(220);
                iv.setPreserveRatio(true);
                imageViews.add(iv);
                imageBox.getChildren().add(iv);
            } else {
                System.err.println("Failed to load image: " + path);
            }
        }

        if (imageViews.isEmpty()) {
            System.err.println("No images loaded.");
            return;
        }

        updateCarousel();

        // Auto-rotate images
        timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> rotateLeft()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        stage.setTitle("3D Slider with Gradient Background");
        stage.setScene(scene);
        stage.show();
    }

    private void rotateLeft() {
        if (!imageViews.isEmpty()) {
            ImageView first = imageViews.remove(0);
            imageViews.add(first);
            updateCarousel();
        }
    }

    private void updateCarousel() {
        imageBox.getChildren().clear();
        imageBox.getChildren().addAll(imageViews);

        int mid = imageViews.size() / 2;

        for (int i = 0; i < imageViews.size(); i++) {
            ImageView iv = imageViews.get(i);
            iv.getTransforms().clear();
            iv.setScaleX(1);
            iv.setScaleY(1);
            iv.setTranslateY(0);
            iv.setTranslateZ(0);

            int offset = i - mid;

            Rotate rotate = new Rotate(offset * 20, Rotate.Y_AXIS);
            iv.getTransforms().add(rotate);

            iv.setTranslateZ(Math.abs(offset) * -80);

            if (offset == 0) {
                iv.setScaleX(1.2);
                iv.setScaleY(1.2);
                iv.setTranslateY(-40);
            }

            iv.setOnMouseEntered(e -> {
                iv.toFront();
                iv.setScaleX(1.3);
                iv.setScaleY(1.3);
                iv.setTranslateY(-60);
                rotate.setAngle(0);
            });

            iv.setOnMouseExited(e -> {
                iv.setScaleX(offset == 0 ? 1.2 : 1);
                iv.setScaleY(offset == 0 ? 1.2 : 1);
                iv.setTranslateY(offset == 0 ? -40 : 0);
                rotate.setAngle(offset * 20);
            });

            iv.setEffect(new DropShadow(10, Color.gray(0.4)));
        }

        for (int i = 0; i < indicators.length; i++) {
            indicators[i].setFill(i == mid ? Color.DARKGRAY : Color.LIGHTGRAY);
        }
    }

    private Image loadImageFromResources(String path) {
        try {
            return new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        } catch (Exception e) {
            System.err.println("Error loading image: " + path + " - " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
