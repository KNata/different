<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 1:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

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
                        <li class="nav-item">
                            <a class="nav-link" href="#">Buses</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Drivers</a>
                        </li>

                        <li class="nav-item active">
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

    <form action="/VisitorServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="addNewVisitor"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idVisitorr" name="idVisitorr" value="${visitor.visitorID}">

        <h3>Welcome to our community</h3>
        <h3>Please properly fill all text boxes. This information will be confidential</h3>

        <div class="form-group col-xs-4">
            <label for="idVisitor" class="control-label col-xs-4">Visitor ID:</label>
            <input type="text" name="idVisitor" id="idVisitor" class="form-control" value="${visitor.visitorID}" required="true"/>
            <br>
            <label for="visitorLogin" class="control-label col-xs-4">Visitor login:</label>
            <input type="text" name="visitorLogin" id="visitorLogin" class="form-control" value="${visitor.visitorLogin}" required="true"/>
            <br>

            <label for="visitorPassword" class="control-label col-xs-4">Visitor password:</label>
            <input type="password" name="visitorPassword" id="visitorPassword" class="form-control" value="${visitor.visitorPassword}" required="true"/>
            <br>
            <label for="visitorName" class="control-label col-xs-4">Visitor name:</label>
            <input type="text" name="visitorName" id="visitorName" class="form-control" value="${visitor.visitorName}" required="true"/>
            <br>
            <label for="driverId" class="control-label col-xs-4">Driver ID:</label>
            <input type="text" name="driverId" id="driverId" class="form-control" value="${visitor.driverId}" required="true"/>
            <br>
            <label for="visitorRole" class="control-label col-xs-4">Visitor Role:</label>
            <input type="text" name="visitorRole" id="visitorRole" class="form-control" value="${visitor.visitorRole}" required="true"/>
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

</div>
<%@include file="/views/commonView/footer.jsp"%>

