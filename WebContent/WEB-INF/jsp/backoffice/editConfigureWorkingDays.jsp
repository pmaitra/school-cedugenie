<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Edit Configure Working Days</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>List Configure Working Days</h2>
	</header>
	<div class="content-padding">
	
					 <div class="row">
						<div class="col-md-6 col-md-offset-3">
						  	<form name="editcongspec" action="updatePublicHolidays.html" method="get">	
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Configure Special Days</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
                                            <input type="hidden" id="holidayCode" name="holidayCode" value="${holidayCode}">
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Academic Year</label>
                                                <div class="col-sm-7">
                                                	<input type="text" class="form-control" id="academicYear" name="academicYear" value="${academicYear}" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Year</label>
                                                <div class="col-sm-7">
                                                	<input type="text" class="form-control" id="year" name="year" value="${year}" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Month</label>
                                                <div class="col-sm-7">
                                                	<input type="hidden" id="month" name="month" value="${month}">
                                                	<c:if test="${month eq '01'}">
														<input type="text" class="form-control" value="<c:out value="January"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '02'}">
														<input type="text" class="form-control" value="<c:out value="February"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '03'}">
														<input type="text" class="form-control" value="<c:out value="March"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '04'}">
														<input type="text" class="form-control" value="<c:out value="April"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '05'}">
														<input type="text" class="form-control" value="<c:out value="May"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '06'}">
														<input type="text" class="form-control" value="<c:out value="June"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '07'}">
														<input type="text" class="form-control" value="<c:out value="July"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '08'}">
														<input type="text" class="form-control" value="<c:out value="August"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '09'}">
														<input type="text" class="form-control" value="<c:out value="September"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '10'}">
														<input type="text" class="form-control" value="<c:out value="October"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '11'}">
														<input type="text" class="form-control" value="<c:out value="November"/>" readonly="readonly">
													</c:if>
													<c:if test="${month eq '12'}">
														<input type="text" class="form-control" value="<c:out value="December"/>" readonly="readonly">
													</c:if>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Total Working Days</label>
                                                <div class="col-sm-7">
                                                    <input type="text" class="form-control" id="workingDays" name="workingDays" value="${workingDays}" readonly="readonly">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Public Holidays</label>
                                                <div class="col-sm-7">
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </span>
                                                        <input type="text" class="form-control" id="inputDate1" name="inputDateA" value="" data-plugin-datepicker="">
                                                    </div>
                                                </div>
										    </div>
										</div>
										<footer style="display: block;" class="panel-footer">
											<button class="btn btn-primary" type = "submit" id="submitbutton">Submit </button>
										</footer>
									</section>
									<br>
									<div class="warningbox" id="warningbox" >
										<span id="warningmsg"></span>	
									</div>
	                            </form>
							</div>
						</div>	
					
	</div>
	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var valid="";
// 	$('#inputDate1').Zebra_DatePicker();
	 
// 	 $('#inputDate1').Zebra_DatePicker({
// 	 	  format: 'd/m/Y'
// 	 	});
$('#submitbutton').click(function(){
	 	var compen = $("#inputDate1").val();
	 	if(compen != ""){
	 		return true;
	 	}
	 	else
	 		document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Enter Date.";
	 	return false;
	 	});
	 		return valid;
});
</script>
</body>
</html>