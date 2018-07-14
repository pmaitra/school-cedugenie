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
<title>category Key Mapping</title>
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
			<h2>Edit Category Key Mapping</h2>
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
						  <form:form method="POST" id="editCategoryKeyMapping" name="editCategoryKeyMapping" action="editCategoryKeyMapping.html" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Category Key Mapping</h2>										
									</header>
									
									<c:forEach var="key" items="${allKeyList}">
										<input type="hidden" name="hiddenRecipientCode" value="${key.jobTypeCode}" />
									</c:forEach>
									<c:forEach var="key" items="${allKeyList}">
										<input type="hidden" name="hiddenRecipientName" value="${key.jobTypeName}" />
									</c:forEach>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
                                            <label class="control-label"> Category Name :<span class="required" aria-required="true">*</span></label>
                                          	  <input type="text" class="form-control" value = "${category.ticketDesc}" placeholder="" disabled>
                                            	<input type="hidden" class="form-control" name="jobTypeObjectId" id="jobTypeObjectId" value = "${category.ticketCode}" placeholder="" >
                                         		<input type="hidden" class="form-control" id = "indexId" name = "indexId" value = "${count}">
                                        </div>                                       
                                        
									
                                       <div class ="row">
                                         <table class="table table-bordered table-condensed mb-none" id="keyTable">
                                    	<thead style="background: #eee;">
                                    		<tr>
												<th><b>Key</b></th>
												<th><div class="col-sm-2"><a class="btn btn-xs btn-primary add-answer" id = "add0" href="javascript:addrows(this)"><i class="glyphicon glyphicon-plus-sign"></i></a></div></th>									
											</tr>
                                    	</thead>
                                    	<tbody id="keyCategoryTableBody">
                                    	<c:forEach var="key" items="${category.commentList}" varStatus="i">
                                    		<tr>
                                    			<td>
                                    				<input type="text" class="form-control" value = "${key.ticketCommentDesc}" placeholder="" readonly>
                                    				<input type="hidden" class="form-control" name="jobTypeCode" id="jobTypeCode${i.index}" value = "${key.ticketCommentDesc}" placeholder="" readonly>
		                                    		
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
$(document).ready(function() {
	 index = document.getElementById("indexId").value;
})
function addrows(){
	
	var table = document.getElementById("keyCategoryTableBody");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount);           
	
	

	alert("index=="+index);
	var cell0 = row.insertCell(0);
	element = document.createElement("input");
	element.className = "form-control";
	element.id = "jobTypeCode" + index;
	element.name = "jobTypeCode";
	element.setAttribute( "required","required");
	//element.setAttribute("onchange","getUserId(this);");	
	/* element.add(new Option("Select", ""));
	 for(var i = 1; i<recipientCode.length;i++){
		element.add(new Option(recipientName[i], recipientCode[i]));
	}  */
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
		var table = document.getElementById("keyCategoryTableBody");
		var rowCount = table.rows.length;	
		if(rowCount>0){
			var p = obj.parentNode.parentNode;
			p.parentNode.removeChild(p);
		}
	}
</script>



</body>
</html>