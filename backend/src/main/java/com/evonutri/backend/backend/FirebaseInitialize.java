package com.evonutri.backend.backend;

import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.FirebaseApp;

import java.io.FileNotFoundException;

import java.io.FileInputStream;
import jakarta.annotation.PostConstruct;

@Service
public class FirebaseInitialize {
    @PostConstruct
    public void initialize() {
        try{
        FileInputStream serviceAccount =
        new FileInputStream("resources/evonutri.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .setDatabaseUrl("https://evonutri-493e8-default-rtdb.firebaseio.com")
        .build();

        FirebaseApp.initializeApp(options);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

