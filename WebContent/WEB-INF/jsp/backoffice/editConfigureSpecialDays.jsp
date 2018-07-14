<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Edit Configure Working Days</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

				
				<div class="row">
					<div class="col-md-6 col-md-offset-3">
						  <form name="special" action="updateSpecialHolidays.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Configure Special Days</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
                                            
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Select The Term</label>
                                                <div class="col-sm-7">
                                                	<c:choose>
														<c:when test="${editTerm eq null && editTerm.size() eq 0}" >			
															<div class="errorbox" id="errorbox" style="visibility: visible;">
																<span id="errormsg">No Details Found</span>	
															</div>
														</c:when>
														<c:otherwise>			
															<c:forEach  var="term" items="${editTerm}">
																<input type="text" name="termname" id="termname" class="form-control" value="${term.termName}"/>
															</c:forEach>
														</c:otherwise>
													</c:choose>
                                                </div>
                                            </div>   
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Working Days</label>
                                                <div class="col-sm-7">
                                                	<c:choose>
													<c:when test="${editTerm eq null && editTerm.size() eq 0}">			
															<div class="errorbox" id="errorbox" style="visibility: visible;">
																<span id="errormsg">No Details Found</span>	
															</div>
														</c:when>
													<c:otherwise>	
														<c:forEach  var="term" items="${editTerm}">
															<input type="text" id="tworkingone" name="tworking" readonly="readonly" class="form-control" value="${term.noOfWorkingDays}"/>
															<input type="hidden" size="1" name="mode" id="mode" value="Special"/>
															<input type="hidden" id="holidaycode" name="holidaycode" size="1" value="${strspeccode}"/>
															<input type="hidden" name="yesno" size="1" value="${term.termCode}"/>
														</c:forEach>
													</c:otherwise>
													</c:choose>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Start Date</label>
                                                <div class="col-sm-7">
                                                	<c:choose>
														<c:when test="${listspecialHolidays eq null && listspecialHolidays.size() eq 0}">			
															<div class="errorbox" id="errorbox" style="visibility: visible;">
																<span id="errormsg">No Details Found</span>	
															</div>	
														</c:when>
													<c:otherwise>			
														<c:forEach var="holi" items="${listspecialHolidays}">
															<c:out value="${holi.specialHoliday}"/>
														</c:forEach>
													</c:otherwise>
													</c:choose>
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </span>
                                                        <input type="text" class="form-control" id="strdate"  readonly="readonly" name="inputDateA1" data-plugin-datepicker="" value = "">
                                                    </div>
                                                </div>
										    </div>
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Enter The Reason</label>
                                                <div class="col-sm-7">
                                                	<c:choose>
													<c:when test="${listspecialHolidays eq null && listspecialHolidays.size() eq 0}">			
														<<div class="errorbox" id="errorbox" style="visibility: visible;">
															<span id="errormsg">No Details Found</span>	
														</div>
													</c:when>
													<c:otherwise>		
														<c:forEach var="holi" items="${listspecialHolidays}">
															<textarea class="form-control" id="reasonone" name="reasonone" maxlength="140" data-plugin-maxlength="" rows="3">${holi.holidayDesc}</textarea>
														</c:forEach>
													</c:otherwise>
													</c:choose>
                                                </div>
										    </div>
                                            <div class="form-group">
                                                <label class="col-sm-5 control-label">Compensatory Working Date</label>
                                                <div class="col-sm-7">
                                                <c:choose>
														<c:when test="${listspecialHolidays eq null && listspecialHolidays.size() eq 0}">			
															<<div class="errorbox" id="errorbox" style="visibility: visible;">
																<span id="errormsg">No Details Found</span>	
															</div>	
														</c:when>
														<c:otherwise>			
															<c:forEach var="holi" items="${listspecialHolidays}">
															<c:out value="${holi.compensatory}"/>
														</c:forEach>
														</c:otherwise>
														</c:choose>
                                                    <div class="input-group">
                                                        <span class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                        </span>
                                                        
                                                        <input type="text" class="form-control" data-plugin-datepicker="" id="inputDate1" name="inputDateA" readonly="readonly">
                                                    </div>
                                                </div>
										    </div>
										</div>
										<footer style="display: block;" class="panel-footer">
											<button class="btn btn-primary" type = "submit" value="submit" id="submitbutton" onclick="checkcompen();">Submit </button>
										</footer>
									</section>
									<div class="warningbox" id="warningbox" >
										<span id="warningmsg"></span>	
									</div>
	                            </form>
							</div>
						</div>	
					
					
	
	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
function checkcompen(){
	var days=null;
	<c:if test="${editTerm ne null}">		
	<c:forEach var="holi" items="${listspecialHolidays}">
	days="${holi.compensatory}";
	</c:forEach>
	</c:if>
	if(days==""){
		var permanant = document.getElementById('tworkingone').value;
		permanant=parseInt(permanant)+1;
		document.getElementById('tworkingone').value = permanant;
	}
}
</script>
<script>
$(document).ready(function() {
// 	$('#strdate').Zebra_DatePicker();
	
// 	$('#strdate').Zebra_DatePicker({
//  	  format: 'd/m/Y'
// 	});
// 	$('#inputDate1').Zebra_DatePicker();
	
// 	$('#inputDate1').Zebra_DatePicker({
//  	  format: 'd/m/Y'
// 	});
	var valid="";
	$('#submitbutton').click(function(){
		var compen = $("#strdate").val();
	 	if(compen != ""){
	 	return true;
	}
	else
	{
 		document.getElementById("warningbox").style.visibility='visible';
 		document.getElementById("warningmsg").innerHTML="Please Enter Date.";
 		return false;
	}
 	});
	return valid;
});
</script>
</body>
</html>