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
			<h2>Edit Category Recipient Mapping</h2>
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
						  <form:form method="POST" id="editCategoryRecipientMapping" name="editCategoryRecipientMapping" action="editCategoryRecipientMapping.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Category Recipient Mapping</h2>										
									</header>
									
									<c:forEach var="task" items="${recipientList}">
										<input type="hidden" name="hiddenRecipientCode" value="${task.approverGroupCode}" />
									</c:forEach>
									<c:forEach var="task" items="${recipientList}">
										<input type="hidden" name="hiddenRecipientName" value="${task.approverGroupName}" />
									</c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label"> Category Name :<span class="required" aria-required="true">*</span></label>
                                          	  <input type="text" class="form-control" name="category" id="category" value = "${category.category}" placeholder="" readonly>
                                            	<input type="hidden" class="form-control" name="jobTypeObjectId" id="jobTypeObjectId" value = "${category.jobTypeCode}" placeholder="" >
                                         
                                        </div>                                       
                                        
									
                                       <div class ="row">
                                         <table class="table table-bordered table-condensed mb-none" id="recipientTable">
                                    	<thead style="background: #eee;">
                                    		<tr>
												<th><b>Recipient Group</b></th>
												<th><div class="col-sm-2"><a class="btn btn-xs btn-primary add-answer" id = "add0" href="javascript:addrows(this)"><i class="glyphicon glyphicon-plus-sign"></i></a></div></th>									
											</tr>
                                    	</thead>
                                    	<tbody id="recipientTableBody">
                                    	<c:forEach var="recipientGroup" items="${category.approverGroupList}" >
                                    		<tr>
                                    			<td>
                                    				
		                                    			 <select class="form-control" name="jobTypeCode" id="jobTypeCode0">
			                                                <option value="">Select...</option>
			                                              
																<c:forEach var="recipient" items="${recipientList}" >
																	<option value="${recipient.approverGroupCode}" ${recipient.approverGroupCode eq recipientGroup.approverGroupCode?'selected':value} >${recipient.approverGroupName}</option>
																</c:forEach>
																
																
	                                            		</select>
                                            		
                                    			</td>
                                    			
                                    			
                                    			<td>
                                    				
                                    					
                                    					<a href = "#" class = "fa fa-minus-square" onclick = "deleteRow(this);" ></a>
                                    				
                                    			</td>
                                    			
                                    		</tr>  
                                    		</c:forEach>                                  		
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
	
	var table = document.getElementById("recipientTableBody");
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
	element.id = "jobTypeCode" + index;
	element.name = "jobTypeCode";
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
		var table = document.getElementById("recipientTableBody");
		var rowCount = table.rows.length;	
		if(rowCount>1){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);
		}
	}
</script>



</body>
</html>