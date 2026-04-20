/*package com.neurobridge;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox sidebar = new VBox(40);
        sidebar.setPadding(new Insets(40, 10, 40, 10));
        sidebar.setPrefWidth(240);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");

        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);
        ImageView logoView = new ImageView(new Image("file:assets/logo.jpg", 36, 36, true, true));

        Text appName = new Text("NeuroBridge");
        appName.setFont(Font.font("Poppins", FontWeight.EXTRA_BOLD, 22));
        LinearGradient textGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ffd6f5")),
                new Stop(1, Color.web("#ffffff"))
        );
        appName.setFill(textGradient);
        appName.setStyle("-fx-effect: dropshadow(gaussian, #00000022, 1, 0, 0, 1);");

        logoBox.setSpacing(10);
        logoBox.getChildren().addAll(logoView, appName);

        VBox navLinks = new VBox(25);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(60, 0, 0, 0));
        navLinks.getChildren().addAll(
                createNavItem("🗂️", "Manage Info", Color.web("#FFD700"), e -> {
                    try {
                        new ManagePatientInfoModule().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createNavItem("📊", "Score Analytics", Color.web("#90EE90"), null),
                createNavItem("⬆️", "Upload Memory", Color.web("#87CEFA"), e -> {
                    try {
                        new GameContentUploader().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createNavItem("🗓️", "Set Routine", Color.web("#FFB6C1"), null)
        );

        sidebar.getChildren().addAll(logoBox, navLinks);

        // === Greeting Banner ===
        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Payal ✨");
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
                createModuleTile("🗂️ Manage Info", e -> {
                    try {
                        new ManagePatientInfoModule().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createModuleTile("📊 Score Analytics", null),
                createModuleTile("⬆️ Upload Memory", e -> {
                    try {
                        new GameContentUploader().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createModuleTile("🗓️ Set Routine", null)
        );

        VBox rightContent = new VBox(30, greetingBox, moduleList);
        rightContent.setPadding(new Insets(40));

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

        Scene scene = new Scene(root, 1500, 800);
        primaryStage.setTitle("NeuroBridge Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String emoji, String label, Color emojiColor, EventHandler<MouseEvent> onClick) {
        Text icon = new Text(emoji);
        icon.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 36));
        icon.setFill(emojiColor);

        Text labelText = new Text(label);
        labelText.setFont(Font.font("Poppins", FontWeight.SEMI_BOLD, 16));
        labelText.setFill(Color.WHITE);
        labelText.setTextAlignment(TextAlignment.CENTER);
        labelText.setWrappingWidth(110);

        VBox box = new VBox(6, icon, labelText);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(130);
        box.setMaxWidth(130);
        box.setPadding(new Insets(14));
        box.setStyle("-fx-background-radius: 14; -fx-cursor: hand;");

        box.setOnMouseEntered(e -> {
            icon.setFill(emojiColor.brighter());
            labelText.setFill(Color.web("#ffffff"));
            box.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 14;");
        });

        box.setOnMouseExited(e -> {
            icon.setFill(emojiColor);
            labelText.setFill(Color.WHITE);
            box.setStyle("-fx-background-radius: 14;");
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
}*/

