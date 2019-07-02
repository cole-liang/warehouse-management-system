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
  <title>入库管理</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">

  <link rel="stylesheet" type="text/css" href="../css/query.css"/>
  <link rel="stylesheet" type="text/css" href="../css/commonv01.css"/>
  <script src="../js/jquery-1.11.3.min.js"></script>
  <script src="../js/commonv01.js"></script>
  <script src="../js/queryForInstore.js"></script>
  <script src="../js/instoreManagerv01.js"></script>
</head>
<body>
<div id="container">
  <input type="hidden" id="username" value=${username}>
  <input type="hidden" id="userLevel" value=${level}>
  <h2> 入库信息一览 </h2>
  <table>
    <tr id="tableTitle">
      <th>编号</th>
      <th>入库时间</th>
      <th>领货学院</th>
      <th>经手人</th>
      <th>最后修改时间</th>
      <th>修改人</th>
      <th>操作</th>
    </tr>
    <s:iterator value="#session.instoreSheets">
      <tr>
        <td><s:property value="instoreSheetId"/></td>
        <td><s:property value="dateAt"/></td>
        <td><s:property value="department"/></td>
        <td><s:property value="checker"/></td>
        <td><s:property value="modifyDateAt"/></td>
        <td><s:property value="modifier"/></td>
        <td><a class="details">详细</a><a class="delete">删除</a></td>
      </tr>
    </s:iterator>
  </table>
  <a id="prev">上一页</a><p id="page"></p><a id="next">下一页</a>
</div>
</body>
</html>
