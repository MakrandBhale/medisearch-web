package com.makarand.Helper;

import com.google.firebase.FirebaseApp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Config implements ServletContextListener {
    static private FirebaseApp firebaseApp = null;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        FirebaseConfig config = new FirebaseConfig(servletContextEvent.getServletContext());
        firebaseApp = config.getFirebaseObject();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static FirebaseApp getFirebaseApp() {
        return firebaseApp;
    }
}
