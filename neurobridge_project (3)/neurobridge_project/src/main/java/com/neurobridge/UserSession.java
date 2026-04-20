package com.neurobridge;


public class UserSession {
    private static UserSession instance;
    private String email;
    private String patientId;
    private String role;
    private String fullName;

    private UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUserDetails(String email, String patientId, String role) {
        this.email = email;
        this.patientId = patientId;
        this.role = role;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getRole() {
        return role;
    }

    public String getFullName(){
        return fullName;
    }
    
    public void clearSession() {
        instance = null;
    }
}