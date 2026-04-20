package com.neurobridge;



import javafx.application.Application;
import javafx.stage.Stage;

public class HospitalAppLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        new HospitalListView().start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
