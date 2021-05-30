<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>

<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="contentBundle" />

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title></title>
    <style>
        <%@include file="/styles/style.css"%>
        <%@include file="/styles/headstyle.css"%>
    </style>
</head>
<body>
<jsp:include page="header.jsp" flush="true" />
<c:if test="${pageContext.errorData.statusCode == 404}">
    <h4></h4>
    <div align="center"><a href=""></a></div>
</c:if>
<c:if test="${pageContext.errorData.statusCode != 404}">
    <h1></h1>
    Url: ${pageContext.errorData.requestURI} is failed  <br/>
    Servlet name: ${pageContext.errorData.servletName}  <br/>
    Status code: ${pageContext.errorData.statusCode}  <br/>
    Exception: ${pageContext.exception}  <br/>
    Message from exception: ${pageContext.exception.message}
</c:if>
</body>
</html>