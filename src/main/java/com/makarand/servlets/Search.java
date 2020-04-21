package com.makarand.servlets;

import com.google.firebase.database.*;
import com.makarand.Helper.Constants;
import com.makarand.Model.Meds;
import com.makarand.Model.ServerMessage;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/search", asyncSupported = true)
public class Search extends HttpServlet {
    AsyncContext asyncContext;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        //response.sendRedirect("search.jsp");
        if (!validate(keyword)) {
            response.getWriter().print("<script>alert('Invalid input');</script>");
        }
        asyncContext = request.startAsync();
        search(keyword);
    }

    private void search(String keyword) {
        final ArrayList<Meds> medsArrayList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constants.MEDS_TREE);
        reference.orderByChild("name").equalTo(keyword).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //if(snapshot.exists()){
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    medsArrayList.add(dataSnapshot.getValue(Meds.class));
                }
                asyncContext.getRequest().setAttribute("medList", medsArrayList);
                asyncContext.dispatch("/search_result.jsp");
                //sendMessage(new ServerMessage("Found " + medsArrayList.size() + " medicines.", Constants.POSITIVE, Constants.POSITIVE));
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });

    }

    private void sendMessage(ServerMessage message) {
        asyncContext.getRequest().setAttribute("serverMessage", message);
        try {
            asyncContext.dispatch("/search.jsp");
            //request.getRequestDispatcher("/signup.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validate(String keyword) {
        if (keyword == null) return false;
        if (keyword.isEmpty()) return false;
        return keyword.length() >= 3 && keyword.length() <= 24;
    }

}
