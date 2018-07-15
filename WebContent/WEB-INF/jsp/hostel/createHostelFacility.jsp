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
       }
       #datatable-editable_filter{
			display: none;
		}
</style>
</head>
<body>

		<c:choose>
		<c:when test="${hostelList eq null || empty hostelList}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">Hostel Not Created Yet</span>	
				</div>
			</div>
		</c:when>
		<c:otherwise>


					<!-- start: page -->
					 <div class="row">
						<div class="col-md-6">
						  <form name="hostelFacilityForm" id="hostelFacilityForm" method="post" action="addhostelFacility.html" class="form-horizontal">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Hostel Facility </h2>										
									</header>
									<c:forEach var="oldHostelFaciliti" items="${hostelFacilityList}">
										<input type="hidden" name="oldHostelFacilities" value="${oldHostelFaciliti.hostelFacilityCode}">
									</c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-4 control-label">Hostel Facility Name:<span class="required" aria-required="true">*</span></label>
											<div class="col-sm-8">
												<input id="hostelFacilityName" name="hostelFacilityName" class="form-control" type="text">
											</div>
										</div>                                        
                                        
										<div class="form-group">
											<label class="col-sm-4 control-label">Hostels:  <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-8">
											<ul class="ulList">
												<c:forEach var="hostel" items="${hostelList}" varStatus="i">
												<li>
													<input type="checkbox" value="${hostel.hostelCode}" name="hostel" id = "hostel"> ${hostel.hostelCode}
												</li>
												</c:forEach>
											</ul>
											</div>
										</div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick = "return validate()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						<div class="col-md-6">						  
							<form name="editHostelFacility" id="editHostelFacility" action="editHostelFacility.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							<c:forEach var="allHostel" items="${hostelList}" >
								<input type="hidden" name="oldHostels" value="${allHostel.hostelCode}">
							</c:forEach>
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Hostel Facility List</h2>
							</header>
							<div class="panel-body">
								
								<table class="table table-bordered table-striped mb-none"  id="datatable-editable">
									<thead>
										<tr>
                                            
											<th>Facility Name<span class="required" aria-required="true">*</span></th>
											<th>Hostel<span class="required" aria-required="true">*</span></th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="hostelFacility" items="${hostelFacilityList}" varStatus="ind">
										<tr>											
											<td>
												<input type="hidden" class="form-control" value="${hostelFacility.hostelFacilityCode}" id="facilityCode${ind.index}" name="facilityCode${ind.index}">
												<input type="text" class="form-control" value="${hostelFacility.hostelFacilityName}" readonly id="facilityName${ind.index}" name="facilityName${ind.index}">
											</td>
											<td>
											<ul class="ulList" readonly id="ulList${ind.index}">
												<c:forEach var="allHostel" items="${hostelList}" >
                                                  	<c:set var="counter" value="0" scope="page" />
                                                  		<c:forEach var="hostel" items="${hostelFacility.hostelList}">
                                                  			<c:if test="${hostel.hostelName eq allHostel.hostelName}">
                                                  				<c:set var="counter" value="1" scope="page" />
                                                  			</c:if>
                                                       </c:forEach>
                                                       <c:if test="${counter eq 1}">
                                                       <li>
                                                       	<input type="hidden" name="oldHostels${ind.index}" value="${allHostel.hostelName}">
                                                       	<input type="checkbox" value="${allHostel.hostelName}" name="hostel${ind.index}" checked="checked" id = "hostel"> ${allHostel.hostelName}
                                                       </li>
                                                       </c:if>
                                                       <c:if test="${counter ne 1}">
                                                       <li>
                                                       	<input type="checkbox" value="${allHostel.hostelName}" name="hostel${ind.index}" id = "hostel"> ${allHostel.hostelName}
                                                       </li>
                                                       </c:if>
                                                  </c:forEach>
												</ul>
                                            </td>
											<td class="actions">
												<a href="#" class="hidden on-editing save-row" id="save${ind.index}"><i class="fa fa-save"></i></a>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="on-default edit-row" id="edit${ind.index}"><i class="fa fa-pencil"></i></a>
											</td>
										</tr>
									</c:forEach>	
									</tbody>
								</table>
							</div>
						</section>
						</form>
						</div>
					</div>	
					<!-- end: page -->
		</c:otherwise>
		</c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/hostel/createHostelFacility.editable.js"></script>
<script src="/cedugenie/js/hostel/createHostelFacility.js"></script>
</body>
</html>