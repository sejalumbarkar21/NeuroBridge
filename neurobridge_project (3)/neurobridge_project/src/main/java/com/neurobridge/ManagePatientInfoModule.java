package com.neurobridge;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.Firestore;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ManagePatientInfoModule extends Application {

    private final String patientId = "abc123"; // Set dynamically if needed

    private TextField nameField, ageField, genderField, conditionField, medicationField;
    private TextField hobbiesField, contactField, addressField, guardianNameField, relationshipField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializeFirebase();

        Label title = new Label("👤 Manage Patient Info");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #552a5b;");

        Button backButton = new Button("← Back");
        backButton.setStyle(buttonStyle("#bde0fe"));
        backButton.setOnAction(e -> {
        try {
            new FamilyDashboardScreen().start(new Stage());
            ((Stage) backButton.getScene().getWindow()).close();
            } catch (Exception ex) {
           ex.printStackTrace();
       }
   });

        nameField = createField("Full Name");
        ageField = createField("Age");
        genderField = createField("Gender");
        conditionField = createField("Medical Condition");
        medicationField = createField("Current Medications");
        hobbiesField = createField("Hobbies / Interests");
        contactField = createField("Emergency Contact");
        addressField = createField("Address");
        guardianNameField = createField("Guardian Name");
        relationshipField = createField("Patient Id");

        Button saveBtn = new Button("💾 Save Info");
        saveBtn.setStyle(buttonStyle("#a18fff"));
        saveBtn.setOnAction(e -> saveToFirebase());

        VBox form = new VBox(12,
                title,
                nameField, ageField, genderField, conditionField, medicationField,
                hobbiesField, contactField, addressField,
                guardianNameField, relationshipField,
                saveBtn,backButton
        );
        form.setAlignment(Pos.CENTER);
        form.setPadding(new Insets(30));
        form.setStyle("-fx-background-color: white; -fx-background-radius: 20;");
        form.setEffect(new DropShadow(10, Color.rgb(100, 100, 100, 0.1)));
        form.setMaxWidth(600);

        StackPane root = new StackPane(form);
        root.setPadding(new Insets(50));
        root.setAlignment(Pos.CENTER);

        Stop[] stops = new Stop[] {
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Manage Patient Info");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField createField(String prompt) {
        TextField field = new TextField();
        field.setPromptText(prompt);
        field.setStyle("-fx-background-radius: 10; -fx-padding: 10;");
        field.setMaxWidth(400);
        return field;
    }

    private String buttonStyle(String bgColor) {
        return "-fx-background-color: " + bgColor + ";" +
                "-fx-text-fill: black;" +
                "-fx-font-weight: bold;" +
                "-fx-background-radius: 20;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 10 20;";
    }

    private void saveToFirebase() {
    try {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> data = new HashMap<>();
        data.put("name", nameField.getText().trim());
        data.put("age", ageField.getText().trim());
        data.put("gender", genderField.getText().trim());
        data.put("condition", conditionField.getText().trim());
        data.put("medications", medicationField.getText().trim());
        data.put("hobbies", hobbiesField.getText().trim());
        data.put("emergency_contact", contactField.getText().trim());
        data.put("address", addressField.getText().trim());
        data.put("guardian_name", guardianNameField.getText().trim());
        data.put("relationship", relationshipField.getText().trim());

        String patientIdEntered = relationshipField.getText().trim();

        if (patientIdEntered.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Missing Patient ID", "⚠ Please enter a Patient ID to save the data.");
            return;
        }

        db.collection("ManagePatientInfoData").document(patientIdEntered).set(data);

        showAlert(Alert.AlertType.INFORMATION, "Success", "✅ Patient information saved successfully!");

    } catch (Exception e) {
        e.printStackTrace();
        showAlert(Alert.AlertType.ERROR, "Error", "❌ " + e.getMessage());
    }
}
    private void showAlert(Alert.AlertType type, String title, String message) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


    private void initializeFirebase() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("firebase-service-account.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}