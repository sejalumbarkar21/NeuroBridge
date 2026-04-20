/*package com.neurobridge;


import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Signup extends Application {

    private VBox formPane;
    private TextField fullName, email, patientId, ageField;
    private PasswordField password;
    private ComboBox<String> genderBox;
    private String selectedRole = "Patient"; // Default role

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up");

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

        formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Sign Up");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextAlignment(TextAlignment.CENTER);

        // Role Toggle
        ToggleGroup roleGroup = new ToggleGroup();
        //RadioButton patientBtn = new RadioButton("Patient");
        //RadioButton familyBtn = new RadioButton("Family");
        //patientBtn.setToggleGroup(roleGroup);
        //familyBtn.setToggleGroup(roleGroup);
        //patientBtn.setSelected(true);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedRole = ((RadioButton) newVal).getText();
            }
        });

        //HBox roleBox = new HBox(10, patientBtn, familyBtn);
        //roleBox.setAlignment(Pos.CENTER);

        fullName = new TextField();
        fullName.setPromptText("Full Name");
        styleInput(fullName);

        email = new TextField();
        email.setPromptText("Email Address");
        styleInput(email);

        password = new PasswordField();
        password.setPromptText("Password");
        styleInput(password);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ageField = new TextField();
        ageField.setPromptText("Age");
        styleInput(ageField);

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female", "Other");
        genderBox.setPromptText("Select Gender");
        genderBox.setMaxWidth(300);
        genderBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-padding: 10;");

        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setPrefWidth(250);
        signUpBtn.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        signUpBtn.setOnAction(e -> {
            String emailText = email.getText().trim();
            String passwordText = password.getText().trim();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                showAlert("Error", "Email and Password are required.");
                return;
            }

            boolean success = FirebaseAuthService.signUp(emailText, passwordText);
            if (success) {
                Firestore db = FirestoreClient.getFirestore();
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("fullName", fullName.getText().trim());
                userMap.put("email", emailText);
                userMap.put("password", passwordText); // Optional, consider security implications
                userMap.put("role", selectedRole);
                userMap.put("patientId", patientId.getText().trim());
                userMap.put("age", ageField.getText().trim());
                userMap.put("gender", genderBox.getValue());

                db.collection("users").document(emailText).set(userMap);
                showAlert("Success", selectedRole + " registered successfully!");
            } else {
                showAlert("Failure", "Registration failed. Email may already exist or password is too weak.");
            }
        });

        Label loginLabel = new Label("Already have an account?");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setOnAction(e -> {
            primaryStage.close();
            new Login().start(new Stage());
        });
        HBox loginBox = new HBox(5, loginLabel, loginLink);
        loginBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(
                title,
                fullName,
                email,
                password,
                patientId,
                ageField,
                genderBox,
                signUpBtn,
                loginBox
        );

        HBox mainBox = new HBox(leftPanel, formPane);
        mainBox.setStyle("-fx-background-radius: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 5, 5);");

        StackPane root = new StackPane(mainBox);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: linear-gradient(to bottom right, #e54cff, #8755ff);");

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setMaxWidth(300);
        field.setFont(Font.font("Arial", 14));
        field.setStyle("-fx-background-radius: 10;-fx-border-radius: 10;-fx-border-color: #ccc;-fx-padding: 10;");
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
/*package com.neurobridge;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Signup extends Application {

    private VBox formPane;
    private TextField fullName, email, patientId, ageField;
    private PasswordField password;
    private ComboBox<String> genderBox;
    private ToggleButton patientBtn, familyBtn;

    private String selectedRole = "Patient"; // Default role

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up");

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

        formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Sign Up");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextAlignment(TextAlignment.CENTER);

        ToggleGroup roleGroup = new ToggleGroup();
        // You can add role buttons here if needed

        fullName = new TextField();
        fullName.setPromptText("Full Name");
        styleInput(fullName);

        email = new TextField();
        email.setPromptText("Email Address");
        styleInput(email);

        password = new PasswordField();
        password.setPromptText("Password");
        styleInput(password);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ageField = new TextField();
        ageField.setPromptText("Age");
        styleInput(ageField);

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female", "Other");
        genderBox.setPromptText("Select Gender");
        genderBox.setMaxWidth(300);
        genderBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-padding: 10;");

        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setPrefWidth(250);
        signUpBtn.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        signUpBtn.setOnAction(e -> {
            String emailText = email.getText().trim();
            String passwordText = password.getText().trim();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                showAlert("Error", "Email and Password are required.");
                return;
            }

            boolean success = FirebaseAuthService.signUp(emailText, passwordText);
            if (success) {
                Firestore db = FirestoreClient.getFirestore();
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("fullName", fullName.getText().trim());
                userMap.put("email", emailText);
                userMap.put("password", passwordText); // Optional: Remove for security
                userMap.put("role", selectedRole);
                userMap.put("patientId", patientId.getText().trim());
                userMap.put("age", ageField.getText().trim());
                userMap.put("gender", genderBox.getValue());

                db.collection("users").document(emailText).set(userMap);

                showAlert("Success", "Registration successful. Redirecting to login...");

                // Navigate to Login screen
                primaryStage.close();
                try {
                    new Login().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                showAlert("Failure", "Registration failed. Email may already exist or password is too weak.");
            }
        });

        Label loginLabel = new Label("Already have an account?");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setOnAction(e -> {
            primaryStage.close();
            new Login().start(new Stage());
        });
        HBox loginBox = new HBox(5, loginLabel, loginLink);
        loginBox.setAlignment(Pos.CENTER);

        HBox roleBox = new HBox(10);
        roleBox.setAlignment(Pos.CENTER);

        ToggleGroup roleGroup = new ToggleGroup();

        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
           if (newVal == patientBtn) selectedRole = "Patient";
              else if (newVal == familyBtn) selectedRole = "Family";
        });

        roleBox.getChildren().addAll(patientBtn, familyBtn);


        formPane.getChildren().addAll(
                title,
                fullName,
                email,
                password,
                patientId,
                ageField,
                genderBox,
                roleBox,
                signUpBtn,
                loginBox
        );

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
        StackPane.setAlignment(mainBox, Pos.CENTER); // <-- ensure centering

    
        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleInput(TextField field) {
        field.setMaxWidth(300);
        field.setFont(Font.font("Arial", 14));
        field.setStyle("-fx-background-radius: 10;-fx-border-radius: 10;-fx-border-color: #ccc;-fx-padding: 10;");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void styleRoleButton(ToggleButton btn) {
    btn.setStyle("-fx-background-radius: 10; -fx-font-weight: bold;");
    btn.setPrefWidth(100);
   }


    public static void main(String[] args) {
        launch(args);
    }
}*/

