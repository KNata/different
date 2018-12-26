<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:12 AM
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
    <form action="/BusServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="add"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idBuss" name="idBuss" value="${bus.busID}">
        <h2>Route</h2>
        <div class="form-group col-xs-4">
            <label for="idBus" class="control-label col-xs-4">Bus ID:</label>
            <input type="text" name="idBus" id="idBus" class="form-control" value="${bus.busID}" required="true"/>

            <label for="busName" class="control-label col-xs-4">Bus model:</label>
            <input type="text" name="busName" id="busName" class="form-control" value="${bus.busModel}" required="true"/>

            <label for="maxPassegers" class="control-label col-xs-4">Max count of passegers:</label>
            <input type="text" name="maxPassegers" id="maxPassegers" class="form-control" value="${bus.maxCountOfPassagers}" required="true"/>

            <label for="miles" class="control-label col-xs-4">How much miles does the bus went:</label>
            <input type="text" name="miles" id="miles" class="form-control" value="${bus.miles}" required="true"/>

            <label for="maintance" class="control-label col-xs-4">Was on service:</label>
            <input type="text" name="maintance" id="maintance" class="form-control" value="${bus.passedService}" required="true"/>

            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
</div>
</body>
</html>>