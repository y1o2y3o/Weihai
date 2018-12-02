<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>学籍注册</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim 和 Respond.js 是为了让 IE8 支持 HTML5 元素和媒体查询（media queries）功能 -->
    <!-- 警告：通过 file:// 协议（就是直接将 html 页面拖拽到浏览器中）访问页面时 Respond.js 不起作用 -->
    <!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
    <style>
      .red{
      	color:red;
      }
    </style>
  </head>
  <body>
    <h1>这是注册页面</h1><hr>
	<div class="container">
	<form method="post" action="${pageContext.request.contextPath }/servlet/RegisterStudentServlet" >
	  <div class="form-group">
	    <label for="InputName">请输入姓名</label>
	    <input type="text" class="form-control" name="InputName" id="InputName" placeholder="Account" value="${formbean.inputName }">
	    <h5 class="red"><c:out value="${formbean.errors.InputName }"/></h5>
	  </div>
	  <div class="form-group">
	    <label for="InputId">请输入学号（7位数字）</label>
	    <input type="text" class="form-control" name="InputId" id="InputId" placeholder="Account" value="${formbean.inputId }">
        <h5 class="red"><c:out value="${formbean.errors.InputId }"/></h5>	  
	  </div>
	  <div class="form-group">
	    <label for="InputPassword">请输入密码（6-20位字母和数字）</label>
	    <input type="password" class="form-control" name="InputPassword" id="InputPassword" placeholder="Password" value="${formbean.inputPassword }">
	    <h5 class="red"><c:out value="${formbean.errors.InputPassword }"/></h5>
	  </div>
	  <div class="form-group">
	    <label for="InputPassword2">再输一遍密码</label>
	    <input type="password" class="form-control" name="InputPassword2" id="InputPassword2" placeholder="Password" value="${formbean.inputPassword2 }">  
		<h5 class="red"><c:out value="${formbean.errors.InputPassword2 }"/></h5>  
	  </div>	
	  <div class="form-group">
	    <label for="InputLevel">输入你的学级（说明: 2015级就填数字 2015）</label>
	    <input type="text" class="form-control" name="InputLevel" id="InputLevel" placeholder="Name" value="${formbean.inputLevel }">
	  	<h5 class="red"><c:out value="${formbean.errors.InputLevel }"/></h5>
	  </div>
	  <div class="form-group" style="width:50%">
	  	<lavel for="InputId_major">选择你的专业</lavel>
	  	<select class="form-control" id="InputId_major" name="InputId_major">
	  	  <c:forEach var="major" items="${sessionScope.majors }">
	  	  <option value="${major.id }">${major.name }</option>
	  	  </c:forEach>
	  	</select>
	  </div>  
	  <button type="submit" class="btn btn-default">提交</button>
	  <a href="${pageContext.request.contextPath }/servlet/IndexUIServlet" class="btn btn-default">返回</a>
	</form>
	</div>
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
  </body>
</html>
