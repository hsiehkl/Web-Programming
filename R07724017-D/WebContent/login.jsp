<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome!</title>
</head>
<body>

	<p>
		<strong>歡迎使用待辦事項系統，請輸入您的資料來登入:</strong>
	</p>
	
	<%
        if ("noAccount".equals(request.getAttribute("error"))) {
    %>
        <p style="color:red;">
		<strong>輸入帳號錯誤，查無此帳號</strong>
		</p>
    <%
        } else if ("wrongPassword".equals(request.getAttribute("error"))) {
    %>
    	<p style="color:red;">
		<strong>輸入密碼錯誤</strong>
		</p>
	<%
        } else if ("nullInput".equals(request.getAttribute("error"))) {
    %>
    	<p style="color:red;">
		<strong>請輸入帳號與密碼</strong>
		</p>
    <%
        }
    %>
	
	<form name="form" method="post" action="Login">
		<table>
			<tr>
				<td><div align="left">名稱:</div></td>
				<td><div align="left">
						<input name="accountName" type="text">
					</div></td>
			</tr>
			<tr>
				<td><div align="left">密碼:</div></td>
				<td><div align="left">
						<input name="password" type="password">
					</div></td>
			</tr>
			<tr>
				<td><div align="left">自動登入:</div></td>
				<td><div align="left">
						<input type="checkbox" name="autoLogin" value="auto">
						<label><a href='AddNewUser'> 新使用者?</a></label>
					</div></td>
			</tr>
		</table>
		<p>
			<label>
				<input type="submit" name="submit" value="送出">
			</label>
		</p>
	</form>
</body>
</html>