<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table class="offers">
<tr><td>Name</td><td>Email</td><td>Offer</td></tr>
<c:forEach var="offers" items="${Offers}">
<tr>
<td><c:out value="${offers.user.name}"></c:out></td>
<td><c:out value="${offers.user.email}"></c:out></td>
<td><c:out value="${offers.text}"></c:out></td>
</tr>
</c:forEach>
</table>
</p>
<c:choose>
<c:when test="${hasoffer}">
 <p><a href="${pageContext.request.contextPath}/createoffers">Edit or delete your offer</a></p>
</c:when>

<c:otherwise>
<p><a href="${pageContext.request.contextPath}/createoffers">Create new Offer</a></p>
</c:otherwise>

</c:choose>

 
 
 
 
 <sec:authorize access="hasRole('ROLE_ADMIN')">
 <p><a href="<c:url value='/admin'/>">Admin</a></p>
 
 </sec:authorize>




</body>
</html>