package com.neurobridge;



public class SessionManager {
    private static String patientId;

    public static void setPatientId(String id) {
        patientId = id;
    }

    public static String getPatientId() {
        return patientId;
    }
}
