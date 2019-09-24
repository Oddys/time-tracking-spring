<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
    <%@ include file="/WEB-INF/jspf/signin.jspf" %>
    <form action="form">
        <input type="hidden" name="activityName" value="Редагування"/>
        <input type="submit" value="Submit"/>
    </form>
    !${exists}!
    !${hello}!
    !${activityName}!
    Привіт, світ!
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
