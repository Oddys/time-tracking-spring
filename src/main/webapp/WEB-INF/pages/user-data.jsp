<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html lang="${not empty lang ? lang : 'en'}">
<html>
<head>
    <title>User data</title>
</head>
<body>
    <c:if test="${not empty messageKey}">
        ${messageKey}
        <c:set scope="session" var="messageKey" value=""/>
    </c:if>
    <form action="add-user" method="post">
        <label for="login">Login</label>
        <input type="text" name="login" value="" id="login" required/>
        <label for="password">Password</label>
        <input type="password" name="password" value="" id="password" required/>
        <label for="firstName">First name</label>
        <input type="text" name="firstName" value="" id="firstName" required/>
        <label for="lastName">Last name</label>
        <input type="text" name="lastName" value="" id="lastName" required/>
        <label for="role">Role</label>
<%--        FIXME USE data from roles table--%>
        <select name="role" id="role">
            <option value="User">User</option>
            <option value="Admin">Admin</option>
        </select>
        <input type="submit" value="<fmt:message key="button.save"/>"/>
    </form>
    <form action="main">
        <input type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
