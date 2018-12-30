<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/25/18
  Time: 7:46 PM
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
    <h2>Visitor</h2>
    <!--Search Form -->
    <form action="/VisitorServlet" method="get" id="seachVisitorForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="visitorName" id="visitorName" class="form-control" required="true"
                   placeholder="Type the Name of the Visitor"/>
        </div>
        <button type="submit" class="btn btn-info">
             Search
        </button>
    </form>

    <form action="/VisitorServlet" method="get" id="visitorForm" role="form" >
        <input type="hidden" id="idVisitor" name="idVisitor">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty visitorList}">
                <table  class="table table-striped">
                    <tr>
                        <td>Visitor ID: </td>
                        <td>Visitor login: </td>
                        <td>Visitor password: </td>
                        <td>Visitor role: </td>
                        <td>Visitor name: </td>
                    </tr>
                    <c:forEach var="visitor" items="${visitorList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idVisitor == visitor.visitorID}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>
                                <a href="/VisitorServlet?idDriver=${visitor.visitorID}&searchAction=searchById">${visitor.visitorID}</a>
                            </td>
                            <td>${visitor.visitorID}</td>
                            <td>${visitor.login}</td>
                            <td>${visitor.visitorRole}</td>
                            <td>${visitor.visitorName}</td>
                            <td><a href="#" id="remove"
                                   onclick="document.getElementById('action').value = 'remove';document.getElementById('idVisitor').value = '${visitor.visitorID}';

                                           document.getElementById('visitorForm').submit();">
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
                    No visitor found matching your search criteria
                </div>
            </c:otherwise>
        </c:choose>
    </form>
    <br>
        <a href="registerPage.jsp"><button type="submit" class="btn btn-primary btn-md">New Visitor</button>
        <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md">Back to main page</button></a>
            <br>
            <br>
        <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
            <button type="submit" class="btn btn-success btn-md">Logout</button>
        </form> <a href="adminMainPage.jsp"></a>


    <%@include file="/views/commonView/footer.jsp"%>

