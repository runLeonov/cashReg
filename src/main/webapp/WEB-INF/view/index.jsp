<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>

<html>
<head>
    <title>Login</title>
    <style>
        <%@include file="/styles/style.css"%>
        <%@include file="/styles/headstyle.css"%>
    </style>
</head>
<body class="mainContainer">
<jsp:include page="header.jsp" flush="true"/>
<div class="log_form">
    <form method="POST">
        <div>
            <c:if test="${not empty userNotExists}"><fmt:message key="authorization.not_found"/> <br></c:if>
            <div> <fmt:message key="naming.email" /> <br> <input type="email" placeholder="Email" name="email" size="40" autofocus required/></div>
            <div> <fmt:message key="naming.password" /> <br> <input type="password" placeholder="Password" name="password" size="40" required/></div>
        </div>
        <br>
        <div><input type="submit" name="btnLogin" style="width: 35%" value=<fmt:message key="authorization.login.button" /> size="200" class="btn"></div>
    </form >
    <div><a href="registration" class="btn"><fmt:message key="authorization.registration.button" /></a></div>
</div>
</body>
</html>
