<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>IFTTT Register</title>
<link href="css/registerStyle.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<div class="main">
		<div class="title-head">
			<h1>Create your account here</h1>
		</div>
		<div class="option">
			<div class="cl-effect-1">
				<ul>
					<li><a href="index.jsp">Home</a></li>
				</ul>
			</div>
		</div>
		<div class="Regisration">
			<div class="Regisration-head">
				<h2>Register</h2>
			</div>
			<form action="register" method="post">
				<input id="emailid" name="email" type="text" value="Email Address"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Email Address';}">
				<input id="username" name="username" type="text" value="Username"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Username';}">
				<h3>Input your password</h3>
				<input id="password" name="password" type="password"
					value="Password" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Password';}">
				<h3>Confirm your password</h3>
				<input id="password2" name="password2" type="password"
					value="Password" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Password';}">
				<div class="submit">
					<input type="submit" value="Sign Me Up">
				</div>
				<div class="clear"></div>
			</form>
		</div>
	</div>
</body>
</html>