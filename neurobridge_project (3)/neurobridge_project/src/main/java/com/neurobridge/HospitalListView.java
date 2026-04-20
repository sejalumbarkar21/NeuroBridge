package com.neurobridge;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class HospitalListView extends Application {

    private static final String DOCTOR_ICON_URL = "https://cdn-icons-png.flaticon.com/512/387/387561.png";

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom,#6A1B9A, #ffffffff);");

        // ✅ Simple Back Button
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: #f2ededff; -fx-text-fill: black; -fx-font-size: 14px; -fx-padding: 8 16; -fx-background-radius: 8;");
        backButton.setOnAction(e -> {
            try {
                new FamilyDashboardScreen().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Title
        Label title = new Label("🏥 Top Alzheimer’s Hospitals in Maharashtra");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#d9e3eeff"));
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(title, new Insets(0, 0, 20, 0));

        // Hospital list container
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        VBox hospitalContainer = new VBox(20);
        hospitalContainer.setPadding(new Insets(10));

        // Firestore fetch
        Firestore db = FirebaseInitializer.getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection("hospitals").get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> docs = future.get().getDocuments();
                Platform.runLater(() -> {
                    for (QueryDocumentSnapshot doc : docs) {
                        Map<String, Object> data = doc.getData();
                        String name = (String) data.getOrDefault("name", "Unknown Hospital");
                        String location = (String) data.getOrDefault("location", "Unknown Location");
                        String phone = (String) data.getOrDefault("phone", "Not Available");
                        String email = (String) data.getOrDefault("email", "Not Available");
                        String description = (String) data.getOrDefault("description", "No description available");

                        hospitalContainer.getChildren().add(
                                createHospitalCard(primaryStage, name, location, phone, email, description)
                        );
                    }
                });
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        scrollPane.setContent(hospitalContainer);

        // Add to root VBox
        root.getChildren().addAll(backButton, title, scrollPane);

        Scene scene = new Scene(root, 1500, 900);
        primaryStage.setTitle("Hospital Booking");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createHospitalCard(Stage primaryStage, String name, String location, String phone, String email, String description) {
        HBox card = new HBox(20);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 12; "
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0.2, 0, 4);"
                + "-fx-cursor: hand;");
        card.setAlignment(Pos.CENTER_LEFT);

        card.setOnMouseEntered(e -> card.setStyle(card.getStyle() + "-fx-scale-y: 1.01; -fx-scale-x: 1.01;"));
        card.setOnMouseExited(e -> card.setStyle(card.getStyle().replaceAll("-fx-scale.*?;", "")));

        ImageView icon = new ImageView(new Image(DOCTOR_ICON_URL));
        icon.setFitHeight(70);
        icon.setFitWidth(70);
        icon.setSmooth(true);
        icon.setPreserveRatio(true);

        VBox detailBox = new VBox(6);
        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        nameLabel.setTextFill(Color.web("#1e272e"));

        Label locationLabel = new Label("📍 " + location);
        locationLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        locationLabel.setTextFill(Color.web("#636e72"));

        Label phoneLabel = new Label("📞 " + phone);
        phoneLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
        phoneLabel.setTextFill(Color.web("#2d3436"));

        Label emailLabel = new Label("✉ " + email);
        emailLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 13));
        emailLabel.setTextFill(Color.web("#2d3436"));

        detailBox.getChildren().addAll(nameLabel, locationLabel, phoneLabel, emailLabel);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button bookBtn = new Button("Book Now");
        bookBtn.setStyle("-fx-background-color: #0097e6; -fx-text-fill: white; -fx-font-size: 14; "
                + "-fx-background-radius: 8; -fx-padding: 8 16;");
        bookBtn.setOnMouseEntered(e -> bookBtn.setStyle(bookBtn.getStyle() + "-fx-opacity: 0.85;"));
        bookBtn.setOnMouseExited(e -> bookBtn.setStyle("-fx-background-color: #0097e6; -fx-text-fill: white; -fx-font-size: 14; "
                + "-fx-background-radius: 8; -fx-padding: 8 16;"));
        bookBtn.setOnAction(e -> openDetailView(primaryStage, name, location, phone, email, description));

        card.getChildren().addAll(icon, detailBox, spacer, bookBtn);
        return card;
    }

    private void openDetailView(Stage owner, String name, String location, String phone, String email, String description) {
        Platform.runLater(() -> {
            HospitalDetailView.showHospitalDetail(owner, name, location, phone, email, description);
        });
    }

    public static void main(String[] args) {
        Application.launch(HospitalListView.class, args);
    }
}
