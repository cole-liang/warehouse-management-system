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
  <title>材料信息一览</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">

  <link rel="stylesheet" type="text/css" href="../css/query.css"/>
  <link rel="stylesheet" type="text/css" href="../css/commonv01.css"/>
  <link rel="stylesheet" type="text/css" href="../css/addv02.css"/>
  <link rel="stylesheet" type="text/css" href="../css/modifyForProduct.css"/>
  <script src="../js/jquery-1.11.3.min.js"></script>
  <script src="../js/commonv01.js"></script>
  <script src="../js/queryForProduct.js"></script>
</head>
<body>
<div id="container">
  <div id="inner">
    <h2> 材料信息一览 </h2>
    <table>
      <tr id="tableTitle">
        <th>编号</th>
        <th>名称</th>
        <th>规格</th>
        <th>单位</th>
        <th>价格</th>
        <th>供应商</th>
        <th>操作</th>
      </tr>
      <s:iterator id="products" value="#session.products">
        <tr>
          <td><s:property value="productId"/></td>
          <td><s:property value="productName"/></td>
          <td><s:property value="model"/></td>
          <td><s:property value="unit"/></td>
          <td><s:property value="price"/></td>
          <td><s:property value="supplier"/></td>
          <td><a class="details">编辑</a></td>
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
      <h2>编辑材料</h2>
      <p class="addInfo"><span>材料编号：</span><input type="text" id="productId" disabled="true"/>
      <p class="addInfo"><span>材料名称：</span><input type="text" id="productName"/><span class="tip">（*必填）</span></p>
      <p class="addInfo"><span>材料规格：</span><input type="text" id="model"/><span class="tip">（*必填）</span></p>
      <p class="addInfo"><span>材料单位：</span><input type="text" id="unit"/><span class="tip">（*必填）</span></p>
      <p class="addInfo"><span>材料价格：</span><input type="text" id="price"/><span class="tip">（*必填）</span></p>
      <p class="addInfo"><span>供应商：</span><input type="text" id="supplier"/><span class="tip">（*必填）</span></p>
      <p class="validateProInfo"></p>
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
