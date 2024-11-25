package com.example.springtoysmobile.Service;

import android.os.AsyncTask;
import android.util.Log;

import com.example.springtoysmobile.util.ConnectionFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FindAllRequest extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... voids) {
        Log.i("Service","CHEGOOOOOOOOOOOOOO");
        StringBuilder apiResponse = new StringBuilder();
        try {
            URL findAll = new URL("http://" + ConnectionFactory.serverIP + ":8080/api/brinquedos");
            HttpURLConnection connection = (HttpURLConnection) findAll.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);
            connection.connect();

            Scanner scanner = new Scanner(findAll.openStream());
            while (scanner.hasNext()) {
                apiResponse.append(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiResponse.toString();
    }
}
