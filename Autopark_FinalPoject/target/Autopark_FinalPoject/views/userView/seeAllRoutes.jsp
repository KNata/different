<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:39 PM
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
${pageContext.request.characterEncoding}

<div class="container">
    <h2>Routes</h2>
    <!--Search Form -->
    <form action="/RouteServlet" method="get" id="seachRouteForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="routeName" id="routeName" class="form-control" required="true" placeholder="Type the Name of the route"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>

    </form>

    <!--Employees List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/RouteServlet" method="post" id="routeForm" role="form" >
        <input type="hidden" id="idRoute" name="idRoute">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty routeList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Route ID: </td>
                        <td>Route name: </td>
                        <td>Driver ID: </td>
                        <td>Bus ID: </td>
                        <td>Route ID: </td>
                        <td>Route city of department: </td>
                        <td>Route city of arrival: </td>
                        <td>Route duration: </td>
                        <td>Route time of department</td>
                        <td>Route time of arrival</td>
                    </tr>
                    </thead>
                    <c:forEach var="route" items="${routeList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idRoute == route.routeID}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/RouteServlet?idDriver=${driver.id}&searchAction=searchById">${route.routeID}</a>
                            </td>
                            <td>${route.routeID}</td>
                            <td>${route.routeName}</td>
                            <td>${route.driverID}</td>
                            <td>${route.busID}</td>
                            <td>${route.cityOfDeparture}</td>
                            <td>${route.cityOfArrival}</td>
                            <td>${route.routeDuration}</td>
                            <td>${route.departureTime}</td>
                            <td>${route.arrivalTime}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No route found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>
</body>
