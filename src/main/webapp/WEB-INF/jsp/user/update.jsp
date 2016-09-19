<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>修改用户</title>
    </head>
    <body>
        <div class="container">
        <jsp:include page="inc.jsp"></jsp:include>
            <span></span>
            <div class="panel panel-info">
                <div class="panel-heading">修改用户</div>
                <div class="panel-body">
                    <sf:form method="post" modelAttribute="user" id="addForm">
                        <table>
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
                                        <%-- 先把后台传递过来的 List 数组转换为 JS 能识别的数组 --%>
                                    <c:forEach items="${hasRole}" var="hr">
                                        <input type="hidden" class="hasRole" value="${hr}"/>
                                    </c:forEach>

                                        <%-- 然后再给页面上的复选框赋值 --%>
                                    <c:forEach var="role" items="${roles}">
                                        ${role.name} <input class="roleId" type="checkbox" name="roleId" value="${role.id}"/><br>
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
                </div>
            </div>
        </div>

        <%-- script 节点不要使用自结束 --%>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.1.0.min.js"></script>
        <script type="text/javascript">
            $(function(){
                // 先把后台传递过来的 List 数组转换为 JS 能识别的数组
                var hasRoles = new Array();
                $(".hasRole").each(function(){
                   hasRoles.push($(this).val());
                });

                // 然后再给页面上的复选框赋值
                $(".roleId").each(function(){
                    var roleCheckbox = $(this);
                    var value = roleCheckbox.val();
                    if($.inArray(value,hasRoles)>=0){
                        roleCheckbox.attr("checked","checked");
                    }
                });

            });
        </script>
    </body>
</html>
