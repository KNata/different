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
    <h2>Drivers</h2>
    <!--Search Form -->
    <form action="/DriverServlet" method="get" id="seachDriverForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="driverName" id="driverName" class="form-control" required="true" placeholder="Type the Name of the driver"/>
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
    <form action="/DriverServlet" method="post" id="driverForm" role="form" >
        <input type="hidden" id="idDriver" name="idDriver">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty driverList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Driver ID</td>
                        <td>Driver name</td>

                    </tr>
                    </thead>
                    <c:forEach var="driver" items="${driverList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idDriver == driver.id}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/DriverServlet?idDriver=${driver.id}&searchAction=searchById">${driver.driverID}</a>
                            </td>
                            <td>${driver.driverID}</td>
                            <td>${driver.driverName}</td>

                            <td><a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'remove';document.getElementById('idDriver').value = '${driver.driverID}';

                                           document.getElementById('driverForm').submit();">
                                <span class="glyphicon glyphicon-trash"/>
                            </a>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <br>
                <div class="alert alert-info">
                    No people found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <form action ="jsp/addNewDriver.jsp">

        <button type="submit" class="btn btn-primary  btn-md">New Driver</button>
    </form>
</div>
</body>
</html>