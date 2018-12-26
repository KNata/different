<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/25/18
  Time: 7:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/bootstrap.min.js"></script>
</head>

<body>
<div class="container">
    <h2>Visitor</h2>
    <!--Search Form -->
    <form action="/VisitorServlet" method="get" id="seachVisitorForm" role="form">
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName">
        <div class="form-group col-xs-5">
            <input type="text" name="visitorName" id="visitorName" class="form-control" required="true" placeholder="Type the Name of the Visitor"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>

    </form>

    <!--Visitor List-->
    <c:if test="${not empty message}">
        <div class="alert alert-success">
                ${message}
        </div>
    </c:if>
    <form action="/VisitorServlet" method="post" id="visitorForm" role="form" >
        <input type="hidden" id="idVisitor" name="idVisitor">
        <input type="hidden" id="action" name="action">
        <c:choose>
            <c:when test="${not empty visitorList}">
                <table  class="table table-striped">
                    <thead>
                    <tr>
                        <td>Visitor ID: </td>
                        <td>Visitor login: </td>
                        <td>Visitor password: </td>
                        <td>Visitor role: </td>
                        <td>Visitor name: </td>
                    </tr>
                    </thead>
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
                            <td>${visitor.password}</td>
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
    <form action ="jsp/addNewVisitor.jsp">

        <button type="submit" class="btn btn-primary  btn-md">New Visitor</button>
    </form>
</div>
</body>
