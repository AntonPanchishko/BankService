package com.example.bankservicedemo.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class HttpClient {
    public double sendHttpRequest(String path) {
        JsonObject jsonObject = null;
        try {
            URL url = new URL(path);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonElement root = JsonParser
                    .parseReader(new InputStreamReader((InputStream) request.getContent()));
            jsonObject = root.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject.get("result").getAsDouble();
    }
}
