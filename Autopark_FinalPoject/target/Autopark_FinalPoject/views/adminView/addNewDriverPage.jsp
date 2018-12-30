<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

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
                    <li class="nav-item active">
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


    <form action="/DriverServlet" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="addNewDriver"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idDriverr" name="idDriverr" value="${driver.driverID}">
        <h2>Driver</h2>
        <div class="form-group col-xs-4">
            <label for="idDriver" class="control-label col-xs-4">Driver ID:</label>
            <input type="text" name="idDriver" id="idDriver" class="form-control" value="${driver.driverID}" required="true"/>
            <br>
            <label for="driverName" class="control-label col-xs-4">Driver name:</label>
            <input type="text" name="driverName" id="driverName" class="form-control" value="${driver.driverName}" required="true"/>
            <br>
            <button type="submit" class="btn btn-primary  btn-md">Accept</button>
        </div>
        <br>
    </form>

    <br>
    <br>
    <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md">Back to main page</button></a>

    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md">Logout</button>
    </form>
</div>

<%@include file="/views/commonView/footer.jsp"%>
