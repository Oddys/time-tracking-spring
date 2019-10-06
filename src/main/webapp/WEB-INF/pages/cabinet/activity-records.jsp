<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="activityRecordsPage" type="org.oddys.timetrackingspring.dto.ActivityRecordsPage"--%>
<%--@elvariable id="targetUser" type="org.oddys.timetrackingspring.dto.UserDto"--%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="title.activity.records"/></title>
</head>
<body>
    <h2>
        <c:if test="${not empty activityRecordsPage.elements}">
            <fmt:message key='title.user.activity.records'>
                <fmt:param value='${activityRecordsPage.elements[0].userFirstName}'/>
                <fmt:param value='${activityRecordsPage.elements[0].userLastName}'/>
                <fmt:param value='${activityRecordsPage.elements[0].activityName}'/>
            </fmt:message>
        </c:if>
    </h2>
    <c:if test="${user.roleName eq 'USER' and activityRecordsPage.assigned}">
        <%@ include file="/WEB-INF/jspf/add-activity-record.jspf"%>
    </c:if>
    <table class="table table-hover table-striped table-bordered">
        <tr>
            <th><fmt:message key="table.column.date"/></th>
            <th><fmt:message key="table.column.duration"/></th>
<%--            <th><fmt:message key="status"/></th>--%>
<%--            <c:if test="${user.roleName eq 'USER' and userActivityAssigned}">--%>
<%--                <th></th>--%>
<%--                <th></th>--%>
<%--            </c:if>--%>
        </tr>
        <c:forEach items="${activityRecordsPage.elements}" var="record">
            <tr>
                <td>${record.activityDate}</td>
                <td>${record.duration}</td>
<%--                <td>${record.userActivityStatusChangeRequested}</td>--%>
<%--                <c:if test="${user.roleName eq 'USER' and userActivityAssigned}">--%>
<%--                    <td>Edit</td>  <!-- TODO Implement -->--%>
<%--                    <td>Delete</td>  <!-- TODO Implement -->--%>
<%--                </c:if>--%>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty activityRecordsPage.elements}">
        <ul class="pagination pagination-lg px-1 py-1">
            <c:if test="${activityRecordsPage.currentPage != 0}">
                <li class="page-item">
                    <span class="border px-2 py-1">
                        <a href="${pageContext.request.contextPath}/cabinet/activity-records?userActivityAssigned=${activityRecordsPage.assigned}&userActivityId=${activityRecordsPage.userActivityId}&rowsPerPage=${activityRecordsPage.rowsPerPage}&currentPage=${activityRecordsPage.currentPage-1}">
                        <fmt:message key="nav.previous"/>
                        </a>
                    </span>
                </li>
            </c:if>
            <c:forEach begin="0" end="${activityRecordsPage.numPages-1}" var="i">
                <c:choose>
                    <c:when test="${activityRecordsPage.currentPage eq i}">
                        <li class="page-item">
                            <span class="border px-2 py-1">
                                    ${i+1}
                            </span>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item">
                            <span class="border px-2 py-1">
                                <a href="${pageContext.request.contextPath}/cabinet/activity-records?userActivityAssigned=${activityRecordsPage.assigned}&userActivityId=${activityRecordsPage.userActivityId}&rowsPerPage=${activityRecordsPage.rowsPerPage}&currentPage=${i}">${i+1}</a>
                            </span>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${activityRecordsPage.currentPage lt activityRecordsPage.numPages-1}">
                <li class="page-item">
                    <span class="border px-2 py-1">
                        <a href="${pageContext.request.contextPath}/cabinet/activity-records?userActivityAssigned=${activityRecordsPage.assigned}&userActivityId=${activityRecordsPage.userActivityId}&rowsPerPage=${activityRecordsPage.rowsPerPage}&currentPage=${activityRecordsPage.currentPage+1}">
                        <fmt:message key="nav.next"/>
                    </a>
                    </span>
                </li>
            </c:if>
        </ul>
    </c:if>
    <form action="user-activities">
        <input type="hidden" name="userId" value="${user.userId}"/>
        <input type="hidden" name="firstName" value="${user.firstName}"/>
        <input type="hidden" name="lastName" value="${user.lastName}"/>
        <input type="submit" value="<fmt:message key="button.back.to.activities"/>" class="btn btn-secondary"/>
    </form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
