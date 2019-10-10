<%--@elvariable id="user" type="org.oddys.timetracking.dto.UserDto"--%>
<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="pageDto" type="org.oddys.timetrackingspring.dto.PageDto"--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="title.activities"/></title>
</head>
<body>
    <div class="text-info">${message}</div>
    <c:if test="${user.roleName eq 'ADMIN'}">
        <h3><fmt:message key="title.activity.add"/> </h3>
        <form action="${pageContext.request.contextPath}/cabinet/add-activity" method="post">
            <label for="activityName"></label>
            <input type="text" name="activityName" id="activityName" placeholder="<fmt:message key="activity.enter"/>" required/>
            <input type="submit" value="<fmt:message key="button.send"/>" class="btn btn-primary"/>
        </form>
    </c:if>
    <h3><fmt:message key="title.activities"/></h3>
    <table class="table table-hover table-striped table-bordered">
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
                        <form:form action="${pageContext.request.contextPath}/cabinet/request-user-activity" modelAttribute="userActivityDto" method="post">
                            <form:input type="hidden" path="assigned" value="false"/>
                            <form:input type="hidden" path="statusChangeRequested" value="true"/>
                            <form:input type="hidden" path="userId" value="${user.userId}"/>
                            <form:input type="hidden" path="userFirstName" value="${user.firstName}"/>
                            <form:input type="hidden" path="userLastName" value="${user.lastName}"/>
                            <form:input type="hidden" path="activityId" value="${activity.id}"/>
                            <form:input type="hidden" path="activityName" value="${activity.name}"/>
                            <input type="submit" value="<fmt:message key="table.column.add.to.my.activities"/>" class="btn btn-secondary"/>
                        </form:form>
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
    <form action="${pageContext.request.contextPath}/cabinet">
        <input type="submit" value="<fmt:message key="button.main"/>" class="btn btn-secondary"/>
    </form>
</body>
</html>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
