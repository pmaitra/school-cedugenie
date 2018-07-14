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
<script type="text/javascript" src="/cedugenie/js/admission/editSubmittedInterviewResult.js"></script>
</head>
<body>
        <form:form method="POST" id="aircontent" name="aircontent"  action="newAddInterviewResult.html">
	                                <div class="row well well-sm">
	                                   <div class="row">
	                                    <div class="col-md-3">                                   
	                                    	<div class="form-group">
	                                            <label class="control-label">Academic Year</label>
	                                            <input type="text" class="form-control" value="${StreamClassYearCourse.admissionFormYear}" readonly>
	                                        </div> 
	                                        <div class="form-group">
	                                            <label class="control-label">Course Name</label>
	                                            <input type="text" class="form-control"  value="${StreamClassYearCourse.courseName}" readonly>
	                                        </div>                                          
	                                        
	                                    </div>
	                                    <div class="col-md-3">
	                                        <div class="form-group">
	                                            <label class="control-label">Course Type</label>
	                                            <input type="text" class="form-control"  value="${StreamClassYearCourse.courseType}" readonly>
	                                        </div> 
	                                        
	                                    </div>
	                                    <div class="col-md-3"> 
	                                       <div class="form-group">
	                                            <label class="control-label">Form ID</label>
	              								<input type="text" class="form-control"  value="${InterviewResult.formId}" readonly>
	              								<input type="hidden" name="formId" value="<c:out value="${InterviewResult.formId}"/>" id="strFormID" />
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Class</label>
	                                            <input type="hidden" name="courseClass" value="<c:out value="${InterviewResult.courseClass}"/>" id="strCourseClass"/>
												<input type="hidden" name="admissionYear" id="admissionYear" value="<c:out value="${InterviewResult.admissionYear}"/>" />
												<input type="hidden" name="formName" id="formName" value="<c:out value="${InterviewResult.formName}"/>" />
	                                        </div>                                          
	                                    </div>
	                                    <div class="col-md-3">
	                                        <div class="form-group">
	                                            <label class="control-label">Name</label>
	                                            <input type="text" class="form-control" name="name" id="name" value =" ${InterviewResult.candidateFirstName} ${InterviewResult.candidateMiddleName} ${InterviewResult.candidateLastName}" readonly="readonly">
	                                        </div>
	                                    </div>
	                                    </div>
	                                    <br>
	                                    <div class="row">                                    
	                                        <div class="col-md-4">
	                                            <div class="form-group">
	                                                <label class="control-label">Comment</label>
	                                                <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" id="strComment" name="comment" value="${InterviewResult.comment}"></textarea>
	                                            </div>
	                                            <div class="form-group">
	                                                <label class="control-label">Decision</label>
	                                                <div style="margin-top: 5px;" class="form-group">
	                                                    <label class="radio-inline radio-primary"> 
	                                                        <input type="radio"  disabled="disabled" id="strStatus0" name="formStatus" <c:if test="${InterviewResult.formStatus == 'SELECTED'}"> checked="checked"</c:if> value="SELECTED"/>SELECTED
	                                                    </label>
	                                                    <label class="radio-inline radio-primary"> 
	                                                        <input type="radio"  disabled="disabled" id="strStatus1" name="formStatus" <c:if test="${InterviewResult.formStatus == 'NOTSELECTED'}"> checked="checked"</c:if> value="NOTSELECTED"/>NOT SELECTED
	                                                    </label>
	                                                    <label class="radio-inline radio-primary"> 
	                                                        <input type="radio" disabled="disabled"  id="strStatus2" name="formStatus" <c:if test="${InterviewResult.formStatus == 'REVIEW'}"> checked="checked" </c:if> value="REVIEW"/>REVIEW
	                                                    </label>
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div class="col-md-8">
	                                            <table class="table table-bordered table-striped mb-none dataTable" id = "dataTable">
	                                                <thead>
	                                                    <tr>
	                                                        <th>Subject</th>
	                                                        <th>Marks</th>
	                                                        <th>Delete</th>
	                                                    </tr>
	                                                </thead>
	                                                <tbody>
	                                                		<c:forEach var="marks" items="${InterviewResult.marksList}">
														        <tr>
														            <td><INPUT type="text" readonly="readonly" class="form-control" name="subject" value="<c:out value="${marks.strSubjectName}"/>"/> </td>
																	<td><INPUT type="text" readonly="readonly" class="form-control" name="marks" value="<c:out value="${marks.intSubjectMarks}"/>" /> </td>
																	<td><a href="#" class="on-default" onclick="deleteRow(this);"><i class="fa fa-minus-square"></i></a></td>
														        </tr>
													       </c:forEach>
	                                                    <!-- <tr>
	                                                        <td><input type="text" class="form-control" name="subject"></td>
	                                                        <td><input type="text" class="form-control" name="marks"></td>
	                                                        <td><a href="#" class="on-default" onclick="deleteRow(this);"><i class="fa fa-minus-square"></i></a></td>
	                                                    </tr> -->
	                                                </tbody>
	                                                <tfoot>
	                                                    <tr>
	                                                        <td></td>
	                                                        <td></td>
	                                                        <td><button class="btn btn-xs btn-primary" type="button" id="AddRow" disabled="disabled" onclick="addRow();">Add</button></td>
	                                                    </tr>
	                                                </tfoot>
	                                            </table>                                                        
	                                        </div>
	                                    </div>
	                                    <div class="col-md-12">&nbsp;</div>
	                                    <div class="col-md-12">
	                                    <input type="hidden" name="control" value="editSubmitInterviewResult">
	                                    	<input type="button" value="Edit" class="btn btn-success"" onclick="editable();">
											<button class="btn btn-danger" type="submit" id="submitbutton" disabled="disabled">Submit</button>
										</div>
	                                </div>
	                             </form:form>



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

</body>
</html>