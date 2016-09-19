<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>用户登录</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            .loginTitle{
                text-align: center;
                font-family: 微软雅黑;
            }
        </style>
    </head>

    <body>
        <div class="container">
            <div class="loginTitle">
                <h1>用户、角色、权限管理系统</h1>
            </div>
            <hr>
            <form class="form-horizontal" role="form" method="post" action="${pageContext.request.contextPath}/login">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="请输入用户名" name="username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
                    </div>
                </div>
                <%--<div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember me
                            </label>
                        </div>
                    </div>
                </div>--%>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">提交</button>
                    </div>
                </div>
            </form>
            <font color="red">${msg}</font>
        </div>
    </body>
</html>
