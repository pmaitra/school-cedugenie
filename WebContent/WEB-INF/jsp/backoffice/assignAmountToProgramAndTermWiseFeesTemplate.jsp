<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Assign Fees To Template</title>
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

		
		<div class="row">
			<form name="form1" action="submitAmountForProgramAndTermWiseFeesTemplate.html" method="POST">
				<div class="col-md-4">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Course And Term</h2>										
						</header>
						<div style="display: block;" class="panel-body">
							<div class="form-group">
                                 <label class="control-label">Template Name:</label>
                                 <select class="form-control" id ="templateName" name="templateName">
                                     <option value="">Select</option>
	                                     <c:forEach var="templates" items="${allFeesTemplates}">
											<option value="${templates.studentFeesTemplateCode}">${templates.studentFeesTemplateName}</option>
										 </c:forEach>
                                 </select>
                             </div> 
                            <div class="form-group">
                                 <label class="control-label">Course Name:<span class="required" aria-required="true">*</span></label>
                                 <select class="form-control" id ="programName" name="programName">
                                     <option value="">Select</option>
	                                    <c:forEach var="program" items="${programList}" varStatus="i">
											<option value="${program.courseCode}">${program.courseName}</option>
										</c:forEach>
                                 </select>
                             </div> 
                             <div class="form-group">
                                 <label class="control-label">Term Name:<span class="required" aria-required="true">*</span></label>
                                  <select class="form-control" id="term" name="term" required>
                                  	<option value="">Select</option>
                                  </select>
                                 <input type="hidden" name = "socialCategorySize" id = "socialCategorySize" value = "${socialCategoryList.size()}">
                             </div> 
                         </div>
					</section>
	            </div>
					<div class="col-md-8" style="display: none;" id="assignAmountTableDiv">
                        <section class="panel">
                             <header class="panel-heading">
                                 <div class="panel-actions">
                                     <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                 </div>

                                 <h2 class="panel-title">Assign Amount</h2>
                             </header>
                             <div class="panel-body">

                                 <table class="table table-bordered table-striped mb-none" id="amountTable">
                                     <thead>
                                         <tr>
                                         	<th></th>
                                             <c:forEach var= "socialCategory" items="${socialCategoryList}">
												<th>
													${socialCategory.socialCategoryName}
													<input type="hidden" name = "socialCategoryName" id = "socialCategoryName" value = "${socialCategory.socialCategoryCode}">
												</th>
											</c:forEach>
	                                     </tr>
                                     </thead>
                                     <tbody id="amountTableBody">   
                                     	<tr style="background:#eee">
											<th>Total</th>
											<c:forEach var= "socialCategory" items="${socialCategoryList}">
												<th>
													<input type="text" id="${socialCategory.socialCategoryCode}Total" value = "0.00" readonly="readonly" class="textfield2 form-control text-right">
												</th>
											</c:forEach>
										</tr>
                                     </tbody>
                                 </table>
                             </div>
                             <footer style="display: block;" class="panel-footer">
                                 <button class="btn btn-primary" type="submit" id="submit" name="submit">Submit </button>
                                 <button type="reset" class="btn btn-default">Reset</button>
                             </footer>
                             <input type="hidden" id="status" name="status" value="INSERT">
                         </section>
					</div>
                </form>
			</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready( function(){
	$("#programName").change(function (){
		$.ajax({
		    url: '/cedugenie/getTermNameForFeesTemplate.html',
		    dataType: 'json',
		    data:"programName=" +  $("#programName").val(),
		    success: function(data) {
		    	var options='<option value="">Select</option>';
		    	if(data!=null && data != ""){	        		
	    			var termArr1 = data.split("*~*");
	    			for(var a=0; a<termArr1.length;a++){
	    				if(termArr1[a] != null && termArr1[a] != ""){
   							var termNameAndCode=termArr1[a].split("#@#");  
							options=options+'<option value="'+termNameAndCode[0]+'">'+termNameAndCode[1]+'</option>';
						}     				
	    			}	        				
		    	}
		    	document.getElementById("term").innerHTML=options;
		    }
		}); 
	});
	
	$("#templateName").change(function(){    	
    	$.ajax({
	        url: '/cedugenie/getTemplateWiseFeesStructure.html',
	        dataType: 'json',
	        data: "templateCode=" + ($(this).val()),
	        success: function(dataDB) {
	        	if(dataDB != null){
	        		var arr = dataDB.split("~");
					for (var i=0; i<arr.length - 1; i++){
						var feesHead = arr[i].split(":");
	                    var table = document.getElementById("amountTable");
		        	    var rowCount = table.rows.length;
		        	    var row = table.insertRow(rowCount-1);
		        	    
		        	    var cell1 = row.insertCell(0);
		        	    var element1 = document.createTextNode(feesHead[1]);
		        	    cell1.appendChild(element1);
		        	    
		        	    var element2 = document.createElement("input");
		        	    element2.type = "hidden";
		        	    element2.value = feesHead[1];
		        	    element2.name = "feesHead";//feesHead[1];
		        	    element2.innerHTML = feesHead[1];
		        	    cell1.appendChild(element2);

	        	    	var cnt = 0;
		        	    var scNames = document.getElementsByName("socialCategoryName");
		        	    countScRows = document.getElementById("socialCategorySize").value;
		        	    for(var j = 1 ; j <= countScRows ; j++){
		        	    	var cell = row.insertCell(j);		
		        	        var element = document.createElement("input");
		        	        element.type = "text";		        	        	
		        	        element.value = "0.00";
		        	        element.setAttribute("style","text-align: right;");
		        	        element.setAttribute("onfocus","removeZero(this);");
		        	        var totBx = scNames[cnt].value+"Total";
		        	        element.setAttribute("onblur","setZero(this,'"+totBx+"');");
		        	        element.name=feesHead[1]+"##"+scNames[cnt].value;
		        	        cnt++;
		        	        element.className ="textField form-control";
		        	        cell.appendChild(element);
		        	    }
					}
				}
	        }
		});
    });
	
	$('#term').change(function(){
		$('#assignAmountTableDiv').css("display","block");
	});
	
});
function removeZero(tBox){
	if(tBox.value=="0.00"){
		tBox.value="";
	}
}

function setZero(tBox,totBx){
	if(tBox.value==""){
		tBox.value="0.00";
	}		
	if(isNaN(tBox.value)){
		alert("Please enter a valid value(Numeric)");
		tBox.value="0.00";
	}else{
		var p=parseFloat(tBox.value);
		if(p<0.0){
			alert("Please enter a no greater than zero");
			tBox.value="0.00";
		}
	}
	calculateTotal(totBx);
}

function calculateTotal(totBx){
	document.getElementById(totBx).value="0.00";
	var tb = document.getElementsByClassName("textField");
	for(var i=0;i<tb.length;i++){
		var tName=tb[i].name.split("##");
		tName=tName[1];
		var checker=totBx.replace("Total","");
		if(tName==checker){
			var tot=parseFloat(document.getElementById(totBx).value);
			tot=tot+parseFloat(tb[i].value);
			document.getElementById(totBx).value=tot;
		}
	}	
}
</script>
</body>
</html>