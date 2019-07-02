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
  <title>根据ID查询入库单</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">

  <link rel="stylesheet" type="text/css" href="../css/query.css"/>
  <link rel="stylesheet" type="text/css" href="../css/commonv01.css"/>
  <script src="../js/jquery-1.11.3.min.js"></script>
  <script src="../js/commonv01.js"></script>
  <script src="../js/queryForInstore.js"></script>
  <script src="../js/checkInById.js"></script>
</head>
<body>
<div id="container">
  <input type="hidden" id="username" value=${username}>
  <h2> 根据ID查询入库单 </h2>
  <span>请输入要查询的入库单ID：</span><input type="text" id="instoreSheetId"><input type="button" value="查询" id="check">
</div>
</body>
</html>
