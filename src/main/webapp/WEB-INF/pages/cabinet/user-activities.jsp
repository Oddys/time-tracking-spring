<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%--@elvariable id="user" type="org.oddys.timetrackingspring.dto.UserDto"--%>
<%--@elvariable id="userActivities" type="java.util.List"--%>
<%--@elvariable id="targetUser" type="org.oddys.timetrackingspring.dto.UserDto"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<html>
<head>
    <title>
        <fmt:message key="title.activities.user">
            <fmt:param value="${firstName}"/>
            <fmt:param value="${lastName}"/>
        </fmt:message>
    </title>
</head>
<body>
    <h2>
        <fmt:message key="title.activities.user">
            <fmt:param value="${firstName}"/>
            <fmt:param value="${lastName}"/>
        </fmt:message>
    </h2>
    <div class="text-info">${message}</div>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th><fmt:message key="title.activity"/></th>
            <th><fmt:message key="table.header.status"/> </th>
            <th></th>
            <c:if test="${user.roleName eq 'USER'}">
                <th></th>
            </c:if>
        </tr>
        <c:forEach var="currentUserActivity" items="${userActivities}">
            <tr>
                <td><c:out value="${currentUserActivity.activityName}"/></td>
                <td>
                    <c:choose>
                        <c:when test="${currentUserActivity.assigned and not currentUserActivity.statusChangeRequested}">
                            <fmt:message key="table.column.assigned"/>
                        </c:when>
                        <c:when test="${not currentUserActivity.assigned and currentUserActivity.statusChangeRequested}">
                            <fmt:message key="table.column.assign.wait"/>
                        </c:when>
                        <c:when test="${currentUserActivity.assigned and currentUserActivity.statusChangeRequested}">
                            <fmt:message key="table.column.cancel.wait"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="table.column.notassigned"/>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form:form action="activity-records" modelAttribute="activityRecordsPageRequestDto" method="get">
                        <form:input type="hidden" path="userActivityId" value="${currentUserActivity.userActivityId}"/>
                        <form:input type="hidden" path="userActivityAssigned" value="${currentUserActivity.assigned}"/>
                        <form:input type="hidden" path="currentPage" value="0"/>
                        <form:input type="hidden" path="rowsPerPage" value="5"/>
                        <input type="submit" value="<fmt:message key="button.show"/>" class="btn btn-secondary"/>
                    </form:form>
                </td>
                <c:if test="${user.roleName eq 'USER'}">
                    <td>
                        <c:if test="${currentUserActivity.assigned and not currentUserActivity.statusChangeRequested}">
                            <form action="${pageContext.request.contextPath}/cabinet/stop-activity" method="post">
                                <input type="hidden" name="userActivityId" value="${currentUserActivity.userActivityId}"/>
                                <input type="hidden" name="userId" value="${userId}"/>
                                <input type="hidden" name="firstName" value="${firstName}"/>
                                <input type="hidden" name="lastName" value="${lastName}"/>
                                <input type="submit" value="<fmt:message key="button.activity.stop"/>" class="btn btn-secondary"/>
                            </form>
                        </c:if>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}/cabinet">
        <input type="submit" value="<fmt:message key="button.main"/>" class="btn btn-secondary"/>
    </form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
