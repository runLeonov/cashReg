<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="lang"/>
<header>
    <nav class="headerContainer">
        <ul class="topmenu">
            <c:if test="${not empty sessionScope.user}">
            <li><a  class="down"><i class="fa fa-angle-down"> </i>${sessionScope.user.name}</a>

                <c:if test="${sessionScope.user.userRoleId == '3'}">
            <li><a href="products"><i class="fa fa-angle-down"> </i>
                <fmt:message key="header.button.products"/></a>
                </c:if>

                <c:if test="${sessionScope.user.userRoleId == '2'}">
            <li><a href="cancel"><i class="fa fa-angle-down"> </i>
                <fmt:message key="header.button.cancel_check"/></a>
                </c:if>

                <c:if test="${sessionScope.user.userRoleId == '1' || sessionScope.user.userRoleId == '2'}">
            <li><a href="check"><i class="fa fa-angle-down"> </i>
                <fmt:message key="header.button.open_check"/></a>
                </c:if>

            <li><a href="logout"><i class="fa fa-angle-down"> </i>
                <fmt:message key="header.button.logout"/></a>

                </c:if>

                <c:if test="${empty sessionScope.user}">
            <li><a href="login" class="down"><i class="fa fa-angle-down"> </i>
                <fmt:message key="header.button.login"/></a>
                </c:if>



            <li><a><fmt:message key="header.button.language"/> <i class="fa fa-angle-down"> </i> </a>
                <ul class="submenu">
                    <li><a href="?lang=ru">Русский</a></li>
                    <li><a href="?lang=ua">Українська</a></li>
                    <li><a href="?lang=en">English</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</header>
