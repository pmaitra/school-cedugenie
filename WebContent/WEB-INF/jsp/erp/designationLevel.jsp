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
<script type="text/javascript">
function validateDesignationLevelForm(){
	var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
	var successmsg=document.getElementById("successmsg");
	if(null != successmsg){
		successmsg.innerHTML = "";
		document.getElementById("successbox").style.visibility='collapse';
	}				
	// Validate DesignationType Name
	var obj = document.getElementById("designationLevelName");	
	var designationLevelName=obj.value;
	designationLevelName=designationLevelName.replace(/\s{1,}/g,' ');
	designationLevelName=designationLevelName.trim();
	designationLevelName=designationLevelName.toUpperCase();
	obj.value=designationLevelName;
	if (designationLevelName == "") {			
		document.getElementById("javascriptmsg").style.display = 'block';			
		document.getElementById("javascriptmsg").innerHTML = "Please Enter Designation Level";		
		return false;
	}
	
	if (designationLevelName != '') {
		if (!designationLevelName.match(alphaNumeric)) {			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Invalid Designation Level ";
			return false;
		}
	}
	
		var oldDesignationLevelNames = document.getElementsByName('oldDesignationLevelNamesforDuplicateChecking');
		for(var i=0; i<oldDesignationLevelNames.length;i++){
			if(oldDesignationLevelNames[i].value==designationLevelName){
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Designation Level Already Exists";				
				return false;
			}
		}		
}



function validateEditDesignationLevelForm(rowId ){	
	//alert("LN 64");
	var alphaNumeric=/^[a-zA-Z0-9 ]+$/;			
	var designationLevelName = document.getElementById("designationLevelName").value;
	//alert("LN 67"+designationLevelName);
	var newDesignationLevelName = document.getElementById("getNewDesignationLevel").value;
	newDesignationLevelName=newDesignationLevelName.trim();
	newDesignationLevelName=newDesignationLevelName.toUpperCase();
	
	var desigTypeList = document.getElementsByName("oldDesignationLevelNamesforDuplicateChecking");		
	for(var i=0; i<desigTypeList.length-1; i++){
		var oldVal=desigTypeList[i].value;
		if(oldVal==newDesignationLevelName){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Duplicate Designation Level";
			return false;
		}
	}
   if(designationLevelName ==""|| designationLevelName==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter DesignationLevel Name"; 
		return false;
	}else if (alphaNumeric.test(designationLevelName)==false)
    {
    	document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "DesignationLevel Name can contain alphabets and spaces and numeric between words !!";
		return false;
    }else
		{
		return true;
		}
}
	

