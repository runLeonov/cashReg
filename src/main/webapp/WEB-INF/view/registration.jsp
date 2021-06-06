<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>
<html>
<head>
    <title>Title</title>
    <style>
        <%@include file="/styles/style.css"%>
        <%@include file="/styles/headstyle.css"%>
    </style>
    <script>
        function check_pass() {
            if (document.getElementById('id_pass').value ===
                document.getElementById('id_pass_confirm').value) {
                document.getElementById('id_btnReg').disabled = false;
            } else {
                document.getElementById('id_btnReg').disabled = true;
            }
        }
    </script>
</head>
<body class="mainContainer">
<jsp:include page="header.jsp" flush="true"/>
<div class="log_form">
    <form method="post">
        <div>
            <c:if test="${not empty existsLogin}"><fmt:message key="authorization.already_exist"/> <br></c:if>
            <div> <fmt:message key="authorization.write.name"/>: <br>
                <input  type="text" placeholder=<fmt:message key="authorization.name"/> name="name" size="40" autofocus required/>
            </div>
            <div> <fmt:message key="authorization.write.email"/>: <br>
                <input type="email" placeholder="Email" name="email" size="40" required/>
            </div>
            <div> <fmt:message key="authorization.write.password"/>: <br>
                <input id="id_pass" type="password" placeholder=<fmt:message key="authorization.password"/> name="password" size="40"
                       required oninput='check_pass();'/>
            </div>
            <div> <fmt:message key="authorization.write.password_repeat"/>: <br>
                <input id="id_pass_confirm" type="password" placeholder=<fmt:message key="authorization.password.repeat"/> name="password" size="40"
                       required oninput='check_pass();'/>
            </div>
            <br>
            <input id="id_btnReg" type="submit" class="btn" name="btnReg" value=<fmt:message key="authorization.registration.button"/> />
        </div>
    </form>
</div>
</body>
</html>