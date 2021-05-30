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
        <h4><fmt:message key="products.form.add.add_new_product"/> </h4>
        <c:if test="${not empty wrongInputAdd}"><fmt:message key="products.message.wrong_input_add"/> <br></c:if>
        <c:if test="${not empty prodAlreadyExist}"><fmt:message key="products.message.product_exist"/> <br></c:if>
        <fmt:message key="products.form.add.write_name_of"/> <br>
        <input type="text" name="newProdName"><br>
        <fmt:message key="products.form.add.write_price"/> <br>
        <input type="number" min="0" name="prodPrice"><br>
        <fmt:message key="products.form.add.write_weight"/> <br>
        <input type="number" min="0" step="0.1" name="newWeight"> <br>
        <input type="submit" size="123" name="btnAddNewProduct" value=<fmt:message key="products.form.add.btn"/>> <br>
    </form>
</div>
<br>
<div class="createCheck">
    <form method="post" class="formToCreate">
        <h4><fmt:message key="products.form.delete.delete"/></h4>
        <c:if test="${not empty wrongInputDelete}"><fmt:message key="products.message.wrong_input_add"/> <br></c:if>
        <c:if test="${not empty notFoundDelete}"><fmt:message key="products.message.not_found_by_id"/> <br></c:if>
        <fmt:message key="products.form.delete.write_id"/> <br>
        <input type="number" min="0" name="deleteId"> <br>
        <input type="submit" size="123" name="btnDeleteProduct" value=<fmt:message key="products.form.delete.btn"/> <br>
    </form>
</div>
<br>
<div class="createCheck">
    <form method="post" class="formToCreate">
        <h4><fmt:message key="products.form.update"/></h4>
        <c:if test="${not empty wrongInputUpdate}"><fmt:message key="products.message.wrong_input_add"/> <br></c:if>
        <c:if test="${not empty notFoundUpdate}"><fmt:message key="products.message.not_found_by_id"/> <br></c:if>
        <fmt:message key="products.form.update.write_id"/><br>
        <input type="number" min="0" name="idEd"> <br>
        <fmt:message key="products.form.update.write_price"/><br>
        <input type="number" min="0" step="0.1" name="newProdPriceEd"> <br>
        <fmt:message key="products.form.update.write_weight"/><br>
        <input type="number" min="0" step="0.1" name="newWeightEd"> <br>
        <input type="submit" size="123" name="btnUpdateInStore" value=<fmt:message key="products.form.update.btn"/>> <br>
    </form>
</div>
<div class="prodslist">
    <table class="table-border">
        <col width="90" valign="top">
        <col width="250" valign="top">
        <col width="100" valign="top">
        <col width="100" valign="top">
        <tr>
            <td style="text-align: center"><fmt:message key="products.table.product_id"/></td>
            <td style="text-align: center"><fmt:message key="products.table.product_name"/></td>
            <td style="text-align: center"><fmt:message key="products.table.product_price"/></td>
            <td style="text-align: center"><fmt:message key="products.table.product_weight"/></td>
        </tr>
        <c:forEach items="${sessionScope.productsInStore}" var="prod" varStatus="counter">
            <tr>
                <td style="text-align: center">${prod.id}</td>
                <td>${prod.product.nameOfProd}</td>
                <td>${prod.product.price}</td>
                <td>${prod.weightOrCount}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>