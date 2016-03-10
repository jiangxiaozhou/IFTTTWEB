<%@page import="Database.DatabaseAccount"%>
<%@ page language="java" contentType="text/html;charset=gb2312"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/SendStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change account level</title>
</head>
<body>
	<div class="main">
		<div class="title-head">
			<h1>设置用户等级</h1>
		</div>
		<div class="Message">
			<form action="ChangeInfo" method="get">
				<h2>
					<%
						HttpSession session1 = request.getSession();
						String username = (String) session1.getAttribute("username");
						out.print("执行管理员：" + username);
					%>
				</h2>
				<h3>用户名</h3>
				<input id="username" name="username" type="text">
				<h3>Email</h3>
				<input id="level" name="level" type="text">
				<div class="submit">
					<input type="submit" value="确定"> <input type="reset"
						value="重置">
				</div>
			</form>
		</div>
		<div class="option">
			<div class="cl-effect-1">
				<ul>
					<li><a href="ADAccountManage.jsp">返回 </a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>