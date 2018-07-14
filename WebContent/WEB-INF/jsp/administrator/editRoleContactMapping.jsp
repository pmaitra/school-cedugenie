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
<script type="text/javascript" src="/cedugenie/js/common/radio.js"></script>

<script type="text/javascript">    
$(document).ready ( function(){      
	$("#userId0").change(function(){		
		$(this).focusout(function(){
			//document.getElementById("warningbox").style.visibility='collapse';
			$.ajax({
		        url: '/cedugenie/getUserNameForId.html',
		        dataType: 'json',
		        data: "userId=" + ($(this).val()),
		        success: function(data) {
		        	if(data != null && data!=""){
		        		($("#name0").val(data));		        		
			     	}else{	
			     		($("#name0").val(""));			     					        	
			     	}
     			}			        
			});
    	});
 	 });
});




var index=1;
function addrows(){	
	//document.getElementById("warningbox").style.visibility='collapse';
	//alert("within");
	var userIds=document.getElementsByName("userID");
	var names=document.getElementsByName("name");
	
	for(var i=0;i<userIds.length;i++){
		var userId=userIds[i].value;
		var name=names[i].value;
		
	/* 	if(userId==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter User Id In Row "+(i+1);
			return false;
		}
		if(name==""){
			document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningmsg").innerHTML="Please Enter Contact Name In Row "+(i+1);
			return false;
		} */
	}
	
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
		         
	var cell0 = row.insertCell(0);		
	var element0 = document.createElement("input");
	element0.type = "text";
	element0.name="userID";
	element0.id="userId"+index;
	element0.className="form-control";	
	cell0.appendChild(element0);	            
	            
	var cell1 = row.insertCell(1);		
	var element1 = document.createElement("input");
	element1.type = "text";
	element1.name="name";
	element1.className="form-control";
	element1.id="name"+index;
	element1.size=25;
	element1.setAttribute("readonly","readonly");
	cell1.appendChild(element1);	        
		        
	var cell2= row.insertCell(2);
	var element2 = document.createElement("img");
	//element2.className = "fa fa-trash-o";
	//element2.id = "deleterow";
	element2.setAttribute("src","/cedugenie/images/minus_icon.png");		
	element2.setAttribute("onclick", "deleteRow(this);");		
	cell2.appendChild(element2);		
		
	var userId="#userId"+index;
	var name="#name"+index;
	auto(userId,name);
	index++;
	
}
function deleteRow(obj){	
//	document.getElementById("warningbox").style.visibility='collapse';
	var table = document.getElementById("userTable");
	var rowCount = table.rows.length;	
	//alert("rowCount=="+rowCount);
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
 	}
	//else{
// 		document.getElementById("warningbox").style.visibility='visible';
// 		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
// 	}
} 

$(".userId").each(function(){		
	$(this).autocomplete({	 
		source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())
});
});
function auto(userId,name){
	$(".userId").each(function(){		
		$(this).autocomplete({	 
			source: '/cedugenie/getUserIdForResourceType.html?resourceType='+($("#resourceTypeName").val())
	});	
	});	
	$(userId).change(function(){		
		$(this).focusout(function(){
		    $.ajax({
		        url: '/cedugenie/getUserNameForId.html',
		        dataType: 'json',
		        data: "userId=" + ($(this).val()),
		        success: function(data) {
		        	if(data != null && data!=""){
			    		($(name).val(data));
			    		document.getElementById("warningbox").style.visibility='collapse';
			     	}else{	        
			     		$(name).val("");			     					        	
			     	}
        		}
			
			});
    	});
 	 });
}
</script>
</head>
<body>
<div class="row">
						<div class="col-md-12">
						<form method="POST" action="editRoleContactMapping.html">
							<c:choose>
								<c:when test="${resourceList eq null || resourceList.size() eq 0 || roleName eq null}">
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Role Contact Mapping Found To Edit</span>	
									</div>						
								</c:when>
							<c:otherwise>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Role Contact Mapping</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        <div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Role Name</label>
                                                    <input type="text" id="roleName" readonly="readonly" value="${roleName}" class="form-control" ">
													<input type="hidden"  name="roleName" value="${code}" />
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Resource Type</label>
                                                   <input type="text" readonly="readonly" id="resourceTypeName" value="${resourceTypeName}" class="form-control">	
													<input type="hidden"  name="resourceTypeName" value="${desc}" />
                                                </div> 
                                            </div>
                                            <div class="col-md-8">
                                                <table class="table table-bordered table-striped mb-none" id="userTable">
                                                    <thead>
                                                        <tr>
                                                            <th>User Id</th>
                                                            <th>Contact Name</th>
                                                            <th>Actions</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    	<c:forEach var="resource" items="${resourceList}">
	                                                        <tr>
	                                                            <td><input type="text" class="form-control" name="userID" id="userId0" placeholder="" value="${resource.userId}" readonly></td>
<!-- 	                                                            <td><input type="text" class="form-control" name="firstname" placeholder="" value="PRADIP  THAKUR"></td> -->
	                                                           	<td>
	                                                           		<c:choose>
													    				<c:when test="${resource.middleName.equals('null')}">
													        				<input type="text" readonly="readonly" id="name0" name="name" size="25" class="form-control" value="${resource.firstName} ${resource.lastName}">
													    				</c:when>
													    				<c:otherwise>
													       	 				<input type="text" readonly="readonly" id="name0" name="name" size="25" class="form-control" value="${resource.firstName} ${resource.middleName} ${resource.lastName}">
													    				</c:otherwise>
																	</c:choose>		
	                                                           	</td>
	                                                            <td><a class="on-default remove-row" id = "deleterow" href="inActiveRoleContactMapping.html?roleContact=+${resource.userId}&roleName=${code} "><i class="fa fa-trash-o"></i></a></td>
	                                                        </tr>
                                                        </c:forEach>
                                                    </tbody>                                                    
                                                </table>
                                                <a class="mb-xs mt-xs mr-xs modal-basic btn btn-info pull-right" href="#modalInfo" id="addrow" onclick="addrows();">Add</a>
                                            </div>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
								</c:otherwise>
								</c:choose>
                            </form>
						</div>
					</div>	
</body>
</html>