package com.neurobridge;


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class HospitalDetailView extends Application {

    private String name;
    private String location;
    private String phone;
    private String email;
    private String description;

    public HospitalDetailView() {
        this("Sunrise Alzheimer’s Clinic", "Mumbai, Maharashtra", "+91-9876543210", "sunrise@hospital.com",
                "Sunrise Alzheimer’s Clinic is one of the top institutions in India providing care, treatment, and research services for Alzheimer's and dementia-related conditions. With expert doctors, a dedicated care team, and modern infrastructure, we focus on holistic memory care.");
    }

    public HospitalDetailView(String name, String location, String phone, String email, String description) {
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.description = description;
    }

    @Override
    public void start(Stage primaryStage) {
        showHospitalDetail(primaryStage, name, location, phone, email, description);
    }

    public static void showHospitalDetail(Stage owner, String name, String location, String phone, String email, String description) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(owner);

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #f0f4f7;");
        root.setAlignment(Pos.TOP_CENTER);

        ImageView icon = new ImageView(new Image("https://cdn-icons-png.flaticon.com/512/387/387561.png", 100, 100, true, true));
        icon.setSmooth(true);

        Label title = new Label(name);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#2d3436"));

        Label subTitle = new Label(location);
        subTitle.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        subTitle.setTextFill(Color.web("#636e72"));

        Separator separator = new Separator();

        VBox infoCard = new VBox(20);
        infoCard.setPadding(new Insets(25));
        infoCard.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 12, 0.2, 0, 4);");
        infoCard.setMaxWidth(500);

        Label aboutLabel = new Label("🏥 About Hospital");
        aboutLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 18));

        TextArea aboutText = new TextArea(description);
        aboutText.setWrapText(true);
        aboutText.setEditable(false);
        aboutText.setPrefRowCount(6);
        aboutText.setStyle("-fx-font-size: 14; -fx-control-inner-background: #f8f8f8; -fx-background-radius: 6;");
        aboutText.setMaxWidth(Double.MAX_VALUE);

        HBox phoneBox = createInfoRow("📞 Phone", phone);
        HBox emailBox = createInfoRow("✉ Email", email);
        HBox locationBox = createInfoRow("📍 Location", location);

        infoCard.getChildren().addAll(aboutLabel, aboutText, phoneBox, emailBox, locationBox);

        HBox buttons = new HBox(15);
        buttons.setAlignment(Pos.CENTER);

        Button backBtn = new Button("← Back");
        backBtn.setStyle("-fx-background-color: #dcdde1; -fx-text-fill: #2d3436; -fx-background-radius: 6;");
        backBtn.setOnAction(e -> stage.close());

        Button bookBtn = new Button("Book Appointment");
        bookBtn.setStyle("-fx-background-color: #0984e3; -fx-text-fill: white; -fx-background-radius: 6;");
        bookBtn.setOnAction(e -> showBookingForm(stage, name, location, phone, email));

        buttons.getChildren().addAll(backBtn, bookBtn);

        root.getChildren().addAll(icon, title, subTitle, separator, infoCard, buttons);

        Scene scene = new Scene(root, 580, 670);
        stage.setTitle("Hospital Details");
        stage.setScene(scene);
        stage.show();
    }

    private static HBox createInfoRow(String label, String value) {
        Label keyLabel = new Label(label + ": ");
        keyLabel.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 15));
        keyLabel.setTextFill(Color.web("#2d3436"));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 15));
        valueLabel.setTextFill(Color.web("#2c3e50"));

        HBox row = new HBox(5, keyLabel, valueLabel);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private static void showBookingForm(Stage parentStage, String hospitalName, String location, String phone, String email) {
        Stage bookingStage = new Stage();
        bookingStage.initModality(Modality.APPLICATION_MODAL);
        bookingStage.initOwner(parentStage);

        VBox form = new VBox(15);
        form.setPadding(new Insets(25));
        form.setStyle("-fx-background-color: #ffffff;");
        form.setAlignment(Pos.CENTER);

        TextField nameField = new TextField();
        nameField.setPromptText("Your Full Name");

        TextField ageField = new TextField();
        ageField.setPromptText("Age");

        TextField dateField = new TextField();
        dateField.setPromptText("Preferred Date (YYYY-MM-DD)");

        TextField timeField = new TextField();
        timeField.setPromptText("Preferred Time (HH:MM)");

        Button submitBtn = new Button("Confirm Booking");
        submitBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white;");
        submitBtn.setOnAction(e -> {
            String userName = nameField.getText();
            String age = ageField.getText();
            String date = dateField.getText();
            String time = timeField.getText();

            if (userName.isEmpty() || age.isEmpty() || date.isEmpty() || time.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Please fill all fields.");
                return;
            }

            // Firestore upload
            try {
                Firestore db = FirebaseInitializer.getDB();

                Map<String, Object> appointment = new HashMap<>();
                appointment.put("patientName", userName);
                appointment.put("age", age);
                appointment.put("date", date);
                appointment.put("time", time);
                appointment.put("hospital", hospitalName);
                appointment.put("location", location);
                appointment.put("phone", phone);
                appointment.put("email", email);
                appointment.put("status", "Pending");

                ApiFuture<DocumentReference> future = db.collection("appointments").add(appointment);

                future.get(); // Wait until upload completes

                showAlert(Alert.AlertType.INFORMATION, "Appointment booked successfully!");
                bookingStage.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Failed to book appointment.");
            }
        });

        form.getChildren().addAll(
                new Label("📝 Book Appointment"),
                nameField, ageField, dateField, timeField, submitBtn
        );

        Scene scene = new Scene(form, 400, 350);
        bookingStage.setTitle("Book Appointment");
        bookingStage.setScene(scene);
        bookingStage.show();
    }

    private static void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}