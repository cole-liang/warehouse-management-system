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
  <title>根据材料名称查询库存信息</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">

  <link rel="stylesheet" type="text/css" href="../css/query.css"/>
  <link rel="stylesheet" type="text/css" href="../css/commonv01.css"/>
  <link rel="stylesheet" type="text/css" href="../css/addv02.css"/>
  <link rel="stylesheet" type="text/css" href="../css/modifyForStoreAmount.css"/>
  <script src="../js/jquery-1.11.3.min.js"></script>
  <script src="../js/commonv01.js"></script>
  <script src="../js/queryForStoreAmount.js"></script>
  <script src="../js/checkStoreByName.js"></script>
</head>
<body>
<div id="container">
  <h2> 根据材料名称查询库存信息 </h2>
  <span>请输入要查询的材料名称：</span><input type="text" id="name"><input type="button" value="查询" id="check">
</div>
</body>
</html>
