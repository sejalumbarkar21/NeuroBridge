/*package com.neurobridge;

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
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyRountinepatient extends Application {

    @Override
    public void start(Stage stage) {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
       // root.setStyle("-fx-background-color: #f5f6ff;");
        //root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");
        //root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f0a8ff, #bfaaff);");
       // root.setStyle("-fx-background-color: linear-gradient(to right, #e54cff, #8755ff);");
       //root.setStyle("-fx-background-color: linear-gradient(to right, #f38fd0, #a18fff);");
       root.setStyle("-fx-background-color: linear-gradient(to bottom, #f38fd0, #a18fff);");




        // Header: Task Name and Date
        VBox headerBox = new VBox(5);
        Label title = new Label("Daily Routine");
        title.setFont(Font.font("Segoe UI", 26));
        title.setTextFill(Color.web("#1e1e2e"));

        Label date = new Label(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));
        date.setFont(Font.font("Segoe UI", 14));
        date.setTextFill(Color.BLACK);

        headerBox.getChildren().addAll(title, date);

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> goToDashboard());

        // Task Cards
        VBox tasksBox = new VBox(20);
        tasksBox.getChildren().addAll(
                createTaskCard("Morning", "Brush your teeth and drink water", "Assets\\Images\\Morning.png", "#fff4dd"),
                createTaskCard("Day", "Play memory game", "Assets\\Images\\Noon.png", "#ffe5ef"),
                createTaskCard("Night", "Take medicines and rest", "Assets\\Images\\night.png", "#dcf3ff")
        );

        root.getChildren().addAll(headerBox, tasksBox);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Alzheimer Care - Daily Routine");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createTaskCard(String timeOfDay, String taskDescription, String imagePath, String bgColor) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 20;");

        // Image icon
        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(70);
        icon.setFitWidth(70);

        // Text
        VBox textBox = new VBox(5);
        Label taskTitle = new Label(timeOfDay);
        taskTitle.setFont(Font.font("Segoe UI", 18));
        taskTitle.setTextFill(Color.web("#333333"));

        Label taskDetail = new Label(taskDescription);
        taskDetail.setFont(Font.font("Segoe UI", 14));
        taskDetail.setTextFill(Color.web("#555555"));
        taskDetail.setWrapText(true);

        textBox.getChildren().addAll(taskTitle, taskDetail);

        card.getChildren().addAll(icon, textBox);
        return card;
    }

        private void goToDashboard() {
    try {
        new DashboardScreen().start(primarystage); // ✅ use this.stage instead of undeclared variable
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    

public static void main(String[] args) {
        launch(args);
    }
}*/
package com.neurobridge;

