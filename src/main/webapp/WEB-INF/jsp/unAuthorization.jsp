<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>未授权</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <div style="margin-top: 20px;" class="alert alert-danger" role="alert">
                <span style="font-size: 24px;">很遗憾,您不具备访问该网页的权限。</span>
            </div>
            <button class="btn btn-default" type="button" onclick="history.go(-1)">返回上一页</button>
            <a class="btn btn-default" href="${pageContext.request.contextPath}/login">请更换账号登录</a>
        </div>
    </body>
</html>
