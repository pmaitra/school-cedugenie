<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Return Book</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	}.mb-md{
		   display: none;
	}
</style>
</head>
<body>

<div class = "row">
	<form:form method="POST" name="userIdForReturnBook" action="submitUserIdForCheckReadingHabit.html">
		<div class="col-md-4 col-md-offset-4">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
					</div>
					<h2 class="panel-title">Reading Habit of Resource</h2>
				</header>
				<div style="display: block;" class="panel-body">
	                <div class="form-group">
	                    <label class="control-label">User ID/Roll Number</label>
	                    <input type="text" class="form-control" name="userId" id="userId" value=""/>
	                </div>
				</div>
				<footer style="display: block;" class="panel-footer">
                    <button class="btn btn-primary" type="submit" onclick = "return validateId()">Submit</button>
                </footer>
			</section>
		</div>
	</form:form>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
function validateId(){
	var id = document.getElementById("userId").value;
	if(id == ""){
		alert("Please enter an user id.");
		return false;
	}
	return true;
}
</script>
</body>
</html>