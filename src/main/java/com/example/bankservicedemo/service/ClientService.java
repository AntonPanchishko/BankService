package com.example.bankservicedemo.service;

import com.example.bankservicedemo.model.Currency;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Value(value = "$(service.url)")
    private String url;

    public double getRate(LocalDate date, Currency currencyFrom, Currency currencyTo) {
        String path = url + currencyFrom.toString()
                + "&to=" + currencyTo.toString() + "&date=" + date;
        try {
            URL url = new URL(path);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonElement root = JsonParser
                    .parseReader(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonObject = root.getAsJsonObject();
            return jsonObject.get("result").getAsDouble();
        } catch (IOException e) {
            throw new DataProcessingException("Can't get connection to the external API", e);
        }
    }
}
