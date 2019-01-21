<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 1/18/19
  Time: 12:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

    <h2>My Driver Info </h2>
    <c:out value="${sessionScope.name}"/>

    <form action="/RouteDriverServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="editDriver"/>
        </c:if>
        <input type="hidden" id="action" name="action" value="${action}">
        <input type="hidden" id="idVisitorr" name="idVisitorr" value="${visitor.visitorID}">

        <h3><fmt:message key="admin.add.user.welcome.warning" bundle="${rb}"/></h3>

        <div class="form-group col-xs-4">

            <label for="driverPassword" class="control-label col-xs-4"><fmt:message key="admin.add.user.visitor.password" bundle="${rb}"/>:</label>
            <input type="password" name="driverPassword" id="driverPassword" class="form-control" value="${visitor.visitorPassword}" required="true"/>

            <button type="submit" class="btn btn-primary  btn-md"><fmt:message key="admin.accept.button" bundle="${rb}"/></button>
        </div>

    </form>
    <br>
    <a href="/driverMainPage.jsp"><button type="submit" class="btn btn-secondary  btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>

</div>
<%@include file="/views/commonView/footer.jsp"%>