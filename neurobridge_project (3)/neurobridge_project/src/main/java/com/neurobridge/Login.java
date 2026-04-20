/*package com.neurobridge;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.neurobridge.FirebaseAuthService;

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

public class Login extends Application {

    private TextField emailField, patientId;
    private PasswordField passwordField;
    private ToggleButton patientBtn, familyBtn;
    private String selectedRole = "Patient"; // default

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        VBox leftPanel = new VBox();
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPrefWidth(400);
        leftPanel.setStyle("-fx-background-color: linear-gradient(to bottom right, #f5f0f6ff, #ffffffff); -fx-background-radius: 20 0 0 20;");
        leftPanel.setPadding(new Insets(30));

        try { 
            Image img = new Image(getClass().getResource("/Assets/Images/loginn.png").toExternalForm());
            ImageView illustration = new ImageView(img);
            illustration.setFitWidth(280);
            illustration.setPreserveRatio(true);
            leftPanel.getChildren().add(illustration);
        } catch (Exception e) {
            Label fallback = new Label("Image not found");
            fallback.setTextFill(Color.WHITE);
            leftPanel.getChildren().add(fallback);
        }

        VBox formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextAlignment(TextAlignment.CENTER);

        emailField = new TextField();
        emailField.setPromptText("Email Address");
        styleInput(emailField);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        styleInput(passwordField);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        HBox roleBox = new HBox(10);
        roleBox.setAlignment(Pos.CENTER);

        ToggleGroup roleGroup = new ToggleGroup();

        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) selectedRole = "Patient";
            else if (newVal == familyBtn) selectedRole = "Family";
        });

        roleBox.getChildren().addAll(patientBtn, familyBtn);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(250);
        loginButton.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String patientIdText = patientId.getText().trim();

            if (email.isEmpty() || password.isEmpty() || patientIdText.isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            boolean authSuccess = FirebaseAuthService.signIn(email, password);

            if (!authSuccess) {
                showAlert("Login Failed", "Invalid email or password.");
                return;
            }

            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<DocumentSnapshot> future = db.collection("users").document(email).get();
            new Thread(() -> {
                try {
                    DocumentSnapshot document = future.get();
                    if (document.exists()) {
                        String dbRole = document.getString("role");
                        String dbPatientId = document.getString("patientId");

                        if (dbRole != null && dbPatientId != null &&
                                dbRole.equalsIgnoreCase(selectedRole) &&
                                dbPatientId.equals(patientIdText)) {

                            // Store in session
                            UserSession.getInstance().setUserDetails(email, dbPatientId, dbRole);

                            Platform.runLater(() -> {
                                primaryStage.close();
                                try {
                                    if (selectedRole.equals("Patient")) {
                                        new DashboardScreen().start(new Stage());
                                    } else {
                                        new FamilyDashboardScreen().start(new Stage());
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });
                        } else {
                            Platform.runLater(() ->
                                showAlert("Validation Failed", "Role or Patient ID does not match our records.")
                            );
                        }
                    } else {
                        Platform.runLater(() ->
                            showAlert("Not Found", "No account found for the provided email.")
                        );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Platform.runLater(() ->
                        showAlert("Error", "Something went wrong. Try again later.")
                    );
                }
            }).start();
        });

        Label signupLabel = new Label("Don't have an account?");
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setOnAction(e -> {
            primaryStage.close();
            new Signup().start(new Stage());
        });

        HBox signupBox = new HBox(5, signupLabel, signupLink);
        signupBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(title, emailField, passwordField, patientId, roleBox, loginButton, signupBox);

        HBox mainBox = new HBox(leftPanel, formPane);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 5, 5);");

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");
        root.getChildren().add(mainBox);
        StackPane.setAlignment(mainBox, Pos.CENTER);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setMaxWidth(300);
        field.setFont(Font.font("Arial", 14));
        field.setStyle("-fx-background-radius: 10;-fx-border-radius: 10;-fx-border-color: #ccc;-fx-padding: 10;");
    }

    private void styleRoleButton(ToggleButton btn) {
        btn.setStyle("-fx-background-radius: 10; -fx-font-weight: bold;");
        btn.setPrefWidth(100);
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/

/*package com.neurobridge;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
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

public class Login extends Application {

    private TextField emailField, patientId;
    private PasswordField passwordField;
    private ToggleButton patientBtn, familyBtn;
    private String selectedRole = "Patient"; // default

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Left Panel (Image Side)
        VBox leftPanel = new VBox();
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPrefWidth(500);
        leftPanel.setStyle("-fx-background-color: #000000ff; -fx-background-radius: 20 0 0 20;");
        leftPanel.setPadding(new Insets(40));

        try {
            Image img = new Image(getClass().getResource("/Assets/Images/signup.png").toExternalForm());
            ImageView illustration = new ImageView(img);
            illustration.setFitWidth(370);
            illustration.setPreserveRatio(true);
            leftPanel.getChildren().add(illustration);
        } catch (Exception e) {
            Label fallback = new Label("Image not found");
            fallback.setTextFill(Color.GRAY);
            leftPanel.getChildren().add(fallback);
        }

        // Right Panel (Form Side)
        VBox formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: white; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        emailField = new TextField();
        emailField.setPromptText("Email Address");
        styleInput(emailField);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        styleInput(passwordField);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ToggleGroup roleGroup = new ToggleGroup();
        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        HBox roleBox = new HBox(10, patientBtn, familyBtn);
        roleBox.setAlignment(Pos.CENTER);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) selectedRole = "Patient";
            else if (newVal == familyBtn) selectedRole = "Family";
        });

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(250);
        loginButton.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String patientIdText = patientId.getText().trim();

            if (email.isEmpty() || password.isEmpty() || patientIdText.isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            boolean authSuccess = FirebaseAuthService.signIn(email, password);

            if (!authSuccess) {
                showAlert("Login Failed", "Invalid email or password.");
                return;
            }

            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<DocumentSnapshot> future = db.collection("users").document(email).get();
            new Thread(() -> {
                try {
                    DocumentSnapshot document = future.get();
                    if (document.exists()) {
                        String dbRole = document.getString("role");
                        String dbPatientId = document.getString("patientId");

                        if (dbRole != null && dbPatientId != null &&
                                dbRole.equalsIgnoreCase(selectedRole) &&
                                dbPatientId.equals(patientIdText)) {

                            UserSession.getInstance().setUserDetails(email, dbPatientId, dbRole);

                            Platform.runLater(() -> {
                                primaryStage.close();
                                try {
                                    if (selectedRole.equals("Patient")) {
                                        new DashboardScreen().start(new Stage());
                                    } else {
                                        new FamilyDashboardScreen().start(new Stage());
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });
                        } else {
                            Platform.runLater(() ->
                                showAlert("Validation Failed", "Role or Patient ID does not match our records.")
                            );
                        }
                    } else {
                        Platform.runLater(() ->
                            showAlert("Not Found", "No account found for the provided email.")
                        );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Platform.runLater(() ->
                        showAlert("Error", "Something went wrong. Try again later.")
                    );
                }
            }).start();
        });

        Label signupLabel = new Label("Don't have an account?");
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setOnAction(e -> {
            primaryStage.close();
            new Signup().start(new Stage());
        });

        HBox signupBox = new HBox(5, signupLabel, signupLink);
        signupBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(title, emailField, passwordField, patientId, roleBox, loginButton, signupBox);

        // Main Container
        HBox mainBox = new HBox(leftPanel, formPane);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setMaxWidth(1000);
        mainBox.setMaxHeight(600);
        mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 5, 5);");

        StackPane root = new StackPane(mainBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");
        StackPane.setAlignment(mainBox, Pos.CENTER);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setMaxWidth(300);
        field.setFont(Font.font("Arial", 14));
        field.setStyle("-fx-background-radius: 10;-fx-border-radius: 10;-fx-border-color: #ccc;-fx-padding: 10;");
    }

    private void styleRoleButton(ToggleButton btn) {
        btn.setStyle("-fx-background-radius: 10; -fx-font-weight: bold;");
        btn.setPrefWidth(100);
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/


/*package com.neurobridge;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Login extends Application {

    private TextField emailField, patientId;
    private PasswordField passwordField;
    private ToggleButton patientBtn, familyBtn;
    private String selectedRole = "Patient"; // default

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");


       StackPane leftPanel = new StackPane();
leftPanel.setPrefWidth(500);
leftPanel.setPrefHeight(600);
leftPanel.setStyle("-fx-background-radius: 20 0 0 20;");

try {
    Image img = new Image(getClass().getResource("/Assets/Images/signup.png").toExternalForm());
    ImageView illustration = new ImageView(img);
    illustration.setFitWidth(500);
    illustration.setFitHeight(600);
    illustration.setPreserveRatio(false);
    illustration.setSmooth(true);

    // Clip the image to match the panel’s rounded corners
    Rectangle clip = new Rectangle(500, 600);
    clip.setArcWidth(40);
    clip.setArcHeight(40);
    illustration.setClip(clip);

    leftPanel.getChildren().add(illustration);
} catch (Exception e) {
    Label fallback = new Label("Image not found");
    fallback.setTextFill(Color.GRAY);
    leftPanel.getChildren().add(fallback);
}



        // Right Panel (Form Side)
        VBox formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: white; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        emailField = new TextField();
        emailField.setPromptText("Email Address");
        styleInput(emailField);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        styleInput(passwordField);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ToggleGroup roleGroup = new ToggleGroup();
        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        HBox roleBox = new HBox(10, patientBtn, familyBtn);
        roleBox.setAlignment(Pos.CENTER);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) selectedRole = "Patient";
            else if (newVal == familyBtn) selectedRole = "Family";
        });

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(250);
        loginButton.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String patientIdText = patientId.getText().trim();

            if (email.isEmpty() || password.isEmpty() || patientIdText.isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            boolean authSuccess = FirebaseAuthService.signIn(email, password);

            if (!authSuccess) {
                showAlert("Login Failed", "Invalid email or password.");
                return;
            }

            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<DocumentSnapshot> future = db.collection("users").document(email).get();
            new Thread(() -> {
                try {
                    DocumentSnapshot document = future.get();
                    if (document.exists()) {
                        String dbRole = document.getString("role");
                        String dbPatientId = document.getString("patientId");

                        if (dbRole != null && dbPatientId != null &&
                                dbRole.equalsIgnoreCase(selectedRole) &&
                                dbPatientId.equals(patientIdText)) {

                            UserSession.getInstance().setUserDetails(email, dbPatientId, dbRole);

                            Platform.runLater(() -> {
                                primaryStage.close();
                                try {
                                    if (selectedRole.equals("Patient")) {
                                        new DashboardScreen().start(new Stage());
                                    } else {
                                        new FamilyDashboardScreen().start(new Stage());
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });
                        } else {
                            Platform.runLater(() ->
                                    showAlert("Validation Failed", "Role or Patient ID does not match our records.")
                            );
                        }
                    } else {
                        Platform.runLater(() ->
                                showAlert("Not Found", "No account found for the provided email.")
                        );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Platform.runLater(() ->
                            showAlert("Error", "Something went wrong. Try again later.")
                    );
                }
            }).start();
        });

        Label signupLabel = new Label("Don't have an account?");
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setOnAction(e -> {
            primaryStage.close();
            new Signup().start(new Stage());
        });

        HBox signupBox = new HBox(5, signupLabel, signupLink);
        signupBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(title, emailField, passwordField, patientId, roleBox, loginButton, signupBox);

        // Main Container
        HBox mainBox = new HBox(leftPanel, formPane);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setMaxWidth(1000);
        mainBox.setMaxHeight(600);
        mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 5, 5);");

        StackPane root = new StackPane(mainBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");
        StackPane.setAlignment(mainBox, Pos.CENTER);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setMaxWidth(300);
        field.setFont(Font.font("Arial", 14));
        field.setStyle("-fx-background-radius: 10;-fx-border-radius: 10;-fx-border-color: #ccc;-fx-padding: 10;");
    }

    private void styleRoleButton(ToggleButton btn) {
        btn.setStyle("-fx-background-radius: 10; -fx-font-weight: bold;");
        btn.setPrefWidth(100);
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/


/*package com.neurobridge;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Login extends Application {

    private TextField emailField, patientId;
    private PasswordField passwordField;
    private ToggleButton patientBtn, familyBtn;
    private String selectedRole = "Patient"; // default

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Left Panel
        StackPane leftPanel = new StackPane();
        leftPanel.setPrefWidth(500);
        leftPanel.setPrefHeight(600);
        leftPanel.setStyle("-fx-background-radius: 20 0 0 20;");

        try {
            Image img = new Image(getClass().getResource("/Assets/Images/signup.png").toExternalForm());
            ImageView illustration = new ImageView(img);
            illustration.setFitWidth(500);
            illustration.setFitHeight(600);
            illustration.setPreserveRatio(false);
            illustration.setSmooth(true);

            Rectangle clip = new Rectangle(500, 600);
            clip.setArcWidth(40);
            clip.setArcHeight(40);
            illustration.setClip(clip);

            leftPanel.getChildren().add(illustration);
        } catch (Exception e) {
            Label fallback = new Label("Image not found");
            fallback.setTextFill(Color.GRAY);
            leftPanel.getChildren().add(fallback);
        }

        // Right Panel
        VBox formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: white; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));

        emailField = new TextField();
        emailField.setPromptText("Email Address");
        styleInput(emailField);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        styleInput(passwordField);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ToggleGroup roleGroup = new ToggleGroup();
        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        HBox roleBox = new HBox(10, patientBtn, familyBtn);
        roleBox.setAlignment(Pos.CENTER);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) selectedRole = "Patient";
            else if (newVal == familyBtn) selectedRole = "Family";
        });

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(250);
        loginButton.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String patientIdText = patientId.getText().trim();

            if (email.isEmpty() || password.isEmpty() || patientIdText.isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            boolean authSuccess = FirebaseAuthService.signIn(email, password);

            if (!authSuccess) {
                showAlert("Login Failed", "Invalid email or password.");
                return;
            }

            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<DocumentSnapshot> future = db.collection("users").document(email).get();
            new Thread(() -> {
                try {
                    DocumentSnapshot document = future.get();
                    if (document.exists()) {
                        String dbRole = document.getString("role");
                        String dbPatientId = document.getString("patientId");

                        if (dbRole != null && dbPatientId != null &&
                                dbRole.equalsIgnoreCase(selectedRole) &&
                                dbPatientId.equals(patientIdText)) {

                            UserSession.getInstance().setUserDetails(email, dbPatientId, dbRole);

                            Platform.runLater(() -> {
                                primaryStage.close();
                                try {
                                    if (selectedRole.equals("Patient")) {
                                        new DashboardScreen().start(new Stage());
                                    } else {
                                        new FamilyDashboardScreen().start(new Stage());
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });
                        } else {
                            Platform.runLater(() ->
                                    showAlert("Validation Failed", "Role or Patient ID does not match our records.")
                            );
                        }
                    } else {
                        Platform.runLater(() ->
                                showAlert("Not Found", "No account found for the provided email.")
                        );
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Platform.runLater(() ->
                            showAlert("Error", "Something went wrong. Try again later.")
                    );
                }
            }).start();
        });

        Label signupLabel = new Label("Don't have an account?");
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setOnAction(e -> {
            primaryStage.close();
            new Signup().start(new Stage());
        });

        HBox signupBox = new HBox(5, signupLabel, signupLink);
        signupBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(title, emailField, passwordField, patientId, roleBox, loginButton, signupBox);

        // Combined Center Layout
        HBox mainBox = new HBox(leftPanel, formPane);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setMaxWidth(1000);
        mainBox.setMaxHeight(600);
        mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 5, 5);");

        // Background ImageView (full scene)
        ImageView backgroundView = new ImageView(new Image(getClass().getResource("/Assets/Images/signbg.png").toExternalForm()));
        backgroundView.setFitWidth(1550);
        backgroundView.setFitHeight(800);
        backgroundView.setPreserveRatio(false);
        //backgroundView.setOpacity(0.3); // Slightly transparent

        // Root container
        StackPane root = new StackPane(backgroundView, mainBox);
        StackPane.setAlignment(mainBox, Pos.CENTER);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setMaxWidth(300);
        field.setFont(Font.font("Arial", 14));
        field.setStyle("-fx-background-radius: 10;-fx-border-radius: 10;-fx-border-color: #ccc;-fx-padding: 10;");
    }

    private void styleRoleButton(ToggleButton btn) {
        btn.setStyle("-fx-background-radius: 10; -fx-font-weight: bold;");
        btn.setPrefWidth(100);
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/
package com.neurobridge;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
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

public class Login extends Application {

    private TextField emailField, patientId;
    private PasswordField passwordField;
    private ToggleButton patientBtn, familyBtn;
    private String selectedRole = "Patient";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login");

        // Left Panel (Image + background)
        VBox leftPanel = new VBox();
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setPrefWidth(400);
        leftPanel.setStyle("-fx-background-color: #eef1ff; -fx-background-radius: 20 0 0 20;");
        leftPanel.setPadding(new Insets(30));

        try {
            Image img = new Image(getClass().getResource("/Assets/Images/loginn.png").toExternalForm());
            ImageView illustration = new ImageView(img);
            illustration.setFitWidth(280);
            illustration.setPreserveRatio(true);
            leftPanel.getChildren().add(illustration);
        } catch (Exception e) {
            Label fallback = new Label("Image not found");
            fallback.setTextFill(Color.GRAY);
            leftPanel.getChildren().add(fallback);
        }

        // Right Panel (Form)
        VBox formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Login");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextAlignment(TextAlignment.CENTER);

        emailField = new TextField();
        emailField.setPromptText("Email Address");
        styleInput(emailField);

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        styleInput(passwordField);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ToggleGroup roleGroup = new ToggleGroup();

        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) selectedRole = "Patient";
            else if (newVal == familyBtn) selectedRole = "Family";
        });

        HBox roleBox = new HBox(10, patientBtn, familyBtn);
        roleBox.setAlignment(Pos.CENTER);

        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(250);
        loginButton.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String patientIdText = patientId.getText().trim();

            if (email.isEmpty() || password.isEmpty() || patientIdText.isEmpty()) {
                showAlert("Error", "All fields are required.");
                return;
            }

            boolean authSuccess = FirebaseAuthService.signIn(email, password);

            if (!authSuccess) {
                showAlert("Login Failed", "Invalid email or password.");
                return;
            }

            Firestore db = FirestoreClient.getFirestore();
            ApiFuture<DocumentSnapshot> future = db.collection("users").document(email).get();

            new Thread(() -> {
                try {
                    DocumentSnapshot document = future.get();
                    if (document.exists()) {
                        String dbRole = document.getString("role");
                        String dbPatientId = document.getString("patientId");

                        if (dbRole != null && dbPatientId != null &&
                                dbRole.equalsIgnoreCase(selectedRole) &&
                                dbPatientId.equals(patientIdText)) {

                            UserSession.getInstance().setUserDetails(email, dbPatientId, dbRole);

                            Platform.runLater(() -> {
                                primaryStage.close();
                                try {
                                    if (selectedRole.equals("Patient")) {
                                        new DashboardScreen().start(new Stage());
                                    } else {
                                        new FamilyDashboardScreen().start(new Stage());
                                    }
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            });
                        } else {
                            Platform.runLater(() -> showAlert("Validation Failed", "Role or Patient ID does not match our records."));
                        }
                    } else {
                        Platform.runLater(() -> showAlert("Not Found", "No account found for the provided email."));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Platform.runLater(() -> showAlert("Error", "Something went wrong. Try again later."));
                }
            }).start();
        });

        Label signupLabel = new Label("Don't have an account?");
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setOnAction(e -> {
            primaryStage.close();
            new Signup().start(new Stage());
        });

        HBox signupBox = new HBox(5, signupLabel, signupLink);
        signupBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(
                title,
                emailField,
                passwordField,
                patientId,
                roleBox,
                loginButton,
                signupBox
        );

        HBox mainBox = new HBox(leftPanel, formPane);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setMaxHeight(600);
        mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 15, 0, 5, 5);");

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");
        root.setPadding(new Insets(40, 20, 40, 20));
        root.getChildren().add(mainBox);
        StackPane.setAlignment(mainBox, Pos.CENTER);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setMaxWidth(300);
        field.setFont(Font.font("Arial", 14));
        field.setStyle("-fx-background-radius: 10;-fx-border-radius: 10;-fx-border-color: #ccc;-fx-padding: 10;");
    }

    private void styleRoleButton(ToggleButton btn) {
        btn.setStyle("-fx-background-radius: 10; -fx-font-weight: bold;");
        btn.setPrefWidth(100);
    }

    private void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}

