<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Page Title - SB Admin</title>
    <link href="${ctx}/static/css/styles.css" rel="stylesheet"/>
    <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script>
        $(function () {
                $("#teacher").change(function () {
                        var value  = $(this).val();
                        $.ajax({
                            type:"post",
                            url :"/teacher/load",
                            data : {id :value},
                            dataType :"json",
                            success :function (result) {
                                var content ="";
                                content += "<table><tr><th>教师名</th><th>手机号</th><th>所在班级</th><th>所在年级</th>" +
                                    "<th>所在部门</th><th>教授课程</th>";
                                content += "<tr><td>" + result.username +"</td><td>" + result.phone+"</td>"+
                                "<td>"+result.clazz.name+"</td>" + "<td>" + result.grade.name +"</td>" +
                                "<td>" +result.deparment.name +"</td><td>" + result.course.name+"</td></tr></table>";

                                $("#info").html(content);
                                $("#cid").val(result.clazz.id);
                                $("#gid").val(result.grade.id);
                                $("#dpid").val(result.deparment.id);
                                $("#tid").val(result.id);
                            }
                        })
                })
        })
    </script>
</head>
<body class="bg-primary">
<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-7">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">添加学生信息</h3></div>
                            <div class="card-body">
                                <form method="post" action="/student/exAdd">
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <select id="teacher">
                                                   <option>选择班主任</option>
                                                    <c:forEach items="${teacher}" var="data">
                                                        <option value="${data.id}">${data.username}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group" id="info">
                                            </div>

                                            <input type="hidden" name="cId" id="cid" />
                                            <input type="hidden" name="gId" id="gid" />
                                            <input type="hidden" name="dpId" id="dpid" />
                                            <input type="hidden" name="tId"  id="tid" />

                                            <div class="form-group ">
                                                <label class="small mb-1" for="inputFirstName">姓名</label>
                                                <input class="form-control py-4" id="inputFirstName" name="username" placeholder="学生姓名" />
                                            </div>
                                            <div class="form-group ">
                                                <label class="small mb-1" >密码</label>
                                                <input class="form-control py-4"  name="password" placeholder="学生密码" />
                                            </div>
                                            <div class="form-group">
                                                <label class="small mb-1">性别</label>
                                                男: <input class=" " name="sex" value="男" type="radio"> &nbsp;&nbsp;
                                                女: <input class="" name="sex" value="女" type="radio">
                                            </div>
                                            <div class="form-group ">
                                                <label class="small mb-1" >手机号</label>
                                                <input class="form-control py-4"  name="phone" placeholder="手机号" />
                                            </div>
                                            <div class="form-group ">
                                                <label class="small mb-1" >民族</label>
                                                <input class="form-control py-4"  name="nation" placeholder="汉族" />
                                            </div>
                                            <div class="form-group ">
                                                <label class="small mb-1" >籍贯</label>
                                                <input class="form-control py-4"  name="nativePlace" placeholder="籍贯" />
                                            </div>
                                            <div class="form-group">
                                                <label class="small mb-1" >邮箱</label>
                                                <input class="form-control py-4"  name="email" placeholder="邮箱" />
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group mt-4 mb-0"><input  class="btn btn-primary btn-block" type="submit" value="添加"/></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

</div>
</div>
</body>
</html>