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
  <title>根据材料名称查询材料信息</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">

  <link rel="stylesheet" type="text/css" href="../css/query.css"/>
  <link rel="stylesheet" type="text/css" href="../css/commonv01.css"/>
  <link rel="stylesheet" type="text/css" href="../css/addv02.css"/>
  <link rel="stylesheet" type="text/css" href="../css/modifyForProduct.css"/>
  <link rel="stylesheet" type="text/css" href="../css/searchBoxv01.css"/>
  <script src="../js/jquery-1.11.3.min.js"></script>
  <script src="../js/commonv01.js"></script>
  <script src="../js/queryForProduct.js"></script>
  <script src="../js/checkProductByName.js"></script>
  <script src="../js/searchBox.js"></script>
</head>
<body>
<div id="container">
  <div id="inner">
    <h2> 根据材料名称查询材料信息 </h2>
    <div id="search">
      <span id="textZone">请输入要查询的材料名称：</span><input type="text" id="searchBox"><input type="button" value="查询" id="check">
    </div>
    <div id="displayZone">
      <ul>
      </ul>
    </div>
  </div>
</div>
</body>
</html>
