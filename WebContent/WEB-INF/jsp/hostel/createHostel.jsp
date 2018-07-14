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
<title>Create Residence</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	}
</style>


</head>
<body>
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Create Residence</h2>
	</header>
	
		<div class = "content-padding">
			<c:if test="${insertStatus eq 'success'}">
					<div class="alert alert-success">
						<strong>New Residence successfully created.</strong>	
					</div>
				</c:if>
				<c:if test="${insertStatus eq 'fail'}">
					<div class="alert alert-danger">
						<strong>New Residence creation failed.</strong>	
					</div>
				</c:if>
				<c:if test="${updateStatus eq 'success'}">
					<div class="alert alert-success">
						<strong>Residence updated successfully.</strong>	
					</div>
				</c:if>
				<c:if test="${updateStatus eq 'fail'}">
					<div class="alert alert-danger">
						<strong>Residence updation failed.</strong>	
					</div>
				</c:if>

                    <div class="row">
                    	<div class="col-md-6 col-md-offset-3">
						  <form name="hostelForm" id="hostelForm" action="addHostel.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Residence</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Residence Type:</label>
											<div class="col-sm-7">
												<select name="hostelTypeCode" id="hostelType" class="form-control" required>
													<option value="">Select...</option>
													<c:forEach var="hostelTypeList" items="${hostelTypeList}">
														<option value="${hostelTypeList.hostelTypeCode}">${hostelTypeList.hostelTypeName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Residence Name:</label>
											<div class="col-sm-7">
												<input id="hostelName" name="hostelName" class="form-control" type="text" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Residence Abbreviation:</label>
											<div class="col-sm-7">
												<input id="hostelAbbreviation" name="hostelAbbreviation" class="form-control" type="text" placeholder="" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Gender Of Resource:</label>
											<div class="col-sm-7">
												<select name="gender" id="gender" class="form-control">
													<option value="">Select..</option>
													<option value="M">Male</option>
													<option value="F">Female</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Zone of Residence:</label>
											<div class="col-sm-7">
												<input name="zone" id="zone" class=" form-control" type="text" placeholder="" required>
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick = "return addHostel()" >Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						
						
						
						
						<div class="col-md-12">
							<form name="editHostelForm" id="editHostelForm" action="editHostel.html" method="post">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="hostelNameNew" id="hostelNameNew">
							<input type="hidden" name="newAbbreviationDesc" id="newAbbreviationDesc">
							<input type="hidden" name="newZoneName" id="newZoneName">
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Existing Residences</h2>
							</header>
							<div class="panel-body">
								<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
                                            <th>Residence Type</th>
											<th>Residence Name</th>
											<th>Residence Abbreviation</th>
											<th>Zone of Residence</th>
											<th>Gender of Resource</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="hostel" items="${hostelList}" varStatus="i">
											<tr class="gradeX">
												<td>
													<input type="hidden" id="newHostelTypeId${i.index}" name="newHostelTypeName${i.index}" class="form-control" value="${hostel.hostelType.hostelTypeName}" readonly>	
													${hostel.hostelType.hostelTypeName}											
												</td>
												<td>
													<input type="hidden" name="oldHostelNames" value="${hostel.hostelCode}">
													<input type="hidden" id="oldHostelCode${i.index}" name="oldHostelCode${i.index}" value="${hostel.hostelCode}">
													${hostel.hostelName}
												</td>
												<td>
													<input type="hidden" name="oldHostelAbbreviation${i.index}" id= "oldHostelAbbreviation${i.index}" value="${hostel.hostelAbbreviation}">	<!--  Added by saif 26-03-2018 -->
													${hostel.hostelAbbreviation}											
												</td>
												<td>
													<input type="hidden" name="oldHostelZone${i.index}" id= "oldHostelZone${i.index}" value="${hostel.zone}">
													${hostel.zone}										
												</td>
												<td>
													<c:choose>
														<c:when test="${hostel.gender eq 'MALE'}">						
															Male
														</c:when>
														<c:otherwise>
															Female
														</c:otherwise>
													</c:choose>
												</td>
												<td class="actions">
													<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateHostel('${i.index}','${hostel.hostelType.hostelTypeName}','${hostel.hostelName}','${hostel.hostelAbbreviation}','${hostel.zone}','${hostel.gender}')"><i class="fa fa-pencil"></i></a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
						
						<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px">
									<section class="panel">
										<header class="panel-heading">
											<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id="updateHostelDetails">
												<thead>
													<tr>
														<th>Residence Type</th>
														<th>Residence Name</th>
														<th>Abbreviation Residence</th>
														<th>Zone of Residence</th>
														<th>Gender of Resource</th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg" style="display: none">
												<span></span>	
											</div>
										</div>
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateHostel" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
							
						</form>
						
						
						
						</div>						
					</div>
 		
 					<div id="dialog" class="modal-block mfp-hide">
						<section class="panel">
							<header class="panel-heading">
								<h2 class="panel-title">Are you sure?</h2>
							</header>
							<div class="panel-body">
								<div class="modal-wrapper">
									<div class="modal-text">
										<p>Are you sure that you want to delete this row?</p>
									</div>
								</div>
							</div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button id="dialogConfirm" class="btn btn-primary">Confirm</button>
										<button id="dialogCancel" class="btn btn-default">Cancel</button>
									</div>
								</div>
							</footer>
						</section>
					</div>
		</div>

				
					

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/hostel/createHostel.editable.js"></script>
<script src="/icam/js/hostel/createHostel.js"></script>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript">
function updateHostel(rowId, hostelType, hostelName, abbreviation, zone, gender){
	$('#updateHostelDetails > tbody').empty();
 	if((hostelType != null && hostelType!="") && (hostelName != null && hostelName!="")
 		&& (abbreviation != null && abbreviation!="") && (zone != null && zone!="") && (gender != null && gender!="")){
 		var row = "<tbody>";
 		row += "<tr><td>"+hostelType+"</td>"
 		+"<td><input type='text' id='newHostelName' name='newHostelName' class='form-control' value='"+hostelName+"' required></td>"
 		+"<td><input type='text' id='newAbbreviation' name='newAbbreviation' class='form-control' value='"+abbreviation+"' required></td>"
 		+"<td><input type='text' id='newZone' name='newZone' class='form-control' value='"+zone+"' required></td>"
 		+"<td>"+gender+"</td></tr>";    				
 		
 		$("#updateHostelDetails").append(row);
 	}
	$('#modalInfo').fadeIn("fast");
    var btn = document.getElementById("updateHostel");
 	btn.setAttribute("onclick","saveHostel('"+rowId+"');");
};

function saveHostel(rowid){
	var reg1=/^[A-Za-z0-9 ]{1,50}$/;
	var newHostelName = document.getElementById("newHostelName").value;
	var newAbbreviation = document.getElementById("newAbbreviation").value;
	var newZone = document.getElementById("newZone").value;
	
	if(newHostelName == null || newHostelName == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Residence Name can not be Empty.";
		return false;
	}else if(newAbbreviation == null || newAbbreviation == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Residence Abbreviation can not be Empty.";
		return false;
	}else if(newZone == null || newZone == ""){
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Zone of Residence can not be Empty.";
		return false;
	}else{
		newHostelName = newHostelName.replace(/\s{1,}/g,' ');
		newHostelName = newHostelName.trim();
		newHostelName = newHostelName.toUpperCase();
		
		newAbbreviation = newAbbreviation.replace(/\s{1,}/g,' ');
		newAbbreviation = newAbbreviation.trim();
		newAbbreviation = newAbbreviation.toUpperCase();
		
		newZone = newZone.replace(/\s{1,}/g,' ');
		newZone = newZone.trim();
		newZone = newZone.toUpperCase();
		
		if(!newHostelName.match(reg1)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Invalid Residence Name. (Alpha-Numeric. Atleat 1 character, and special caracter not allowed.)";
			return false;
		}else if(!newAbbreviation.match(reg1)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Invalid Abbreviation. (Alpha-Numeric. Atleat 1 character, and special caracter not allowed.)";
			return false;
		}else if(!newZone.match(reg1)){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Invalid Zone Name. (Alpha-Numeric. Atleat 1 character, and special caracter not allowed.)";
			return false;
		}else{
			document.getElementById("saveId").value=rowid;
			document.getElementById("hostelNameNew").value=newHostelName;
			document.getElementById("newAbbreviationDesc").value=newAbbreviation;
			document.getElementById("newZoneName").value=newZone;
			
			document.getElementById("warningmsg").style.display = 'none';	
			document.editHostelForm.submit();
		}
	}
	
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
};
</script>
</body>
</html>