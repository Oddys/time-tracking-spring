<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="userActivities" type="java.util.List"--%>
<%--@elvariable id="errorMessageKey" type="java.lang.String"--%>
<%--@elvariable id="targetUser" type="org.oddys.timetrackingspring.dto.UserDto"--%>
<%--@elvariable id="activityRecordsPageRequestDto" type="org.oddys.timetrackingspring.dto.ActivityRecordsPageRequestDto"--%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>
        <fmt:message key="title.activities.user">
            <fmt:param value="${targetUser.firstName}"/>
            <fmt:param value="${targetUser.lastName}"/>
        </fmt:message>
    </title>
</head>
<body>
    <h2>
        <fmt:message key="title.activities.user">
            <fmt:param value="${targetUser.firstName}"/>
            <fmt:param value="${targetUser.lastName}"/>
        </fmt:message>
    </h2>
    <c:if test="${not empty messageKey}">
        <fmt:message key="${messageKey}"/>
    </c:if>
    <table>
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
                    <form:form action="show-activity-records" modelAttribute="activityRecordsPageRequestDto">
                        <form:input type="hidden" path="userActivityId" value="${currentUserActivity.userActivityId}"/>
                        <form:input type="hidden" path="userActivityAssigned" value="${currentUserActivity.assigned}"/>
                        <form:input type="hidden" path="currentPage" value="0"/>
                        <form:input type="hidden" path="rowsPerPage" value="5"/>
                        <input type="submit" value="<fmt:message key="button.show"/>"/>
                    </form:form>
                </td>
                <c:if test="${user.roleName eq 'USER'}">
                    <td>
                        <c:if test="${currentUserActivity.assigned and not currentUserActivity.statusChangeRequested}">
                            <form action="controller" method="post">
                                <input type="hidden" name="command" value="stop_activity"/>
                                <input type="hidden" name="userActivityId" value="${currentUserActivity.userActivityId}"/>
                                <input type="submit" value="Stop activity"/>
                            </form>
                        </c:if>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <form action="${pageContext.request.contextPath}">
        <input type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
