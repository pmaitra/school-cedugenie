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
<title>Program Selection for location</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style></head>
<body>
<header class="page-header">
	<h2>Program and Drive</h2>
</header>
<div class="content-padding">
	<div class="row">
		<div class="col-md-6 col-md-offset-3" >
		  <form action="sendLocationDetailsForProgram.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Program</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class="form-group">
							<label class="col-sm-5 control-label">Program Name:</label>
							<div class="col-sm-7">
								<select class="form-control" id="course" name="courseName" required>
									<option value="">Select</option>
									<c:forEach var="program" items="${programList}" varStatus="i">
										<option value="${program.courseCode}">${program.courseName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-5 control-label">Drive Name:</label>
							<div class="col-sm-7">
								<input type="text" class = "form-control" name = "drive" id = "drive" readonly="readonly">
							</div>
						</div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit">Send Location</button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
	        </form>
		</div>
	</div>
</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$("#course").change(function(){
	$.ajax({
		url:'/cedugenie/getDriveWithUnsentLocationAgainstCourse.html',
		datatype:'text',
		data: "course="+$(this).val(),
		success:function(dataDB){
			document.getElementById("drive").value = dataDB;
		}
	});
});
</script>
</body>
</html>