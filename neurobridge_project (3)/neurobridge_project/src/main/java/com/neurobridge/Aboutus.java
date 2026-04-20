/*package com.neurobridge;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Aboutus extends Application {

    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
        // Dynamic animated gradient background
        Stop[] stops1 = new Stop[]{new Stop(0, Color.web("#cb6ce6")), new Stop(1, Color.web("#9547f7"))};
        Stop[] stops2 = new Stop[]{new Stop(0, Color.web("#9547f7")), new Stop(1, Color.web("#cb6ce6"))};

        LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops1);
        LinearGradient gradient2 = new LinearGradient(1, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops2);

        BackgroundFill[] fills = {new BackgroundFill(gradient1, CornerRadii.EMPTY, Insets.EMPTY)};
        StackPane root = new StackPane();
        root.setBackground(new Background(fills));

        Timeline bgAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(root.backgroundProperty(), new Background(new BackgroundFill(gradient1, null, null)))),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(root.backgroundProperty(), new Background(new BackgroundFill(gradient2, null, null))))
        );
        bgAnimation.setCycleCount(Animation.INDEFINITE);
        bgAnimation.setAutoReverse(true);
        bgAnimation.play();

        // Heading with animation
        Label heading = new Label("A Big Thank You to Core2Web");
        heading.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 36));
        heading.setTextFill(Color.WHITE);
        heading.setEffect(new DropShadow(10, Color.web("#333")));

        FadeTransition fadeInHeading = new FadeTransition(Duration.seconds(2), heading);
        fadeInHeading.setFromValue(0);
        fadeInHeading.setToValue(1);
        fadeInHeading.play();

        // Profile image
        imageView = new ImageView(new Image("/Assets/Images/image.png"));
        imageView.setFitWidth(180);
        imageView.setFitHeight(180);
        Circle clip = new Circle(90, 90, 90);
        imageView.setClip(clip);

        StackPane imageHolder = new StackPane(imageView);
        imageHolder.setPadding(new Insets(10));
        imageHolder.setStyle(
                "-fx-background-radius: 100;" +
                        "-fx-background-color: linear-gradient(to bottom right, #ffffff, #ccccff);" +
                        "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.3), 15, 0.2, 0, 8);"
        );
        imageHolder.setMaxSize(200, 200);

        // Swap image on click
        imageHolder.setOnMouseClicked((MouseEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imageView.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        FadeTransition fadeImage = new FadeTransition(Duration.seconds(2), imageHolder);
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);
        fadeImage.setDelay(Duration.seconds(0.5));
        fadeImage.play();

        // Thank You message with glass card effect
        String msg = """
                We want to express our heartfelt thanks to Shashi Sir and the entire Core2Web team for being a constant source of support and encouragement.

                Your teaching, mentorship, and patience have truly helped us grow not just as students, but as individuals with confidence and purpose.

                To every mentor and trainer who guided us, believed in us, and helped us build clarity thank you for shaping our journey.

                This project was made possible through your motivation and support, and we’re proud to share this success with the entire Core2Web family.

                This isn’t just a technical win it’s a celebration of teamwork, learning, and gratitude. 
                """;

        Label msgLabel = new Label(msg);
        msgLabel.setWrapText(true);
        msgLabel.setFont(Font.font("Segoe UI", 18));
        msgLabel.setTextFill(Color.web("#2c2c2c"));

        VBox msgCard = new VBox(msgLabel);
        msgCard.setPadding(new Insets(30));
        msgCard.setMaxWidth(850);
        msgCard.setAlignment(Pos.CENTER);
        msgCard.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.8);" +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0.3, 0, 8);"
        );

        // Slide-in animation for message card
        msgCard.setTranslateY(800); // off-screen
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(2), msgCard);
        slideIn.setToY(0);
        slideIn.setDelay(Duration.seconds(1));
        slideIn.setInterpolator(Interpolator.EASE_OUT);
        slideIn.play();

        VBox content = new VBox(30, heading, imageHolder, msgCard);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40));

        root.getChildren().add(content);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Thank You - Core2Web");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
/*package com.neurobridge;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Aboutus extends Application {

    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
        // Dynamic animated gradient background
        Stop[] stops1 = new Stop[]{new Stop(0, Color.web("#cb6ce6")), new Stop(1, Color.web("#9547f7"))};
        Stop[] stops2 = new Stop[]{new Stop(0, Color.web("#9547f7")), new Stop(1, Color.web("#cb6ce6"))};

        LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops1);
        LinearGradient gradient2 = new LinearGradient(1, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops2);

        BackgroundFill[] fills = {new BackgroundFill(gradient1, CornerRadii.EMPTY, Insets.EMPTY)};
        StackPane root = new StackPane();
        root.setBackground(new Background(fills));

        Timeline bgAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(root.backgroundProperty(), new Background(new BackgroundFill(gradient1, null, null)))),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(root.backgroundProperty(), new Background(new BackgroundFill(gradient2, null, null))))
        );
        bgAnimation.setCycleCount(Animation.INDEFINITE);
        bgAnimation.setAutoReverse(true);
        bgAnimation.play();

        // Heading with animation
        Label heading = new Label("A Big Thank You to Core2Web");
        heading.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 36));
        heading.setTextFill(Color.WHITE);
        heading.setEffect(new DropShadow(10, Color.web("#333")));

        FadeTransition fadeInHeading = new FadeTransition(Duration.seconds(2), heading);
        fadeInHeading.setFromValue(0);
        fadeInHeading.setToValue(1);
        fadeInHeading.play();

        // Profile image
        imageView = new ImageView(new Image("/Assets/Images/image.png"));
        imageView.setFitWidth(180);
        imageView.setFitHeight(180);
        Circle clip = new Circle(90, 90, 90);
        imageView.setClip(clip);

        StackPane imageHolder = new StackPane(imageView);
        imageHolder.setPadding(new Insets(10));
        imageHolder.setStyle(
                "-fx-background-radius: 100;" +
                        "-fx-background-color: linear-gradient(to bottom right, #ffffff, #ccccff);" +
                        "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.3), 15, 0.2, 0, 8);"
        );
        imageHolder.setMaxSize(200, 200);

        // Swap image on click
        imageHolder.setOnMouseClicked((MouseEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imageView.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        FadeTransition fadeImage = new FadeTransition(Duration.seconds(2), imageHolder);
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);
        fadeImage.setDelay(Duration.seconds(0.5));
        fadeImage.play();

        // Name and Mentor Label
        Label nameLabel = new Label("Prof.Shashi Sir");
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.WHITE);

        Label mentorLabel = new Label("Mentor: Manasi Jadhav");
        mentorLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        mentorLabel.setTextFill(Color.WHITE);

        VBox imageSection = new VBox(10, imageHolder, nameLabel, mentorLabel);
        imageSection.setAlignment(Pos.CENTER);

        // Thank You message
        String msg = """
                We want to express our heartfelt thanks to Shashi Sir and the entire Core2Web team for being a constant source of support and encouragement.

                Your teaching, mentorship, and patience have truly helped us grow not just as students, but as individuals with confidence and purpose.

                To every mentor and trainer who guided us, believed in us, and helped us build clarity thank you for shaping our journey.

                This project was made possible through your motivation and support, and we’re proud to share this success with the entire Core2Web family. 
                """;

        Label msgLabel = new Label(msg);
        msgLabel.setWrapText(true);
        msgLabel.setFont(Font.font("Segoe UI", 18));
        msgLabel.setTextFill(Color.web("#2c2c2c"));

        VBox msgCard = new VBox(msgLabel);
        msgCard.setPadding(new Insets(30));
        msgCard.setMaxWidth(850);
        msgCard.setAlignment(Pos.CENTER);
        msgCard.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.8);" +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0.3, 0, 8);"
        );

        msgCard.setTranslateY(800); // off-screen
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(2), msgCard);
        slideIn.setToY(0);
        slideIn.setDelay(Duration.seconds(1));
        slideIn.setInterpolator(Interpolator.EASE_OUT);
        slideIn.play();

        VBox content = new VBox(30, heading, imageSection, msgCard);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40));

        root.getChildren().add(content);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Thank You - Core2Web");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
package com.neurobridge;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;

public class Aboutus extends Application {

    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) {
        // Dynamic animated gradient background
        Stop[] stops1 = new Stop[]{new Stop(0, Color.web("#cb6ce6")), new Stop(1, Color.web("#9547f7"))};
        Stop[] stops2 = new Stop[]{new Stop(0, Color.web("#9547f7")), new Stop(1, Color.web("#cb6ce6"))};

        LinearGradient gradient1 = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops1);
        LinearGradient gradient2 = new LinearGradient(1, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops2);

        BackgroundFill[] fills = {new BackgroundFill(gradient1, CornerRadii.EMPTY, Insets.EMPTY)};
        StackPane root = new StackPane();
        root.setBackground(new Background(fills));

        Timeline bgAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new KeyValue(root.backgroundProperty(), new Background(new BackgroundFill(gradient1, null, null)))),
                new KeyFrame(Duration.seconds(5),
                        new KeyValue(root.backgroundProperty(), new Background(new BackgroundFill(gradient2, null, null))))
        );
        bgAnimation.setCycleCount(Animation.INDEFINITE);
        bgAnimation.setAutoReverse(true);
        bgAnimation.play();

        // Back button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnMouseEntered(e -> backButton.setTextFill(Color.LIGHTGRAY));
        backButton.setOnMouseExited(e -> backButton.setTextFill(Color.WHITE));

        backButton.setOnAction(e -> {
            try {
                new FamilyDashboardScreen().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox topBar = new HBox(backButton);
        topBar.setAlignment(Pos.TOP_LEFT);
        topBar.setPadding(new Insets(20, 0, 0, 20));

        // Heading with animation
        Label heading = new Label("A Big Thank You to Core2Web");
        heading.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 36));
        heading.setTextFill(Color.WHITE);
        heading.setEffect(new DropShadow(10, Color.web("#333")));

        FadeTransition fadeInHeading = new FadeTransition(Duration.seconds(2), heading);
        fadeInHeading.setFromValue(0);
        fadeInHeading.setToValue(1);
        fadeInHeading.play();

        // Profile image
        imageView = new ImageView(new Image("/Assets/Images/image.png"));
        imageView.setFitWidth(180);
        imageView.setFitHeight(180);
        Circle clip = new Circle(90, 90, 90);
        imageView.setClip(clip);

        StackPane imageHolder = new StackPane(imageView);
        imageHolder.setPadding(new Insets(10));
        imageHolder.setStyle(
                "-fx-background-radius: 100;" +
                        "-fx-background-color: linear-gradient(to bottom right, #ffffff, #ccccff);" +
                        "-fx-effect: dropshadow(gaussian, rgba(255,255,255,0.3), 15, 0.2, 0, 8);"
        );
        imageHolder.setMaxSize(200, 200);

        // Swap image on click
        imageHolder.setOnMouseClicked((MouseEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Profile Picture");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                imageView.setImage(new Image(selectedFile.toURI().toString()));
            }
        });

        FadeTransition fadeImage = new FadeTransition(Duration.seconds(2), imageHolder);
        fadeImage.setFromValue(0);
        fadeImage.setToValue(1);
        fadeImage.setDelay(Duration.seconds(0.5));
        fadeImage.play();

        // Name and Mentor Label
        Label nameLabel = new Label("Prof.Shashi Sir");
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.WHITE);

        Label mentorLabel = new Label("Mentor: Manasi Jadhav");
        mentorLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));
        mentorLabel.setTextFill(Color.WHITE);

        VBox imageSection = new VBox(10, imageHolder, nameLabel, mentorLabel);
        imageSection.setAlignment(Pos.CENTER);

        // Thank You message
        String msg = """
                We want to express our heartfelt thanks to Shashi Sir and the entire Core2Web team for being a constant source of support and encouragement.

                Your teaching, mentorship, and patience have truly helped us grow not just as students, but as individuals with confidence and purpose.

                To every mentor and trainer who guided us, believed in us, and helped us build clarity thank you for shaping our journey.

                This project was made possible through your motivation and support, and we’re proud to share this success with the entire Core2Web family. 
                """;

        Label msgLabel = new Label(msg);
        msgLabel.setWrapText(true);
        msgLabel.setFont(Font.font("Segoe UI", 18));
        msgLabel.setTextFill(Color.web("#2c2c2c"));

        VBox msgCard = new VBox(msgLabel);
        msgCard.setPadding(new Insets(30));
        msgCard.setMaxWidth(850);
        msgCard.setAlignment(Pos.CENTER);
        msgCard.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.8);" +
                        "-fx-background-radius: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0.3, 0, 8);"
        );

        msgCard.setTranslateY(800); // off-screen
        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(2), msgCard);
        slideIn.setToY(0);
        slideIn.setDelay(Duration.seconds(1));
        slideIn.setInterpolator(Interpolator.EASE_OUT);
        slideIn.play();

        VBox content = new VBox(30, topBar, heading, imageSection, msgCard);
        content.setAlignment(Pos.TOP_CENTER);
        content.setPadding(new Insets(40));

        root.getChildren().add(content);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Thank You - Core2Web");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

