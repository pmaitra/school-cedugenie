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
<title>Major Events</title>
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
					<c:if test="${insertStatus eq 'success'}">
						<div class="alert alert-success">
											<strong>Major Event is Successfully Created!!!</strong>
						</div>
						
					</c:if>
					<c:if test="${insertStatus eq 'fail'}">
						<div class="alert alert-danger">
											<strong>Major Event is Not Created!!!</strong>
						</div>
						
					</c:if>
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form method="POST" action="createMajorEvent.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
												<h2 class="panel-title">Create Major Events</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="col-md-12">
	                                        <div class="form-group">
												<label class="col-sm-5 control-label">Major Event Date</label>
												<div class="col-sm-7">
													<div class="input-group">
						                                 <span class="input-group-addon">
						                                     <i class="fa fa-calendar"></i>
						                                 </span>
						                                 <input type="text" name="majorEventDate" id="majorEventDate" class="form-control" data-plugin-datepicker="" >
						                            </div>
												</div>
											</div>																														
										<div class="form-group">
                                           	<label class="control-label">Major Event Description</label>
                                           	<textarea rows="7" cols="98"  class="form-control" id ="majorEventDesc" name="majorEventDesc"></textarea>
                                       	</div>
                                        </div>
                                        
                                        
									</div>
									<footer style="display: block;" class="panel-footer">
										<%-- <c:if test="${noticeBoard eq null}"> --%>
											<button class="btn btn-primary" type="submit" id="submit" name="submit" onclick="return checkdata()">Submit</button>
										<button type="reset" class="btn btn-default" id="clear">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>
					<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
					<%@ include file="/include/js-include.jsp" %>
		</body>
	</html>
	
	<script type="text/javascript">
		function checkdata(){
			var date= document.getElementById("majorEventDate").value;
			var desc= document.getElementById("majorEventDesc").value;
			if(date == "")
				{
					alert("Major Event Date can't be blank!!");
					return false;
				}
			if(desc == "")
				{
					alert("Major Event Desciption can't be blank");
					return false;
				}
			}
	</script>
	
	
	