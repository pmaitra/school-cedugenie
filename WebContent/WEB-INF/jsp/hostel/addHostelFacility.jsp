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
<title>Hostel Room Type</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
		<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
			<h2>Add Hostel Facility</h2>
		</header>
			<div class = "content-padding">
				<div class="row">
						<c:choose>
							<c:when test="${hostelList eq null && hostelList.size() eq 0}">
								<div class="alert alert-danger">
									<strong>You've to create hostel before adding hostel facility.</strong>
								</div>						
							</c:when>
							<c:when test="${socialCategoryList eq null && socialCategoryList.size() eq 0}">
								<div class="alert alert-danger">
									<strong>You've to create social category before adding hostel facility.</strong>
								</div>						
							</c:when>
							<c:otherwise>
								<div class="col-md-8 col-md-offset-2">
									<form id="addhostelFacility" name="room" action="addHostelFacilityDetails.html" method="post">
										<section class="panel">
											<header class="panel-heading">
												<div class="panel-actions">
													<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
												</div>
												<h2 class="panel-title">Add Hostel Facility</h2>										
											</header>
											<div style="display: block;" class="panel-body">
		                                        
												<div class="form-group">
													<label class="col-sm-6 control-label">Facility Name</label>
													<div class="col-sm-6">
														<input type="hidden" id="hostelFacilityCode" name="hostelFacilityCode"/>
														<input type="text" class="form-control" id="hostelFacilityName" name="hostelFacilityName" required>
													</div>
												</div>                                        
		                                        <div class="form-group">
													<label class="col-sm-6 control-label">Description</label>
													<div class="col-sm-6">
														<input type="text" class="form-control" id="hostelFacilityDesc" name="hostelFacilityDesc" required>
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
		                                                	<c:forEach var="socialCategoryList" items="${socialCategoryList}">
		                                                    <tr>
		                                                        <td>
																	<input type="hidden" name="category" value="${socialCategoryList.socialCategoryCode}" />
																	${socialCategoryList.socialCategoryName}
																</td>
		                                                        <td><input type="text" class="textfield2 form-control" name="${socialCategoryList.socialCategoryCode}" value = "0.00"></td>
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
		                                                	<c:forEach var="allhostelList" items="${hostelList}">
		                                                		<tr>
			                                                        <td>
			                                                        	<input type="checkbox" name="hostelName" value="${allhostelList.hostelCode}">
		                                                        	</td>
			                                                        <td>${allhostelList.hostelName}</td>
			                                                    </tr>
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
							</c:otherwise>
						</c:choose>
					</div>
			</div>
				



<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
function validate(){
	
	var valid = true;
	var hostelName = document.getElementsByName('hostelName');
	var p=0;
	for(var i=0; i<hostelName.length;i++){
		if(hostelName[i].checked)
		p=p+1;
	}
	if(p<=0){
		alert("Please select atleast one hostel name");
		valid = false;
		return false;
	}	
	
	return valid;
}
</script>
</body>
</html>