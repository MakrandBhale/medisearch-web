package com.makarand.servlets;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

@WebServlet("/helloworld")
public class HelloWorld extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        try {
            FirebaseApp firebaseApp = initFirebase();
            pw.println(firebaseApp.getName());
        } catch (IOException e){
            e.printStackTrace();
        }
        pw.close();
    }

    private FirebaseApp initFirebase() throws IOException{

        InputStream serviceAccount =
                 getServletContext().getResourceAsStream("/WEB-INF/medisearch-admin-sdk.json");;

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://medisearch00.firebaseio.com")
                .build();

        return FirebaseApp.initializeApp(options);

    }
}
