/*package com.neurobridge;





import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Splashscreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load brain image
        //Image brainImage = new Image(getClass().getResource("/Assets/Images/Splash.png").toExternalForm());
        //Image brainImage = new Image(getClass().getResource("/Assets/Images/Splash.png").toExternalForm());
        java.net.URL imageUrl = getClass().getResource("/Assets/Images/Splash.png");

   if (imageUrl == null) {
    System.err.println("❌ Image not found: /Assets/Images/Splash.png");
    return;
   }

Image brainImage = new Image(imageUrl.toExternalForm());


        
        //brainView.setPreserveRatio(true);
        //brainView.setFitWidth(150);
        //brainView.setOpacity(0);

        /*brainView.setPreserveRatio(true);
        brainView.setFitWidth(180);        // Slightly bigger
        brainView.setTranslateY(-60);      // Move it up visually
        brainView.setOpacity(0);


        // App name
        Label appName = new Label("NEUROBRIDGE");
        appName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        appName.setTextFill(Color.WHITE);
        appName.setEffect(new DropShadow(30, Color.web("#f38fd099")));
        appName.setOpacity(0);

        // Tagline
        Label tagline = new Label("Connecting Memories");
        tagline.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 20));
        tagline.setTextFill(Color.web("#ffffffcc"));
        tagline.setOpacity(0);
        tagline.setTranslateY(10);

        //VBox content = new VBox(40, brainView, appName, tagline);
        //content.setAlignment(Pos.CENTER);

        VBox textBox = new VBox(10, appName, tagline);
        textBox.setAlignment(Pos.CENTER);
        textBox.setPadding(new Insets(30, 0, 0, 0)); // push text away from image

        VBox content = new VBox(60, brainView, textBox); // more spacing between image and text
        content.setAlignment(Pos.CENTER);


        // Background gradient
        RadialGradient gradient = new RadialGradient(
                0, 0, 0.5, 0.5, 1,
                true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        );

        StackPane root = new StackPane(content);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));*/


        // Image setup
       // Brain Image
// Brain image
// Brain image
/*ImageView brainView = new ImageView(brainImage);
brainView.setPreserveRatio(true);
brainView.setFitWidth(180); // Adjust as needed
brainView.setOpacity(0);

// App name
Label appName = new Label("NEUROBRIDGE");
appName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
appName.setTextFill(Color.WHITE);
appName.setEffect(new DropShadow(30, Color.web("#f38fd099")));
appName.setOpacity(0);

// Tagline
Label tagline = new Label("Connecting Memories");
tagline.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 20));
tagline.setTextFill(Color.web("#ffffffcc"));
tagline.setOpacity(0);

// VBox that stacks everything with tight spacing
VBox content = new VBox(50, brainView, appName, tagline); // 20px spacing
content.setAlignment(Pos.CENTER); // Centered vertically and horizontally

// Background gradient
RadialGradient gradient = new RadialGradient(
        0, 0, 0.5, 0.5, 1,
        true, CycleMethod.NO_CYCLE,
        new Stop(0, Color.web("#f38fd0")),
        new Stop(1, Color.web("#a18fff"))
);

StackPane root = new StackPane(content);
root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

Scene scene = new Scene(root, 1550, 800);
primaryStage.setScene(scene);
primaryStage.setTitle("NeuroBridge - Splash");
primaryStage.show();


// Scene setup



     
        // --- Entry Animations ---
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(2), brainView);
        scaleIn.setFromX(1.0);
        scaleIn.setFromY(1.0);
        scaleIn.setToX(2.5);
        scaleIn.setToY(2.5);
        scaleIn.setInterpolator(Interpolator.EASE_BOTH);

        FadeTransition fadeInBrain = new FadeTransition(Duration.seconds(2), brainView);
        fadeInBrain.setFromValue(0);
        fadeInBrain.setToValue(1);

        ParallelTransition brainAppear = new ParallelTransition(scaleIn, fadeInBrain);

        FadeTransition fadeText = new FadeTransition(Duration.seconds(1.6), appName);
        fadeText.setFromValue(0);
        fadeText.setToValue(1);

        TranslateTransition slideText = new TranslateTransition(Duration.seconds(1.3), appName);
        slideText.setFromY(30);
        slideText.setToY(0);
        slideText.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition titleEntry = new SequentialTransition(
                new PauseTransition(Duration.seconds(1.2)),
                new ParallelTransition(fadeText, slideText)
        );

        FadeTransition fadeTag = new FadeTransition(Duration.seconds(1.4), tagline);
        fadeTag.setFromValue(0);
        fadeTag.setToValue(1);

        TranslateTransition slideTag = new TranslateTransition(Duration.seconds(1.2), tagline);
        slideTag.setFromY(20);
        slideTag.setToY(0);
        slideTag.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition taglineEntry = new SequentialTransition(
                new PauseTransition(Duration.seconds(2.3)),
                new ParallelTransition(fadeTag, slideTag)
        );

        brainAppear.play();
        titleEntry.play();
        taglineEntry.play();

        // --- Exit Animation ---
        brainAppear.setOnFinished(e -> {
            ScaleTransition zoomOut = new ScaleTransition(Duration.seconds(1.8), brainView);
            zoomOut.setFromX(2.5);
            zoomOut.setFromY(2.5);
            zoomOut.setToX(4.5);
            zoomOut.setToY(4.5);
            zoomOut.setInterpolator(Interpolator.EASE_IN);

            FadeTransition fadeOutBrain = new FadeTransition(Duration.seconds(1.8), brainView);
            fadeOutBrain.setFromValue(1.0);
            fadeOutBrain.setToValue(0.0);

            FadeTransition fadeOutText = new FadeTransition(Duration.seconds(1.6), appName);
            fadeOutText.setFromValue(1.0);
            fadeOutText.setToValue(0.0);

            FadeTransition fadeOutTagline = new FadeTransition(Duration.seconds(1.6), tagline);
            fadeOutTagline.setFromValue(1.0);
            fadeOutTagline.setToValue(0.0);

            ParallelTransition exitAnim = new ParallelTransition(
                    zoomOut,
                    fadeOutBrain,
                    fadeOutText,
                    fadeOutTagline
            );

            exitAnim.setDelay(Duration.seconds(1.5));

            exitAnim.setOnFinished(event -> {
                System.out.println("Splash complete — Launching Signup...");
                loadSignupScene(primaryStage);
            });

            exitAnim.play();
        });
    }

    private void loadSignupScene(Stage stage) {
        try {
            new Signup().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
}*/

