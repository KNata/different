<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 1/6/19
  Time: 1:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

    <form action="/RouteServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="edit"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idVisitorr" name="idVisitorr" value="${visitor.visitorID}">

        <h3><fmt:message key="admin.add.visitor.welcome.first" bundle="${rb}"/></h3>
        <h3><fmt:message key="admin.add.user.welcome.warning" bundle="${rb}"/></h3>

        <div class="form-group col-xs-4">

            <label for="idRoute" class="control-label col-xs-4"><fmt:message key="admin.add.route.routeID" bundle="${rb}"/>:</label>
            <input type="text" name="idRoute" id="idRoute" class="form-control" value="${route.routeID}" required="true"/>
            <br>
            <label for="driverID" class="control-label col-xs-4"><fmt:message key="admin.add.route.driverId" bundle="${rb}"/>:</label>
            <input type="text" name="driverID" id="driverID" class="form-control" value="${route.driverID}" required="true"/>
            <br>
            <label for="busID" class="control-label col-xs-4"><fmt:message key="admin.add.route.busID" bundle="${rb}"/>:</label>
            <input type="text" name="busID" id="busID" class="form-control" value="${route.busID}" required="true"/>
            <br>
            <label for="routeDuration" class="control-label col-xs-4"><fmt:message key="admin.add.route.duration" bundle="${rb}"/>:</label>
            <input type="text" name="routeDuration" id="routeDuration" class="form-control" value="${route.routeDuration}" required="true"/>
            <br>
            <label for="arrivalTime" class="control-label col-xs-4"><fmt:message key="admin.add.route.arrivalTime" bundle="${rb}"/>:</label>
            <input type="text" name="arrivalTime" id="arrivalTime" class="form-control" value="${route.arrivalTime}" required="true" placeholder="e.g. 12/06/2018 20:00:00"/>
            <br>
            <label for="departureTime" class="control-label col-xs-4"><fmt:message key="admin.add.route.depatureTime" bundle="${rb}"/>:</label>
            <input type="text" name="departureTime" id="departureTime" class="form-control" value="${route.departureTime}" required="true" placeholder="e.g. 12/06/2018 20:00:00"/>
            <br>
            <button type="submit" class="btn btn-primary  btn-md"><fmt:message key="index.page.submit" bundle="${rb}"/></button>
            <br>
        </div>

    </form>
    <br>
    <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary  btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>

</div>

<%@include file="/views/commonView/footer.jsp"%>

