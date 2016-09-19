<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>更改权限页面</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="inc.jsp"/>
            <div class="panel panel-danger">
                <div class="panel-heading">更改权限</div>
                <div class="panel-body">
                    <sf:form method="post" modelAttribute="resource" id="resourceForm" class="form-horizontal" role="form">
                        <div class="form-group">
                            <label for="inputName" class="col-sm-2 control-label">权限名称：</label>
                            <div class="col-sm-10">
                                <sf:input path="name" class="form-control" id="inputName" placeholder="请输入权限名称"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPermission" class="col-sm-2 control-label">权限 permission ：</label>
                            <div class="col-sm-10">
                                <sf:input path="permission" class="form-control" id="inputPermission" placeholder="请输入权限 permission 字符串"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputUrl" class="col-sm-2 control-label">权限 url ：</label>
                            <div class="col-sm-10">
                                <sf:input path="url" class="form-control" id="inputUrl" placeholder="请输入权限 url"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">修改</button>
                                <button type="submit" class="btn btn-default">重置</button>
                            </div>
                        </div>
                    </sf:form>
                </div>
            </div>
        </div>
    </body>
</html>