// ... (Keep the same package and import statements)
/*package com.neurobridge;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox sidebar = new VBox(40);
        sidebar.setPadding(new Insets(40, 10, 40, 10));
        sidebar.setPrefWidth(240);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");

        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);
        ImageView logoView = new ImageView(new Image("file:assets/logo.jpg", 36, 36, true, true));

        Text appName = new Text("NeuroBridge");
        appName.setFont(Font.font("Poppins", FontWeight.EXTRA_BOLD, 22));
        LinearGradient textGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ffd6f5")),
                new Stop(1, Color.web("#ffffff"))
        );
        appName.setFill(textGradient);
        appName.setStyle("-fx-effect: dropshadow(gaussian, #00000022, 1, 0, 0, 1);");

        logoBox.setSpacing(10);
        logoBox.getChildren().addAll(logoView, appName);

        VBox navLinks = new VBox(25);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(60, 0, 0, 0));
        navLinks.getChildren().addAll(
                createNavItem("🗂️", "Manage Info", Color.web("#FFD700"), e -> {
                    try {
                        new ManagePatientInfoModule().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createNavItem("📊", "Score Analytics", Color.web("#90EE90"), null),
                createNavItem("⬆️", "Upload Memory", Color.web("#87CEFA"), e -> {
                    try {
                        new GameContentUploader().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createNavItem("🗓️", "Set Routine", Color.web("#FFB6C1"), null)
        );

        sidebar.getChildren().addAll(logoBox, navLinks);

        // === Greeting Banner ===
        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Payal ✨");
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

        // === Properly aligned module list ===
        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 40, 0));
        moduleList.setAlignment(Pos.TOP_CENTER);
        moduleList.getChildren().addAll(
                createModuleTile("🗂️ Manage Info", e -> {
                    try {
                        new ManagePatientInfoModule().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createModuleTile("📊 Score Analytics", null),
                createModuleTile("⬆️ Upload Memory", e -> {
                    try {
                        new GameContentUploader().start(new Stage());
                        primaryStage.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }),
                createModuleTile("🗓️ Set Routine", null)
        );

        VBox rightContent = new VBox(30, greetingBox, moduleList);
        rightContent.setPadding(new Insets(40));
        rightContent.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(rightContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        HBox root = new HBox(sidebar, scrollPane);

        Stop[] stops = new Stop[] {
                new Stop(0, Color.web("#f38fd0")),
                new Stop(1, Color.web("#a18fff"))
        };
        LinearGradient backgroundGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 1500, 800);
        primaryStage.setTitle("NeuroBridge Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String emoji, String label, Color emojiColor, EventHandler<MouseEvent> onClick) {
        Text icon = new Text(emoji);
        icon.setFont(Font.font("Segoe UI Emoji", FontWeight.BOLD, 36));
        icon.setFill(emojiColor);

        Text labelText = new Text(label);
        labelText.setFont(Font.font("Poppins", FontWeight.SEMI_BOLD, 16));
        labelText.setFill(Color.WHITE);
        labelText.setTextAlignment(TextAlignment.CENTER);
        labelText.setWrappingWidth(110);

        VBox box = new VBox(6, icon, labelText);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(130);
        box.setMaxWidth(130);
        box.setPadding(new Insets(14));
        box.setStyle("-fx-background-radius: 14; -fx-cursor: hand;");

        box.setOnMouseEntered(e -> {
            icon.setFill(emojiColor.brighter());
            labelText.setFill(Color.web("#ffffff"));
            box.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 14;");
        });

        box.setOnMouseExited(e -> {
            icon.setFill(emojiColor);
            labelText.setFill(Color.WHITE);
            box.setStyle("-fx-background-radius: 14;");
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
        tile.setAlignment(Pos.CENTER); // <-- Align content inside tile to center
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(gaussian, #c23297e1, 4, 0, 0, 2); -fx-cursor: hand;");

        Text titleText = new Text(title);
        titleText.setFont(Font.font("Poppins", FontWeight.BOLD, 16));
        titleText.setFill(Color.web("#552a5bff"));
        titleText.setTextAlignment(TextAlignment.CENTER);

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
}*/

