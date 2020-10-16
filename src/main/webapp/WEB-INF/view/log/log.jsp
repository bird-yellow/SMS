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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
</head>
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
                <a class="dropdown-item" href="/common/updatePassword?id=${obj.id}&type=3">密码修改</a>
            </div>
        </li>
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="logDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-university fa-fw"></i></a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="logDropdown">
                <a class="dropdown-item" href="/log/clear?value=1">保留当天日志</a>
                <a class="dropdown-item" href="/log/clear?value=2">保留7天内日志</a>
                <a class="dropdown-item" href="/log/clear?value=3">保留30天内日志</a>
                <a class="dropdown-item" href="/log/clear?value=4">删除所有日志</a>
            </div>
        </li>
    </ul>
</nav>
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <div class="card mb-4">

                    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" method="post" action="/log/findBySql">
                        <div class="input-group">
                            <input class="form-control" name="username" name="username" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
                                <div class="input-group-append">
                                    <button class="btn btn-primary" type="submit"><i class="fas fa-search"></i></button>
                                </div>
                        </div>
                    </form>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <tr>
                                    <th>日志ID</th>
                                    <th>用户名</th>
                                    <th>执行操作</th>
                                    <th>操作时间</th>
                                    <th>操作结果</th>
                                    <th>IP地址</th>
                                    <td>操作</td>
                                </tr>
                                <c:forEach items="${pagers.datas}" var="data">
                                    <tr>
                                        <td>${data.id}</td>
                                        <td>${data.username}</td>
                                        <td>${data.operator}</td>
                                        <td>${data.time}</td>
                                        <td>${data.result}</td>
                                        <td>${data.ip}</td>
                                        <td>
                                            <a   class="btn  btn-danger" href="/log/deleteById?id=${data.id}">删除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="8">
                                        <div class="pagelist">
                                            <!--分页开始-->
                                            <pg:pager url="/log/findBySql" maxIndexPages="5" items="${pagers.total}" maxPageItems="8" export="curPage=pageNumber">
                                                <pg:last>
                                                    共${pagers.total}记录，共${pageNumber}页，
                                                </pg:last>
                                                当前第${curPage}页
                                                <pg:first>
                                                    <a href="${pageUrl}">首页</a>
                                                </pg:first>
                                                <pg:prev>
                                                    <a href="${pageUrl}">上一页</a>
                                                </pg:prev>
                                                <pg:pages>
                                                    <c:choose>
                                                        <c:when test="${curPage eq pageNumber}">
                                                            <font color="red">[${pageNumber}]</font>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="${pageUrl}">${pageNumber}</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </pg:pages>
                                                <pg:next>
                                                    <a href="${pageUrl}">下一页</a>
                                                </pg:next>
                                                <pg:last>
                                                    <c:choose>
                                                        <c:when test="${curPage eq pageNumber}">
                                                            <font color="red">尾页</font>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="${pageUrl}">尾页</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </pg:last>
                                            </pg:pager>
                                        </div>
                                    </td>
                                </tr>
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
<script src="js/scripts.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/datatables-demo.js"></script>
</body>
</html>

