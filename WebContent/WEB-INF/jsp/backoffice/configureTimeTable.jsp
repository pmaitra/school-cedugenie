<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html>
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Configure Time Table</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("teacherId"+rowId).removeAttribute("disabled");
	document.getElementById("classSectionId"+rowId).removeAttribute("disabled");
	document.getElementById("subjectId"+rowId).removeAttribute("disabled");
	document.getElementById("classId"+rowId).removeAttribute("disabled");
	
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	var teacherId= document.getElementById("teacherId"+rowId).value;
	
	var classSectionId = document.getElementById("classSectionId"+rowId).value;
	
	var subjectId=  document.getElementById("subjectId"+rowId).value;
	
	
	var oldTeacherNames = document.getElementsByName("OldTeacherName");
	var OldClassSectionName= document.getElementsByName("OldClassSectionName");
	var OldSubjectName= document.getElementsByName("OldSubjectName");
	
	for(var i=0; i<oldTeacherNames.length;i++)
	{
		if(oldTeacherNames[i].value==teacherId)
		{
			if(OldClassSectionName[i].value==classSectionId)
			{
				if(OldSubjectName[i].value==subjectId)
					{
						document.getElementById("addbtn").disabled = true;
						alert("Teacher :: "+ teacherId + "\n With Same Program :: "+ classSectionId +"\n And Course :: "+ subjectId + " Already Exixts");
						return false;
					}
			}
			/*alert("Teacher Already Exixts");
			return false;*/
		}
	}
	
	//window.location="editHostel.html?saveId="+rowId;
	document.editAndUpdateTimeTable.submit();
};
</script>
</head>
<body>
		<header class="page-header">
			<h2>Configure Time Table</h2>
		</header>
		
		<c:if test="${failuremsg != null}">
				<div class="alert alert-danger">
						<strong>Failed To Update Student Time Table Configuration</strong>
				</div>
		</c:if>
		<c:if test="${successmsg != null}">
				<div class="alert alert-success">
						<strong>Student Time Table Configuration Updated Successfully</strong>
				</div>
		</c:if>
		
		<c:if test="${status == 'fail'}">
				<div class="alert alert-danger">
						<strong>Failed To Insert Student Time Table Configuration</strong>
				</div>
		</c:if>
		<c:if test="${successMessage != null}">
				<div class="alert alert-success">
						<strong>Teacher with Program and Course Deleted Successfully.</strong>
				</div>
		</c:if>
		
		<c:if test="${errorMessage != null}">
				<div class="alert alert-danger">
						<strong>Failed To Delete Teacher with Program and Course.</strong>
				</div>
		</c:if>
		<c:if test="${status == 'success'}">
				<div class="alert alert-success">
						<strong>Student Time Table Configuration Inserted Successfully</strong>
				</div>
		</c:if>

		<div class="row">
			<form:form method="POST" action="saveTimeTableConfiguration.html" >
                 <div class="col-md-12">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">
								Time-Table: Allot Classes To Teachers
								<button type="button"  class="mb-xs mt-xs mr-xs modal-basic btn btn-info"  id="addbtn" disabled>Add</button>
							</h2> 
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" >
									<thead>
										<tr>
											<th>Semester</th>
											<th>
												<select class="form-control" name="semsterName" id="semsterName" onchange="enableAllFieldsToChooseValue()">
		                                            <option value="">Select...</option>
														<c:forEach var="term" items="${termList}">
															<option value="${term.termName}">${term.termName}</option>
														</c:forEach>
                                        		</select>
                                        	</th>
										</tr>
										<tr>
                                            <th>Teacher Name</th>
                                            <th>Program</th>
                                            <th>Course</th>
											<th>No. Of Classes</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody id="timetable">
										<tr>
                                            <td>
                                            	<input type= "hidden" name= "teacherID" id= "teacherID">
                                            	<select class="form-control teacher" name="teacherName" id="teacherName0" disabled>
		                                            <option value="">Select...</option>
													<c:forEach var="teacher" items="${teacherList}">
														<option value="${teacher.userId}">${teacher.teacherName}</option>
													</c:forEach>
                                        		</select>
                                           	</td>
                                            <td>
                                            	<select class="form-control sectionName" name="classSectionName" id="classSectionName0" disabled>
		                                            <option value="">Select...</option>
												</select>
                                           	</td>
                                           	<td>
												<select class="form-control subject" name="subjectName" id="subjectName0" disabled>
		                                            <option value="">Select...</option>
													<%-- <c:forEach var="subject" items="${subjectList}">
														<option value="${subject.subjectName}">${subject.subjectName}</option>
													</c:forEach> --%>
                                        		</select>
											</td>
											<td>
												<input type="text" class="form-control" id="noOfClasses0"  name="noOfClasses" disabled>
											</td>
                                            <td>
                                            	<button type="button" class = "mb-xs mt-xs mr-xs btn btn-danger" onclick='deleteRow1(this);' id= "cancelButton" disabled>Cancel</button>
                                           	</td>
										</tr>
									</tbody>
								</table>
							</div>
                            <footer style="display: block;" class="panel-footer">
                                <button type="submit" class="btn btn-primary" value="SUBMIT" onclick = "return validateTimeTable();" id= "submitButton" disabled>Submit </button>
                            </footer>
						</section>
					</div>
                </form:form>
                
                	<c:choose>
						<c:when test="${timeTableList eq null || fn:length(timeTableList) lt 1 }">
							<!-- <div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
								<span id="infomsg">No Alloted Class(s) Found.</span>
								<p>No Alloted Class(s) Found.</p>	
							</div> -->
							<div class="alert alert-danger">
								<strong>No Alloted Class(s) Found.</strong>
							</div>
						</c:when>
					<c:otherwise>
					
                	<form:form name="editAndUpdateTimeTable" id="editAndUpdateTimeTable" method="POST" action="editAndUpdateTimeTable.html">
						<input type="hidden" name="saveId" id="saveId">	
						<div class="col-md-12">	
	                       <section class="panel">
	                           <header class="panel-heading">
	                               <div class="panel-actions">
	                                   <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                               </div>
	
	                               <h2 class="panel-title">View / Edit Alloted Classes</h2>
	                           </header>
	                           <div class="panel-body">
	
	                               <table class="table table-bordered table-striped mb-none" id="datatable-editable">
	                                   <thead>
	                                       <tr>
		                                        <th>Teacher Name</th>
	                                            <th>Program</th>
	                                            <th>Course</th>
												<th>No. Of Classes</th>
												<th>Actions</th>
	                                       </tr>
	                                   </thead>
	                                   <tbody>
	                                   	<c:forEach var="timeTableList" items="${timeTableList}" varStatus="indexVal" >
	                                       <tr class="gradeX">
												<td>
													<input type="hidden" name="OldTeacherName" id= "OldTeacherName${indexVal.index}" value="${timeTableList.teacherUserId}">
													<%-- <input type="text" class="form-control"   value="${timeTableList.teacherName}" disabled /> --%>
													<select name="teacherName${indexVal.index}" id="teacherId${indexVal.index}" class="form-control" disabled >
														<c:forEach var="teacher" items="${teacherList}">
															<c:if test="${teacher.teacherName eq timeTableList.teacherName}">
																<option value="${teacher.userId}">${teacher.teacherName}</option>									
															</c:if>
														</c:forEach>
														  <c:forEach var="teacher" items="${teacherList}">
															<c:if test="${teacher.teacherName ne timeTableList.teacherName}">
																<option value="${teacher.userId}">${teacher.teacherName}</option>
															</c:if>
														</c:forEach>
													</select>
												</td>
												<td>
													<input type="hidden" name="OldClassSectionName" id= "OldClassSectionName${indexVal.index}" value="${timeTableList.classSectionName}">
													<select name="classSectionName${indexVal.index}" id="classSectionId${indexVal.index}" class="form-control" disabled >
														<option value="${timeTableList.classSectionName}">${timeTableList.classSectionName}</option>
														<%-- <c:forEach var="clSec" items="${standardList}">
															<c:if test="${clSec.desc eq timeTableList.classSectionName}">
																<option value="${clSec.desc}">${clSec.desc}</option>									
															</c:if>
														</c:forEach> --%>
														 <%-- <c:forEach var="clSec" items="${standardList}">
															<c:if test="${clSec.desc ne timeTableList.classSectionName}">
																<option value="${clSec.desc}">${clSec.desc}</option>
															</c:if>
														</c:forEach> --%>
													</select>
												</td>
												<td>
													<input type="hidden" name="OldSubjectName" id= "OldSubjectName${indexVal.index}" value="${timeTableList.subjectName}">
													<select name="subjectName${indexVal.index}" id="subjectId${indexVal.index}" class="form-control" disabled >
														<option value="${timeTableList.subjectName}">${timeTableList.subjectName}</option>
														 <%-- <c:forEach var="subject" items="${subjectList}">
															<c:if test="${subject.subjectName ne timeTableList.subjectName}">
																<option value="${subject.subjectName}">${subject.subjectName}</option>
															</c:if>
														</c:forEach> --%>
													</select>
												</td>
												<td>
													<input type="text" class="form-control" name="className${indexVal.index}" id="classId${indexVal.index}"  value="${timeTableList.noOfClasses}" disabled />
												</td>
												<td class="actions">
			                                         <a class="on-default edit-row" href="#" id="edit${indexVal.index}"><i class="fa fa-pencil"></i></a>
			                                         <a class="hidden on-editing save-row" href="#" id="save${indexVal.index}"><i class="fa fa-save"></i></a>
			                                     	 <a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
			                                     	 <a href="deleteClassForTeacher.html?detailsId=${timeTableList.teacherUserId}~${timeTableList.classSectionName}~${timeTableList.subjectName}" class="on-default remove-row"><i class="fa fa-trash-o"></i></a>
			                                    </td>
											</tr>
	                                    </c:forEach>
	                                   </tbody>
	                               </table>
	                           </div>
	                       </section>
						</div>
					</form:form>
					</c:otherwise>
					</c:choose>
	            </div>	
					
	

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/configureTimeTable.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/configureTimeTable.editable.js"></script>
</body>
</html>