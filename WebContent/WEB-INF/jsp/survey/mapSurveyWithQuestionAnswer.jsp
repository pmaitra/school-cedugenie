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
<title>Create Term</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script type="text/javascript">

</script>




</head>
<body>
		<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success">
				<strong>${msg}</strong>
			</div>
		</c:if>
		
		<c:if test="${insertUpdateStatus eq 'fail'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>	
		<c:if test="${insertUpdateStatus eq 'exists'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>	
		<%-- <c:if test="${insertUpdateStatus eq 'duplicate'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>	 --%>
		
					<!-- start: page -->
					<div class="alert alert-danger" id="javascriptmsg1" style="display: none">
						<span> </span>	
					</div>
                    <div class="row">
						<div class="col-md-6 col-md-offset-3" >
						  <form action = "addSurveyQuestionAnswer" method = "POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Configure Survey</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-5 control-label">Survey Name: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="surveyName" name="surveyName" class="form-control" type="text" pattern="[a-zA-Z\s]+" required title = "Charecter And atleast One Number Only">
											</div>
										</div>	
										<div class="form-group">
										<label class="col-sm-5 control-label"><b>Programme Name: <span class="required" aria-required="true">*</span></b></label>
										<div class="col-sm-7">
											<select class="form-control" name="course" id="course" required >
	                                              <option value="">Select...</option>
	                                              <c:forEach var="course" items="${courseList}" varStatus="i">
														<option value="${course.courseCode}">${course.courseName}</option>
												 </c:forEach>
	                                         </select> 
	                                      </div>
									</div>
									</div>
									
									
									
									<div class="col-md-12">
									
									<!-- <input type="hidden" name="saveId" id="saveId">
									<input type="hidden" name="getTerm" id="getTerm">
									<input type="hidden" name="getProgram" id="getProgram"> -->
									<!-- <input type="hidden" name="saveId" id="saveId"> -->
		                            <section class="panel">
									<header class="panel-heading">
										<h2 class="panel-title">Existing Question</h2>
									</header>
									<div class="panel-body">
										<%-- <c:forEach var="terms" items="${termList}" varStatus="i">
											<input type="hidden" name="termCode" value="${courseType.courseTypeName}">
										</c:forEach> --%>
										<table  id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
											<thead>
												<tr>
		                                            <th>Select</th>
													<th>Question Name</th>
													<th>Must Answer</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach var="question" items="${questionList}" varStatus="i">
												<tr class="gradeX">
													<td>
														<input type="checkbox" id="questionId${i.index}" name="questionId"  value="${question.questionId}">
														
													</td>
													<td>
														${question.question}
													</td>
													<td>
														<%-- <input type="radio" name="mustAnswer${i.index}" id="mustAnswer${i.index}" value="Y" > Yes 
														<input type="radio" name="mustAnswer${i.index}" id="mustAnswer${i.index}" value="N" > No  --%>
														<input type="checkbox" id="mustAnswer${i.index}" name="mustAnswer" value ="selected">
													</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
									</section>
									
								</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" >Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
										<!--  <input class="mb-xs mt-xs mr-xs btn btn-primary" type = "button"  value = "Submit" onclick="return addSurveyQuestionAnswer()"> </input> -->
									</footer>
								</section>
                            </form>
						</div>
					</div>
					
				
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

<script type="text/javascript">
	function addSurveyQuestionAnswer(){
		//alert("hiiii");
		var questionId = document.getElementsByName("questionId");
		var mustAnswer = document.getElementsByName("mustAnswer");
		var questionAnswer = null;
		//alert("length====="+questionId.length);
		for(var i=0;i<questionId.length;i++){
			if(document.getElementById("questionId"+i).checked){
				questionAnswer = questionAnswer + "*"+document.getElementById("questionId"+i).value;
				var answer =  document.getElementById("mustAnswer"+i).checked;
				//alert("answer=="+answer);
				questionAnswer = questionAnswer + "#"+answer;
			}
			
			
		}
	//	alert("questionAnswer=="+questionAnswer);
		window.location="addSurveyQuestionAnswer.html?questionAnswer="+questionAnswer;
	}
</script>
</body>
</html>