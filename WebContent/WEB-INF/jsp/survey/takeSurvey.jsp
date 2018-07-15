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
       }
</style>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>

</head>
<body>

		<c:if test="${status eq 'success'}">
			<div class="alert alert-success">
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<c:if test="${status eq 'fail'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>
	
		<%-- <c:if test="${surveyList eq null}">
			<div class="alert alert-danger">
				<strong>No Survey Available For This Program.</strong>
			</div>
		</c:if> --%>
		<div class="row">
						<div class="col-md-6 col-md-offset-3">
							<form:form method="GET" id="takeSurvey" name="takeSurvey" action ="takeSurvey.html">
								<section class="panel">
									
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Survey</h2>										
									</header>
									<div style="display: block;" class="panel-body"> 
									
                                        <div class="form-group">
                                            <label class="col-md-5 control-label">Survey Name :</label>
                                            <div class="col-md-7">
                                                <select class="form-control" name="surveyId" id="SurveyId" required >
                                                    <option value="">Select...</option>
                                                    <c:forEach var="survey" items="${surveyList}" varStatus="i">
														<option value="${survey.surveyId}">${survey.surveyName}</option>
													</c:forEach>
                                                </select> 
                                            </div>
                                        </div>
										
									</div>
									
								 <footer  class="panel-footer">
											<button class="btn btn-primary" type="submit" id="submit" name="submit" >Submit  </button>
											<!-- <button type="button" class="btn btn-default">Reset</button> -->
									</footer> 
								</section>
							</form:form>	                          
						</div>
						
					</div>	
	
	
		
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>	
</body>
</html>