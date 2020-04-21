package com.makarand.Helper;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseConfig {
    private ServletContext context;
    private FirebaseApp firebaseApp = null;
    private boolean initialized = false;
    public FirebaseConfig(ServletContext context){
        this.context = context;
    }

    public FirebaseApp getFirebaseObject(){
        if(initialized && this.firebaseApp != null) return this.firebaseApp;

        try {
            InputStream serviceAccount = context.getResourceAsStream("/WEB-INF/medisearch-admin-sdk.json");;
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://medisearch00.firebaseio.com")
                    .build();
            initialized = true;
            return FirebaseApp.initializeApp(options);
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
