package com.neurobridge;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {
    public static void initialize() {
        try {
            // Correct path to the Firebase service account file
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase_config.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://myfxproject-96640-default-rtdb.firebaseio.com/")  // ✅ Realtime DB
                    .setStorageBucket("myfxproject-96640.firebasestorage.app")                         // ✅ Firebase Storage
                    .build();

            // Only initialize once
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase Initialized Successfully!");
            }

        } catch (IOException e) {
            System.err.println("❌ Failed to initialize Firebase:");
            e.printStackTrace();
        }
    }
}
