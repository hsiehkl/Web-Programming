<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新增備忘錄</title>
</head>
<body>
		<form method="post" action="AddToDo">
		<table>
			<tr>
				<td><div align="left">時間:</div></td>
				<td><div align="left">
						<input type="datetime-local" name="dateTime" required><br>
					</div></td>
			</tr>
			<tr>
				<td><div align="left">地點:</div></td>
				<td><div align="left">
						<input type="text" name="location" required>
					</div></td>
			</tr>
			<tr>
				<td><div align="left">註記:</div></td>
				<td><div align="left">
						<input type="text" name="note" />
					</div></td>
			</tr>
		</table>
		<p>
			<label>
				<input type="submit" name="submit" value="新增">
			</label>
		</p>
	</form>
</body>
</html>