package com.neurobridge;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Splashscreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load brain image
        java.net.URL imageUrl = getClass().getResource("/Assets/Images/Splash.png");
        if (imageUrl == null) {
            System.err.println("❌ Image not found: /Assets/Images/Splash.png");
            return;
        }

        Image brainImage = new Image(imageUrl.toExternalForm());
        ImageView brainView = new ImageView(brainImage);
        brainView.setPreserveRatio(true);
        brainView.setFitWidth(200); // Adjust width as needed
        brainView.setOpacity(0);

        // App Name
        Label appName = new Label("NEUROBRIDGE");
        appName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        appName.setTextFill(Color.WHITE);
        appName.setEffect(new DropShadow(30, Color.web("#f38fd099")));
        appName.setOpacity(0);

        // Tagline
        Label tagline = new Label("Connecting Memories");
        tagline.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 20));
        tagline.setTextFill(Color.web("#ffffffcc"));
        tagline.setOpacity(0);

        // VBox for Text with spacing
        VBox textBox = new VBox(10, appName, tagline);
        textBox.setAlignment(Pos.TOP_CENTER);
        textBox.setPadding(new Insets(20, 0, 0, 0)); // Push text slightly below image

        // Combine image and text (text is under image)
        VBox content = new VBox(30, brainView, textBox);
        content.setAlignment(Pos.CENTER);

        // Background gradient
        RadialGradient gradient = new RadialGradient(
                0, 0, 0.5, 0.5, 1,
                true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        );

        StackPane root = new StackPane(content);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NeuroBridge - Splash");
        primaryStage.show();

        // Animations
        ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(2), brainView);
        scaleIn.setFromX(1.0);
        scaleIn.setFromY(1.0);
        scaleIn.setToX(2.5);
        scaleIn.setToY(2.5);
        scaleIn.setInterpolator(Interpolator.EASE_BOTH);

        FadeTransition fadeInBrain = new FadeTransition(Duration.seconds(2), brainView);
        fadeInBrain.setFromValue(0);
        fadeInBrain.setToValue(1);

        ParallelTransition brainAppear = new ParallelTransition(scaleIn, fadeInBrain);

        FadeTransition fadeText = new FadeTransition(Duration.seconds(1.6), appName);
        fadeText.setFromValue(0);
        fadeText.setToValue(1);

        TranslateTransition slideText = new TranslateTransition(Duration.seconds(1.3), appName);
        slideText.setFromY(30);
        slideText.setToY(0);
        slideText.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition titleEntry = new SequentialTransition(
                new PauseTransition(Duration.seconds(1.2)),
                new ParallelTransition(fadeText, slideText)
        );

        FadeTransition fadeTag = new FadeTransition(Duration.seconds(1.4), tagline);
        fadeTag.setFromValue(0);
        fadeTag.setToValue(1);

        TranslateTransition slideTag = new TranslateTransition(Duration.seconds(1.2), tagline);
        slideTag.setFromY(20);
        slideTag.setToY(0);
        slideTag.setInterpolator(Interpolator.EASE_OUT);

        SequentialTransition taglineEntry = new SequentialTransition(
                new PauseTransition(Duration.seconds(2.3)),
                new ParallelTransition(fadeTag, slideTag)
        );

        brainAppear.play();
        titleEntry.play();
        taglineEntry.play();

        // Exit Animation
        brainAppear.setOnFinished(e -> {
            ScaleTransition zoomOut = new ScaleTransition(Duration.seconds(1.8), brainView);
            zoomOut.setFromX(2.5);
            zoomOut.setFromY(2.5);
            zoomOut.setToX(4.5);
            zoomOut.setToY(4.5);
            zoomOut.setInterpolator(Interpolator.EASE_IN);

            FadeTransition fadeOutBrain = new FadeTransition(Duration.seconds(1.8), brainView);
            fadeOutBrain.setFromValue(1.0);
            fadeOutBrain.setToValue(0.0);

            FadeTransition fadeOutText = new FadeTransition(Duration.seconds(1.6), appName);
            fadeOutText.setFromValue(1.0);
            fadeOutText.setToValue(0.0);

            FadeTransition fadeOutTagline = new FadeTransition(Duration.seconds(1.6), tagline);
            fadeOutTagline.setFromValue(1.0);
            fadeOutTagline.setToValue(0.0);

            ParallelTransition exitAnim = new ParallelTransition(
                    zoomOut,
                    fadeOutBrain,
                    fadeOutText,
                    fadeOutTagline
            );

            exitAnim.setDelay(Duration.seconds(1.5));
            exitAnim.setOnFinished(event -> {
                System.out.println("Splash complete — Launching Signup...");
                loadSignupScene(primaryStage);
            });

            exitAnim.play();
        });
    }

    private void loadSignupScene(Stage stage) {
        try {
            new Signup().start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
