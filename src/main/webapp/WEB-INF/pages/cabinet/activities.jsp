<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="activityName" type="String"--%>
<%--@elvariable id="pageDto" type="org.oddys.timetrackingspring.dto.PageDto"--%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="title.activities"/></title>
</head>
<body>
    <h2><fmt:message key="title.activities"/></h2>
    <c:if test="${not empty messageKey}">
        <fmt:message key="${messageKey}">
            <fmt:param value="${activityName}"/>
            <c:remove var="messageKey" scope="session"/>
            <c:remove var="activityName" scope="session"/>
        </fmt:message>
    </c:if>
    <c:if test="${user.roleName eq 'ADMIN'}">
        <h3><fmt:message key="title.activity.add"/> </h3>
        <form action="${pageContext.request.contextPath}/cabinet/add-activity" method="post">
            <label for="activityName"></label>
            <input type="text" name="activityName" id="activityName" placeholder="<fmt:message key="activity.enter"/>" required/>
            <input type="submit" value="<fmt:message key="button.send"/>"/>
        </form>
    </c:if>
    <table>
        <tr>
            <th><fmt:message key="table.column.name"/> </th>
            <c:if test="${user.roleName eq 'USER'}">
                <th><fmt:message key="table.column.action"/></th>
            </c:if>
        </tr>
        <c:forEach items="${pageDto.elements}" var="activity">
            <tr>
                <td>${activity.name}</td>
                <c:if test="${user.roleName eq 'USER'}">
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="assign_activity"/>
                            <input type="hidden" name="activityId" value="${activity.id}"/>
                            <input type="hidden" name="userId" value="${user.userId}"/>
                            <input type="hidden" name="activityName" value="${activity.name}"/>
                            <input type="submit" value="<fmt:message key="table.column.add.to.my.activities"/>"/>
                        </form>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${not empty pageDto.elements}">
        <nav>
            <ul class="pagination pagination-lg px-1 py-1">
                <c:if test="${pageDto.currentPage != 0}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/activities?rowsPerPage=${pageDto.rowsPerPage}&currentPage=${pageDto.currentPage-1}">
                                <fmt:message key="nav.previous"/>
                            </a>
                        </span>
                    </li>
                </c:if>
                <c:forEach begin="0" end="${pageDto.numPages-1}" var="i">
                    <c:choose>
                        <c:when test="${pageDto.currentPage eq i}">
                            <li class="page-item"><span class="border px-2 py-1">${i+1}</span></li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <span class="border px-2 py-1">
                                    <a href="${pageContext.request.contextPath}/cabinet/activities?rowsPerPage=${pageDto.rowsPerPage}&currentPage=${i}">${i+1}</a>
                                </span>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${pageDto.currentPage lt pageDto.numPages-1}">
                    <li class="page-item">
                        <span class="border px-2 py-1">
                            <a href="${pageContext.request.contextPath}/cabinet/activities?rowsPerPage=${pageDto.rowsPerPage}&currentPage=${pageDto.currentPage+1}">
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
