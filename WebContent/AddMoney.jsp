<%@page import="model.Account"%>
<%@page import="Database.DatabaseAccount"%>
<%@ page language="java" contentType="text/html;charset=gb2312"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/SendStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add money</title>
</head>
<body>
	<div class="main">
		<div class="title-head">
			<h1>充值会费</h1>
		</div>
		<div class="Message">
			<form action="AddMoney" method="post">
				<h2>
					<%
						HttpSession session2 = request.getSession();
						String username = (String) session2.getAttribute("username");
						Account a = DatabaseAccount.getAccount(username);
						int money = a.getMoney();
						out.print("用户名：" + username);
					%>
				</h2>
				<h3>充值金额</h3>
				<input id="money" name="money" type="text">
				<h3>
					<%
						out.print("会费余额：" + money);
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