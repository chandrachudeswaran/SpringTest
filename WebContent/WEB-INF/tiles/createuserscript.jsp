<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<script type="text/javascript">
	function onLoad() {
		$("#password").keyup(checkpasswordsmatch);
		$("#confirmpassword").keyup(checkpasswordsmatch);

		$("#usercreation").submit();

	}

	function canSubmit() {
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password != confirmpassword) {
			alert("<fmt:message key='UnMatchedPasswords.user.password'/>");
			return false;
		}

		else {
			return true;
		}

	}

	function checkpasswordsmatch() {
		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password.length > 3 || confirmpassword.length > 3) {

			if (password == confirmpassword) {
				$("#matchpass").text(
						"<fmt:message key='MatchedPasswords.user.password'/>");
				$("#matchpass").addClass("valid");
				$("#matchpass").removeClass("error");
			} else {
				$("#matchpass")
						.text(
								("<fmt:message key='UnMatchedPasswords.user.password'/>"));
				$("#matchpass").addClass("error");
				$("#matchpass").removeClass("valid");
			}
		}

	}

	$(document).ready(onLoad);
</script>