<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>别看今天闹得欢</title>   
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
   
  </head>
  
  <body class="container">
  
    <div class="jumbotron">
    <a class="btn btn-default btn-lg" href="${pageContext.request.contextPath }/servlet/IndexUIServlet" role="button">现在返回还来得及🐎？</a>
    	<hr>
	  <div class="alert alert-danger" role="alert"><h1>小心今后拉清单!</h1></div>
	  
	  <p>扛二百斤麦子十里山路不换肩</p>
	  <p><a class="btn btn-primary btn-lg" href="#" role="button">通商宽衣</a></p>

	</div>

  <table class="table table-bordered table-hover">
    <tr>
      <th>#id</th>
      <th>#name</th>
      <th>#work</th>
    </tr>
    
	  <c:forEach items="${requestScope.stuffs }" var="stf">
	  <tr>
	    <td>${stf.id }</td>
	    <td>${stf.name }</td>
	    <td>${stf.work }</td>	
	  </tr>
	  </c:forEach>
			
 </table>   
	
	 
	
 </body>

  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>
