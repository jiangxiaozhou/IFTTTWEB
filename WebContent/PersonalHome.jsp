<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/PersonalStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person Home</title>
</head>
<body>
	<h1>
		<%
			HttpSession session1 = request.getSession();
			String username = (String) session1.getAttribute("username");
			out.println("Welcome " + username);
		%>
	</h1>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="/IFTTT/TaskManagement.jsp">任务管理</a></li>
				<li><a href="/IFTTT/PersonalInfo.jsp">个人信息</a></li>
				<li><a href="/IFTTT/MessageManagement.jsp">消息管理</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<h1>Choose one option above</h1>
		<br><br>
		<p>Stay hungry stay foolish</p>
	</div>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="index.jsp">返回起始页</a></li>
			</ul>
		</div>
	</div>
</body>
</html>