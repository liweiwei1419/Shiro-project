<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>修改用户信息</title>
    </head>
    <body>
        <div class="container">
        <jsp:include page="inc.jsp"></jsp:include>
            <span></span>
            <div class="panel panel-info">
                <div class="panel-heading">修改用户信息</div>
                <div class="panel-body">
                    <sf:form method="post" modelAttribute="user" id="updateForm" class="form-horizontal" role="form">

                        <div class="form-group">
                            <label for="inputUsername" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <sf:input path="username" class="form-control" id="inputUsername" placeholder="请输入用户名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputNickname" class="col-sm-2 control-label">昵称</label>
                            <div class="col-sm-10">
                                <sf:input path="nickname" class="form-control" id="inputNickname" placeholder="请输入昵称"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <%--<sf:input path="password" class="form-control" id="inputPassword" placeholder="请输入密码"/>--%>
                                <input class="form-control" id="inputPassword" value="${user.password}" readonly/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputStatus" class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <sf:select path="status" class="form-control" id="inputStatus">
                                    <sf:option value="0">停用</sf:option>
                                    <sf:option value="1">启用</sf:option>
                                </sf:select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色</label>
                            <div class="col-sm-10">
                                <%-- 先把后台传递过来的 List 数组转换为 JS 能识别的数组 --%>
                                <c:forEach items="${hasRole}" var="hr">
                                    <input type="hidden" class="hasRole" value="${hr}"/>
                                </c:forEach>
                                <%-- 然后再给页面上的复选框赋值 --%>
                                <c:forEach var="role" items="${roles}">
                                    <div class="checkbox">
                                        <label>
                                            <input class="roleId" type="checkbox" name="roleId" value="${role.id}"/>
                                                ${role.name}
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input class="btn btn-default" role="button" type="submit" value="提交修改">
                                <input class="btn btn-default" role="button" type="reset" value="重置">
                            </div>
                        </div>
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