function showDesignationLevelDetails(designationLevelName,rowId){
	//alert("rowId : "+rowId);
	//alert("LN 85 :: "+designationLevelName);
	
	$('#designationLevelNames > tbody').empty();
 	if(designationLevelName != null && designationLevelName!=""){
 		//alert("LN 108 :: ");
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='designationLevelName' id='designationLevelName' class = 'form-control'  value='"+designationLevelName+"'></td></tr>"; 
 				
 		$("#designationLevelNames").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateDesignationLevel");
 	btn.setAttribute("onclick","saveData('"+rowId+"','"+designationLevelName+"');");
	
	
}

function saveData(rowId,designationLevelName){
	var designationLevelName = document.getElementById("designationLevelName").value;
	//alert("LN 121 :" +designationLevelName);
	document.getElementById("saveId").value=rowId;	
	document.getElementById("getNewDesignationLevel").value=designationLevelName;
	var validateStatus = validateEditDesignationLevelForm(rowId);
	//alert("LN 126 :" +validateStatus);
	if(validateStatus == true){	
		document.editDesignationLevelForm.submit();
	}
	};
	function closeWarning(){
		document.getElementById("warningmsg1").style.display = 'none';	
	}
	
	function validateDesignationLevelForm(){
		var alphaNumeric=/^[A-Za-z0-9 ]{1,50}$/;
		var successmsg=document.getElementById("successmsg");
		if(null != successmsg){
			successmsg.innerHTML = "";
			document.getElementById("successbox").style.visibility='collapse';
		}				
		// Validate DesignationType Name
		var obj = document.getElementById("designationLevelName");	
		var designationLevelName=obj.value;
		designationLevelName=designationLevelName.replace(/\s{1,}/g,' ');
		designationLevelName=designationLevelName.trim();
		designationLevelName=designationLevelName.toUpperCase();
		obj.value=designationLevelName;
		if (designationLevelName == "") {			
			document.getElementById("javascriptmsg").style.display = 'block';			
			document.getElementById("javascriptmsg").innerHTML = "Please Enter Designation Level";		
			return false;
		}
		
		if (designationLevelName != '') {
			if (!designationLevelName.match(alphaNumeric)) {			
				document.getElementById("javascriptmsg").style.display = 'block';			
				document.getElementById("javascriptmsg").innerHTML = "Invalid Designation Level ";
				return false;
			}
		}
		
			var oldDesignationLevelNames = document.getElementsByName('oldDesignationLevelNamesforDuplicateChecking');
			for(var i=0; i<oldDesignationLevelNames.length;i++){
				if(oldDesignationLevelNames[i].value==designationLevelName){
					document.getElementById("javascriptmsg").style.display = 'block';			
					document.getElementById("javascriptmsg").innerHTML = "Designation Level Already Exists";				
					return false;
				}
			}		
	}

</script>
</head>
<body>
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Add Designation Level</h2>
	</header>
	<div class = "content-padding">
		<div class="row">
		<div class="col-md-5">
		  	<form:form name="submitDesignationLevel" action="submitDesignationLevel.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
	
						<h2 class="panel-title">Create Designation Level</h2>										
					</header>
					<div style="display: block;" class="panel-body">
	                                   										
                       <div class="form-group">
                           <label class="control-label">Enter Designation Level <span class="required" aria-required="true">*</span></label>
                           <input type="text" class="form-control" name="designationLevelName" id="designationLevelName" placeholder="">
                       </div> 
	                                       
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateDesignationLevelForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
	         </form:form>
		</div>
		
		<div class="alert alert-warning" id="javascriptmsg" style="display: none">
		  <span></span>	
		</div>
		
		<c:if test="${submitResponse ne null}">			
			<c:if test="${submitResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Designation level successfully created.</strong>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Designation level creation failed.</strong>	
				</div>
			</c:if>			
		</c:if>
		<c:if test="${updateResponse ne null}">	
			<c:if test="${updateResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>Designation level successfully updated.</strong>	
				</div>
			</c:if>
			<c:if test="${updateResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>Designation level updation failed.</strong>	
				</div>
			</c:if>			
		</c:if>
		
		<c:choose>
			<c:when test="${designationLevelList == null ||  empty designationLevelList}">
				<div class="alert alert-danger">
					<strong>No designation level created yet</strong>	
	 			</div>
			</c:when>	
		<c:otherwise>
		<div class="col-md-7">	
        <section class="panel">
        	<form name="editDesignationLevelForm" id="editDesignationLevelForm" action="editDesignationLevel.html" method="post">
				<input type="hidden" name="saveId" id="saveId">
				<input type="hidden" name="getNewDesignationLevel" id="getNewDesignationLevel">
                <header class="panel-heading">
                    <div class="panel-actions">
                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                    </div>

                    <h2 class="panel-title">View / Edit Designation Level</h2>
                </header>
                <div class="panel-body">
                    <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                        <thead>
                            <tr>
                                <th>Designation Level Name</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<c:forEach var="designationLevel" items="${designationLevelList}" varStatus="i">
                    		<tr>
		                        <td>
		                        	<input type="hidden" name="oldDesignationLevelNames${i.index}" value="${designationLevel.designationLevelName}">
		                        	<input type="hidden" class="form-control" id="designationLevelName${i.index}" name="designationLevelName${i.index}" value="${designationLevel.designationLevelName}" >
		                       		<input type="hidden" name="oldDesignationLevelNamesforDuplicateChecking" value="${designationLevel.designationLevelName}">
		                       		${designationLevel.designationLevelName}
		                       	</td>
		                       <td class="actions">
		                        <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showDesignationLevelDetails('${designationLevel.designationLevelName}','${i.index}');"><i class="fa fa-pencil"></i></a>
									<%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
									<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
									<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a> --%>
								</td>
                  			 </tr>
                      		</c:forEach>   
                    	</tbody>
	                </table>
	            </div>
            </form>
              <!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit Designation Level</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "designationLevelNames">
												<thead>
													<tr>
				                                        <th>Designation Level </th>
				                                       
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
													<button id="updateDesignationLevel" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
        </section>
	</div>
	
	
		</c:otherwise>
	</c:choose>	
	
	</div>
	
</div>	
					


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/erp/designationLevel.editable.js"></script>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
<!-- <script src="/icam/js/erp/designationLevel.js"></script> -->
</body>
</html>