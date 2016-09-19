<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>角色列表查询</title>
</head>
<body>
    <jsp:include page="inc.jsp"/>
    <table border="1">
        <thead>
            <tr>
                <th>角色标识</th>
                <th>角色名称</th>
                <th>角色字符串</th>
                <th>
                    操作
                </th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${roleList}" var="role">
                <tr>
                    <td>${role.id}</td>
                    <td>${role.name}</td>
                    <td>${role.sn}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/role/update/${role.id}">更新</a>
                        <a href="${pageContext.request.contextPath}/admin/role/resources/${role.id}">设置资源</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
