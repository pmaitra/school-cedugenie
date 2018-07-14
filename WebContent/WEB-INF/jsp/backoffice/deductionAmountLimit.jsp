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


						<div class="row">
						<c:choose>
							<c:when test="${financialYearList eq null || fn:length(financialYearList) lt 1 }">
								<div class="errorbox" id="errorbox" style="visibility:visible;">
									<span id="errormsg">No Financial Year Added Yet.</span>	
								</div>					
							</c:when>
							
							<c:when test="${incomeAgeList eq null || fn:length(incomeAgeList) lt 1 }">
								<div class="errorbox"  style="visibility:visible;">
									<span >No Age Category Added.</span>	
								</div>					
							</c:when>
						<c:otherwise>
						<div class="col-md-8 col-md-offset-2">
						  <form:form method="POST" action="insertITSectionDeductionAmount.html">
						  <input type="hidden" id="allSections"/>
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">IT Sections Deduction Limit</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-5 control-label">Financial Year</label>
											<div class="col-sm-7">
												<select class="form-control" name="financialYear.financialYearCode" id="financialYearCode" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="financialYear" items="${financialYearList}">
														<option value="${financialYear.financialYearCode}">${financialYear.financialYearName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div>  
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Age Category</label>
											<div class="col-sm-7">
												<select class="form-control" name="incomeAge.incomeAgeCode" id="incomeAgeCode" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="incomeAge" items="${incomeAgeList}">
														<option value="${incomeAge.incomeAgeCode}">${incomeAge.incomeAgeName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div>
                                            
									</div>
                                    <hr>
                                    <div style="display: block;" class="panel-body">
                                        
                                        <table class="table table-bordered table-striped mb-none" id="itSectionTable"> 
                                            <tbody>
                                                <tr>											
                                                    <td>IT Section Name</td>
                                                    <td>
                                                        <select class="form-control" name="itSectionCode" id="itSectionCode0" required> 
                                                            <option value="">Select...</option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        Amount
                                                    </td>
                                                    <td>
                                                        <input type="text" class="form-control" name="amount" id="amount0" onblur="addAmount();" pattern="^[1-9]\d*$" required>
                                                    </td>
                                                    <td>
                                                        <button type = "button" class="btn btn-info" onclick="addMoreSections();">Add </button>
                                                    </td>
                                                </tr>
                                                <tr style="background:#eee" >											
                                                    <td colspan="2">
                                                        <div class="checkbox-custom checkbox-default">
                                                        <input type="checkbox" name="totalAmountApplicable" id="totalAmountApplicable">
                                                        <label for="totalAmountApplicable">Please Check If Individual Section Amount Not Applicable</label>
                                                        </div>
                                                    </td>
                                                    <td><b>Total Deduction</b></td>
                                                    <td colspan="2"><input type="text" class="form-control" name="totalGroupAmount" id="groupAmount" readonly="readonly"></td>
                                                </tr>
                                            </tbody>
                                        </table>   
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type = "submit" name = "submit" value="submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default" value = "clear">Reset</button>
									</footer>
									<br>
									<c:if test="${failuremsg ne null}">
										<div class="errorbox" id="errorbox" style="visibility:visible;">
											<span id="errormsg">${failuremsg}</span>	
										</div>					
									</c:if>		
									<c:if test="${successmsg ne null}">
										<div class="successbox" id="successbox" style="visibility:visible;">
											<span id="successmsg">${successmsg}</span>	
										</div>					
									</c:if>
								</section>
                            </form:form>
						</div>
					</c:otherwise>
					</c:choose>	
					</div>	
					
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script>

// function validateFrom(){
// 	var itSectionName=document.getElementById("itSectionName").value;	
		
// 	if(itSectionName == ""){
// 		document.getElementById("warningbox").style.visibility = 'visible';
// 		document.getElementById("warningmsg").innerHTML =  "I.T Section Name Cannot Be Empty.";
// 		return false;
// 	}
	
	
// 	var itSectionNameClass=document.getElementsByClassName("itSectionName");
// 	var status= false;
// 	for(var varf=0;varf<itSectionNameClass.length;varf++){
// 		if(itSectionNameClass[varf].value==itSectionName){
// 			status= true;
// 			}
// 		}
// 	if(status){
// 		document.getElementById("warningbox").style.visibility = 'visible';
// 		document.getElementById("warningmsg").innerHTML =  "I.T Section Name Already Exists";
// 		return false;
// 		}
// }

$(document).ready(function() {
	$("#totalAmountApplicable").change(function() {
		if($(this).is(':checked')){
			var allAmount=document.getElementsByName("amount");			
			for(var y=0;y<allAmount.length;y++){
				allAmount[y].value='';
				var xid=allAmount[y].id;
				document.getElementById(xid).setAttribute("readonly","readonly");
			}
				document.getElementById("groupAmount").removeAttribute("readonly");
				$("#groupAmount").val('');
			}else{
					document.getElementById("groupAmount").setAttribute("readonly","readonly");
					$("#groupAmount").val('');
					var allAmount=document.getElementsByName("amount");			
					for(var y=0;y<allAmount.length;y++){
						allAmount[y].value='';
						var xid=allAmount[y].id;
						document.getElementById(xid).removeAttribute("readonly");
					}
					
				}
	});

	$("#financialYearCode").change(function() {
		$("#incomeAgeCode").get(0).selectedIndex=0;
	});
	
	$("#incomeAgeCode").change(function() {
		//document.getElementById("warningbox").style.display='none';				
		if($("#financialYearCode").val()==''){
			document.getElementById("warningbox").style.display = 'block';	        		
    		document.getElementById("warningmsg").innerHTML = "Please Select Financial Year.";
			return;
		}
		if($(this).val()==''){			
			return;
		}
		$.ajax({
	        url: '/cedugenie/getUnmappedITSections.html',
	        dataType: 'json',
	        data:{
	        		financialYearCode:$("#financialYearCode").val(),
	        		incomeAgeCode:$(this).val()
		        	}, 
	        success: function(data){
	        	if(data != ''){
	        		//document.getElementById("warningbox").style.visibility='collapse';	   
	        		$("#allSections").val(data);
	        		var totalSections=data.split(";");
	        		removeOption("itSectionCode0");
	        		for(var len=0;len<totalSections.length-1;len++){
		        		var individualSection=totalSections[len];
		        		var sectionArray=individualSection.split("~");
		        		document.getElementById("itSectionCode0").add(new Option(sectionArray[1], sectionArray[0]+"~"+sectionArray[1]));
		        		}
	        	}else{
	        		//document.getElementById("warningbox").style.visibility='visible';	  
	        		alert("All I.T Section Data Already Inserted For "+$("#incomeAgeCode").val());
	        		//document.getElementById("warningmsg").innerHTML = "All I.T Section Data Already Inserted For "+$("#incomeAgeCode").val();
	        	}
	        }			        
		}); 
	});
});

function removeOption(itSectionCode){
	var x=document.getElementById(itSectionCode);
	x.innerHTML="";
	var option=document.createElement("option");
	option.text="Please Select";
	option.value="";
	try  {	  
	  x.add(option,x.options[0]);
	  }
	catch (e)  {
	  x.add(option,0);
	  }
}

var y = 1;

function CheckSection(existingSections){
	var status = true;
	for(var x=0;x<existingSections.length;x++){
		if(existingSections[x].value==''){
			document.getElementById("warningbox").style.visibility='visible';	        		
    		document.getElementById("warningmsg").innerHTML = "Please Select I.T Section";
    		status= false;
    		return false;
			}	
	}
	return status;
}

function addAmount(){
	var allAmount=document.getElementsByName("amount");
	var groupAmount=0;
	for(var y=0;y<allAmount.length;y++){
		if(isNaN(allAmount[y].value)){
			//document.getElementById("warningbox").style.visibility='visible';	        		
		//	document.getElementById("warningmsg").innerHTML = "Please insert numeric value";
			alert("Please insert numeric value");
			return false;
			}
		if(parseInt(allAmount[y].value)<0){
			//document.getElementById("warningbox").style.visibility='visible';	        		
			//document.getElementById("warningmsg").innerHTML = "Please insert positive value";
			alert("Please insert positive value");
			return false;
			}
		if(allAmount[y].value!=''){
			//document.getElementById("warningbox").style.visibility='collapse';
			groupAmount=parseInt(groupAmount)+parseInt(allAmount[y].value);
			$("#groupAmount").val(groupAmount);
			}	
	}
}

function addMoreSections() {

	var existingSections=document.getElementsByName("itSectionCode");
	alert("existingSections==="+existingSections);
	if( !CheckSection(existingSections)){
			return false;
		}
	addAmount();
	var newSelect=document.getElementById("itSectionCode0").cloneNode(true);
	var newid="itSectionCode"+y;
	newSelect.id=newid;
	
	for(var x=0;x<existingSections.length;x++){
		for(var i=0;i<newSelect.length;i++){		
			if(existingSections[x].value==newSelect.options[i].value)
				newSelect.remove(i);
		}
	}
	if(newSelect.length<2){
		//document.getElementById("warningbox").style.visibility='visible';	        		
		//document.getElementById("warningmsg").innerHTML = "No More I.T Sections To Add";	
		alert("No More I.T Sections To Add");
		return false;
		}	
	
	newSelect.setAttribute("name","itSectionCode");
	newSelect.setAttribute("class","form-control");

	
	//document.getElementById("warningbox").style.visibility='collapse';
	
	var table = document.getElementById("itSectionTable");
	var rowCount = table.rows.length;
	var row = table.insertRow(rowCount-1);
	
	var cell,element;
	
	cell = row.insertCell(0);
	element = document.createElement("td");
	element.innerHTML = "I.T Section Name";	
	cell.appendChild(element);	
	
	var cell0 = row.insertCell(1);	
	cell0.appendChild(newSelect);	
	
	cell = row.insertCell(2);
	element = document.createElement("td");
	element.innerHTML = "Amount";	
	cell.appendChild(element);
	
	cell = row.insertCell(3);
	element = document.createElement("input");
	element.type = "text";	
	element.setAttribute("class","form-control");
	element.setAttribute("id","amount"+y);
	element.setAttribute("name","amount");
	element.setAttribute("onBlur","addAmount();");
	cell.appendChild(element);
	
// 	cell = row.insertCell(4);
// 	element = document.createElement("button");
// 	element.id = "del"+y;
// 	element.setAttribute("class", );
// 	element.setAttribute("onclick","deleteThisRow(this);");
// 	cell.appendChild(element);
	cell = row.insertCell(4);
	element = document.createElement('a');
    element.setAttribute("onclick","deleteThisRow(this);");
    element.setAttribute("class","fa fa-minus-square");
    element.setAttribute("href","#");
    cell.appendChild(element);
	y++;
// 	var innerHeight2=document.body.scrollHeight;
// 	var frame=window.parent.document.getElementById("frame");	    	
// 	frame.style.height = innerHeight2+25+ 'px';
	
	return true;
};

function deleteThisRow(obj) {
	var table = document.getElementById("itSectionTable");
	var rowCount = table.rows.length;
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
// 		var innerHeight2=document.body.scrollHeight;
//     	var frame=window.parent.document.getElementById("frame");	    	
//     	frame.style.height = innerHeight2+25+ 'px';
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
    		
}
</script>
</body>
</html>