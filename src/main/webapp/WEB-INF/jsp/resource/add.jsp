<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>添加权限页面</title>
</head>
<body>
    <jsp:include page="inc.jsp"/>
    <span>添加权限页面</span>
    <sf:form method="post" modelAttribute="resource" id="resourceForm">
        <table>
            <tr>
                <td>权限名称：</td>
                <td>
                    <sf:input path="name"/>
                </td>
            </tr>
            <tr>
                <td>权限 permission：</td>
                <td>
                    <sf:input path="permission"/>
                </td>
            </tr>
            <tr>
                <td>权限 url：</td>
                <td>
                    <sf:input path="url"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="提交">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </sf:form>

</body>
</html>