/*package com.neurobridge;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Signup extends Application {

    private VBox formPane;
    private TextField fullName, email, patientId, ageField;
    private PasswordField password;
    private ComboBox<String> genderBox;
    private ToggleButton patientBtn, familyBtn;

    private String selectedRole = "Patient"; // Default role

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up");

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

        formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Sign Up");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextAlignment(TextAlignment.CENTER);

        fullName = new TextField();
        fullName.setPromptText("Full Name");
        styleInput(fullName);

        email = new TextField();
        email.setPromptText("Email Address");
        styleInput(email);

        password = new PasswordField();
        password.setPromptText("Password");
        styleInput(password);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ageField = new TextField();
        ageField.setPromptText("Age");
        styleInput(ageField);

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female", "Other");
        genderBox.setPromptText("Select Gender");
        genderBox.setMaxWidth(300);
        genderBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-padding: 10;");

        // --- Role toggle buttons ---
        ToggleGroup roleGroup = new ToggleGroup();

        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) {
                selectedRole = "Patient";
            } else if (newVal == familyBtn) {
                selectedRole = "Family";
            }
        });

        HBox roleBox = new HBox(10, patientBtn, familyBtn);
        roleBox.setAlignment(Pos.CENTER);

        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setPrefWidth(250);
        signUpBtn.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        signUpBtn.setOnAction(e -> {
            String emailText = email.getText().trim();
            String passwordText = password.getText().trim();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                showAlert("Error", "Email and Password are required.");
                return;
            }

            boolean success = FirebaseAuthService.signUp(emailText, passwordText);
            if (success) {
                Firestore db = FirestoreClient.getFirestore();
                Map<String, Object> userMap = new HashMap<>();
                userMap.put("fullName", fullName.getText().trim());
                userMap.put("email", emailText);
                userMap.put("password", passwordText); // ⚠️ Consider removing for production
                userMap.put("role", selectedRole);
                userMap.put("patientId", patientId.getText().trim());
                userMap.put("age", ageField.getText().trim());
                userMap.put("gender", genderBox.getValue());

                db.collection("users").document(emailText).set(userMap);

                showAlert("Success", "Registration successful. Redirecting to login...");

                primaryStage.close();
                try {
                    new Login().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                showAlert("Failure", "Registration failed. Email may already exist or password is too weak.");
            }
        });

        Label loginLabel = new Label("Already have an account?");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setOnAction(e -> {
            primaryStage.close();
            new Login().start(new Stage());
        });

        HBox loginBox = new HBox(5, loginLabel, loginLink);
        loginBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(
                title,
                fullName,
                email,
                password,
                patientId,
                ageField,
                genderBox,
                roleBox,
                signUpBtn,
                loginBox
        );

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

   
}*/