/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30));
        sidebar.setStyle("-fx-background-color: #252525;");
        sidebar.setPrefWidth(200);

        Label title = new Label("NeuroBridge");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 22));

        VBox navLinks = new VBox(25);
        navLinks.setPadding(new Insets(40, 0, 0, 0));

        navLinks.getChildren().addAll(
            createNavItem("🗂️", "Manage Info", Color.web("#FFD700"), e -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("📊", "Score Analytics", Color.web("#90EE90"), e -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("⬆️", "Upload Memory", Color.web("#87CEFA"), e -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🗓️", "Set Routine", Color.web("#FFB6C1"), e -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        sidebar.getChildren().addAll(title, navLinks);

        // Main content
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom, #1c1c1c, #121212);");
        mainContent.setPrefWidth(800);

        Label greeting = new Label("Welcome, Family Member!");
        greeting.setTextFill(Color.WHITE);
        greeting.setFont(Font.font("Verdana", FontWeight.BOLD, 28));

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(20, 0, 0, 0));

        moduleList.getChildren().addAll(
            createModuleTile("🗂️ Manage Info", e -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("📊 Score Analytics", e -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("⬆️ Upload Memory", e -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🗓️ Set Routine", e -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        mainContent.getChildren().addAll(greeting, moduleList);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        root.setLeft(sidebar);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setTitle("Family Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String icon, String label, Color color, javafx.event.EventHandler<MouseEvent> handler) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #333333; -fx-background-radius: 10;");
        box.setPrefSize(120, 80);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(28));
        iconLabel.setTextFill(color);

        Label textLabel = new Label(label);
        textLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        textLabel.setTextFill(Color.WHITE);

        box.getChildren().addAll(iconLabel, textLabel);
        box.setOnMouseClicked(handler);

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #444444; -fx-background-radius: 10;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: #333333; -fx-background-radius: 10;"));

        return box;
    }

    private VBox createModuleTile(String text, javafx.event.EventHandler<MouseEvent> handler) {
        VBox tile = new VBox();
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.setPadding(new Insets(15));
        tile.setStyle("-fx-background-color: #2d2d2d; -fx-background-radius: 12;");
        tile.setPrefHeight(60);

        Label label = new Label(text);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        label.setTextFill(Color.WHITE);

        tile.getChildren().add(label);
        tile.setOnMouseClicked(handler);

        tile.setOnMouseEntered(e -> tile.setStyle("-fx-background-color: #3e3e3e; -fx-background-radius: 12;"));
        tile.setOnMouseExited(e -> tile.setStyle("-fx-background-color: #2d2d2d; -fx-background-radius: 12;"));

        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/

/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30));
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #d471ff, #a46bf5);");
        sidebar.setPrefWidth(230);

        Label title = new Label("NeuroBridge");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        VBox navLinks = new VBox(25);
        navLinks.setPadding(new Insets(40, 0, 0, 0));

        navLinks.getChildren().addAll(
            createNavItem("🗂️", "Manage Info", Color.web("#FFD700"), e -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("📊", "Score Analytics", Color.web("#90EE90"), e -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("⬆️", "Upload Memory", Color.web("#87CEFA"), e -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🗓️", "Set Routine", Color.web("#FFB6C1"), e -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        sidebar.getChildren().addAll(title, navLinks);

        // Main content with gradient background
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setStyle("-fx-background-color: linear-gradient(to right, #e4b3ff, #bca8f0);");
        mainContent.setPrefWidth(800);

        Label greeting = new Label("Hi, Family Member ✨");
        greeting.setTextFill(Color.web("#333"));
        greeting.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));

        Label subGreeting = new Label("Ready to support your loved one today?");
        subGreeting.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
        subGreeting.setTextFill(Color.web("#555"));

        VBox greetingBox = new VBox(greeting, subGreeting);
        greetingBox.setPadding(new Insets(0, 0, 20, 0));
        greetingBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-background-radius: 16;");
        greetingBox.setPadding(new Insets(20));
        greetingBox.setMaxWidth(400);

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 0, 0));

        moduleList.getChildren().addAll(
            createModuleTile("🗂️ Manage Info", e -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("📊 Score Analytics", e -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("⬆️ Upload Memory", e -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🗓️ Set Routine", e -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        mainContent.getChildren().addAll(greetingBox, moduleList);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        root.setLeft(sidebar);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1100, 650);
        primaryStage.setTitle("Family Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String icon, String label, Color color, javafx.event.EventHandler<MouseEvent> handler) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 10;");
        box.setPrefSize(140, 80);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(28));
        iconLabel.setTextFill(color);

        Label textLabel = new Label(label);
        textLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        textLabel.setTextFill(Color.WHITE);

        box.getChildren().addAll(iconLabel, textLabel);
        box.setOnMouseClicked(handler);

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 10;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 10;"));

        return box;
    }

    private VBox createModuleTile(String text, javafx.event.EventHandler<MouseEvent> handler) {
        VBox tile = new VBox();
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.setPadding(new Insets(15));
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);");
        tile.setPrefHeight(60);

        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        label.setTextFill(Color.web("#333"));

        tile.getChildren().add(label);
        tile.setOnMouseClicked(handler);

        tile.setOnMouseEntered(e -> tile.setStyle("-fx-background-color: #f2f2f2; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 4);"));
        tile.setOnMouseExited(e -> tile.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);"));

        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/

/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30));
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #d471ff, #a46bf5);");
        sidebar.setPrefWidth(230);

        Label title = new Label("NeuroBridge");
        title.setTextFill(Color.web("#FFFFFF"));
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        VBox navLinks = new VBox(25);
        navLinks.setPadding(new Insets(40, 0, 0, 0));

        navLinks.getChildren().addAll(
            createNavItem("🗂️", "Manage Info", Color.web("#FFD700"), e -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("📊", "Score Analytics", Color.web("#90EE90"), e -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("⬆️", "Upload Memory", Color.web("#87CEFA"), e -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🗓️", "Set Routine", Color.web("#FFB6C1"), e -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        sidebar.getChildren().addAll(title, navLinks);

        // Main content with gradient background
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setStyle("-fx-background-color: linear-gradient(to right, #e4b3ff, #bca8f0);");
        mainContent.setPrefWidth(800);

        Label greeting = new Label("Hi, Family Member ✨");
        greeting.setTextFill(Color.BLACK); // Black color set here
        greeting.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));

        Label subGreeting = new Label("Ready to support your loved one today?");
        subGreeting.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
        subGreeting.setTextFill(Color.BLACK);

        VBox greetingBox = new VBox(greeting, subGreeting);
        greetingBox.setPadding(new Insets(20));
        greetingBox.setMaxWidth(400);
        greetingBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-background-radius: 16;");

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 0, 0));

        moduleList.getChildren().addAll(
            createModuleTile("🗂️ Manage Info", e -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("📊 Score Analytics", e -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("⬆️ Upload Memory", e -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🗓️ Set Routine", e -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        mainContent.getChildren().addAll(greetingBox, moduleList);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        root.setLeft(sidebar);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Family Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String icon, String label, Color color, javafx.event.EventHandler<MouseEvent> handler) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #ffffff1a; -fx-background-radius: 10;");
        box.setPrefSize(140, 80);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(28));
        iconLabel.setTextFill(color);

        Label textLabel = new Label(label);
        textLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        textLabel.setTextFill(Color.WHITE);

        box.getChildren().addAll(iconLabel, textLabel);
        box.setOnMouseClicked(handler);

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 10;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: rgba(255,255,255,0.1); -fx-background-radius: 10;"));

        return box;
    }

    private VBox createModuleTile(String text, javafx.event.EventHandler<MouseEvent> handler) {
        VBox tile = new VBox();
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.setPadding(new Insets(15));
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);");
        tile.setPrefHeight(60);

        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        label.setTextFill(Color.web("#000000"));

        tile.getChildren().add(label);
        tile.setOnMouseClicked(handler);

        tile.setOnMouseEntered(e -> tile.setStyle("-fx-background-color: #f2f2f2; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 4);"));
        tile.setOnMouseExited(e -> tile.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);"));

        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/


/*package com.neurobridge;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Sidebar
        VBox sidebar = new VBox(20);
        sidebar.setPadding(new Insets(30));
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #d471ff, #a46bf5);");
        sidebar.setPrefWidth(230);

        Label title = new Label("NeuroBridge");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Segoe UI", FontWeight.BOLD, 24));

        VBox navLinks = new VBox(25);
        navLinks.setPadding(new Insets(40, 0, 0, 0));

        navLinks.getChildren().addAll(
            createNavItem("🗂️", "Manage Info", Color.web("#FFD700"), event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("📊", "Score Analytics", Color.web("#90EE90"), event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("⬆️", "Upload Memory", Color.web("#87CEFA"), event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🗓️", "Set Routine", Color.web("#FFB6C1"), event -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        sidebar.getChildren().addAll(title, navLinks);

        // Main content
        VBox mainContent = new VBox(30);
        mainContent.setPadding(new Insets(40));
        mainContent.setStyle("-fx-background-color: linear-gradient(to right, #e4b3ff, #bca8f0);");
        mainContent.setPrefWidth(800);

        Label greeting = new Label("Hi, Family Member ✨");
        greeting.setTextFill(Color.BLACK);
        greeting.setFont(Font.font("Segoe UI", FontWeight.BOLD, 26));

        Label subGreeting = new Label("Ready to support your loved one today?");
        subGreeting.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 16));
        subGreeting.setTextFill(Color.BLACK);

        VBox greetingBox = new VBox(greeting, subGreeting);
        greetingBox.setPadding(new Insets(20));
        greetingBox.setMaxWidth(400);
        greetingBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.6); -fx-background-radius: 16;");

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 0, 0));

        moduleList.getChildren().addAll(
            createModuleTile("🗂️ Manage Info", event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("📊 Score Analytics", event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("⬆️ Upload Memory", event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🗓️ Set Routine", event -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        mainContent.getChildren().addAll(greetingBox, moduleList);

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");

        root.setLeft(sidebar);
        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setTitle("Family Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createNavItem(String icon, String label, Color color, javafx.event.EventHandler<MouseEvent> handler) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10));
        box.setStyle("-fx-background-color: #ffffff1a; -fx-background-radius: 10;");
        box.setPrefSize(140, 80);

        Label iconLabel = new Label(icon);
        iconLabel.setFont(Font.font(28));
        iconLabel.setTextFill(color);

        Label textLabel = new Label(label);
        textLabel.setFont(Font.font("Segoe UI", FontWeight.NORMAL, 14));
        textLabel.setTextFill(Color.WHITE); // Sidebar text white

        box.getChildren().addAll(iconLabel, textLabel);
        box.setOnMouseClicked(handler);

        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: rgba(255,255,255,0.2); -fx-background-radius: 10;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: #ffffff1a; -fx-background-radius: 10;"));

        return box;
    }

    private VBox createModuleTile(String text, javafx.event.EventHandler<MouseEvent> handler) {
        VBox tile = new VBox();
        tile.setAlignment(Pos.CENTER_LEFT);
        tile.setPadding(new Insets(15));
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);");
        tile.setPrefHeight(60);

        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        label.setTextFill(Color.BLACK); // All module tile text in black

        tile.getChildren().add(label);
        tile.setOnMouseClicked(handler);

        tile.setOnMouseEntered(e -> tile.setStyle("-fx-background-color: #f2f2f2; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 10, 0, 0, 4);"));
        tile.setOnMouseExited(e -> tile.setStyle("-fx-background-color: white; -fx-background-radius: 12; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 4);"));

        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}*/

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

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox sidebar = new VBox(40);
        sidebar.setPadding(new Insets(40, 20, 40, 20));
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");

        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);
        //ImageView logoView = new ImageView(new Image("/Assets/Images/1.4.png", 36, 36, true, true));

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
        logoBox.getChildren().addAll( appName);

        VBox navLinks = new VBox(30);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(40, 0, 0, 0));
        navLinks.getChildren().addAll(
                createNavItem("👤 Manage Info", Color.web("#FFD700"), event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
                createNavItem("📊 Score Analytics", Color.web("#90EE90"), event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
                createNavItem("🧠 Upload Memory", Color.web("#87CEFA"), event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
                createNavItem("🕒 Set Routine", Color.web("#FFB6C1"), event -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })

        );

        sidebar.getChildren().addAll(logoBox, navLinks);

        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Family Member ✨");
        greetTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 22));
        greetTitle.setFill(Color.web("#2A2E5B"));

        Text greetSub = new Text("Ready to suppoet your loved one today?");
        greetSub.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        greetSub.setFill(Color.GRAY);
        greetTexts.getChildren().addAll(greetTitle, greetSub);

       // ImageView illustration = new ImageView(new Image("/Assets/Images/loginn.png", 80, 80, true, true));
        greetingBox.getChildren().addAll(greetTexts);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), greetingBox);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 40, 0));
        moduleList.getChildren().addAll(
                createModuleTile("👤 Manage Info",event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
                createModuleTile("📊 Score Analytics", event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
                createModuleTile("🧠 Upload Memory", event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
                createModuleTile("🕒 Set Routine", event -> {
                try {
                    new RoutinePlanner().start(new Stage());
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

        Scene scene = new Scene(root, 1000, 800);
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
}*/


/*package com.neurobridge;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox sidebar = new VBox(40);
        sidebar.setPadding(new Insets(40, 20, 40, 20));
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");

        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);

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
        logoBox.getChildren().addAll(appName);

        VBox navLinks = new VBox(30);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(40, 0, 0, 0));
        navLinks.getChildren().addAll(
            createNavItem("👤 Manage Info", Color.web("#FFD700"), event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("📊 Score Analytics", Color.web("#90EE90"), event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🧠 Upload Memory", Color.web("#87CEFA"), event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🕒 Set Routine", Color.web("#FFB6C1"), event -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("📘 About Us", Color.web("#ADD8E6"), event -> {
                try {
                    new Aboutus().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        sidebar.getChildren().addAll(logoBox, navLinks);

        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Family Member ✨");
        greetTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 22));
        greetTitle.setFill(Color.web("#2A2E5B"));

        Text greetSub = new Text("Ready to suppoet your loved one today?");
        greetSub.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        greetSub.setFill(Color.GRAY);
        greetTexts.getChildren().addAll(greetTitle, greetSub);

        greetingBox.getChildren().addAll(greetTexts);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), greetingBox);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 40, 0));
        moduleList.getChildren().addAll(
            createModuleTile("👤 Manage Info", event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("📊 Score Analytics", event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🧠 Upload Memory", event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🕒 Set Routine", event -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("📘 About Us", event -> {
                try {
                    new Aboutus().start(new Stage());
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

        Stop[] stops = new Stop[] {
            new Stop(0, Color.web("#f38fd0")),
            new Stop(1, Color.web("#a18fff"))
        };
        LinearGradient backgroundGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(root, 1000, 800);
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
}*/


/*package com.neurobridge;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox sidebar = new VBox(40);
        sidebar.setPadding(new Insets(40, 20, 40, 20));
        sidebar.setPrefWidth(240);
        sidebar.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");

        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);

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
        logoBox.getChildren().addAll(appName);

        VBox navLinks = new VBox(30);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(40, 0, 0, 0));
        navLinks.getChildren().addAll(
            createNavItem("👤 Manage Info", Color.web("#FFD700"), event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("📊 Score Analytics", Color.web("#90EE90"), event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🧠 Upload Memory", Color.web("#87CEFA"), event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("🕒 Set Routine", Color.web("#FFB6C1"), event -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createNavItem("💊 Medical Treatment", Color.web("#FFB6C1"), event -> {
                try {
                    new HospitalListView().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
           createNavItem("💬 Community Chat", Color.web("#FFB6C1"), event -> {
                try {
                    new MemoryCircleModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),

            createNavItem("📘 About Us", Color.web("#ADD8E6"), event -> {
                try {
                    new Aboutus().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            })
        );

        //sidebar.getChildren().addAll(logoBox, navLinks);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

// Logout button at bottom
        VBox logoutBox = createNavItem("🚪 Logout", Color.web("#ffffff"), event -> {
        try {
          new Login().start(new Stage());
          primaryStage.close();
        } catch (Exception ex) {
           ex.printStackTrace();
       }
});

        logoutBox.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 8;");

        sidebar.getChildren().addAll(logoBox, navLinks, spacer, logoutBox);


        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Family Member ✨");
        greetTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 22));
        greetTitle.setFill(Color.web("#2A2E5B"));

        Text greetSub = new Text("Ready to support your loved one today?");
        greetSub.setFont(Font.font("Poppins", FontWeight.BOLD, 14));
        greetSub.setFill(Color.GRAY);
        greetTexts.getChildren().addAll(greetTitle, greetSub);

        greetingBox.getChildren().addAll(greetTexts);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), greetingBox);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 40, 0));
        moduleList.getChildren().addAll(
            createModuleTile("👤 Manage Info", event -> {
                try {
                    new ManagePatientInfoModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("📊 Score Analytics", event -> {
                try {
                    new Scorefamily().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🧠 Upload Memory", event -> {
                try {
                    new GameContentUploader().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("🕒 Set Routine", event -> {
                try {
                    new RoutinePlanner().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            
            createModuleTile("💊 Medical Treatment", event -> {
                try {
                    new HospitalListView().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),
            createModuleTile("💬 Community Chat", event -> {
                try {
                    new MemoryCircleModule().start(new Stage());
                    primaryStage.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }),

            createModuleTile("📘 About Us", event -> {
                try {
                    new Aboutus().start(new Stage());
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
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");

        HBox root = new HBox(sidebar, scrollPane);

        Stop[] stops = new Stop[] {
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
}*/

package com.neurobridge;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FamilyDashboardScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
       VBox sidebarContent = new VBox(40);
       sidebarContent.setPadding(new Insets(40, 20, 40, 20));
       sidebarContent.setPrefWidth(240);
       sidebarContent.setStyle("-fx-background-color: linear-gradient(to bottom, #a163f7, #cb6ce6);");


        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER_LEFT);

        Text appName = new Text("NeuroBridge");
        appName.setFont(Font.font("Poppins", FontWeight.EXTRA_BOLD, 30));
        LinearGradient textGradient = new LinearGradient(
                0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.web("#ffd6f5")),
                new Stop(1, Color.web("#ffffff"))
        );
        appName.setFill(textGradient);
        appName.setStyle("-fx-effect: dropshadow(gaussian, #00000022, 1, 0, 0, 1);");

        logoBox.getChildren().addAll(appName);

        VBox navLinks = new VBox(30);
        navLinks.setAlignment(Pos.TOP_CENTER);
        navLinks.setPadding(new Insets(40, 0, 0, 0));
        navLinks.getChildren().addAll(
            createNavItem("👤 Manage Info", Color.web("#FFD700"), e -> openModule(new ManagePatientInfoModule(), primaryStage)),
            
            createNavItem("🧠 Upload Memory", Color.web("#87CEFA"), e -> openModule(new GameContentUploader(), primaryStage)),
            createNavItem("🕒 Set Routine", Color.web("#FFB6C1"), e -> openModule(new RoutinePlanner(), primaryStage)),
            createNavItem("🏢 Book Appointment", Color.web("#FFB6C1"), e -> openModule(new HospitalListView(), primaryStage)),
            createNavItem("📊 Score Analytics", Color.web("#90EE90"), e -> openModule(new Scorefamily(), primaryStage)),
            createNavItem("💬 Chat Room", Color.web("#FFB6C1"), e -> openModule(new ChatRoomModule(), primaryStage))
            
        );

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        VBox logoutBox = createNavItem("🚪 Logout", Color.web("#ffffff"), e -> openModule(new Login(), primaryStage));
        logoutBox.setStyle("-fx-background-color: rgba(255,255,255,0.15); -fx-background-radius: 8;");
        sidebarContent.getChildren().addAll(logoBox, navLinks, spacer, logoutBox);

        HBox greetingBox = new HBox();
        greetingBox.setPadding(new Insets(30));
        greetingBox.setSpacing(30);
        greetingBox.setAlignment(Pos.CENTER_LEFT);
        greetingBox.setStyle("-fx-background-color: linear-gradient(to right, #f0c4d6ff, #e3b7f8ff); -fx-background-radius: 25;");

        VBox greetTexts = new VBox(8);
        Text greetTitle = new Text("Hi, Family Member ✨");
        greetTitle.setFont(Font.font("Poppins", FontWeight.BOLD, 24));
        greetTitle.setFill(Color.web("#2A2E5B"));

        Text greetSub = new Text("Ready to support your loved one today?");
        greetSub.setFont(Font.font("Poppins", FontWeight.BOLD, 18));
        greetSub.setFill(Color.GRAY);
        greetTexts.getChildren().addAll(greetTitle, greetSub);
        greetingBox.getChildren().addAll(greetTexts);

        FadeTransition fade = new FadeTransition(Duration.seconds(1), greetingBox);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();

        VBox moduleList = new VBox(20);
        moduleList.setPadding(new Insets(10, 0, 40, 0));
        moduleList.getChildren().addAll(
            createModuleTile("👤 Manage Info", e -> openModule(new ManagePatientInfoModule(), primaryStage)),
            
            createModuleTile("🧠 Upload Memory", e -> openModule(new GameContentUploader(), primaryStage)),
            createModuleTile("🕒 Set Routine", e -> openModule(new RoutinePlanner(), primaryStage)),
            createModuleTile("🏢 Book Appointment", e -> openModule(new HospitalListView(), primaryStage)),
            createModuleTile("📊 Score Analytics", e -> openModule(new Scorefamily(), primaryStage)),
            createModuleTile("💬 Chat Room", e -> openModule(new ChatRoomModule(), primaryStage))
            
        );

        VBox leftContent = new VBox(30, greetingBox, moduleList);
        leftContent.setPadding(new Insets(40));

        // 📸 IMAGE ADDED HERE ON RIGHT SIDE
        
        StackPane imageBox = new StackPane();
        imageBox.setPrefWidth(600); // Adjust as needed
        imageBox.setAlignment(Pos.CENTER); // Center the image vertically
        imageBox.setPadding(new Insets(0, 40, 0, 0)); // Right side padding

        ImageView illustration = new ImageView(new Image(getClass().getResourceAsStream("/Assets/Images/dashboardfamily.png")));
        illustration.setPreserveRatio(true);
        illustration.setFitWidth(500);
        illustration.setSmooth(true);
        illustration.setCache(true);

        imageBox.getChildren().add(illustration);

 


        HBox contentBox = new HBox(40, leftContent, imageBox);

        ScrollPane scrollPane = new ScrollPane(contentBox);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
        

        ScrollPane sidebarScroll = new ScrollPane(sidebarContent);
        sidebarScroll.setFitToWidth(true);
        sidebarScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sidebarScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-border-color: transparent;");
        sidebarScroll.setPrefWidth(260);

        HBox root = new HBox(sidebarScroll, scrollPane);

        Stop[] stops = new Stop[] {
            new Stop(0, Color.web("#f38fd0")),
            new Stop(1, Color.web("#a18fff"))
        };
        LinearGradient backgroundGradient = new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, stops);
        root.setBackground(new Background(new BackgroundFill(backgroundGradient, CornerRadii.EMPTY, Insets.EMPTY)));

        // 🌟 Add floating logout button (bottom-right)
        

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

        box.setOnMouseClicked(onClick);
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

    private void openModule(Application module, Stage currentStage) {
        try {
            module.start(new Stage());
            currentStage.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

