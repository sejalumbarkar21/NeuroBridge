package com.neurobridge;


import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class Level4 extends Application {

    private int currentIndex = 0;

    private final List<String> messages = List.of(
            "We love you always, Dad.",
            "Your smile still lights up our home.",
            "You taught us how to love and laugh."
    );

    private final List<String> senders = List.of(
            "- Your Daughter Asha",
            "- Your Wife Meena",
            "- Your Son Rohit"
    );

    private Label messageLabel;
    private Label senderLabel;
    private StackPane messagePane;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #FDF6EC, #EBD4CB);");

        // Title
        Text title = new Text("Messages That Matter");
        title.setFont(Font.font("Segoe Script", 40));
        title.setFill(Color.DARKSLATEBLUE);
        VBox titleBox = new VBox(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20));

        // Image Display
        ImageView photo;
        try {
            photo = new ImageView(new Image(getClass().getResource("Assets/Images/m1.jpeg").toExternalForm()));
        } catch (Exception ex) {
            System.out.println("Image not found, loading placeholder.");
            photo = new ImageView(new Image("https://via.placeholder.com/600x250.png?text=Memory+Image"));
        }
        photo.setFitHeight(250);
        photo.setPreserveRatio(true);

        // Message Area
        messageLabel = new Label();
        messageLabel.setFont(Font.font("Dancing Script", FontPosture.REGULAR, 28));
        messageLabel.setWrapText(true);
        messageLabel.setTextFill(Color.DARKSLATEGRAY);

        senderLabel = new Label();
        senderLabel.setFont(Font.font("Comic Sans MS", FontPosture.ITALIC, 20));
        senderLabel.setTextFill(Color.SLATEGRAY);

        messagePane = new StackPane();
        VBox messageBox = new VBox(15, messageLabel, senderLabel);
        messageBox.setAlignment(Pos.CENTER);
        messageBox.setPadding(new Insets(20));
        messagePane.getChildren().add(messageBox);

        FadeTransition ft = new FadeTransition(Duration.seconds(1), messagePane);
        ft.setFromValue(0);
        ft.setToValue(1);

        showMessage(ft);

        // Buttons
        Button backBtn = new Button("⟵ Back");
        Button nextBtn = new Button("Finish ⟶");
        backBtn.setStyle("-fx-background-color: #D3BCCC; -fx-font-size: 14px; -fx-font-weight: bold;");
        nextBtn.setStyle("-fx-background-color: #D3BCCC; -fx-font-size: 14px; -fx-font-weight: bold;");

        backBtn.setOnAction(e -> {
            try {
                new L4Game().start(stage); // Navigate to L4Game
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        nextBtn.setOnAction(e -> {
            try {
                new DashboardScreen().start(stage); // Navigate to Dashboard
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox buttonBox = new HBox(20, backBtn, nextBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        // Score
        Label scoreLabel = new Label("Score: " + messages.size() + "/" + messages.size());
        scoreLabel.setFont(Font.font("Verdana", FontPosture.REGULAR, 18));
        scoreLabel.setTextFill(Color.DARKBLUE);
        scoreLabel.setPadding(new Insets(15));
        scoreLabel.setAlignment(Pos.CENTER_RIGHT);

        VBox bottomBox = new VBox(buttonBox, scoreLabel);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(10);
        bottomBox.setPadding(new Insets(10));

        VBox centerBox = new VBox(40, photo, messagePane);
        centerBox.setAlignment(Pos.TOP_CENTER);
        centerBox.setPadding(new Insets(30));

        root.setTop(titleBox);
        root.setCenter(centerBox);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 1550, 700);
        stage.setScene(scene);
        stage.setTitle("Level 4 - Messages That Matter");
        stage.show();
    }

    private void showMessage(FadeTransition ft) {
        messageLabel.setText(messages.get(currentIndex));
        senderLabel.setText(senders.get(currentIndex));
        ft.playFromStart();
    }
}