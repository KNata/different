<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:26 PM
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
    <h2>Drivers</h2>
    <form action="/DriverServlet" method="get" id="seachBusForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="driverName" id="driverName" class="form-control" required="true" placeholder="Type the Name of a driver"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
    </form>

    <div class="row-fluid top-margin" align="center">
        <table class="table table-bordered">
            <tr>
                <th>Driver ID</th>
                <th>Driver Name</th>
            </tr>
            <c:forEach var="d" items="${driverList}">
                <tr>
                    <td><c:out value=" ${d.driverID}"/></td>
                    <td><c:out value="${d.driverName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>


        <a href="addNewBusPage.jsp"><button type="submit" class="btn btn-primary btn-md">New Bus</button></a>
        <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md">Back to main page</button></a>

    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md">Logout</button>
    </form>
</div>
<%@include file="/views/commonView/footer.jsp"%>
