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
    <title>Title</title>
    <style>
        <%@include file="/styles/style.css"%>
        <%@include file="/styles/headstyle.css"%>
    </style>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div class="createCheck">
    <form method="post" class="formToCreate">
        <c:if test="${not empty checkNotFoundById}"><fmt:message key="cancel.check.not_found"/> <br></c:if>
        <c:if test="${not empty checkDeletedById}"><fmt:message key="cancel.check.deleted"/> <br></c:if>
        <h4> <fmt:message key="cancel.check.white_id"/> </h4>
        <input type="number" min="0" name="checkId"><br>
        <input type="submit" size="123" name="btnShowById" value=<fmt:message key="cancel.check.btn.find_by_id"/>> <br>
        <input type="submit" size="123" name="btnDeleteById" value=<fmt:message key="cancel.check.btn.delete_by_id"/>>
    </form>
</div>
<br>
<div class="createCheck">
    <form method="post" class="formToCreate">
        <c:if test="${not empty checkDeletedLast}"><fmt:message key="cancel.check.deleted_last"/> <br></c:if>
        <c:if test="${not empty checkNotFoundLast}"><fmt:message key="cancel.check.not_found_last"/> <br></c:if>
        <h4> <fmt:message key="cancel.check.show_or_delete_last"/> </h4>
        <input type="submit" size="123" name="btnShowLast" value=<fmt:message key="cancel.check.btn.show_last_check"/>> <br><br>
        <input type="submit" size="123" name="btnDeleteLastCheck" value=<fmt:message key="cancel.check.btn.delete_last_check"/>> <br>
    </form>
</div>
<br>
<div class="createCheck">
    <form method="post" class="formToCreate">
        <h4> <fmt:message key="cancel.check.create_reports"/> </h4>
        <input type="submit" size="123" name="btnCreateXReport" value=<fmt:message key="cancel.check.btn.create_x"/>> <br><br>
        <input type="submit" size="123" name="btnCreateXReport" value=<fmt:message key="cancel.check.btn.create_z"/>> <br>
    </form>
</div>
<div class="prodslist">
    <table class="table-border">
        <col width="50">
        <col width="250" valign="top">
        <col width="150" valign="top">
        <col width="100" valign="top">
        <tr bgcolor="#bd4a5b">
            <td height="40" colspan="4" style="text-align: center" class="col"><fmt:message
                    key="cancel.check.table.id_check"/>: ${sessionScope.checks.id}  </td>
        </tr>
        <tr bgcolor="#bd4a5b">
            <td height="40" class="col"><fmt:message key="cancel.check.table.id"/></td>
            <td height="40" class="col"><fmt:message key="cancel.check.table.name"/></td>
            <td height="40" class="col"><fmt:message key="cancel.check.table.weight"/></td>
            <td height="40" class="col"><fmt:message key="cancel.check.table.price"/></td>
        </tr>
        <c:forEach items="${sessionScope.products}" var="product" varStatus="counter">
            <tr bgcolor="#bd4a5b">
                <td style="text-align: right">${product.id}</td>
                <td style="text-align: center">${product.product.nameOfProd}</td>
                <td style="text-align: center">${product.weightOrCount}</td>
                <td>${product.totalPrice}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>