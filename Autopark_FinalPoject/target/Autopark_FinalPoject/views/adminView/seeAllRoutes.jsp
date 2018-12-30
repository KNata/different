<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="index.jsp">About Autopark</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="#">Routes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Buses</a>
                    </li>
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Drivers</a>
                    </li>

                    <li class="nav-item ">
                        <a class="nav-link" href="#">Visitors</a>
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
    <h2>Routes</h2>
    <!--Search Form -->
    <form action="/RouteServlet" method="get" id="seachRouteForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="routeName" id="routeName" class="form-control" required="true" placeholder="Type the Name of the route"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>

    </form>

    <form action="/RouteServlet" method="post" id="routeForm" role="form" >
        <input type="hidden" id="idRoute" name="idRoute">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty routeList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Route ID: </td>
                        <td>Route name: </td>
                        <td>Driver ID: </td>
                        <td>Bus ID: </td>
                        <td>Route ID: </td>
                        <td>Route city of department: </td>
                        <td>Route city of arrival: </td>
                        <td>Route duration: </td>
                        <td>Route time of department</td>
                        <td>Route time of arrival</td>
                    </tr>
                    </thead>
                    <c:forEach var="route" items="${routeList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idRoute == route.routeID}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/RouteServlet?idDriver=${driver.id}&searchAction=searchById">${route.routeID}</a>
                            </td>
                            <td>${route.routeID}</td>
                            <td>${route.routeName}</td>
                            <td>${route.driverID}</td>
                            <td>${route.busID}</td>
                            <td>${route.cityOfDeparture}</td>
                            <td>${route.cityOfArrival}</td>
                            <td>${route.routeDuration}</td>
                            <td>${route.departureTime}</td>
                            <td>${route.arrivalTime}</td>

                            <td><a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'remove';document.getElementById('idRoute').value = '${route.routeID}';

                                           document.getElementById('routeForm').submit();">
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
                    No route found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <br>
    <a href="addNewRoute.jsp"><button type="submit" class="btn btn-primary btn-md">New route</button></a>
    <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md">Back to main page</button></a>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md">Logout</button>
    </form>

</div>

    <%@include file="/views/commonView/footer.jsp"%>

