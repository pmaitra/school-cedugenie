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
<title>Leave Category</title>
 <%@ include file="/include/include.jsp" %> 

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>

</head>
<body>

			<c:if test="${insertUpdateStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>Leave category inserted successfully.</strong>
				</div>
			</c:if>
			<c:if test="${insertUpdateStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>Leave category insertion failed.</strong>
				</div>
			</c:if>
			<!--  /* added by ranita.sur on 14092017 for showing pop up */ -->
			<c:if test="${status eq 'Update Successful'}">
				<div class="alert alert-success">
					<strong>Leave category updated successfully.</strong>
				</div>
			</c:if>
			<c:if test="${status eq 'Update Failed'}">
				<div class="alert alert-danger">
					<strong>Leave category updation failed.</strong>
				</div>
			</c:if>

					<div class="row">
						<div class="col-md-5">
							<form name="leaveCategoryForm" id="leaveCategoryForm" action="insertLeave.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Assign Leave Category</h2>										
									</header>
									 <c:forEach var="leave" items="${leaveList}" varStatus="i">
									 <input type="hidden" name="oldLeaveTypes" value="${leave.leaveType}">
									 </c:forEach>
									<div style="display: block;" class="panel-body">
                                        <div class="form-group">
                                            <label class="control-label">Leave Type</label>
                                            <input type="text" class="form-control" id="leaveType" name="leaveType" placeholder="" pattern="^[a-zA-Z ]+$" title="Leave type can consist of alphabetical characters and spaces only" required/>
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" id="idforsubmit" name="submitxxx" class="btn btn-primary" onclick = "return validation()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						<div class="col-md-7">
						<form name="editLeaveForm" id="editLeaveForm" action="editLeave.html" method="post">
							<input type="hidden" name="saveId" id="saveId">	
							<input type="hidden" name="getLeaveType" id="getLeaveType">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Previous Leave Type</h2>
                                </header>
                                <div class="panel-body" >

                                    <table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                        <thead>
                                            <tr>
                                                <th>Previous Leave Type</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        
                                        <c:forEach var="leave" items="${leaveList}" varStatus="i">
											<tr class="gradeX">
												<td>
													<input type="hidden" name="oldLeaveCode${i.index}" value="${leave.leaveCode}"> 
													
													<input type="hidden" name="leaveName${i.index}" class="form-control" value="${leave.leaveType}" readonly id="leaveName${i.index}" required>
													${leave.leaveType}
												</td>
												<td class="actions">
													  <!--  /* added by ranita.sur on 14092017 for showing pop up */ -->
								                    <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showLeaveTypeDetails('${leave.leaveType}','${i.index}')"><i class="fa fa-pencil"></i></a>
												</td>
											</tr>
										</c:forEach>
                                    </table>
                                </div>
                            </section>
                            
                            <!-- popup Window code -->
             <!--  /* added by ranita.sur on 14092017 for showing pop up */ -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Edit Leave Type</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "leaveCategoryDetails">
												<thead>
													<tr>
				                                        <th>Previous Leave Type</th>
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
													<button id="updateLeaveType" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
                            </form>
						</div>
					</div>	
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
 <!--  /* added by ranita.sur on 14092017 for showing pop up */ -->
	<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/leaveCategory.editable.js"></script>
<script type="text/javascript">
/* function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("leaveName"+rowId).removeAttribute("readonly");
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	var status = validationForEdit(rowId);
	if(status==true){
		document.editLeaveForm.submit();
	}
	
};
 */
 /* added by ranita.sur on 14092017 for showing pop up */
 function showLeaveTypeDetails(leaveName,index)
 {
 	//alert("HII");
 	//alert("LN 97 ::"+departmentName);
 	$('#leaveCategoryDetails> tbody').empty();
  	if(leaveName != null && leaveName!=""){
  		var row = "<tbody>";
  		row += "<tr><td><input type='text' name='leaveName' id='leaveName' class = 'form-control'  value='"+leaveName+"'> </td></tr>";
  		$("#leaveCategoryDetails").append(row);
  	}
  	
  	$('#modalInfo').fadeIn("fast");
  	var btn = document.getElementById("updateLeaveType");
  	btn.setAttribute("onclick","saveData('"+index+"','"+leaveName+"');");
 	
 	}
 /* added by ranita.sur on 14092017 for showing pop up */
 function saveData(rowId,leaveName){
	// alert("HII");		// Alerts are closed by Saif 20/03/2018 not necessary to show those alerts
 	var leaveName=document.getElementById("leaveName").value;
 	//alert("LN 184"+leaveName);
 	document.getElementById("saveId").value=rowId;
 	document.getElementById("getLeaveType").value = leaveName;
   
 	var validateStatus = validationForEdit(rowId);
 	//alert("validateStatus"+validateStatus);
 	if(validateStatus == true){
 		document.editLeaveForm.submit();
 	}
 }
 /* added by ranita.sur on 14092017 for showing pop up */
 function closeWarning(){
 	document.getElementById("warningmsg1").style.display = 'none';	
 }

 function validation(){
	 var oldLeaveTypes = document.getElementsByName("oldLeaveTypes");
	 var leaveType = document.getElementById("leaveType").value;
	 leaveType = leaveType.trim();
	 leaveType = leaveType.toUpperCase();
	 for(var i = 0;i<oldLeaveTypes.length; i++){
		 if(oldLeaveTypes[i].value==leaveType){
			 alert("Leave Type Already Exist");
			 return false;
		 }
	 }
	 return true;
}
 /* added by ranita.sur on 14092017 for showing pop up */
function validationForEdit(rowId){
	  var leaveName=document.getElementById("leaveName").value;
		var alphaNumeric=/^[a-zA-Z \s]+$/;
		var newleaveName = document.getElementById("getLeaveType").value;
		//alert("LN 223"+newleaveName);
		newleaveName=newleaveName.trim();
		newleaveName=newleaveName.toUpperCase();
	 var oldLeaveTypes = document.getElementsByName("oldLeaveTypes");
	 for(var i = 0;i<oldLeaveTypes.length-1; i++){
		// alert("LN 227"+oldLeaveTypes[i]);
		 if(oldLeaveTypes[i].value==newleaveName){
			 document.getElementById("warningmsg1").style.display = 'block';			
			 document.getElementById("warningmsg1").innerHTML = "Leave Type Already Exist";
			// alert("Leave Type Already Exist");
			 return false;
		 }
	 }
	 if(leaveName ==""|| leaveName==null ){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Enter Leave Name"; 
			return false;
		}else if (alphaNumeric.test(leaveName)==false)
	    {
	    	document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Leave Type can contain alphabets and spaces between words !!";
			return false;
	    }else{
		return true;
	   }
}
</script>
</body>
</html>