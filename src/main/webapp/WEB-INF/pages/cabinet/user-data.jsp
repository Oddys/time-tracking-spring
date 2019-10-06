<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%--@elvariable id="message" type="java.lang.String"--%>
<html>
<head>
    <title><fmt:message key="user.data"/> </title>
</head>
<body>
    <div class="text-info">${message}</div>
<h2><fmt:message key="title.add.user"/> </h2>
    <div style="width: 50%">
        <form:form action="${pageContext.request.contextPath}/cabinet/add-user" modelAttribute="userDto"
                   class="needs-validation" novalidate="true" method="post">
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="login"><fmt:message key="label.login"/> </label>
                    <form:input type="text" class="form-control" id="login" path="login" required="true"/>
                    <div class="text-warning">
                        <form:errors path="login"/>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="password"><fmt:message key="label.password"/></label>
                    <form:input type="password" class="form-control" id="password" path="password" required="true"/>
                    <div class="text-warning">
                        <form:errors path="password"/>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="firstName"><fmt:message key="label.firstname"/></label>
                    <form:input type="text" class="form-control" id="firstName" path="firstName" required="true"/>
                    <div class="text-warning">
                        <form:errors path="firstName"/>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <label for="lastName"><fmt:message key="label.lastname"/></label>
                    <form:input type="text" class="form-control" id="lastName" path="lastName" required="true"/>
                    <div class="text-warning">
                        <form:errors path="lastName"/>
                    </div>
                </div>
            </div>
            <div class="form-row">
                <div class="col-md-4 mb-3">
                    <label for="roleId"><fmt:message key="label.role"/></label>
                    <form:select class="custom-select form-inline" path="roleId" id="roleId">
                        <c:forEach var="role" items="${roles}">
                            <form:option value="${role.id}"><fmt:message key="${role.name}"/></form:option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
            <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
        </form:form>
    </div>
<form action="${pageContext.request.contextPath}">
    <input class="btn btn-secondary" type="submit" value="<fmt:message key="button.main"/>"/>
</form>
    <script>validate();</script>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
