package com.neurobridge;


import com.google.gson.*;
import okhttp3.*;

import java.io.IOException;

public class GeminiClient {

    private static final String API_KEY = "AIzaSyDj6CyWLpe_DpSEy8Tyjz6fRIu8zbNXzp4";  // 🔐 Paste Gemini key from AI Studio
    private static final String API_URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + API_KEY;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public String getGeminiReply(String userMessage) throws IOException {
        JsonObject textPart = new JsonObject();
        textPart.addProperty("text", userMessage);

        JsonArray parts = new JsonArray();
        parts.add(textPart);

        JsonObject content = new JsonObject();
        content.add("parts", parts);

        JsonArray contentsArray = new JsonArray();
        contentsArray.add(content);

        JsonObject requestBody = new JsonObject();
        requestBody.add("contents", contentsArray);

        RequestBody body = RequestBody.create(
                gson.toJson(requestBody),
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String responseStr = response.body().string();

            if (!response.isSuccessful()) {
                return "❌ Gemini API Error: " + responseStr;
            }

            JsonObject responseJson = JsonParser.parseString(responseStr).getAsJsonObject();
            JsonArray candidates = responseJson.getAsJsonArray("candidates");

            if (candidates == null || candidates.size() == 0) {
                return "❌ No reply from Gemini.";
            }

            JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
            JsonArray replyParts = firstCandidate.getAsJsonObject("content").getAsJsonArray("parts");

            return replyParts.get(0).getAsJsonObject().get("text").getAsString().trim();
        }
    }
}
