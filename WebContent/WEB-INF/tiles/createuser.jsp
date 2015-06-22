<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
    
    <%@ taglib prefix="fmt" 
           uri="http://java.sun.com/jsp/jstl/fmt" %>


<h2>Create User Account</h2>
<sf:form name="usercreation" method="post" action="${pageContext.request.contextPath}/createuser" commandName="user">
<table class="formtable">

<tr><td class="label">Username:<sf:input type="text" name="username" path="username" class="control"/><br/><sf:errors path="username" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Name:<sf:input type="text" name="name" path="name" class="control"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Email:<sf:input type="text" name="email" path="email" class="control"/><br/><sf:errors path="email" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Password:<sf:input id = "password" type="password" name="password" path="password" class="control"/><br/><sf:errors path="password" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Confirm Password:<input type="password" id="confirmpassword" name="confirmpass" class="control"/><div id="matchpass"></div></td></tr>
<tr><td class="label"><input type="submit" value="Register" class="control"></td></tr>

</table>
</sf:form>
