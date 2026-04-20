package com.neurobridge;

import javafx.stage.Stage;

public class LoginHandler {
    public void onLoginSuccess(String retrievedPatientId, Stage primaryStage) {
        SessionManager.setPatientId(retrievedPatientId);
        try {
            new DashboardScreen().start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
