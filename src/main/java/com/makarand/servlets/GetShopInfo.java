package com.makarand.servlets;

import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.makarand.Helper.Constants;
import com.makarand.Model.ServerMessage;
import com.makarand.Model.Shop;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/GetShopInfo", asyncSupported = true)
public class GetShopInfo extends HttpServlet {
    AsyncContext asyncContext;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shopId = request.getParameter("shopId");

        if (!validate(shopId)) {
            response.getWriter().print("<script>alert('Invalid shopId')</script>");
        }

        asyncContext = request.startAsync();
        getShopInfo(shopId);
    }

    private void getShopInfo(String shopId) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference(Constants.SHOPS_TREE);

        dbRef.child(shopId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                sendMessage(snapshot.getValue(Shop.class));
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

    private void sendMessage(Shop shop) {
        try{
            PrintWriter pw  = asyncContext.getResponse().getWriter();
            pw.print(new Gson().toJson(shop));
            pw.flush();
            asyncContext.complete();
        } catch (IOException e){
            e.printStackTrace();
        }

        //asyncContext.getRequest().setAttribute("shopData", "test");
        //asyncContext.dispatch("search_result.jsp");
    }

    private boolean validate(String shopId) {
        if (shopId == null) return false;
        if (shopId.isEmpty()) return false;
        return true;
    }
}
