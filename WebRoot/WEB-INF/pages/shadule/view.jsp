<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>查课表</title>   
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
  	<script type="text/javascript">var tim = [ "", "第一节 1st", "第二节 2ed", "第四节 4th ", "第五节 5th", "第六节 6th" ];</script>
  </head>
  
  <body>
    <h1>这是查课表的页面</h1><hr>
   	<div class="container">
	<form method="post" action="${pageContext.request.contextPath }/servlet/ViewShaduleServlet" >
	  <div class="form-group" style="width:50%">
	  	<lavel for="InputId_major">选择你的专业</lavel>
	  	<select class="form-control" id="InputId_major" name="InputId_major">
	  	  <c:forEach var="major" items="${sessionScope.majors }">
	  	  <option value="${major.id }">${major.name }</option>
	  	  </c:forEach>
	  	</select>
	  </div>  
	  <div class="form-group" style="width:50%">
	  	<lavel for="InputScope">选择学年</lavel>
	  	<select class="form-control" id="InputScope" name="InputScope">
	  	  <option value="2018-2019-1">2018-2019-1学期</option>
	  	  <option value="2018-2019-2">2018-2019-2学期</option>
	  	  <option value="2017-2018-1">2017-2018-1学期</option>
	  	  <option value="2017-2018-2">2017-2018-2学期</option>
	  	</select>
	  </div>
	  <button type="submit" class="btn btn-default">查课表</button>
	  <a href="${pageContext.request.contextPath }/servlet/IndexUIServlet" class="btn btn-default">返回</a>
	</form>
	</div> 
	
	<hr>
	<h2>课表内容：</h2>
	<c:forEach items="${shadules }" var="shadule">
	  ${shadule.name }<br>
	</c:forEach>
	
	<table>
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
	        <c:forEach items="${shadules }" var="shadule">
	          <c:if test="${shadule.order == i && shadule.day == j}">
	            ${shadule.name }
	          </c:if>
	        </c:forEach>
	      </td>
	    </c:forEach>
	    </tr>
	  </c:forEach>
	  
	</table>
  </body>
  
  <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
</html>
