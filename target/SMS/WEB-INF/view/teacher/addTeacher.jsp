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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
    <script>
        $(function () {
                $("#deparment").change(function () {
                        var value = $(this).val();
                        //将dpId 传递到 teacher/findBySqlByClazzReturnGrade,返回可选的年级
                        $.ajax({
                            type:"post",
                            data:{id:value},
                            url :"/teacher/findBySqlByClazzReturnGrade",
                            dataType:"json",
                            success:function (result) {
                                var content = "";
                                content += "<option>选择年级</option>";
                                for(var i = 0;i < result.length;i++){
                                    content += "<option value="+result[i].id +">" + result[i].name +"</option>";
                                }
                                // 将数据展示到html组件中
                                $("#grade").html(content);

                                // $("#abc").val("123");给id=abc 的组件赋值
                            }
                        });
                        //根据院系,找到这个院系下的所有课程
                        $.ajax({
                            type:"post",
                            url :"/teacher/getCourseByDeparmentId",
                            data:{id:value},
                            dataType :"json",
                            success : function (result) {
                                    var content = "";
                                    content += "<option>选择课程</option>";
                                    for(var i = 0; i < result.length;i++){
                                        content += "<option value=" +result[i].id +">" +result[i].name +"</option>";
                                    }
                                    $("#course").html(content);
                            }
                        })
                });
                // 根据年级 和 部门 选择班级
                $("#grade").change(function () {
                    var v1 = $("#deparment").val();
                    var v2 = $(this).val();
                    $.ajax({
                        type:"post",
                        url :"/teacher/findBySqlByDepar",
                        data:{dpId:v1,gId:v2},
                        dataType:"json",
                        success:function (result) {
                            var content ="";
                            content += "<option>选择班级</option>";
                            for(var i = 0;i < result.length;i++){
                                content += "<option value=" + result[i].id + ">" + result[i].name +"</option>";
                            }
                            $("#clazz").html(content);
                        }
                    })
                });
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
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">添加班主任信息</h3></div>
                            <div class="card-body">
                                <form method="post" action="/teacher/exAdd">
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <select name="dpId" id="deparment">
                                                    <option>选择部门</option>
                                                    <c:forEach items="${deparment}" var="data">
                                                        <option value="${data.id}">${data.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>选择年级</label>
                                                <select name="gId" id="grade">

                                                </select>
                                            </div>
                                            <%--                                            教师选择课程,同一个课程最多有三个讲师--%>
                                            <div class="form-group">
                                                <label>选择课程</label>
                                                <select name="crId" id="course">

                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>选择班级</label>
                                                <select name="cId" id="clazz">
                                                </select>
                                            </div>

                                            <div class="form-group ">
                                                <label class="small mb-1" for="inputFirstName">教师名</label>
                                                <input class="form-control py-4" id="inputFirstName" name="username" placeholder="教师" />
                                            </div>
                                            <div class="form-group ">
                                                <label class="small mb-1" >密码</label>
                                                <input class="form-control py-4"  name="password" placeholder="密码" />
                                            </div>
                                            <div class="form-group ">
                                                <label class="small mb-1" >手机号</label>
                                                <input class="form-control py-4"  name="phone" placeholder="手机号" />
                                            </div>
                                            <div class="form-group">
                                                <label class="smaill mb-1">邮箱</label>
                                                <input class="form-control py-4" name="email" placeholder="邮箱"/>
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
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${ctx}/static/js/scripts.js"></script>
</body>
</html>