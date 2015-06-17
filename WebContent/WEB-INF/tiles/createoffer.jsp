<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<sf:form name="offercreation" method="post" action="${pageContext.request.contextPath}/doCreate" commandName="offer">
<table class="formtable">

<tr><td class="label">Name:<sf:input type="text" name="name" path="name" class="control"/><br/><sf:errors path="name" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Email:<sf:input type="text" name="email" path="email" class="control"/><br/><sf:errors path="email" cssClass="error"></sf:errors></td></tr>
<tr><td class="label">Text:<sf:textarea rows="10" cols="10" name="text" path="text" class="control"></sf:textarea><br/><sf:errors path="text" cssClass="error"></sf:errors>
<tr><td class="label"><input type="submit" value="Submit" class="control"></td></tr>

</table>
</sf:form>
