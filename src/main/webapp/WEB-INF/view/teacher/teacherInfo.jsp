<%--
  Created by IntelliJ IDEA.
  User: duck
  Date: 2020/9/22
  Time: 下午11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pg" uri="/WEB-INF/tlds/pager-taglib.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Tables - SB Admin</title>
    <link href="${ctx}/static/css/styles.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="${ctx}/static/js/jquery-1.3.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
</head>

<div id="layoutSidenav">
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">

                <div class="card mb-4">

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <tr>
                                    <th>教师ID</th>
                                    <th>名字</th>
                                    <th>电话号码</th>
                                    <th>所带班级</th>
                                    <th>年级</th>
                                    <th>所在系</th>
                                    <th>教授课程</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach  items="${pagers.datas}" var="data">
                                    <tr>
                                        <td>${data.id}</td>
                                        <td>${data.username}</td>
                                        <td>${data.phone}</td>
                                        <td>${data.clazz.name}</td>
                                        <td>${data.grade.name}</td>
                                        <td>${data.deparment.name}</td>
                                        <td>${data.course.name}</td>
                                        <td>
                                            <a href="/teacher/deleteById?id=${obj.id}">
                                                <button type="button" class="btn btn-danger">删除</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </main>

    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="${ctx}/static/js/scripts.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="${ctx}/static/assets/demo/datatables-demo.js"></script>
</body>
</html>
