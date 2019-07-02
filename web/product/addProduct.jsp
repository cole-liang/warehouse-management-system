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
    <title>添加材料信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <link rel="stylesheet" type="text/css" href="../css/addProductv01.css"/>
    <script src="../js/jquery-1.11.3.min.js"></script>
    <script src="../js/addProduct.js"></script>
</head>
<body>
<h2>添加材料信息</h2>
<p class="addInfo"><span>材料编号：</span><input type="text" id="productId"/><span class="tip">（*必填）</span><span class="validate" id="validateProId"></span></p>
<p class="addInfo"><span>材料名称：</span><input type="text" id="productName"/><span class="tip">（*必填）</span><span class="validate" id="validateProName"></span></p>
<p class="addInfo"><span>材料规格：</span><input type="text" id="model"/><span class="tip">（*必填）</span><span class="validate" id="validateModel"></span></p>
<p class="addInfo"><span>材料单位：</span><input type="text" id="unit"/><span class="tip">（*必填）</span><span class="validate" id="validateUnit"></span></p>
<p class="addInfo"><span>材料价格：</span><input type="text" id="price"/><span class="tip">（*必填）</span><span class="validate" id="validatePrice"></span></p>
<p class="addInfo"><span>供应商：</span><input type="text" id="supplier"/><span class="tip">（*必填）</span><span class="validate" id="validateSupplier"></span></p>
<p class="validateProInfo"></p>
<div class="inputButton">
    <input type="button" id="inputOk1" value="确定"/>
    <input type="button" class="reset" value="重置"/>
    <input type="button" id="cancel1" value="取消"/>
</div>
</body>
</html>
