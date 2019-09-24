<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="title.main"/></title>
</head>
<body>
    <c:choose>
        <c:when test="${empty sessionScope.user}">
            <%@ include file="/WEB-INF/jspf/signin.jspf" %>
        </c:when>
        <c:otherwise>
            <jsp:forward page="/WEB-INF/pages/cabinet.jsp"/>
        </c:otherwise>
    </c:choose>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
