<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%--@elvariable id="pageDto" type="org.oddys.timetrackingspring.dto.PageDto"--%>
<html>
<head>
    <title>User Activity Requests</title>
</head>
<body>
    URL: ${pageContext.request.requestURL}<br/>
    URI: ${pageContext.request.requestURI}<br/>
    PATH: ${pageContext.request.servletPath}<br/>
    QUERY: ${pageContext.request.queryString}<br/>
<c:if test="${not empty messageKey}">
    <fmt:message key="${messageKey}"/>
    <c:remove var="messageKey" scope="session"/>
</c:if>
<table class="table table-hover table-striped table-bordered">
    <tr>
        <th><fmt:message key="table.column.user.id"/></th>
        <th><fmt:message key="table.column.user.firstname"/></th>
        <th><fmt:message key="table.column.user.lastname"/></th>
        <th><fmt:message key="table.column.activity"/></th>
        <th><fmt:message key="table.column.action"/> </th>
    </tr>
    <c:forEach items="${pageDto.elements}" var="userActivity">
        <tr>
            <td>${userActivity.userId}</td>
            <td>${userActivity.userFirstName}</td>
            <td>${userActivity.userLastName}</td>
            <td>${userActivity.activityName}</td>
            <td>
                <form action="${pageContext.request.contextPath}/cabinet/change-user-activity-status" method="post">
                    <input type="hidden" name="userActivityId" value="${userActivity.userActivityId}"/>
                    <input type="hidden" name="currentAssigned" value="${userActivity.assigned}"/>
                    <c:choose>
                        <c:when test="${userActivity.assigned}">
                            <input type="submit" value="<fmt:message key="button.activity.stop"/>"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="<fmt:message key="button.user.activity.assign"/> "/>
                        </c:otherwise>
                    </c:choose>
                </form>
            </td>
        </tr>
    </c:forEach>
</table>


<c:if test="${not empty pageDto.elements}">
    <nav>
        <ul class="pagination pagination-lg px-1 py-1">
            <c:if test="${pageDto.currentPage != 0}">
                <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/user-activity-requests?rowsPerPage=${pageDto.rowsPerPage}&currentPage=${pageDto.currentPage-1}">
                                <fmt:message key="nav.previous"/>
                            </a>
                        </span>
                </li>
            </c:if>
            <c:forEach begin="0" end="${pageDto.numPages-1}" var="i">
                    <span class="border px-2 py-1">
                        <c:choose>
                            <c:when test="${pageDto.currentPage eq i}">
                                <li class="page-item">${i+1}</li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a href="${pageContext.request.contextPath}/cabinet/user-activity-requests?rowsPerPage=${pageDto.rowsPerPage}&currentPage=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </span>
            </c:forEach>
            <c:if test="${pageDto.currentPage lt pageDto.numPages-1}">
                <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/user-activity-requests?rowsPerPage=${pageDto.rowsPerPage}&currentPage=${pageDto.currentPage+1}">
                                <fmt:message key="nav.next"/>
                            </a>
                        </span>
                </li>
            </c:if>
        </ul>
    </nav>
</c:if>
<form action="${pageContext.request.contextPath}">
    <input type="submit" value="<fmt:message key="button.main"/>"/>
</form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
