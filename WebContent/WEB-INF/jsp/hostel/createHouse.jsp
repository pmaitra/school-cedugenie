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
<title>Create House</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	}
</style>


</head>
<body>
	<header class= "page-header">
		<h2>Create House</h2>
	</header>
	<div class = "content-padding">
		<c:if test="${insertStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>New House successfully created.</strong>	
			</div>
		</c:if>
		<c:if test="${insertStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>New House creation failed.</strong>	
			</div>
		</c:if>
		<c:if test="${updateStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>House updated successfully.</strong>	
			</div>
		</c:if>
		<c:if test="${updateStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>House updation failed.</strong>	
			</div>
		</c:if>

   		<div class="row">
           	<div class="col-md-6 col-md-offset-3">
				<form name="houseForm" id="houseForm" action="addNewHouse.html" method="post">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Create House</h2>	
						</header>
						<div style="display: block;" class="panel-body">
							<div class="form-group">
								<label class="col-sm-5 control-label">House Type</label>
								<div class="col-sm-7">
									<select name="houseType.houseTypeCode" id="houseType" class="form-control">
										<option value="">Select...</option>
										<c:forEach var="ht" items="${houseTypeList}">
											<option value="${ht.houseTypeCode}">${ht.houseTypeName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label">House Name<span class="required" aria-required="true">*</span></label>
								<div class="col-sm-7">
									<input id="houseName" name="houseName" class="form-control" type="text" required>
								</div>
							</div>
						</div>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit" onclick = "return validateHouseCreation()">Submit</button>
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
						<h2 class="panel-title">Existing Houses</h2>
					</header>
					<div class="panel-body">
						<c:choose>
							<c:when test="${null eq houseList}">
								<div class="alert alert-danger">
									<strong>No house is created yet.</strong>
								</div>
							</c:when>
							<c:otherwise>
								<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
                                            <th>House Type</th>
                                            <th>House Name</th>
											<!-- <th>Actions</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach var="h" items="${houseList}" varStatus="i">
											<tr class="gradeX">
												<td>
													${h.houseType.houseTypeName}											
												</td>
												<td>
													<input type="hidden" name="oldHouseNames" value="${h.houseCode}">
													${h.houseName}
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
<script src="/icam/js/hostel/createHouse.js"></script>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>