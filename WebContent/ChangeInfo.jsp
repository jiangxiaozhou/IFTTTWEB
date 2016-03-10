<%@page import="Database.DatabaseAccount"%>
<%@ page language="java" contentType="text/html;charset=gb2312"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/SendStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Personal Information</title>
</head>
<body>
	<div class="main">
		<div class="title-head">
			<h1>修改资料</h1>
		</div>
		<div class="Message">
			<form action="ChangeInfo" method="post">
				<h2>
					<%
						HttpSession session1 = request.getSession();
						String username = (String) session1.getAttribute("username");
						out.print("用户名：" + username);
					%>
				</h2>
				<h3>密码</h3>
				<input id="password" name="password" type="password">
				<h3>确认密码</h3>
				<input id="password2" name="password2" type="password">
				<h3>Email</h3>
				<input id="email" name="email" type="text">
				<h3>
					<%
						int level = DatabaseAccount.getLevel(username);
						out.print("会员等级：" + level);
					%>
				</h3>
				<div class="submit">
					<input type="submit" value="确定"> <input type="reset"
						value="重置">
				</div>
			</form>
		</div>
		<div class="option">
			<div class="cl-effect-1">
				<ul>
					<li><a href="PersonalInfo.jsp">返回 </a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>