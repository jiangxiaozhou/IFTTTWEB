<%@page import="model.Cost"%>
<%@page import="Database.DatabaseCost"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Database.DatabaseAccount, model.Account"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/PersonalStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PersonalInfo</title>
</head>
<body>
	<h1>个人信息</h1>
	<%
		HttpSession session2 = request.getSession();
		String username = (String) session2.getAttribute("username");
		String password = (String) session2.getAttribute("password");
		Account a = DatabaseAccount.getAccount(username);
		int money = a.getMoney();
		int level = a.getLevel();
		int userid = a.getUserid();
		String email = a.getEmailaddress();
	%>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="ChangeInfo.jsp">修改资料</a></li>
				<li><a href="AddMoney.jsp">充值会费</a></li>
				<li><a href="PersonalHome.jsp">返回主页</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<h3>
			<%
				out.print("用户名: " + username + "<br>");
				out.print("用户ID: " + userid + "<br>");
				out.print("Email: " + email + "<br>");
				out.print("会费余额: " + money + "<br>");
				out.print("会员等级: " + level + "<br>");
				out.print("<br>消费记录: <br>");
				int[] costidGot = DatabaseCost.getCostByUser(a.getUsername());
				for (int j = 0; j < costidGot.length; j++) {
					Cost cost = DatabaseCost.getCost(costidGot[j]);
					out.print("<h5><div class=\"Message\"><ul><li>花费：" + cost.getMoney() + "</li><li><pre>  </pre></li><li>时间：" + cost.getTime()
							+ "</li></ul></div></h5>");
				}
			%>
		</h3>
	</div>
</body>
</html>