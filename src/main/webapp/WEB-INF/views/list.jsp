<%--
  Created by IntelliJ IDEA.
  User: human
  Date: 2020-11-6
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <%--引入jQuery--%>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.12.4.min.js"></script>
    <%--引入bootstrap样式--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
    <%--搭--%>
    <div class="container">
<%--        标题--%>
        <div class="row">
            <div class="col-md-12">
                <h1>CRUD</h1>
            </div>
        </div>
<%--        按钮--%>
        <div class="row">
            <div class="col-md-4 col-md-offset-8">
                <button class="btn bg-primary">新增</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
<%--        显示--%>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover">
                    <tr>
                        <th>#</th>
                        <th>name</th>
                        <th>password</th>
                        <th>操作</th>
                    </tr>
                    <tr>
                        <th>1</th>
                        <th>test</th>
                        <th>123123</th>
                        <th>
                            <button class="btn bg-primary btn-sm">编辑</button>
                            <button class="btn btn-danger btn-sm">删除</button>
                        </th>
                    </tr>
                </table>
            </div>
        </div>
<%--        分页--%>
        <div class="row">
<%--            分页文字信息--%>
            <div class="col-md-6">
                当前记录数：xxx
            </div>
            <div class="col-md-6">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="#" aria-label="Previous">首页</a>
                        </li>
                        <li>
                            <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li>
                            <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <li>
                            <a href="#" aria-label="Previous">尾页</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</body>
</html>
