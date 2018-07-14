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
<title>Create Scholarship</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
				

<script>
$(document).ready(function() {
/* // 	 $('#startDate').Zebra_DatePicker();
// 	 $('#endDate').Zebra_DatePicker();
	 
// 	 $('#startDate').Zebra_DatePicker({
// 	 	  format: 'd/m/Y'
// 	 	});
// 	 $('#endDate').Zebra_DatePicker({
// 	  format: 'd/m/Y'
// 	});
	/*submit action*/
	/* var codeandtype = "";
	var code="";
	var termName="";
	var termStartDateVal = "";
	var termEndDateVal = "";
	var value = "";
	$('#submitPre').click(function(){
		$('input:checkbox').each(function()
				{	
			<c:forEach var="year" items="${AcademicYear}">
			value = '<c:out value="${year.academicYearCode}"></c:out>';
			$("#hiddenAcademicYear").val(value);
			</c:forEach>
					if ($(this).is(':checked'))
					{		
		    			code = $(this).val();
		    			termName = $(this).parent().next().next().next().find('input:text').val();
		    			termStartDateVal = $(this).parent().next().next().next().next().find('input:text').val();
		    			termEndDateVal = $(this).parent().next().next().next().next().next().find('input:text').val();
					}
					if(code != ""){
						codeandtype = code + "," + termName + "," + termStartDateVal + "," + termEndDateVal  +  "," + value + "/" + codeandtype;
					}
					
	    	
				});
		$('#hiddenTermType').val(codeandtype);
		editAction();
				});	
  */ 
				
		$("#standard").change(function(){
			$('#course').empty().append('<option value="">Select</option>');
			$.ajax({
		    url: '/cedugenie/getCourseForTermCreation.html',
		    dataType: 'json',
		    data:"standard=" + ($(this).val()),
		    success: function(data) {
		    	if(data == ""){
		    		alert("Course is not assigrned for particular Class.");
	        	}
	        	if(data != ""){
		    		  var arrCourse = data.split(",");
		    		var courseid=document.getElementById("course");
		    		 for(var courselist=0;courselist<=arrCourse.length-2;courselist++){
		    			 var option = document.createElement("option");
		    				 option.value = arrCourse[courselist];
			    		     option.text = arrCourse[courselist];
			    		     courseid.appendChild(option);


		    		 }
		    	}
		    } 
		}); 
	});
});
</script>
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("termType"+rowId).removeAttribute("readonly");
	document.getElementById("termStartDate"+rowId).removeAttribute("readonly");
	document.getElementById("termEndDate"+rowId).removeAttribute("readonly");
	
	
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	var status = editValidate(rowId);
	if(status == true){
		document.editAcademicTerm.submit();
	}
	
	
};
</script>

