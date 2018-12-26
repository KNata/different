<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:13 AM
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
    <form action="/DriverServlet" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idDriverr" name="idDriverr" value="${driver.driverID}">
        <h2>Driver</h2>
        <div class="form-group col-xs-4">
            <label for="idDriver" class="control-label col-xs-4">Driver ID:</label>
            <input type="text" name="idDriver" id="idDriver" class="form-control" value="${driver.driverID}" required="true"/>

            <label for="driverName" class="control-label col-xs-4">Driver name:</label>
            <input type="text" name="driverName" id="driverName" class="form-control" value="${driver.driverName}" required="true"/>

            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>