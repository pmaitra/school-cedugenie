<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>QR For Book</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<div class="row">
	<div class="col-md-6 col-md-offset-3">
		<form:form action="submitBookAttributeForQRCode.html" method="post">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>
					<h2 class="panel-title">Generate QR Code For Book</h2>										
				</header>
				<div style="display: block;" class="panel-body">
					<table class="table table-bordered table-striped mb-none" id="TeahetAttributeColumn"> 
						<tbody>
							<tr>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="bookName" id="bookName">
										<label for="userId">Book Name</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="individualCode" id="individualCode">
										<label for="name">Code</label>
									</div>
								</td>
							</tr>
							<tr>											
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="dateOfEntry" id="dateOfEntry">
										<label for="gender">Date Of Entry</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="author" id="author">
										<label for="dateOfBirth">Author</label>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="publisher" id="publisher">
										<label for="designation">Publisher</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="isbn" id="isbn">
										<label for="bloodGroup">ISBN</label>
									</div>
								</td>
							</tr>
							<tr>											
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="medium" id="medium">
										<label for="mobile">Medium</label>
									</div>
								</td>
								<td>
									<div class="checkbox-custom checkbox-default">
										<input type="checkbox" checked="checked" name="attributeColumn" value="edition" id="edition">
										<label for="emailId">Edition</label>
									</div>
								</td>
							</tr>
							
						</tbody>
					</table>    
				</div>
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary" id="submit" name="submit" value="Submit" onclick="return validateBookQRCode();">Submit </button>
					<button type="reset" class="btn btn-default" id="reset" name="reset" value="reset">Reset</button>
				</footer>
			</section>
		</form:form>
	</div>
</div>					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script>	
$(document).ready(function() {    
//     $('#date').Zebra_DatePicker();
    
    
//     $('#date').Zebra_DatePicker({
//     	  format: 'Y-m-d',
//     	  direction: true
//     	});
    
 });
 

	function validateBookQRCode(){	
		var dateValue=document.getElementById("date").value;
		var timeValue=document.getElementById("time").value;
		
		if(dateValue==""){
			alert("Please Enter Date");
			return false;
		}

		
		if(timeValue==""){
			alert("Please Enter Time");
			return false;
		}			
	}
</script>
</body>
</html>