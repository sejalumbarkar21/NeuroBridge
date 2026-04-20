/*package com.neurobridge;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.neurobridge.FirebaseInitializer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Level3Quiz extends Application {

    private VBox mainContainer;
    private final String patientId = "50"; // Change to dynamic ID if needed
    private ProgressBar progressBar;
    private Label progressLabel;
    private int totalQuestions = 0;
    private int answeredCount = 0;

    private List<QuestionEntry> questionEntries = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.init();

        mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setStyle("-fx-background-color: #f3e8ff;");

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(400);
        progressLabel = new Label("Loading quiz...");
        progressLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        progressLabel.setTextFill(Color.DARKSLATEBLUE);

        VBox topBar = new VBox(10, progressLabel, progressBar);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f3e8ff;");

        VBox root = new VBox(topBar, scrollPane);
        Scene scene = new Scene(root, 1500, 800);

        primaryStage.setTitle("🧠 Level 3 Memory Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();

        loadAllQuizQuestions();
    }

    private void loadAllQuizQuestions() {
        Firestore db = FirebaseInitializer.getDB();
        ApiFuture<QuerySnapshot> future = db.collection("memory_game_data")
                .document(patientId)
                .collection("level_3")
                .get();

        new Thread(() -> {
            try {
                QuerySnapshot snapshot = future.get();
                if (snapshot != null && !snapshot.isEmpty()) {
                    List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
                    totalQuestions = documents.size();
                    Platform.runLater(() -> displayAllQuestions(documents));
                } else {
                    showError("No quiz found for this patient.");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                showError("Error fetching quiz: " + e.getMessage());
            }
        }).start();
    }

    private void displayAllQuestions(List<QueryDocumentSnapshot> documents) {
        int questionNumber = 1;

        for (QueryDocumentSnapshot doc : documents) {
            String questionText = doc.getString("question");
            String optionA = doc.getString("optionA");
            String optionB = doc.getString("optionB");
            String optionC = doc.getString("optionC");
            String optionD = doc.getString("optionD");
            String correct = doc.getString("correctAnswer");

            ToggleGroup group = new ToggleGroup();

            VBox card = new VBox(10);
            card.setPadding(new Insets(15));
            card.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15; -fx-border-color: #b19cd9; -fx-border-width: 2; -fx-border-radius: 15;");
            card.setMaxWidth(500);

            Label questionLabel = new Label("Q" + questionNumber + ": " + questionText);
            questionLabel.setWrapText(true);
            questionLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            questionLabel.setTextFill(Color.DARKMAGENTA);

            RadioButton a = createOption("A", optionA, group);
            RadioButton b = createOption("B", optionB, group);
            RadioButton c = createOption("C", optionC, group);
            RadioButton d = createOption("D", optionD, group);

            // Add listener to count answers
            group.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                if (newToggle != null && oldToggle == null) {
                    answeredCount++;
                    updateProgress();
                }
            });

            card.getChildren().addAll(questionLabel, a, b, c, d);
            mainContainer.getChildren().add(card);

            questionEntries.add(new QuestionEntry(group, correct));
            questionNumber++;
        }

        Button submit = new Button("✅ Submit Quiz");
        submit.setStyle("-fx-background-color: #6a1b9a; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 10;");
        submit.setOnAction(e -> evaluateScore());

        mainContainer.getChildren().add(submit);
    }

    private RadioButton createOption(String label, String text, ToggleGroup group) {
        RadioButton option = new RadioButton(label + ": " + text);
        option.setUserData(label); // Important for answer comparison
        option.setToggleGroup(group);
        option.setFont(Font.font("Arial", 14));
        option.setWrapText(true);
        option.setStyle("-fx-padding: 5px;");
        return option;
    }

    private void updateProgress() {
        double progress = (double) answeredCount / totalQuestions;
        progressBar.setProgress(progress);
        progressLabel.setText("Answered " + answeredCount + " of " + totalQuestions);
    }

    private void evaluateScore() {
        int score = 0;
        for (QuestionEntry entry : questionEntries) {
            Toggle selected = entry.group.getSelectedToggle();
            if (selected != null && selected.getUserData().equals(entry.correctAnswer)) {
                score++;
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Completed");
        alert.setHeaderText("🎉 Your Score: " + score + "/" + totalQuestions);
        alert.setContentText(score >= totalQuestions / 2 ? "Great job!" : "Keep practicing, you can improve!");
        alert.showAndWait();
    }

    private void showError(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
            alert.showAndWait();
        });
    }

    private static class QuestionEntry {
        ToggleGroup group;
        String correctAnswer;

        public QuestionEntry(ToggleGroup group, String correctAnswer) {
            this.group = group;
            this.correctAnswer = correctAnswer;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
package com.neurobridge;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Level3Quiz extends Application {

    private VBox mainContainer;
    private final String patientId = UserSession.getInstance().getPatientId(); // Change to dynamic ID if needed
    private ProgressBar progressBar;
    private Label progressLabel;
    private int totalQuestions = 0;
    private int answeredCount = 0;

    private List<QuestionEntry> questionEntries = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.init();

        mainContainer = new VBox(20);
        mainContainer.setPadding(new Insets(30));
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setStyle("-fx-background-color: #f3e8ff;");

        // ⬅️ Back Button using Emoji
        Button backButton = new Button("⬅️ Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-font-size: 18px;");
        backButton.setOnAction(e -> {
            try {
                new DashboardScreen().start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        HBox backContainer = new HBox(backButton);
        backContainer.setAlignment(Pos.CENTER_LEFT);
        backContainer.setPadding(new Insets(10, 0, 10, 10));

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(400);
        progressLabel = new Label("Loading quiz...");
        progressLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        progressLabel.setTextFill(Color.DARKSLATEBLUE);

        VBox topBar = new VBox(10, progressLabel, progressBar);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(mainContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f3e8ff;");

        VBox root = new VBox(backContainer, topBar, scrollPane);
        Scene scene = new Scene(root, 1500, 800);

        primaryStage.setTitle("🧠 Level 3 Memory Quiz");
        primaryStage.setScene(scene);
        primaryStage.show();

        loadAllQuizQuestions();
    }

    private void loadAllQuizQuestions() {
        Firestore db = FirebaseInitializer.getDB();
        ApiFuture<QuerySnapshot> future = db.collection("memory_game_data")
                .document(patientId)
                .collection("level_3")
                .get();

        new Thread(() -> {
            try {
                QuerySnapshot snapshot = future.get();
                if (snapshot != null && !snapshot.isEmpty()) {
                    List<QueryDocumentSnapshot> documents = snapshot.getDocuments();
                    totalQuestions = documents.size();
                    Platform.runLater(() -> displayAllQuestions(documents));
                } else {
                    showError("No quiz found for this patient.");
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                showError("Error fetching quiz: " + e.getMessage());
            }
        }).start();
    }

    private void displayAllQuestions(List<QueryDocumentSnapshot> documents) {
        int questionNumber = 1;

        for (QueryDocumentSnapshot doc : documents) {
            String questionText = doc.getString("question");
            String optionA = doc.getString("optionA");
            String optionB = doc.getString("optionB");
            String optionC = doc.getString("optionC");
            String optionD = doc.getString("optionD");
            String correct = doc.getString("correctAnswer");

            ToggleGroup group = new ToggleGroup();

            VBox card = new VBox(10);
            card.setPadding(new Insets(15));
            card.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15; -fx-border-color: #b19cd9; -fx-border-width: 2; -fx-border-radius: 15;");
            card.setMaxWidth(500);

            Label questionLabel = new Label("Q" + questionNumber + ": " + questionText);
            questionLabel.setWrapText(true);
            questionLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            questionLabel.setTextFill(Color.DARKMAGENTA);

            RadioButton a = createOption("A", optionA, group);
            RadioButton b = createOption("B", optionB, group);
            RadioButton c = createOption("C", optionC, group);
            RadioButton d = createOption("D", optionD, group);

            group.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                if (newToggle != null && oldToggle == null) {
                    answeredCount++;
                    updateProgress();
                }
            });

            card.getChildren().addAll(questionLabel, a, b, c, d);
            mainContainer.getChildren().add(card);

            questionEntries.add(new QuestionEntry(group, correct));
            questionNumber++;
        }

        Button submit = new Button("✅ Submit Quiz");
        submit.setStyle("-fx-background-color: #6a1b9a; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-background-radius: 10;");
        submit.setOnAction(e -> evaluateScore());

        mainContainer.getChildren().add(submit);
    }

    private RadioButton createOption(String label, String text, ToggleGroup group) {
        RadioButton option = new RadioButton(label + ": " + text);
        option.setUserData(label);
        option.setToggleGroup(group);
        option.setFont(Font.font("Arial", 14));
        option.setWrapText(true);
        option.setStyle("-fx-padding: 5px;");
        return option;
    }

    private void updateProgress() {
        double progress = (double) answeredCount / totalQuestions;
        progressBar.setProgress(progress);
        progressLabel.setText("Answered " + answeredCount + " of " + totalQuestions);
    }

    private void evaluateScore() {
        int score = 0;
        for (QuestionEntry entry : questionEntries) {
            Toggle selected = entry.group.getSelectedToggle();
            if (selected != null && selected.getUserData().equals(entry.correctAnswer)) {
                score++;
            }
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Quiz Completed");
        alert.setHeaderText("🎉 Your Score: " + score + "/" + totalQuestions);
        alert.setContentText(score >= totalQuestions / 2 ? "Great job!" : "Keep practicing, you can improve!");
        alert.showAndWait();
    }

    private void showError(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
            alert.showAndWait();
        });
    }

    private static class QuestionEntry {
        ToggleGroup group;
        String correctAnswer;

        public QuestionEntry(ToggleGroup group, String correctAnswer) {
            this.group = group;
            this.correctAnswer = correctAnswer;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
