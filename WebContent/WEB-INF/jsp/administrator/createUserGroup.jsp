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

<script type="text/javascript">  

$("#resourceTypeName").change(function (){
	if(($("#resourceTypeName").val()!=null)){
		$("#userId").autocomplete({
			source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())   
	 	});
	}
});

$("#resourceTypeName").change(function (){
	if(($("#resourceTypeName").val()!=null)){	
		alert($("#resourceTypeName").val());
		$("#userId0").autocomplete({
			source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val()) 
		});
	}
});

$(document).ready ( function(){
	
	$("#name0").focus(function(){
	    $.ajax({
			url: '/cedugenie/getUserNameForId.html',
			dataType: 'json',
			data: "userId=" + ($("#userId0").val()),
			success: function(data) {
				if(data != null && data!=""){
					($("#name0").val(data));
					//document.getElementById("warningbox").style.visibility='collapse';
				}
				//else{	   
				//	document.getElementById("warningbox").style.visibility='visible';
				//	document.getElementById("warningmsg").innerHTML="User Name Not Found";
// 				}
			}			        
		});
	});

});


function auto(userId,name){	
// 	$(userId).autocomplete({	 
// 		source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())
// 	});	
	$(name).focus(function(){	
		//document.getElementById("warningbox").style.visibility='collapse';
	
		$.ajax({
			url: '/cedugenie/getUserNameForId.html',
			dataType: 'json',
			data: "userId=" + ($(userId).val()),
		    success: function(data) {
		     	if(data != null && data!=""){
		     		
		  			($(name).val(data));
		  			
 		     	}else{	  
 		     		$(name).val("");
// 		     		document.getElementById("warningbox").style.visibility='visible';
// 		    		document.getElementById("warningmsg").innerHTML="User Name Not Found";			        	
 		     	}
		  	}
		});
	});
}



var index=1;
function addrows(){		
	
	//document.getElementById("warningbox").style.visibility='collapse';
	
	var userIds=document.getElementsByName("userName");
	var names=document.getElementsByName("name");
	
	for(var i=0;i<userIds.length;i++){
		var userId=userIds[i].value;
		var name=names[i].value;
		alert("userId=="+userId);
// 		if(userId==""){
// 			document.getElementById("warningbox").style.visibility='visible';
// 			document.getElementById("warningmsg").innerHTML="Please Enter User Id In Row "+(i+1);
// 			return false;
// 		}
// 		if(name==""){
// 			document.getElementById("warningbox").style.visibility='visible';
// 			document.getElementById("warningmsg").innerHTML="Please Enter Contact Name In Row "+(i+1);
// 			return false;
// 		}
	}
	
	
	
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;

	var row = table.insertRow(rowCount);           
		         
	var cell0 = row.insertCell(0);		
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="userName";
	element0.id="userId"+index;
	element0.className="form-control";	
	element0.setAttribute("required","");
	cell0.appendChild(element0);	            
	            
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="name";
	element1.className="form-control";
	element1.id="name"+index;
	//element1.size=25;
	element1.setAttribute("readonly","readonly");
	cell1.appendChild(element1);	        
		        
	var cell2= row.insertCell(2);
	/* var element2 = document.createElement("img");
	element2.setAttribute("src","/cedugenie/images/minus_icon.png");		
	element2.setAttribute("onclick", "deleteRow(this);");		 */
	element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell2.appendChild(element2);		
		
	var userId="#userId"+index;
	var name="#name"+index;
	
	index++;
	auto(userId,name);
	
}
 
function deleteRow(obj){	
	//document.getElementById("warningbox").style.visibility='collapse';
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;
	alert("rowCount=="+rowCount);
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
 	}//else{
// 		document.getElementById("warningbox").style.visibility='visible';
// 		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
// 	}
} 

