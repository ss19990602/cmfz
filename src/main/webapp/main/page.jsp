<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="../login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <%--引入jquery的css--%>
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <%--引入bootstrap的js--%>
    <script src="../login/assets/bootstrap/js/bootstrap.min.js"></script>
    <%--引入jqgrid的核心js--%>
    <script src="../jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
    <%--引入jqgridg国际化js--%>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <script src="../kindeditor/kindeditor-all-min.js"></script>
    <script src="../kindeditor/lang/zh-CN.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script src="../js/china.js"></script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">

            <h2 style="color: #9d9d9d">持明法州管理系统</h2>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <c:if test="${sessionScope.login == null}">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="${pageContext.request.contextPath}/login/login.jsp"><h5>登录</h5></a></li>
                </ul>
            </c:if>
            <c:if test="${sessionScope.login != null}">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><h5>欢迎: <span id="dl" style="color: #55acee">${sessionScope.login.username}</span>
                    </h5></a></li>
                    <li><a href="${pageContext.request.contextPath}/cmfz/Logout"><h6>退出登录<span
                            class="glyphicon glyphicon-share"></span></h6></a></li>
                </ul>
            </c:if>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <!--左侧手风琴-->
        <div class="col-sm-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./lg/user.jsp')">用户分布</a>
                        </div>
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./lg/user1.jsp')">用户注册走势</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="">上师列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./lg/article.jsp')">文章列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./lg/album.jsp')">专辑列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./lg/picture.jsp')">图片列表</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-10" id="lay-right">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <br>
                        <h2 style="color: #9d9d9d">持明法州管理系统</h2>
                        <br>
                    </div>
                </div>
            </nav>
            <img src="../img/shouye.jpg" class="img-responsive" alt="Responsive image">
        </div>
    </div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <center>@百知教育baizhi@zparkhr.com.cn</center>

    </div>
</nav>
</body>
</html>