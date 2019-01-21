<%--
  Created by IntelliJ IDEA.
  User: nataliakiselyk
  Date: 12/29/18
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page session="true"%>
${sessionScope}

<c:set var="lang" scope="session"
       value="${empty sessionScope.locale ? 'uk_UK' : sessionScope.locale}" />

<fmt:setLocale value="${lang}" scope="session" />
<fmt:setBundle basename="i18n.language" var="rb" />


<html lang=${lang}>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>



<div class="container-fluid">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#"> <fmt:message key="header.title.message" bundle="${rb}"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <form>
            <select id="lang" name="lang" onchange="submit()">
                <option value="en" ${lang == 'en' ? 'selected' : ''}>English</option>
                <option value="uk" ${lang == 'uk' ? 'selected' : ''}>Ukrainian</option>
            </select>
        </form>

    </nav>
</div>
<body>



