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
<title>Create Course Type</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">

function updateProgram(courseTypeName, courseTypeDescription, index){
	$('#updateProgramDetails > tbody').empty();
 	if( (courseTypeName != null && courseTypeName!="") &&  (courseTypeDescription != null && courseTypeDescription!="")){
 		var row = "<tbody>";
 		row += "<tr><td>"+"<input type='hidden' id='index' name='index' value='"+index+"'><input type='text' id='courseTypeName' name='courseTypeName' class='form-control' pattern = '[a-zA-Z][a-zA-Z ]+[a-zA-Z]$' onclick='closeWarning()'  title='Programme Type Name Contains Charecter And Spaces Only' value='"+courseTypeName+"' required ></td>"
 				+"<td><input type='text' id='courseTypeDescription' name='courseTypeDescription' class='form-control'  onclick='closeWarning()' value='"+courseTypeDescription+"' required></td>";    				
 		$("#updateProgramDetails").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateProgram");
 	btn.setAttribute("onclick","saveData('"+index+"','"+courseTypeDescription+"');");
};

function saveData(rowId,courseTypeDescription){
	
	
	var updatedCourseTypeName = document.getElementById("courseTypeName").value;
	var updatedCourseTypeDescription = document.getElementById("courseTypeDescription").value;
	
	document.getElementById("saveId").value=rowId;
	document.getElementById("newCourseTypeName").value=updatedCourseTypeName;
	document.getElementById("newCourseTypeDesc").value=updatedCourseTypeDescription;

	var status=validatingField(rowId,courseTypeDescription);
	if(status==true)
		{
		document.editCourseTypeForm.submit();
		}
}

function validatingField(rowId,courseTypeDescription)
{
	var regex = /^[a-zA-Z\s]+$/;
	var oldProgName=document.getElementsByName("oldCourseTypeNames");
	
	var newProgName=document.getElementById("newCourseTypeName").value;
	newProgName=newProgName.trim();
	newProgName=newProgName.toUpperCase();
	
	var desc = document.getElementById("newCourseTypeDesc").value;
	
	for(var i=0;i<document.getElementsByName("oldCourseTypeNames").length;i++)
		{
		
			if(oldProgName[i].value==newProgName && desc==courseTypeDescription)
				{
				
				document.getElementById("warningmsg").style.display = 'block';			
				document.getElementById("warningmsg").innerHTML = "Course type Name with same description Alerady Exists.";
				return false;
				}
			}
	 if (desc ==""||desc==null)
	{
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Description must no be empty.";
		return false;
	}
	
	else if (newProgName ==""||newProgName==null)
	{
		document.getElementById("warningmsg").style.display = 'block';			
		document.getElementById("warningmsg").innerHTML = "Course type Name must no be empty.";
		return false;
	}
	
	
	else if (regex.test(newProgName)==false)
	    {
	    	document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Course type name can contain alphabets and spaces between words !!";
			return false;
	    }
	else
		{
		return true;
		}
};

function closeWarning(){
	document.getElementById("warningmsg").style.display = 'none';	
}

function validating()
{
	var newName=document.getElementById("courseTypeName").value;
	newName=newName.trim();
	newName=newName.toUpperCase();

	var oldName=document.getElementsByName("oldCourseTypeNames");
	for(var i=0;i<oldName.length;i++)
		{
		
		if(oldName[i].value==newName)
			{
			document.getElementById("message1").style.display = 'block';			
			document.getElementById("message1").innerHTML = "Course type Name already exists";
			return false;
			}
		}
	return true;
	}
	

</script>
</head>
<body>
			<header class="page-header">
				<h2>Create Course Type</h2>
			</header>
			<div class="content-padding">
				<c:if test="${insertUpdateStatus eq 'success'}">
					<div class="alert alert-success"  >
						<strong>${msg}</strong>	
					</div>
				</c:if>
				<c:if test="${insertUpdateStatus eq 'fail'}">
					<div class="alert alert-danger" >
						<strong>${msg}</strong>	
					</div>
				</c:if>
                    <div class="row">
						<div class="col-md-4">
						  <form action="createCourseType.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Create Course Type</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Course Type Name: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input name="courseTypeName" id = "courseTypeName" class="form-control" type="text" pattern = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$"  title="Charecter And Spaces Only" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Description:  <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="desc" name="courseTypeDesc" class="form-control" type="text" placeholder="" pattern = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Charecter And Spaces Only" required>
											</div>
										</div>
									</div>
									    <div class="alert alert-warning" id="message1" style="display: none">
				  							<span></span>	
										</div>	
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" onclick="return validating()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>

						
					<c:choose>
						<c:when test="${courseTypeList eq null || empty courseTypeList}">
							No Course Type Found
						</c:when>
						<c:otherwise>
						<div class="col-md-8">
							<form name="editCourseTypeForm" id="editCourseTypeForm" action="editCourseType.html" method="post">
								<input type="hidden" name="saveId" id="saveId">
								<input type="hidden" name="newCourseTypeName" id="newCourseTypeName">
								<input type="hidden" name="newCourseTypeDesc" id="newCourseTypeDesc">
	                            <section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										</div>
								
										<h2 class="panel-title">Existing Course Type</h2>
									</header>
									<div class="panel-body">
										<div id="oldCourse">
											<c:forEach var="courseType" items="${courseTypeList}" varStatus="i">
												<input type="hidden" name="oldCourseTypeNames" value="${courseType.courseTypeName}">
											</c:forEach>
										</div>
										<div class="panel-body">
										<div id="oldDescription">
											<c:forEach var="courseType" items="${courseTypeList}" varStatus="i">
												<input type="hidden" name="oldDescription" value="${courseType.courseTypeDesc}">
											</c:forEach>
										</div>
										<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
											<thead>
												<tr>
													<th>Course Type</th>
													<th>Description</th>
													<th>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="courseType" items="${courseTypeList}" varStatus="i">
													<tr class="gradeX">
														<td>
															<input type="hidden" name="oldCourseTypeCode${i.index}" value="${courseType.courseTypeCode}">
															${courseType.courseTypeName}
														</td>
														<td>
														<input type="hidden" name="oldDesc${i.index}" id="oldDesc${i.index}" value="${courseType.courseTypeDesc}">
															${courseType.courseTypeDesc}											
														</td>
														<td class="actions">
															<c:if test="${courseType.courseTypeName ne 'REGULAR'}">
															<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateProgram('${courseType.courseTypeName}','${courseType.courseTypeDesc}','${i.index}')"><i class="fa fa-pencil"></i></a>
															<a href="inactiveProgramType.html?courseTypeCode=${courseType.courseTypeCode}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
															</c:if>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</section>
									<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
										<section class="panel">
											<header class="panel-heading">
												<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
											</header>
											<div class="panel-body">
												<table class="table table-bordered table-striped mb-none" id = "updateProgramDetails">
													<thead>
														<tr>
															<th>Course Type</th>
															<th>Description</th>
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
														<button id="updateProgram" class="btn btn-success">Update</button>
														<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
													</div>
												</div>
											</footer>
										</section>
									</div>
								</form>
							</div>
						</c:otherwise>
					</c:choose>
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
					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createCourseType.js"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>