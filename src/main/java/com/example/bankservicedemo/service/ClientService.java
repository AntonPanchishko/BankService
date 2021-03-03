package com.example.bankservicedemo.service;

import com.example.bankservicedemo.model.Currency;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Value(value = "$(service.url)")
    private String url;
    private final HttpClient httpClient;

    @Autowired
    public ClientService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public double getRate(LocalDate date, Currency currencyFrom, Currency currencyTo) {
        String path = url + currencyFrom.toString()
                + "&to=" + currencyTo.toString() + "&date=" + date;
        return httpClient.sendHttpRequest(path);
    }
}
