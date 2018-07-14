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
			<h2>Create Category Department Mapping</h2>
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
						  <form:form method="POST" id="insertCategoryDepartmentMapping" name="insertCategoryDepartmentMapping" action="insertCategoryDepartmentMapping.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Category Department Mapping</h2>										
									</header>
									
									<c:forEach var="category" items="${categoryList}">
										<input type="hidden" name="hiddenRecipientCode" value="${category.jobTypeCode}" />
									</c:forEach>
									<c:forEach var="category" items="${categoryList}">
										<input type="hidden" name="hiddenRecipientName" value="${category.category}" />
									</c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label"> Department Name :<span class="required" aria-required="true">*</span></label>
                                           <select class="form-control" name="department" id="department" required>
		                                                <option value="">Select...</option>
		                                              		<c:forEach var="dept" items="${departmentList}" >
																<option value="${dept.departmentCode}">${dept.departmentName}</option>
															</c:forEach>
															<%-- <c:forEach var="category" items="${categoryList}" varStatus="rowCount">
																<option value="${category.jobTypeCode}">${category.category}</option>
															</c:forEach> --%>
														
                                            		</select>
                                           
                                        </div>                                       
                                        
									
                                       <div class ="row">
                                         <table class="table table-bordered table-condensed mb-none" id="departmentTable">
                                    	<thead style="background: #eee;">
                                    		<tr>
												<th><b>Category</b></th>
												<th>Action</th>									
											</tr>
                                    	</thead>
                                    	<tbody id="categoryTableBody">
                                    		<tr>
                                    			<td>
	                                    			 <select class="form-control" name="categoryName" id="categoryName0" required>
		                                                <option value="">Select...</option>
		                                              		<c:forEach var="category" items="${categoryList}" varStatus="rowCount">
																<option value="${category.jobTypeCode}">${category.category}</option>
															</c:forEach>
															<%-- <c:forEach var="dept" items="${departmentList}" >
																<option value="${dept.departmentCode}">${dept.departmentName}</option>
															</c:forEach> --%>
														
                                            		</select>
                                    			</td>
                                    			
                                    			
                                    			<td>
                                    				
                                    					
                                    					<div class="col-sm-2"><a class="btn btn-xs btn-primary add-answer" id = "add0" href="javascript:addrows()"><i class="glyphicon glyphicon-plus-sign"></i></a></div>
                                    				
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
							<input type="hidden" id="saveId" name="saveId">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Department</h2>
							</header>
							
							<div class="panel-body">
								
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
                                            
											<th>Department Name</th>
											
                                            
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="dept" items="${departmentMappedCategoryList}" varStatus="rowCount">
										<tr>											
											<td>
												
												<input type="text" class="form-control" name="department${rowCount.index}" value="${dept.departmentName}" id="department${rowCount.index}" readonly>
											</td>
											
                                           
											<td class="actions">
												<a href = "getCategoryDepartmentMapping.html?departmentCode=${dept.departmentCode}" class="mb-xs mt-xs mr-xs btn btn-info">Details</a>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						
							
						</section>
						</div>
					</div>	
		</div>	
	</div>		
			



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

<script src="/cedugenie/js/ticketing/servicetype.editable.js"></script>

<script type="text/javascript">

/*var index=0;
var newIndex = 1;*/


var index=1;
var newIndex = 0;
function addrows(){
	
	alert("hiii");
	var table = document.getElementById("categoryTableBody");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
	var hiddenRecipientCodes = document.getElementsByName("hiddenRecipientCode"); 
	var hiddenRecipientNames = document.getElementsByName("hiddenRecipientName");  
	
	var recipientCode1 = null;
	var recipientName1 = null;
	for(var i=0;i<hiddenRecipientCodes.length;i++){
		recipientCode1 = recipientCode1 + "*" +hiddenRecipientCodes[i].value;
		recipientName1 = recipientName1 + "*" + hiddenRecipientNames[i].value;
	}
	
	
	var recipientCode = recipientCode1.split("*");
	var recipientName = recipientName1.split("*");
	
	var cell0 = row.insertCell(0);
	element = document.createElement("select");
	element.className = "form-control";
	element.id = "categoryName" + index;
	element.name = "categoryName";
	element.setAttribute( "required","required");
	//element.setAttribute("onchange","getUserId(this);");	
	element.add(new Option("Select", ""));
	 for(var i = 1; i<recipientCode.length;i++){
		element.add(new Option(recipientName[i], recipientCode[i]));
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

	
	function deleteRow(obj){
		var table = document.getElementById("categoryTableBody");
		var rowCount = table.rows.length;	
		if(rowCount>1){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);
		}
	}
</script>



</body>
</html>