<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <%--引入jQuery--%>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.12.4.min.js"></script>
    <%--引入bootstrap样式--%>
    <link href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>
            <div class="modal-body">
                <%--                表单内容--%>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">EmpName</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="empName_add_input"
                                   placeholder="empName">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <input type="empEmail" name="empEmail" class="form-control" id="empEmail_add_input"
                                   placeholder="email@moy.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">gender</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender1_add_input" value="M" checked> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">deptName</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="departmentId" id="dept_add_select"></select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
            </div>
        </div>
    </div>
</div>


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
                <button class="btn bg-primary" id="emp_add_modal">新增</button>
                <button class="btn btn-danger">删除</button>
            </div>
        </div>
        <%--        显示--%>
        <div class="row">
            <div class="col-md-12">
                <table class="table table-hover" id="emps_table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>empName</th>
                        <th>empEmail</th>
                        <th>gender</th>
                        <th>departmentName</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>

        <%--        分页--%>
    <div class="row">
        <%--            分页文字信息--%>
        <div class="col-md-6" id="page_info_area">
        </div>
        <%--            分页条信息--%>
        <div class="col-md-6" id="page_nav_area">
        </div>
    </div>
</div>

<script type="text/javascript">
    //1 界面加载完成以后，再发送ajax请求拿到数据
    $(function () {
        //拿到分页信息，并跳转到首页
        to_page(1);
    });

    //返回指定页数的页面数据
    function to_page(pn) {
        $.ajax({
            url: "${APP_PATH}/emps",
            data: "pn=" + pn,
            type: "GET",
            success: function (result) {
                console.log(result);
                //1，解析并显示员工数据
                build_emps_table(result);
                //2，解析并显示分页信息
                build_page_info(result);
                //3，解析并显示分页条
                build_page_nav(result);
            }
        });
    }

    //1，解析并显示员工数据
    function build_emps_table(result) {
        //清空table表格
        $("#emps_table tbody").empty();
        var emps = result.extendInfo.pageInfo.list;
        $.each(emps, function (index, item) {
            var empIdTd = $("<td></td>").append(item.empId);
            var empNameTd = $("<td></td>").append(item.empName);
            var empEmail = $("<td></td>").append(item.empEmail);
            var gender = $("<td></td>").append(item.gender == "M" ? "男" : "女");
            var deptName = $("<td></td>").append(item.dept.deptName);
            var editBtn = $("<button></button>").addClass("btn bg-primary btn-sm")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑");
            var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
            var btn = $("<td></td>").append(editBtn).append(" ").append(delBtn);
            $("<tr></tr>").append(empIdTd)
                .append(empNameTd)
                .append(empEmail)
                .append(gender)
                .append(deptName)
                .append(btn)
                .appendTo("#emps_table tbody")
        });
    }

    //解析显示分页信息
    function build_page_info(result) {
        //清空分页信息
        $("#page_info_area").empty();
        $("#page_info_area").append("当前" +
            result.extendInfo.pageInfo.pageNum + "页,总" +
            result.extendInfo.pageInfo.pages + "页,总" +
            result.extendInfo.pageInfo.total + "条记录")
    }

    //解析显示分页条
    function build_page_nav(result) {
        //清空分页条
        $("#page_nav_area").empty();
        var ul = $("<ul></ul>").addClass("pagination");
        var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
        var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
        if (result.extendInfo.pageInfo.hasPreviousPage == false) {
            firstPageLi.addClass("disabled");
            prePageLi.addClass("disabled");
        } else {
            firstPageLi.click(function () {
                to_page(1);
            });
            prePageLi.click(function () {
                to_page(result.extendInfo.pageInfo.pageNum - 1);
            });
        }
        var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
        var lastPageLi = $("<li></li>").append($("<a></a>").append("尾页").attr("href", "#"));
        if (result.extendInfo.pageInfo.hasNextPage == false) {
            lastPageLi.addClass("disabled");
            nextPageLi.addClass("disabled");
        } else {
            lastPageLi.click(function () {
                to_page(result.extendInfo.pageInfo.pages)
            });
            nextPageLi.click(function () {
                to_page(result.extendInfo.pageInfo.pageNum + 1)
            });
        }
        ul.append((firstPageLi)).append(prePageLi);
        $.each(result.extendInfo.pageInfo.navigatepageNums, function (index, item) {
            var numLi = $("<li></li>").append($("<a></a>").append(item));
            if (result.extendInfo.pageInfo.pageNum == item) {
                numLi.addClass("active");
            }
            numLi.click(function () {
                to_page(item)
            });
            ul.append(numLi);
        });
        ul.append(nextPageLi).append(lastPageLi);
        var navEle = $("<nav></nav>").append(ul);
        navEle.appendTo("#page_nav_area");
    }

    function reset_form(ele) {
        $(ele)[0].reset();
        $(ele).find("*").removeClass("has-error has-success");
        $(ele).find(".help-block").text("");
    }


    $("#emp_add_modal").click(function () {
        //清除表单数据 完全
        reset_form("#empAddModal form")
        getDepts();
        $("#empAddModal").modal({
            backdrop: "static"
        });
    });

    function getDepts() {
        $.ajax({
            url: "${APP_PATH}/depts",
            type: "GET",
            success: function (result) {
                $("#dept_add_select")
                $.each(result.extendInfo.depts, function () {
                    var optionElement = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                    optionElement.appendTo("#dept_add_select")
                });
            }
        });
    };

    //校验用户名邮箱格式
    function validate_add_form() {
        //拿到数据 使用正则表达式
        var empName = $("#empName_add_input").val();
        var regName = /(^[a-z0-9_-]{6,16}$)|(^[\u2e80-\u9fff]{2,5})/;
        if (!regName.test(empName)) {
            //alert("请输入2-5位汉字或5-16数字字母组合");
            show_validate_msg("#empName_add_input", "error", "请输入2-5位汉字或5-16数字字母组合");
            return false;
        } else {
            show_validate_msg("#empName_add_input", "success", "");
        }
        ;
        //校验邮箱信息
        var email = $("#empEmail_add_input").val();
        var regEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
        if (!regEmail.test(email)) {
            //alert("邮箱格式不正确");
            show_validate_msg("#empEmail_add_input", "error", "邮箱格式不正确");
            return false;
        } else {
            show_validate_msg("#empEmail_add_input", "success", "");
        }
        ;
        return true;
    }

    //输入框下显示提示信息
    function show_validate_msg(ele, status, msg) {
        //清除当前元素校验状态
        $(ele).parent().removeClass("has-error has-success");
        $(ele).next("span").text("");
        if ("success" == status) {
            $(ele).parent().addClass("has-success");
            $(ele).next("span").text(msg);
        } else if ("error" == status) {
            $(ele).parent().addClass("has-error");
            $(ele).next("span").text(msg);
        }
    };

    //用户名查重
    $("#empName_add_input").change(function () {
        //发送ajax请求校验用户名是否重复
        var empName = this.value;
        $.ajax({
            url: "${APP_PATH}/checkName",
            data: "empName=" + empName,
            type: "POST",
            success: function (result) {
                if (result.code == 100) {
                    show_validate_msg("#empName_add_input", "success", "用户名可用")
                    $("#emp_save_btn").attr("ajax-va", "success")
                } else {
                    show_validate_msg("#empName_add_input", "error", result.extendInfo.va_msg)
                    $("#emp_save_btn").attr("ajax-va", "error")
                }
            }
        })
    });

    //点击保存
    $("#emp_save_btn").click(function () {

        //用户名邮箱的格式校验
        if (!validate_add_form()) {
            return false;
        }

        //用户名查重
        if ($(this).attr("ajax-va") == "error") {
            return false;
        }

        //将表单数据提交给服务器
        $.ajax({
            url: "${APP_PATH}/insert",
            type: "POST",
            data: $("#empAddModal form").serialize(),
            success: function (result) {
                alert(result.msg);
                $("#empAddModal").modal('hide');
                to_page(9999);
            }
        });
    });

</script>
</body>
</html>
