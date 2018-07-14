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
</head>
<body>
	<header class="page-header">
			<h2>Create Categoty</h2>
		</header>

	<div class="content-padding">
	
	<c:if test="${insertStatus eq 'success'}">
				<div class="alert alert-success">
					<strong>${msg}</strong>
				</div>
			</c:if>
			
			<c:if test="${insertStatus eq 'fail'}">
				<div class="alert alert-danger">
					<strong>${msg}</strong>
				</div>
			</c:if>
<div class="row">
 					<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form:form method="POST" id="insertCategory" name="insertCategory" action="insertCategory.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Category</h2>										
									</header>
									<c:forEach var="serviceTypeList" items="${serviceTypeList}">
									<input type="hidden" name="allServiceType" value="${serviceTypeList.ticketServiceName}" />
									</c:forEach>
									<c:forEach var="task" items="${allJobList}">
									<input type="hidden" name="hiddenJobTypeCode" value="${task.jobTypeCode}" />
									</c:forEach>
									<c:forEach var="task" items="${allJobList}">
									<input type="hidden" name="hiddenJobTypeName" value="${task.jobTypeName}" />
									</c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label"> Category Name :<span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="category" id="category" placeholder="" required>
                                        </div>                                       
                                        
									
                                       <div class ="row">
                                         <table class="table table-bordered table-condensed mb-none" id="taskTable">
                                    	<thead style="background: #eee;">
                                    		<tr>
												<th><b>Task</b></th>
												<th>Action</th>									
											</tr>
                                    	</thead>
                                    	<tbody id="taskTableBody">
                                    		<tr>
                                    			<td>
	                                    			 <select class="form-control" name="jobTypeCode" id="jobTypeCode0">
		                                                <option value="">Select...</option>
		                                              
															<c:forEach var="task" items="${allJobList}" >
																<option value="${task.jobTypeCode}">${task.jobTypeName}</option>
															</c:forEach>
														
                                            		</select>
                                    			</td>
                                    			
                                    			
                                    			<td>
                                    				
                                    					
                                    					<div class="col-sm-2"><a class="btn btn-xs btn-primary add-answer" id = "add0" href="javascript:addrows(this)"><i class="glyphicon glyphicon-plus-sign"></i></a></div>
                                    				
                                    			</td>
                                    			
                                    		</tr>                                    		
                                    	</tbody>										
										
									</table> 
									</div>  
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit"  onclick = "return checkServiceName()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">						  
						
                        <section class="panel">
                        	<%-- <c:choose>
								<c:when test="${serviceTypeList eq null || serviceTypeList.size() eq 0}">
									<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
										<span id="infomsg">No Service Type created Yet. Please Create New.</span>	
									</div>						
								</c:when>
							<c:otherwise> --%>
                            <form:form id="editServiceType" name="editServiceType" action="updateServicetype.html" method="post">
							<input type="hidden" id="saveId" name="saveId">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Category</h2>
							</header>
							
							<div class="panel-body">
								
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
                                            
											<th>Category Name</th>
											
                                            
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="category" items="${categoryList}" varStatus="rowCount">
										<tr>											
											<td>
												<input type="hidden" name="oldService${rowCount.index}" value="${serviceTypeList.ticketServiceName}" >
												<input type="text" class="form-control" name="category${rowCount.index}" value="${category.category}" id="category${rowCount.index}" readonly>
											</td>
											
                                           
											<td class="actions">
												<a href = "getTaskListForCategory.html?categoryCode=${category.jobTypeCode}" class="mb-xs mt-xs mr-xs btn btn-info">Details</a>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							</form:form>
							<%-- </c:otherwise>
							</c:choose> --%>
						</section>
						</div>
					</div>	
		</div>	
	</div>		
					<div id="dialog" class="modal-block mfp-hide">
						<section class="panel">
							<header class="panel-heading">
								<h2 class="panel-title">Are you sure?</h2>
							</header>
							<div class="panel-body">
								<div class="modal-wrapper">
									<div class="modal-text">
										<p>Are you sure that you want to delete this row?</p>
									</div>
								</div>
							</div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button id="dialogConfirm" class="btn btn-primary">Confirm</button>
										<button id="dialogCancel" class="btn btn-default">Cancel</button>
									</div>
								</div>
							</footer>
						</section>
					</div>




<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/ticketing/serviceType.js"></script>
<script src="/cedugenie/js/ticketing/servicetype.editable.js"></script>

<script type="text/javascript">

/*var index=0;
var newIndex = 1;*/


var index=1;
var newIndex = 0;
function addrows(){
	//alert("hii");
	var table = document.getElementById("taskTableBody");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
	var jobTypeCodes = document.getElementsByName("hiddenJobTypeCode"); 
	var jobTypeNames = document.getElementsByName("hiddenJobTypeName");  
	
	var jobTypeCode1 = null;
	var jobTypeName1 = null;
	for(var i=0;i<jobTypeCodes.length;i++){
		jobTypeCode1 = jobTypeCode1 + "*" +jobTypeCodes[i].value;
		jobTypeName1 = jobTypeName1 + "*" + jobTypeNames[i].value;
	}
	
	
	var jobCode = jobTypeCode1.split("*");
	var jobName = jobTypeName1.split("*");
	
	var cell0 = row.insertCell(0);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "jobTypeCode" + index;
	element.name = "jobTypeCode";
	element.setAttribute( "required","required");
	//element.setAttribute("onchange","getUserId(this);");	
	element.add(new Option("Select", ""));
	 for(var i = 1; i<jobCode.length;i++){
		element.add(new Option(jobName[i], jobCode[i]));
	} 
	cell0.appendChild(element);            
	            
	      
		        
	var cell1= row.insertCell(1);
	
	var element2= document.createElement('a');	
	 element2.setAttribute("class","fa fa-minus-square");
	element2.setAttribute("onclick", "deleteRow(this);");
	element2.setAttribute("href","#");
	cell1.appendChild(element2);		
		

	index++;
	
}
	function makeEditable(rowId){
		rowId=rowId.replace("edit","");
		document.getElementById("serviceName"+rowId).removeAttribute("readonly");
		document.getElementById("serviceOwner"+rowId).removeAttribute("readonly");
		document.getElementById("department"+rowId).removeAttribute("readonly");
	};
	
	function saveData(rowId){
		rowId=rowId.replace("save","");
		document.getElementById("saveId").value=rowId;
		document.editServiceType.submit();
	};
	
	function deleteRow(obj){
		var table = document.getElementById("taskTableBody");
		var rowCount = table.rows.length;	
		if(rowCount>1){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);
		}
	}
</script>



</body>
</html>