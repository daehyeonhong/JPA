<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title><spring:message code="message.user.login.title"/></title>
<style type="text/css">
	input {
	border: 0px;
}
</style>
</head>
<body>
	<script type="text/javascript">
		function checkSubmit() {
			let password = document.forms[0].password.value;
			let confirmPassword = document.forms[0].confirmPassword.value;
			if (password != confirmPassword) {
				alert('비밀번호 확인 값이 서로 다릅니다.');
				document.forms[0].password.value = "";
				document.forms[0].confirmPassword.value = "";
				document.forms[0].password.focus();
				return false;
			}
		}
	</script>
	<div align="center">
		<h1>
			<em><spring:message code="message.user.regist.title"/></em>
		</h1>
		<a href="?lang=en">
			<spring:message code="message.user.login.language.en" />
		</a>&nbsp;&nbsp;
		<a href="?lang=ko">
			<spring:message code="message.user.login.language.ko" />
		</a>
		<hr />
		<form action="register.do" method="post" onsubmit="return checkSubmit()">
			<table border="1" style="border-spacing: 0; border-collapse: collapse;">
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.regist.id" /></td>
					<td><input type="text" name="id" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.regist.password" /></td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.regist.confirmPassword" /></td>
					<td><input type="password" name="confirmPassword" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.regist.name" /></td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="<spring:message code="message.user.regist.confirmBtn" />" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>