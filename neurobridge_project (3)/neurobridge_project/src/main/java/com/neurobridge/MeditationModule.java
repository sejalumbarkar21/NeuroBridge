/*package com.neurobridge;


import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class MeditationModule extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 🌸 Root layout
        VBox root = new VBox(30);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f3e5f5, #fce4ec);");
        root.setAlignment(Pos.TOP_CENTER);

        // 🖼️ Top banner image (full-width)
        Image image = new Image(getClass().getResource("/Assets/Images/med_banner.jpeg").toExternalForm());
        ImageView banner = new ImageView(image);
        banner.setFitHeight(400);
        banner.setFitWidth(800);
        banner.setPreserveRatio(false);
        banner.setSmooth(true);
        banner.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 5); -fx-background-radius: 20;");

        // 🌟 Title/
        Label exploreTitle = new Label("Explore Meditation");
        exploreTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 36));
        exploreTitle.setTextFill(Color.web("#6A1B9A"));

        // 🧘‍♂️ Button row: Focus | Calm | Relax
        HBox categoryBox = new HBox(40);
        categoryBox.setAlignment(Pos.CENTER);
        categoryBox.setPadding(new Insets(10, 0, 0, 0));

        Button focus = new Button("Focus");
        Button calm = new Button("Calm");
        Button relax = new Button("Relax");
        focus.setOnAction(e -> {
            try {
                new Focus().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        calm.setOnAction(e -> {
            try {
                new Calm().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        relax.setOnAction(e -> {
            try {
                new Relax().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        for (Button b : List.of(focus, calm, relax)) {
            b.setStyle("""
                -fx-background-color: #BA68C8;
                -fx-text-fill: white;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-background-radius: 30;
                -fx-padding: 15 35;
            """);

            // 🖱️ Hover effect
            b.setOnMouseEntered(e -> b.setStyle(b.getStyle() + "-fx-background-color: #AB47BC;"));
            b.setOnMouseExited(e -> b.setStyle("""
                -fx-background-color: #BA68C8;
                -fx-text-fill: white;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-background-radius: 30;
                -fx-padding: 15 35;
            """));
        }

        categoryBox.getChildren().addAll(focus, calm, relax);

        // 💬 Subtitle / instructions
        Label comingSoon = new Label("Tap a category to begin your mindful journey.");
        comingSoon.setFont(Font.font("Poppins", FontWeight.NORMAL, 18));
        comingSoon.setTextFill(Color.web("#7B1FA2"));
        comingSoon.setPadding(new Insets(10, 0, 0, 0));

        // 🔗 Assemble layout
        root.getChildren().addAll(banner, exploreTitle, categoryBox, comingSoon);

        // 🎬 Final scene setup
        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Meditation Module");
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.List;

public class MeditationModule extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 🌸 Root layout
        VBox root = new VBox(30);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f3e5f5, #fce4ec);");
        root.setAlignment(Pos.TOP_CENTER);

        // ← Back button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        backButton.setTextFill(Color.web("#6A1B9A"));
        backButton.setStyle("-fx-background-color: transparent;");
        backButton.setOnMouseEntered(e -> backButton.setTextFill(Color.web("#4A148C")));
        backButton.setOnMouseExited(e -> backButton.setTextFill(Color.web("#6A1B9A")));

        backButton.setOnAction(e -> {
            try {
                new DashboardScreen().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox backBar = new HBox(backButton);
        backBar.setAlignment(Pos.TOP_LEFT);
        backBar.setPadding(new Insets(0, 0, 10, 0));
        root.getChildren().add(backBar);

        // 🖼️ Top banner image (full-width)
        Image image = new Image(getClass().getResource("/Assets/Images/med_banner.jpeg").toExternalForm());
        ImageView banner = new ImageView(image);
        banner.setFitHeight(400);
        banner.setFitWidth(800);
        banner.setPreserveRatio(false);
        banner.setSmooth(true);
        banner.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 5); -fx-background-radius: 20;");

        // 🌟 Title/
        Label exploreTitle = new Label("Explore Meditation");
        exploreTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 36));
        exploreTitle.setTextFill(Color.web("#6A1B9A"));

        // 🧘‍♂️ Button row: Focus | Calm | Relax
        HBox categoryBox = new HBox(40);
        categoryBox.setAlignment(Pos.CENTER);
        categoryBox.setPadding(new Insets(10, 0, 0, 0));

        Button focus = new Button("Focus");
        Button calm = new Button("Calm");
        Button relax = new Button("Relax");
        focus.setOnAction(e -> {
            try {
                new Focus().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        calm.setOnAction(e -> {
            try {
                new Calm().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        relax.setOnAction(e -> {
            try {
                new Relax().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        for (Button b : List.of(focus, calm, relax)) {
            b.setStyle("""
                -fx-background-color: #BA68C8;
                -fx-text-fill: white;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-background-radius: 30;
                -fx-padding: 15 35;
            """);

            // 🖱️ Hover effect
            b.setOnMouseEntered(e -> b.setStyle(b.getStyle() + "-fx-background-color: #AB47BC;"));
            b.setOnMouseExited(e -> b.setStyle("""
                -fx-background-color: #BA68C8;
                -fx-text-fill: white;
                -fx-font-size: 18px;
                -fx-font-weight: bold;
                -fx-background-radius: 30;
                -fx-padding: 15 35;
            """));
        }

        categoryBox.getChildren().addAll(focus, calm, relax);

        // 💬 Subtitle / instructions
        Label comingSoon = new Label("Tap a category to begin your mindful journey.");
        comingSoon.setFont(Font.font("Poppins", FontWeight.NORMAL, 18));
        comingSoon.setTextFill(Color.web("#7B1FA2"));
        comingSoon.setPadding(new Insets(10, 0, 0, 0));

        // 🔗 Assemble layout
        root.getChildren().addAll(banner, exploreTitle, categoryBox, comingSoon);

        // 🎬 Final scene setup
        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Meditation Module");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
