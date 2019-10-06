<%--@elvariable id="user" type="org.oddys.timetrackingspring.dto.UserDto"--%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="title.main"/> - <fmt:message key="title.cabinet"/></title>
</head>
<body>
    <div class="my-5">
        <h2 class="h2"><fmt:message key="title.cabinet"/></h2>
        <p>
            <fmt:message key="greet.user">
                <fmt:param value="${sessionScope.user.firstName}"/>
                <fmt:param value="${sessionScope.user.lastName}"/>
            </fmt:message>
        </p>
        <form action="signout">
            <input type="submit" value="<fmt:message key="button.signout"/>" class="btn btn-primary">
        </form>
    </div>
    <c:choose>
        <c:when test="${user.roleName == 'ADMIN'}">
            <%@ include file="/WEB-INF/jspf/cabinet-admin.jspf"%>
        </c:when>
        <c:otherwise>
            <%@ include file="/WEB-INF/jspf/cabinet-user.jspf"%>
        </c:otherwise>
    </c:choose>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
