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
</head>
<body>
	<form action="${ctx}/login" method="post">
		账号：<input type="text" name="username"> 密码：<input
			type="password" name="password" />
			记住我：<input type="checkbox" name="rememberMe" id="rememberMe" checked="checked"
                            style="margin: 10px 0px" />
		<button type="submit">登录</button>
	</form>
</body>
</html>