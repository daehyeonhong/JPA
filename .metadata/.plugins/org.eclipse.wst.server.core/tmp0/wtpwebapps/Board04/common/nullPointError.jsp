<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Null Point Error Page</title>
</head>
<body bgcolor="#fff" text="#000">
	<!-- Title 시작 -->
	<table border="1" style="border-spacing: 0; border-collapse: collapse; width: 100%">
		<tr>
			<td align="center" bgcolor="orange"><b>Null Point Error Page</b></td>
		</tr>
	</table>

	<!-- Error Message -->
	<table border="1" style="border-spacing: 0; border-collapse: collapse; width: 100%">
		<tr>
			<td align="center"><br /><br /><br /><br />Message:${exception.message}<br /><br /><br /><br /></td>
		</tr>
	</table>
</body>
</html>