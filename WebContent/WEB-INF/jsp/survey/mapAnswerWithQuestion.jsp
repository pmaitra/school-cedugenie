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
		<c:if test="${insertUpdateStatus eq 'duplicate'}">
			<div class="alert alert-danger">
				<strong>${msg}</strong>
			</div>
		</c:if>	
		
					<!-- start: page -->
					<div class="alert alert-danger" id="javascriptmsg1" style="display: none">
						<span> </span>	
					</div>
                    <div class="row">
						<div class="col-md-6 col-md-offset-3" >
						  <form action="addQuestionAnswer.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Configure Question Answer</h2>										
									</header>
									<div style="display: block;" class="panel-body">
											<div class="form-group">
												<label class="col-sm-5 control-label">Question Name: <span class="required" aria-required="true">*</span></label>
												<div class="col-sm-7">
													<%-- <select class="form-control" id="questionId" name="questionId" required >
														<option value="">Select</option>
														<c:forEach var="question" items="${questionList}" varStatus="i">
															<option value="${question.questionId}">${question.question}</option>
														</c:forEach>
													</select> --%>
													<input id="questionId" name="questionId" class="form-control" type="text" required title = "Charecter And atleast One Number Only">
												</div>
											</div>	
											<div class="form-group">
												<label class="col-sm-5 control-label">Answer Type: <span class="required" aria-required="true">*</span></label>
												<div class="col-sm-7">
													<select class="form-control" id="inputType" name="inputType" required onchange = "hideAddButton()">
														<option value="">Select</option>
														<option value="text">Text</option>
										                   <option value="checkbox">Checkbox</option>
										                   <option value="radio">Radio</option>
										                   <option value="textarea">Textarea</option>
													</select>
												</div>
											</div>	
										<!-- <div class="form-group">
											<label class="col-sm-5 control-label">Answer:  <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="termName" name="termName" class="form-control" type="text" pattern="[a-zA-Z0-9\s]+" required title = "Charecter And atleast One Number Only">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-5 control-label">Weightage:  <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="termName" name="termName" class="form-control" type="text" pattern="[a-zA-Z0-9\s]+" required title = "Charecter And atleast One Number Only">
											</div>
										</div> -->
									</div>
									<!-- <div class="col-md-6 col-md-offset-3"> -->	
									<div id = "answerAndWeitage">
			                            <section class="panel">
			                                <header class="panel-heading">
			                                    <div class="panel-actions">
			                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
			                                    </div>
			
			                                    <h2 class="panel-title">Answer Weightage Mapping </h2>
			                                </header>
			                                <div class="panel-body">
			                                    <table class="table table-bordered table-striped mb-none" id="answerTable">
			                                        <thead>
			                                            <tr>
			                                                <th>Answer</th>
			                                                <th>Weightage</th>
			                                                <th>Add</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody id = "addNewRowsBody">
			                                            <tr>
			                                                <td><input type="text" class="form-control" name="answer" id="answer0" placeholder="" ></td>
			                                                <td><input type="text" class="form-control" id="weightage0" name="weightage" placeholder="" ></td>
			                                                <td><a class="mb-xs mt-xs mr-xs modal-basic btn btn-info" href="javascript:addNewRows()" id="addrow" >Add</a></td>
			                                            </tr>
			                                            
			                                        </tbody>
			                                    </table>
			                                </div> 
			                            </section>
									 </div> 
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
					  <div class="row">	
					<%-- <c:choose>
						<c:when test="${termList eq null || empty termList}">
							No Term Found
						</c:when>
						<c:otherwise> --%>
					
							<form name="editTerm" id="editTerm" action="editTerm.html" method="post">
							<div class="col-md-12">
									
									<input type="hidden" name="saveId" id="saveId">
		                            <section class="panel">
									<header class="panel-heading">
										<h2 class="panel-title">Existing Question Answer</h2>
									</header>
									<div class="panel-body">
										<%-- <c:forEach var="terms" items="${termList}" varStatus="i">
											<input type="hidden" name="termCode" value="${courseType.courseTypeName}">
										</c:forEach> --%>
										<table  id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
											<thead>
												<tr>
		                                            
													<th>Question</th>
													<th>Answer Type</th>
													<!-- <th>Actions</th> -->
												</tr>
											</thead>
											<tbody>
											<c:forEach var="question" items="${questionList}" varStatus="i">
												<tr class="gradeX">
													<td>
														<input type="hidden" name="questionId${i.index}" value="${question.questionId}">
														${question.question}
													</td>
													<td>
														${question.inputType}
													</td>
													
													<%-- <td class="actions">
														<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showTermDetails('${terms.course.courseName}','${terms.termName}','${i.index}')"><i class="fa fa-pencil"></i></a>
														<a href="inactiveTerm.html?termCode=${terms.termCode}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
													</td> --%>
												</tr>
											</c:forEach>
											</tbody>
										</table>
									</div>
									</section>
									
								</div>
								</form>
								</div>
								<!-- popup Window code -->
							<%--	<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
									<section class="panel">
										<header class="panel-heading">
											<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "programTermDetails">
												<thead>
													<tr>
														<th>Program Name</th>
														<th>Term</th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg" style="display: none">
												<span></span>	
											</div>
										</div>
										
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateTerms" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
								</form>
							
						</c:otherwise>
					</c:choose> 
					</div> --%>
							
						

						

						



					
					<!-- end: page -->
 				<!-- 	<div id="dialog" class="modal-block mfp-hide">
						<section class="panel">
							<header class="panel-heading">
								<h2 class="panel-title">Are you sure?</h2>
							</header>
							<div class="panel-body">
								<div class="modal-wrapper">
									<div class="modal-text">
										<p>Are you sure that you want to delete this row?</p>
									</div>
								</div>
							</div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button id="dialogConfirm" class="btn btn-primary">Confirm</button>
										<button id="dialogCancel" class="btn btn-default">Cancel</button>
									</div>
								</div>
							</footer>
						</section>
					</div>
					 -->
					
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/survey/configureQuestionAnswer.js"></script>
<!-- <script src="/icam/js/academics/createTerm.js"></script>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script> -->
<script type="text/javascript">

function hideAddButton(){
	var selectedValue = document.getElementById('inputType').value;
//	alert("selectedValue===="+selectedValue);
	if(selectedValue != null){
		if(selectedValue=='text' || selectedValue =='textarea'){
			document.getElementById('answerAndWeitage').style.display = 'none';
			//document.getElementById('answer0').removeAttribute("required",required);
			//document.getElementById('weightage0').removeAttribute("required",required);
			//document.getElementById('answer0').style.display = 'none';
		}else{
			document.getElementById('answerAndWeitage').style.display = 'block';
			document.getElementById('answer0').setAttribute("required",required);
			document.getElementById('weightage0').setAttribute("required",required);
			//document.getElementById('delete0').style.display = 'block';
			//document.getElementById('answer0').style.display = 'block';
		}
	}
	
}

</script>
</body>
</html>