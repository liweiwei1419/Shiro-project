<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>用户权限列表</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="inc.jsp"/>
            用户名：${user.username}，昵称：${user.nickname}
            <hr>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>资源名称</th>
                        <th>资源 url</th>
                        <th>资源权限字符串</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${resources}" var="res">
                        <tr>
                            <td>${res.name}</td>
                            <td>${res.url}</td>
                            <td>${res.permission}</td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </body>
</html>
