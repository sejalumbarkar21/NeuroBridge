package com.neurobridge;

/*import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FirebaseAuthService {

    private static final String API_KEY = "AIzaSyCXHXgkbZZFYpH1s-U7_a3V0_4Z-8SL8nk"; // 🔁 Replace with your Firebase Web API key

    public static boolean SignUp(String email, String password) {
        try {
            String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;
            return sendAuthRequest(endpoint, email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean signIn(String email, String password) {
        try {
            String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
            return sendAuthRequest(endpoint, email, password);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean sendAuthRequest(String endpoint, String email, String password) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            // Success
            return true;
        } else {
            // Read and print error message
            JsonObject error = JsonParser.parseReader(new java.io.InputStreamReader(conn.getErrorStream())).getAsJsonObject();
            System.err.println("Firebase error: " + error);
            return false;
        }
    }
}*/

import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.neurobridge.UserSession; // ✅ Import UserSession

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FirebaseAuthService {

    private static final String API_KEY = "AIzaSyCXHXgkbZZFYpH1s-U7_a3V0_4Z-8SL8nk"; // 🔁 Replace with your Firebase Web API key

    public static boolean signUp(String email, String password, String patientId, String name, String role, String age, String gender) {
        try {
            String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY;
            boolean authSuccess = sendAuthRequest(endpoint, email, password);

            if (authSuccess) {
                Firestore db = FirestoreClient.getFirestore();

                Map<String, Object> userData = new HashMap<>();
                userData.put("email", email);
                userData.put("patientId", patientId);
                userData.put("name", name);
                userData.put("role", role);
                userData.put("age", age);
                userData.put("gender", gender);

                DocumentReference docRef = db.collection("users").document(email);
                docRef.set(userData).get(); // ⏳ Wait for write to complete

                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean signIn(String email, String password) {
        try {
            String endpoint = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY;
            boolean authSuccess = sendAuthRequest(endpoint, email, password);

            if (authSuccess) {
                // ✅ Fetch user details and store in UserSession
                Firestore db = FirestoreClient.getFirestore();
                DocumentSnapshot snapshot = db.collection("users").document(email).get().get();

                if (snapshot.exists()) {
                    String patientId = snapshot.getString("patientId");
                    String role = snapshot.getString("role");

                    UserSession.getInstance().setUserDetails(email, patientId, role);
                }

                return true;
            }

            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyPatientLogin(String email, String enteredPatientId) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentSnapshot snapshot = db.collection("users").document(email).get().get();

            if (snapshot.exists()) {
                String storedPatientId = snapshot.getString("patientId");
                return enteredPatientId.equals(storedPatientId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static boolean sendAuthRequest(String endpoint, String email, String password) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        String payload = String.format("{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}", email, password);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = conn.getResponseCode();
        if (responseCode == 200) {
            return true;
        } else {
            JsonObject error = JsonParser.parseReader(new InputStreamReader(conn.getErrorStream())).getAsJsonObject();
            System.err.println("Firebase error: " + error);
            return false;
        }
    }

    public static void saveAdditionalUserData(String email, String name, String age, String gender, String role) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            DocumentReference docRef = db.collection("users").document(email);

            docRef.update(
                    "fullName", name,
                    "age", age,
                    "gender", gender,
                    "role", role
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
