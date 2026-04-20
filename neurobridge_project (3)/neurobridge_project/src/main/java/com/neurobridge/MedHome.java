/*package com.neurobridge;


import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MedHome extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 🌸 Background gradient
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8bbd0, #f3e5f5);");

        // 🌷 Blended card with larger padding and width
        VBox card = new VBox(25); // more spacing between items
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(50)); // increased padding
        card.setMaxWidth(450); // increased card width

        // 🌺 Matching card color with transparency and soft shadows
        card.setStyle("""
            -fx-background-color: rgba(255, 240, 250, 0.65);
            -fx-background-radius: 35;
            -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 20, 0.1, 0, 6);
        """);

        // 🧘‍♀️ Larger Image
        Image image = new Image(getClass().getResource("/Assets/Images/mediation.jpeg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(320); // increased from 260
        imageView.setPreserveRatio(true);

        // 🌟 Title (quote)
        Label title = new Label("Meditation reveals \nyour true self");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 26)); // larger font
        title.setTextFill(Color.web("#4A148C"));
        title.setTextAlignment(TextAlignment.CENTER);

        // 🌸 Subtitle
        Label subtitle = new Label("Learn to live happier.");
        subtitle.setFont(Font.font("Poppins", FontWeight.NORMAL, 16));
        subtitle.setTextFill(Color.web("#6A1B9A"));

        // 💖 Button
        Button getStarted = new Button("GET STARTED");
        getStarted.setStyle("""
            -fx-background-color: #F06292;
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-background-radius: 20;
            -fx-padding: 12 24;
        """);
        getStarted.setOnAction(e -> {
      try {
        MeditationModule module = new MeditationModule();
        module.start(primaryStage); // Switches to the meditation screen using the same stage
      } catch (Exception ex) {
        ex.printStackTrace();
      }
     });
      
     Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #8A2BE2; -fx-background-radius: 20;");
        backButton.setPadding(new Insets(8, 18, 8, 18));
        backButton.setOnAction(e -> {
            try {
                new DashboardScreen().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 📦 Assemble UI
        card.getChildren().addAll(imageView, title, subtitle, getStarted);
        root.getChildren().add(card);

        // 🎬 Scene setup
        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Mindfulness App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}*/

package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MedHome extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 🌸 Background gradient
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8bbd0, #f3e5f5);");

        // 🌷 Blended card with larger padding and width
        VBox card = new VBox(25);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(50));
        card.setMaxWidth(450);

        card.setStyle("""
            -fx-background-color: rgba(255, 240, 250, 0.65);
            -fx-background-radius: 35;
            -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 20, 0.1, 0, 6);
        """);

        // 🧘‍♀️ Larger Image
        Image image = new Image(getClass().getResource("/Assets/Images/meditation.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(320);
        imageView.setPreserveRatio(true);

        // 🌟 Title
        Label title = new Label("Meditation reveals \nyour true self");
        title.setFont(Font.font("Poppins", FontWeight.BOLD, 26));
        title.setTextFill(Color.web("#4A148C"));
        title.setTextAlignment(TextAlignment.CENTER);

        // 🌸 Subtitle
        Label subtitle = new Label("Learn to live happier.");
        subtitle.setFont(Font.font("Poppins", FontWeight.NORMAL, 16));
        subtitle.setTextFill(Color.web("#6A1B9A"));

        // 💖 Get Started Button
        Button getStarted = new Button("GET STARTED");
        getStarted.setStyle("""
            -fx-background-color: #F06292;
            -fx-text-fill: white;
            -fx-font-size: 15px;
            -fx-background-radius: 20;
            -fx-padding: 12 24;
        """);
        getStarted.setOnAction(e -> {
            try {
                MeditationModule module = new MeditationModule();
                module.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // 🔙 Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #8A2BE2; -fx-background-radius: 20;");
        backButton.setPadding(new Insets(8, 18, 8, 18));
        backButton.setOnAction(e -> {
            try {
                new DashboardScreen().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Assemble UI
        card.getChildren().addAll(imageView, title, subtitle, getStarted);
        StackPane centerPane = new StackPane(card);
        root.setCenter(centerPane);

        HBox backContainer = new HBox(backButton);
        backContainer.setAlignment(Pos.CENTER_LEFT);
        backContainer.setPadding(new Insets(20, 0, 20, 20));
        root.setBottom(backContainer);

        // 🎬 Scene setup
        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Mindfulness App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

