<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">


    <form action="/DriverServlet" method="post"  role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="addNewDriver"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idDriverr" name="idDriverr" value="${driver.driverID}">
        <h2><fmt:message key="driver" bundle="${rb}"/></h2>
        <div class="form-group col-xs-4">
            <label for="idDriver" class="control-label col-xs-4"><fmt:message key="admin.add.driver.driverID" bundle="${rb}"/>:</label>
            <input type="text" name="idDriver" id="idDriver" class="form-control" value="${driver.driverID}" required="true"/>
            <br>
            <label for="driverName" class="control-label col-xs-4"><fmt:message key="admin.add.driver.driverName" bundle="${rb}"/>:</label>
            <input type="text" name="driverName" id="driverName" class="form-control" value="${driver.driverName}" required="true"/>
            <br>
            <button type="submit" class="btn btn-primary  btn-md"><fmt:message key="admin.accept.button" bundle="${rb}"/></button>
        </div>
        <br>
    </form>

    <br>
    <br>
    <a href="adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>

    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>

    <br>
    <br>
</div>

<%@include file="/views/commonView/footer.jsp"%>
