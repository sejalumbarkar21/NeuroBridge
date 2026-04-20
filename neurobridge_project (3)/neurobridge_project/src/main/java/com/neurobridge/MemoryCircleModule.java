package com.neurobridge;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MemoryCircleModule extends Application {

    private VBox messageBox;
    private TextField inputField;
    private ScrollPane scrollPane;
    private final String COLLECTION_NAME = "memory_circle";
    private VBox root;
    private Scene scene;
    private Label typingIndicator;

    @Override
    public void start(Stage stage) {
        FirebaseConfig.initialize();

        root = new VBox();
        root.setSpacing(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #f8f4ff, #e8dcff);");

        Label title = new Label("🧠 Memory Circle");
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#5d3fd3"));

        scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        messageBox = new VBox(12);
        messageBox.setPadding(new Insets(10));
        scrollPane.setContent(messageBox);

        HBox inputArea = new HBox(10);
        inputArea.setAlignment(Pos.CENTER);
        inputArea.setPadding(new Insets(10));

        inputField = new TextField();
        inputField.setPromptText("Share your thoughts or memories...");
        inputField.setPrefWidth(400);
        inputField.setFont(Font.font("Segoe UI", 14));
        inputField.setStyle("-fx-background-color: white; -fx-border-color: #c0aaff; -fx-border-radius: 20; -fx-background-radius: 20; -fx-padding: 10;");

        Button sendButton = new Button("Send");
        sendButton.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 14));
        sendButton.setStyle("-fx-background-color: #7c4dff; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 8 16;");

        Button uploadImageBtn = new Button("📸");
        uploadImageBtn.setOnAction(e -> chooseImage());
        uploadImageBtn.setStyle("-fx-background-color: #eee; -fx-border-radius: 20; -fx-background-radius: 20;");

        sendButton.setOnAction(e -> sendMessage());
        inputField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) sendMessage();
        });

        typingIndicator = new Label();
        typingIndicator.setFont(Font.font("Segoe UI", 12));
        typingIndicator.setTextFill(Color.GRAY);

        inputArea.getChildren().addAll(inputField, sendButton, uploadImageBtn);

        root.getChildren().addAll(title, scrollPane, typingIndicator, inputArea);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        scene = new Scene(root, 1550, 800);
        stage.setTitle("Memory Circle");
        stage.setScene(scene);
        stage.show();

        listenToMessages();
    }

    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            addImageBubble(UserSession.getInstance().getFullName(), file.toURI().toString(), Timestamp.now(), null);
        }
    }

    private void sendMessage() {
        String messageText = inputField.getText().trim();
        if (messageText.isEmpty()) return;

        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document();

        Message msg = new Message(
                UserSession.getInstance().getPatientId(),
                UserSession.getInstance().getFullName(),
                messageText,
                Timestamp.now(),
                new HashMap<>()
        );

        docRef.set(msg);
        inputField.clear();
    }

    private void listenToMessages() {
        Firestore db = FirestoreClient.getFirestore();
        db.collection(COLLECTION_NAME)
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener((snapshots, e) -> {
                    if (e != null || snapshots == null) return;
                    Platform.runLater(() -> {
                        messageBox.getChildren().clear();
                        for (QueryDocumentSnapshot doc : snapshots) {
                            String name = doc.getString("senderName");
                            String message = doc.getString("message");
                            Timestamp timestamp = doc.getTimestamp("timestamp");
                            Map<String, List<String>> reactions = (Map<String, List<String>>) doc.get("reactions");
                            addMessageBubble(name, message, timestamp, doc.getId(), reactions);
                        }
                    });
                });
    }

    private void addMessageBubble(String senderName, String message, Timestamp timestamp, String docId, Map<String, List<String>> reactions) {
        HBox container = new HBox(10);
        container.setAlignment(Pos.TOP_LEFT);

        Circle avatar = new Circle(20, Color.web("#c2aaff"));
        Label initials = new Label(senderName != null ? senderName.substring(0, 1).toUpperCase() : "?");
        initials.setTextFill(Color.WHITE);
        initials.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));

        StackPane avatarStack = new StackPane(avatar, initials);

        VBox bubble = new VBox(4);
        bubble.setPadding(new Insets(10));
        bubble.setMaxWidth(600);
        bubble.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-border-color: #e0d2ff; -fx-border-radius: 15;");

        Label nameLabel = new Label(senderName);
        nameLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        nameLabel.setTextFill(Color.web("#5d3fd3"));

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setFont(Font.font("Segoe UI", 13));

        HBox emojiBox = new HBox(5);
        String[] emojis = {"❤️", "👍", "😂"};
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference msgRef = db.collection(COLLECTION_NAME).document(docId);
        String currentUser = UserSession.getInstance().getFullName();

        for (String emoji : emojis) {
            int count = reactions != null && reactions.containsKey(emoji) ? reactions.get(emoji).size() : 0;
            Label emojiLabel = new Label(emoji + " " + count);
            emojiLabel.setStyle("-fx-cursor: hand;");
            emojiLabel.setOnMouseClicked(e -> {
                db.runTransaction(transaction -> {
                    DocumentSnapshot snapshot = transaction.get(msgRef).get();
                    Map<String, List<String>> currentReactions = (Map<String, List<String>>) snapshot.get("reactions");
                    if (currentReactions == null) currentReactions = new HashMap<>();
                    List<String> users = currentReactions.getOrDefault(emoji, new ArrayList<>());
                    if (users.contains(currentUser)) users.remove(currentUser);
                    else users.add(currentUser);
                    currentReactions.put(emoji, users);
                    transaction.update(msgRef, "reactions", currentReactions);
                    return null;
                });
            });
            emojiBox.getChildren().add(emojiLabel);
        }

        String time = DateTimeFormatter.ofPattern("hh:mm a")
                .withZone(ZoneId.systemDefault())
                .format(Instant.ofEpochMilli(timestamp.toDate().getTime()));

        Label timeLabel = new Label(time);
        timeLabel.setFont(Font.font("Segoe UI", 10));
        timeLabel.setTextFill(Color.GRAY);

        bubble.getChildren().addAll(nameLabel, messageLabel, emojiBox, timeLabel);
        container.getChildren().addAll(avatarStack, bubble);
        messageBox.getChildren().add(container);
    }

    private void addImageBubble(String senderName, String imageUrl, Timestamp timestamp, String docId) {
        // Optional: Extend emoji reactions to images similarly
    }

    public static class Message {
        private String senderId;
        private String senderName;
        private String message;
        private Timestamp timestamp;
        private Map<String, List<String>> reactions;

        public Message() {}

        public Message(String senderId, String senderName, String message, Timestamp timestamp, Map<String, List<String>> reactions) {
            this.senderId = senderId;
            this.senderName = senderName;
            this.message = message;
            this.timestamp = timestamp;
            this.reactions = reactions;
        }

        public String getSenderId() { return senderId; }
        public void setSenderId(String senderId) { this.senderId = senderId; }
        public String getSenderName() { return senderName; }
        public void setSenderName(String senderName) { this.senderName = senderName; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public Timestamp getTimestamp() { return timestamp; }
        public void setTimestamp(Timestamp timestamp) { this.timestamp = timestamp; }
        public Map<String, List<String>> getReactions() { return reactions; }
        public void setReactions(Map<String, List<String>> reactions) { this.reactions = reactions; }
    }

    public static void main(String[] args) {
        launch(args);
    }
}