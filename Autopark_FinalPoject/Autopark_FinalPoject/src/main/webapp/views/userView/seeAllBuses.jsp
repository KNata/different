<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:26 PM
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
    <h2>Buses</h2>
    <!--Search Form -->
    <form action="/BusServlet" method="get" id="seachBusForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="busName" id="busName" class="form-control" required="true" placeholder="Type the Name of the bus"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
    </form>

    <!-- List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/BusServlet" method="post" id="busForm" role="form" >
        <input type="hidden" id="idBus" name="idBus">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty busList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Bus ID: </td>
                        <td>Bus Model: </td>
                        <td>Max Count Of Passangers: </td>
                        <td>Miles: </td>
                        <td>Passed Servise? </td>
                    </tr>
                    </thead>
                    <c:forEach var="bus" items="${busList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idBus == bus.busID}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/BusServlet?idBus=${bus.busID}&searchAction=searchById">${bus.busID}</a>
                            </td>
                            <td>${bus.busID}</td>
                            <td>${bus.busModel}</td>
                            <td>${bus.maxCountOfPassangers}</td>
                            <td>${bus.miles}</td>
                            <td>${bus.passedService}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No bus found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
</div>
</body>
</html>