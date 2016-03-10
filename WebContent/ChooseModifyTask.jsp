<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="model.Task, Action.TaskOperation, Database.DatabaseTask"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<link href="css/TaskStyle.css" rel='stylesheet' type='text/css' />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Choose task to modify</title>
</head>
<body>
	<h1>
		<%
			HttpSession session2 = request.getSession();
			String username = (String) session2.getAttribute("username");
			out.print("选择任务");
		%>
	</h1>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="LookTaskInfo.jsp">返回查看任务</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<form action="getChooseTaskTid" method="post">
			<%
				for (int i = 0; i < TaskOperation.taskList.size(); i++) {
					if (TaskOperation.taskList.get(i).getUsername().equals(username)) {
						Task task = TaskOperation.taskList.get(i);
						out.print("<div class=\"Task\"><ul><li><pre>   <pre></li><li>");
						out.print(task.getInfo());
					}
				}
			%>
			<div class="TaskSlt">
				<select id="status" name="tid">
					<%
						for (int i = 0; i < TaskOperation.taskList.size(); i++) {
							if (TaskOperation.taskList.get(i).getUsername().equals(username)) {
								Task task = TaskOperation.taskList.get(i);
								out.print("<option value=" + task.getTid() + ">" + task.getTaskName() + "</option>");
							}
						}
					%>
				</select>
				<div class="button2">
					<input type="submit" value="开始修改">
				</div>
			</div>
		</form>
	</div>
</body>
</html>