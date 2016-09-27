<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>未授权</title>
        <link href="${pageContext.request.contextPath}/resources/bootstrap-3.3.0/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
    <body>
        <div class="container">
            <h1>很遗憾,您不具备访问该网页的权限。</h1>
            <a href="${pageContext.request.contextPath}/login">请更换账号登录</a>
        </div>
    </body>
</html>