/*import javafx.application.Application;
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
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyRountinepatient extends Application {

    private Stage mainStage; // ✅ Store stage here

    @Override
    public void start(Stage stage) {
        this.mainStage = stage; // ✅ Save reference to use later

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f38fd0, #a18fff);");

        // Header: Task Name and Date
        VBox headerBox = new VBox(5);
        Label title = new Label("Daily Routine");
        title.setFont(Font.font("Segoe UI", 26));
        title.setTextFill(Color.web("#1e1e2e"));

        Label date = new Label(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));
        date.setFont(Font.font("Segoe UI", 14));
        date.setTextFill(Color.BLACK);

        headerBox.getChildren().addAll(title, date);

        // Back button
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> goToDashboard());

        headerBox.getChildren().add(backButton);

        // Task Cards
        VBox tasksBox = new VBox(20);
        tasksBox.getChildren().addAll(
                createTaskCard("Morning", "Brush your teeth and drink water", "Assets\\Images\\Morning.png", "#fff4dd"),
                createTaskCard("Day", "Play memory game", "Assets\\Images\\Noon.png", "#ffe5ef"),
                createTaskCard("Night", "Take medicines and rest", "Assets\\Images\\night.png", "#dcf3ff")
        );

        root.getChildren().addAll(headerBox, tasksBox);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Alzheimer Care - Daily Routine");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createTaskCard(String timeOfDay, String taskDescription, String imagePath, String bgColor) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 20;");

        // Image icon
        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(70);
        icon.setFitWidth(70);

        // Text
        VBox textBox = new VBox(5);
        Label taskTitle = new Label(timeOfDay);
        taskTitle.setFont(Font.font("Segoe UI", 18));
        taskTitle.setTextFill(Color.web("#333333"));

        Label taskDetail = new Label(taskDescription);
        taskDetail.setFont(Font.font("Segoe UI", 14));
        taskDetail.setTextFill(Color.web("#555555"));
        taskDetail.setWrapText(true);

        textBox.getChildren().addAll(taskTitle, taskDetail);

        card.getChildren().addAll(icon, textBox);
        return card;
    }

    private void goToDashboard() {
        try {
            new DashboardScreen().start(mainStage); // ✅ Use stored stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/


/*import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.neurobridge.FirebaseInitializer;

public class DailyRountinepatient extends Application {

    private VBox tasksBox = new VBox(20); // Declared here for access inside methods

    @Override
    public void start(Stage stage) {
        FirebaseInitializer.init();
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f38fd0, #a18fff);");

        // Header: Task Name and Date
        VBox headerBox = new VBox(5);
        Label title = new Label("Daily Routine");
        title.setFont(Font.font("Segoe UI", 26));
        title.setTextFill(Color.web("#1e1e2e"));

        Label date = new Label(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));
        date.setFont(Font.font("Segoe UI", 14));
        date.setTextFill(Color.BLACK);

        headerBox.getChildren().addAll(title, date);

        // Init Firebase and fetch tasks
        FirebaseInitializer.init(); // Initialize Firebase
        String patientId = "1"; // 🔁 Replace with dynamic ID after login
        fetchTasksForPatient(patientId);

        root.getChildren().addAll(headerBox, tasksBox);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Alzheimer Care - Daily Routine");
        stage.setScene(scene);
        stage.show();
    }

    private HBox createTaskCard(String timeOfDay, String taskDescription, String imagePath, String bgColor) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 20;");

        // Image icon
        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(70);
        icon.setFitWidth(70);

        // Text
        VBox textBox = new VBox(5);
        Label taskTitle = new Label(timeOfDay);
        taskTitle.setFont(Font.font("Segoe UI", 18));
        taskTitle.setTextFill(Color.web("#333333"));

        Label taskDetail = new Label(taskDescription);
        taskDetail.setFont(Font.font("Segoe UI", 14));
        taskDetail.setTextFill(Color.web("#555555"));
        taskDetail.setWrapText(true);

        textBox.getChildren().addAll(taskTitle, taskDetail);

        card.getChildren().addAll(icon, textBox);
        return card;
    }

    /*private void fetchTasksForPatient(String patientId) {
        Firestore db = FirebaseInitializer.getFirestore();

       ApiFuture<QuerySnapshot> future = db.collection("routine_tasks")
        .document(patientId)
        .collection("task_list")
        .get();
 

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                Platform.runLater(() -> {
                    if (!documents.isEmpty()) {
                        for (QueryDocumentSnapshot doc : documents) {
                            String slot = doc.getString("slot");
                            String time = doc.getString("time");
                            String description = doc.getString("desc");

                            String imagePath = switch (slot.toLowerCase()) {
                                case "morning" -> "/Images/Assets/morning.png";
                                case "day", "noon" -> "/Images/Assets/noon.png";
                                case "night" -> "/Images/Assets/night.png";
                                default -> "/Images/Assets/morning.png";
                            };

                            String bgColor = switch (slot.toLowerCase()) {
                                case "morning" -> "#fff4dd";
                                case "day", "noon" -> "#ffe5ef";
                                case "night" -> "#dcf3ff";
                                default -> "#eeeeee";
                            };

                            HBox card = createTaskCard(slot, time + " - " + description, imagePath, bgColor);
                            tasksBox.getChildren().add(card);
                        }

                        showNotification("📝 New tasks have been added for today!");
                    } else {
                        showNotification("📭 No routine tasks found for today.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showNotification("⚠️ Failed to load tasks!"));
            }
        }).start();
    }*/

    /*private void showNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task Update");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


// 🔽 Add this below showNotification()
private void fetchTasksForPatient(String patientId) {
    Firestore db = FirebaseInitializer.getFirestore();

    ApiFuture<QuerySnapshot> future = db.collection("routine_tasks")
        .document(patientId)
        .collection("task_list")
        .get();

    new Thread(() -> {
        try {
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            Platform.runLater(() -> {
                if (!documents.isEmpty()) {
                    for (QueryDocumentSnapshot doc : documents) {
                        String slot = doc.getString("slot");
                        String time = doc.getString("time");
                        String description = doc.getString("desc");

                        String slotSafe = slot != null ? slot.toLowerCase() : "morning";

                        String imagePath = switch (slotSafe) {
                            case "Morning" -> getClass().getResource("/Assets/Images/Morning.png").toExternalForm();
                            case "Noon" -> getClass().getResource("/Assets/Images/Noon.png").toExternalForm();
                            case "Night" -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                            default -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                        };

                        String bgColor = switch (slotSafe) {
                            case "morning" -> "#fff4dd";
                            case "day", "noon" -> "#ffe5ef";
                            case "night" -> "#dcf3ff";
                            default -> "#eeeeee";
                        };

                        HBox card = createTaskCard(slot, time + " - " + description, imagePath, bgColor);
                        tasksBox.getChildren().add(card);
                    }

                    showNotification("📝 New tasks have been added for today!");
                } else {
                    showNotification("📭 No routine tasks found for today.");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Platform.runLater(() -> showNotification("⚠️ Failed to load tasks!"));
        }
    }).start();
}

}*/


