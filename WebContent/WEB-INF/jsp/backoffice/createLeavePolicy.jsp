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
<title>Leave Policy</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
					<c:if test="${insertStatus eq 'success'}">
							<div class="alert alert-success">
									<strong>Leave Policy Successfully Inserted</strong>
							</div>
					</c:if>
					<c:if test="${insertStatus eq 'fail'}">
							<div class="alert alert-danger">
									<strong>Leave Policy Insertion failed</strong>
							</div>
					</c:if>
						<c:choose>
							<c:when test="${resourceTypeList eq null}">	
								<div class="errorbox" id="errorbox" style="visibility:visible;">
									<span id="errormsg"><strong>No Resource Type Found</strong></span>
								</div>					
							</c:when>
							<%-- <c:when test="${leaveList eq null}">	
								<div class="errorbox" id="errorbox" style="visibility:visible;">
									<span id="errormsg"><strong>No Leave Type Found</strong></span>
								</div>					
							</c:when> --%>
							<c:otherwise>
			
					 <div class="row">
						<div class="col-md-12">
						  <form:form name="leavePolicy"  method="POST" action="saveLeavePolicy.html">
						  
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Leave Policy</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                       <div class="row">
                                       <div class="col-md-3">                                                            
                                                <div class="form-group">
                                                    <label class="control-label">Select Resource Type <span aria-required="true" class="required">*</span></label>
                                                    <select class="form-control" id="resourceTypeSelect" name="resourceTypeSelect">
                                                        <option value="">Select...</option>
                                                        <c:forEach var="resourceType" items="${resourceTypeList}">
															<option value="${resourceType.resourceTypeName}">${resourceType.resourceTypeName}</option>
														</c:forEach>
                                                    </select>
                                                </div>   
                                        </div>
                                        </div>                    
                                  </div> 
									<div class="panel-body" id="encashmentLeave" name="encashmentLeave" style="display: none;"> 
                                       <div class="row">
                                        <p><b> There are Three types of leave:-</b> 
	                                         
	                                        	<ul type="circle">
	                                        		<li>
	                                        			Casual Leave(CL)
	                                        		</li>
	                                        		<li>
	                                        			Earned Leave(EL)
	                                        		</li>
	                                        		<li>
	                                        			Medical Leave(ML)
	                                        		</li>
	                                        	</ul>
	                                        </p>
	                                        <p> <strong>1) Casual Leave:-</strong> 
	                                        <ul type="bullet">
	                                        	<li>
	                                        		 <p>Casual Leave is not fixed for all organization. It varies from organization to organization.
	                                        		You can set your <b>Casual Leave</b> according to you. </p>
	                                        	</li>
	                                        	<li>
	                                        		 	<p>Casual Leave is not earned by duty. A staff on CL is not treated as absent from duty.</p> 
	                                        	</li>
	                                        	<li >
	                                        		 <p>Saturdays, Sundays, restricted holiday and holidays, whether intervening, prefixed or suffixed, shall not be counted as Casual Leave.</p>  
	                                        	</li>
	                                        	<li>
	                                        		 <p>CL should not be granted for more than 5 days at any time, except under special circumstances.</p>  
	                                        	</li>
	                                        	<li>
	                                        		 <p>CL can be taken for half a day also.</p>  
	                                        	</li>
	                                        	<li>
	                                        		 <p>An officer who takes CL when on tour is not entitled to draw daily allowance during such CL.</p> 
	                                        	</li>
	                                        </ul>
	                                        </p>
	                                         <p><strong>2) Earned Leave:-</strong>  
	                                        <ul type="bullet">
	                                        	<li>
	                                        		 <p>Earned Leave is not fixed for all organization. It varies from organization to organization.<br>
	                                        		You can set your <b>Earned Leave</b> according to you.</p>
	                                        	</li>
	                                        	<li>
	                                        		 	<p>The EL admissible to a member of the staff shall be according to the set days in a calendar year. 15 days of EL is credited in advance on the first January and first July every year.</p>  
	                                        	</li>
	                                        	<li >
	                                        		 <p>EL credit for the half-year in which the staff retires/resigns/removed/dismissed or dies in service will be afforded at the rate of 2½ days per completed calendar month up to the end of the calendar month preceding the last calendar month of service. While affording credit, fraction shall be rounded off to the nearest day.</p>  
	                                        	</li>
	                                        	<li>
	                                        		 <p>EL can be accumulated up to 300 days (including the number of days for which encashment has been allowed along with LTC).</p>  
	                                        	</li>
	                                        	<li>
	                                        		 <p>The maximum amount of Earned Leave that can be granted to a member of the staff at a time shall be 180 days.</p>   
	                                        	</li>
	                                        </ul>
	                                       </p>
	                                       <p>  <strong>3) Medical Leave:-</strong> 
	                                        <ul type="bullet">
	                                        	<li>
	                                        		 	<p>Medical Leave is not fixed for all organization. It varies from organization to organization.<br>
	                                        		You can set your <b>Medical Leave</b> according to you.</p>
	                                        	</li>
	                                        	<li>
	                                        		 	<p>The Medical Leave can be given to the respected person according to his/her application.</p> 
	                                        	</li>
	                                        	<li >
	                                        		  <p>If the person has given the respected certificate for the Medical Leave then his half pay leave will be deducted equals to twice of the number of Medical Leave days.<br> 
	                                        		 At the same time his salary will not be deducted.</p> 
	                                        	</li>
	                                        	<li>
	                                        		  <p>If the person is unable to show the respected certificate then his Earn pay leave deducted equals to his medical days leave.<br> 
	                                        		At the same time his salary will be deducted.</p> 
	                                        	</li>
	                                        </ul>
	                                       </p>
	                               </div><br><br> 
	                               <div class="row">
	                               	<p><b> Unused Leaves Carryover:-</b>
	                               	<p>This is an important policy option which allows the company to define rules about 'if and how' will any unused leaves be carried forward to the next annual leave cycle. </p>
	                               	<ul type="circle">
	                                        		<li>
	                                        			Encashment
	                                        		</li>
	                                        		<li>
	                                        			Carry Forward
	                                        		</li>
	                                </ul><br>
	                                <p> <strong>1) Encashment:-</strong>There are two options under the <b>"Encashment":-</b>
	                                	<ul>
	                                		<li>
	                                			Allowed
	                                		</li>
	                                			<ul>
	                                				<li>
	                                					<p>An amount of @<input type="text" class="form-control" name="dailyEncashment" id="dailyEncashment" style="width:100px; display: inline-block;" required> for encashment will be defined equal to the employees one day salary.</p> 
	                                					<input type= "hidden" id= "oldDailyEncashment" name= "oldDailyEncashment">
	                                				</li>
	                                				<li>
	                                					<p>If user chooses the option of <b>Allowed</b> then he will get the respected amount. <b><u>(Total unused days x Encashment amount)</u></b></p> 
	                                				</li>
	                                			</ul>
	                                		<li>
	                                			Not Allowed
	                                		</li>
	                                			<ul>
	                                				<li>
	                                					<p>If user chooses the option of <b> Not Allowed</b> then he will not get any amount. </p> 
	                                				</li>
	                                				<li>
	                                					<p>His Unused Leave days will not be counted as a salary.</p> 
	                                				</li>
	                                			</ul>
	                                	</ul>
	                               	</p>
	                               	<p> <strong>2) Carry Forward:-</strong>There are two options under the <b>"Carry Forward":-</b>
	                               	<ul>
	                                		<li>
	                                			Yes
	                                		</li>
	                                			<ul>
	                                				<li>
	                                					<p>If user chooses the option of <b>Yes</b> then his unused leaves will be carried forward to the next calendar year.</p> 
	                                				</li>
	                                				<li>
	                                					<p>In the next calendar year his leaves will be equal to the sum of unused leaves of the previous year and the leaves of current year</p> 
	                                				</li>
	                                				<li>
	                                					<p>For<input type="text" class="form-control" name="noOfYearsCarryForward" id="noOfYearsCarryForward" style="width:100px; display: inline-block;" required> years you want to carry forward the leaves.</p> 
	                                					<input type= "hidden" id= "oldNoOfYearsCarryForward" name= "oldNoOfYearsCarryForward">
	                                				</li>
	                                			</ul>
	                                		<li>
	                                			No
	                                		</li>
	                                			<ul>
	                                				<li>
	                                					<p>If user chooses the option of <b> No</b> then he will not be allowed to carried forward his unused leaves. </p> 
	                                				</li>
	                                			</ul>
	                                	</ul>
	                               	</p>
	                               <button class="btn btn-primary" type="submit" id="submitLeavePolicy" name= "submitLeavePolicy" value="SUBMIT">Submit </button>
	                               <button type="reset" class="btn btn-default" name="reset" >Reset</button>
	                               </div>
	                         </div>
								</section>
                            </form:form>
						</div>
				</div>	
					
              </c:otherwise>
         </c:choose>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#resourceTypeSelect").change(function(){
		var resourceTypeSelect = $(this).val();
		document.getElementById("encashmentLeave").style.display="block";
		$.ajax({
			url: '/cedugenie/getLeavePolicyToShow.html',
			data: "resourceTypeSelect=" + resourceTypeSelect,
	        dataType: 'json',
	        success: function(data) {
	        	document.getElementById("dailyEncashment").value = "";
	        	document.getElementById("noOfYearsCarryForward").value = "";
	        	if(data != "")
	        	{
	        		var splitedData = data.split("#");
		        	document.getElementById("dailyEncashment").value = splitedData[0];
		        	document.getElementById("noOfYearsCarryForward").value = splitedData[1];
		        	document.getElementById("oldDailyEncashment").value = splitedData[0];	// Addded by saif 20-03-2018
		        	document.getElementById("oldNoOfYearsCarryForward").value = splitedData[1]; // Addded by saif 20-03-2018
	        	}
	        }
	  });
		if(resourceTypeSelect == ""){
			document.getElementById("encashmentLeave").style.display="none";
		}
	});
	
	
	$("#dailyEncashment").change(function(){
		var regex = /^[0-9]*$/;
		var dailyEncashment= document.getElementById("dailyEncashment").value;
		if(dailyEncashment!= ""){
			if (!dailyEncashment.match(regex)){
				alert("Daily Encashment should be a integer value!!");
				document.getElementById("dailyEncashment").value = 0;
				alert("Daily Encashment is now set to '0'");
				return false;
			}
		}
});
	$("#noOfYearsCarryForward").change(function(){
		var regex = /^[0-9]*$/;
		var noOfYearsCarryForward= document.getElementById("noOfYearsCarryForward").value;
		if(noOfYearsCarryForward!= ""){
			if (!noOfYearsCarryForward.match(regex)){
				alert("Carry Forward Year should be a integer value!!");
				document.getElementById("noOfYearsCarryForward").value = 0;
				alert("Carry Forward Year is now set to '0'");
				return false;
			}
		}
});
	
});
</script>
</body>
</html>