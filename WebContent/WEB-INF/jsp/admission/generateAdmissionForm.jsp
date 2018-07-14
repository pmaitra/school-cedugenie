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
<title></title>
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
                            <section class="panel">
                             <form:form method="POST" name="printForm" action="print.html">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                                    </div>

                                    <h2 class="panel-title">Generate Admission Form</h2>										
                                </header>
                               <c:choose>
								<c:when test="${admissionFormLastFormId[0].status ne null}">
									<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
										<span id="infomsg">${admissionFormLastFormId[0].status}</span>	
									</div>
								</c:when>
								<c:otherwise>
                                <c:forEach var="listOfAdmission" items="${admissionFormLastFormId}">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label class="col-md-5 control-label"><b>Admission Year</b></label>
                                        <label class="col-md-7 control-label">${listOfAdmission.admissionFormYear}</label>
                                        <input type="hidden" name="admissionFormYear" value="<c:out value="${listOfAdmission.admissionFormYear}"></c:out>" />
                                    </div>
                                   <%--  <div class="form-group">
                                        <label class="col-md-5 control-label"><b>Class</b></label>
                                        <label class="col-md-7 control-label">${listOfAdmission.courseClass}</label>
                                        <input type="hidden" name="courseClass" value="<c:out value="${listOfAdmission.courseClass}"></c:out>"/>
                                    </div> --%>
                                    <div class="form-group">
                                        <label class="col-md-5 control-label"><b>Last Form ID</b></label>
                                        <label class="col-md-7 control-label">${listOfAdmission.admissionDriveCode}</label>
                                        <input type="hidden" name="admissionDriveCode" value="<c:out value="${listOfAdmission.admissionDriveCode}"></c:out>" />
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-5 control-label"><b>Drive Name</b></label>
                                        <label class="col-md-7 control-label">${listOfAdmission.admissionDriveName}</label>
                                        <input type="hidden" name="admissionDriveName" value="<c:out value="${listOfAdmission.admissionDriveName}"></c:out>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-5 control-label"><b>Program Name</b></label>
                                        <label class="col-md-7 control-label">${listOfAdmission.courseName}</label>
                                        <input type="hidden" name="courseName" value="<c:out value="${listOfAdmission.courseName}"></c:out>"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-5 control-label"><b>Course Type</b></label>
                                        <label class="col-md-7 control-label">${listOfAdmission.courseType}</label>
                                        <input type="hidden" name="courseType" value="<c:out value="${listOfAdmission.courseType}"></c:out>"/>
                                    </div>                                    
                                    <div class="form-group">
                                        <label class="col-md-5 control-label"><b>Last Date of Form Submission</b></label>
                                        <label class="col-md-4 control-label">${listOfAdmission.admissionFormSubmissionLastDate}</label>
                                     	 <input type="hidden" name="admissionFormSubmissionLastDate" value="<c:out value="${listOfAdmission.admissionFormSubmissionLastDate}"/>" />
                                           
                                        
                                    </div>
                                   <div class="form-group">
                                        <label class="col-md-5 control-label"><b>No.Of Copies</b><span class="required" aria-required="true">*</span></label>
                                        <label class="col-md-4 control-label"><input type="text" class="form-control" id="numberOfprint" name="numberOfprint" placeholder="" pattern="^[1-9]\d*$" required></label>
                                   </div> 
                                </div>
                                </c:forEach>
                                <footer style="display: block;" class="panel-footer">
                                    <button type="submit" class="btn btn-primary"  >Print </button>
                                    
                                </footer>
                               </c:otherwise>
                               </c:choose>
                                </form:form>
                            </section>
						</div>
                        
					</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/admission/generateAdmissionForm.js"></script>
</body>
</html>