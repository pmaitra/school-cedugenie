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
<title>User Defined Exams</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .no-bordered td{
       	border: none !important;
       }
       
       #ui-datepicker-div{
       	z-index:99999 !important;
       }
</style>
<link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet">
</head>
<body>
	<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<c:if test="${insertUpdateStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>	
	<!-- start: page -->
    <div class="row">
    	<div class="col-md-8 col-md-offset-2">
		  <form action="addUserDefinedExams.html" method="post">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Create Exam</h2>										
					</header>
					<div style="display: block;" class="panel-body"> 
					<div class="col-md-6">
						<div class="form-group">
							<label class="control-label">Exam Name: </label>
							
								<input name="examName" id = "examName" class="form-control" type="text"  pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]" required>
							
						</div>
					</div>
					 <div class="col-md-6">  
						<div class="form-group">
							<label class="control-label">Exam Type Name:<span class="required" aria-required="true">*</span></label>
							<select class="form-control" name="examType" id = "examType" required >
                             	<option value="">Select...</option>
                                      <c:forEach var="examType" items="${examTypeListFromDB}">
											<option value="${examType.examTypeCode}">${examType.examTypeName}</option>					
									  </c:forEach>
                            </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label">Course : </label>
						<div class="col-sm-9">
							<table class="table mb-none no-bordered">																									
								<c:forEach var="course" items="${courseList}" >
									<tr>
									<td>
										
									</td>	
									<td>
										<input type="checkbox" name="course" id ="course" value="${course.courseCode}"> ${course.courseName}		
									</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div> 
					 <div class="col-md-6">
						 <div class="form-group">
	                           <label class="control-label">Exam Start Date<span class="required" aria-required="true">*</span></label>
	                            <div class="input-group">
	                               <span class="input-group-addon">
	                                   <i class="fa fa-calendar"></i>
	                               </span>
	                               <input type="text" class="form-control" id="startDate" name="startDate"  required>
	                           </div>
	                       </div>  
	                 </div>
					  <div class="col-md-6">
						  <div class="form-group">
							  <label class="control-label">Exam End Date<span class="required" aria-required="true">*</span></label>
							   <div class="input-group">
								  <span class="input-group-addon">
									  <i class="fa fa-calendar"></i>
								  </span>
								  <input type="text" class="form-control" id="endDate" name="endDate"  required>
							  </div>
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
		<div class="col-md-12">
			<form name="editUserDefinedExams" id="editUserDefinedExams" action="editUserDefinedExams.html" method="post">
				<input type="hidden" name="saveId" id="saveId">
				<input type="hidden" name="newExamStartDate" id="newExamStartDate">
				<input type="hidden" name="newExamEndDate" id="newExamEndDate">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						</div>			
						<h2 class="panel-title">Existing Exams</h2>
					</header>
					<div class="panel-body">					
						<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
							<thead>
								<tr>
									<th>Exam</th>
									<th>Course</th>
									<th>Exam Start DAte</th>
									<th>Exam End Date</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="exam" items="${examList}" varStatus="i">
									<tr class="gradeX">
									<td>
										<input type="hidden" name="oldCourseCode${i.index}" value="${course.courseCode}">
										<input type="hidden" name="oldExamNames" value="${exam.examName}">
										<input type="hidden" id="examName${i.index}" name="newCourseName${i.index}" class="form-control" value="${exam.examName}" readonly >
										<input type="hidden" id="examCode${i.index}" name="examCode${i.index}" value="${exam.examCode}"/>
										<input type="hidden" id="rowId" name="rowId" value="${i.index}"/>
										${exam.examName}
									</td>
									<td>	
										<c:forEach var="course" items="${courseList}" >
											<c:set var="checker" value="0" scope="page" />
											<c:forEach var="selectedCourse" items="${exam.courseList}" >
												<c:if test="${selectedCourse.courseCode eq course.courseCode}">
													<c:set var="checker" value="1" scope="page" />
												</c:if>																
												<c:if test="${checker eq 1}">
													<input type="hidden" class="form-control" name="course${i.index}" id = "course${i.index}" value="${course.courseName}" readonly>
													${course.courseName}
													<input type="hidden"  name="courseCode${i.index}" id = "courseCode${i.index}" value="${course.courseCode}" >
												</c:if>
											</c:forEach>
										</c:forEach>
									</td>
									<td>
										${exam.startDate}
									</td>
									<td>
										${exam.endDate}
									</td>
									<td class="actions">
										<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateExam('${i.index}','${exam.examName}','${exam.startDate}','${exam.endDate}')"><i class="fa fa-pencil"></i></a>
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
							<table class="table table-bordered table-striped mb-none" id = "updateExamDetails">
								<thead>
									<tr>
										<th>Exam</th>
										<th>Course</th>
										<th>Exam Start Date</th>
										<th>Exam End Date</th>
									</tr>
								</thead>
								<tbody>
									 
								</tbody>
							</table>
							<div class="alert alert-danger" id="warningmsg" style="display: none">
								<span></span>	
							</div>
						</div>
						<footer class="panel-footer">
							<div class="row">
								<div class="col-md-12 text-right">
									<button id="updateExam" class="btn btn-success">Update</button>
									<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
								</div>
							</div>
						</footer>
					</section>
				</div>
			</form>
		</div>
	</div>
