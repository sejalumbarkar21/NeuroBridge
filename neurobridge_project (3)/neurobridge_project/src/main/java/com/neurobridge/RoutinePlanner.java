package com.neurobridge;


/*import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.neurobridge.FirebaseInitializer;


public class RoutinePlanner extends Application {

    private final List<Task> tasks = new ArrayList<>();
    private VBox taskDisplayArea;

    @Override
    public void start(Stage stage) {
        FirebaseInitializer.initialize();
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f38fd0, #a18fff);");

        VBox mainContent = new VBox(12);
        mainContent.setPadding(new Insets(20));

        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.TOP_LEFT);
        Label title = new Label("Routine Planner");
        title.setFont(Font.font("Segoe UI", 28));
        title.setTextFill(Color.web("#17181bff"));
        header.getChildren().add(title);

        // Date Label
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        Label dateLabel = new Label(formatter.format(LocalDate.now()));
        dateLabel.setFont(Font.font("Segoe UI", 16));
        dateLabel.setTextFill(Color.web("#1c1d1dff"));

        
        ImageView plannerImage = new ImageView(new Image("/Assets/Images/Routineplanner.png")); // Replace with your path
        plannerImage.setFitWidth(340); // increased from 280
        plannerImage.setPreserveRatio(true);

        HBox imageContainer = new HBox(plannerImage);
        imageContainer.setAlignment(Pos.CENTER);


        // Input Section
        VBox inputContainer = new VBox(10);
        inputContainer.setPadding(new Insets(12));
        inputContainer.setStyle("-fx-background-color: #e0f7fa; -fx-background-radius: 12;");
        inputContainer.setVisible(false);
        inputContainer.setManaged(false); // removes white space when hidden

        // Time Slot Buttons
        HBox timeButtons = new HBox(10);
        ToggleGroup slotGroup = new ToggleGroup();

        ToggleButton morningBtn = createSlotButton("🌅 Morning");
        ToggleButton noonBtn = createSlotButton("🌞 Noon");
        ToggleButton nightBtn = createSlotButton("🌙 Night");

        morningBtn.setToggleGroup(slotGroup);
        noonBtn.setToggleGroup(slotGroup);
        nightBtn.setToggleGroup(slotGroup);
        timeButtons.getChildren().addAll(morningBtn, noonBtn, nightBtn);

        // Task Input
        TextField taskField = new TextField();
        taskField.setPromptText("Enter Task Description");
        taskField.setStyle("-fx-background-radius: 10; -fx-background-color: #ffffff; -fx-border-color: #ccc;");

        // Time Input
        TextField timeField = new TextField();
        timeField.setPromptText("Enter Time (e.g., 8:00 AM)");
        timeField.setStyle("-fx-background-radius: 10; -fx-background-color: #ffffff; -fx-border-color: #ccc;");

        // Add Button
        Button addBtn = new Button("Add Task");
        addBtn.setFont(Font.font("Segoe UI", 14));
        addBtn.setStyle("-fx-background-color: #67c887; -fx-text-fill: white; -fx-background-radius: 8;");
        addBtn.setOnAction(e -> {
            try {
                new FamilyDashboardScreen().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        
        addBtn.setOnAction(e -> {
        ToggleButton selected = (ToggleButton) slotGroup.getSelectedToggle();
        String desc = taskField.getText();
        String time = timeField.getText();

        if (selected != null && !desc.isEmpty() && !time.isEmpty()) {
        Task newTask = new Task(selected.getText(), time, desc);
        tasks.add(newTask);
        showTasks();

        uploadTaskToFirestore(newTask, "1"); // ✅ Replace "1" with dynamic patient ID when available

        taskField.clear();
        timeField.clear();
        slotGroup.selectToggle(null);
        inputContainer.setVisible(false);
        inputContainer.setManaged(false);
    }
        });


        inputContainer.getChildren().addAll(timeButtons, taskField, timeField, addBtn);

        // Scrollable Task Display
        taskDisplayArea = new VBox(10);
        taskDisplayArea.setPadding(new Insets(10, 0, 0, 0));
        ScrollPane scrollPane = new ScrollPane(taskDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: transparent;" +
                "-fx-background-color: transparent;" +
                "-fx-control-inner-background: transparent;" +
                "-fx-padding: 0;"
        );

        // Floating + Button
        Button plusBtn = new Button("+");
        plusBtn.setFont(Font.font("Segoe UI", 22));
        plusBtn.setStyle(
                "-fx-background-color: #4da3ff; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 50%; " +
                        "-fx-min-width: 50px; " +
                        "-fx-min-height: 50px;"
        );
        plusBtn.setEffect(new DropShadow());

        plusBtn.setOnAction(e -> {
            boolean isVisible = inputContainer.isVisible();
            inputContainer.setVisible(!isVisible);
            inputContainer.setManaged(!isVisible);
        });

        StackPane floating = new StackPane(plusBtn);
        StackPane.setAlignment(plusBtn, Pos.BOTTOM_RIGHT);
        floating.setPadding(new Insets(10, 20, 20, 10));

        // Add all elements to mainContent
        mainContent.getChildren().addAll(header, dateLabel, imageContainer, inputContainer, scrollPane);
        root.setCenter(mainContent);
        root.setBottom(floating);

        stage.setTitle("Family - Add Task");
        stage.setScene(new Scene(root, 1000, 800));
        stage.show();
    }

    private ToggleButton createSlotButton(String label) {
        ToggleButton btn = new ToggleButton(label);
        btn.setStyle(
                "-fx-background-color: #ffffff; " +
                        "-fx-border-color: #ccc; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 5 15 5 15;" +
                        "-fx-font-size: 14;"
        );

        btn.setOnMouseClicked(e -> {
            btn.setStyle(
                    "-fx-background-color: #a5d6a7; " +
                            "-fx-text-fill: black; " +
                            "-fx-background-radius: 8;"
            );
        });

        return btn;
    }

    
    private void showTasks() {
    taskDisplayArea.getChildren().clear();
    for (Task t : tasks) {
        String color = switch (t.slot) {
            case "🌅 Morning" -> "#fff3cd";
            case "🌞 Day", "🌞 Noon" -> "#ffe6ea"; // support Noon too
            case "🌙 Night" -> "#e6f7ff";
            default -> "#eeeeee";
        };

        VBox card = new VBox(6);
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 12;");

        Label slotLabel = new Label(t.slot);
        slotLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
        slotLabel.setStyle("-fx-text-fill: black;"); // 🔥 force black via inline CSS

        Label descLabel = new Label(t.time + " - " + t.desc);
        descLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
        descLabel.setWrapText(true);
        descLabel.setStyle("-fx-text-fill: black;"); // 🔥 force black via inline CSS

        card.getChildren().addAll(slotLabel, descLabel);
        taskDisplayArea.getChildren().add(card);
    }
}


    private void uploadTaskToFirestore(Task task, String patientId) {
    Firestore db = FirestoreClient.getFirestore();

    Map<String, Object> taskMap = new HashMap<>();
    taskMap.put("slot", task.slot);
    taskMap.put("time", task.time);
    taskMap.put("desc", task.desc);

    new Thread(() -> {
        try {
            db.collection("routine_tasks")
              .document(patientId)
              .collection("task_list")
              .add(taskMap)
              .get(); // wait for completion
            System.out.println("Task uploaded to Firebase!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }).start();
}


    static class Task {
        String slot;
        String time;
        String desc;

        Task(String slot, String time, String desc) {
            this.slot = slot;
            this.time = time;
            this.desc = desc;
        }
    }
}*/



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.neurobridge.FirebaseInitializer;
import com.neurobridge.UserSession;

