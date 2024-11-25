package com.example.springtoysmobile.Service;

import android.os.AsyncTask;

import com.example.springtoysmobile.util.ConnectionFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class FindByidRequest extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... strings) {

        StringBuilder apiResponse = new StringBuilder();
        try {
            URL findById = new URL("http://" + ConnectionFactory.serverIP + ":8080/api/brinquedos/" + strings[0] );
            HttpURLConnection connection = (HttpURLConnection) findById.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(15000);
            connection.connect();

            Scanner scanner = new Scanner(findById.openStream());
            while (scanner.hasNext()) {
                apiResponse.append(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apiResponse.toString();
    }

}
