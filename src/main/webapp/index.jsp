<%@ page import="com.makarand.Model.ServerMessage" %><%--
  Created by IntelliJ IDEA.
  User: Genesis
  Date: 4/5/2020
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>

<html>
  <head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

  </head>
  <body>
  <% ServerMessage msg =(ServerMessage) request.getAttribute("serverMessage"); %>
  <% if (msg != null) {
    if(msg.getMsg() != null){
      String message = msg.getMsg();
  %>
  <script type="text/javascript">alert("<%out.print(message);%>")</script>
  <%    } } %>
  <div class="container">
    <div class="row justify-content-center align-items-center" style="height:100vh">
      <div class="col-4">
        <div class="card">
          <div class="card-header">Log in</div>
          <div class="card-body">
            <form action="login" method="post" autocomplete="off">
              <div class="form-group">
                <label for="email">E-Mail Address</label>
                <input id="email" type="text" class="form-control" name="email" placeholder="Enter your email" required>
              </div>
              <div class="form-group">
                <label for="password" >Password</label>
                <input id="password" type="password" class="form-control" name="password" placeholder="Enter password" required>
              </div>
              <button type="submit" id="sendlogin" class="btn btn-primary">Login</button>
              <a href="signup.jsp" class="btn btn-link">
                Create account
              </a>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
  </body>
</html>
