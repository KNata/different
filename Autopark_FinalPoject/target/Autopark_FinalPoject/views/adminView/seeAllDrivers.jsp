<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<div class="container">

    <h2><fmt:message key="admin.see.drivers" bundle="${rb}"/></h2>
    <form action="/DriverServlet" method="get" id="seachBusForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="driverName" id="driverName" class="form-control" required="true" placeholder="Type the Name of a driver"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> <fmt:message key="search.button" bundle="${rb}"/>
        </button>
    </form>

    <form action="/DriverServlet" method="get" id="showAll" role="form">
    <div class="row-fluid top-margin" align="center">
        <table class="table table-bordered">
            <tr>
                <th><fmt:message key="admin.add.driver.driverID" bundle="${rb}"/></th>
                <th><fmt:message key="admin.add.driver.driverName" bundle="${rb}"/></th>
            </tr>
            <c:forEach var="driver" items="${driverList}">
                <tr>
                    <td><c:out value=" ${driver.driverID}"/></td>
                    <td><c:out value="${driver.driverName}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </form>

        <a href="/views/adminView/addNewDriverPage.jsp"><button type="submit" class="btn btn-primary btn-md"><fmt:message key="admin.add.new.driver" bundle="${rb}"/></button></a>
        <a href="/views/adminView/adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>

    <br>
    <br>

    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>
</div>
<%@include file="/views/commonView/footer.jsp"%>
