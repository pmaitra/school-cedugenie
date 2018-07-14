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
       } .mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script>
var count=0;
function addrows(){
	//document.getElementById("warningbox").style.visibility='collapse';
	
	var roleName=document.getElementsByName("roleName");
	/* for(var i=0;i<roleName.length;i++){
		if(roleName[i].value==""){
			document.getElementById("warningbox").style.visibility="visible";
			document.getElementById("warningmsg").innerHTML="Please Select Role Name No. "+(i+1);
			return false;
		}
	} */
	
	var table = document.getElementById("roleTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
 			
 	var cell0 = row.insertCell(0);
	var existingRoles=document.getElementsByName("roleName");	
	
	var newSelect=document.getElementById("roleNameDefault").cloneNode(true);
	newSelect.removeAttribute("style");
	newSelect.setAttribute("name","roleName");
	newSelect.setAttribute("required","");
	var newid="newSelect"+count;
	newSelect.id=newid;		
	count++;
	
	for(var x=0;x<existingRoles.length;x++){
		for(var i=0;i<newSelect.length;i++){		
			if(existingRoles[x].value==newSelect.options[i].value)
				newSelect.remove(i);
		}
	}
	cell0.appendChild(newSelect);
	
	var cell1 = row.insertCell(1);
	/* var element1 = document.createElement("img");
	element1.setAttribute("src","/cedugenie/images/minus_icon.png");	 */	
	var element1 = document.createElement('a');
	//element1.setAttribute("src","/cedugenie/images/minus_icon.png");	
	 element1.setAttribute("class","fa fa-minus-square");
	element1.setAttribute("onclick", "deleteRow(this);");
	element1.setAttribute("href","#");
	cell1.appendChild(element1);
}


function deleteRow(obj){	
	var table = document.getElementById("roleTable");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}
	/* else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	} */
}

function validate(){
	var alphaNumeric = /^[A-Za-z0-9 ]{1,50}$/;
	var accessTypeName = document.getElementById("accessTypeName").value;
	accessTypeName = accessTypeName.replace(/\s{1,}/g,' ');
	accessTypeName = accessTypeName.trim();
	if(accessTypeName == ""){
		alert("Please Enter Access Type Name");
		/* document.getElementById("warningbox").style.visibility="visible";
		document.getElementById("warningmsg").innerHTML="Please Enter Access Type Name"; */
		return false;
	}
	if (accessTypeName != '') {
		if (!accessTypeName.match(alphaNumeric)) {
			alert("Invalid Access Type Name");
			return false;
		}
	}
	var roleName = document.getElementsByName("roleName");
	for(var i=0;i<roleName.length;i++){
		if(roleName[i].value==""){
			alert("Please Select Role Name No. "+(i+1));
			return false;
		}
	}
	return true;
}

</script>
</head>
<body>
		<header class="page-header">
			<h2>Access Type Role Mapping</h2>	<!--  ADDED BY SAIF 28-03-2018 -->
		</header>
			<div class = "content-padding">
				<div class="row">
					<c:if test="${successMessage ne null}">
						<div class="alert alert-success" id="successboxmsgbox" >
							<strong>${successMessage}</strong>	
						</div>
					</c:if>
			
					<c:if test="${errorMessage ne null}">
							<div class="alert alert-danger" id="errormsgbox" >
								<strong>${errorMessage}</strong>	
							</div>
					</c:if>
						<div class="col-md-12">
						 <form:form action="addRoleAccessMapping.html" method="POST">
							 <c:choose>
								<c:when test="${roleList eq null || roleList.size() eq 0}">
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Role Found To Map Access Type</span>	
									</div>						
								</c:when>
							<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Role Access Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label class="control-label">Access Type Name<span class="required" aria-required="true">*</span></label>
                                                    <input type="text" class="form-control" name="accessTypeName" id="accessTypeName" placeholder="" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="The full name can consist of alphabetical characters and spaces only" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Access Type Description</label>
                                                    <textarea maxlength="250" data-plugin-maxlength="" rows="3" name="accessTypeDesc" id="accessTypeDesc" class="form-control" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="The full name can consist of alphabetical characters and spaces only" ></textarea>
                                                </div> 
                                            </div>
                                            <div class="col-md-6">
                                                <table class="table table-bordered table-striped mb-none" id = "roleTable">
                                                    <thead>
                                                        <tr>
                                                            <th>Role Name</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <select class="form-control" name="roleName" id = "roleName" required>
                                                                    <option value="">Select...</option>
	                                                                    <c:forEach var="role" items="${roleList}">
																			<option value="${role.roleCode}">${role.roleName}</option>					
																		</c:forEach>
                                                                </select>
                                                            </td>
                                                            <td><!-- <a class="on-default remove-row" href="#" onclick="deleteRow(this);"><i class="fa fa-trash-o"></i></a> --></td>
                                                        </tr>
                                                    </tbody>                                                    
                                                </table>
                                                <a class="mb-xs mt-xs mr-xs modal-basic btn btn-info pull-right" href="#modalInfo" id="addrow" onclick="addrows();">Add</a>
                                            </div>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type = "submit" class="btn btn-primary" id="submit" >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
								</c:otherwise>
								</c:choose>
                            </form:form>
						</div>
					</div>
			</div>
				
<select id="roleNameDefault" style="visibility: collapse;" class = "form-control">
	<option value="">Select...</option>
	<c:forEach var="role" items="${roleList}">
		<option value="${role.roleCode}">${role.roleName}</option>					
	</c:forEach>								
</select>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>				
</body>
</html>