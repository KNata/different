<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/19/18
  Time: 2:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="/views/commonView/header.jsp"%>

    <div class="container">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">About Autopark</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div align="center">Welcome to the Driver's page</div>


                <div class="collapse navbar-collapse" id="navbarSupportedContent">

                    <div align="right">
                        <jsp:useBean id="now" class="java.util.Date" />
                        <fmt:setLocale value="us-US"/>
                        <fmt:formatDate value="${now}"/>
                    </div>
                </div>
            </nav>
        </div>

        <a href="#" class="btn btn-danger btn-lg btn-block" role="button" aria-pressed="true">Pending Requests <span class="badge badge-light">4</span></a>

        <br>
        <br>

        <table class="table table-striped">
            <thead>
            <tr>
                <td>Route ID: </td>
                <td>Route name: </td>
                <td>Driver Name:</td>
                <td>Bus ID: </td>
                <td>Route ID: </td>
                <td>Route city of department: </td>
                <td>Route city of arrival: </td>
                <td>Route duration: </td>
                <td>Route time of department</td>
                <td>Route time of arrival</td>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Route ID: </td>
                <td>Route name: </td>
                <td>Driver Name:</td>
                <td>Bus ID: </td>
                <td>Route ID: </td>
                <td>Route city of department: </td>
                <td>Route city of arrival: </td>
                <td>Route duration: </td>
                <td>Route time of department</td>
                <td>Route time of arrival</td>
            </tr>

            </tbody>
        </table>

        <br><br>
        <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
            <button type="submit" class="btn btn-success btn-md">Logout</button>
        </form>

    </div>


    </div>
<%@include file="/views/commonView/footer.jsp"%>
