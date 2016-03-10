<%@page import="model.Message"%>
<%@page import="Database.DatabaseMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/TaskStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<title>Manage Messages</title>
</head>
<body>
	<h1>
		<%
			HttpSession session1 = request.getSession();
			String username = (String) session1.getAttribute("username");
			out.println("消息管理");
		%>
	</h1>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="ADSendPublic.jsp">发送公共消息</a></li>
				<li><a href="ADSendMessage.jsp">发送私人消息</a></li>
				<li><a href="Administrator.jsp">返回管理主页</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<form action=manageMessage method="get">
			<h4>公共消息</h4>
			<%
				int count = DatabaseMessage.getPublicMessage().length;
				int[] midGot = new int[count];
				midGot = DatabaseMessage.getPublicMessage();
				for (int i = 0; i < count; i++) {
					Message m = DatabaseMessage.getMessage(midGot[i]);
					out.print("<div class=\"Message\"><ul><h1><li><input type=\"radio\" value=" + m.getMid()
							+ " name=\"message\">" + m.getContent() + "</li></h1><h5><li>来自：" + m.getSourceid()
							+ "</li><li><pre> </pre></li><li>时间：" + m.getTime() + "</li></h5></ul></div>");
				}
			%>
			<h4>私人消息</h4>
			<%
				int count2 = DatabaseMessage.getTReceiveMessage(username).length;
				int[] midGot2 = new int[count2];
				midGot2 = DatabaseMessage.getTReceiveMessage(username);
				for (int i = 0; i < count2; i++) {
					Message m = DatabaseMessage.getMessage(midGot2[i]);
					out.print("<div class=\"Message\"><ul><h1><li><input type=\"radio\" value=" + m.getMid()
							+ " name=\"message\">" + m.getContent() + "</li></h1><h5><li>来自：" + m.getSourceid()
							+ "</li><li><pre> </pre></li><li>时间：" + m.getTime() + "</li></h5></ul></div>");
				}
			%>
			<div class="button3">
				<input type='submit' value='删除消息'>
			</div>
		</form>
	</div>
</body>
</html>