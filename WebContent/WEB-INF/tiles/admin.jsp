<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="formtable">
<tr><td>Username</td><td>Email</td><td>Role</td><td>Enabled</td></tr>
<c:forEach var="users" items="${users}">
<tr>
<td><c:out value="${users.username}"></c:out></td>
<td><c:out value="${users.email}"></c:out></td>
<td><c:out value="${users.authority}"></c:out></td>
<td><c:out value="${users.enabled}"></c:out></td>
</tr>
</c:forEach>
</table>