/*import com.google.api.core.ApiFuture;
import javafx.scene.paint.Stop;

import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class DailyRountinepatient extends Application {

    private VBox tasksBox;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Gradient background
        BackgroundFill bgFill = new BackgroundFill(
                new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#e0f7fa")),
                        new Stop(1, Color.web("#ffffff"))),
                CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(bgFill));

        Label header = new Label("🧠 Daily Routine");
        header.setFont(Font.font("Verdana", 28));
        header.setPadding(new Insets(20));
        header.setTextFill(Color.web("#333"));

        tasksBox = new VBox(20);
        tasksBox.setPadding(new Insets(20));
        tasksBox.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(tasksBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        root.setTop(header);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Daily Routine");
        stage.setScene(scene);
        stage.show();

        // ✅ Dynamic Patient ID Fetch
        String patientId = UserSession.getInstance().getPatientId();
        if (patientId != null && !patientId.isEmpty()) {
            fetchTasksForPatient(patientId);
        } else {
            showNotification("⚠️ No Patient ID found.");
        }
    }

    private void fetchTasksForPatient(String patientId) {
        Firestore db = FirebaseInitializer.getFirestore();

        new Thread(() -> {
            try {
                ApiFuture<QuerySnapshot> future = db.collection("routine_tasks")
                        .document(patientId)
                        .collection("task_list")
                        .get();

                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                Platform.runLater(() -> {
                    tasksBox.getChildren().clear();

                    if (!documents.isEmpty()) {
                        for (QueryDocumentSnapshot doc : documents) {
                            String slot = doc.getString("slot");
                            String time = doc.getString("time");
                            String description = doc.getString("desc");

                            if (slot == null || time == null || description == null) continue;

                            String slotSafe = slot.toLowerCase();

                            String imagePath = switch (slotSafe) {
                                case "🌅 morning" -> getClass().getResource("/Assets/Images/Morning.png").toExternalForm();
                                case "🌞 noon", "🌞 day" -> getClass().getResource("/Assets/Images/Noon.png").toExternalForm();
                                case "🌙 night" -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                                default -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                            };

                            String bgColor = switch (slotSafe) {
                                case "🌅 morning" -> "#fff3cd";
                                case "🌞 noon", "🌞 day" -> "#ffe6ea";
                                case "🌙 night" -> "#e6f7ff";
                                default -> "#eeeeee";
                            };

                            HBox card = createTaskCard(slot, time + " - " + description, imagePath, bgColor);
                            tasksBox.getChildren().add(card);
                        }

                        showNotification("📝 Tasks loaded from Firebase!");
                    } else {
                        showNotification("📭 No routine tasks found.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showNotification("⚠️ Failed to fetch tasks."));
            }
        }).start();
    }

    private HBox createTaskCard(String title, String content, String imagePath, String bgColor) {
        HBox card = new HBox(20);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 12;");
        card.setMaxWidth(700);

        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(50);
        icon.setFitWidth(50);

        VBox textBox = new VBox(5);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 20));
        Label contentLabel = new Label(content);
        contentLabel.setFont(Font.font("Arial", 14));
        textBox.getChildren().addAll(titleLabel, contentLabel);

        card.getChildren().addAll(icon, textBox);

        // Animations
        FadeTransition fade = new FadeTransition(Duration.millis(500), card);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        ScaleTransition scale = new ScaleTransition(Duration.millis(300), card);
        scale.setFromX(0.9);
        scale.setFromY(0.9);
        scale.setToX(1);
        scale.setToY(1);
        scale.play();

        return card;
    }

    private void showNotification(String message) {
        Label notification = new Label(message);
        notification.setFont(Font.font("Arial", 14));
        notification.setTextFill(Color.web("#333"));
        tasksBox.getChildren().add(notification);
    }

    
}
*/

