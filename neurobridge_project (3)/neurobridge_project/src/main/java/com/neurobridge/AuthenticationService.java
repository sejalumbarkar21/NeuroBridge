package com.neurobridge;

/*import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

public class AuthenticationService {

    public static String registerUser(String email, String password, String fullName) {
        try {
            CreateRequest request = new CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(fullName);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return "Success: " + userRecord.getUid();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String loginUser(String email, String password) {
        // Firebase Admin SDK does not provide login method
        // You need to use REST API or use another mechanism here (or only allow signup/admin logic)
        return "Login not supported via Admin SDK. Use REST API or Firebase Client SDK for login.";
    }
}*/



import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;

public class AuthenticationService {

    public static String registerUser(String email, String password, String fullName) {
        try {
            CreateRequest request = new CreateRequest()
                    .setEmail(email)
                    .setPassword(password)
                    .setDisplayName(fullName);

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);
            return "Success: " + userRecord.getUid();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    public static String loginUser(String email, String password) {
        // Firebase Admin SDK does not provide login method
        // You need to use REST API or use another mechanism here (or only allow signup/admin logic)
        return "Login not supported via Admin SDK. Use REST API or Firebase Client SDK for login.";
    }
}