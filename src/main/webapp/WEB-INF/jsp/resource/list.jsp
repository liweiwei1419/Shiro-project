<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>资源查看列表</title>
</head>
<body>
    <jsp:include page="inc.jsp"/>
    <table border="1">
        <thead>
            <tr>
                <th>资源标识</th>
                <th>资源名称</th>
                <th>资源 permission</th>
                <th>资源 url</th>
                <th>资源操作</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${resourceList}" var="resource">
                <tr>
                    <td>${resource.id}</td>
                    <td>${resource.name}</td>
                    <td>${resource.permission}</td>
                    <td>${resource.url}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/resource/${resource.id}">修改权限</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
