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
       } .mb-md{
       	   display: none;
       }
</style>

</head>
<body>

	<div class="row">
						<div class="col-md-12">
							<form name="academicTimeTableForm" id="academicTimeTableForm">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Time Table</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="control-label">Academic Year</label>
                                                <select class="form-control" id="radioYearId" name="radioYearName">
                                                    <option value="">Select...</option>
                                                    <option value="${year.academicYearCode}">${year.academicYearName}</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="control-label">Class</label>                                                
                                                <select class="form-control"  id="radioClassId" name="radioClassName">
                                                    <option value="">Select...</option>
                                                    	<c:forEach var="className" items="${listStandard}">
															<option value="${className.standardCode}">${className.standardName}</option>			
														</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label class="control-label">Section</label>                                                
                                                <select class="form-control" id="hiddensectiontoset" name="hiddensectiontoset">
                                                    <option value="">Select...</option>
                                                </select>
                                            </div>
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary">Search </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						<div class="col-md-6 col-md-offset-3">						  
							<form>	
                                <section class="panel">
                                    <header class="panel-heading">
                                        <div class="panel-actions">
                                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                        </div>

                                        <h2 class="panel-title">Time Table</h2>
                                    </header>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-4 control-label">School Start Time</label>
                                            <div class="col-md-8">
                      							  <input type="text" class="form-control" id="SchoolStartTime" name="SchoolStartTime" value="${schoolDetails.schoolStartTime}" placeholder="">
                                           		<input type="hidden" id="hiddenSchoolStartTime" name="hiddenSchoolStartTime" class="textfield1" value="${schoolDetails.schoolStartTime}" readonly="readonly">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4 control-label">School End Time</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" id="SchoolEndTime" name="SchoolEndTime" value="${schoolDetails.schoolEndTime}"  placeholder="">
                                                
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4 control-label">No Of Periods</label>
                                            <div class="col-md-8">
                                                <input type="text" class="form-control" id="schoolPeriodNo" placeholder="">
                                            </div>
                                        </div>
                                    </div>
                                    <footer style="display: block;" class="panel-footer">
                                        <button id="popupBoxYes" class="btn btn-primary">Submit </button>
                                        <button type="reset" id="popupBoxNo" class="btn btn-default">Cancel</button>
                                    </footer>    
                                </section>
                            </form>
						</div>
					</div>	

	<input type="hidden" id="hiddenclasstoset" name="hiddenclasstoset" />
	<input type="hidden" id="hiddenSectiontoset" name="hiddenSectiontoset" />
	<script src='/cedugenie/js/backoffice/getSelectedValuesForTimeTable.js'></script>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>