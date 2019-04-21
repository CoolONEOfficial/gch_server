package ru.ruykarpuni.testservlet;


import ru.ruykarpuni.testservlet.error.FirebaseException;
import ru.ruykarpuni.testservlet.model.FirebaseResponse;
import ru.ruykarpuni.testservlet.service.Firebase;

import java.io.UnsupportedEncodingException;

public class FirebaseConnection {
    private String firebase_baseUrl ;

    // get the api-key (ie: 'tR7u9Sqt39qQauLzXmRycXag18Z2')
    private String firebase_apiKey = "AIzaSyBHuqE27uLKjGCoCkBPkbcaQ-uuLX0zJDM";

    Firebase firebase;
    FirebaseResponse response ;

    public FirebaseConnection(String firebase_baseUrl, String firebase_apiKey) throws FirebaseException {
        this.firebase_baseUrl = firebase_baseUrl;
        this.firebase_apiKey = firebase_apiKey;

        if( firebase_baseUrl == null || firebase_baseUrl.trim().isEmpty() ) {
            throw new IllegalArgumentException( "Program-argument baseUrl not found but required" );
        }
        firebase = new Firebase( firebase_baseUrl );
    }

    public String get() throws UnsupportedEncodingException, FirebaseException {
        response = firebase.get();
        return response.getRawBody();
    }

}