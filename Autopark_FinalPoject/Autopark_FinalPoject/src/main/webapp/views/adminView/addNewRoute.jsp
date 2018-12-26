<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form action="/RouteServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idRoutee" name="idRoutee" value="${route.routeID}">
        <h2>Route</h2>
        <div class="form-group col-xs-4">
            <label for="idRoute" class="control-label col-xs-4">Driver ID:</label>
            <input type="text" name="driverID" id="idRoute" class="form-control" value="${route.routeID}" required="true"/>

            <label for="routeName" class="control-label col-xs-4">Route name:</label>
            <input type="text" name="driverName" id="routeName" class="form-control" value="${route.driverName}" required="true"/>

            <label for="driverID" class="control-label col-xs-4">Driver ID:</label>
            <input type="text" name="driverID" id="driverID" class="form-control" value="${route.driverID}" required="true"/>

            <label for="busID" class="control-label col-xs-4">Bus ID:</label>
            <input type="text" name="driverName" id="busID" class="form-control" value="${route.busID}" required="true"/>

            <label for="cityOfDeparture" class="control-label col-xs-4">City of Departure:</label>
            <input type="text" name="driverID" id="cityOfDeparture" class="form-control" value="${route.cityOfDeparture}" required="true"/>

            <label for="cityOfArrival" class="control-label col-xs-4">City of Arrival:</label>
            <input type="text" name="driverName" id="cityOfArrival" class="form-control" value="${route.cityOfArrival}" required="true"/>

            <label for="routeDuration" class="control-label col-xs-4">Route Duration:</label>
            <input type="text" name="driverID" id="routeDuration" class="form-control" value="${route.routeDuration}" required="true"/>

            <label for="departureTime" class="control-label col-xs-4">Departure Time:</label>
            <input type="text" name="driverName" id="departureTime" class="form-control" value="${route.departureTime}" required="true"/>

            <label for="arrivalTime" class="control-label col-xs-4">Arrival Time:</label>
            <input type="text" name="driverName" id="arrivalTime" class="form-control" value="${route.arrivalTime}" required="true"/>

            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>