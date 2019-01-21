<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

        <h3><fmt:message key="admin.main.welcome" bundle="${rb}"/></h3>

        <a href="#" class="btn btn-danger btn-lg btn-block" role="button" aria-pressed="true">Pending Requests <span class="badge badge-light">4</span></a>

        <form action="${pageContext.request.contextPath}/DriverServlet" method="get">
            <button type="submit" class="btn btn-info"><fmt:message key="admin.see.drivers" bundle="${rb}"/></button>
        </form>
        <form action="${pageContext.request.contextPath}/RouteServlet" method="get">
            <button type="submit" class="btn btn-info"><fmt:message key="admin.see.routes" bundle="${rb}"/></button>
        </form>
        <form action="${pageContext.request.contextPath}/BusServlet" method="get">
            <button type="submit" class="btn btn-info"><fmt:message key="admin.see.buses" bundle="${rb}"/></button>
        </form>
        <form action="${pageContext.request.contextPath}/VisitorServlet" method="get">
            <button type="submit" class="btn btn-info"><fmt:message key="admin.see.visitors" bundle="${rb}"/></button>
        </form>

        <a href="addNewDriverPage.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true"><fmt:message key="admin.add.driver" bundle="${rb}"/></a>
        <a href="addNewRoute.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true"><fmt:message key="admin.add.route" bundle="${rb}"/></a>
        <a href="addNewBusPage.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true"><fmt:message key="admin.add.bus" bundle="${rb}"/></a>
        <a href="registerPage.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true"><fmt:message key="admin.add.visitor" bundle="${rb}"/></a>

    </div>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>

    <%@include file="/views/commonView/footer.jsp"%>


