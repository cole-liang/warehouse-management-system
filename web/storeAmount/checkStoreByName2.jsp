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
  <div id="inner">
    <h2> 根据材料名称查询库存信息 </h2>
    <span>请输入要查询的材料名称：</span><input type="text" id="name"><input type="button" value="查询" id="check">
    <table>
      <tr id="tableTitle">
        <th>年月</th>
        <th>材料编号</th>
        <th>材料名称</th>
        <th>库存量</th>
        <th>备注</th>
        <th>操作</th>
      </tr>
      <s:iterator id="storeAmounts" value="#session.storeAmounts">
        <tr>
          <td><s:property value="#storeAmounts[0]"/></td>
          <td><s:property value="#storeAmounts[1]"/></td>
          <td><s:property value="#storeAmounts[2]"/></td>
          <td><s:property value="#storeAmounts[3]"/></td>
          <td><s:property value="#storeAmounts[4]"/></td>
          <td><a class="details">编辑备注</a></td>
        </tr>
      </s:iterator>
    </table>
    <a id="prev">上一页</a><p id="page"></p><a id="next">下一页</a>
  </div>
  <div id="outer1">
    <div class="inputInfo">
      <div class="dragZone">
        <a class="closeInput">关闭</a>
      </div>
      <h2>编辑备注</h2>
      <textarea id="remark1" rows="5" cols="30"></textarea>
      <div class="inputButton">
        <input type="button" id="inputOk1" value="确定"/>
        <input type="button" class="reset" value="重置"/>
        <input type="button" id="cancel1" value="取消"/>
      </div>
    </div>
  </div>
</div>
</body>
</html>