<!-- end: page -->		
					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createUserDefinedExam.editable.js"></script>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript">
	function updateExam(rowId, examName, startDate, endDate){
		var courseName = document.getElementById("course"+rowId).value;
		$('#updateExamDetails > tbody').empty();
	 	if((examName != null && examName!="") && (startDate != null && startDate!="") && (endDate != null && endDate!="")){
	 		var row = "<tbody>";
	 		row += "<tr><td>"+examName+"</td>"
	 		+"<td>"+courseName+"</td>"
	 		+"<td><div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='fa fa-calendar'></i></span><input type='text' id='examNewStartDate' name='examNewStartDate' class='form-control' value='"+startDate+"' required></div></div></td>"
	 		+"<td><div class='form-group'><div class='input-group'><span class='input-group-addon'><i class='fa fa-calendar'></i></span><input type='text' id='examNewEndDate' name='examNewEndDate' class='form-control' value='"+endDate+"' required></div></div></td>"
	 		+"</tr>";
	 		$("#updateExamDetails").append(row);
	 	}
		
	 	/*Calender*/
	 	$("#examNewStartDate").datepicker({
	 		 minDate: 0,
	         maxDate: '+1Y+6M',
	 		 dateFormat: 'dd/mm/yy',
	         onSelect: function (dateStr) {
	             var min = $(this).datepicker('getDate'); // Get selected date
	             $("#examNewEndDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	         }
	    });
	    
	    $("#examNewEndDate").datepicker({
	        minDate: '0',
	        maxDate: '',
			dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var max = $(this).datepicker('getDate'); // Get selected date
	            $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
	        }
	    });
	    
		$('#modalInfo').fadeIn("fast");
		var btn = document.getElementById("updateExam");
	 	btn.setAttribute("onclick","saveData('"+rowId+"');");
	};
	
	
	function saveData(rowId){
		var newExamStartDate = document.getElementById("examNewStartDate").value;
		var newExamEndDate = document.getElementById("examNewEndDate").value;
		
		if(newExamStartDate == null || newExamStartDate == ""){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Exam Start Date can not be Empty.";
			return false;
		}else if(newExamEndDate == null || newExamEndDate == ""){
			document.getElementById("warningmsg").style.display = 'block';			
			document.getElementById("warningmsg").innerHTML = "Exam End Date can not be Empty.";
			return false;
		}else{
			
			document.getElementById("saveId").value=rowId;
			document.getElementById("newExamStartDate").value=newExamStartDate;
			document.getElementById("newExamEndDate").value=newExamEndDate;
			
			document.editUserDefinedExams.submit();
		}
		
	};
	
	function closeWarning(){
		document.getElementById("warningmsg").style.display = 'none';	
	};
	
	function validate(){
		var examName=document.getElementById("examName").value;	
		examName=examName.replace(/\s{1,}/g,' ');
		examName=examName.trim();
		examName=examName.toUpperCase();
		var oldExamNames = document.getElementsByName('oldExamNames');
		for(var i=0; i<oldExamNames.length;i++){
			if(oldExamNames[i].value==examName){
				alert("Exam Name Already Exists");
				return false;
			}
		};
		var courseCodes = document.getElementsByName('course');
		var counter=0;
		for(var i=0; i<courseCodes.length;i++){
			if(courseCodes[i].checked)
				counter=counter+1;
		}
		if(counter<=0){			
			alert("Select atleast one Course.");
			return false;
		}
		return true;
	};
	$(function(){
		    $("#startDate").datepicker({
		        minDate: 0,
		        maxDate: '+1Y+6M',
				dateFormat: 'dd/mm/yy',
		        onSelect: function (dateStr) {
		            var min = $(this).datepicker('getDate'); // Get selected date
		            $("#endDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
		        }
		    });

		    $("#endDate").datepicker({
		        minDate: '0',
		        maxDate: '+1Y+6M',
				dateFormat: 'dd/mm/yy',
		        onSelect: function (dateStr) {
		            var max = $(this).datepicker('getDate'); // Get selected date
		            $('#datepicker').datepicker('option', 'maxDate', max || '+1Y+6M'); // Set other max, default to +18 months
		        }
		    });
	});
</script>
</body>
</html>