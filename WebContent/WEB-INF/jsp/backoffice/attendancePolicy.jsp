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
<title>Library Policy</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
						
					 <div class="row">
						<div class="col-md-12">
						  	<form:form name="attendancePolicy"  method="POST" action="saveAttendancePolicy.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Attendance Policy</h2>										
									</header>
									<div style="display: block;" class="panel-body" >
                                        
										<ol>
											<li>
                                                Employees are required to work for a minimum of 40 hour per week, that is 8 hours per day.
                                            </li>
                                            <li>
                                                Normal working hours are from Monday to Friday, shift wise with a one hour lunch break. This will vary depending on the nature of work.
                                            </li>
                                            <li>
                                                It is the duty of all staff to report for work and be punctual on every official working day. If they are unable to attend or are late due to unavoidable circumstances, they should inform their line manager as soon as possible.
                                            </li>
                                            <li>
                                                All absences from duty will require reasonable explanation from the employee and approval of the line manager. Absenteeism and habitual late coming are causes for disciplinary action under disciplinary code.
                                            </li>
                                            <li>
                                                Public and official holidays will be observed in accordance with the public holidays laws of each country.
                                            </li>
                                            <li>
                                                Staff members may be required to work on official holidays. The staff may be compensated by taking 
                                                <select id="compensation" name="compensation" class="form-control" style="width: 165px;" required>
                                                    <option value="">Select...</option>
                                                    <c:if test="${attendancePolicy.compensation eq 'COMP-OFF'}">
														<option value="COMP-OFF" selected="selected">COMP-OFF</option>
														<option value="COMPENSATION">COMPENSATION</option>
													</c:if>
													<c:if test="${attendancePolicy.compensation eq 'COMPENSATION'}">
														<option value="COMPENSATION" selected="selected">COMPENSATION</option>
														<option value="COMP-OFF">COMP-OFF</option>
													</c:if>
													<c:if test="${attendancePolicy.compensation eq null}">
														<option value="COMP-OFF">COMP-OFF</option>
														<option value="COMPENSATION">COMPENSATION</option>
													</c:if>
                                                </select> with approval from their immediate supervisor. Compensatory shall apply on public holidays. Compensatory day must be taken within 
                                                <input type="text" name="limitationOfDay" id="limitationOfDay" value="${attendancePolicy.limitationOfDay}" class="form-control" style="width: 100px;" required>
                                                days and depending on work demands/ agreement with the line manager.
                                            </li>
                                            <li>
                                                In cases where staff members work beyond these working hours or during weekends, over-time will be  
                                                <select  id="overTime" name="overTime" class="form-control" style="width: 100px;" required>
                                                    <option value="">Select...</option>
                                                    <c:if test="${attendancePolicy.overTime eq 'PAID'}">
														<option value="PAID" selected="selected">PAID</option>
														<option value="NOT PAID">NOT PAID</option>
													</c:if>
													<c:if test="${attendancePolicy.overTime eq 'NOT PAID'}">
														<option value="PAID">PAID</option>
														<option value="NOT PAID" selected="selected">NOT PAID</option>
													</c:if>
													<c:if test="${attendancePolicy.overTime eq null}">	
														<option value="PAID">PAID</option>
														<option value="NOT PAID">NOT PAID</option>
													</c:if>
                                                </select>
                                            </li>
										</ol> 
                                            
									</div>                                    
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type= "submit" onclick="return validate();">Submit </button>
									</footer>
								</section>
                            </form:form>
						</div>
						
					</div>	
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
function validate(){
			var valNumeric = document.getElementById("limitationOfDay").value;
			if(valNumeric!=""){
				if(isNaN(valNumeric)){
					document.getElementById("warningbox").style.visibility='visible';
					document.getElementById("waringmsg").innerHTML="Number of days must be numeric.";
					return false;
				}/* else{
				
					var intNum=parseInt(valNumeric);
					if(intNum<=0){
						document.getElementById("warningbox").style.visibility='visible';
						document.getElementById("waringmsg").innerHTML="Number of days must be greater than 0.";
						return false;
					}
				} */
			}else{
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("waringmsg").innerHTML="Number of days required.";
				return false;
			}
			if(document.getElementById("compensation").value==""){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("waringmsg").innerHTML="Compansation type required.";
				return false;
			}
			if(document.getElementById("overTime").value==""){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("waringmsg").innerHTML="Overtime mode required.";
				return false;
			}
		return true;
}


</script>
</body>
</html>