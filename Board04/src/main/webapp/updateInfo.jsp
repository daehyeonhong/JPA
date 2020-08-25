<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				document.forms[0].password.value = '';
				document.forms[0].confirmPassword.value = '';
				document.forms[0].password.focus();
				return false;
			}
		}

		function checkDelete() {
			let yesNo = confirm('정말 탈퇴하시겠습니까?');
			if (yesNo) {
				location.href = 'deleteUser.do?id=${user.id}';/* Method = GET */
			} else {
				alert('요청이 취소되었습니다.');
			}
		}

	</script>
	<div align="center">
		<h1>
			<em><spring:message code="message.user.updateInfo.title"/></em>
		</h1>
		<a href="?lang=en&id=${user.id}&password=${user.password}">
			<spring:message code="message.user.login.language.en" />
		</a>&nbsp;&nbsp;
		<a href="?lang=ko&id=${user.id}&password=${user.password}">
			<spring:message code="message.user.login.language.ko" />
		</a>
		<hr />
		<form action="updateInfo.do" method="post" onsubmit="return checkSubmit()">
			<table border="1" style="border-spacing: 0; border-collapse: collapse;">
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.updateInfo.id" /></td>
					<td><input type="text" name="id" value="${user.id}" readonly="readonly" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.updateInfo.password" /></td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.updateInfo.confirmPassword" /></td>
					<td><input type="password" name="confirmPassword" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.updateInfo.name" /></td>
					<td><input type="text" name="name" value="${user.name}" /></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.updateInfo.role" /></td>
					<td>
					<c:choose>
						<c:when test="${user.role == 'admin'}">
							<select name="role">
								<c:forEach var="role" items="${roleMap}">
									<option value="${role.key}"<c:if test="${role.key == user.role }">selected="selected"</c:if>>${role.value}</option>
								</c:forEach>
							</select>
						</c:when>
						<c:otherwise>
							${user.role}
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="<spring:message code="message.user.updateInfo.confirmBtn" />" />
						<input type="button" value="<spring:message code="message.user.updateInfo.deleteBtn" />" onclick="checkDelete()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>