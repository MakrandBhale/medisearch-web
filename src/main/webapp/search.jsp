<%--
  Created by IntelliJ IDEA.
  User: Genesis
  Date: 4/7/2020
  Time: 7:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Search</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/search_jsp.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body class="d-flex container ">
<form class="d-flex container" action="${pageContext.request.contextPath}/search" method="get">
<div class="d-flex container  align-items-center justify-content-center">
    <script src="js/search.js"></script>
    <div class="d-flex flex-column align-items-center col-12">

        <div class="d-flex flex-column col-6 align-items-center justify-content-center">
            <div class="offset-container">
                <h1>Medisearch</h1>
            </div>
        </div>
        <div class="d-flex flex-column col-6 align-items-center justify-content-center">
            <div class="d-flex flex-row" id="search-box-container">
                <input type="text" id="search-box" class="search-box" placeholder="Search for medicines" name="keyword"/>
                <i class="material-icons blue600">search</i>
            </div>
        </div>
        <div class="d-flex flex-column col-8 align-items-center justify-content-center button-bar ">
            <div class="offset-container">
                <button type="submit" class="myButton" id="search_button">Search</button>
<%--                <button onclick="search()" class="myButton" id="search_button">Search</button>--%>
            </div>
        </div>
    </div>
</div>
</form>
</body>
<%--<div class="container">
    <div class="col justify-content-center align-items-center" style="height:100vh">
        <div class="row col-6 justify-content-center align-items-center"><h2>Hello World</h2></div>
        <div class="row col-6 justify-content-center align-items-center search-box-container" id="search-box-container">
            <label for="search-box"></label><input type="text" id="search-box" class="search-box" placeholder="Search for medicines"/>
        </div>
    </div>
</div>--%>
</html>
