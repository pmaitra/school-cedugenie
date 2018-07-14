<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>I.T Section Deduction Limit</title>
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
	<c:if test="${updateResponse ne null}">	
		<div class="alert alert-success">
			<strong>${updateResponse}</strong>	
		</div>
	</c:if>		
									
			
			
					 <div class="row">
					 	<c:choose>
						<c:when test="${financialYearList == null}">
							<div class="errorbox" id="errorbox" style="visibility:visible;">
								<span id="errormsg">No Financial Year Found</span>	
				 			</div>
						</c:when>	
						<c:otherwise>
						<div class="col-md-6 col-md-offset-3">
						  <form:form name="updateITSectionRebateAmountLimit" action="updateITSectionRebateAmountLimit.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">View / Edit Rebate Amount For IT Section</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-5 control-label">Financial Year</label>
											<div class="col-sm-7">
												<select class="form-control" name="financialYear.financialYearCode" id="finYear" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var= "fin" items="${financialYearList}">									
														<option value="${fin.financialYearCode}">${fin.financialYearName}</option>					
													</c:forEach>
                                                </select>
											</div>
										</div>  
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Income Age</label>
											<div class="col-sm-7">
												<select class="form-control" name="incomeAge.incomeAgeCode" id="incomeAge" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var= "incAge" items="${incomeAgeList}">									
														<option value="${incAge.incomeAgeCode}">${incAge.incomeAgeName}</option>					
													</c:forEach>
                                                </select>
											</div>
										</div>
                                        <div class="form-group">
											<label class="col-sm-5 control-label">IT Section Group</label>
											<div class="col-sm-7">
												<select class="form-control" name="itSectionGroupCode" id="itSecGroup" required>
                                                    <option value="">Select...</option>
                                                </select>
											</div>
										</div>    
									</div>
                                    <hr>
                                    <div style="display: none;" class="panel-body" id="itSecDetailAmntDiv">
                                        
                                        <table class="table table-bordered table-striped mb-none" id="itSecDetailAmnt">
                                             
                                        </table>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" id="submitt" disabled="disabled">Submit </button>
										<button type="button" id="editButton"  value="edit" onclick="return removeDisabled();" class="btn btn-success">Edit</button>
									</footer>
									
									<div class="infomsgbox" id="infomsgbox" >
										<span id="infomsg"></span>	
									</div>		
									<div class="warningbox" id="warningbox" >
										<span id="warningmsg"></span>	
									</div>	
								</section>
                            </form:form>
						</div>
						</c:otherwise>
						</c:choose>
					</div>	
					
					
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	$("#incomeAge").change(function(){	
		removeSelectData();			
		if(($(this).val()) != ''){
			$.ajax({
		        url: '/cedugenie/getITSectionGroupsForIncomeAge.html',
		        dataType: 'json',
		        data: {	'incomeAge.incomeAgeCode': $("#incomeAge").val(),	'financialYear.financialYearCode': $("#finYear").val() },
		        success: function(dataDB) {		        	
		        	if(dataDB != null && dataDB != ""){
		        		var arr = dataDB.split("^^");
						for(var i=0; i<arr.length-1; i++){
							var arr2 = arr[i].split("~");
							$("#itSecGroup").append($("<option></option>").val(arr2[0]).html(arr2[1]));
						}		        		
					}		        	
		        }
			});
		}    	
	});
	
	$("#itSecGroup").change(function(){	
		$('#itSecDetailAmnt').empty();
		if(($(this).val()) != ''){
			
			$.ajax({
		        url: '/cedugenie/checkITSecDetailAmount.html',
		        dataType: 'json',
		        data: {	'status': $("#itSecGroup").val(), 
		        		'incomeAge.incomeAgeCode': $("#incomeAge").val(),	
		        		'financialYear.financialYearCode': $("#finYear").val() },
		        success: function(dataDB) {		        	
		        	if(dataDB != null && dataDB != ""){
		        		if(dataDB == 0){
		        			document.getElementById("itSecDetailAmntDiv").style.display = 'none';	
		        			document.getElementById('infomsgbox').style.display = "block";
		        			document.getElementById('infomsg').innerHTML = "Maximum Amount for Rebates under this IT Section Group is NOT CREATED. Please Set the Amount First.";
		        		}else{
		        			document.getElementById('infomsgbox').style.display = 'none';
		        			$.ajax({
		        		        url: '/cedugenie/getRebateAmountDetailForITGroup.html',
		        		        dataType: 'json',
		        		        data: {	'status': $("#itSecGroup").val(), 
		        		        		'incomeAge.incomeAgeCode': $("#incomeAge").val(),	
		        		        		'financialYear.financialYearCode': $("#finYear").val() },
		        		        success: function(dataDB) {		        	
		        		        	if(dataDB != null && dataDB != ""){	
		        		        		var arr = dataDB.split("###");
		        		        		var p = 0;
		        	        			var sm = "";						
		        	        			sm = sm + "<tr><th colspan='2'>Amount Details of IT Section Group : "+($("#itSecGroup option:selected").text())+"</th></tr>" +
		        	        					"<tr><th>IT Section Group</th><th>Maximum Amount</th></tr>" +
		        	        					"<tr><th>"+($("#itSecGroup option:selected").text())+"</th>" +
		        	        						"<th><input type='text' class='form-control' id='groupAmount' name='groupAmount' readonly='readonly' value='"+arr[0]+"'></th></tr>";
		        		        				        		        		
		        		        		var arr2 = arr[1].split("##");
		        		        		for(var i=0; i<arr2.length-1; i++){							
		        							var arr3 = arr2[i].split("^^");
		        							
		        							//split arr3[0]
		        							var secSplit = arr3[0].split("~");
		        							sm = sm + "<tr><th colspan='2'>Enter Maximum Amount For Sub Section of "+secSplit[1]+"</th></tr>" +
		        										"<tr><th>IT Section</th><th>Maximum Amount</th></tr>" +
		        										"<input type='hidden' name='itSecName' value='"+secSplit[0]+"' >" ;
		        							if(secSplit[2] == 0){								
		        								sm = sm + "<tr><th>"+secSplit[1]+"</th><th>NA</th></tr>";
		        							}else{
		        								sm = sm + "<tr><th>"+secSplit[1]+"</th><th><input type='text' class='form-control' id='secAmount' name='secAmount' readonly='readonly' value='"+secSplit[2]+"'></th></tr>";
		        							}							
//		        							alert("sec --> "+arr3[0]+"  ||  sec Details --> "+arr3[1]);
		        							
		        							if(arr3[1] != ""){
		        								var arr4 = arr3[1].split("::");
		        								sm = sm + "<tr><th>Rebate</th><th>Maximum Amount</th></tr>";
		        								for(var j=0; j<arr4.length-1; j++){
//		        									alert("@@ secDetailName --> "+arr4[j]);
		        									var rebateSplit = arr4[j].split(":");									
		        									sm = sm + "<input type='hidden' name='"+secSplit[0]+"' value='"+rebateSplit[0]+"' >" +
		        											"<tr><td>"+rebateSplit[1]+"</td><td><input type='text' class='form-control textfield2' id='secDetailAmount"+p+"' name='"+rebateSplit[0]+"' value='"+rebateSplit[2]+"' readonly='readonly' required></td></tr>";
		        									p++;
		        								}
		        							}
		        						}
		        		        		$('#itSecDetailAmnt').append(sm);
		        		        		document.getElementById("itSecDetailAmntDiv").style.display = 'block';		        		        		
		        					}
		        		        //	document.getElementById("successbox").style.display = 'none';
		        		        }
		        			});
		        		}
		        	}
		        }
			});
		}
	});
});


function removeSelectData(){
	var x = document.getElementById("itSecGroup");
	x.innerHTML = "";
	var option = document.createElement("option");
	option.text = "Select...";
	option.value = "";
	try  {	  
	  x.add(option,x.options[0]);
	}catch (e)  {
	  x.add(option,0);
	}
}


function removeDisabled(){
	var i;	
	document.getElementById("submitt").removeAttribute("disabled");
	
	var rebateAmountField = document.getElementsByClassName("textfield2");	
	for(i=0; i<rebateAmountField.length; i++){
		rebateAmountField[i].removeAttribute("readonly");
	}
}
</script>
</body>
</html>