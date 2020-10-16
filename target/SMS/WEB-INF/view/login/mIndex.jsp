<%--
  Created by IntelliJ IDEA.
  User: duck
  Date: 2020/9/19
  Time: 下午8:54
  To change this template use File | Settings | File Templates.
--%>
<%@page language="java" contentType="text/html; charset=utf-8" %>
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
    <title>Dashboard - SB Admin</title>
    <link href="${ctx}/static/css/styles.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <a class="navbar-brand" href="/login/mIndex">管理员后台</a>
    <button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" method="post" action="/manage/search">
        </div>
    </form>

    <!-- Navbar-->
    <ul class="navbar-nav ml-auto ml-md-0  ">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="/manage/getInfo?id=${obj.id}">个人信息</a>
                <a class="dropdown-item" href="/log/findBySql">日志查看</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="/login/logout">退出</a>
                <a class="dropdown-item" href="/common/getVerificationCode?id=${obj.id}">密码修改</a>
                </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="logDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-university fa-fw"></i></a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="logDropdown">
                <a class="dropdown-item" href="/log/clear?value=1">清空日志</a>
                <a class="dropdown-item" href="/log/clear?value=2">保留当天日志</a>
                <a class="dropdown-item" href="/log/clear?value=3">保留7天内日志</a>
                <a class="dropdown-item" href="/log/clear?value=4">保留一个月内日志</a>
            </div>
        </li>
    </ul>
</nav>
<div id="layoutSidenav">
    <div id="layoutSidenav_nav">
        <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
            <div class="sb-sidenav-menu">
                <div class="nav">
                    <div class="sb-sidenav-menu-heading">核心</div>
                    <a class="nav-link" href="index.jsp">
                        <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                        Dashboard
                    </a>
                    <div class="sb-sidenav-menu-heading">interface</div>
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#one" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        学生管理
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="one" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/student/findBySql" target="right">
                                学生信息
                            </a>
                            <a class="nav-link" href="/student/add" target="right">
                                增加学生
                            </a>
                            <a class="nav-link" href="/student/findBySql" target="right">
                                👨‍🎓学生分数
                            </a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#two" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        教师管理
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="two" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/teacher/findBySql" target="right">
                                教师信息
                            </a>
                            <a class="nav-link" href="/teacher/add" target="right">
                                增加教师
                            </a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#three" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        班级管理
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="three" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/clazz/findBySql" target="right">
                                班级信息
                            </a>
                            <a class="nav-link" href="/clazz/add" target="right">
                                添加
                            </a>
                            <a class="nav-link" href="/clazz/echarts" target="right">
                                图表
                            </a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#four" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        年级管理
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="four" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/grade/findBySql" target="right">
                                查看年级
                            </a>
                            <a class="nav-link" href="/grade/add" target="right">
                                增加年级
                            </a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#five" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        院系管理
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="five" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/deparment/findBySql" target="right">
                                所有院系
                            </a>
                            <a class="nav-link" href="/deparment/add" target="right">
                                增加院系
                            </a>
                        </nav>
                    </div>
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#six" aria-expanded="false" aria-controls="collapseLayouts">
                        <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                        课程管理
                        <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                    </a>
                    <div class="collapse" id="six" aria-labelledby="headingOne" data-parent="#sidenavAccordion">
                        <nav class="sb-sidenav-menu-nested nav">
                            <a class="nav-link" href="/course/findBySql" target="right">
                                所有课程
                            </a>
                            <a class="nav-link" href="/course/add" target="right">
                                添加院系课程
                            </a>
                            <a class="nav-link" href="/course/addOpen" target="right">
                                添加公开课
                            </a>
                        </nav>
                    </div>
                    <div class="sb-sidenav-menu-heading">其他</div>
                    <a class="nav-link" href="WEB-INF/view/charts.jsp">
                        <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                        图表
                    </a>
                    <a class="nav-link" href="WEB-INF/view/tables.jsp">
                        <div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>
                        表格
                    </a>
                </div>
            </div>

        </nav>
    </div>
    <div style="background:#fff;position:fixed;border-left:solid 1px #b5cfd9;right:0;bottom:0;top:110px;left:180px;padding:15px;padding-right: 0px;padding-bottom:0px;overflow:auto;border-top:1px solid #b5cfd9;padding-right: 15px;">
        <iframe name="right" scrolling="auto" frameborder="0" width="100%" height="100%"></iframe>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="static/js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="static/assets/demo/chart-area-demo.js"></script>
<script src="static/assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="static/assets/demo/datatables-demo.js"></script>
</body>
</html>