/*import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DailyRountinepatient extends Application {

    private VBox tasksBox = new VBox(20);

    @Override
    public void start(Stage stage) {
        FirebaseInitializer.initialize();

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f38fd0, #a18fff);");

        VBox headerBox = new VBox(5);
        Label title = new Label("Daily Routine");
        title.setFont(Font.font("Segoe UI", 26));
        title.setTextFill(Color.web("#1e1e2e"));

        Label date = new Label(LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy")));
        date.setFont(Font.font("Segoe UI", 14));
        date.setTextFill(Color.BLACK);

        headerBox.getChildren().addAll(title, date);

        String patientId = UserSession.getInstance().getPatientId();
        if (patientId != null && !patientId.isEmpty()) {
            fetchTasksForPatient(patientId);
        } else {
            showNotification("⚠️ No Patient ID found in session.");
        }

        root.getChildren().addAll(headerBox, tasksBox);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Alzheimer Care - Daily Routine");
        stage.setScene(scene);
        stage.show();
    }

    private void fetchTasksForPatient(String patientId) {
        Firestore db = FirebaseInitializer.getFirestore();

        ApiFuture<QuerySnapshot> future = db.collection("routine_tasks")
                .document(patientId)
                .collection("task_list")
                .get();

        new Thread(() -> {
            try {
                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                Platform.runLater(() -> {
                    tasksBox.getChildren().clear();

                    if (!documents.isEmpty()) {
                        for (QueryDocumentSnapshot doc : documents) {
                            String slot = doc.getString("slot");
                            String time = doc.getString("time");
                            String description = doc.getString("desc");

                            if (slot == null || time == null || description == null) continue;

                            String slotSafe = slot.toLowerCase();

                            String imagePath = switch (slotSafe) {
                                case "morning", "🌅 morning" -> getClass().getResource("/Images/Assets/morning.png").toExternalForm();
                                case "noon", "day", "🌞 noon" -> getClass().getResource("/Images/Assets/noon.png").toExternalForm();
                                case "night", "🌙 night" -> getClass().getResource("/Images/Assets/night.png").toExternalForm();
                                default -> getClass().getResource("/Images/Assets/morning.png").toExternalForm();
                            };

                            String bgColor = switch (slotSafe) {
                                case "morning", "🌅 morning" -> "#fff4dd";
                                case "noon", "day", "🌞 noon" -> "#ffe5ef";
                                case "night", "🌙 night" -> "#dcf3ff";
                                default -> "#eeeeee";
                            };

                            HBox card = createTaskCard(slot, time + " - " + description, imagePath, bgColor);
                            tasksBox.getChildren().add(card);
                        }

                        showNotification("📝 New tasks have been added for today!");
                    } else {
                        showNotification("📭 No routine tasks found for today.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showNotification("⚠️ Failed to load tasks!"));
            }
        }).start();
    }

    private HBox createTaskCard(String timeOfDay, String taskDescription, String imagePath, String bgColor) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 20;");

        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(70);
        icon.setFitWidth(70);

        VBox textBox = new VBox(5);
        Label taskTitle = new Label(timeOfDay);
        taskTitle.setFont(Font.font("Segoe UI", 18));
        taskTitle.setTextFill(Color.web("#333333"));

        Label taskDetail = new Label(taskDescription);
        taskDetail.setFont(Font.font("Segoe UI", 14));
        taskDetail.setTextFill(Color.web("#555555"));
        taskDetail.setWrapText(true);

        textBox.getChildren().addAll(taskTitle, taskDetail);
        card.getChildren().addAll(icon, textBox);
        return card;
    }

    private void showNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Task Update");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
} 
*/
/*import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import com.neurobridge.UserSession;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DailyRountinepatient extends Application {

    private VBox tasksBox;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // 🌈 Pink-to-purple gradient background
        BackgroundFill bgFill = new BackgroundFill(
                new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#fda4cf")),
                        new Stop(1, Color.web("#c084fc"))),
                CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(bgFill));

        // 🧠 Header
        Label title = new Label("Daily Routine");
        title.setFont(Font.font("Verdana", 28));
        title.setTextFill(Color.web("#2e004f"));

        // 📅 Current date
        LocalDate today = LocalDate.now();
        String dateText = today.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"));
        Label dateLabel = new Label(dateText);
        dateLabel.setFont(Font.font("Arial", 16));
        dateLabel.setTextFill(Color.web("#4a154b"));

        VBox headerBox = new VBox(5, title, dateLabel);
        headerBox.setPadding(new Insets(25, 25, 10, 25));
        root.setTop(headerBox);

        // 📜 Tasks container
        tasksBox = new VBox(20);
        tasksBox.setPadding(new Insets(20));
        tasksBox.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(tasksBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Alzheimer Care - Daily Routine");
        stage.setScene(scene);
        stage.show();

        // 🔄 Fetch tasks dynamically using patient ID
        String patientId = UserSession.getInstance().getPatientId();
        if (patientId != null && !patientId.isEmpty()) {
            fetchTasksForPatient(patientId);
        } else {
            showNotification("⚠️ No Patient ID found.");
        }
    }

    private void fetchTasksForPatient(String patientId) {
        Firestore db = FirebaseInitializer.getFirestore();

        new Thread(() -> {
            try {
                ApiFuture<QuerySnapshot> future = db.collection("routine_tasks")
                        .document(patientId)
                        .collection("task_list")
                        .get();

                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                Platform.runLater(() -> {
                    tasksBox.getChildren().clear();

                    if (!documents.isEmpty()) {
                        for (QueryDocumentSnapshot doc : documents) {
                            String slot = doc.getString("slot");
                            String time = doc.getString("time");
                            String description = doc.getString("desc");

                            if (slot == null || time == null || description == null) continue;

                            String slotSafe = slot.toLowerCase();

                            String imagePath = switch (slotSafe) {
                                case "🌅 morning" -> getClass().getResource("/Assets/Images/Morning.png").toExternalForm();
                                case "🌞 noon", "🌞 day" -> getClass().getResource("/Assets/Images/Noon.png").toExternalForm();
                                case "🌙 night" -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                                default -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                            };

                            String bgColor = switch (slotSafe) {
                                case "🌅 morning" -> "#ffa9a9ff";
                                case "🌞 noon", "🌞 day" -> "#ffe6ea";
                                case "🌙 night" -> "#e6f7ff";
                                default -> "#eeeeee";
                            };

                            HBox card = createTaskCard(slot, time + " - " + description, imagePath, bgColor);
                            tasksBox.getChildren().add(card);
                        }

                        showNotification("📝 Tasks loaded from Firebase!");
                    } else {
                        showNotification("📭 No routine tasks found.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showNotification("⚠️ Failed to fetch tasks."));
            }
        }).start();
    }

    private HBox createTaskCard(String title, String content, String imagePath, String bgColor) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 14;");
        card.setMaxWidth(700);

        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(50);
        icon.setFitWidth(50);

        VBox textBox = new VBox(5);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 20));
        titleLabel.setTextFill(Color.web("#3d155f"));

        Label contentLabel = new Label(content);
        contentLabel.setFont(Font.font("Arial", 14));
        contentLabel.setTextFill(Color.web("#333"));

        textBox.getChildren().addAll(titleLabel, contentLabel);

        card.getChildren().addAll(icon, textBox);

        // Fade-in animation
        FadeTransition fade = new FadeTransition(Duration.millis(400), card);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        return card;
    }

    private void showNotification(String message) {
        Label notification = new Label(message);
        notification.setFont(Font.font("Arial", 14));
        notification.setTextFill(Color.web("#4a154b"));
        tasksBox.getChildren().add(notification);
    }

   
}*/
/*import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import com.neurobridge.UserSession;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DailyRountinepatient extends Application {

    private VBox tasksBox;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // 🌈 Pink-to-purple gradient background
        BackgroundFill bgFill = new BackgroundFill(
                new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#fda4cf")),
                        new Stop(1, Color.web("#c084fc"))),
                CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(bgFill));

        // 🧠 Header
        Label title = new Label("Daily Routine");
        title.setFont(Font.font("Verdana", 28));
        title.setTextFill(Color.web("#2e004f"));

        // 📅 Current date
        LocalDate today = LocalDate.now();
        String dateText = today.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"));
        Label dateLabel = new Label(dateText);
        dateLabel.setFont(Font.font("Arial", 16));
        dateLabel.setTextFill(Color.web("#4a154b"));

        VBox headerBox = new VBox(5, title, dateLabel);
        headerBox.setPadding(new Insets(25, 25, 10, 25));
        root.setTop(headerBox);

        // 📜 Tasks container
        tasksBox = new VBox(20);
        tasksBox.setPadding(new Insets(20));
        tasksBox.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(tasksBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("Alzheimer Care - Daily Routine");
        stage.setScene(scene);
        stage.show();

        // 🔄 Fetch tasks dynamically using patient ID
        String patientId = UserSession.getInstance().getPatientId();
        if (patientId != null && !patientId.isEmpty()) {
            fetchTasksForPatient(patientId);
        } else {
            showNotification("⚠️ No Patient ID found.");
        }
    }

    private void fetchTasksForPatient(String patientId) {
        Firestore db = FirebaseInitializer.getFirestore();

        new Thread(() -> {
            try {
                ApiFuture<QuerySnapshot> future = db.collection("routine_tasks")
                        .document(patientId)
                        .collection("task_list")
                        .get();

                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                Platform.runLater(() -> {
                    tasksBox.getChildren().clear();

                    if (!documents.isEmpty()) {
                        for (QueryDocumentSnapshot doc : documents) {
                            String slot = doc.getString("slot");
                            String time = doc.getString("time");
                            String description = doc.getString("desc");

                            if (slot == null || time == null || description == null) continue;

                            String slotSafe = slot.toLowerCase();

                            String imagePath = switch (slotSafe) {
                                case "🌅 morning" -> getClass().getResource("/Assets/Images/Morning.png").toExternalForm();
                                case "🌞 noon", "🌞 day" -> getClass().getResource("/Assets/Images/Noon.png").toExternalForm();
                                case "🌙 night" -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                                default -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                            };

                            String bgColor = switch (slotSafe) {
                                case "🌅 morning" -> "#ff8e86ff";
                                case "🌞 noon", "🌞 day" -> "#a98efaff";
                                case "🌙 night" -> "#a0dbf6ff";
                                default -> "#eeeeee";
                            };

                            HBox card = createTaskCard(slot, time + " - " + description, imagePath, bgColor);
                            tasksBox.getChildren().add(card);
                        }

                        showNotification("📝 Tasks loaded from Firebase!");
                    } else {
                        showNotification("📭 No routine tasks found.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showNotification("⚠️ Failed to fetch tasks."));
            }
        }).start();
    }

    private HBox createTaskCard(String title, String content, String imagePath, String bgColor) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 14;");
        card.setMaxWidth(700);

        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(50);
        icon.setFitWidth(50);

        VBox textBox = new VBox(5);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 20));
        titleLabel.setTextFill(Color.web("#3d155f"));

        Label contentLabel = new Label(content);
        contentLabel.setFont(Font.font("Arial", 14));
        contentLabel.setTextFill(Color.BLACK);  // ✅ This line sets content text to black

        textBox.getChildren().addAll(titleLabel, contentLabel);

        card.getChildren().addAll(icon, textBox);

        // Fade-in animation
        FadeTransition fade = new FadeTransition(Duration.millis(400), card);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        return card;
    }

    private void showNotification(String message) {
        Label notification = new Label(message);
        notification.setFont(Font.font("Arial", 14));
        notification.setTextFill(Color.web("#4a154b"));
        tasksBox.getChildren().add(notification);
    }
}*/


