<%--
  Created by IntelliJ IDEA.
  User: duck
  Date: 2020/9/18
  Time: 下午11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${ctx}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <script src="${ctx}/static/js/jquery-3.3.1.min.js"></script>
    <script src="${ctx}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <style>
        .row-center{
            text-align:center;
            margin-top: 130px;
        }
        .col-center {
            display:inline-block;
            float:none;
            text-align:left;
        }
    </style>
</head>
<body style="background-color: #93D1FF">
<div class="container" >
    <div  class="row  row-center" >
        <div  class="col-center">
            <form method="post" action="/login/login" id="form">
                    <div  style="background-color: #afd9ee; padding: 30px;width: 400px;border-radius: 8px;border-color: #00a3ff;border-width: 2px  ">
                        <div style="height: 50px;">
                            <h3 style="text-align: center;color: darkolivegreen; font-size: 35px;">用户登陆</h3>
                        </div>
                        <div  class="form-group ">
                            <input name="username" class="form-control"  placeholder="请输入您的用户名" id="username">
                        </div>

                        <div style="padding: 1px" class="form-group">
                            <input name="password" type="password" class="form-control " id="password" placeholder="请输入您的密码">
                        </div>
                        <ul class="list-inline">
                            <li>
                                <div class="form-group form-inline">
                                    <span>学生</span>
                                    <input type="radio" name="userType" value="1"/>
                                </div>
                            </li>
                            <li>
                                <div class="form-group form-inline">
                                    <span>教师</span>
                                    <input type="radio" name="userType" value="2" />
                                </div>
                            </li>
                            <li>
                                <div class="form-group form-inline">
                                    <span>管理员</span>
                                    <input type="radio" name="userType" value="3"/>
                                </div>
                            </li>
                        </ul>
                        <div class="submit">
                            <button style="border-radius:8px; "  class="btn btn-block btn-info" type="submit" id="submit">提交</button>
                        </div>
<%--                        <input type="submit" id="submit" value="提交" />--%>
                    </div>
            </form>
        </div>
    </div>
</div>
<%--    <script>--%>
<%--        $(function () {--%>
<%--                $("#submit").click(function () {--%>

<%--                            var username = $("#username").val();--%>
<%--                            var password = $("#password").val();--%>
<%--                            var userType = $('input:radio[name="userType"]:checked').val();--%>
<%--                            if(username.trim() == '' || password.trim() =='' || userType == null){--%>
<%--                                alert("请输入完整信息");--%>
<%--                                window.location.href="/login/uLogin";--%>
<%--                            }--%>
<%--                            else{--%>
<%--                                alert("跳转");--%>
<%--                                var data = $("#form").serialize();--%>
<%--                                 $.ajax({--%>
<%--                                     type:"post",--%>
<%--                                     url :"/login/login",--%>
<%--                                     data:data,--%>
<%--                                     dataType:"json",--%>
<%--                                     success:function (result) {--%>
<%--                                         alert("返回值=" );--%>
<%--                                        if(result == "1"){--%>
<%--                                            window.location.href="/login/sIndex";--%>
<%--                                        }--%>
<%--                                        else if(result =="2"){--%>
<%--                                            window.location.href="/login/tIndex";--%>
<%--                                        }--%>
<%--                                        else if(result == "3"){--%>
<%--                                            window.location.href="/login/mIndex";--%>
<%--                                        }--%>
<%--                                        else if(result == "4"){--%>
<%--                                            window.location.href="/login/uLogin";--%>
<%--                                        }--%>
<%--                                     }--%>
<%--                                 })--%>
<%--                            }--%>
<%--                })--%>
<%--        })--%>
<%--    </script> --%>
</body>
</html>
