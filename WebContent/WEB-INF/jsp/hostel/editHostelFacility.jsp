<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Edit Hostel Facility</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>


							<div class="row">
								<div class="col-md-8 col-md-offset-2">
									<form id="addhostelFacility" name="room" action="submitEdittedHostelFacility.html" method="post">
										<section class="panel">
											<header class="panel-heading">
												<div class="panel-actions">
													<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
												</div>
												<h2 class="panel-title">Edit Hostel Facility</h2>										
											</header>
											<div style="display: block;" class="panel-body">
		                                        <input type="hidden" id="hostelLength" name="hostelLength" value="${hostelFacility.hostelList.size()}"/>
												<div class="form-group">
													<label class="col-sm-6 control-label">Facility Name</label>
													<div class="col-sm-6">
														<input type="hidden" id="hostelFacilityCode" name="hostelFacilityCode" value="${hostelFacility.hostelFacilityCode}"/>
														<input type="text" class="form-control" id="hostelFacilityName" readonly="readonly" name="hostelFacilityName" value="${hostelFacility.hostelFacilityName}" required>
													</div>
												</div>                                        
		                                        <div class="form-group">
													<label class="col-sm-6 control-label">Description</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="hostelFacilityDesc" name="hostelFacilityDesc" required value="${hostelFacility.hostelFacilityDesc}">
													</div>
												</div>
		                                        <hr>
		                                        <div class="form-group col-sm-6">
		                                           <table class="table table-bordered table-striped mb-none">
		                                                <thead>
		                                                    <tr>
		                                                        <th style="background:#eee; text-align:center;">Category</th>
		                                                        <th style="background:#eee; text-align:center;">Charge(Yearly)</th>
		                                                    </tr>
		                                                </thead>
		                                                <tbody>
		                                                	<c:forEach var="socialCategoryObject" items="${hostelFacility.socialCategoryList}">
		                                                    <tr>
		                                                        <td>
																	<input type="hidden" name="category" value="${socialCategoryObject.socialCategoryName}" />
																	${socialCategoryObject.socialCategoryName}
																</td>
		                                                        <td>
		                                                        	<input type="text" class="textfield2 form-control" name="${socialCategoryObject.socialCategoryName}" value="${socialCategoryObject.amount}">
	                                                        		<input type="hidden" name="${socialCategoryObject.socialCategoryName}Old" value="${socialCategoryObject.amount}" />
	                                                        	</td>
		                                                    </tr>
		                                                    </c:forEach>
		                                                </tbody>
		                                            </table>
		                                        </div>
		                                        <div class="form-group col-sm-6">
		                                            <table class="table table-bordered table-striped mb-none">
		                                                <thead>
		                                                    <tr>
		                                                        <th colspan="2" style="background:#eee; text-align:center;">Hostel Name</th>
		                                                    </tr>
		                                                </thead>
		                                                <tbody>
		                                                	<c:forEach var="allHostelList" items="${hostelList}">
		                                                		<c:if test="${allHostelList.status eq 'selected'}">
			                                                		<tr>
				                                                        <td>
				                                                        	<input type="checkbox" name="hostelName" value="${allHostelList.hostelCode}" checked="checked">
			                                                        	</td>
				                                                        <td>
				                                                        	${allhostelList.hostelName}
			                                                        	</td>
				                                                    </tr>
			                                                    </c:if>
			                                                    <c:if test="${allHostelList.status ne 'selected'}">
			                                                		<tr>
				                                                        <td>
				                                                        	<input type="checkbox" name="hostelName" value="${allHostelList.hostelCode}">
			                                                        	</td>
				                                                        <td>
				                                                        	${allHostelList.hostelName}
			                                                        	</td>
				                                                    </tr>
			                                                    </c:if>
		                                                	</c:forEach>
	                                                   	</tbody>
		                                            </table>
		                                        </div>                                        
		                                            
											</div>
											<footer style="display: block;" class="panel-footer">
												<button class="submitbtn btn btn-primary" id="submit" name="submit" onclick=" return validate();">Submit </button>
												<button type="reset" class="btn btn-default">Reset</button>
											</footer>
										</section>
		                            </form>
								</div>
							</div>



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
function validate(){
	
	var valid = true;	
	var hostelFacilityName = document.getElementById('hostelFacilityName').value;
	if(hostelFacilityName == ""){
		alert("Please enter hostel facility name.");		
		valid = false;
		return false;
		}
	return valid;
}
</script>
</body>
</html>