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
  <title>添加入库信息</title>
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">

  <link rel="stylesheet" type="text/css" href="../css/commonv01.css"/>
  <link rel="stylesheet" type="text/css" href="../css/addv02.css"/>
  <script src="../js/jquery-1.11.3.min.js"></script>
  <%--<script src="js/commonv01.js"></script>--%>
  <script src="../js/addForInstore.js"></script>
</head>
<body>
  <div id="container">
    <input type="hidden" id="username" value=${param.username}>
    <div id="inner">
      <%--<s:form action="addInstoreSheet" namespace="/manage">--%>
      <div id="header">
        <h2>添加入库信息</h2>
        <div id="basicInfo">
          <span>领货学院:</span><input type="text" id="department"/><span class="tip">（*必填）</span><span class="validateDept"></span>
        </div>
      </div>
        <div id="pageBody">
          <a id="addProduct">添加材料</a>
          <table>
            <tr id="tableTitle">
              <th>编号</th>
              <th>名称</th>
              <th>规格</th>
              <th>单位</th>
              <th>价格</th>
              <th>供应商</th>
              <th>添加数量</th>
              <th>备注</th>
              <th>操作</th>
            </tr>
          </table>
        </div>
        <div id="footer">
          <input type="button" value="保存" id="save"/>
          <input type="button" value="取消" id="cancel"/>
        </div>
      <%--</s:form>--%>
    </div>
    <div id="outer1">
      <div class="inputInfo">
        <div class="dragZone">
          <a class="closeInput">关闭</a>
        </div>
        <h2>添加材料</h2>
        <p class="addInfo"><span>材料编号：</span><input type="text" id="productId1"/><span class="tip">（*必填）</span></p>
        <p class="validateProId"> </p>
        <p class="addInfo"><span>添加数量：</span><input type="text" id="productAmount1"/><span class="tip">（*必填）</span></p>
        <p class="validateAmount"> </p>
        <p class="addInfo">备注：</p><textarea id="remark1" rows="5" cols="30"></textarea>
        <div class="inputButton">
          <input type="button" id="inputOk1" value="确定"/>
          <input type="button" class="reset" value="重置"/>
          <input type="button" id="cancel1" value="取消"/>
        </div>
      </div>
    </div>
    <div id="outer2">
      <div class="inputInfo">
        <div class="dragZone">
          <a class="closeInput">关闭</a>
        </div>
        <h2>编辑材料</h2>
        <p class="addInfo"><span>材料编号：</span><input type="text" id="productId2"/><span class="tip">（*必填）</span></p>
        <p class="validateProId"> </p>
        <p class="addInfo"><span>添加数量：</span><input type="text" id="productAmount2"/><span class="tip">（*必填）</span></p>
        <p class="validateAmount"> </p>
        <p class="addInfo">备注：</p><textarea id="remark2" rows="5" cols="30"></textarea>
        <div class="inputButton">
          <input type="button" id="inputOk2" value="确定"/>
          <input type="button" class="reset" value="重置"/>
          <input type="button" id="cancel2" value="取消"/>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