package com.neurobridge;

/*import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.neurobridge.FirebaseAuthService;
import com.neurobridge.FirebaseInitializer;
import com.neurobridge.UserSession;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Signup extends Application {

    private VBox formPane;
    private TextField fullName, email, patientId, ageField;
    private PasswordField password;
    private ComboBox<String> genderBox;
    private ToggleButton patientBtn, familyBtn;

    private String selectedRole = "Patient"; // Default role

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.initialize();
        primaryStage.setTitle("Sign Up");

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

        formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Sign Up");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextAlignment(TextAlignment.CENTER);

        fullName = new TextField();
        fullName.setPromptText("Full Name");
        styleInput(fullName);

        email = new TextField();
        email.setPromptText("Email Address");
        styleInput(email);

        password = new PasswordField();
        password.setPromptText("Password");
        styleInput(password);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ageField = new TextField();
        ageField.setPromptText("Age");
        styleInput(ageField);

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female", "Other");
        genderBox.setPromptText("Select Gender");
        genderBox.setMaxWidth(300);
        genderBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-padding: 10;");

        ToggleGroup roleGroup = new ToggleGroup();
        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) {
                selectedRole = "Patient";
            } else if (newVal == familyBtn) {
                selectedRole = "Family";
            }
        });

        HBox roleBox = new HBox(10, patientBtn, familyBtn);
        roleBox.setAlignment(Pos.CENTER);

        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setPrefWidth(250);
        signUpBtn.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        signUpBtn.setOnAction(e -> {
            String emailText = email.getText().trim();
            String passwordText = password.getText().trim();
            String genderValue = genderBox.getValue();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                showAlert("Error", "Email and Password are required.");
                return;
            }

            if (genderValue == null || genderValue.isEmpty()) {
                showAlert("Error", "Please select a gender.");
                return;
            }

            boolean success = FirebaseAuthService.signUp(
                    emailText,
                    passwordText,
                    patientId.getText().trim(),
                    fullName.getText().trim(),
                    selectedRole,
                    ageField.getText().trim(),
                    genderValue
            );

            if (success) {
                FirebaseInitializer.initialize();
                Firestore db = FirestoreClient.getFirestore();

                Map<String, Object> userMap = new HashMap<>();
                userMap.put("fullName", fullName.getText().trim());
                userMap.put("email", emailText);
                userMap.put("role", selectedRole);
                userMap.put("patientId", patientId.getText().trim());
                userMap.put("age", ageField.getText().trim());
                userMap.put("gender", genderValue);

                db.collection("users").document(emailText).set(userMap);

                // ✅ Store session for reuse
                UserSession.getInstance().setUserDetails(
                        emailText,
                        patientId.getText().trim(),
                        selectedRole
                );

                showAlert("Success", "Registration successful. Redirecting to login...");

                primaryStage.close();
                try {
                    new Login().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                showAlert("Failure", "Registration failed. Email may already exist or password is too weak.");
            }
        });

        Label loginLabel = new Label("Already have an account?");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setOnAction(e -> {
            primaryStage.close();
            new Login().start(new Stage());
        });

        HBox loginBox = new HBox(5, loginLabel, loginLink);
        loginBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(
                title,
                fullName,
                email,
                password,
                patientId,
                ageField,
                genderBox,
                roleBox,
                signUpBtn,
                loginBox
        );

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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}*/





