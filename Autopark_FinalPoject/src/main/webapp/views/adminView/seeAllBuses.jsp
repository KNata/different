<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/23/18
  Time: 4:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">


    <h2><fmt:message key="admin.see.buses" bundle="${rb}"/></h2>

    <form action="/BusServlet" method="get" id="busForm" role="form" >
        <c:choose>
            <c:when test="${not empty busList}">
                <table  class="table table-striped">
                    <thead>${action}
                    <tr>
                        <td><strong><fmt:message key="admin.add.bus.busID" bundle="${rb}"/>: </strong> </td>
                        <td><strong><fmt:message key="admin.add.bus.model" bundle="${rb}"/>: </strong></td>
                        <td><strong><fmt:message key="admin.add.bus.maxPass" bundle="${rb}"/>: </strong></td>
                        <td><strong><fmt:message key="admin.add.bus.miles" bundle="${rb}"/>: </strong></td>
                        <td><strong><fmt:message key="admin.add.bus.service" bundle="${rb}"/>? </strong></td>
                    </tr>
                    </thead>
                    <c:forEach var="bus" items="${busList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idBus == bus.busID}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">

                            <td>${bus.busID}</td>
                            <td>${bus.busModel}</td>
                            <td>${bus.maxCountOfPassagers}</td>
                            <td>${bus.miles}</td>
                            <td>${bus.passedService}</td>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>
        </c:choose>
    </form>
    <br>
    <br>
    <a href="/views/adminView/addNewBusPage.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="admin.new.bus" bundle="${rb}"/></button></a>
    <a href="/views/adminView/editBus.jsp"><button type="submit" class="btn btn-primary btn-md"><fmt:message key="admin.edit.bus" bundle="${rb}"/></button></a>
    <a href="/views/adminView/deleteBus.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="admin.delete.bus" bundle="${rb}"/></button></a>

    <br>
    <br>

    <a href="/views/adminView/adminMainPage.jsp"><button type="submit" class="btn btn-primary  btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>


    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>
</div>
<%@include file="/views/commonView/footer.jsp"%>
