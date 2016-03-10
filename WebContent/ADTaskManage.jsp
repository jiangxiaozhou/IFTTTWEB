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
		所有会员的任务
	</h1>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="Administrator.jsp">返回管理主页</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<form action=TaskController method="get">
			<%
				for (int i = 0; i < TaskOperation.taskList.size(); i++) {
						Task task = TaskOperation.taskList.get(i);
						out.print("<div class=\"Task\"><ul><li><input type=\"radio\" value=" + task.getTid()
								+ " name=\"task\">");
						out.print(task.getInfo());
				}
			%>
			<div class="TaskSlt">
				<select id="status" name="status">
					<option value=3>暂停</option>
					<option value=2>停止</option>
					<option value=100>删除</option>
				</select>
				<div class="button2">
					<input type='submit' value='确定'>
				</div>
			</div>
		</form>
	</div>
</body>
</html>