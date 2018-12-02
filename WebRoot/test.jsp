<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>较大云盘</title>   
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
   <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	  .green{color:green;}
	  .vertical{
	  	border-left:solid 2px green;
	  	border-right:solid 2px green;
	  }
	  .gray-border{
	  	border: solid 1px gray
	  }
	</style>
	<script>
     function setFid(fileFid){
     	document.getElementById("inputFid").value = fileFid;
     }
     function setPath(path){
     	document.getElementById("inputPath").value = path;
     }
    </script>
  </head>
  
<body class="vertical container">

  <nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">
        <img alt="Brand" src="...">
      </a>
    </div>
  </div>
</nav>
  <hr>
  
  
	

  
	<div class="container">
	
	  <!-- Nav tabs -->
	  <ul class="nav nav-tabs" role="tablist">
	    <li role="presentation"class="active"><a href="#apps" aria-controls="apps" role="tab" data-toggle="tab"><h5>搅打应用</h5></a></li>
	    
	  </ul>
	
	  <!-- Tab panes -->
	  <div class="tab-content">
	    <div role="tabpanel" class="tab-pane active" id="apps">
	   	  <br>
	      
			
			  <td>
			    <a href="${pageContext.request.contextPath }/servlet/ViewDiskServlet?curPath=home/"><button type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-hdd"></span>我的云盘</button></a>
			  </td>
			  <td>
			    <a href="${pageContext.request.contextPath }/servlet/ViewListServlet"><button type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-star-empty"></span>别看今天闹得欢</button></a>
			  </td>
			  <td>  	
			    <a href="${pageContext.request.contextPath }/servlet/ManageShaduleServlet"><button type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-plus"></span>管理课表</button></a>
			  </td>
			  <td>
			    <a href="${pageContext.request.contextPath }/servlet/ViewShaduleServlet"><button type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-search"></span>查看课表</button></a>
			  </td>

			</tr>
		 
	    </div>
	    
	  </div>
	
	</div>
</body>
  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>
