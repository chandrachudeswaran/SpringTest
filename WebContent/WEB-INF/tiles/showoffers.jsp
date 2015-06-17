<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="offers">
<tr><td>Name</td><td>Email</td><td>Offer</td></tr>
<c:forEach var="offers" items="${Offers}">
<tr>
<td><c:out value="${offers.name}"></c:out></td>
<td><c:out value="${offers.email}"></c:out></td>
<td><c:out value="${offers.text}"></c:out></td>
</tr>
</c:forEach>
</table>
