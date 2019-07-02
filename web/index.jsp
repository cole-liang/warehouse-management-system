<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" import="java.util.*"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%><%--导入了ss表单--%>
<!DOCTYPE html>

<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登录</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">

    <%--<script>--%>
      <%--var maxWd = window.innerWidth;--%>
      <%--var maxHt = window.innerHeight;--%>
      <%--function res() {--%>
        <%--$("#image").width($("#image").width() * window.innerWidth / maxWd);--%>
        <%--$("#image").height($('#image').height() * window.innerHeight / maxHt);--%>
        <%--maxWd = window.innerWidth;--%>
        <%--maxHt = window.innerHeight;--%>
      <%--}--%>
    <%--</script>--%>

    <link rel="stylesheet" type="text/css" href="css/login.css"/>
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script src="js/login.js"></script>
  </head>
  <body id="container"  onload="init()">
      <p id="test"></p>
      <div class="title">
        <h1>科研项目材料入出库管理系统</h1>
      </div>
      <div class="form">
        <s:form action="login" namespace="/manage">
          <p>
            <label class="label"> 用户名：<s:textfield name="username" type="text" size="20" /> </label>
          </p>
          <p>
            <label class="label"> <span>密码：</span><s:password name="password" type="password" size="20" /> </label>
          </p>
          <p class="login">
            <s:submit value="登录"></s:submit>
          </p>
        </s:form>
      </div>
  </body>
</html>
