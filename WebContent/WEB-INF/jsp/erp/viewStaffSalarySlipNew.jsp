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
<title>Salary Break Up</title>
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
			<h2>Disburse Salary</h2>
		</header>
		<c:if test="${status eq 'success'}">
			<div class="alert alert-success"  >
				<strong>Salary disbursed  Successfully</strong>	
			</div>
		</c:if>
		<c:if test="${status eq 'fail'}">
			<div class="alert alert-danger" >
				<strong>Failed To disburse salary</strong>	
			</div>
		</c:if>
		<c:if test="${status eq 'disbursed'}">
			<div class="alert alert-danger" >
				<strong>Salary disbursed for this month</strong>	
			</div>
		</c:if>
	<div class="content-padding">
	<div class="row">
		
		<div class="col-md-6 col-md-offset-3">
		  	<c:set var="earningTotalAmount" value="0" scope="page" />
			<c:set var="deductionTotalAmount" value="0" scope="page" />
			<c:set var="extraTotalAmount" value="0" scope="page" />
			<c:if test="${staffForViewStaffSalaryDetails.resource.userId eq null}">
				<form:form method="POST" id="saveStaffSalaryDetails" name="saveStaffSalaryDetails" action="saveStaffSalaryDetailsNew.html">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
	
							<h2 class="panel-title">Employee's Salary Slip</h2>										
						</header>
						<div style="display: block;" class="panel-body">   
							<div class = "row">  
							<div class = "col-md-12">                                
	                          	<div class="form-group">
	                                <label class="control-label">Resource Type <span aria-required="true" class="required">*</span></label>                                                
		                                  <select class="form-control" name="resourceTypeName" id="resourceTypeName" required>
		                                      <option value="">Select...</option>
												<c:forEach var="resource" items="${resourceTypes}" >
													<option value="${resource.resourceTypeCode}">${resource.resourceTypeName}</option>
												</c:forEach>
		                                  </select>
	                                 
	                              </div>									
		                       		<div class="form-group">
		                              	<label class="control-label">Select Year</label>                     			
										<select id="year" name="year" class="form-control" required >
											<option value="">select year</option> 
											<c:if test="${year ne null}">
												<c:forEach var="year" items="${year}">
													<option>
													${year.academicYearName}
													</option>
												</c:forEach>
											</c:if>
										</select>                                           
		                          	</div>  
			                          <div class="form-group">
			                          		<label class="control-label">Salary Month</label>
			                          		<select id="month" name="month" class="form-control" required >				
											<option value="">select Month</option> 
											<option value="01">January</option>
											<option value="02">February</option>
											<option value="03">March</option>
											<option value="04">April</option>
											<option value="05">May</option>
											<option value="06">June</option>
											<option value="07">July</option>
											<option value="08">August</option>
											<option value="09">September</option>
											<option value="10">October</option >
											<option value="11">November</option>
											<option value="12">December</option>
										</select>                     	                                            
									</div>
								</div>
								</div>
								 <hr>
		                         <div class = "row">
		                         	<div class="col-md-12">
		                         
										<table class="table table-bordered table-striped mb-none" id="datatable-editable" >
											<thead>
												<tr>
													<th>Select</th>
													<th>userId</th>
													<th>Name</th>
													<th>Payment Status</th>
													<th>Payment</th>
												</tr>
											</thead>
											<tbody id = "disburseStaffSalary">
														
											</tbody>							
										</table>
									</div>
								</div>
						</div>
						
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit" id="submitButton">Submit </button>
							<button type="reset" class="btn btn-default">Reset</button>											
						</footer>					
					</section>
                </form:form>
                </c:if>               
			</div>					
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/erp/viewStaffSalarySlip.js"></script>
<script type=text/javascript>
$("#resourceTypeName").change(function(){
	 var table = document.getElementById("disburseStaffSalary");
	//alert("hii");
	$.ajax({
	    url: '/cedugenie/getResourceDetailsForSalary.html',
	    dataType: 'json',
	    data:"resourceTypeName=" + $("#resourceTypeName").val(), /* + "&year=" + $("#year").val() + "&month="+ $("#month").val(), */
	    success: function(dataDB) {
	    	// alert(dataDB); 
	    	 $('#datatable-editable > tbody').empty();
	    	if(null!=dataDB && dataDB!=""){
		        dataDB=dataDB.split(";");
		        for(var i=0;i<dataDB.length-1;i++){
		        	var dataDB1 = dataDB[i].split("#");
	        		var data = dataDB1[0].split("*");
	                var rowCount = table.rows.length;
	              //  alert("rowCount===="+rowCount);
	                var row = table.insertRow(rowCount);
	                
	                var cell,element;
	                
	                cell = row.insertCell(0);		
	                element = document.createElement("input");
	                element.type = "checkbox";
	                element.name="userId";
	                element.setAttribute("class","checkboxClass")
	                element.id="userId_"+data[0];
	                element.value=data[0];
	                cell.appendChild(element);
	                
	                cell = row.insertCell(1);		
	                element = document.createTextNode(data[0]);
	                var element1 = document.createElement("input");
	                element1.type = "hidden";
	                element1.id="templateCode_"+data[0];
	                element1.name="templateCode_"+data[0];
	                element1.value = data[2];
	                cell.appendChild(element);
	                cell.appendChild(element1);
	                
	                cell = row.insertCell(2);		
	                element = document.createTextNode(data[1]);
	                cell.appendChild(element);
	                
	                cell = row.insertCell(3);
	                var paymentSpan = document.createElement('span');
	                paymentSpan.id="payment_"+data[0];
	                paymentSpan.name="payment";
	                paymentSpan.setAttribute("class","payment")
	                paymentSpan.innerHTML = "Pending";
	              
	                cell.appendChild(paymentSpan);
	              
	                cell = row.insertCell(4);
	                
	                if(null != dataDB1[1] && dataDB1[1] != ""){
	                	
	                	var dataDB2 = dataDB1[1].split("@");
	                	
	                	if(null != dataDB2 && dataDB2 != ""){
	                		  for(var i=0;i<dataDB2.length-1;i++){
	                			  	var breakup = dataDB2[i].split("*");
	                			  	element = document.createTextNode(breakup[1]);
		      	 	                var element1 = document.createElement("input");
		      	 	                element1.type = "hidden";
		      	 	                element1.id="breakUpCode_"+breakup[0];
		      	 	                element1.name="breakUpCode_"+data[0];
		      	 	                element1.value = breakup[0];
		      	 	                
		      	 	               	var element2 = document.createElement("input");
		      	 	                element2.type = "text";
		      	 	                element2.id= "value_"+breakup[0];
		      	 	                element2.name="value_"+breakup[0];
		      	 	                
			      	 	             var element3 = document.createElement("input");
				      	 	         element3.type = "hidden";
				      	 	       	 element3.id="breakUpName_"+breakup[0];
				      	 	   		 element3.name="breakUpName_"+breakup[0];
				      	 	 		 element3.value = breakup[1];
		      	 	                
		      	 	                cell.appendChild(element);
		      	 	                cell.appendChild(element1);
		      	 	               	cell.appendChild(element2);
		      	 	             	cell.appendChild(element3);
	                		  }
	                		
	                	}
	                	
	                	
	                			
	 	               
	                }else{
	                	var element4 = document.createTextNode("NA");
	                	cell.appendChild(element4);
	                }
	                
		        }
	    	}
		       
	    }
	});
});

$("#month").change(function(){
	$.ajax({
	    url: '/cedugenie/getPaymentStatusForEmployeeForAYearAndMonth.html',
	    dataType: 'json',
	    data:"resourceTypeName=" + $("#resourceTypeName").val() + "&year=" + $("#year").val() + "&month="+ $("#month").val(),
	    success: function(dataDB) {
	    	
	    	$('.checkboxClass').removeAttr("disabled");
	    	var payments = document.getElementsByClassName("payment");

	    	for(var i = 0; i < payments.length; i++) {
	    	  
	    	  var payment = payments[i];
	    	  
	    	  payment.innerHTML = "Pending";
	    	}
	    	if(dataDB != null){
	    		var data = dataDB.split("*");
		    	for(var i = 0 ; i<data.length-1;i++ ){
		    		document.getElementById("payment_"+data[i]).innerHTML = 'Paid';
		    		document.getElementById("userId_"+data[i]).setAttribute('disabled','disabled');
		    	}
	    	}
	    	
	    }
	});
});

$("#year").change(function(){
	document.getElementById("month").selectedIndex = 0;
});

</script>
</body>
</html>