<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>修改用户</title>
</head>
<body>
    <jsp:include page="inc.jsp"></jsp:include>
    <sf:form action="${pageContext.request.contextPath}/admin/user/update" method="post" modelAttribute="user" id="addForm">
        <table>
            <thead>
                <th>
                    <td colspan="2">修改用户功能</td>
                </th>
            </thead>
            <tbody>
                <tr>
                    <td>用户名</td>
                    <td>
                        <sf:input path="username"/>
                    </td>
                </tr>
                <tr>
                    <td>昵称</td>
                    <td>
                        <sf:input path="nickname"/>
                    </td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td>
                        <sf:input path="password"/>
                    </td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td>
                        <sf:select path="status">
                            <sf:option value="0">停用</sf:option>
                            <sf:option value="0">启用</sf:option>
                        </sf:select>
                    </td>
                </tr>
                <tr>
                    <td>角色</td>
                    <td>
                        <c:forEach var="role" items="${roles}">
                            ${role.name} <input type="checkbox" name="roldId" value="${role.id}"/><br>
                        </c:forEach>
                    </td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="提交修改">
                        <input type="reset" value="重置">
                    </td>
                </tr>
            </tfoot>
        </table>
    </sf:form>
</body>
</html>
