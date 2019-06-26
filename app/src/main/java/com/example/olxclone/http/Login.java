package com.example.olxclone.http;

import android.os.AsyncTask;
import android.util.Log;

import com.example.olxclone.entity.User;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Login extends AsyncTask<Void, Void, User> {

    private final String url;
    private final User user;

    public Login(String url, User user){

        this.url = url;
        this.user = user;
    }

    @Override
    protected User doInBackground(Void... voids) {

        try {

            URL url = new URL(this.url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(5000);

            Gson gson = new Gson();
            String userJson = gson.toJson(user);

            PrintStream printStream = new PrintStream(connection.getOutputStream());
            printStream.println(userJson);

            connection.connect();

            if(connection.getResponseCode() == 200){

                Scanner scanner = new Scanner(connection.getInputStream());
                String resposta = scanner.next();
                while(scanner.hasNext()){

                    resposta += " ";
                    resposta += scanner.next();
                }

                return gson.fromJson(resposta, User.class);
            }
        } catch (MalformedURLException e) {
            Log.i("MalformedURLException", e.getMessage());
        } catch (IOException e) {
            Log.i("IOException", e.getMessage());
        }
        return null;
    }
}
