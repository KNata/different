<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

    <h2>My Driver Info </h2>
    <c:out value="${sessionScope.name}"/>

    <form action="/RouteDriverServlet" method="get" id="showAllMyRoutes" role="form">
        <div class="row-fluid top-margin" align="center">
            <table class="table table-bordered">
                <tr>
                    <th><fmt:message key="admin.add.route.routeID" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.add.route.name" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.add.bus.busID" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.add.route.depatureCity" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.add.route.arrivalCity" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.add.route.depatureTime" bundle="${rb}"/></th>
                    <th><fmt:message key="admin.add.route.arrivalTime" bundle="${rb}"/></th>
                </tr>
                <c:forEach var="routeStory" items="${routeStoryList}">
                    <tr>
                        <td><c:out value=" ${routeStory.routeID}"/></td>
                        <td><c:out value="${routeStory.routeTitle}" /></td>
                        <td><c:out value=" ${routeStory.getBus().getBusID()}"/></td>
                        <td><c:out value="${routeStory.getRouteBegin()}" /></td>
                        <td><c:out value=" ${routeStory.getRouteEnd()}"/></td>
                        <td><c:out value="${routeStory.getRouteStartTime()}" /></td>
                        <td><c:out value=" ${routeStory.getRouteEndTime()}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </form>
<br><br>
    <a href="/views/adminView/adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>
    <br>
    <br>

    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>
</div>
<%@include file="/views/commonView/footer.jsp"%>