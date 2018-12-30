<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

    <div class="container">
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
                            <li class="nav-item ">
                                <a class="nav-link" href="#">Routes</a>
                            </li>
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Buses</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#">Drivers</a>
                            </li>

                            <li class="nav-item">
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
        </div>

    <h2>Buses</h2>
    <!--Search Form -->
    <form action="/BusServlet" method="get" id="seachBusForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="busName" id="busName" class="form-control" required="true" placeholder="Type the Name of the bus"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
    </form>

    <form action="/BusServlet" method="post" id="busForm" role="form" >
        <input type="hidden" id="idBus" name="idBus">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty busList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Bus ID: </td>
                        <td>Bus Model: </td>
                        <td>Max Count Of Passangers: </td>
                        <td>Miles: </td>
                        <td>Passed Servise? </td>
                    </tr>
                    </thead>
                    <c:forEach var="bus" items="${busList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idBus == bus.busID}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/BusServlet?idBus=${bus.busID}&searchAction=searchById">${bus.busID}</a>
                            </td>
                            <td>${bus.busID}</td>
                            <td>${bus.busModel}</td>
                            <td>${bus.maxCountOfPassangers}</td>
                            <td>${bus.miles}</td>
                            <td>${bus.passedService}</td>

                            <td><a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'remove';document.getElementById('idBus').value = '${bus.busID}';

                                           document.getElementById('busForm').submit();">
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
                    No bus found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <br>
        <a href="addNewBusPage.jsp"><button type="submit" class="btn btn-secondary btn-md">New Bus</button></a>
        <a href="adminMainPage.jsp"><button type="submit" class="btn btn-primary  btn-md">Back to main page</button></a>

        <br>
        <br>
        <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
             <button type="submit" class="btn btn-success btn-md">Logout</button>
        </form>
</div>
<%@include file="/views/commonView/footer.jsp"%>
