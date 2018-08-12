<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" import="java.util.*"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>shiro_demo</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(function() {
		 $('#captchaImage').click(changeCaptcha);
	});
	function changeCaptcha() {
		$('#captchaImage').hide().attr('src',
				'${ctx}/captchaImage?' + Math.floor(Math.random() * 100))
				.fadeIn();
	}
</script>
</head>
<body>
	<form action="${ctx}/login" method="post">
		账号：<input type="text" name="username"> 密码：<input
			type="password" name="password" /> 记住我：<input type="checkbox"
			name="rememberMe" id="rememberMe" checked="checked"
			style="margin: 10px 0px" /> <img src="${ctx}/captchaImage"
			id="captchaImage" width="70" style="cursor: pointer;" height="30px" />
		&nbsp; <a href="#" onclick="changeCaptcha()">看不清?换一张</a>
		<input type="text"  id="captcha" name="captcha" class="form-control valid" placeholder="验证码"></input>
		<button type="submit">登录</button>
	</form>
</body>
</html>