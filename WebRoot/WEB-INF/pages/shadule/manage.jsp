<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>管理课表</title>   
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
  	<script type="text/javascript">var tim = [ "", "第一节 1st<br>8:00 - 9:50", "第二节 2ed<br>10:10 - 12:00", "第四节 4th<br>14:10 - 16:00 ", "第五节 5th<br>16:20 - 18:10", "第六节 6th <br>19:00 - 20:50" ];</script>
  	<script>
  	
  	  function setInputDayAndInputOrder(i, j){
  	  	document.getElementById("inputDay").value = i+"";
  	  	document.getElementById("inputOrder").value = j+"";
  	  }
  	  function delShadule(){
  	    alert("要删除这一条shadule马？");	
  	  }
  	</script>
  	<style>
  	  .red{color:red;}
  	</style>
  </head>
  
  <body>
    <h1>这是查课表的页面</h1><hr>
   	<div class="container">
	<form method="post" action="${pageContext.request.contextPath }/servlet/ManageShaduleServlet" >
	  <div class="form-group" style="width:50%">
	  	<lavel for="InputId_major">选择你的专业</lavel>
	  	<select class="form-control" id="InputId_major" name="InputId_major">
	  	  <c:forEach var="major" items="${sessionScope.majors }">
	  	  <option value="${major.id }" ${major.id== id_major? 'selected':''}>${major.name }</option>
	  	  </c:forEach>
	  	</select>
	  </div>  
	  <div class="form-group" style="width:50%">
	  	<lavel for="InputScope">选择学年</lavel>
	  	<select class="form-control" id="InputScope" name="InputScope">
	  	  <option value="2018-2019-1" ${scope=='2018-2019-1'? 'selected':''}>2018-2019-1学期</option>
	  	  <option value="2018-2019-2" ${scope=='2018-2019-2'? 'selected':''}>2018-2019-2学期</option>
	  	  <option value="2017-2018-1" ${scope=='2017-2018-1'? 'selected':''}>2017-2018-1学期</option>
	  	  <option value="2017-2018-2" ${scope=='2017-2018-2'? 'selected':''}>2017-2018-2学期</option>
	  	</select>
	  </div>
	  <button type="submit" class="btn btn-default">编辑课表</button>
	  <a href="${pageContext.request.contextPath }/servlet/IndexUIServlet" class="btn btn-default">返回</a>
	</form>
	</div> 
	
	<hr>
	
	<div class="table-responsive container">
	<h2>课表内容：</h2>
	<table class="table table-bordered">
	  <tr>
	    <th>空白</th>
	    <th>星期一<br>Monday</th>
	    <th>星期二<br>Tuesday</th>
	    <th>星期三<br>Wednesday</th>
	    <th>星期四<br>Thursday</th>
	    <th>星期五<br>Friday</th>
	    <th>星期六<br>Saturday</th>
	    <th>星期天<br>Sunday</th>
	  </tr>
	  <c:forEach var="i" begin="1" end="5">
	  	<tr>
	  	<th><script>var i = ${i};document.write(tim[i]);</script></th>	
	    <c:forEach var="j" begin="1" end="7">
	      <td>
	        <c:forEach items="${sessionScope.shadules }" var="shadule">
	          <c:if test="${shadule.order == i && shadule.day == j}">
	            ${shadule.id }<br>
	            ${shadule.name }<br>
	            ${shadule.teacher }<br>
	            ${shadule.place }<br>
	            ${shadule.remark }
	            <a type="button" class="btn btn-default btn-xs" onclick="delShadule()" href="${pageContext.request.contextPath }/servlet/DeleteShaduleServlet?del_index=${shadule.index}">
	          	  <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
				</a>	
				<hr>
	          </c:if>
	        </c:forEach>
	        <button type="button" class="btn btn-default btn-xs" onclick="setInputDayAndInputOrder(${j}, ${i})">
	          <span class="glyphicon glyphicon-plus" aria-hidden="true" data-toggle="modal" data-target="#addModal">添加</span>
			</button>	      
	      </td>
	    </c:forEach>
	    </tr>
	  </c:forEach>
	  
	</table>
	<h5 class="red"><c:out value="${shadule_tip}"/></h5>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<!-- Add Modal -->
	<form action="${pageContext.request.contextPath }/servlet/ManageShaduleServlet" method="post">
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">添加一个 shadule</h4>
	      </div>
	      <div class="modal-body">
	        <div class="form-group">
		      <label for="inputId">课程序列号</label>
		      <input type="text" class="form-control" name="inputId" id="inputId" placeholder="课程序列号" value="${formbean.inputName }">
		      <h5 class="red"><c:out value="${formbean.errors.InputName }"/></h5>
		    </div>
		    <div class="form-group">
		      <label for="inputName">课程名字（中英文皆可）</label>
		      <input type="text" class="form-control" name="inputName" id="inputName" placeholder="课程名称" value="${formbean.inputId }">
	          <h5 class="red"><c:out value="${formbean.errors.InputId }"/></h5>	  
		    </div>
		    <div class="form-group">
		      <label for="inputTeacher">授课老师</label>
		      <input type="text" class="form-control" name="inputTeacher" id="inputTeacher" placeholder="Teacher" value="${formbean.inputPassword }">
		      <h5 class="red"><c:out value="${formbean.errors.InputPassword }"/></h5>
		    </div>
		    <div class="form-group">
		      <label for="inputPlace">授课地点</label>
		      <input type="text" class="form-control" name="inputPlace" id="inputPlace" placeholder="Password" value="${formbean.inputPassword2 }">  
		  	  <h5 class="red"><c:out value="${formbean.errors.InputPassword2 }"/></h5>  
		    </div>	
		    <div class="form-group">
		      <label for="inputRemark">课程备注</label>
		      <input type="text" class="form-control" name="inputRemark" id="inputRemark" placeholder="" value="${formbean.inputPassword2 }">  
		  	  <h5 class="red"><c:out value="${formbean.errors.InputPassword2 }"/></h5>  
		    </div>
	      </div>
	      
	      <input type="hidden" id="inputDay" name="inputDay" /><br>
	   
	      <input type="hidden" id="inputOrder" name="inputOrder" /><br>
	    
	      <input type="hidden" id="inputScope" name="inputScope" value="${scope}"/><br>
	     
	      <input type="hidden" id="inputId_major" name="inputId_major" value="${id_major }"/><br>
	      
	      <!-- "编辑课表的专业相关参数回传" -->
	      <input type="hidden" id="InputId_major" name="InputId_major" value="${id_major }"/>
	      <input type="hidden" id="InputScope" name="InputScope" value="${scope }"/>
	      
	       <input type="hidden" id="token" name="token" value=""/>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-primary">提交</button>
	      </div>
	    </div>
	  </div>
	</div>
	</form>
  </body>
  

</html>
