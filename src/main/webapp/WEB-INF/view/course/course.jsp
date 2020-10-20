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
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/static/css/styles.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="${ctx}/static/js/jquery-1.3.1.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
    <script>

    </script>
</head>
<body>
<div id="layoutSidenav">
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid">
                <div class="card mb-4">
                    <form class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0" method="post" action="/clazz/clazzInfo">
                        <div class="input-group">
                            <input class="form-control" name="name" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="submit"><i class="fas fa-search"></i></button>
                            </div>
                        </div>
                    </form>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <tr>
                                    <th>院系</th>
                                    <th>课程</th>
                                    <th>课时</th>
                                    <th>操作</th>
                                </tr>
                                <c:forEach   items="${pagers.datas}" var="data">
                                    <tr>
                                        <td>
                                            <c:if test="${data.deparment  == null}">学校公开课</c:if>
                                            <c:if test="${data.deparment != null}">${data.deparment.name}</c:if>
                                        </td>
                                        <td>${data.name}</td>
                                        <td>${data.time}</td>
                                        <td>
                                            <c:if test="${data.deparment == null}">
                                                <a href="/course/updateById?id=${data.id}&type=1">
                                                    <button type="button" class="btn btn-warning">修改</button>
                                                </a>
                                            </c:if>
                                            <c:if test="${data.deparment !=null}">
                                                <a href="/course/updateById?id=${data.id}&type=2">
                                                    <button type="button" class="btn btn-warning">修改</button>
                                                </a>
                                            </c:if>
                                            <a href="/course/deleteById?id=${data.id}">
                                                <button type="button" class="btn btn-danger">删除</button>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="8">
                                        <div class="pagelist">
                                            <!--分页开始-->
                                            <pg:pager url="/course/findBySql" maxIndexPages="5" items="${pagers.total}" maxPageItems="8" export="curPage=pageNumber">
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
<script src="${ctx}/static/js/scripts.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="${ctx}/static/assets/demo/datatables-demo.js"></script>
</body>
</html>
