<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登陆</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <style>
      .min-container{
      	width:200px;
      }
    </style>
  </head>
  <body>
    <h1>这是登陆页面</h1><hr>
	<div class="container min-container">
	<form action="${pageContext.request.contextPath }/servlet/LoginServlet" method="post">
	  <div class="form-group">
	    <label for="inputId">学号</label>
	    <input type="text" class="form-control" name="inputId" id="inputId" placeholder="学号">
	    <h5></h5>
	  </div>
	  <div class="form-group">
	    <label for="inputPassword">密码</label>
	    <input type="password" class="form-control" name="inputPassword" id="inputPassword" placeholder="密码">
	  </div>
	  <button type="submit" class="btn btn-default">登陆</button>
	  <a href="${pageContext.request.contextPath }/servlet/IndexUIServlet" class="btn btn-default">返回</a>
	</form>
	<hr> 
	</div>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
  </body>
</html>
