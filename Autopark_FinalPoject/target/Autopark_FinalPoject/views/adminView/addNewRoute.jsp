<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#">About Autopark</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Routes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Buses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Drivers</a>
                </li>
            </ul>
            <div align="right">
                <jsp:useBean id="now" class="java.util.Date" />
                <fmt:setLocale value="us-US"/>
                <fmt:formatDate value="${now}"/>
            </div>
        </div>
    </nav>
</div>

<div class="container">
    <form action="/RouteServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="addNewRoute"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idVisitorr" name="idVisitorr" value="${visitor.visitorID}">

        <h2>Route</h2>
        <div class="form-group col-xs-4">
            <label for="idRoute" class="control-label col-xs-4">Route ID:</label>
            <input type="text" name="idRoute" id="idRoute" class="form-control" value="${route.routeID}" required="true"/>
            <br>
            <label for="routeName" class="control-label col-xs-4">Route name:</label>
            <input type="text" name="routeName" id="routeName" class="form-control" value="${route.driverName}" required="true"/>
            <br>
            <label for="driverID" class="control-label col-xs-4">Driver ID:</label>
            <input type="text" name="driverID" id="driverID" class="form-control" value="${route.driverID}" required="true"/>
            <br>
            <label for="busID" class="control-label col-xs-4">Bus ID:</label>
            <input type="text" name="busID" id="busID" class="form-control" value="${route.busID}" required="true"/>
            <br>
            <label for="cityOfDeparture" class="control-label col-xs-4">City of Departure:</label>
            <input type="text" name="cityOfDeparture" id="cityOfDeparture" class="form-control" value="${route.cityOfDeparture}" required="true"/>
            <br>
            <label for="cityOfArrival" class="control-label col-xs-4">City of Arrival:</label>
            <input type="text" name="cityOfArrival" id="cityOfArrival" class="form-control" value="${route.cityOfArrival}" required="true"/>
            <br>
            <label for="routeDuration" class="control-label col-xs-4">Route Duration:</label>
            <input type="text" name="routeDuration" id="routeDuration" class="form-control" value="${route.routeDuration}" required="true"/>
            <br>
            <label for="arrivalTime" class="control-label col-xs-4">Arrival Time:</label>
            <input type="text" name="arrivalTime" id="arrivalTime" class="form-control" value="${route.arrivalTime}" required="true" placeholder="e.g. 12/06/2018 20:00:00"/>
            <br>
            <label for="departureTime" class="control-label col-xs-4">Departure Time:</label>
            <input type="text" name="departureTime" id="departureTime" class="form-control" value="${route.departureTime}" required="true" placeholder="e.g. 12/06/2018 20:00:00"/>
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
            <br>

        </div>
    </form>
    <br>

    <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md">Back to main page</button></a>
        <br>
    <br>

        <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
            <button type="submit" class="btn btn-success btn-md">Logout</button>
        </form>
</div>

<%@include file="/views/commonView/footer.jsp"%>
