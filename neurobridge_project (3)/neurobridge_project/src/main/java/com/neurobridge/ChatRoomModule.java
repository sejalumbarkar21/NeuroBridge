/*package com.neurobridge;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChatRoomModule extends Application {

    private static final String API_KEY = "AIzaSyC9UiO-DcVtrSB7v_092ttbGMtuljOSBpE"; // Replace with your Gemini API key
    private static final String MODEL = "gemini-1.5-flash-latest";

    private VBox messageBox;
    private ScrollPane scrollPane;
    private TextField inputField;
    private Button sendBtn;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("NeuroBridge - Chat");

        // Top Bar
        HBox titleBar = new HBox();
        titleBar.setPadding(new Insets(15));
        titleBar.setStyle("-fx-background-color: #a18fff;");
        titleBar.setAlignment(Pos.CENTER_LEFT);

        Label titleText = new Label("💬 Chat with Gemini AI");
        titleText.setFont(new Font("Arial Rounded MT Bold", 20));
        titleText.setStyle("-fx-text-fill: #3a3a3a;");
        titleBar.getChildren().add(titleText);

        // Message Area
        messageBox = new VBox(10);
        messageBox.setPadding(new Insets(10));
        messageBox.setStyle("-fx-background-color: white;");
        scrollPane = new ScrollPane(messageBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: white; -fx-border-color: transparent;");

        // Input Area
        inputField = new TextField();
        inputField.setPromptText("Type a message...");
        inputField.setPrefHeight(40);
        inputField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25;");
        inputField.setOnAction(e -> sendMessage());
        HBox.setHgrow(inputField, Priority.ALWAYS);

        sendBtn = new Button("Send");
        sendBtn.setPrefHeight(40);
        sendBtn.setPrefWidth(80);
        sendBtn.setStyle(
            "-fx-background-color: #acfbb0ff;" +
            "-fx-text-fill: #000000;" +
            "-fx-background-radius: 30;" +
            "-fx-border-radius: 30;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );
        sendBtn.setOnAction(e -> sendMessage());

        HBox inputArea = new HBox(10, inputField, sendBtn);
        inputArea.setPadding(new Insets(10));
        inputArea.setStyle("-fx-background-color: #f9f9f9;");
        inputArea.setAlignment(Pos.CENTER);

        VBox chatContainer = new VBox(titleBar, scrollPane, inputArea);
        chatContainer.setPadding(new Insets(30, 0, 0, 0));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Scene scene = new Scene(chatContainer, 1000, 800, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        String timestamp = getCurrentTime();
        addMessage("You", userText, Pos.BASELINE_RIGHT, "#acfbb0ff", timestamp);
        inputField.clear();

        Label typingLabel = new Label("Gemini is typing...");
        typingLabel.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
        messageBox.getChildren().add(typingLabel);

        new Thread(() -> {
            try {
                String reply = getGeminiResponse(userText);

                Platform.runLater(() -> {
                    messageBox.getChildren().remove(typingLabel);
                    addMessage("Gemini", reply, Pos.BASELINE_LEFT, "#a18fff", getCurrentTime());
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    messageBox.getChildren().remove(typingLabel);
                    addMessage("Gemini", "Oops! Something went wrong.", Pos.BASELINE_LEFT, "#f7f0ff", getCurrentTime());
                });
            }
        }).start();
    }

    private void addMessage(String sender, String message, Pos alignment, String bgColor, String timestamp) {
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(500);
        messageLabel.setPadding(new Insets(10));
        messageLabel.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                "-fx-background-radius: 18;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 14px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 1);"
        );

        Label timeLabel = new Label(timestamp);
        timeLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 11px;");
        timeLabel.setPadding(new Insets(2, 0, 0, 0));

        VBox bubbleContainer = new VBox(messageLabel, timeLabel);
        bubbleContainer.setAlignment(alignment == Pos.BASELINE_RIGHT ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        bubbleContainer.setSpacing(3);

        HBox wrapper = new HBox(bubbleContainer);
        wrapper.setAlignment(alignment);
        wrapper.setPadding(new Insets(5, 10, 5, 10));

        messageBox.getChildren().add(wrapper);
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.now().format(formatter).toLowerCase();
    }

    private String getGeminiResponse(String prompt) throws Exception {
        URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/" + MODEL + ":generateContent?key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String payload = new JSONObject()
                .put("contents", new JSONArray()
                        .put(new JSONObject()
                                .put("parts", new JSONArray()
                                        .put(new JSONObject().put("text", prompt))
                                )
                        )
                ).toString();

        OutputStream os = conn.getOutputStream();
        os.write(payload.getBytes());
        os.flush();
        os.close();

        if (conn.getResponseCode() != 200) {
            Scanner errorScanner = new Scanner(conn.getErrorStream());
            StringBuilder err = new StringBuilder();
            while (errorScanner.hasNext()) err.append(errorScanner.nextLine());
            errorScanner.close();
            throw new RuntimeException("API Error: " + err.toString());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) response.append(scanner.nextLine());
        scanner.close();

        JSONObject responseJson = new JSONObject(response.toString());
        JSONArray candidates = responseJson.getJSONArray("candidates");

        return candidates.getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
package com.neurobridge;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ChatRoomModule extends Application {

    private static final String API_KEY = "AIzaSyC9UiO-DcVtrSB7v_092ttbGMtuljOSBpE";
    private static final String MODEL = "gemini-1.5-flash-latest";

    private VBox messageBox;
    private ScrollPane scrollPane;
    private TextField inputField;
    private Button sendBtn;

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("NeuroBridge - Chat");

        // Top Bar with Back Button
        HBox titleBar = new HBox(10);
        titleBar.setPadding(new Insets(15));
        titleBar.setStyle("-fx-background-color: #a18fff;");
        titleBar.setAlignment(Pos.CENTER_LEFT);

        // 👇 Back Button
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> goToDashboard());

        Label titleText = new Label("💬 Chat with Gemini AI");
        titleText.setFont(new Font("Arial Rounded MT Bold", 20));
        titleText.setStyle("-fx-text-fill: #ffffff;");
        titleBar.getChildren().addAll(backButton, titleText);

        // Message Area
        messageBox = new VBox(10);
        messageBox.setPadding(new Insets(10));
        messageBox.setStyle("-fx-background-color: white;");
        scrollPane = new ScrollPane(messageBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: white; -fx-border-color: transparent;");

        // Input Area
        inputField = new TextField();
        inputField.setPromptText("Type a message...");
        inputField.setPrefHeight(40);
        inputField.setStyle("-fx-background-radius: 25; -fx-border-radius: 25;");
        inputField.setOnAction(e -> sendMessage());
        HBox.setHgrow(inputField, Priority.ALWAYS);

        sendBtn = new Button("Send");
        sendBtn.setPrefHeight(40);
        sendBtn.setPrefWidth(80);
        sendBtn.setStyle(
            "-fx-background-color: #acfbb0ff;" +
            "-fx-text-fill: #000000;" +
            "-fx-background-radius: 30;" +
            "-fx-border-radius: 30;" +
            "-fx-font-weight: bold;" +
            "-fx-cursor: hand;"
        );
        sendBtn.setOnAction(e -> sendMessage());

        HBox inputArea = new HBox(10, inputField, sendBtn);
        inputArea.setPadding(new Insets(10));
        inputArea.setStyle("-fx-background-color: #f9f9f9;");
        inputArea.setAlignment(Pos.CENTER);

        VBox chatContainer = new VBox(titleBar, scrollPane, inputArea);
        chatContainer.setPadding(new Insets(30, 0, 0, 0));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Scene scene = new Scene(chatContainer, 1550, 800, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void sendMessage() {
        String userText = inputField.getText().trim();
        if (userText.isEmpty()) return;

        String timestamp = getCurrentTime();
        addMessage("You", userText, Pos.BASELINE_RIGHT, "#acfbb0ff", timestamp);
        inputField.clear();

        Label typingLabel = new Label("Gemini is typing...");
        typingLabel.setStyle("-fx-text-fill: gray; -fx-font-style: italic;");
        messageBox.getChildren().add(typingLabel);

        new Thread(() -> {
            try {
                String reply = getGeminiResponse(userText);

                Platform.runLater(() -> {
                    messageBox.getChildren().remove(typingLabel);
                    addMessage("Gemini", reply, Pos.BASELINE_LEFT, "#a18fff", getCurrentTime());
                });

            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> {
                    messageBox.getChildren().remove(typingLabel);
                    addMessage("Gemini", "Oops! Something went wrong.", Pos.BASELINE_LEFT, "#f7f0ff", getCurrentTime());
                });
            }
        }).start();
    }

    private void addMessage(String sender, String message, Pos alignment, String bgColor, String timestamp) {
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(500);
        messageLabel.setPadding(new Insets(10));
        messageLabel.setStyle(
                "-fx-background-color: " + bgColor + ";" +
                "-fx-background-radius: 18;" +
                "-fx-text-fill: #000000;" +
                "-fx-font-size: 14px;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 5, 0, 0, 1);"
        );

        Label timeLabel = new Label(timestamp);
        timeLabel.setStyle("-fx-text-fill: gray; -fx-font-size: 11px;");
        timeLabel.setPadding(new Insets(2, 0, 0, 0));

        VBox bubbleContainer = new VBox(messageLabel, timeLabel);
        bubbleContainer.setAlignment(alignment == Pos.BASELINE_RIGHT ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        bubbleContainer.setSpacing(3);

        HBox wrapper = new HBox(bubbleContainer);
        wrapper.setAlignment(alignment);
        wrapper.setPadding(new Insets(5, 10, 5, 10));

        messageBox.getChildren().add(wrapper);
        Platform.runLater(() -> scrollPane.setVvalue(1.0));
    }

    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return LocalTime.now().format(formatter).toLowerCase();
    }

    private String getGeminiResponse(String prompt) throws Exception {
        URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/" + MODEL + ":generateContent?key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String payload = new JSONObject()
                .put("contents", new JSONArray()
                        .put(new JSONObject()
                                .put("parts", new JSONArray()
                                        .put(new JSONObject().put("text", prompt))
                                )
                        )
                ).toString();

        OutputStream os = conn.getOutputStream();
        os.write(payload.getBytes());
        os.flush();
        os.close();

        if (conn.getResponseCode() != 200) {
            Scanner errorScanner = new Scanner(conn.getErrorStream());
            StringBuilder err = new StringBuilder();
            while (errorScanner.hasNext()) err.append(errorScanner.nextLine());
            errorScanner.close();
            throw new RuntimeException("API Error: " + err.toString());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) response.append(scanner.nextLine());
        scanner.close();

        JSONObject responseJson = new JSONObject(response.toString());
        JSONArray candidates = responseJson.getJSONArray("candidates");

        return candidates.getJSONObject(0)
                .getJSONObject("content")
                .getJSONArray("parts")
                .getJSONObject(0)
                .getString("text");
    }

    // 👇 Navigation to Dashboard
    private void goToDashboard() {
        try {
            new FamilyDashboardScreen().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
