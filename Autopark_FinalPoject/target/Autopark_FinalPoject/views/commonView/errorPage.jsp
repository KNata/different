<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/21/18
  Time: 7:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>

<div class="container-fluid">
    <div class="alert alert-danger" role="alert">
        <fmt:message key="error.message" bundle="${rb}"/>
    </div>
<br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md"><fmt:message key="logout.button" bundle="${rb}"/></button>
    </form>
</div>

<%@include file="footer.jsp"%>
