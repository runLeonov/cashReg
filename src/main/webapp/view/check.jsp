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
    <title>Check</title>
    <style>
        <%@include file="/styles/style.css"%>
        <%@include file="/styles/headstyle.css"%>
    </style>
</head>
<body class="mainContainer">
<jsp:include page="header.jsp" flush="true"/>
<div class="createCheck">
    <form method="post" class="formToCreate">
        <c:if test="${not empty wrongInput}"><fmt:message key="check.wronginput"/> <br></c:if>
        <c:if test="${not empty notFound}"><fmt:message key="check.notfound"/> <br></c:if>
        <c:if test="${not empty notEnough}"><fmt:message key="check.not_enough"/> <br></c:if>
        <fmt:message key="check.form.write.id"/>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input id="prod_ID" type="number" min="0" name="prodId"><br>
        <fmt:message key="check.form.write.name"/>:&nbsp;&nbsp;
        <input id="prod_NAME" type="text"  name="prodName"><br>
        <fmt:message key="check.form.write.weight"/>:
        <input type="number" min="0" step="0.1" name="weight"> <br>
        <input type="submit" size="123" name="btnAddToCheck" value=<fmt:message key="check.from.button.add"/>> <br>
        <input type="submit" size="123" name="btnCreateCheck" value=<fmt:message key="check.from.button.create"/>> <br>
        <input type="submit" size="123" name="clearCheck" value=<fmt:message key="check.from.button.delete"/>> <br>
        <c:if test="${not empty checkCreated}"><fmt:message key="check.created"/> <br></c:if>
    </form>
</div>
<div class="prodslist">
    <table class="table-border">
        <col width="50" valign="top">
        <col width="50" valign="top">
        <col width="250" valign="top">
        <col width="50" valign="top">
        <col width="250" valign="top">
        <tr bgcolor="#bd4a5b" >
            <th height="40" class="col"><fmt:message key="check.table.header.number"/> </th>
            <th height="40" class="col"><fmt:message key="check.table.header.id"/></th>
            <th height="40" class="col"><fmt:message key="check.table.header.name"/></th>
            <th height="40" class="col"><fmt:message key="check.table.header.weight"/></th>
            <th height="40" class="col"><fmt:message key="check.table.header.price"/></th>
            <th height="40" class="col"><fmt:message key="check.table.header.total_price"/></th>
        </tr>
        <c:forEach items="${sessionScope.productsInCheck}" var="prod" varStatus="counter">
            <tr bgcolor="#bd4a5b">
                <td>${counter.count}</td>
                <td>${prod.product.id}</td>
                <td>${prod.product.nameOfProd}</td>
                <td>${prod.weightOrCount}</td>
                <td>${prod.product.price}</td>
                <td>${prod.totalPrice}</td>
            </tr>
        </c:forEach>
        <tr bgcolor="#bd4a5b">
            <td style="text-align: right" colspan="3">
                <c:if test="${sessionScope.productsInCheck.size() > '0'}">
                    <fmt:message key="check.table.footer.count_of_prods"/>
                    ${sessionScope.productsInCheck.size()}
                </c:if>
                <c:if test="${sessionScope.productsInCheck.size() == '0' || sessionScope.productsInCheck.size() == null}">
                    <fmt:message key="check.table.footer.count_of_prods"/> 0
                </c:if>
            </td>
            <td style="text-align: right" colspan="3">
                <c:if test="${sessionScope.productsInCheck.size() > '0'}">
                    <fmt:message key="check.table.footer.total_price_of_check"/>
                    ${sessionScope.sumOfProds}
                </c:if>
                <c:if test="${sessionScope.productsInCheck.size() == '0' ||
                                sessionScope.productsInCheck == null}">
                    <fmt:message key="check.table.footer.total_price_of_check"/>0
                </c:if></td>
        </tr>
    </table>
</div>
<script>
    let id_code = document.getElementById("prod_ID");
    let id_name = document.getElementById("prod_NAME");
    if (id_code != null) {
        id_code.addEventListener("input", function(){
            if (id_code.value != null) {
                id_name.value = null;
            }
        });
        id_name.addEventListener("input", function(){
            if (id_name.value != null) {
                id_code.value = null;
            }
        });
    }
</script>
</body>
</html>