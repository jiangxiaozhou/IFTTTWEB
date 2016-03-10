<%@ page language="java" contentType="text/html; charset=gb2312"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>IFTTT Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- meta name="viewport" content="width=device-width,initial-scale=1.0" 解释
 <meta name="viewport" content="width=device-width,initial-scale=1.0">
 content属性值 :
     width:可视区域的宽度，值可为数字或关键词device-width
     height:同width
     intial-scale:页面首次被显示是可视区域的缩放级别，取值1.0则页面按实际尺寸显示，无任何缩放
     maximum-scale=1.0, minimum-scale=1.0;可视区域的缩放级别，
     maximum-scale用户可将页面放大的程序，1.0将禁止用户放大到实际尺寸之上。
     user-scalable:是否可对页面进行缩放，no 禁止缩放 -->
<link href="css/loginStyle.css" rel='stylesheet' type='text/css' />
</head>
<body>
	<h1>Please login here！</h1>
	<div class="login-box">
		<form action="Login" method="post">
			<input id="username" name="username" type="text" class="text"
				value="Username" onfocus="this.value = '';"
				onblur="if (this.value == '') {this.value = 'Username';}"> <input
				id="password" name="password" type="password" value="Password"
				onfocus="this.value = '';"
				onblur="if (this.value == '') {this.value = 'Password';}">
			<div class="clear"></div>
			<div class="btn">
				<input type="submit" value="Login"> <a href="index.jsp">Cancel</a>
			</div>
			<div class="clear"></div>
		</form>
		<div class="remember">
			<a href="#">Remember me</a>
		</div>
	</div>
</body>
</html>
