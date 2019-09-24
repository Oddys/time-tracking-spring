<%--@elvariable id="activities" type="java.util.List"--%>
<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="currentPage" type="long"--%>
<%--@elvariable id="rowsPerPage" type="int"--%>
<%--@elvariable id="numPages" type="long"--%>
<%--@elvariable id="messageKey" type="String"--%>
<%--@elvariable id="activityName" type="String"--%>
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
        </fmt:message>
    </c:if>
    <c:if test="${user.roleName eq 'ADMIN'}">
        <h3><fmt:message key="title.activity.add"/> </h3>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="add_activity">
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
        <c:forEach items="${activities}" var="activity">
            <tr>
                <td>${activity.name}</td>
                <c:if test="${user.roleName eq 'USER'}">
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="assign_activity"/>
                                <%--                                <input type="hidden" name="action" value="add"/>--%>
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
    <c:if test="${not empty activities}">
        <ul>
            <c:if test="${currentPage != 0}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${currentPage-1}">
                        <fmt:message key="nav.previous"/>
                    </a>
                </li>
            </c:if>
            <c:forEach begin="0" end="${numPages-1}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li>${i+1}</li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/controller?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${i}">${i+1}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt numPages-1}">
                <li>
                    <a href="${pageContext.request.contextPath}/controller?command=show_activities&rowsPerPage=${rowsPerPage}&currentPage=${currentPage+1}">
                        <fmt:message key="nav.next"/>
                    </a>
                </li>
            </c:if>
        </ul>
    </c:if>
    <form action="${pageContext.request.contextPath}">
        <input type="submit" value="<fmt:message key="button.main"/>"/>
    </form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
