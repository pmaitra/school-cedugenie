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
       }  .mb-md{
       	   display: none;
       } 
</style>

<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

<script>
$(document).ready(function() { 
	$("#moduleName").change(function() {
		//document.getElementById("warningbox").style.visibility='collapse';			
		if($("#moduleName").val()== "null" || $("#moduleName").val()==''){
			return;
		}
		$.ajax({
	        url: '/cedugenie/getRolesForModule.html',
	        dataType: 'json',
	        data: "Module=" + ($("#moduleName").val()),
	        success: function(data){        					
	        	if(data != ''){	
		        	removeOption();
	        	    var roleArray=data.split("#");	        	   
	        	    for (var count=0;count<roleArray.length-1;count++)	{  
		        	    var roleDesc= new Array();
		        	    roleDesc=roleArray[count].split("*"); 
		        	    $("#roleName").append($("<option></option>").val(roleDesc[1]).html(roleDesc[0]));  
	        	    }
	        	}else{
	        		document.getElementById("warningbox").style.visibility='visible';	        		
	        		document.getElementById("warningmsg").innerHTML = "No Role Created For This Module";
	        	}
	        }			        
		}); 		
	});
});
function removeOption(){
	var roleName=document.getElementById("roleName");
	for(var count=roleName.length;count>0;count--){
		roleName.remove(count);
	}
}

function validate() {
	var moduleName=document.getElementById("moduleName").value;
	var roleName=document.getElementById("roleName").value;
	
	if(moduleName=="" || moduleName=='null'){		
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select a Module";		
		return false;
	}
	
	if(roleName=="" || roleName=='null'){		
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select Role Name";		
		return false;
	}
}

function selection(thisCheck){	
	if(thisCheck.checked){			
		$(thisCheck).parent().next().find('input').prop('checked', true);   //view	
		$(thisCheck).parent().next().next().find('input').prop('checked', true);	//insert	
		$(thisCheck).parent().next().next().next().find('input').prop('checked', true);		//update	
	}else{		
		$(thisCheck).parent().next().find('input').removeAttr('checked');	//view
		$(thisCheck).parent().next().next().find('input').removeAttr('checked');	//insert
		$(thisCheck).parent().next().next().next().find('input').removeAttr('checked');	//update
	}
}
</script>
</head>
<body>
	<header class="page-header">
			<h2>Functionality Role Mapping</h2>	<!--  ADDED BY SAIF 28-03-2018 -->
	</header>
		<div class = "content-padding">
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
					 <div class="row">
						<div class="col-md-4">
						  <form:form action="functionalityRoleMapping.html" method="post" name="functionalityRoleMapping" id="functionalityRoleMapping">
								<c:choose>
									<c:when test="${moduleList eq null || moduleList.size() eq 0}">
										<div class="errorbox" id="errorbox" style="visibility: visible;">
											<span id="errormsg">No Module Found</span>	
										</div>
									</c:when>
								<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Functionality - Role Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label">Module Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control"  name="moduleCode" id="moduleName" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="module" items="${moduleList}">
													<option value="${module.moduleCode}">${module.moduleName}</option>
												</c:forEach>	
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label">Role Name <span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" name="roleCode" id="roleName" required>
                                                <option value="">Select...</option>
                                            </select>
                                        </div> 
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
								</c:otherwise>
								</c:choose>
                           </form:form>
						</div>
						
						<c:choose>
							 <c:when test="${errorMessage ne null}">
								<%-- <div class="errorbox" id="errorbox" style="visibility: visible;">
									<span id="errormsg">${errorMessage}</span>	
								</div>	 --%>					
							</c:when> 
						<c:otherwise>
 						<div class="col-md-8">	
 						<form:form action="insertFunctionalityRoleMapping.html" method="post">
                            <section class="panel">
                            	
                            		<input type="hidden" value="${module.moduleCode}" name="moduleCode">
                            		<%-- <input type="text" value="${module.moduleName}" name="moduleName"> --%>
	                                <header class="panel-heading">
	                                    <div class="panel-actions">
	                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                                    </div>
 										<c:forEach var="role" varStatus="roll" items="${module.roleList}"> 
 										<h2 class="panel-title">Module Name :: ${module.moduleName}</h2>
 										<br></br>
	                                    <h2 class="panel-title">Role Name :: ${role.roleName}</h2>
	                                    <input type="hidden" value="${role.roleCode}" name="roleCode">
 	                                    </c:forEach> 
	                                </header>	
	                                <div class="panel-body">
	 								<%-- <c:forEach var="role" varStatus="roll" items="${module.roleList}"> --%>
	                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
		                                       
			                                        <thead>
			                                      		                                           
			                                            <tr>
			                                                <th>Functionality Name</th>
			                                                <th>Select All</th>
			                                                <th>View</th>
			                                                <th>Insert</th>
			                                                <th>Update</th>
			                                            </tr>
			                                       
			                                        </thead>
			                                        <tbody>
			                                        <c:forEach var="role" varStatus="roll" items="${module.roleList}">
			                                        	<c:forEach var="functionality"  items="${role.functionalityList}">
				                                            <tr>
				                                                <td>
				                                                	<input type="text" class="form-control" name="functionality${roll.index}" value="${functionality.functionalityName}" readonly>
			                                                	</td>
				                                                <td>
				                                                	<input type="checkbox" id="checkboxExample1" onchange="selection(this);">
			                                                	</td>
				                                                <!-- <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>  -->
				                                            	 <td>
																	<c:choose>
													    				<c:when test="${functionality.view.equals(true)}">
													        				<input type="checkbox" name="view" checked="checked" value="${role.roleCode}#${functionality.functionalityName}~VIEW">
													    				</c:when>
													    				<c:otherwise>
													       	 				<input type="checkbox" name="view" value="${role.roleCode}#${functionality.functionalityName}~VIEW">
													    				</c:otherwise>
																	</c:choose>									
																</td>
																<td>			
																	<c:choose>
													    				<c:when test="${functionality.insert.equals(true)}">
													        				<input type="checkbox" checked="checked" name="insert" value="${role.roleCode}#${functionality.functionalityName}~INSERT">
													    				</c:when>
													    				<c:otherwise>
													       	 				<input type="checkbox" name="insert" value="${role.roleCode}#${functionality.functionalityName}~INSERT">
													    				</c:otherwise>
																	</c:choose>									
																</td>
																<td>
																	<c:choose>
													    				<c:when test="${functionality.update.equals(true)}">
													        				<input type="checkbox" checked="checked" name="update" value="${role.roleCode}#${functionality.functionalityName}~UPDATE">
													    				</c:when>
													    				<c:otherwise>
													       	 				<input type="checkbox" name="update" value="${role.roleCode}#${functionality.functionalityName}~UPDATE">
													    				</c:otherwise>
																	</c:choose>										
																</td> 
				                                            </tr>
				                                             <!-- <tr> 
				                                                <td><input type="text" class="form-control" name="firstname" placeholder="" value="Student Result Activity Log" disabled></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                                <td><input type="checkbox" id="checkboxExample1"></td>
				                                            </tr>  -->
			                                            </c:forEach>
			                                           </c:forEach>
			                                        </tbody>
	                                    </table>
	                                </div> 
	                                                             
	                                <footer style="display: block;" class="panel-footer">
	                                    <button type="submit" class="btn btn-primary" >Submit </button>
	                                    <button type="reset" class="btn btn-default">Reset</button>
	                                </footer>
	                               
                                
                            </section>
                            </form:form>
						</div> 
						</c:otherwise>
						</c:choose>
						 
					</div>	
		</div>
				

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>