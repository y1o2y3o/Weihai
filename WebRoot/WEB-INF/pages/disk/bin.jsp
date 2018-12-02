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
     function setType(fileType){
     	document.getElementById("inputType").value = fileType;
     }
     
     function setFid2(fileFid){
     	document.getElementById("inputFid2").value = fileFid;
     }
    </script>
  </head>
  
  <body>
  <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	    <div class="container-fluid"> 
	    <div class="navbar-header">
	        <button type="button" class="navbar-toggle" data-toggle="collapse"
	                data-target="#example-navbar-collapse">
	            <span class="sr-only">切换导航</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            
	        </button>
	        <a class="navbar-brand" href="${sessionScope.lastURL}">返回</a></li>
	    </div>
	    <div class="collapse navbar-collapse" id="example-navbar-collapse">
	        <ul class="nav navbar-nav">
		       
		        <li class="active"><a href="#" data-toggle="modal" data-target="#modal5">清空回收站</a></li>
		        
	        </ul>
	    </div>
	    </div>
	</nav>
    
   	<div class="container" style="margin-top:100px">
	<div class="panel panel-primary">
	  <!-- Default panel contents -->
	  <div class="panel-heading">回收站系统</div>
	  <div class="panel-body">
	    <span>src: bin/</span>
	  </div>
	
	  <!-- List group -->
	  <ul class="list-group">
	        
	    <c:forEach var="fn" items="${sessionScope.binFiles }">
	    	<li class="list-group-item"><a href="">
	    		<span class="glyphicon glyphicon-file"></span>${fn.name }.${fn.type }
	    	</a>
	    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal3" onclick="setFid('${fn.fid}');setType('${fn.type}')">Delete</button>
	    	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal4" onclick="setFid2('${fn.fid}')">Restore</button>
	    	</li>
	    </c:forEach>
	  </ul>
	</div>
	</div>
	
	 
	<!-- Delete file addModal3 -->
	<form  action="${pageContext.request.contextPath }/servlet/DeleteFileServlet" method="post">
	<div class="modal fade bs-example-modal-sm" id="modal3" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
 	 	<div class="modal-dialog modal-sm" role="document">
   		<div class="modal-content">	
	   		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h5 class="modal-title" id="myModalLabel">确定要彻底删除此文件🐎？</h5>
		    </div>
		   
		   <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">确定</button>
	       </div>	   
    	</div>
 			
  		</div>
    </div>
    <input type="hidden" class="form-control" name="inputFid" id="inputFid" >
    <input type="hidden" class="form-control" name="inputType" id="inputType" >
   </form>
   
   <!-- restore file addModal4 -->
	<form  action="${pageContext.request.contextPath }/servlet/RestoreFileServlet" method="post">
	<div class="modal fade bs-example-modal-sm" id="modal4" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
 	 	<div class="modal-dialog modal-sm" role="document">
   		<div class="modal-content">	
	   		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h5 class="modal-title" id="myModalLabel">确定要还原此文件🐎？</h5>
		    </div>
		   
		   <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">还原</button>
	       </div>	   
    	</div>
 			
  		</div>
    </div>
    <input type="hidden" class="form-control" name="inputFid" id="inputFid2" >
   </form>
   
   <!-- restore file addModal5 -->
	<form  action="${pageContext.request.contextPath }/servlet/ClearBinServlet" method="post">
	<div class="modal fade bs-example-modal-sm" id="modal5" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
 	 	<div class="modal-dialog modal-sm" role="document">
   		<div class="modal-content">	
	   		<div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h5 class="modal-title" id="myModalLabel">确定要清空回收站🐎？</h5>
		    </div>
		   
		   <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">清空</button>
	       </div>	   
    	</div>
 			
  		</div>
    </div>
   </form>
  </body>

  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>
