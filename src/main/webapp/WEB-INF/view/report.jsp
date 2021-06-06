<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<c:if test="${not empty param.language}">
    <c:set var="language" value="${param.language}" scope="session"/>
</c:if>
<c:if test="${empty param.language}">
    <c:set var="language" value="en" scope="session"/>
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
<c:if test="${not empty sessionScope.user
        and sessionScope.user.userRoleId == '2' and not empty sessionScope.reportX}">
    <div>
        <div style="margin-left: 30%; margin-right: 30%;
            font-size: 20px; text-align: center; width: 40%">
            <div style="text-align: center"><fmt:message key="report.report_name"/></div>
            <br>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.date_of_create"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.printTime}</div>
            </div>
            <br>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.count_of_checks"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.countChecks}</div>
            </div>
            <br>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.count_of_deleted_checks"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.countCancelChecks}</div>
            </div>
            <br>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.total_a"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.totalA}</div>
            </div>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.total_b"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.totalB}</div>
            </div>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.total_c"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.totalC}</div>
            </div>
            <br>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.total_sum"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.sumTotal}</div>
            </div>
            <br>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.nds_a"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.ndsTotalA}</div>
            </div>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.nds_b"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.ndsTotalB}</div>
            </div>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.nds_c"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.ndsTotalC}</div>
            </div>
            <br>
            <div>
                <div style="width: 49%; display: inline-grid; text-align: left">
                    <fmt:message key="report.nds_sum"/>
                </div>
                <div style="width: 49%; display: inline-grid; text-align: right">
                        ${sessionScope.reportX.sumNdsTotal}</div>
            </div>
            <a style="text-align: center" href="javascript:(print());"> <fmt:message key="report.print"/></a>
        </div>
    </div>
</c:if>
<c:if test="${not empty sessionScope.user
        and sessionScope.user.userRoleId == '2' and not empty sessionScope.reportZ}">
    <div style="margin-left: 30%; margin-right: 30%;
            font-size: 22px; text-align: center; width: 50%">
        <table border="2">
            <tr>
                <td style="font-size: 26px; text-align: center"  width="400" colspan="2"><fmt:message key="report.report_name_z"/></td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.date_of_create"/></td>
                <td style="text-align: right; font-size: 22px" width="200">${sessionScope.reportZ.printTime}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.count_of_checks"/></td>
                <td style="text-align: right; font-size: 22px" width="200">${sessionScope.reportZ.countChecks}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.count_of_deleted_checks"/></td>
                <td style="text-align: right; font-size: 22px" width="200">${sessionScope.reportZ.countCancelChecks}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.total_a"/></td>
                <td style="text-align: right; font-size: 22px" width="200">${sessionScope.reportZ.totalA}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.total_b"/></td>
                <td style="text-align: right; font-size: 22px" width="200">${sessionScope.reportZ.totalB}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.total_c"/></td>
                <td style="text-align: right; font-size: 22px" width="200">${sessionScope.reportZ.totalC}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.total_sum"/></td>
                <td style="text-align: right; font-size: 22px" width="200">${sessionScope.reportZ.sumTotal}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.nds_a"/></td>
                <td style="text-align: right; font-size: 22px" width="200"> ${sessionScope.reportZ.ndsTotalA}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.nds_b"/></td>
                <td style="text-align: right; font-size: 22px" width="200"> ${sessionScope.reportZ.ndsTotalB}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.nds_c"/></td>
                <td style="text-align: right; font-size: 22px" width="200"> ${sessionScope.reportZ.ndsTotalC}</td>
            </tr>
            <tr>
                <td style="font-size: 22px" width="200"><fmt:message key="report.nds_sum"/></td>
                <td style="text-align: right; font-size: 22px" width="200"> ${sessionScope.reportZ.sumNdsTotal}</td>
            </tr>
        </table>
        <a style="text-align: center" href="javascript:(print());"> <fmt:message key="report.print"/></a>
    </div>
</c:if>
</body>
</html>