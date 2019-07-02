<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>科研项目材料入出库管理系统</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script src="../js/jquery-1.11.3.min.js"></script>
    <script src="../js/main.js"></script>
</head>
<body>
<div id="container">
    <input type="hidden" id="username" value=${username}>
    <div id="Header">
        <div id="timeGadget"></div>
        <p id="title">科研项目材料入出库管理系统</p>
        <ul>
            <li>
                <p id="info">欢迎您，${username}！</p>
            </li>
            <li>
                <p id="exit"><a href="/index.jsp">注销</a></p>
            </li>
        </ul>
    </div>
    <div id="PageBody">
        <div id="Sidebar">
            <%--<a id="InstoreManager"><p>入库管理</p></a>--%>
            <div id="choice1">
                <a class="flip" id="InstoreManager"><p>入库管理</p></a>
                <div class="panel" id="panel1">
                    <ul>
                        <li><a id="addInstoreInfo">添加入库信息</a></li>
                        <li>查询入库信息
                            <ul>
                                <li><a id="checkInById">按编号查询</a></li>
                                <li><a id="checkInByTime">按时间段查询</a></li>
                                <li><a id="checkInByDept">按领货学院查询</a></li>
                                <li><a id="checkInByChecker">按经手人查询</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="choice2">
                <a class="flip" id="OutstoreManager"><p>出库管理</p></a>
                <div class="panel" id="panel2">
                    <ul>
                        <li><a id="addOutstoreInfo">添加出库信息</a></li>
                        <li>查询出库信息
                            <ul>
                                <li><a id="checkOutById">按编号查询</a></li>
                                <li><a id="checkOutByTime">按时间段查询</a></li>
                                <li><a id="checkOutByDept">按领货学院查询</a></li>
                                <li><a id="checkOutByChecker">按经手人查询</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="choice3">
                <a class="flip" id="ProductManager"><p>材料信息管理</p></a>
                <div class="panel" id="panel3">
                    <ul>
                        <li><a id="addProductInfo">添加材料信息</a></li>
                        <li>查询材料信息
                            <ul>
                                <li><a id="checkProductById">按编号查询</a></li>
                                <li><a id="checkProductByName">按名称查询</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div id="choice4">
                <a class="flip" id="StoreAmountManager"><p>库存信息管理</p></a>
                <div class="panel" id="panel4">
                    <ul>
                        <li>查询库存信息
                            <ul>
                                <li><a id="checkStoreById">按材料编号查询</a></li>
                                <li><a id="checkStoreByName">按材料名称查询</a></li>
                                <li><a id="checkStoreByTime">按时间范围查询</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="MainBody">
            <iframe id="content">
            </iframe>
        </div>
    </div>
    <div id="Footer">
        <p>版权信息</p>
    </div>
</div>
</body>
</html>