function showUserGroupDetails(userGroupDetails)
{
	
	$.ajax({
		url: '/cedugenie/getUserGroupDetails.html',
		dataType: 'json',
		data: "userGroupCode=" + userGroupDetails,
	    success: function(data) {
	     	if(data != null && data!=""){
	     		var dataarr = data.toString().split("#");
	     		var row = "<tbody>";
	     		var arr = dataarr[1].toString().split("*");
	     		for(var i=0; i<arr.length; i++){
    				var arr2 = arr[i].toString().split("-");
    				row += "<tr><td>"+arr2[0]+"</td><td>"+arr2[1]+"</td></tr>";    				
    			}
	     		
	     		$("#userGroupDetails").append(row);
	     		
		     	}
	     	$('#modalInfo').fadeIn("fast");
	     //	alert(row);
	     	
	    }
	});
}
</script>
</head>
<body>
					 <div class="row">
						<div class="col-md-12">
						  <form  action="insertUserGroup.html"  method="POST">
						  <c:choose>
							<c:when test="${resourceTypeList eq null || resourceTypeList.size() eq 0}">
								<div class="errorbox" id="errorbox" style="visibility: visible;">
									<span id="errormsg">No Resource Type Found To Create User Group</span>	
								</div>						
							</c:when>
							<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create User Group</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-5">
                                            <div class="form-group">
                                                <label class="control-label">Resource Type <span class="required" aria-required="true">*</span></label>
                                                <select class="form-control" name="resourceTypeCode" id="resourceTypeName" required>
                                                    <option value="">Select...</option>
                                                   		 <c:forEach var="resourceType" items="${resourceTypeList}">
															<option value="${resourceType.resourceTypeCode}">${resourceType.resourceTypeName}</option>
														</c:forEach>	
                                                </select>
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">User Group Name <span class="required" aria-required="true">*</span></label>
                                                <input type="text" class="form-control" name="userGroupName" id="userGroupName" placeholder="" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$"  title="The full name can consist of alphabetical characters and spaces only"required>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">User Group Description <span class="required" aria-required="true">*</span></label>
                                                <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" name="userGroupDesc" id="userGroupDesc" required></textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-7">
                                            <table class="table table-bordered table-striped mb-none" id="userTable">
                                                <thead>
                                                    <tr>
                                                        <th>User Id</th>
                                                        <th>Contact Name</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>											
                                                        <td><input type="text" class="form-control" name="userName" id="userId0" placeholder="" required></td>
                                                        <td><input type="text" class="form-control" id="name0" name="name" placeholder="" required></td>
                                                        <td>
                                                            <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" id="addrow" onclick="addrows();">Add</a>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
							</c:otherwise>
						</c:choose>
                      </form>
					</div>
                         <div class="col-md-12">
						  
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">User Group List</h2>										
									</header>
									<div style="display: block;" class="panel-body">   
                                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                            <thead>
                                                <tr>
                                                    <th>Group Name</th>
                                                    <th>Created On</th>
                                                    <th>Group Description</th>
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach var="userGroup"  items="${pagedListHolder}">
                                            	<input type="hidden" name="userGroupCode" id="userGroupCode" value="${userGroup.userGroupCode}"/>
	                                                <tr>
	                                                	
	                                                    <td>${userGroup.userGroupName}</td>
	                                                    <td>${userGroup.status}</td>
	                                                    <td>${userGroup.userGroupDesc}</td>
	                                                    <td>
 	                                                        <a href="#modalInfo"   class="mb-xs mt-xs mr-xs modal-basic btn btn-info"  onclick = "showUserGroupDetails('${userGroup.userGroupCode}')">Details</a> 
															<button class="mb-xs mt-xs mr-xs modal-basic btn btn-danger" onclick="deleteUserGroupDetails('${userGroup.userGroupCode}')">Delete</button>
	                                                    </td>
	                                                </tr>
	                                              </c:forEach>
                                               
                                            </tbody>
                                        </table>
                                      
                          				<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                                            <section class="panel">
                                                <header class="panel-heading">
                                                    <h2 class="panel-title">User Group Name - Library</h2>
                                                </header>
                                                <div class="panel-body">
                                                    <table class="table table-bordered table-striped mb-none" id = "userGroupDetails">
                                                       <thead>
                                                            <tr>
                                                                <th>Group Name</th>
                                                                <th>User ID</th>
                                                            </tr>
                                                        </thead>
                                                        <!--<tbody>
                                                            <tr>
                                                                <td>AMIT KR VIDYARTHY </td>
                                                                <td>ssp_akv</td>
                                                            </tr>
                                                        </tbody> -->
                                                    </table>
                                                </div>
                                                <footer class="panel-footer">
                                                    <div class="row">
                                                        <div class="col-md-12 text-right">
                                                            <button class="btn btn-info modal-dismiss">OK</button>
                                                        </div>
                                                    </div>
                                                </footer>
                                            </section>
                                        </div>
                                       
                                        </div>
                                       </section>
<%--                                         </form:form> --%>
                                       	 </div>
									</div>
                                

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!--  <script src="/cedugenie/js/administrator/createUserGroup.editable.js"></script> -->
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script> 
<script type="text/javascript">
function deleteUserGroupDetails(url){
	alert("inactiveUserGroupDetail.html?userGroupCode="+url);
	window.location="inactiveUserGroupDetail.html?userGroupCode="+url;
}
</script>
</body>
</html>
