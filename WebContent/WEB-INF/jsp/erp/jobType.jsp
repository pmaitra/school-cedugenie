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
       }.mb-md{
       	   display: none;
       }
</style>
<!-- added by ranita.sur on 11092017 -->
<script type="text/javascript">
<!-- modified by ranita.sur on 21092017 -->

function validateJobTypeForm(){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	
	// Validate Job Type Name
	var obj = document.getElementById("jobTypeName");
	var jobTypeName=obj.value;
	jobTypeName=jobTypeName.replace(/\s{1,}/g,' ');
	jobTypeName=jobTypeName.trim();
	jobTypeName=jobTypeName.toUpperCase();
	obj.value=jobTypeName;
	
	if (jobTypeName == "") {			
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Job Type";
		//alert("Please Enter Job Type");
		return false;
	}
	if (jobTypeName != '') {
		if (!jobTypeName.match(alphaNumeric)) {
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Invalid Job Type";	
			//alert("Invalid Job Type");
			return false;
		}
	}
	var oldJobTypeNames = document.getElementsByName('oldJobTypeNamesForDuplicateCheck');
	for(var i=0; i<oldJobTypeNames.length;i++){
		if(oldJobTypeNames[i].value==jobTypeName){			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Job Type Name Already Exists";
			//alert("Job Type Name Already Exists");
			return false;
		}
	}
	return true;
}
function showJobTypeDetails(jobTypeName,index)
{
	
	$('#jobTypesNames > tbody').empty();
 	if(jobTypeName != null && jobTypeName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='jobTypeName' id='jobTypeName' class = 'form-control'  value='"+jobTypeName+"'></td></tr>";
 		$("#jobTypesNames").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateJobType");
 	btn.setAttribute("onclick","saveData('"+index+"','"+jobTypeName+"');");
	
	}

function validateEditJobTypeForm(rowId){
	//alert("Hii");
	var jobTypeName=document.getElementById("jobTypeName").value;
	//alert("jobTypeName"+jobTypeName);
	var alphaNumeric=/^[a-zA-Z \s]+$/;
	var newjobTypeName = document.getElementById("getJobType").value;
	//alert("newjobTypeName"+newjobTypeName);
	newjobTypeName=newjobTypeName.trim();
	newjobTypeName=newjobTypeName.toUpperCase();
	
	var jobTypeList = document.getElementsByName("oldJobTypeNamesForDuplicateCheck");		
	for(var i=0; i<jobTypeList.length-1; i++){
		var oldVal=jobTypeList[i].value;
		//alert("oldVal"+oldVal);
		if(oldVal==newjobTypeName){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Duplicate Job Type";
			//alert("Duplicate Job Type");
			return false;
		}
	}
	if(jobTypeName ==""|| jobTypeName==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter JobType Name"; 
		return false;
	}else if (alphaNumeric.test(jobTypeName)==false)
    {
    	document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Job type name can contain alphabets and spaces between words !!";
		return false;
    }else
		{
		return true;
		}
		
	
	
}
function saveData(rowId,jobTypeName){
	var jobTypeName=document.getElementById("jobTypeName").value;
	document.getElementById("saveId").value=rowId;
	document.getElementById("getJobType").value = jobTypeName;
  
   var validateStatus = validateEditJobTypeForm(rowId);
	//alert("LN 145 ::"+validateStatus);
	if(validateStatus == true ){
		document.editJobType.submit();
	}
}
function closeWarning(){
	document.getElementById("warningmsg1").style.display = 'none';	
}

</script>
</head>
<body>
					<!-- added by ranita.sur on 21092017 -->

                          <div class="alert alert-danger" id="javascriptmsg" style="display: none">
							    <span></span>
							</div>
					<div class="row">
						<div class="col-md-5">
						  	<form:form name="submitJobType" action="submitJobType.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create New Job Type</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        										
                                        <div class="form-group">
                                            <label class="control-label">Enter Job Type Name <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="jobTypeName" id="jobTypeName" placeholder="">
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateJobTypeForm();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
                           
						</div>
						
						<c:if test="${submitResponse ne null}">			
							<c:if test="${submitResponse eq 'Success'}">
								<div class="alert alert-success">
									<strong>Job type created successfully.</strong>	
								</div>
							</c:if>
							<c:if test="${submitResponse eq 'Fail'}">
								<div class="alert alert-danger">
									<strong>Job type creation failed.</strong>
								</div>
							</c:if>
						</c:if>
						<c:if test="${updateResponse ne null}">	
							<c:if test="${updateResponse eq 'Success'}">
								<div class="alert alert-success">
									<strong>Job type updated successfully.</strong>
								</div>
							</c:if>
							<c:if test="${updateResponse eq 'Fail'}">
								<div class="alert alert-danger">
									<strong>Job type updation failed.</strong>
								</div>
							</c:if>			
						</c:if>
						
						<c:choose>
							<c:when test="${jobTypeList == null ||  empty jobTypeList}">
								<div class="alert alert-danger">
									<strong>No job type created yet.</strong>	
					 			</div>
							</c:when>	
						<c:otherwise>
						<div class="col-md-7">	
                            <section class="panel">
                            	<form name="editJobType" id="editJobType" action="editJobType.html" method="post">
								<input type="hidden" name="saveId" id="saveId">
								<input type="hidden" name="getJobType" id="getJobType">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">View / Edit Job Type</h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                        <thead>
                                            <tr>
                                                <th>Job Type Name</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach var="jobType" items="${jobTypeList}" varStatus="i">
	                                            <tr>
	                                                <td>
	                                                	<input type="hidden" name="oldJobTypeNames${i.index}" value="${jobType.jobTypeName}">
	                                                	<input type="hidden" name="oldJobTypeNamesForDuplicateCheck" value="${jobType.jobTypeName}">
	                                                	<input type="hidden" class="form-control" id="jobTypeName${i.index}" name="jobTypeName${i.index}" value="${jobType.jobTypeName}" >
	                                                	${jobType.jobTypeName}
                                                	</td>
	                                               <td class="actions">
	                                               <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showJobTypeDetails('${jobType.jobTypeName}','${i.index}')"><i class="fa fa-pencil"></i></a>
														
													</td>
	                                            </tr>
	                                         </c:forEach>   
                                        </tbody>
                                    </table>
                                </div>
                             </form>
                          </section>
						</div>
						
						<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit Designation Type</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id ="jobTypesNames">
												<thead>
													<tr>
				                                        <th>Job Type</th>
				                                   </tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg1" style="display: none">
												<span></span>	
											</div>
										</div>
										
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateJobType" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
													
												</div>
											</div>
										</footer>
									</section>
								</div>
					</c:otherwise>
					</c:choose>	
					</div>	
					


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/erp/jobType.editable.js"></script>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>



</body>
</html>