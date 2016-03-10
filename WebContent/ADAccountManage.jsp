<%@page import="model.Cost"%>
<%@page import="Database.DatabaseCost"%>
<%@page import="model.Account"%>
<%@page import="Database.DatabaseAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="model.Task, Action.TaskOperation, Database.DatabaseTask"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/TaskStyle.css" rel='stylesheet' type='text/css' />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Look task list</title>
</head>
<body>
	<h1>
		<%
			HttpSession session2 = request.getSession();
			String username = (String) session2.getAttribute("username");
			out.print("会员列表");
		%>
	</h1>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="ChangeAccountLevel.jsp">更改会员等级</a></li>
				<li><a href="Administrator.jsp">返回管理主页</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<%
			String[] userGot = DatabaseAccount.getAllAccount();
			for (int i = 0; i < userGot.length; i++) {
				Account a = DatabaseAccount.getAccount(userGot[i]);
				int[] tidGot = DatabaseTask.getTaskOfUserid(a.getUsername());
				int[] costidGot = DatabaseCost.getCostByUser(a.getUsername());
				out.print("<div class=\"Message\"><ul><h1><li>用户名：" + a.getUsername() + "</li><li>用户邮箱："
						+ a.getEmailaddress() + "</li><li>拥有任务：");
				for (int j = 0; j < tidGot.length; j++) {
					Task t = DatabaseTask.getTask(tidGot[j]);
					out.print("</li><li>" + t.getTaskName() + "</li><li><pre>  </pre></li><li>");
				}
				out.print("</li></h1><h1><li>消费记录：</li><li>");
				for (int j = 0; j < costidGot.length; j++) {
					Cost cost = DatabaseCost.getCost(costidGot[j]);
					out.print("</li><li>花费：" + cost.getMoney() + "</li><li><pre>  </pre></li><li>时间：" + cost.getTime()
							+ "</li><li><pre>  </pre></li><li>");
				}
				out.print("</li></h1><h5><li>用户会费余额：" + a.getMoney() + "</li><li>用户等级：" + a.getLevel()
						+ "</li></h5></ul></div>");
			}
		%>
	</div>
</body>
</html>