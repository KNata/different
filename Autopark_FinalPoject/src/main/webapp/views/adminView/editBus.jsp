<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 1/6/19
  Time: 1:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">

    <form action="/BusServlet" method="post" role="form" data-toggle="validator" >
        <c:if test ="${empty action}">
            <c:set var="action" value="editBus"/>
        </c:if>

        <h3><fmt:message key="edit.bus.title" bundle="${rb}"/></h3>

        <div class="form-group col-xs-4">

            <label for="idBus" class="control-label col-xs-4"><fmt:message key="admin.add.bus.busID" bundle="${rb}"/>:</label>
            <input type="text" name="idBus" id="idBus" class="form-control" value="${bus.busID}" required="true"/>
            <br>
            <label for="miles" class="control-label col-xs-4"><fmt:message key="admin.add.bus.miles" bundle="${rb}"/>:</label>
            <input type="text" name="miles" id="miles" class="form-control" value="${bus.miles}"/>

            <label for="passedService" class="control-label col-xs-4"><fmt:message key="admin.add.bus.service" bundle="${rb}"/>?</label>
            <input type="text" name="passedService" id="passedService" class="form-control" value="${bus.passedService}" required="true" placeholder="true / false"/>
        </div>
        <br>
        <button type="submit" class="btn btn-primary btn-md"><fmt:message key="edit.button" bundle="${rb}"/></button>
    </form>
    <br>
    <br>
    <br>
    <a href="/views/adminView/adminMainPage.jsp"><button type="submit" class="btn btn-secondary  btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>

</div>

<%@include file="/views/commonView/footer.jsp"%>

