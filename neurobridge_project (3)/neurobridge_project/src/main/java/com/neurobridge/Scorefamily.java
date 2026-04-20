/*package com.neurobridge;



import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Scorefamily extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main Anchor Pane Layout
        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(40));

        // 🌈 Gradient Background from your reference image
        Stop[] stops = new Stop[] {
            new Stop(0, Color.web("#ff00cc")),
            new Stop(1, Color.web("#333399"))
        };
        LinearGradient gradient = new LinearGradient(
            0, 0, 1, 1, true,
            CycleMethod.NO_CYCLE, stops
        );
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // Main Content Box
        VBox mainContent = new VBox(30);
        mainContent.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(mainContent, 100.0);
        AnchorPane.setLeftAnchor(mainContent, 0.0);
        AnchorPane.setRightAnchor(mainContent, 0.0);
        AnchorPane.setBottomAnchor(mainContent, 0.0);

        // Greeting Section
        Label greeting = new Label("Hello! Family Member");
        greeting.setFont(Font.font("Arial", 28));
        greeting.setStyle("-fx-font-weight: bold;");
        greeting.setTextFill(Color.WHITE);

        Label subText = new Label("Track your loved one's progress easily and clearly.");
        subText.setFont(Font.font("Arial", 16));
        subText.setTextFill(Color.WHITE);

        VBox greetingBox = new VBox(5, greeting, subText);
        greetingBox.setAlignment(Pos.CENTER);

        // Score Cards Layout
        VBox cardsBox = new VBox(25);
        cardsBox.setAlignment(Pos.CENTER);

        GridPane cardGrid = new GridPane();
        cardGrid.setHgap(30);
        cardGrid.setVgap(30);
        cardGrid.setAlignment(Pos.CENTER);

        // Add top 4 cards
        cardGrid.add(createCard("Score Point", "480"), 0, 0);
        cardGrid.add(createCard("Level Reaction", "72%"), 1, 0);
        cardGrid.add(createCard("Game Track", "12 Games"), 0, 1);
        cardGrid.add(createCard("App Usage Time", "1.5 Hrs"), 1, 1);

        // Progress Card in Center Below
        HBox progressRow = new HBox();
        progressRow.setAlignment(Pos.CENTER);
        VBox progressCard = createCard("Progress", "100%");
        progressRow.getChildren().add(progressCard);

        cardsBox.getChildren().addAll(cardGrid, progressRow);

        // Final Layout
        mainContent.getChildren().addAll(greetingBox, cardsBox);
        root.getChildren().addAll(mainContent);

        // Scene and Stage
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Family Dashboard - Patient Score Analysis");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper to create a card with hover effect
    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefSize(200, 100);

        String baseStyle = "-fx-background-color: rgba(255, 255, 255, 0.85);"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #cccccc;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 4);";

        String hoverStyle = "-fx-background-color: #f0faff;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #007acc;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 6);";

        card.setStyle(baseStyle);

        card.setOnMouseEntered(e -> card.setStyle(hoverStyle));
        card.setOnMouseExited(e -> card.setStyle(baseStyle));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 16));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setStyle("-fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", 20));
        valueLabel.setTextFill(Color.BLACK);
        valueLabel.setStyle("-fx-font-weight: bold;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
} */
/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Scorefamily extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout with gradient background
        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(40));

        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#ff00cc")),
                new Stop(1, Color.web("#333399"))
        };
        LinearGradient gradient = new LinearGradient(
                0, 0, 1, 1, true,
                CycleMethod.NO_CYCLE, stops
        );
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // VBox content holder
        VBox mainContent = new VBox(30);
        mainContent.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(mainContent, 100.0);
        AnchorPane.setLeftAnchor(mainContent, 0.0);
        AnchorPane.setRightAnchor(mainContent, 0.0);

        // Greeting section
        Label greeting = new Label("Hello! Family Member");
        greeting.setFont(Font.font("Arial", 28));
        greeting.setStyle("-fx-font-weight: bold;");
        greeting.setTextFill(Color.WHITE);

        Label subText = new Label("Track your loved one's progress easily and clearly.");
        subText.setFont(Font.font("Arial", 16));
        subText.setTextFill(Color.WHITE);

     

        VBox greetingBox = new VBox(5, greeting, subText);
        greetingBox.setAlignment(Pos.CENTER);

        // Glass-like container
        VBox squareContainer = new VBox();
        squareContainer.setPadding(new Insets(40));
        squareContainer.setSpacing(30);
        squareContainer.setAlignment(Pos.CENTER);
        squareContainer.setPrefSize(500, 400);
        squareContainer.setMaxSize(500, 400);

        // Glass effect using rgba and shadow
        squareContainer.setBackground(new Background(new BackgroundFill(
                Color.web("#ffffff30"), new CornerRadii(20), Insets.EMPTY
        )));
        squareContainer.setEffect(new DropShadow(20, Color.rgb(0, 0, 0, 0.2)));

        GridPane cardGrid = new GridPane();
        cardGrid.setHgap(30);
        cardGrid.setVgap(30);
        cardGrid.setAlignment(Pos.CENTER);

        cardGrid.add(createCard("Score Point", "480"), 0, 0);
        cardGrid.add(createCard("Level Reaction", "72%"), 1, 0);
        cardGrid.add(createCard("Game Track", "12 Games"), 0, 1);
        cardGrid.add(createCard("App Usage Time", "1.5 Hrs"), 1, 1);

        squareContainer.getChildren().add(cardGrid);

        mainContent.getChildren().addAll(greetingBox, squareContainer);
        root.getChildren().add(mainContent);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Family Dashboard - Progress Overview");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create a stat card
    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefSize(200, 100);

        String baseStyle = "-fx-background-color: white;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #eeeeee;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 6, 0, 0, 4);";

        String hoverStyle = "-fx-background-color: #e0f7ff;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #007acc;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 6);";

        card.setStyle(baseStyle);
        card.setOnMouseEntered(e -> card.setStyle(hoverStyle));
        card.setOnMouseExited(e -> card.setStyle(baseStyle));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 16));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setStyle("-fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", 20));
        valueLabel.setTextFill(Color.BLACK);
        valueLabel.setStyle("-fx-font-weight: bold;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/



/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Scorefamily extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(40));

        // Gradient Background
        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#ff00cc")),
                new Stop(1, Color.web("#333399"))
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", 14));
        backButton.setStyle("-fx-background-color: #ffffffaa; -fx-text-fill: #333; -fx-background-radius: 8;");
        backButton.setOnAction(e -> primaryStage.close()); // replace with actual navigation if needed

        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        root.getChildren().add(backButton);

        // Main VBox
        VBox mainContent = new VBox(30);
        mainContent.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(mainContent, 100.0);
        AnchorPane.setLeftAnchor(mainContent, 0.0);
        AnchorPane.setRightAnchor(mainContent, 0.0);

        // Greeting Section
        Label greeting = new Label("Hello! Family Member");
        greeting.setFont(Font.font("Arial", 28));
        greeting.setStyle("-fx-font-weight: bold;");
        greeting.setTextFill(Color.WHITE);

        Label subText = new Label("Track your loved one's progress easily and clearly.");
        subText.setFont(Font.font("Arial", 16));
        subText.setTextFill(Color.WHITE);

        VBox greetingBox = new VBox(5, greeting, subText);
        greetingBox.setAlignment(Pos.CENTER);

        // Glass-like Stats Container
        VBox squareContainer = new VBox();
        squareContainer.setPadding(new Insets(40));
        squareContainer.setSpacing(30);
        squareContainer.setAlignment(Pos.CENTER);
        squareContainer.setPrefSize(500, 400);
        squareContainer.setMaxSize(500, 400);
        squareContainer.setBackground(new Background(new BackgroundFill(
                Color.web("#ffffff30"), new CornerRadii(20), Insets.EMPTY
        )));
        squareContainer.setEffect(new DropShadow(20, Color.rgb(0, 0, 0, 0.2)));

        // Cards Grid
        GridPane cardGrid = new GridPane();
        cardGrid.setHgap(30);
        cardGrid.setVgap(30);
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.add(createCard("Score Point", "480"), 0, 0);
        cardGrid.add(createCard("Level Reaction", "72%"), 1, 0);
        cardGrid.add(createCard("Game Track", "12 Games"), 0, 1);
        cardGrid.add(createCard("App Usage Time", "1.5 Hrs"), 1, 1);

        squareContainer.getChildren().add(cardGrid);

        // Final Layout
        mainContent.getChildren().addAll(greetingBox, squareContainer);
        root.getChildren().add(mainContent);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Family Dashboard - Progress Overview");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to create a stat card
    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefSize(200, 100);

        String baseStyle = "-fx-background-color: white;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #eeeeee;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 6, 0, 0, 4);";

        String hoverStyle = "-fx-background-color: #e0f7ff;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #007acc;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 6);";

        card.setStyle(baseStyle);
        card.setOnMouseEntered(e -> card.setStyle(hoverStyle));
        card.setOnMouseExited(e -> card.setStyle(baseStyle));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 16));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setStyle("-fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", 20));
        valueLabel.setTextFill(Color.BLACK);
        valueLabel.setStyle("-fx-font-weight: bold;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/


/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Scorefamily extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPadding(new Insets(40));

        // Gradient Background
        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#ff00cc")),
                new Stop(1, Color.web("#333399"))
        };
        LinearGradient gradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", 14));
        backButton.setStyle("-fx-background-color: #ffffffaa; -fx-text-fill: #333; -fx-background-radius: 8;");
        backButton.setOnAction(e -> {
            try {
                new FamilyDashboardScreen().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        AnchorPane.setTopAnchor(backButton, 10.0);
        AnchorPane.setLeftAnchor(backButton, 10.0);
        root.getChildren().add(backButton);

        // Main VBox
        VBox mainContent = new VBox(30);
        mainContent.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(mainContent, 100.0);
        AnchorPane.setLeftAnchor(mainContent, 0.0);
        AnchorPane.setRightAnchor(mainContent, 0.0);

        // Greeting Section
        Label greeting = new Label("Hello! Family Member");
        greeting.setFont(Font.font("Arial", 28));
        greeting.setStyle("-fx-font-weight: bold;");
        greeting.setTextFill(Color.WHITE);

        Label subText = new Label("Track your loved one's progress easily and clearly.");
        subText.setFont(Font.font("Arial", 16));
        subText.setTextFill(Color.WHITE);

        VBox greetingBox = new VBox(5, greeting, subText);
        greetingBox.setAlignment(Pos.CENTER);

        // Glass-like Stats Container
        VBox squareContainer = new VBox();
        squareContainer.setPadding(new Insets(40));
        squareContainer.setSpacing(30);
        squareContainer.setAlignment(Pos.CENTER);
        squareContainer.setPrefSize(500, 400);
        squareContainer.setMaxSize(500, 400);
        squareContainer.setBackground(new Background(new BackgroundFill(
                Color.web("#ffffff30"), new CornerRadii(20), Insets.EMPTY
        )));
        squareContainer.setEffect(new DropShadow(20, Color.rgb(0, 0, 0, 0.2)));

        // Cards Grid
        GridPane cardGrid = new GridPane();
        cardGrid.setHgap(30);
        cardGrid.setVgap(30);
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.add(createCard("Score Point", "15"), 0, 0);
        cardGrid.add(createCard("Level Reaction", "72%"), 1, 0);
        cardGrid.add(createCard("Game Track", "4 Games"), 0, 1);
        cardGrid.add(createCard("App Usage Time", "1.5 Hrs"), 1, 1);

        squareContainer.getChildren().add(cardGrid);

        // Final Layout
        mainContent.getChildren().addAll(greetingBox, squareContainer);
        root.getChildren().add(mainContent);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Family Dashboard - Progress Overview");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCard(String title, String value) {
        VBox card = new VBox(10);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setPrefSize(200, 100);

        String baseStyle = "-fx-background-color: white;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #eeeeee;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 6, 0, 0, 4);";

        String hoverStyle = "-fx-background-color: #e0f7ff;"
                + "-fx-background-radius: 15;"
                + "-fx-border-color: #007acc;"
                + "-fx-border-radius: 15;"
                + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 6);";

        card.setStyle(baseStyle);
        card.setOnMouseEntered(e -> card.setStyle(hoverStyle));
        card.setOnMouseExited(e -> card.setStyle(baseStyle));

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 16));
        titleLabel.setTextFill(Color.BLACK);
        titleLabel.setStyle("-fx-font-weight: bold;");

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", 20));
        valueLabel.setTextFill(Color.BLACK);
        valueLabel.setStyle("-fx-font-weight: bold;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/

package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.*;
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
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class Scorefamily extends Application {
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // 🌈 Main background
        root.setStyle("-fx-background-color: linear-gradient(to right, #d084f7, #99c3fc);");

        VBox contentBox = new VBox(20);
        contentBox.setPadding(new Insets(30, 0, 30, 0));
        contentBox.setAlignment(Pos.TOP_CENTER);

        // Welcome section
        VBox welcomeBox = new VBox(10);
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.setPadding(new Insets(20));
        welcomeBox.setMaxWidth(600);
        welcomeBox.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10,0,0,2);");

        Label title = new Label("Hello! Family Member");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTextFill(Color.web("#222"));

        Label subtitle = new Label("Track your loved one's progress easily and clearly.");
        subtitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        subtitle.setTextFill(Color.GRAY);

        ImageView topImage = new ImageView(new Image("/Assets/Images/background1.png"));
        topImage.setFitHeight(150);
        topImage.setPreserveRatio(true);

        welcomeBox.getChildren().addAll(topImage, title, subtitle);

        // Cards Grid
        GridPane cardGrid = new GridPane();
        cardGrid.setAlignment(Pos.CENTER);
        cardGrid.setHgap(40);
        cardGrid.setVgap(30);

        cardGrid.add(createCard("Score Points", "15", "/Assets/Images/scorepoint1.png", "#ffd1dc"), 0, 0);
        cardGrid.add(createCard("Level Reaction", "72%", "/Assets/Images/levelreaction1.png", "#eaffc3"), 1, 0);
        cardGrid.add(createCard("Game Track", "4 Games", "/Assets/Images/gametrack1.png", "#cde5ff"), 0, 1);
        cardGrid.add(createCard("App Usage Time", "1.5 Hrs", "/Assets/Images/time1.png", "#f9ccff"), 1, 1);

        // 🔙 Back Button
        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        backButton.setStyle("-fx-background-color: #ffffffaa; -fx-background-radius: 10; -fx-text-fill: #444;");
        backButton.setPadding(new Insets(8, 16, 8, 16));
        backButton.setOnAction(e -> {
            try {
                new FamilyDashboardScreen().start(new Stage());
                primaryStage.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        contentBox.getChildren().addAll(backButton,welcomeBox, cardGrid);
        root.setCenter(contentBox);

        // 📊 Sidebar
        VBox rightPanel = new VBox(20);
        rightPanel.setPadding(new Insets(40));
        rightPanel.setStyle("-fx-background-color: linear-gradient(to bottom right, #c3a4f9, #d4e9ff);");
        rightPanel.setPrefWidth(300);
        rightPanel.setAlignment(Pos.TOP_CENTER);

        Label dateLabel = new Label("📅 Today's Date");
        dateLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        dateLabel.setTextFill(Color.web("#111"));

        DatePicker datePicker = new DatePicker();
        datePicker.setPrefWidth(180);
        datePicker.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-radius: 5;");

        Label statsLabel = new Label("📊 Quick Stats");
        statsLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        statsLabel.setTextFill(Color.web("#111"));

        VBox statsBox = new VBox(10);
        statsBox.getChildren().addAll(
                statCard(" Total Family Members ", "1", "#ffe5e5"),
                statCard("Average Score", "95%", "#e2f7e2"),
                statCard("Total Games Played", "4", "#eaffc3")
        );

        // 📎 Add image under cards
        ImageView bottomImage = new ImageView(new Image("/Assets/Images/bgnew1.png"));
        bottomImage.setFitWidth(300);
        bottomImage.setPreserveRatio(true);
        VBox.setMargin(bottomImage, new Insets(20, 0, 0, 0));

        rightPanel.getChildren().addAll(dateLabel, datePicker, statsLabel, statsBox, bottomImage);
        root.setRight(rightPanel);

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("Family Score Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createCard(String title, String value, String imagePath, String bgColor) {
        VBox box = new VBox(10);
        box.setPadding(new Insets(20));
        box.setAlignment(Pos.CENTER);
        box.setPrefSize(220, 180);
        box.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 20; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 5,0,0,2);");

        ImageView img = new ImageView(new Image(imagePath));
        img.setFitWidth(60);
        img.setFitHeight(60);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        titleLabel.setTextFill(Color.web("#111"));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        valueLabel.setTextFill(Color.web("#111"));

        box.getChildren().addAll(img, titleLabel, valueLabel);

        // 🌀 Hover effect
        ScaleTransition stEnter = new ScaleTransition(Duration.millis(200), box);
        stEnter.setToX(1.05);
        stEnter.setToY(1.05);

        ScaleTransition stExit = new ScaleTransition(Duration.millis(200), box);
        stExit.setToX(1.0);
        stExit.setToY(1.0);

        box.setOnMouseEntered(e -> stEnter.playFromStart());
        box.setOnMouseExited(e -> stExit.playFromStart());

        return box;
    }

    private VBox statCard(String title, String value, String bgColor) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(15));
        card.setAlignment(Pos.CENTER_LEFT);
        card.setStyle("-fx-background-color: " + bgColor + "; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 5, 0, 0, 2);");

        Label dot = new Label("●");
        dot.setTextFill(Color.BLACK);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        titleLabel.setTextFill(Color.web("#222"));

        Label valueLabel = new Label(value);
        valueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        valueLabel.setTextFill(Color.web("#111"));

        card.getChildren().addAll(dot, titleLabel, valueLabel);

        // 🌀 Hover animation
        ScaleTransition stEnter = new ScaleTransition(Duration.millis(200), card);
        stEnter.setToX(1.03);
        stEnter.setToY(1.03);

        ScaleTransition stExit = new ScaleTransition(Duration.millis(200), card);
        stExit.setToX(1.0);
        stExit.setToY(1.0);

        card.setOnMouseEntered(e -> stEnter.playFromStart());
        card.setOnMouseExited(e -> stExit.playFromStart());

        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

