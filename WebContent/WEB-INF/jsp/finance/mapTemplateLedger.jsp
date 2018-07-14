<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Ledger List</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
				<h2>Template Ledger Mapping</h2>
	</header>
<div class="content-padding">
	<c:if test="${submitResponse eq 'success'}">
			<div class="alert alert-success" >
				<strong>SuccessFully Mapped</strong>	
			</div>
		</c:if>
	<c:if test="${submitResponse eq 'fail'}">
			<div class="alert alert-success" >
				<strong>Failed To Map</strong>	
			</div>
		</c:if>
<c:choose>
	<c:when test="${templateDetailsList == null}">
		<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
			<span id="infomsg">Template List Not Found</span>
		</div>
	</c:when>
<c:otherwise>
			<c:if test="${ledgerList eq null && type eq 'MAP'}">
			<div class="btnsarea01" >
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span>Ledger List Not Found</span>	
				</div>
			</div>
		</c:if>
		<form:form action="mapLedgerTemplate.html" method="POST" >
			<section role="main" class="content-body">		
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
						</div>						
						<h2 class="panel-title">Template :: ${templateDetailsList.get(0).salaryTemplateName}</h2>
					</header>
					<div style="display: block;"  class="panel-body">
						<div class="row">
                      				<c:forEach var="ledger" items="${ledgerList}">
                      				</c:forEach>
                                   <div class="col-md-4 col-md-offset-4">
                                      <div class="form-group">
                                          <label class="control-label">Resource Type <span aria-required="true" class="required">*</span></label>                                                
                                          <select class="form-control" name="resourceTypeName" id="resourceTypeName" required>
                                              <option value="">Select...</option>
												<c:forEach var="resource" items="${resourceTypes}" >
													<option value="${resource.resourceTypeCode}">${resource.resourceTypeName}</option>
												</c:forEach>
                                          </select>
                                          <input type="hidden" name="tempDetCode" id = "tempDetCode" value="${templateDetails.salaryTemplateDetailsCode}">
                                      </div>
                                  </div>
                         </div>
                         <hr>
                         <div class="alert alert-danger" id="javascriptmsg2" style="display: none">
							 <span></span>	
						</div>
                         <div class = "row">
                         	<div class="col-md-12">
                         
							<table class="table table-bordered table-striped mb-none" id="datatable-editable" >
								<thead>
									<tr>
										<th>Select</th>
										<th>Name</th>
										<th>Ledger</th>
									</tr>
								</thead>
								<tbody id = "mapTemplateLedger">
											
								</tbody>							
							</table>
						</div>
						</div>
					</div>	
					<%-- <c:if test="${type eq 'MAP'}"> --%>
						<input type="hidden" id="templateCode" name="templateCode" value="${templateCode}">	
						
						<div id="buttonSection" style="display: none;">
							<footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validate();">Submit </button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>			
						</div>					
						
				<%-- </c:if>	 --%>			
				</section>			
			</section>
		</form:form>
			
	</c:otherwise>
</c:choose>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

<script type=text/javascript>
$("#resourceTypeName").change(function(){
	 var table = document.getElementById("mapTemplateLedger");
	//alert("hii");
	$.ajax({
	    url: '/icam/getResourceLedgerDetails.html',
	    dataType: 'json',
	    data:"resourceTypeName=" + $("#resourceTypeName").val()+ "&tempDetCode=" + ($("#templateCode").val()),
	    success: function(dataDB) {
	    	//alert("LN122 :: Data DB :: "+dataDB);
	    	 $('#datatable-editable > tbody').empty();
	    	if(null!=dataDB && dataDB!=""){
		        dataDB=dataDB.split(";");
		        var userDetails = dataDB[0].split("~");
		        var ledgerDetails = dataDB[1].split("~");
		       	//alert("LN128 :: length=="+userDetails.length);
		       	
		       	/* added by sourav.bhadra on 05-10-2017 */
		       	if(userDetails.length <= 1){
		       		document.getElementById("buttonSection").style.display = "none";
		       	}else{
		       		document.getElementById("buttonSection").style.display = "block";
		       	}
		       	/**/
		        for(var i=0;i<userDetails.length-1;i++){
	        		var data=userDetails[i].split("*");
	                var rowCount = table.rows.length;
	              //  alert("rowCount===="+rowCount);
	                var row = table.insertRow(rowCount);
	                
	                var cell,element;
	                
	                cell = row.insertCell(0);		
	                element = document.createElement("input");
	                element.type = "checkbox";
	                element.name="user";
	                element.value=data[0];
	                if(data[2]=='check'){
	                	element.setAttribute("checked","checked");
	                	element.setAttribute("disabled","disabled");
	                }
	                cell.appendChild(element);
	                
	                cell = row.insertCell(1);		
	                element = document.createTextNode(data[1]);
	                cell.appendChild(element);
	                
	                cell = row.insertCell(2);	
	                var selectList = document.createElement("select");
	                selectList.setAttribute("id","ledger"+i);
	                selectList.setAttribute("name",data[0]+"ledger");
	                //selectList.setAttribute("name","ledger");
	                
	               
	                selectList.add(new Option("Please Select", ""));
	        		
	              	 for(var j=0;j<ledgerDetails.length-1;j++){
               			  var dataLedger = ledgerDetails[j].split("*");
			        		var option = document.createElement("option");
			        		option.setAttribute("value",dataLedger[0]);
			        		option.text = dataLedger[1];
			        		/* if(dataLedger[0]==data[3]){
			        			option.setAttribute("selected","selected");
			        			option.setAttribute("disabled","disabled");
			        		} */
					       selectList.appendChild(option); 
	                }
	              	 
				    cell.appendChild(selectList);
	               
	                var selectObject = document.getElementById("ledger"+i);
	              	 if(data[2] != "" || data[2] != null){
	              		 for(var k=0;k<selectObject.options.length;k++){
					    	 if(selectObject.options[k].value == data[3]){
					    		 
					    		 selectObject.options[k].selected = true;
					    		 selectObject.setAttribute("disabled","disabled");
					    		 break;
					    	 }
					     }
	              		
	              	 }
		        }
	    	}
	    }
	});
});

/* added by sourav.bhadra on 05-10-2017 */
 function validate(){
	var count = 0;
	var status = true;
	var table = document.getElementsByName("user");
	for(var i=0; i<table.length;i++){
		if(table[i].checked)
			count = count+1;
	}
	if(count<=0){
		document.getElementById("javascriptmsg2").style.display='block';
		document.getElementById("javascriptmsg2").innerHTML="No Employee Selected.";
		status = false;
	}
	return status;
}
</script>
</body>
</html>