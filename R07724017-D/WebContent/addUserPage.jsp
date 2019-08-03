<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
</head>
<body>

	<%
		if ("2".equals(request.getAttribute("toPage"))) {
	%>
	<div>
		<p>
			<strong>待辦事項系統註冊- Page 2</strong>
		</p>
		<%
	        if ("true".equals(request.getAttribute("isRepeated"))) {
	    %>
	        <p style="color:red;">
			<strong>所輸入的帳戶名稱已經有人使用，請輸入另一個帳戶名稱！</strong>
			</p>
	    <%
	        }
	    %>
		<form name="form" method="post" action="AddNewUser">
			<table>
				<tr>
					<td><div align="left">新增帳戶名稱:</div></td>
					<td><div align="left">
							<input name="accountName" type="text">
						</div></td>
				</tr>
				<tr>
					<td><div align="left">新增帳戶密碼:</div></td>
					<td><div align="left">
							<input name="password" type="password" required>
						</div></td>
				</tr>
			</table>
			<p>
				<label> <input type="reset" name="reset" value="重設">
					<input type="submit" name="submit" value="送出">
				</label>
			</p>
		</form>
	</div>
	<%
		} else if ("3".equals(request.getAttribute("toPage"))) {
	%>
	<div>
		<p>
			<strong>待辦事項系統註冊- Page 3<br><br>
					新增資料完成！
			</strong>
		</p>
		
		<%
		String username = (String)session.getAttribute("accountName");
		String password = (String)session.getAttribute("password");
		String name = (String)session.getAttribute("name");
		String address = (String)session.getAttribute("address");
		String phoneNumber = (String)session.getAttribute("phoneNumber");
		String education = (String)session.getAttribute("education");
		%>
		
		<table>
			<tr>
				<td><div align="left">帳戶名稱:</div></td>
				<td width=30>&nbsp;</td>
				<td><div align="left"><%= username%></div></td>
			</tr>
			<tr>
				<td><div align="left">帳戶密碼:</div></td>
				<td width=30>&nbsp;</td>
				<td><div align="left"><%= password%></div></td>
			</tr>
			<tr>
				<td><div align="left">姓名:</div></td>
				<td width=30>&nbsp;</td>
				<td><div align="left"><%= name%></div></td>
			</tr>
			<tr>
				<td><div align="left">地址:</div></td>
				<td width=30>&nbsp;</td>
				<td><div align="left"><%= address%></div></td>
			</tr>
			<tr>
				<td><div align="left">電話:</div></td>
				<td width=30>&nbsp;</td>
				<td><div align="left"><%= phoneNumber%></div></td>
			</tr>
			<tr>
				<td><div align="left">學歷:</div></td>
				<td width=30>&nbsp;</td>
				<td><div align="left"><%= education%></div></td>
			</tr>
		</table>
		<p>
			<label> <a href='Login'>請回到首頁進行登入動作！</a>
			</label>
		</p>
	</div>
	<%
		} else {
	%>
	<div>
		<p>
			<strong>待辦事項系統註冊- Page 1</strong>
		</p>
		<form name="form" method="post" action="AddNewUser">
			<table>
				<tr>
					<td><div align="left">姓名:</div></td>
					<td><div align="left">
							<input name="name" type="text">
						</div></td>
				</tr>
				<tr>
					<td><div align="left">住址:</div></td>
					<td><div align="left">
							<input name="address" type="text">
						</div></td>
				</tr>
				<tr>
					<td><div align="left">電話:</div></td>
					<td><div align="left">
							<input name="phoneNumber" type="text">
						</div></td>
				</tr>
				<tr>
					<td><div align="left">學歷</div></td>
					<td><div align="left">
							<input type="radio" name="education" value="高中職">高中職 
							<input type="radio" name="education" value="五專">五專
							<input type="radio" name="education" value="四技二專">四技二專
							<input type="radio" name="education" value="大學">大學
							<input type="radio" name="education" value="研究所"> 研究所
						</div></td>
				</tr>
			</table>
			<p>
				<label> <input type="reset" name="reset" value="重設">
					<input type="submit" name="submit" value="下一頁">
				</label>
			</p>
		</form>
	</div>
	<%
		} 
	%>

</body>
</html>