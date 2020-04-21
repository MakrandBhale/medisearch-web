package com.makarand.servlets;

import com.google.gson.Gson;
import com.makarand.Helper.Config;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import com.makarand.Helper.Constants;
import com.makarand.Model.ServerMessage;
import com.makarand.Model.User;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/signup", asyncSupported = true)
public class Signup extends HttpServlet {
    AsyncContext asyncContext;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User(
            request.getParameter("name"),
            request.getParameter("email"),
            request.getParameter("password"),
            request.getParameter("phone")
        );

        //sendMessage("", 1 , 2);
        if(!user.validate()){
            sendMessage("Invalid input provided", Constants.INVALID_INPUT, Constants.NEGATIVE, "/signup.jsp");
        }
        asyncContext = request.startAsync();
        checkExistingUser(user);
        System.out.println("Ending");
    }

    private void sendMessage(String serverMessage, int code, int type, String target) {

        ServletRequest request = asyncContext.getRequest();
        ServletResponse response = asyncContext.getResponse();
        ServerMessage message = new ServerMessage(serverMessage, code, type);
        //if(request == null || response == null) return;
        request.setAttribute("serverMessage", message);
        try{
            asyncContext.dispatch(target);
            //request.getRequestDispatcher("/signup.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            //asyncContext.complete();
        }
    }


    private void checkExistingUser(final User user) {
        FirebaseApp firebaseApp = Config.getFirebaseApp();

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(Constants.USERS_TREE);
        dbRef.orderByChild("email").equalTo(user.getEmail()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(!snapshot.exists()){
                    /*User does not exists*/
                    createNewUser(user);
                } else {
                    /*user already exists with same email*/
                    sendMessage(
                            "Email already in use, please use another email",
                            Constants.USER_EXISTS,
                            Constants.NEGATIVE,
                            "/signup.jsp"
                    );
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }


    private void createNewUser(final User newUser) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(Constants.USERS_TREE);
        final String key = dbRef.push().getKey();
        newUser.setUid(key);
        dbRef.child(key).setValue(newUser, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
            if(error == null){
                /*DON't SEND PASSWORD TO CLIENT*/
                newUser.setPassword("");
                asyncContext.getRequest().setAttribute("userObject", newUser);
                asyncContext.dispatch("/search.jsp");
                //sendMessage(userObject, Constants.USER_CREATED, Constants.POSITIVE, "/search.jsp");
            } else {
                /*error occurred*/
                sendMessage(
                        error.getMessage(),
                        Constants.DB_ERROR,
                        Constants.NEGATIVE,
                        "/signup.jsp"
                );
            }
            }
        });
    }
}
