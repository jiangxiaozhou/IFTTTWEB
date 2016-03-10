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
			out.print(username + "的任务列表");
		%>
	</h1>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="/IFTTT/TaskManagement.jsp">新建任务</a></li>
				<li><a href="/IFTTT/LookTaskInfo.jsp">查看任务</a></li>
				<li><a href="/IFTTT/ChooseModifyTask.jsp">修改任务</a></li>
				<li><a href="PersonalHome.jsp">返回主页</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<form action=TaskController method="post">
			<%
				for (int i = 0; i < TaskOperation.taskList.size(); i++) {
					if (TaskOperation.taskList.get(i).getUsername().equals(username)) {
						Task task = TaskOperation.taskList.get(i);
						out.print("<div class=\"Task\"><ul><li><input type=\"radio\" value=" + task.getTid()
								+ " name=\"task\">");
						out.print(task.getInfo());
					}
				}
			%>
			<div class="TaskSlt">
				<select id="status" name="status">
					<option value=1>开始</option>
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