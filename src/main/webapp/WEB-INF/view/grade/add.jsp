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
                $("#deparment").change(function () {
                        var value = $(this).val();
                        $.ajax({
                            type :"post",
                            url :"/grade/findBySqlByDepar",
                            data:{id:value},
                            dataType: "json",
                            success : function (result) {
                                var content ="";
                                content += "<option>选择年级</option>"
                                for(var i = 0; i < result.length;i++){
                                    // alert(result[i].id + "年级名" + result[i].name);
                                    content += "<option value=" + result[i].name +">" + result[i].name + "</option>";
                                }
                                $("#grade").html(content);
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
                                <form method="post" action="/grade/exAdd">
                                    <div class="form-row">
                                        <div class="col-md-6">
                                            <label>选择院系</label>
                                            <div class="form-group">
                                                <select id="deparment" name="dpId">
                                                    <option>选择院系</option>
                                                    <c:forEach items="${deparment}" var="data">
                                                        <option value="${data.id}">${data.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>选择年级</label>
                                                <select id="grade" name="name"></select>
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