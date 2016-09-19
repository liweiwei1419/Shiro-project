<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加角色</title>
</head>
<body>
    <jsp:include page="inc.jsp"/>
    添加角色功能
    <sf:form modelAttribute="role" method="post" id="roleForm">
        <table>
            <tbody>
                <tr>
                    <td>角色名称</td>
                    <td>
                        <sf:input path="name"/>
                    </td>
                </tr>
                <tr>
                    <td>角色标识字符串</td>
                    <td>
                        <sf:input path="sn"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="添加">
                        <input type="reset" value="重置">
                    </td>
                </tr>
            </tbody>
        </table>
    </sf:form>

</body>
</html>
