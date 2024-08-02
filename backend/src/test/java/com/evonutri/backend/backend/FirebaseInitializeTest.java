package com.evonutri.backend.backend;

import com.evonutri.backend.backend.FirebaseInitialize;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FirebaseInitializeTest {

    @InjectMocks
    private FirebaseInitialize firebaseInitialize;

    @Mock
    private FileInputStream fileInputStream;

    @Mock
    private GoogleCredentials googleCredentials;

    @BeforeEach
    public void setup() throws Exception {
        when(new FileInputStream("resources/evonutri.json")).thenReturn(fileInputStream);
        when(GoogleCredentials.fromStream(fileInputStream)).thenReturn(googleCredentials);
    }

    @Test
    public void testInitialize() throws Exception {
        firebaseInitialize.initialize();

        FirebaseOptions options = new FirebaseOptions.Builder()
            .setCredentials(googleCredentials)
            .setDatabaseUrl("https://evonutri-493e8-default-rtdb.firebaseio.com")
            .build();

        verify(fileInputStream, times(1)).close();
        assertTrue(FirebaseApp.getApps().size() > 0);
    }

    @Test
    public void testFileNotFound() {
        doThrow(new FileNotFoundException()).when(() -> new FileInputStream("resources/evonutri.json"));

        firebaseInitialize.initialize();

        // Verify that the exception is handled and printed
        verify(fileInputStream, never()).close();
    }
}