</head>
<body onload="validationPage()">
					<div class="row">						
						<div class="col-md-8 col-md-offset-2">
					  		<form action = "insertAcademicTerm.html" method = "POST" >
						  		<c:choose>
									<c:when test="${AcademicYear eq null}">	
										<div class="errorbox" id="errorbox" style="visibility:visible;">
											<span id="errormsg">No Academic Year Found</span>
										</div>					
									</c:when>
								<c:otherwise>
									<section class="panel">
										<header class="panel-heading">
											<div class="panel-actions">
												<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
											</div>
	
											<h2 class="panel-title">Create Academic Term</h2>										
										</header>
										<c:forEach var="term" items="${TermName}" varStatus="status" >
											<input type="hidden" class="form-control" id="termNames" name="termNames" value = "${term.termName}">
										</c:forEach>
										<div style="display: block;" class="panel-body" id="categoryDetail">
	                                        <div class="row">
	                                            <div class="col-md-6">
	                                                <div class="form-group">
	                                                    <label class="control-label">Academic Year</label>
	                                                    <c:forEach var="year" items="${AcademicYear}">
	                                                    	<input type="text" class="form-control" id="academicYear" name="academicYear" value = "${year.academicYearCode}" readonly="readonly">
	                                                	</c:forEach>
	                                                </div>
	                                                <div class="form-group">
	                                                    <label class="control-label">Academic Term</label>
	                                                    <input type="text" class="form-control" id="academicTermType" name="academicTermType" required>
	                                                </div>
	                                                 <div class="form-group">
	                                                    <label class="control-label">Standard</label>
	                                                    <select class="form-control" id="standard" name="classObj" required>
	                                                        <option value="">Select...</option>
	                                                        <c:forEach var="classNameList" items="${classNameList}" >
																<option value="${classNameList.standardCode}">${classNameList.standardName}</option>
															</c:forEach>
	                                                    </select>
	                                                </div>
	                                            </div>
	                                            <div class="col-md-6">  
	                                         		<div class="form-group">
	                                         			<label class="control-label">Start Date</label>
		                                         			<div class="input-group">
				                                                 <span class="input-group-addon">
				                                                       <i class="fa fa-calendar"></i>
				                                                  </span>
				                                                  <input type="text" id="startDate"  name="startDate"  value="" data-plugin-datepicker="" class="form-control">
				                                             </div>
	                                         		</div>
	                                         		<div class="form-group">
	                                         			<label class="control-label">End Date</label>
		                                         			<div class="input-group">
				                                                 <span class="input-group-addon">
				                                                       <i class="fa fa-calendar"></i>
				                                                  </span>
				                                                  <input type="text" id="endDate" name="endDate" value="" data-plugin-datepicker="" class="form-control">
				                                             </div>
	                                         		</div>
	                                                <div class="form-group">
	                                                    <label class="control-label">Course</label>
	                                                    <select id="course" class="form-control" name="course.courseName" required>
	                                                        <option value="">Select...</option>
	                                                    </select>
	                                                </div>
	                                            </div>                                            
	                                        </div>                                            
										</div>
	                                    <footer style="display: block;" class="panel-footer">
	                                        <button type = "submit" class="btn btn-sm btn-primary" id="submit" name="submit" value="Submit" onclick = "return submitActionValidation()">Submit</button>
											<button type="reset" class="btn btn-sm btn-default">Reset</button>
										</footer>
									</section>
								</c:otherwise>
								</c:choose>	
                            </form>
						</div>
					</div>
					<c:choose>
						<c:when test="${TermName eq null}">			
							 <div class="errorbox" id="errorbox" style="visibility:visible;">
								<span id="errormsg">No Academic Term Found</span>
							</div>	 	
						</c:when>
					<c:otherwise>
                    <div class="row">
                        <div class="col-md-12">
						  <form name = "editAcademicTerm" id = "editAcademicTerm" action="editAcademicTerm.html" method="POST">
						  		<input type="hidden" name="saveId" id="saveId">
						  		<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Academic Term</h2>										
									</header>
									<div style="display: block;" class="panel-body" id="previousCategoryDetail">   
										<div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-bordered table-striped mb-none dataTable" id="datatable-editable">
                                                	<thead>
														<tr>
															<th>Standard</th>
															<th>Course Name</th>
															<th>Term Name</th>
															<th>Start Date</th>
															<th>End Date</th>									
															<th>Actions</th>
														</tr>
													</thead>
                                                    <tbody>
                                                    	<c:forEach var="term" items="${TermName}" varStatus="status" >	
	                                                        <tr>
	                                                            <td>
	                                                            	<input type="text" class="form-control" name="termClass" id="termClass" value="${term.classObj}" readonly="readonly" >
                                                            	</td>
	                                                            <td>
	                                                            	<input type="text" class="form-control" name="termCourse" id="termCourse" readonly="readonly" value="${term.course.courseName}">
                                                            	</td>
	                                                            <td>
	                                                            	<input type="hidden" class="form-control" name="oldTermDetailsCode${status.index}" value="${term.termDetailsId}">
	                                                            	<input type="text" class="form-control" name="termType${status.index}" id="termType${status.index}" value="${term.termName}" readonly >
                                                            	</td>
	                                                            <td>
	                                                            	<input type="hidden" class="form-control" name="hiddenTermStartDate${status.index}" id="hiddenTermStartDate${status.index}"  value="${term.termStartDate}" >
	                                                            	<input type="text" class="form-control" name="termStartDate${status.index}" id="termStartDate${status.index}"  value="${term.termStartDate}" readonly>
                                                            	</td>
	                                                            <td>
                                                            		<input type="text" class="form-control" name="termEndDate${status.index}" id="termEndDate${status.index}"  value="${term.termEndDate}" readonly>
                                                            		<input type="hidden" class="form-control" name="hiddenTermEndDate${status.index}" id="hiddenTermEndDate${status.index}"  value="${term.termEndDate}" >
                                                            		<input class="textfield1" type="hidden" name="dateOfCreation${status.index}" id="dateOfCreation${status.index}"  value="${term.dateOfCreation}" readonly>
                                                           		</td>
	                                                            <td class="actions">
																	<a href="#" class="hidden on-editing save-row" id="save${status.index}"><i class="fa fa-save"></i></a>
																	<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
																	<a href="#" class="on-default edit-row" id="edit${status.index}"><i class="fa fa-pencil"></i></a>
																</td>
	                                                        </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
									</div>
								</section>
                            </form>
						</div>
                    </div>
 					</c:otherwise>
 					</c:choose>
 					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/backoffice/createScholarship.editable1.js"></script>	
<script>

 
 function editValidate(rowId){
	 var termNames = document.getElementsByName("termNames");
	 var academicTermType = document.getElementById("termType"+rowId).value;
	 var hiddenTermStart = document.getElementById("hiddenTermStartDate"+rowId).value;
	 var hiddenTermEnd = document.getElementById("hiddenTermEndDate"+rowId).value;
	 var termStart = document.getElementById("termStartDate"+rowId).value;
	 var termEnd = document.getElementById("termEndDate"+rowId).value;
		for(var i=0; i<termNames.length;i++){
			if(termNames[i].value==academicTermType){
				if(hiddenTermStart==termStart){
					if(hiddenTermEnd == termEnd){
						alert("Term with same Information Already Exixts");
						return false;
					}
					
				}
				
			}
		}
		return true;
 }
function submitActionValidation(){
	var termStart = document.getElementById("startDate");
	var termEnd = document.getElementById("endDate");
	if(termEnd > termStart){
		alert("Term End Date can not be before Term Start Date");
		return false;
	}
	var termNames = document.getElementsByName("termNames");
	var academicTermType = document.getElementById("academicTermType").value;
	for(var i=0; i<termNames.length;i++){
		if(termNames[i].value==academicTermType){
			alert("Term Name Already Exixts");
			return false;
		}
	}
	return true;
}

	

</script>

	
</body>
</html>
