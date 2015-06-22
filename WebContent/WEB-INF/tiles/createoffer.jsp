<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     
     <script type="text/javascript">

     function onDelete(event){
    	 event.preventDefault();
    	 
    	 var dodelete = confirm("Are you sure you want to delete the offer");
    	 
    	 if(doDelete== false){
    		 event.preventDefault();
    	 }
     }
function onReady(){
	$("#delete").click(onDelete);
}


$(document).ready(onReady);
</script>

<sf:form name="offercreation" method="post" action="${pageContext.request.contextPath}/doCreate" commandName="offer">
<sf:input type="hidden" path="id" name="id" />
<table class="formtable">



<tr><td class="label">Text:<sf:textarea rows="10" cols="10" name="text" path="text" class="control"></sf:textarea><br/><sf:errors path="text" cssClass="error"></sf:errors>
<tr><td class="label"><input type="submit" value="Submit" class="control"></td></tr>

<c:if test="${offer.id !=0}">
<tr><td class="label">&nbsp;</td></tr>
<tr><td class="label"><input type="submit" value="Delete Offer" id="delete" class="delete" name="delete" class="control"></td></tr>
</c:if>


</table>
</sf:form>
