package com.neurobridge;



import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class QuizUploader extends Application {

    private final String patientId = UserSession.getInstance().getPatientId();

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.init();
        Firestore db = FirebaseInitializer.getDB();

        Label title = new Label("🧠 Upload Level 3 Quiz");
        title.setFont(Font.font("Arial", 24));
        title.setTextFill(Color.web("#ffffff"));

        Label subtitle = new Label("Create multiple choice questions for the memory game");
        subtitle.setFont(Font.font("Arial", 14));
        subtitle.setTextFill(Color.web("#eeeeee"));

        VBox titleBox = new VBox(5, title, subtitle);
        titleBox.setAlignment(Pos.CENTER);

        TextField questionField = new TextField();
        TextField optionAField = new TextField();
        TextField optionBField = new TextField();
        TextField optionCField = new TextField();
        TextField optionDField = new TextField();
        TextField correctField = new TextField();

        VBox form = new VBox(14,
                createLabeledField("Question", questionField),
                createLabeledField("Option A", optionAField),
                createLabeledField("Option B", optionBField),
                createLabeledField("Option C", optionCField),
                createLabeledField("Option D", optionDField),
                createLabeledField("Correct Answer (A/B/C/D)", correctField)
        );
        form.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        form.setPadding(new Insets(30));
        form.setAlignment(Pos.CENTER_LEFT);
        form.setMaxWidth(600);
        form.setEffect(new DropShadow(12, Color.rgb(0, 0, 0, 0.1)));

        Button uploadButton = new Button("🚀 Upload Quiz");
        uploadButton.setStyle("-fx-background-color: #cdb4db; -fx-text-fill: black; -fx-font-weight: bold; -fx-background-radius: 18; -fx-padding: 10 20; -fx-cursor: hand;");
        uploadButton.setOnAction(e -> {
            if (questionField.getText().isEmpty() ||
                optionAField.getText().isEmpty() ||
                optionBField.getText().isEmpty() ||
                optionCField.getText().isEmpty() ||
                optionDField.getText().isEmpty() ||
                correctField.getText().isEmpty()) {

                showAlert(Alert.AlertType.ERROR, "All fields are required!");
                return;
            }

            Map<String, Object> data = new HashMap<>();
            data.put("question", questionField.getText().trim());
            data.put("optionA", optionAField.getText().trim());
            data.put("optionB", optionBField.getText().trim());
            data.put("optionC", optionCField.getText().trim());
            data.put("optionD", optionDField.getText().trim());
            data.put("correctAnswer", correctField.getText().trim().toUpperCase());

            new Thread(() -> {
                try {
                    ApiFuture<DocumentReference> future = db.collection("memory_game_data")
                            .document(patientId)
                            .collection("level_3")
                            .add(data);

                    future.get();

                    javafx.application.Platform.runLater(() -> {
                        showAlert(Alert.AlertType.INFORMATION, "✅ Quiz uploaded successfully!");
                        questionField.clear();
                        optionAField.clear();
                        optionBField.clear();
                        optionCField.clear();
                        optionDField.clear();
                        correctField.clear();
                    });

                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                    javafx.application.Platform.runLater(() ->
                            showAlert(Alert.AlertType.ERROR, "❌ Upload failed: " + ex.getMessage()));
                }
            }).start();
        });

        Button backButton = new Button("Back");
        backButton.setStyle(buttonStyle("#bde0fe"));
        backButton.setOnAction(e -> {
        try {
            new FamilyDashboardScreen().start(new Stage());
            ((Stage) backButton.getScene().getWindow()).close();
            } catch (Exception ex) {
           ex.printStackTrace();
       }
});

        VBox card = new VBox(20,backButton, titleBox, form, uploadButton);
        card.setAlignment(Pos.TOP_CENTER);

        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        };
        BackgroundFill bgFill = new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops),
                CornerRadii.EMPTY,
                Insets.EMPTY
        );

        StackPane root = new StackPane(card);
        root.setBackground(new Background(bgFill));
        root.setPadding(new Insets(60));

        Scene scene = new Scene(root, 1500, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("NeuroBridge - Quiz Uploader");
        primaryStage.show();
    }

    private VBox createLabeledField(String labelText, TextField textField) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        textField.setPromptText(labelText);
        textField.setPrefHeight(35);
        textField.setStyle("-fx-background-radius: 6; -fx-border-color: #dcdcdc;");
        return new VBox(4, label, textField);
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type, message, ButtonType.OK);
        alert.showAndWait();
    }

    private String buttonStyle(String color) {
    return "-fx-background-color: " + color + ";" +
           "-fx-text-fill: black;" +
           "-fx-font-weight: bold;" +
           "-fx-background-radius: 18;" +
           "-fx-padding: 10 20;" +
           "-fx-cursor: hand;";
    }


    public static void main(String[] args) {
        launch(args);
    }
}

