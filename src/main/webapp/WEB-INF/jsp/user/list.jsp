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
                    <td >
                        【<a class="status" data-id="${user.id}" data-status="${user.status}">启用</a>】
                    </td>
                    <td>
                        <a href="#">更新用户信息</a>
                        <a href="#">查询用户权限</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <%-- 不要使用自结束 --%>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/jquery-3.1.0.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".status").each(function(){
                var status = $(this).attr("data-status");
                var oper = $(this);
                if(status == 1){
                    oper.text("禁用");
                }else {
                    oper.text("启用");
                }
            });

            $(".status").on("click",function(){
                var oper = $(this);
                var user_id = oper.attr("data-id");
                var user_status = oper.attr("data-status");
                var update_status = (user_status == 1 ? 0: 1);
                $.post("${pageContext.request.contextPath}/admin/user/update",
                        {
                            id:user_id,
                            status:update_status
                        },function(data){
                            if(data.success){
                                alert("修改状态成功！");
                                var oper_name = (update_status == 1 ? "禁用":"启用");
                                oper.attr("data-status",update_status);
                                oper.text(oper_name);
                            }else{
                                alert("失败");
                            }
                        }
                );

            });
        })
    </script>
</body>
</html>
