<%@ page language="java" contentType="text/html;charset=gb2312"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/SendStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> AD Send Massage</title>
</head>
<body>
	<div class="main">
		<div class="title-head">
			<h1>发送私人消息</h1>
		</div>
		<div class="Message">
			<form action="SendPublic" method="get">
				<h3>
					<br>收信人
				</h3>
				<input id="receive" name="receive" type="text">
				<h3>内容</h3>
				<textarea id="content" name="content" rows="10" cols="30"></textarea>
				<h3>
					<%
						HttpSession session1 = request.getSession();
						String username = (String) session1.getAttribute("username");
						out.print("发信人：" + username);
					%>
				</h3>
				<div class="submit">
					<input type="submit" value="发送"> <input type="reset"
						value="重置">
				</div>
			</form>
		</div>
		<div class="option">
			<div class="cl-effect-1">
				<ul>
					<li><a href="ADMessageManage.jsp">返回 </a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>