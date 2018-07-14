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
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
</head>
<body>
		 <div class="row">
						<div class="col-md-4 col-md-offset-4">
						  <form:form method="POST" id="vmlform" action="getAdmissionFormStatusDetails.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Form Status</h2>										
									</header>
									<div style="display: block;" class="panel-body">  
                                        <div class="form-group">
                                            <label class="control-label">Form Id</label>
                                            <input type="text" class="form-control" name="admissionFormId" placeholder="">
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" name="submit" >Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>						
					</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>