public class RoutinePlanner extends Application {

    private final List<Task> tasks = new ArrayList<>();
    private VBox taskDisplayArea;

    @Override
    public void start(Stage stage) {
        FirebaseInitializer.initialize();
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #f38fd0, #a18fff);");

        VBox mainContent = new VBox(12);
        mainContent.setPadding(new Insets(20));

        // 🔙 Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Segoe UI", 14));
        backButton.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #333333; -fx-background-radius: 8;");
        backButton.setOnAction(e -> {
            try {
                new FamilyDashboardScreen().start(new Stage());
                stage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        // Header
        HBox header = new HBox();
        header.setAlignment(Pos.TOP_LEFT);
        Label title = new Label("Routine Planner");
        title.setFont(Font.font("Segoe UI", 28));
        title.setTextFill(Color.web("#17181bff"));
        header.getChildren().add(title);

        // Date Label
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy");
        Label dateLabel = new Label(formatter.format(LocalDate.now()));
        dateLabel.setFont(Font.font("Segoe UI", 16));
        dateLabel.setTextFill(Color.web("#1c1d1dff"));

        // Centered Image View
        ImageView plannerImage = new ImageView(new Image("/Assets/Images/Routineplanner.png"));
        plannerImage.setFitWidth(340);
        plannerImage.setPreserveRatio(true);
        HBox imageContainer = new HBox(plannerImage);
        imageContainer.setAlignment(Pos.CENTER);

        // Input Section
        VBox inputContainer = new VBox(10);
        inputContainer.setPadding(new Insets(12));
        inputContainer.setStyle("-fx-background-color: #e0f7fa; -fx-background-radius: 12;");
        inputContainer.setVisible(false);
        inputContainer.setManaged(false);

        // Time Slot Buttons
        HBox timeButtons = new HBox(10);
        ToggleGroup slotGroup = new ToggleGroup();
        ToggleButton morningBtn = createSlotButton("🌅 Morning");
        ToggleButton noonBtn = createSlotButton("🌞 Noon");
        ToggleButton nightBtn = createSlotButton("🌙 Night");
        morningBtn.setToggleGroup(slotGroup);
        noonBtn.setToggleGroup(slotGroup);
        nightBtn.setToggleGroup(slotGroup);
        timeButtons.getChildren().addAll(morningBtn, noonBtn, nightBtn);

        TextField taskField = new TextField();
        taskField.setPromptText("Enter Task Description");
        taskField.setStyle("-fx-background-radius: 10; -fx-background-color: #ffffff; -fx-border-color: #ccc;");

        TextField timeField = new TextField();
        timeField.setPromptText("Enter Time (e.g., 8:00 AM)");
        timeField.setStyle("-fx-background-radius: 10; -fx-background-color: #ffffff; -fx-border-color: #ccc;");

        Button addBtn = new Button("Add Task");
        addBtn.setFont(Font.font("Segoe UI", 14));
        addBtn.setStyle("-fx-background-color: #67c887; -fx-text-fill: white; -fx-background-radius: 8;");
        addBtn.setOnAction(e -> {
            ToggleButton selected = (ToggleButton) slotGroup.getSelectedToggle();
            String desc = taskField.getText();
            String time = timeField.getText();

            if (selected != null && !desc.isEmpty() && !time.isEmpty()) {
                Task newTask = new Task(selected.getText(), time, desc);
                tasks.add(newTask);
                showTasks();
                String patientId = UserSession.getInstance().getPatientId();
                uploadTaskToFirestore(newTask, patientId);

                taskField.clear();
                timeField.clear();
                slotGroup.selectToggle(null);
                inputContainer.setVisible(false);
                inputContainer.setManaged(false);
            }
        });

        inputContainer.getChildren().addAll(timeButtons, taskField, timeField, addBtn);

        taskDisplayArea = new VBox(10);
        taskDisplayArea.setPadding(new Insets(10, 0, 0, 0));
        ScrollPane scrollPane = new ScrollPane(taskDisplayArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle(
                "-fx-background: transparent;" +
                "-fx-background-color: transparent;" +
                "-fx-control-inner-background: transparent;" +
                "-fx-padding: 0;"
        );

        Button plusBtn = new Button("+");
        plusBtn.setFont(Font.font("Segoe UI", 22));
        plusBtn.setStyle(
                "-fx-background-color: #4da3ff; " +
                        "-fx-text-fill: white; " +
                        "-fx-background-radius: 50%; " +
                        "-fx-min-width: 50px; " +
                        "-fx-min-height: 50px;"
        );
        plusBtn.setEffect(new DropShadow());
        plusBtn.setOnAction(e -> {
            boolean isVisible = inputContainer.isVisible();
            inputContainer.setVisible(!isVisible);
            inputContainer.setManaged(!isVisible);
        });

        StackPane floating = new StackPane(plusBtn);
        StackPane.setAlignment(plusBtn, Pos.BOTTOM_RIGHT);
        floating.setPadding(new Insets(10, 20, 20, 10));

        mainContent.getChildren().addAll(backButton, header, dateLabel, imageContainer, inputContainer, scrollPane);
        root.setCenter(mainContent);
        root.setBottom(floating);

        stage.setTitle("Family - Add Task");
        stage.setScene(new Scene(root, 1550, 800));
        stage.show();
    }

    private ToggleButton createSlotButton(String label) {
        ToggleButton btn = new ToggleButton(label);
        btn.setStyle(
                "-fx-background-color: #ffffff; " +
                        "-fx-border-color: #ccc; " +
                        "-fx-background-radius: 8; " +
                        "-fx-padding: 5 15 5 15;" +
                        "-fx-font-size: 14;"
        );

        btn.setOnMouseClicked(e -> {
            btn.setStyle(
                    "-fx-background-color: #a5d6a7; " +
                            "-fx-text-fill: black; " +
                            "-fx-background-radius: 8;"
            );
        });

        return btn;
    }

    private void showTasks() {
        taskDisplayArea.getChildren().clear();
        for (Task t : tasks) {
            String color = switch (t.slot) {
                case "🌅 Morning" -> "#fff3cd";
                case "🌞 Day", "🌞 Noon" -> "#ffe6ea";
                case "🌙 Night" -> "#e6f7ff";
                default -> "#eeeeee";
            };

            VBox card = new VBox(6);
            card.setPadding(new Insets(10));
            card.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 12;");

            Label slotLabel = new Label(t.slot);
            slotLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
            slotLabel.setStyle("-fx-text-fill: black;");

            Label descLabel = new Label(t.time + " - " + t.desc);
            descLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 13));
            descLabel.setWrapText(true);
            descLabel.setStyle("-fx-text-fill: black;");

            card.getChildren().addAll(slotLabel, descLabel);
            taskDisplayArea.getChildren().add(card);
        }
    }

    private void uploadTaskToFirestore(Task task, String patientId) {
        Firestore db = FirestoreClient.getFirestore();
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("slot", task.slot);
        taskMap.put("time", task.time);
        taskMap.put("desc", task.desc);

        new Thread(() -> {
            try {
                db.collection("routine_tasks")
                  .document(patientId)
                  .collection("task_list")
                  .add(taskMap)
                  .get();
                System.out.println("Task uploaded to Firebase!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    static class Task {
        String slot;
        String time;
        String desc;

        Task(String slot, String time, String desc) {
            this.slot = slot;
            this.time = time;
            this.desc = desc;
        }
    }
}