import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Signup extends Application {

    private VBox formPane;
    private TextField fullName, email, patientId, ageField;
    private PasswordField password;
    private ComboBox<String> genderBox;
    private ToggleButton patientBtn, familyBtn;

    private String selectedRole = "Patient"; // Default role

    @Override
    public void start(Stage primaryStage) {
        FirebaseInitializer.initialize();
        primaryStage.setTitle("Sign Up");

        // Left Panel - Illustration and message
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

        // Right Panel - Form
        formPane = new VBox(15);
        formPane.setAlignment(Pos.CENTER);
        formPane.setPadding(new Insets(40));
        formPane.setPrefWidth(600);
        formPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 0 20 20 0;");

        Label title = new Label("Sign Up");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        title.setTextAlignment(TextAlignment.CENTER);

        fullName = new TextField();
        fullName.setPromptText("Full Name");
        styleInput(fullName);

        email = new TextField();
        email.setPromptText("Email Address");
        styleInput(email);

        password = new PasswordField();
        password.setPromptText("Password");
        styleInput(password);

        patientId = new TextField();
        patientId.setPromptText("Patient ID");
        styleInput(patientId);

        ageField = new TextField();
        ageField.setPromptText("Age");
        styleInput(ageField);

        genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female", "Other");
        genderBox.setPromptText("Select Gender");
        genderBox.setMaxWidth(300);
        genderBox.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: #ccc; -fx-padding: 10;");

        ToggleGroup roleGroup = new ToggleGroup();
        patientBtn = new ToggleButton("Patient");
        patientBtn.setToggleGroup(roleGroup);
        patientBtn.setSelected(true);
        styleRoleButton(patientBtn);

        familyBtn = new ToggleButton("Family");
        familyBtn.setToggleGroup(roleGroup);
        styleRoleButton(familyBtn);

        roleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == patientBtn) {
                selectedRole = "Patient";
            } else if (newVal == familyBtn) {
                selectedRole = "Family";
            }
        });

        HBox roleBox = new HBox(10, patientBtn, familyBtn);
        roleBox.setAlignment(Pos.CENTER);

        Button signUpBtn = new Button("Sign Up");
        signUpBtn.setPrefWidth(250);
        signUpBtn.setStyle("-fx-background-color: #8755ff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 10;");

        signUpBtn.setOnAction(e -> {
            String emailText = email.getText().trim();
            String passwordText = password.getText().trim();
            String genderValue = genderBox.getValue();

            if (emailText.isEmpty() || passwordText.isEmpty()) {
                showAlert("Error", "Email and Password are required.");
                return;
            }

            if (genderValue == null || genderValue.isEmpty()) {
                showAlert("Error", "Please select a gender.");
                return;
            }

            boolean success = FirebaseAuthService.signUp(
                    emailText,
                    passwordText,
                    patientId.getText().trim(),
                    fullName.getText().trim(),
                    selectedRole,
                    ageField.getText().trim(),
                    genderValue
            );

            if (success) {
                FirebaseInitializer.initialize();
                Firestore db = FirestoreClient.getFirestore();

                Map<String, Object> userMap = new HashMap<>();
                userMap.put("fullName", fullName.getText().trim());
                userMap.put("email", emailText);
                userMap.put("role", selectedRole);
                userMap.put("patientId", patientId.getText().trim());
                userMap.put("age", ageField.getText().trim());
                userMap.put("gender", genderValue);

                db.collection("users").document(emailText).set(userMap);

                UserSession.getInstance().setUserDetails(
                        emailText,
                        patientId.getText().trim(),
                        selectedRole
                );

                showAlert("Success", "Registration successful. Redirecting to login...");

                primaryStage.close();
                try {
                    new Login().start(new Stage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                showAlert("Failure", "Registration failed. Email may already exist or password is too weak.");
            }
        });

        Label loginLabel = new Label("Already have an account?");
        Hyperlink loginLink = new Hyperlink("Login");
        loginLink.setOnAction(e -> {
            primaryStage.close();
            new Login().start(new Stage());
        });

        HBox loginBox = new HBox(5, loginLabel, loginLink);
        loginBox.setAlignment(Pos.CENTER);

        formPane.getChildren().addAll(
                title,
                fullName,
                email,
                password,
                patientId,
                ageField,
                genderBox,
                roleBox,
                signUpBtn,
                loginBox
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

        Scene scene = new Scene(root, 1550, 800); // Adjusted height to be shorter
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
