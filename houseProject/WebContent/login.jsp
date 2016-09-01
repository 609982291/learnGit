<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<script type="text/javascript">
	if (top.location != self.location) {

		top.location = self.location;

	}
</script>
<style type="text/css">
.bodytyle {
	background-color: #1c77ac;
	background-image: url(images/light.png);
	background-repeat: no-repeat;
	background-position: center top;
	overflow: hidden;
}
</style>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>
<script type="text/javascript">
	$(function() {
		$(":input[name=submit]").click(function() {
			var loginName = $(":input[name=loginName]").val();
			var loginPwd = $(":input[name=loginPwd]").val();
			var $this = $(this)

			$this.nextAll("font").remove();
			var url = "login";
			var args = {
				"loginName" : loginName,
				"loginPwd" : loginPwd,
				"time" : new Date()
			};
			$.post(url, args, function(data) {
				if (data == "password_wrong") {//可用
					$this.after("<font color='red'>密码错误</font>");
				} else if (data == "user_not_exist") {//可用
					$this.after("<font color='red'>用户不存在</font>");
				}

				else if (data == "login_success") {
					window.location.href = "Main.jsp";
				}
			})

		});
	})
</script>
</head>

<body class="bodytyle">

	<s:form action="login" methos="post" theme="simple">


		<div id="mainBody">
			<div id="cloud1" class="cloud"></div>
			<div id="cloud2" class="cloud"></div>
		</div>


		<div class="logintop">
			<span>Hotel Master</span>
			<ul>
				<li><a href="#">Home</a></li>
				<li><a href="#">Help</a></li>
				<li><a href="#">About</a></li>
			</ul>
		</div>

		<div class="loginbody">

			<span class="systemlogo"></span>

			<div class="loginbox">

				<ul>
					<li><s:textfield name="loginName" type="text"
							class="loginuser" value="admin"
							onclick="JavaScript:this.value=''" /></li>
					<li><input name="loginPwd" type="password" class="loginpwd"
						value="Password" onclick="JavaScript:this.value=''" /></li>
					<li><input type="button" class="loginbtn" value="Login"
						name="submit" /></li>
				</ul>


			</div>

		</div>



		<div class="loginbm">Hotel Master</div>
	</s:form>
</body>
</html>