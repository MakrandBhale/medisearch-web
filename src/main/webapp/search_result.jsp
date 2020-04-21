<%@ page import="com.makarand.Model.ServerMessage" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.makarand.Model.Meds" %><%--
  Created by IntelliJ IDEA.
  User: Genesis
  Date: 4/8/2020
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous">
    </script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous">
    </script>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/search_result.css" rel="stylesheet"/>
    <script src="js/search_result.js"></script>
</head>
<body>
<script src="js/signup.js"></script>
<% ArrayList<Meds> medsArrayList = (ArrayList<Meds>) request.getAttribute("medList"); %>

<nav class="navbar fixed-top navbar-light d-flex align-items-stretch justify-content-center">
    <form class="form-inline d-flex flex-row col-5 align-items-center justify-content-center">
        <div class="d-flex flex-column col-12">
            <div class="d-flex flex-row" id="search-box-container">
                <input type="text" id="search-box" class="search-box" placeholder="Search for medicines"
                       name="keyword"/>
                <button type="submit">
                    <i class="material-icons blue600">search</i>
                </button>
            </div>
        </div>
    </form>
</nav>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyASZcF25cW5RtQWsfyC4FddPUf5d0PtPN4&callback=initMap">
</script>
<%--<div class="search_result d-flex flex-column col-12 align-items-center justify-content-center">
    <%
        if (medsArrayList != null && medsArrayList.size() > 0) {
            for (Meds med : medsArrayList) {
    %>

    <div class="search_item card d-flex flex-column col-5" style="display: none;">
        <div class="card-body">
            <h5 class="card-title"><%out.println(med.getName());%></h5>
            <h6 class="card-subtitle mb-2 text-muted"><%out.println(med.getBrand());%></h6>
            <p class="card-text">
            <div class="light-background"><%out.println("₹ " + med.getPrice());%></div>
            </p>
        </div>
    </div>
    <%
        }
    } else {
    %>
        <img alt="no rocords found" src="img/nothing.png" height="344">
        <h3>No medicines found.</h3>
    <% } %>
</div>--%>


<div id="wrapper">
    <div class="" id="maps"></div>
    <div id="over_map" class="car bg-light">
        <div class="card-header">Search Results</div>
        <div id="search_result">
            <div class="search_result">
                <%
                    if (medsArrayList != null && medsArrayList.size() > 0) {
                        for (Meds med : medsArrayList) {
                %>
                <div class="search_item custom-card" onclick="search('<%= med.getOwnedby()%>')">
                    <h5 class=" card-title"><%out.println(med.getName());%></h5>
                    <h6 class="card-subtitle mb-2 text-muted"><%out.println(med.getName());%></h6>
                    <p class="card-text">
                    <div class=" light-background"><%out.println("₹ " + med.getPrice());%></div>
                    </p>
                </div>
                <%
                        }
                    }
                %>
            </div>
        </div>

    </div>
</div>
</body>
</html>
