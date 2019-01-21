<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

    <h2><fmt:message key="admin.routes" bundle="${rb}"/></h2>

    <form action="/RouteServlet" method="post" id="routeForm" role="form" >
        <input type="hidden" id="idRoute" name="idRoute">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty routeList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td><fmt:message key="admin.add.route.routeID" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.route.name" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.driver.driverID" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.bus.busID" bundle="${rb}"/>: </td>
                         <td><fmt:message key="admin.add.route.depatureCity" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.route.arrivalTime" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.route.duration" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.route.depatureTime" bundle="${rb}"/></td>
                        <td><fmt:message key="admin.add.route.arrivalTime" bundle="${rb}"/></td>
                    </tr>
                    </thead>
                    <c:forEach var="route" items="${routeList}">
                        <tr class="${classSucess}">
                            <td>${route.getRouteID()}</td>
                            <td>${route.getRouteTitle()}</td>
                            <td>${route.getDriverID()}</td>
                            <td>${route.getBusID()}</td>
                            <td>${route.getRouteBegin()}</td>
                            <td>${route.getRouteEnd()}</td>
                            <td>${route.getRouteDuration()}</td>
                            <td>${route.getRouteStartTime()}</td>
                            <td>${route.getRouteEndTime()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
    </form>
    <br>
    <a href="/views/adminView/addNewRoute.jsp"><button type="submit" class="btn btn-primary btn-md"><fmt:message key="admin.add.route" bundle="${rb}"/></button></a>
    <a href="/views/adminView/editRoute.jsp"><button type="submit" class="btn btn-primary btn-md"><fmt:message key="admin.edit.route" bundle="${rb}"/></button></a>
    <a href="/views/adminView/deleteRoute.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="admin.delete.route" bundle="${rb}"/></button></a>
    <br>
    <br>
    <a href="/views/adminView/adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>

    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>

</div>

    <%@include file="/views/commonView/footer.jsp"%>

