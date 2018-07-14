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
<title>Create House-Residence Mapping</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	}
</style>


</head>
<body>
	<header class= "page-header">
		<h2>Create House-Residence Mapping</h2>
	</header>
	<div class = "content-padding">
		<c:if test="${insertStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>House-Residence mapping created successfully.</strong>	
			</div>
		</c:if>
		<c:if test="${insertStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>House-Residence mapping creation failed.</strong>	
			</div>
		</c:if>
		<c:if test="${updateStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>House-Residence mapping updated successfully.</strong>	
			</div>
		</c:if>
		<c:if test="${updateStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>House-Residence mapping updation failed.</strong>	
			</div>
		</c:if>

   		<div class="row">
           	<div class="col-md-6 col-md-offset-3">
				<form name="houseResidenceMappingForm" id="houseResidenceMappingForm" action="submitHouseResidenceMapping.html" method="post">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">House & Residence</h2>	
						</header>
						<div style="display: block;" class="panel-body">
							<div class="form-group">
								<label class="col-sm-5 control-label">House Name<span class="required" aria-required="true">*</span></label>
								<div class="col-sm-7">
									<select name="houseName" id="houseName" class="form-control" required>
										<option value="">Select...</option>
										<c:forEach var="h" items="${houseList}">
											<option value="${h.houseCode}">${h.houseName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label">Residence Name<span class="required" aria-required="true">*</span></label>
								<div class="col-sm-7">
									<select name="residenceName" id="residenceName" class="form-control" required>
										<option value="">Select...</option>
										<c:forEach var="hstl" items="${hostelList}">
											<option value="${hstl.hostelCode}">${hstl.hostelName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit" onclick = "return validateMappingOfHouseAndResidence()">Submit</button>
							<button type="reset" class="btn btn-default">Reset</button>
						</footer>
					</section>
            	</form>
			</div>
			<div id="warningBox" class="col-md-6 col-md-offset-3" style="display: none">
				<div class="alert alert-danger" id="warningMsg"></div>
			</div>
			<div class="col-md-12">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						</div>
						<h2 class="panel-title">Existing Mapping</h2>
					</header>
					<div class="panel-body">
						<c:choose>
							<c:when test="${null eq houseResidenceMappingList}">
								<div class="alert alert-danger">
									<strong>No house-residence mapping is created yet.</strong>
								</div>
							</c:when>
							<c:otherwise>
								<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
                                            <th>House Name</th>
                                            <th>Residence Name</th>
											<!-- <th>Actions</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach var="mapping" items="${houseResidenceMappingList}" varStatus="i">
											<tr class="gradeX">
												<td>
													${mapping.houseName}											
												</td>
												<td>
													${mapping.residenceName}
												</td>
												<%-- <td class="actions">
													<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateHostel('${i.index}','${hostel.hostelType.hostelTypeName}','${hostel.hostelName}','${hostel.hostelAbbreviation}','${hostel.zone}','${hostel.gender}')"><i class="fa fa-pencil"></i></a>
												</td> --%>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</section>
			</div>						
		</div>
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>