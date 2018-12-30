<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:11 AM
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
                        <li class="nav-item ">
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


    <h2> Welcome to Admin's page</h2>

        <a href="#" class="btn btn-danger btn-lg btn-block" role="button" aria-pressed="true">Pending Requests <span class="badge badge-light">4</span></a>

        <a href="seeAllDrivers.jsp" class="btn btn-info btn-lg btn-block" role="button" aria-pressed="true">See all Drivers</a>
        <a href="seeAllRoutes.jsp" class="btn btn-info btn-lg btn-block" role="button" aria-pressed="true">See all Routes</a>
        <a href="seeAllBuses.jsp" class="btn btn-info btn-lg btn-block" role="button" aria-pressed="true">See all Buses</a>
        <a href="allVisitors.jsp" class="btn btn-info btn-lg btn-block" role="button" aria-pressed="true">See all Users</a>
        <a href="addNewDriverPage.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true">Add a new Driver</a>
        <a href="addNewRoute.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true">Add a new Route</a>
        <a href="addNewBusPage.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true">Add a new Bus</a>
        <a href="registerPage.jsp" class="btn btn-primary btn-lg btn-block" role="button" aria-pressed="true">Add a new User</a>

    </div>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md">Logout</button>
    </form>

    <%@include file="/views/commonView/footer.jsp"%>


