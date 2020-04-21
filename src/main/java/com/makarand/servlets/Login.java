package com.makarand.servlets;

import com.google.firebase.database.*;
import com.makarand.Helper.Constants;
import com.makarand.Model.ServerMessage;
import com.makarand.Model.User;
import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/login", asyncSupported = true)
public class Login extends HttpServlet {
    AsyncContext asyncContext;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        asyncContext = request.startAsync();

        if(!validate(email, password)){
            sendResponseObject(new ServerMessage("Invalid Input", Constants.INVALID_INPUT, Constants.NEGATIVE));
        }

        signInUser(email, password);
    }

    private void sendResponseObject(ServerMessage message){
        asyncContext.getRequest().setAttribute("serverMessage", message);
        try{

            asyncContext.dispatch("/index.jsp");
            //request.getRequestDispatcher("/signup.jsp").forward(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }

    }



    private void signInUser(final String email, final String password) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Constants.USERS_TREE);

        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    /*user exits, check credentials*/
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User newUser = snapshot.getValue(User.class);
                        if(newUser.getPassword().equals(password) && newUser.getEmail().equals(email)){
                            /*user authenticated*/
                            newUser.setPassword("");
                            asyncContext.getRequest().setAttribute("userObject", newUser);
                            try{
                                asyncContext.getResponse().getWriter().write("<script>document.cookie='uid=" + newUser.getUid() + "'</script>");
                                asyncContext.dispatch("/search.jsp");
                                //request.getRequestDispatcher("/signup.jsp").forward(request, response);
                            } catch (Exception e){
                                e.printStackTrace();
                            }

                        }  else {
                            /*wrong username or password*/
                            ServerMessage message = new ServerMessage("Wrong credentials", Constants.WRONG_CREDENTIALS, Constants.NEGATIVE);
                            sendResponseObject(message);
                        }
                    }
                } else {
                    /*User does not exists*/
                    ServerMessage message = new ServerMessage("No user found", Constants.USER_NOT_FOUND, Constants.NEGATIVE);
                    sendResponseObject(message);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                ServerMessage message = new ServerMessage(error.getMessage(), Constants.DB_ERROR, Constants.NEGATIVE);
                sendResponseObject(message);
            }
        });
    }

    private boolean validate(String email, String password){
        if(email == null || email.length() < 6) return false;
        if(password == null || password.length() < 6) return false;
        return true;
    }
}
