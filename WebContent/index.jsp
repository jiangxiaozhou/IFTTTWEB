<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>IFTTT</title>
<link href="css/indexStyle.css" rel="stylesheet" type="text/css"
	media="all" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- js -->
<script src="js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
</head>
<body>
	<!-- banner -->
	<div class="banner">
		<div class="container">
			<div class="header">
				<div class="logo">
					<a href="index.jsp"><img src="images/logo.png" alt=" " /></a>
				</div>
				<div class="logo-right">
					<nav class="cl-effect-1">
					<ul>
						<li><a href="index.jsp" class="act">Home</a></li>
						<li><a href="register.jsp" class="act">Sign Up</a></li>
						<li><a href="#about" class="act">About</a></li>
					</ul>
					</nav>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="banner-info">
			<h1>Welcome!</h1>
			<p>This is a web application IFTTT</p>
			<div class="read">
				<a href="login.jsp" class="hvr-rectangle-out">LOGIN</a>
			</div>
		</div>
	</div>
	<!-- //banner -->
</body>
</html>