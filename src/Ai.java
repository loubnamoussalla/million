import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class Ai {
    public Ai() {
    }

    private static final String API_KEY = "";

    public String aiHelp(String question) {
        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;
        String finalAnswer = " ";
        // Creating the correct JSON request body
        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("contents", new org.json.JSONArray().put(
                new JSONObject().put("parts", new org.json.JSONArray().put(
                        new JSONObject().put("text", question)
                ))
        ));

        // Build request
        RequestBody body = RequestBody.create(jsonRequest.toString(), MediaType.get("application/json"));
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        // Send request using OkHttp
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();

                // Parse JSON response to extract text
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray candidates = jsonResponse.getJSONArray("candidates");
                JSONObject firstCandidate = candidates.getJSONObject(0);
                JSONObject content = firstCandidate.getJSONObject("content");
                JSONArray parts = content.getJSONArray("parts");
                String text = parts.getJSONObject(0).getString("text");
                finalAnswer = text;

            } else {
                System.err.println("Error: " + response.code() + " - " + response.message());
                System.err.println("Response body: " + (response.body() != null ? response.body().string() : "null"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return finalAnswer;
    }

}

