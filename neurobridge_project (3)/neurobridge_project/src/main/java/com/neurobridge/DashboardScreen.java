/*package com.neurobridge;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox sidebar = new VBox(40);
        sidebar.setPadding(new Insets(40, 20, 40, 20));
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");

        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);
        ImageView logoView = new ImageView(new Image("file:assets/logo.jpg", 36, 36, true, true));

        Text appName = new Text("NeuroBridge");
        appName.setFont(Font.font("Poppins", FontWeight.EXTRA_BOLD, 26));
        LinearGradient textGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ffd6f5")),
                new Stop(1, Color.web("#ffffff"))
        );
        appName.setFill(textGradient);
        appName.setStyle("-fx-effect: dropshadow(gaussian, #00000022, 1, 0, 0, 1);");

        logoBox.setSpacing(10);
        logoBox.getChildren().addAll(logoView, appName);

        VBox navLinks = new VBox(30);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(40, 0, 0, 0));
        navLinks.getChildren().addAll(
                createNavItem("🧠 Memory Games", Color.web("#FFD700"), event -> {
                    new MemoryGames().start(primaryStage);
                }),
                createNavItem("📅 Daily Routine", Color.web("#90EE90"), event -> {
                    new DailyRountinepatient().start(primaryStage);
                }),
                createNavItem("📈 My Progress", Color.web("#87CEFA"), event -> {
                    new ProgressPatient().start(primaryStage); // Reuse same stage
                }),
                createNavItem("💬 Chat Room", Color.web("#FFB6C1"), event -> {
                new ChatRoomModule().start(primaryStage); // navigate to ChatRoom
    })

        );

        sidebar.getChildren().addAll(logoBox, navLinks);

        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Riyaa ✨");
        greetTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 22));
        greetTitle.setFill(Color.web("#2A2E5B"));

        Text greetSub = new Text("Ready to start your day with some memory boosters?");
        greetSub.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        greetSub.setFill(Color.GRAY);
        greetTexts.getChildren().addAll(greetTitle, greetSub);

        ImageView illustration = new ImageView(new Image("file:assets/cute.png", 80, 80, true, true));
        greetingBox.getChildren().addAll(greetTexts, illustration);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), greetingBox);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 40, 0));
        moduleList.getChildren().addAll(
                createModuleTile("🧠 Memory Games", event -> {
                new MemoryGames().start(primaryStage); // ✅ Open MemoryGames when module tile is clicked
                }),

                createModuleTile("📅 Daily Routine", event -> {
                    new DailyRountinepatient().start(primaryStage); // Reuse same stage
                }),
                createModuleTile("📈 My Progress", event -> {
                    new ProgressPatient().start(primaryStage); // Reuse same stage
                }),
                createModuleTile("💬 Chat Room", event -> {
                new ChatRoomModule().start(primaryStage); // navigate to ChatRoom
                }),
                createModuleTile("Meditation", event -> {
                try {
                    new MedHome().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })

        );

        VBox rightContent = new VBox(30, greetingBox, moduleList);
        rightContent.setPadding(new Insets(40));
        rightContent.setBackground(Background.EMPTY);

        ScrollPane scrollPane = new ScrollPane(rightContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        HBox root = new HBox(sidebar, scrollPane);

        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        };
        LinearGradient backgroundGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("NeuroBridge Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String label, Color iconColor, EventHandler<MouseEvent> onClick) {
        String[] parts = label.split(" ", 2);
        String icon = parts[0];
        String textLabel = parts[1];

        Text iconText = new Text(icon);
        iconText.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 30));
        iconText.setFill(iconColor);

        Text labelText = new Text(textLabel);
        labelText.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
        labelText.setFill(Color.WHITE);

        VBox box = new VBox(6, iconText, labelText);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(12));
        box.setStyle("-fx-background-radius: 10; -fx-cursor: hand;");

        box.setOnMouseEntered(e -> {
            iconText.setFill(iconColor.brighter());
            labelText.setFill(Color.web("#ffd6f5"));
            box.setScaleX(1.08);
            box.setScaleY(1.08);
        });
        box.setOnMouseExited(e -> {
            iconText.setFill(iconColor);
            labelText.setFill(Color.WHITE);
            box.setScaleX(1);
            box.setScaleY(1);
        });

        if (onClick != null) {
            box.setOnMouseClicked(onClick);
        }

        return box;
    }

    private VBox createModuleTile(String title, EventHandler<MouseEvent> onClick) {
        VBox tile = new VBox();
        tile.setPadding(new Insets(25));
        tile.setSpacing(10);
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, #c23297e1, 4, 0, 0, 2); -fx-cursor: hand;");

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
        titleText.setFill(Color.web("#552a5bff"));

        tile.getChildren().addAll(titleText);

        ScaleTransition zoomIn = new ScaleTransition(Duration.millis(200), tile);
        zoomIn.setToX(1.03);
        zoomIn.setToY(1.03);

        ScaleTransition zoomOut = new ScaleTransition(Duration.millis(200), tile);
        zoomOut.setToX(1);
        zoomOut.setToY(1);

        tile.setOnMouseEntered(e -> zoomIn.playFromStart());
        tile.setOnMouseExited(e -> zoomOut.playFromStart());

        if (onClick != null) {
            tile.setOnMouseClicked(onClick);
        }

        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/

package com.neurobridge;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class DashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Sidebar content
        VBox sidebarContent = new VBox(40);
        sidebarContent.setPadding(new Insets(40, 20, 40, 20));
        sidebarContent.setPrefWidth(240);
        sidebarContent.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");

        // Logo
        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);
        ImageView logoView = new ImageView(new Image("file:assets/logo.jpg", 36, 36, true, true));

        Text appName = new Text("NeuroBridge");
        appName.setFont(Font.font("Poppins", FontWeight.EXTRA_BOLD, 26));
        LinearGradient textGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ffd6f5")),
                new Stop(1, Color.web("#ffffff"))
        );
        appName.setFill(textGradient);
        appName.setStyle("-fx-effect: dropshadow(gaussian, #00000022, 1, 0, 0, 1);");

        logoBox.setSpacing(10);
        logoBox.getChildren().addAll(logoView, appName);

        VBox navLinks = new VBox(30);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(40, 0, 0, 0));
        navLinks.getChildren().addAll(
                createNavItem("🧠 Memory Games", Color.web("#FFD700"), e -> new MemoryGames().start(primaryStage)),
                createNavItem("📅 Daily Routine", Color.web("#90EE90"), e -> new DailyRountinepatient().start(primaryStage)),
                createNavItem("🧘 Meditation", Color.web("#87CEFA"), e -> new MedHome().start(primaryStage)),
                createNavItem("💬 Community Chat", Color.web("#FFB6C1"), e -> new MemoryCircleModule().start(primaryStage)),
                createNavItem("👥 About us", Color.web("#FFB6C1"), e -> new Aboutus().start(primaryStage))
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox logoutBox = createNavItem("🚪 Logout", Color.WHITE, e -> new Login().start(primaryStage));
        logoutBox.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 8;");

        sidebarContent.getChildren().addAll(logoBox, navLinks, spacer, logoutBox);

        ScrollPane sidebarScroll = new ScrollPane(sidebarContent);
        sidebarScroll.setFitToWidth(true);
        sidebarScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sidebarScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        sidebarScroll.setPrefWidth(260);
        sidebarScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        // Greeting section
        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Riyaa ✨");
        greetTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 22));
        greetTitle.setFill(Color.web("#2A2E5B"));

        Text greetSub = new Text("Ready to start your day with some memory boosters?");
        greetSub.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        greetSub.setFill(Color.GRAY);
        greetTexts.getChildren().addAll(greetTitle, greetSub);

        ImageView illustration = new ImageView(new Image("file:assets/cute.png", 80, 80, true, true));
        greetingBox.getChildren().addAll(greetTexts, illustration);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), greetingBox);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 40, 0));
        moduleList.getChildren().addAll(
                createModuleTile("🧠 Memory Games", e -> new MemoryGames().start(primaryStage)),
                createModuleTile("📅 Daily Routine", e -> new DailyRountinepatient().start(primaryStage)),
                createModuleTile("🧘 Meditation", e -> {
                    try {
                        new MedHome().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createModuleTile("💬 Community Chat", e -> new MemoryCircleModule().start(primaryStage)),
                createModuleTile("👥 About us", e -> new Aboutus().start(primaryStage))
        );

        VBox leftContent = new VBox(30, greetingBox, moduleList);
        leftContent.setPadding(new Insets(40));

        // Right image section
        StackPane imageBox = new StackPane();
        imageBox.setPrefWidth(600);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(0, 40, 0, 0));

        ImageView dashboardImage = new ImageView(new Image(getClass().getResourceAsStream("/Assets/Images/dashboardfamily.png")));
        dashboardImage.setPreserveRatio(true);
        dashboardImage.setFitWidth(500);
        dashboardImage.setSmooth(true);
        dashboardImage.setCache(true);
        imageBox.getChildren().add(dashboardImage);


        HBox contentBox = new HBox(40, leftContent, imageBox);

        ScrollPane contentScroll = new ScrollPane(contentBox);
        contentScroll.setFitToWidth(true);
        contentScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        contentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        contentScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        // Combine sidebar and main content
        HBox root = new HBox(sidebarScroll, contentScroll);

        Stop[] stops = new Stop[]{
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        };
        LinearGradient backgroundGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 1550, 800);
        primaryStage.setTitle("NeuroBridge Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String label, Color iconColor, EventHandler<MouseEvent> onClick) {
        String[] parts = label.split(" ", 2);
        String icon = parts[0];
        String textLabel = parts[1];

        Text iconText = new Text(icon);
        iconText.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 30));
        iconText.setFill(iconColor);

        Text labelText = new Text(textLabel);
        labelText.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
        labelText.setFill(Color.WHITE);

        VBox box = new VBox(6, iconText, labelText);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(12));
        box.setStyle("-fx-background-radius: 10; -fx-cursor: hand;");

        box.setOnMouseEntered(e -> {
            iconText.setFill(iconColor.brighter());
            labelText.setFill(Color.web("#ffd6f5"));
            box.setScaleX(1.08);
            box.setScaleY(1.08);
        });
        box.setOnMouseExited(e -> {
            iconText.setFill(iconColor);
            labelText.setFill(Color.WHITE);
            box.setScaleX(1);
            box.setScaleY(1);
        });

        if (onClick != null) box.setOnMouseClicked(onClick);
        return box;
    }

    private VBox createModuleTile(String title, EventHandler<MouseEvent> onClick) {
        VBox tile = new VBox();
        tile.setPadding(new Insets(35));
        tile.setSpacing(15);
        tile.setMinWidth(400);
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, #c23297e1, 4, 0, 0, 2); -fx-cursor: hand;");

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Poppins", FontWeight.BOLD, 20));
        titleText.setFill(Color.web("#552a5bff"));
        tile.getChildren().addAll(titleText);

        ScaleTransition zoomIn = new ScaleTransition(Duration.millis(200), tile);
        zoomIn.setToX(1.03);
        zoomIn.setToY(1.03);

        ScaleTransition zoomOut = new ScaleTransition(Duration.millis(200), tile);
        zoomOut.setToX(1);
        zoomOut.setToY(1);

        tile.setOnMouseEntered(e -> zoomIn.playFromStart());
        tile.setOnMouseExited(e -> zoomOut.playFromStart());
        tile.setOnMouseClicked(onClick);

        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}




