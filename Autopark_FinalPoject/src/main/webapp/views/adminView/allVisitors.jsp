<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/25/18
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

<div class="container">


    <!--Search Form -->
    <form action="/VisitorServlet" method="get" id="seachVisitorForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="visitorName" id="visitorName" class="form-control" required="true"
                   placeholder="Type the Name of the Visitor"/>
        </div>
        <button type="submit" class="btn btn-info">
            <fmt:message key="search.button" bundle="${rb}"/>
        </button>
    </form>

    <h2>Visitor List</h2>

    <form action="/VisitorServlet" method="get" id="visitorForm" role="form" >
        <input type="hidden" id="idVisitor" name="idVisitor">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty visitorList}">
                <table  class="table table-striped">
                    <tr>
                        <td><fmt:message key="admin.add.user.visitorID" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.user.visitor.login" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.user.visitor.password" bundle="${rb}"/>: </td>
                        <td><fmt:message key="admin.add.user.visitor.name" bundle="${rb}"/>: </td>
                    </tr>
                    <c:forEach var="visitor" items="${visitorList}">
                        <c:set var="classSucess" value=""/>
                        <c:if test ="${idVisitor == visitor.visitorID}">
                            <c:set var="classSucess" value="info"/>
                        </c:if>
                        <tr class="${classSucess}">
                            <td>${visitor.visitorID}</td>
                            <td>${visitor.visitorLogin}</td>
                            <td>${visitor.visitorRole}</td>
                            <td>${visitor.visitorName}</td>
                            <td>

                            </td>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

        </c:choose>
    </form>
    <br>
        <a href="/views/adminView/registerPage.jsp"><button type="submit" class="btn btn-primary btn-md"><fmt:message key="admin.new.visitor" bundle="${rb}"/></button>
            <a href="/views/adminView/editVisitor.jsp"><button type="submit" class="btn btn-primary btn-md"><fmt:message key="admin.rdit.visitor" bundle="${rb}"/></button></a>
            <a href="/views/adminView/deleteVisitor.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="admin.delete.visitor" bundle="${rb}"/></button></a>
            <br>
            <br>
            <a href="/views/adminView/adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>
            <br><br>


            <form action="${pageContext.request.contextPath}/VisitorServlet" method="post">
                <button type="submit" class="btn btn-info"><fmt:message key="logout.button" bundle="${rb}"/></button>
            </form>


            <a href="adminMainPage.jsp"></a>


    <%@include file="/views/commonView/footer.jsp"%>

