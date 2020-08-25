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
		function checkUpdate() {
			let id = document.forms[0].id.value.trim();
			let password = document.forms[0].password.value.trim();
			if (id == '') {
				alert('아이디를 입력하세요!');
				document.forms[0].id.focus();
				return false;
			}
			if (password == '') {
				alert('비밀번호를 입력하세요!');
				document.forms[0].password.focus();
				return false;
			}
			location.href = 'updateInfo.do?id=' + id + '&password=' + password;
		}
	</script>
	<div align="center">
		<h1>
			<em><spring:message code="message.user.login.title"/></em>
		</h1>
		<a href="login.do?lang=en">
			<spring:message code="message.user.login.language.en" />
		</a>&nbsp;&nbsp;
		<a href="login.do?lang=ko">
			<spring:message code="message.user.login.language.ko" />
		</a>
		<hr />
		<form action="login.do" method="post">
			<table border="1" style="border-spacing: 0; border-collapse: collapse;">
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.id" /></td>
					<td><input type="text" name="id" value="${user.id}" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.password" /></td>
					<td><input type="password" name="password" value="${user.password}" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="<spring:message code="message.user.login.loginBtn" />" />
						<input type="button" value="<spring:message code="message.user.login.registBtn" />" onclick="location.href='register.do'" />
						<input type="button" value="<spring:message code="message.user.login.updateBtn" />" onclick="return checkUpdate()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>