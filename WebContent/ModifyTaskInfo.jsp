<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="model.Task, Action.TaskOperation, Database.DatabaseTask"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function showWeiboThat() {
		document.getElementById("WeiboThat").style.display = "block";
		document.getElementById("MailThat").style.display = "none";
	}
	function showMailThat() {
		document.getElementById("WeiboThat").style.display = "none";
		document.getElementById("MailThat").style.display = "block";
	}
	function showTimeThis() {
		document.getElementById("TimeTriggerThis").style.display = "block";
		document.getElementById("WeiboTriggerThis").style.display = "none";
		document.getElementById("MailTriggerThis").style.display = "none";
		document.getElementById("WeiboTimeTriggerThis").style.display = "none";
	}
	function showMailThis() {
		document.getElementById("TimeTriggerThis").style.display = "none";
		document.getElementById("WeiboTriggerThis").style.display = "none";
		document.getElementById("MailTriggerThis").style.display = "block";
		document.getElementById("WeiboTimeTriggerThis").style.display = "none";
	}
	function showWeiboThis() {
		document.getElementById("TimeTriggerThis").style.display = "none";
		document.getElementById("WeiboTriggerThis").style.display = "block";
		document.getElementById("MailTriggerThis").style.display = "none";
		document.getElementById("WeiboTimeTriggerThis").style.display = "none";
	}
	function showWeiboTimeThis() {
		document.getElementById("TimeTriggerThis").style.display = "none";
		document.getElementById("WeiboTriggerThis").style.display = "none";
		document.getElementById("MailTriggerThis").style.display = "none";
		document.getElementById("WeiboTimeTriggerThis").style.display = "block";
	}
</script>
<<<<<<< HEAD
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/TaskStyle.css" rel='stylesheet' type='text/css' />
<title>Task Management</title>
</head>
<body>
	<h1>
		<%
			HttpSession session1 = request.getSession();
			String username = (String) session1.getAttribute("username");
			out.println(username + "的任务修改");
			int tid = Integer.parseInt(request.getParameter("tid"));
			Task task = TaskOperation.getTask(tid);
			int thistype = task.getThistype();
			int thattype = task.getThattype();
		%>
	</h1>
	<div class="option">
		<div class="cl-effect-1">
			<ul>
				<li><a href="/IFTTT/LookTaskInfo.jsp">返回</a></li>
			</ul>
		</div>
	</div>
	<div class="banner-info">
		<form method="post" action="getTask">
			<div class="ThisThatChoose">
				<ul>
					<li>
						<h1>
							任务名：<%
							out.print(task.getTaskName());
						%>
						</h1>
					</li>
					<li><pre>        </pre></li>
					<li>
						<h1>THIS</h1> <input type="radio" value=0 checked="checked"
						name="this" id="timeTrigger" onchange="showTimeThis()">
						定时发送 <input type="radio" value=1 name="this" id="MailTrigger"
						onchange="showMailThis()"> 收到邮件 <input type="radio"
						value=2 name="this" id="WeiboTrigger" onchange="showWeiboThis()">微博内容
						<input type="radio" value=3 name="this" id="WeiboTimeTrigger"
						onchange="showWeiboTimeThis()"> 微博持续时间未发
					</li>
					<li><pre>        </pre></li>
					<li>
						<h1>THAT</h1> <input type="radio" checked="checked" name="that"
						id="MailAction" onchange="showMailThat()" value=1> 邮件 <input
						type="radio" name="that" id="WeiboAction"
						onchange="showWeiboThat()" value=0> 微博
					</li>
				</ul>
			</div>
			<div class="This">
				<ul>
					<li>
						<h2>THIS</h2>
					</li>
					<li><pre>        </pre></li>
					<li>
						<div id="TimeTriggerThis" style="display: block">
							<ul>
								<li>日期：<input type="text" name="Date" id="Date">
								</li>
								<li><pre>        </pre></li>
								<li>时间：<input type="text" name="Time" id="Time">
								</li>
							</ul>
						</div>
					</li>
					<li>
						<div id="MailTriggerThis" style="display: none">
							<ul>
								<li>邮箱：<input type="text" name="MailId1" id="MailId1">
								</li>
								<li><pre>        </pre></li>
								<li>密码：<input type="password" name="MailPass1"
									id="MailPass1"></li>
							</ul>
						</div>
					</li>
					<li>
						<div id="WeiboTriggerThis" style="display: none">
							<ul>
								<li>微博ID：<input type="text" name="WeiboId1" id="WeiboId1">
								</li>
								<li><pre>        </pre></li>
								<li>内容： <textarea rows="10" cols="23" name="weiboPattern"
										id="weiboPattern"> </textarea>
								</li>
							</ul>
						</div>
					</li>
					<li>
						<div id="WeiboTimeTriggerThis" style="display: none">
							<ul>
								<li>微博ID：<input type="text" name="WeiboId2" id="WeiboId2">
								</li>
								<li><pre>        </pre></li>
								<li>持续时间:<input type="text" name="during" id="during">
								</li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="That">
				<ul>
					<li>
						<h2>THAT</h2>
					</li>
					<li>
						<div id="WeiboThat" style="display: none">
							<ul>
								<li><pre>    </pre></li>
								<li>微博账户：<input type="text" name="weiboid" id="weiboid">
								</li>
								<li><pre>    </pre></li>
								<li>微博密码：<input type="password" name="weibopassword"
									id="weibopassword">
								</li>
								<li><pre>    </pre></li>
								<li>微博内容: <textarea rows="10" cols="30" name="weibocontent"
										id="weibocontent">
						</textarea></li>
							</ul>
						</div>
					</li>
					<li>
						<div id="MailThat" style="display: block">
							<ul>
								<li>邮件用户:<input type="text" name="MailId" id="MailId">
									密码：<input type="password" name="Mailpass" id="Mailpass">
								</li>
								<li>收件人：<input type="text" name="Mailrec" id="Mailrec">
									主题：<input type="text" name="Mailsub" id="Mailsub">
								</li>
								<li>邮件内容: <textarea rows="10" cols="30" name="Mailcontent"
										id="Mailcontent">
						</textarea></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<div class="button">
				<ul>
					<li><input type="submit" value="    确  认    "></li>
					<li><pre>    </pre></li>
					<li><input type="reset" value="    取  消    "></li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>