import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import com.neurobridge.UserSession;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DailyRountinepatient extends Application {

    private VBox tasksBox;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // 🌈 Gradient background
        BackgroundFill bgFill = new BackgroundFill(
                new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#fda4cf")),
                        new Stop(1, Color.web("#c084fc"))),
                CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(bgFill));

        // 🧠 Header
        Label title = new Label("Daily Routine");
        title.setFont(Font.font("Verdana", 28));
        title.setTextFill(Color.web("#2e004f"));

        // 📅 Current date
        LocalDate today = LocalDate.now();
        String dateText = today.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy"));
        Label dateLabel = new Label(dateText);
        dateLabel.setFont(Font.font("Arial", 16));
        dateLabel.setTextFill(Color.web("#4a154b"));

        // 🔙 Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", 14));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #4a154b; -fx-background-radius: 10;");
        backButton.setOnAction(e -> {
            try {
                new DashboardScreen().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox topBar = new HBox(20, backButton, title);
        topBar.setAlignment(Pos.CENTER_LEFT);

        VBox headerBox = new VBox(5, topBar, dateLabel);
        headerBox.setPadding(new Insets(25, 25, 10, 25));
        root.setTop(headerBox);

        // 📜 Tasks container
        tasksBox = new VBox(20);
        tasksBox.setPadding(new Insets(20));
        tasksBox.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(tasksBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1550, 800);
        stage.setTitle("Alzheimer Care - Daily Routine");
        stage.setScene(scene);
        stage.show();

        // 🔄 Fetch tasks dynamically using patient ID
        String patientId = UserSession.getInstance().getPatientId();
        if (patientId != null && !patientId.isEmpty()) {
            fetchTasksForPatient(patientId);
        } else {
            showNotification("⚠️ No Patient ID found.");
        }
    }

    private void fetchTasksForPatient(String patientId) {
        Firestore db = FirebaseInitializer.getFirestore();

        new Thread(() -> {
            try {
                ApiFuture<QuerySnapshot> future = db.collection("routine_tasks")
                        .document(patientId)
                        .collection("task_list")
                        .get();

                List<QueryDocumentSnapshot> documents = future.get().getDocuments();

                Platform.runLater(() -> {
                    tasksBox.getChildren().clear();

                    if (!documents.isEmpty()) {
                        for (QueryDocumentSnapshot doc : documents) {
                            String slot = doc.getString("slot");
                            String time = doc.getString("time");
                            String description = doc.getString("desc");

                            if (slot == null || time == null || description == null) continue;

                            String slotSafe = slot.toLowerCase();

                            String imagePath = switch (slotSafe) {
                                case "🌅 morning" -> getClass().getResource("/Assets/Images/Morning.png").toExternalForm();
                                case "🌞 noon", "🌞 day" -> getClass().getResource("/Assets/Images/Noon.png").toExternalForm();
                                case "🌙 night" -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                                default -> getClass().getResource("/Assets/Images/night.png").toExternalForm();
                            };

                            String bgColor = switch (slotSafe) {
                                case "🌅 morning" -> "#ff8e86ff";
                                case "🌞 noon", "🌞 day" -> "#a98efaff";
                                case "🌙 night" -> "#a0dbf6ff";
                                default -> "#eeeeee";
                            };

                            HBox card = createTaskCard(slot, time + " - " + description, imagePath, bgColor);
                            tasksBox.getChildren().add(card);
                        }

                        showNotification("📝 Tasks loaded from Firebase!");
                    } else {
                        showNotification("📭 No routine tasks found.");
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> showNotification("⚠️ Failed to fetch tasks."));
            }
        }).start();
    }

    private HBox createTaskCard(String title, String content, String imagePath, String bgColor) {
        HBox card = new HBox(15);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 14;");
        card.setMaxWidth(700);

        ImageView icon = new ImageView(new Image(imagePath));
        icon.setFitHeight(50);
        icon.setFitWidth(50);

        VBox textBox = new VBox(5);
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 20));
        titleLabel.setTextFill(Color.web("#3d155f"));

        Label contentLabel = new Label(content);
        contentLabel.setFont(Font.font("Arial", 14));
        contentLabel.setTextFill(Color.BLACK);  // ✅ Text color set to black

        textBox.getChildren().addAll(titleLabel, contentLabel);

        card.getChildren().addAll(icon, textBox);

        // Fade-in animation
        FadeTransition fade = new FadeTransition(Duration.millis(400), card);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        return card;
    }

    private void showNotification(String message) {
        Label notification = new Label(message);
        notification.setFont(Font.font("Arial", 14));
        notification.setTextFill(Color.web("#4a154b"));
        tasksBox.getChildren().add(notification);
    }
}
