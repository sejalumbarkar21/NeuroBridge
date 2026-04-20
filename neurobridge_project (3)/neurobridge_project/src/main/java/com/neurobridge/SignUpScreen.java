package com.neurobridge;



/*import javafx.application.Application;
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
        leftPanel.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff); -fx-background-radius: 20 0 0 20;");
        leftPanel.setPadding(new Insets(30));

        try {
            Image img = new Image(getClass().getResource("/Assets/Images/loginn.png").toExternalForm());
            ImageView illustration = new ImageView(img);
            illustration.setFitWidth(300);
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
            } else {
                // Replace this with real Firebase logic
                boolean success = true; // Dummy success
                if (success) {
                    primaryStage.close();
                    try {
                        if (selectedRole.equals("Patient")) {
                            new com.neurobridge.DashboardScreen().start(new Stage());
                        } else {
                            new FamilyDashboardScreen().start(new Stage());
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    showAlert("Failure", "Invalid credentials.");
                }
            }
        });

        Label signupLabel = new Label("Don't have an account?");
        Hyperlink signupLink = new Hyperlink("Sign Up");
        signupLink.setOnAction(e -> {
            primaryStage.close();
            new Signup().start(new Stage()); // if you have a Signup class
        });

        HBox signupBox = new HBox(5, signupLabel, signupLink);
        signupBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(title, emailField, passwordField, patientId, roleBox, loginButton, signupBox);

        //HBox mainBox = new HBox(leftPanel, formPane);
        //mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 5, 5);");

        //StackPane root = new StackPane(mainBox);
        //root.setPadding(new Insets(50));
        //root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");

        HBox mainBox = new HBox(leftPanel, formPane);
        mainBox.setAlignment(Pos.CENTER); // horizontally align children
        mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 5, 5);");

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");
        root.getChildren().add(mainBox);
        StackPane.setAlignment(mainBox, Pos.CENTER);// <-- Center the login card in the scene


         // <-- Center the login card in the scene


        Scene scene = new Scene(root, 1000, 800);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/


/*import com.google.api.core.ApiFuture;
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
        leftPanel.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff); -fx-background-radius: 20 0 0 20;");
        leftPanel.setPadding(new Insets(30));

        try { 
            Image img = new Image(getClass().getResource("/Assets/Images/loginn.png").toExternalForm());
            ImageView illustration = new ImageView(img);
            illustration.setFitWidth(300);
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

        Scene scene = new Scene(root, 1000, 800);
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




import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SignUpScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load brain image
        Image brainImage = new Image("file:src/main/resources/images/brain.png");
        ImageView brainView = new ImageView(brainImage);
        brainView.setPreserveRatio(true);
        brainView.setFitWidth(160);

        // App name
        Label appName = new Label("NEUROBRIDGE");
        appName.setFont(Font.font("Segoe UI", FontWeight.BOLD, 48));
        appName.setTextFill(Color.WHITE);
        appName.setEffect(new DropShadow(30, Color.web("#f38fd099")));

        // Tagline
        Label tagline = new Label("Connecting Memories");
        tagline.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 20));
        tagline.setTextFill(Color.web("#ffffffcc"));

        VBox headerBox = new VBox(10, brainView, appName, tagline);
        headerBox.setAlignment(Pos.CENTER);

        // Input Fields
        TextField fullNameField = createStyledTextField("Full Name");
        TextField emailField = createStyledTextField("Email");
        PasswordField passwordField = createStyledPasswordField("Password");
        TextField patientIdField = createStyledTextField("Patient ID");

        // Role Toggle
        ToggleGroup roleToggleGroup = new ToggleGroup();
        RadioButton patientToggle = createToggle("Patient", roleToggleGroup);
        RadioButton familyToggle = createToggle("Family", roleToggleGroup);
        patientToggle.setSelected(true);

        HBox toggleBox = new HBox(20, patientToggle, familyToggle);
        toggleBox.setAlignment(Pos.CENTER);

        // Sign Up Button
        Button signUpButton = new Button("Sign Up");
        signUpButton.setStyle("-fx-background-color: #ffffffaa; -fx-text-fill: #5a189a; -fx-font-weight: bold;");
        signUpButton.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        signUpButton.setPrefWidth(300);
        signUpButton.setCursor(javafx.scene.Cursor.HAND);

        // Login link
        Label loginPrompt = new Label("Already have an account?");
        loginPrompt.setTextFill(Color.WHITE);
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        loginLink.setTextFill(Color.WHITE);
        loginLink.setBorder(Border.EMPTY);
        loginLink.setOnAction(e -> System.out.println("Login clicked"));

        HBox loginBox = new HBox(5, loginPrompt, loginLink);
        loginBox.setAlignment(Pos.CENTER);

        // Form VBox
        VBox formBox = new VBox(20,
                fullNameField, emailField, passwordField, patientIdField,
                toggleBox, signUpButton, loginBox);
        formBox.setAlignment(Pos.CENTER);
        formBox.setPadding(new Insets(30));
        formBox.setMaxWidth(400);

        // Combine all content
        VBox content = new VBox(40, headerBox, formBox);
        content.setAlignment(Pos.CENTER);

        // Background gradient
        RadialGradient gradient = new RadialGradient(
                0, 0, 0.5, 0.5, 1,
                true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        );

        StackPane root = new StackPane(content);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // Full screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        primaryStage.setTitle("NeuroBridge - Sign Up");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private TextField createStyledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setMaxWidth(300);
        tf.setFont(Font.font("Segoe UI", 14));
        tf.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: white;");
        return tf;
    }

    private PasswordField createStyledPasswordField(String prompt) {
        PasswordField pf = new PasswordField();
        pf.setPromptText(prompt);
        pf.setMaxWidth(300);
        pf.setFont(Font.font("Segoe UI", 14));
        pf.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: white;");
        return pf;
    }

    private RadioButton createToggle(String label, ToggleGroup group) {
        RadioButton rb = new RadioButton(label);
        rb.setToggleGroup(group);
        rb.setTextFill(Color.WHITE);
        rb.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        return rb;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
