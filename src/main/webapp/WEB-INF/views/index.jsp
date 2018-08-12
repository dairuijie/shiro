<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.dai.ruijie.core.Auths"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>${user}
	login success<br>
	<shiro:hasPermission name="<%=Auths.ADD%>">
    add<a href="${ctx}/list">data</a>
</shiro:hasPermission>
<br>
	<shiro:hasPermission name="<%=Auths.DELETE%>">
    delete
</shiro:hasPermission>
<br>
	<shiro:hasPermission name="<%=Auths.UPDATE%>">
    update
</shiro:hasPermission>
	<shiro:hasPermission name="<%=Auths.SELECT%>">
    update
</shiro:hasPermission>
	<a href="${ctx}/logout">退出</a>
	${data}
</body>
</html>