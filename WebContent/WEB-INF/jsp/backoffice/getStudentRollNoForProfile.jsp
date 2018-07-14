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
<title>View Student Profile</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>View Student Profile</h2>
</header>
<div class="content-padding">
	<div class = "row">
		<form:form method="POST" action="getStudentProfileAgainstSchoolNumber.html">
			<div class="col-md-4 col-md-offset-4">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						</div>
						<h2 class="panel-title">School Number</h2>
					</header>
					<div style="display: block;" class="panel-body">
		                <div class="form-group">
		                    <label class="control-label">Number</label>
		                    <input type="text" class="form-control" name="rollNumber" id="rollNumber" value=""/>
		                </div>
					</div>
					<footer style="display: block;" class="panel-footer">
	                    <button class="btn btn-primary" type="submit" onclick = "return validateId()">View Profile</button>
	                </footer>
				</section>
			</div>
		</form:form>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
function validateId(){
	var id = document.getElementById("rollNumber").value;
	if(id == ""){
		alert("Please enter an school number.");
		return false;
	}
	return true;
}
</script>
</body>
</html>