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
						  	<form name="special" action="listConfigureSpecialDays.html" method="get">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Configure Spacial Days</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-5 control-label">Course</label>
											<div class="col-sm-7">
											<c:choose>
												<c:when test="${CourseList eq null && CourseList.size() eq 0}">			
													<div class="errorbox" id="errorbox" style="visibility: visible;">
														<span id="errormsg">No Course Found</span>	
													</div>		
												</c:when>
											<c:otherwise>
												<select id="courseId" name="courseId" class="form-control">
                                                    <option value="">Select...</option>
                                                    <c:forEach var="courses" items="${CourseList}">
														<option value="<c:out value="${courses.courseCode}"></c:out>"><c:out value="${courses.courseName}"></c:out></option>
													</c:forEach>
                                                </select>
                                                </c:otherwise>
                                                </c:choose>
											</div>
										</div>                                        
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Select The Term</label>
											<div class="col-sm-7">
												<c:choose>
													<c:when test="${TermName eq null && TermName.size() eq 0}">			
														<div class="errorbox" id="errorbox" style="visibility: visible;">
															<span id="errormsg">No Academic Year Found</span>	
														</div>		
													</c:when>
												<c:otherwise>
												<select id="sterm" name="inputstream" class="form-control">
                                                    <option value="">Select...</option>
                                                </select>
                                                </c:otherwise>
                                                </c:choose>
											</div>
										</div>
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Working Days</label>
											<div class="col-sm-7">
												<input id="tworkingone" value="" class="form-control" type="text" readonly="readonly">
												<input type="hidden" size="1" name="mode" id="mode" value="Special"/>
											</div>
										</div>
                                        <div class="form-group col-sm-5">
                                            <div class="radio-custom radio-primary">
                                                <input type="radio" id="singled" name="singled" value="single" onclick="showdiv();">
                                                <label for="singled">Single Day</label>
                                            </div>
                                        </div>
                                        <div class="form-group col-sm-7">
                                            <div class="radio-custom radio-primary">
                                                <input type="radio" id="singled" name="singled" onclick="showdivone();" value="multi">
                                                <label for="singled">Multiple Days</label>
                                            </div>
                                        </div>
									</div>
                                    <hr>
                                    <div id="one" style="display: none;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-5 control-label">Date</label>
											<div class="col-sm-7">
												<div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" class="form-control" data-plugin-datepicker="" id="inputDate"  readonly="readonly" name="inputDateA12" value = "">
                                                </div>
											</div>
										</div>                                        
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Reason</label>
											<div class="col-sm-7">
												<textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" id="reason" name="reason" value=""></textarea>
												<input type="hidden" id="datesone" name="datesone"/>
											</div>
										</div>
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Compensate On</label>
											<div class="col-sm-7">
												<div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" class="form-control" data-plugin-datepicker="" id="cwone" name="inputDateAh" readonly="readonly">
                                                </div>
											</div>
										</div>
                                        <div class="form-group">
											<label class="col-sm-5 control-label"></label>
											<div class="col-sm-7">
												<button type="button" class="mb-xs mt-xs mr-xs btn btn-sm btn-info" value="calculate" name="Calculate" onclick="validate(); showupdateddiff();">Calculate</button>
											</div>
										</div>
                                            
									</div>
									<div id="two" style="display: none;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label"> Start Date</label>
											<div class="col-sm-7">
												<div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" class="form-control" data-plugin-datepicker="" id="strdate"  readonly="readonly" name="inputDateA1" value = "">
                                                </div>
											</div>
										</div> 
										<div class="form-group">
											<label class="col-sm-5 control-label"> End Date</label>
											<div class="col-sm-7">
												<div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" class="form-control" data-plugin-datepicker="" id="enddate" name="inputDateA2"  readonly="readonly" value = "">
                                                </div>
											</div>
										</div>                                        
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Reason</label>
											<div class="col-sm-7">
												<textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" id="reasonone" name="reasonone" value=""></textarea>
											</div>
										</div>
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Compensate On</label>
											<div class="col-sm-7">
                                                <table class="table table-bordered table-striped mb-none" id="tabdata">
                                                    <tbody>
                                                        <tr>
                                                            <td>
                                                                <div class="input-group">
                                                                    <span class="input-group-addon">
                                                                        <i class="fa fa-calendar"></i>
                                                                    </span>
                                                                    <input type="text" class="form-control" data-plugin-datepicker="" id="inputDate1" name="inputDateA" value="" readonly="readonly">
                                                                	
                                                                </div>
                                                            </td>
                                                            <td>
                                                                <a class="on-default" href="#">
                                                                    <i class="fa fa-minus-square" onclick="deleteRow1(this);"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <tr>
															<td>
																<input id="moreDates" type="button" value="Add" class="btn btn-primary pull-right" onclick="addRow();" />
															</td>
														</tr>
                                                    </tbody>
                                                </table>
											</div>
										</div>
                                        <div class="form-group">
											<label class="col-sm-5 control-label"></label>
											<div class="col-sm-7">
												<button type="button" class="mb-xs mt-xs mr-xs btn btn-sm btn-info" value="calculate" name="Calculate" onclick="validate(); showupdateddiff();">Calculate</button>
											</div>
										</div>
                                        <table id="data" style="left: 60px;">
											<tr>
												<td><input type="hidden" id="dates" name="dates" size="10" readonly="readonly"/></td>
											</tr>
										</table>    
									</div>
                                    <hr>
                                    <div style="display: block;" class="panel-body">
                                        <div class="form-group">
											<label class="col-sm-5 control-label">Updated Working Days</label>
											<div class="col-sm-7">
												<input class="form-control" type="text" id="tworking" name="tworking" readonly="readonly">
											</div>
										</div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" value="submit" id="submitbutton">Submit </button>
										<button type="reset" class="btn btn-default" value = "Clear">Reset</button>
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
<script type="text/javascript" src="/cedugenie/js/backoffice/updatedWorkingDays.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/configureSpecialDays.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/adddelete.js"></script>
</body>
</html>