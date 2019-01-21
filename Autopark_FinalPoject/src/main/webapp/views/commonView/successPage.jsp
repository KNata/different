<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/22/18
  Time: 6:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="header.jsp"%>
<br>
<br>
<div class="container-fluid">
    <div class="alert alert-success" role="alert">
        An item was added successfully.
    </div>
    <br>
    <br>
    <a href="/views/userView/adminMainPage.jsp"><button type="submit" class="btn btn-secondary btn-md"><fmt:message key="back.to.main.page" bundle="${rb}"/></button></a>

    <br>
    <br>
    <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
        <button type="submit" class="btn btn-success btn-md">Logout</button>
    </form>


</div>

<%@include file="footer.jsp"%>

