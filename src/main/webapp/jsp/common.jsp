<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<br>
<div style="float: left">
<a class="btn btn-danger" role="button" href="${pageContext.request.contextPath}/admin/resource/list">资源管理</a>

<a class="btn btn-success" role="button" href="${pageContext.request.contextPath}/admin/role/list">角色管理</a>

<a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/admin/user/list">用户管理</a>
</div>
<div style="float: right">
    欢迎您,【<shiro:principal property="nickname"/>】
    <a class="btn btn-warning" role="button" href="${pageContext.request.contextPath}/logout">退出登录</a>
</div>
<hr style="clear: both;margin-top: 50px">
<link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
