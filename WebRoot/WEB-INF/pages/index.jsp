<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>南交威校园网</title>   
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
  </head>
  
<body class="vertical container">
	<nav class="navbar navbar-default " role="navigation">
	    <div class="container-fluid"> 
	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse"
	                data-target="#example-navbar-collapse">
	            <span class="sr-only">切换导航</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            
	        </button>
	        <a class="navbar-brand" >南郊威校园网</a>
	    </div>
	    <div class="collapse navbar-collapse" id="example-navbar-collapse">
	        <ul class="nav navbar-nav">
		       
		        
		        
	        </ul>
	    </div>
	    </div>
	</nav>
 <small>欢迎🐶学生 <span class="green">${student.name }</span></small>
  <div class="pull-right gray-border">
	  <ul class="nav nav-tabs">
	    <c:choose>
	      <c:when test="${sessionScope.student == null}">
	        <li role="presentation"><a href="${pageContext.request.contextPath }/servlet/LoginUIServlet">登陆</a></li>
	    	<li role="presentation"><a href="${pageContext.request.contextPath }/servlet/RegisterStudentUIServlet">注册</a></li>	  
	      </c:when>
	      <c:otherwise>
	        <li role="presentation" ><a href="${pageContext.request.contextPath }/servlet/LogoutServlet">注销</a></li>
	      </c:otherwise>
	    </c:choose>
	    
	    
	  </ul>
  </div> <hr>
  
  
	

  
	<div>
	
	  <!-- Nav tabs -->
	  <ul class="nav nav-tabs" role="tablist">
	    <li role="presentation" class="active"><a href="#apps" aria-controls="apps" role="tab" data-toggle="tab"><h5>搅打应用</h5></a></li>
	    <li role="presentation" ><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab"><h5>学院动向</h5></a></li>
	    <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab"><h5>集体保研</h5></a></li>
	    <li role="presentation"><a href="#settings" aria-controls="settings" role="tab" data-toggle="tab"><h5>工会</h5></a></li>
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

			
		
	    </div>
	    <div role="tabpanel" class="tab-pane" id="profile"><br>
	      <a href="${pageContext.request.contextPath }/zjyjdyl.jsp"><button type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-book"></span>张晋豫经典语录</button></a>
	    </div>
	    <div role="tabpanel" class="tab-pane" id="messages">...</div>
	    <div role="tabpanel" class="tab-pane" id="settings">...
	    	
			<a href="${pageContext.request.contextPath }/servlet/ViewFactoryServlet"><button type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-hdd"></span>学生组织内幕</button></a>
			 	 
	    </div>
	  </div>
	
	</div>
</body>
  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>
