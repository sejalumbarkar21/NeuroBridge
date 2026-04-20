//package com.neurobridge;


/*import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.InputStream;

public class FirebaseInitializer {

    public static void initialize() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("/serviceAccountKey.json");

                FirebaseOptions options = FirebaseOptions.builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setStorageBucket("your-project-id.appspot.com") // Optional, if using Firebase Storage
                        .build();

                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("🔥 Failed to initialize Firebase.");
        }
    }
}*/



/*import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

//import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class FirebaseInitializer {
    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;

        try {
            // ✅ Recommended: Load from classpath (inside resources folder)
            InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream(
                    "/Images/Assets/firebase_config.json"
            );

            if (serviceAccount == null) {
                System.err.println("❌ Firebase config file not found.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully.");
                initialized = true;
            }

        } catch (IOException e) {
            System.err.println("❌ Failed to initialize Firebase:");
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}
*/
/* 

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;
        try {
            // Load the file from resources
            InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("/serviceAccountKey.json");

            if (serviceAccount == null) {
                System.out.println("? Firebase config file not found.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("✅ Firebase initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}*/


/*import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static void init() {
        if (initialized) return;
        try {
            // Load the file from resources
            InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("E:\\Super-X\\n" + //
                                "eurobridge_project\\src\\main\\resources\\firebase_config.json");

            if (serviceAccount == null) {
                System.out.println("? Firebase config file not found.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("✅ Firebase initialized successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }
}*/

/* 
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static void initialize() {
        if (initialized) return;

        try {
            // Load the JSON file from resources folder
            InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("/firebase_config.json");

            if (serviceAccount == null) {
                System.out.println("❌ Firebase config file not found.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("✅ Firebase Initialized Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Failed to initialize Firebase.");
        }
    }

    public static Firestore getFirestore() {
        if (!initialized) {
            initialize();
        }
        return FirestoreClient.getFirestore();
    }
}*/


/*import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;
import java.util.List;

public class FirebaseInitializer {

    private static boolean initialized = false;

    public static void initialize() {
        try {
            // Check if Firebase is already initialized
            List<FirebaseApp> apps = FirebaseApp.getApps();
            if (!apps.isEmpty()) {
                initialized = true;
                System.out.println("⚠️ Firebase already initialized.");
                return;
            }

            // Load the config file
            InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("/firebase_config.json");

            if (serviceAccount == null) {
                System.err.println("❌ Firebase config file not found. Make sure it's in src/main/resources.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("✅ Firebase Initialized Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to initialize Firebase.");
        }
    }

    public static Firestore getFirestore() {
        if (!initialized) {
            initialize();
        }
        return FirestoreClient.getFirestore();
    }
}*/
package com.neurobridge; // Or use com.project.model if needed

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;

public class FirebaseInitializer {

    private static boolean initialized = false;

    // Main initialization method
    public static void initialize() {
        if (initialized) {
            System.out.println("⚠️ Firebase already initialized.");
            return;
        }

        try {
            // Try loading the service account JSON from different paths
            InputStream serviceAccount = FirebaseInitializer.class.getResourceAsStream("/firebase_config.json");

            if (serviceAccount == null) {
                serviceAccount = FirebaseInitializer.class.getResourceAsStream("/Images/Assets/firebase_config.json");
            }

            if (serviceAccount == null) {
                System.err.println("❌ Firebase config file not found. Expected at /firebase_config.json or /Images/Assets/firebase_config.json");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            List<FirebaseApp> apps = FirebaseApp.getApps();
            if (apps == null || apps.isEmpty()) {
                FirebaseApp.initializeApp(options);
                System.out.println("✅ Firebase initialized successfully.");
            } else {
                System.out.println("⚠️ Firebase already initialized.");
            }

            initialized = true;

        } catch (IOException e) {
            System.err.println("❌ Failed to initialize Firebase:");
            e.printStackTrace();
        }
    }

    // Alias method for initialize()
    public static void init() {
        initialize();
    }

    public static Firestore getFirestore() {
        if (!initialized) {
            initialize();
        }
        return FirestoreClient.getFirestore();
    }

    public static Firestore getDB() {
        return getFirestore();
    }
}






