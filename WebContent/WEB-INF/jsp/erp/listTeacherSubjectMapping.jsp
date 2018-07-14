<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Teacher Subject Mapping List</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
</head>
<body>
	<header class="page-header">
		<h2>Teacher Subject Mapping List</h2>
	</header>
	<div class="content-padding">
	    <div class="row">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>			
					<h2 class="panel-title">Teacher List</h2>										
				</header>
				<div style="display: block;" class="panel-body">   
				    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
				        <thead>
				            <tr>
				                <th>Teacher Id</th>
				                <th>Teacher Name</th>
				                <th>Actions</th>
				            </tr>
				        </thead>
				        <tbody>
					       	<c:forEach var="teacher"  items="${teacherList}">
						        <input type="hidden" name="teacherId" id="teacherId" value="${teacher.userId}"/>
						        <tr>
							        <td>
							        	${teacher.userId}
						        	</td>
							        <td>
							        	${teacher.name}
						        	</td>
							        <td>
							        	<a href="getTeacherSubjectMappingForList.html?userId=${teacher.userId}" class="mb-xs mt-xs mr-xs modal-basic btn btn-info">Details</a> 
									</td>
						        </tr>
					        </c:forEach>
				        </tbody>
				    </table>
			    </div>
		   	</section>
  		 </div>
	 </div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>	 
<%@ include file="/include/js-include.jsp" %> 
</body>
</html>