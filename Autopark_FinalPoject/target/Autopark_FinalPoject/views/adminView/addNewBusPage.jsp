<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">
    <div class="container-fluid">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="index.jsp">About Autopark</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
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
    <form action="/BusServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="addNewBus"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idBuss" name="idBuss" value="${bus.busID}">
        <h2>Add new Bus</h2>
        <div class="form-group col-xs-4">
            <label for="idBus" class="control-label col-xs-4">Bus ID:</label>
            <input type="text" name="idBus" id="idBus" class="form-control" value="${bus.busID}" required="true"/>
            <br>
            <label for="busName" class="control-label col-xs-4">Bus model:</label>
            <input type="text" name="busName" id="busName" class="form-control" value="${bus.busModel}" required="true"/>
            <br>
            <label for="maxPassegers" class="control-label col-xs-4">Max count of passegers:</label>
            <input type="text" name="maxPassegers" id="maxPassegers" class="form-control" value="${bus.maxCountOfPassagers}" required="true"/>
            <br>
            <label for="miles" class="control-label col-xs-4">How much miles does the bus went:</label>
            <input type="text" name="miles" id="miles" class="form-control" value="${bus.miles}" required="true"/>
            <br>
            <label for="maintance" class="control-label col-xs-4">Was on service:</label>
            <input type="text" name="maintance" id="maintance" class="form-control" value="${bus.passedService}" required="true"/>
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
    </form>
    <br>
    <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary  btn-md">Back to main page</button></a>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md">Logout</button>
    </form>

    <%@include file="/views/commonView/footer.jsp"%>
