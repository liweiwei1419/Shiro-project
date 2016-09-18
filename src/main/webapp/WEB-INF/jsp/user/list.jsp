<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <jsp:include page="inc.jsp"></jsp:include>

    <table border="1">
        <thead>
            <th>
                <td>用户标识</td>
                <td>用户名称</td>
                <td>用户昵称</td>
                <td>用户状态</td>
                <td>用户操作</td>
            </th>
        </thead>
        <tbody>
            <c:forEach items="${list}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.password}</td>
                    <td>${user.nickname}</td>
                    <td>${user.status}</td>
                    <td>
                        <a href="#">更新用户信息</a>
                        <a href="#">查询用户权限</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
