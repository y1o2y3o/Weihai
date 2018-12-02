<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>搅打云盘</title>   
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <script>
     function setFid(fileFid){
     	document.getElementById("inputFid").value = fileFid;
     }
     function setPath(path){
     	document.getElementById("inputPath").value = path;
     }
    </script>
  </head>
  
  <body>
  	<nav class="navbar navbar-default" role="navigation">
	    <div class="container-fluid"> 
	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse"
	                data-target="#example-navbar-collapse">
	            <span class="sr-only">切换导航</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	        </button>
	        <a class="navbar-brand"href="${pageContext.request.contextPath }/servlet/IndexUIServlet">主页</a>
	        <a class="navbar-brand" href="${sessionScope.lastURL}">返回</a></li>
	        <a class="navbar-brand"href="${pageContext.request.contextPath }/servlet/ViewBinServlet">回收站</a>
		      
		    
	    </div>
	    <div class="collapse navbar-collapse" id="example-navbar-collapse">
	        <ul class="nav navbar-nav">
	          
		        
		        <li><a href="" id="dd1" aria-hidden="true" data-toggle="modal" data-target="#modal2">新建文件夹</a></li>
		        <li><a href="" id="dd1" aria-hidden="true" data-toggle="modal" data-target="#modal1">上传文件</a></li>
		     
	            
	        </ul>
	    </div>
	    </div>
	</nav>
    
   	<div class="container" style="margin-top:100px">
	<div class="panel panel-primary">
	  <!-- Default panel contents -->
	  <div class="panel-heading">文件🐎系统</div>
	  <div class="panel-body">
	    <span>src: ${curPath }</span>
	  </div>
	
	  <!-- List group -->
	  <ul class="list-group">
	    
	    <c:forEach var="fn" items="${sessionScope.curFloderNames }">
	    	<li class="list-group-item"><a href="${pageContext.request.contextPath }/servlet/ViewDiskServlet?curPath=${curPath}${fn }/">
	    	<span class="glyphicon glyphicon-folder-close">&nbsp; </span>${fn }
	    	</a>
	    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal4" onclick="setPath('${sessionScope.curPath}${fn}/')">Remove</button>
	    	</li>
	    </c:forEach>
	    
	    <c:forEach var="fn" items="${sessionScope.curFiles }">
	    	<li class="list-group-item"><a href="${pageContext.request.contextPath }/servlet/DownloadFileServlet?filenameMd5=${fn.fid }.${fn.type}">
	    		&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-file">&nbsp; </span>${fn.name }.${fn.type }
	    	</a>
	    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal3" onclick="setFid('${fn.fid}')">Remove</button>
	    	</li>
	    </c:forEach>
	  </ul>
	</div>
	</div>
	<!-- Add Modal -->
	<form action="${pageContext.request.contextPath }/servlet/UploadFileServlet" method="post" enctype="multipart/form-data">
	<div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">上传一个文件</h4>
	      </div>
	      <div class="modal-body">
	        <div class="form-group">
		      <label for="inputFile">选择文件</label>
		      <input type="file" class="form-control" name="inputFile" id="inputFile" >
	      </div>
	      
	         
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>
	</div>
	</form>
	 
	<!-- Add Modal2 -->
	<form action="${pageContext.request.contextPath }/servlet/CreateFloderServlet" method="post">
	<div class="modal fade" id="modal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">新建文件夹🐎</h4>
	      </div>
	      <div class="modal-body">
	        <div class="form-group">
		      <label for="floderName">输入文件夹名</label>
		      <input type="text" class="form-control" name="floderName" id="floderName" value="sss">
	      	</div>
	      
	         
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>
	</div>
	</form>
	<!-- addModal3 -->
	<form  action="${pageContext.request.contextPath }/servlet/RemoveFileServlet" method="post">
	<div class="modal fade bs-example-modal-sm" id="modal3" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
 	 	<div class="modal-dialog modal-sm" role="document">
   		<div class="modal-content">	
	   		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h5 class="modal-title" id="myModalLabel">确定要移动此文件到回收站里🐎？</h5>
		    </div>
		   
		   <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">确定</button>
	       </div>	   
    	</div>
 			
  		</div>
    </div>
    <input type="hidden" class="form-control" name="inputFid" id="inputFid" >
   </form>
   
   <!-- addModal4 -->
	<form  action="${pageContext.request.contextPath }/servlet/DeleteFloderServlet" method="post">
	<div class="modal fade bs-example-modal-sm" id="modal4" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
 	 	<div class="modal-dialog modal-sm" role="document">
   		<div class="modal-content">	
	   		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h5 class="modal-title" id="myModalLabel">确定要移除此文件夹🐎？</h5>
		        	<br>文件夹下的所有文件将会进入回收站
		    </div>
		   <input type="hidden" class="form-control" name="inputPath" id="inputPath">
		   <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">删除文件夹</button>
	        
	       </div>	   
    	</div>			
  		</div>
    </div>
    
   </form>
   
  </body>

  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>
