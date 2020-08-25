<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="message.board.list.mainTitle" /></title>
</head>
<body>
	<div align="center">
		<h1><em><spring:message code="message.board.list.mainTitle" /></em></h1>
		<a href="getBoardList.do?lang=en">
			<spring:message code="message.user.login.language.en" />
		</a>&nbsp;&nbsp;
		<a href="getBoardList.do?lang=ko">
			<spring:message code="message.user.login.language.ko" />
		</a>
		<h3>[${userName}]<spring:message code="message.board.list.welcomeMsg" /><br /><a href="logout.do">Log-Out</a></h3>

		<!-- 검색 시작 -->
		<form action="getBoardList.do" method="post">
			<table border="1" style="border-spacing: 0; border-collapse: collapse;" style="width: 700px">
				<tr>
					<td align="right">
						<select name="searchCondition" style="border: 0px;">
							<!-- <option value="TITLE">제목</option>
							<option value="CONTENT">내용</option> -->
							<c:forEach var="option" items="${conditionMap}">
								<option value="${option.value}">${option.key}</option>
							</c:forEach>
						</select>
						<input type="text" name="searchKeyword" style="border: 0px;" />
						<input type="submit" value="<spring:message code="message.board.list.search.condition.btn" />" />
					</td>
				</tr>
			</table>
		</form>
		<br />

		<!-- 검색 종료 -->
		<table border="1" style="border-spacing: 0; border-collapse: collapse;" style="width: 700px">
			<tr>
				<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.seq"/></th>
				<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title"/></th>
				<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer"/></th>
				<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate"/></th>
				<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt"/></th>
			</tr>
			<c:forEach var="board" items="${boardList}">
				<tr>
					<td>${board.seq}</td>
					<td align="left"><a href="getBoard.do?seq=${board.seq}" style="text-decoration: none;">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.regDate}</td>
					<td>${board.cnt}</td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<a href="insertBoard.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
	</div>
</body>
</html>