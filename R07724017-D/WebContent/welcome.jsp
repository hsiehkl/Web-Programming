<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="model.ToDo"%>
<%@page import="model.ToDoService"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Your Schedule</title>
</head>
<body>

	<%
		User user = (User)session.getAttribute("user");
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		String accountName = user.getAccountName();
	    ServletContext sct = getServletContext();
		ArrayList<ToDo> toDos = ToDoService.getTodosByUser(sct, accountName);
		pageContext.setAttribute("toDos", toDos);
	%>

	<p>
		<strong><%= accountName %>, 歡迎登入待辦事項系統！</strong>
	</p>

	<table>	

		<c:choose>
			<c:when test= "${not empty toDos}">
				<tr>
					<td><div align="left">以下是您的待辦事項!</div></td>
					<td width=50>&nbsp;</td>
					<td><div align="right"><label><a href='addToDo.jsp'>點擊此處新增事項</a></label></div><br></td>
				</tr>
				<c:forEach var="toDo" items="${toDos}">
					<tr>
						<td><b><br>${toDo.dateTimeStr}</b></td>
						<td><form action="DeleteToDo">
								<button name="delete" value="${toDo.id}" type="submit">刪除</button>
							</form>
						</td>
					</tr>
					<tr>
						<td><b>${toDo.location}</b></td>
					</tr>
					<tr>
						<td>${toDo.note}<br></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td><div align="left">您尚無待辦事項!</div></td>
					<td width=50>&nbsp;</td>
					<td><div align="right"><label><a href='addToDo.jsp'>點擊此處新增事項</a></label></div><br></td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>
	<p>
		<label> <a href='Logout'>登出</a>
		</label>
	</p>
</body>
</html>