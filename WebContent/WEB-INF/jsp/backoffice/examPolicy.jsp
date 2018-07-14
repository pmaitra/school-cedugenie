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
<title>Exam Policy</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
						
					<div class="row">
						<div class="col-md-12">
						  	<form:form name="libraryPolicy"  method="POST" action="saveExamPolicy.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Exam Policy</h2>										
									</header>
									<div style="display: block;" class="panel-body" id="policyDetails">
                                        
										<ol>
                                            <li>
                                                Examiner and Paper Setter can be changed after <input type="text" name="changeDay" id="changeDay" value="${examPolicy.changeDay}" class="form-control" style="width: 100px;" required>
                                                days of Creation.
                                            </li>
										</ol>
									</div>                                    
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitExamPolicy" name="submitExamPolicy">Submit </button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>	
						
						
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
var retval = null;
$(document).ready(function(){
	
	$("#submitExamPolicy").click(function(){
			var valNumeric = $("#changeDay").val();
			if(isNaN(valNumeric)){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("waringmsg").innerHTML="Please Enter the input in neumerical format !";
				retval = false;
				return false;
			} 
			else{
				document.getElementById("warningbox").style.visibility='collapse';
				document.getElementById("waringmsg").innerHTML="";
				retval = true;
				return true;
			}

		return retval;
	});

});


</script>
</body>
</html>