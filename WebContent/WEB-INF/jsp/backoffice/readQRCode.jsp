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
<title>Read QR Code</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<div class="row">
	<div class="col-md-5 col-md-offset-4">
		<form action="getQRCode.html" target="_blank" method="post">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>
					<h2 class="panel-title" data-toggle="tooltip" data-placement="right" title="" data-original-title="Read QR Code">Read QR Code</h2>										
				</header>
				<div style="display: block;" class="panel-body" id="TeahetAttributeColumn">
					<div class="col-md-9">
						<div class="form-group">
                   			<label class="control-label">QR Code For<span class="required" aria-required="true">*</span></label>
                   				<select class="form-control" id="type" onchange="checkForBook(this);">
                   					<option value="">Select...</option>
                   					<option value="teacher">Teacher</option>
									<option value="student">Student</option>
									<option value="book">Book</option>
                   				</select>
               			</div>
	                    <div class="form-group">
	                        <label class="control-label">ID</label>
	                        <input type="text" class="form-control" name="idd" id="idd">
	                    </div>
                   	</div>
				</div>
				<input type="hidden" name="initialPath" id="initialPath" value="${path}">
				<input type="hidden" name="fileName" id="fileName">
				<input type="hidden" name="path" id="path" >
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary" type="submit" id="getCode" onclick="return viewCode();">Get Code</button>
				</footer>
			</section>
		</form>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script>
	function checkForBook(type){
		if(type.value=="book"){
			$("#idd").autocomplete({
				source: '/icam/getBookIdForQrCode.html'
			});
		}
	};
		
	
	function viewCode(){		
		var idValue=document.getElementById("idd").value;
			if(idValue==""){
				alert("Please Enter ID");
				return false;
			}else{
					var source=document.getElementById("initialPath").value;
					var filename = "";
					if(document.getElementById("type").value=="book"){
						source=source+idValue+".png";
						filename = idValue+".png";
					}
					else if(document.getElementById("type").value=="teacher"){
						source=source.replace("Book","Teacher");
						source=source+idValue+".png";
						filename = idValue+".png";
					}
					else if(document.getElementById("type").value=="student"){
						source=source.replace("Book","Student");
						source=source+idValue+".png";
						filename = idValue+".png";
					}else{
						alert("Please Select Type");
						return false;
					}
					document.getElementById("path").value=source;	
					document.getElementById("fileName").value = filename;
				}	
		}
</script>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%-- <%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script> --%>
</body>
</html>