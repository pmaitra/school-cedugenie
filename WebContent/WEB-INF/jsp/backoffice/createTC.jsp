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
	<h2>Create Tc</h2>
</header>
<div class= "content-padding">
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
					
	<div class="row">
		<form method="POST" action="addTC.html" >
			<div class="col-md-6 col-md-offset-3">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Create TC</h2>										
					</header>
					<div style="display: block;" class="panel-body"> 
	                    <div class="col-md-8 col-md-offset-2" id ="rollNumberTable">
	                        <div class="form-group">
	                            <label class="col-md-4 control-label">Roll Number</label>                                                
	                            <div class="col-md-8">
	                                <input type="text" class="form-control" id="rollNumber" name="rollNumber">
	                            </div>
	                        </div>
	                    </div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button type="button" id="detailsButton" class="btn btn-primary">Get Details</button>
					</footer>
				</section>
			</div>
			<div class="col-md-8 col-md-offset-2">	
	            <section class="panel">
	                <header class="panel-heading">
	                    <div class="panel-actions">
	                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                    </div>
	                    <h2 class="panel-title">Details</h2>
	                </header>
	                <div style="display: none;" class="panel-body" id="subjectDetailTable">
                  		<div class="row">
                       		<div class="col-md-6">
                           		<div class="form-group">
                                	<label class="col-md-4 control-label">Standard</label>
                               		<div class="col-md-8">
                                   		<input type="text" class="form-control" id="standard" readonly="readonly" name="standard">
                                	</div>
                              	</div>
                              	<div class="form-group">
                                  	<label class="col-md-4 control-label">Name</label>
                                  	<div class="col-md-8">
                                    	<input type="text" class="form-control" readonly="readonly" id="name">
                                  	</div>
                              	</div>
                          	</div>
                          	<div class="col-md-6">
                              	<div class="form-group">
                                  	<label class="col-md-4 control-label">Section</label>
                                  		<div class="col-md-8">
                                   	 		<input type="text" class="form-control" id="section" readonly="readonly" name="section">
                                  		</div>
                           		</div>
                              	<div class="form-group">
                                  	<label class="col-md-4 control-label">Reason</label>
                               		<div class="col-md-8">
                                   		<select class="form-control" id="reason" name="reason">
                                       		<option value="">Select...</option>
                                       		<option value="PASSOUT">PASSOUT</option>
											<option value="DISCIPLINARY">DISCIPLINARY</option>
											<option value="NON-DISCIPLINARY">NON-DISCIPLINARY</option>	
                               			</select>
                               		</div>
                           		</div>
                          	</div>
                      	</div>
                        <br>
                        <div class="row">
                       		<div class="col-md-12">
                           		<div class="form-group">
                               		<label class="col-md-2 control-label">Description</label>
                               		<div class="col-md-10">
                                        <textarea class="form-control" id="description" name="description" rows="2" data-plugin-maxlength="" maxlength="240"></textarea>
                                   	</div>
                               	</div>
                               	<div class="form-group">
                                    <label class="col-md-2 control-label">Character</label>
                                    <div class="col-md-10">
                                        <input type="text" class="form-control" id="studentCharacter" name="studentCharacter">
                                    </div>
                                </div>
                            </div>
                     	</div>
                    </div> 
                   	<div class="alert alert-danger" id="infomsgbox" style="display:none">
						<span id="infomsg"></span>
					</div>
					<footer id="submitButtonDiv" class="panel-footer" style="display:none">
						<button type="submit" class="btn btn-primary" id="submitbtn">Grant TC</button>
					</footer>
					<div class="alert alert-warning" id="warningbox" style="display: none">
						<span id="warningmsg"></span>	
					</div>
           		</section>
			</div>
    	</form>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	 $("#detailsButton").click(function(){
		var Regx= /^[ A-Za-z0-9_@./#&+-]*$/;
		var roll=document.getElementById("rollNumber").value;
		document.getElementById("rollNumber").value=roll;
		if(roll =="" || !roll.match(Regx)){
			document.getElementById("warningbox").style.display='block';
			document.getElementById("warningmsg").innerHTML="Please provide Roll Number";
			document.getElementById("rollNumber").value="";
		}else{
		$.ajax({
	        url: '/cedugenie/getNameStandardSectionForRollNumber.html',
	        dataType: 'json',
	        data: "rollNumber=" + ($("#rollNumber").val()),
	        success: function(dataDB) {
	        	if(dataDB != "null" && dataDB !=""){
	        		document.getElementById("subjectDetailTable").style.display='block';
	        		dataDB=dataDB.split("*");
	        		document.getElementById("name").value=dataDB[0];
	        		document.getElementById("standard").value=dataDB[1];
	        		document.getElementById("section").value=dataDB[2];
	        		
	        		$.ajax({
	    		        url: '/cedugenie/getStudentFeesPaymentStatus.html',
	    		        dataType: 'json',
	    		        data: "rollNumber=" + ($("#rollNumber").val()),
	    		        success: function(data) {
	    		        	if(data == ""){
	    		        		document.getElementById("infomsgbox").style.display = 'block';
	    		        		document.getElementById("infomsg").innerHTML="TC Can not be granted because Fees is PENDING";
	    		        	}
	    		        	else{
	    		        		document.getElementById("submitButtonDiv").style.display='block';
	    		        	}
	    		        	//data=data.split("*");
	    		        	//var status="Student Fees Status :: "+data[0]+"<br>Library Fine Status :: "+data[1]+"<br>Library Book Status :: "+data[2];
	    		        	/* if(data[0]=="REMAINING" || data[1]=="PENDING" || data[2]=="ALLOTED"){
	    		        		status=status+"<br>TC Cannot Be Granted";
	    		        		document.getElementById("submitButtonDiv").style.display='none';
	    		        	}else{
	    		        		document.getElementById("submitbtn").removeAttribute('disabled');
	    		        		document.getElementById("submitButtonDiv").style.display='block';
	    		        	} */
	    		        	/* document.getElementById("submitbtn").removeAttribute('disabled');
    		        		document.getElementById("submitButtonDiv").style.visibility='visible';

	    		        	
	    		        	document.getElementById("infomsgbox").style.display='block';
	    		     		document.getElementById("infomsg").innerHTML=status; */
	    		       }
	    			});
				}else{
					document.getElementById("subjectDetailTable").style.display='none';
					document.getElementById("infomsgbox").style.display='block';
		     		document.getElementById("infomsg").innerHTML="Student Details Not Found";
				}
       		}
		});
	 }
});
	  
});
</script> 
</body>
</html>