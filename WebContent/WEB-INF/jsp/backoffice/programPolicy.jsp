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
<title>Library Policy</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

<c:if test="${status eq 'success'}">
			<div class="alert alert-success"  >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		<c:if test="${status eq 'failed'}">
			<div class="alert alert-danger" >
				<strong>${msg}</strong>	
			</div>
		</c:if>
						
					 <div class="row">
						<div class="col-md-12">
						  <form:form name="addVendor"  method="POST" action="addProgramPolicy.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Programe Policy</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class = "form-group" id="policyDetails" >
										<u style="text-transform: uppercase;"><span id="titleToShow">Programe Policy</span></u><br>
											<p>
                                               If the program duration is less than  <input type="text" name="duration" id="duration" value= "${duration}" class="form-control" style="width: 100px; display: inline-block;" pattern="^[1-9]\d*$"  title = "Please provide integer value" required> months
                                            	then no login is available in iCAM for this program.
                                            </p>
										</div>
									</div>                                    
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type="submit">Submit </button>
									</footer>
								</section>
								<div class="btnsarea01">
								<div class="warningbox" id="warningbox" >
									<span id="warningmsg"></span>	
								</div>
							</div>
                            </form:form>
						</div>
						
					</div>	
					
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/vendorRating.js"></script>
</body>
</html>