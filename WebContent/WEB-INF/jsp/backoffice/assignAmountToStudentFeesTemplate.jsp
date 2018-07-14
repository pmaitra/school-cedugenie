<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Assign Fees To Template</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>

		<header class="page-header">
			<h2>Assign Fees To Template For A Standard</h2>
		</header>
		<div class="content-padding">
		<div class="row">
			<form name="form1" action="submitAmountInStudentFeesTemplate.html" method="POST">
				<div class="col-md-4">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>

							<h2 class="panel-title">Template And Standard Details</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                            <div class="form-group">
                                 <label class="control-label">Template Name:<span class="required" aria-required="true">*</span></label>
                                 <select class="form-control" id ="templateName" name="templateName">
                                     <option value="">Select</option>
	                                     <c:forEach var="templates" items="${allFeesTemplates}">
											<option value="${templates.studentFeesTemplateCode}">${templates.studentFeesTemplateName}</option>
										 </c:forEach>
                                 </select>
                             </div> 
                             <div class="form-group">
                                 <label class="control-label">Standard Name:<span class="required" aria-required="true">*</span></label>
                                 <select class="form-control" id ="unMappedCourseName" name="unMappedCourseName">
                                     <option value="">Select</option>
                                     <c:forEach var="courses" items="${allUnmappedCourses}" >
										<option value="${courses.courseCode}">${courses.courseName}</option>
									</c:forEach>
                                 </select>
                                 <input type="hidden" name = "socialCategorySize" id = "socialCategorySize" value = "${socialCategoryList.size()}">
                             </div> 
                         </div>
					</section>
	            </div>
					<div class="col-md-8" style="display: none;" id="assignAmountTableDiv">
                        <section class="panel">
                             <header class="panel-heading">
                                 <div class="panel-actions">
                                     <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                 </div>
                                 <h2 class="panel-title">Assign Amount</h2>
                             </header>
                             <div class="panel-body">
                                 <table class="table table-bordered table-striped mb-none" id="amountTable">
                                     <thead>
                                         <tr>
                                         	<th></th>
                                             <c:forEach var= "socialCategory" items="${socialCategoryList}">
												<th>
													${socialCategory.socialCategoryName}
													<input type="hidden" name = "socialCategoryName" id = "socialCategoryName" value = "${socialCategory.socialCategoryCode}">
												</th>
											</c:forEach>
	                                     </tr>
                                     </thead>
                                     <tbody id="amountTableBody">   
                                     	<tr style="background:#eee">
											<th>Total</th>
											<c:forEach var= "socialCategory" items="${socialCategoryList}">
												<th>
													<input type="text" id="${socialCategory.socialCategoryCode}Total" value = "0.00" readonly="readonly" class="textfield2 form-control text-right">
												</th>
											</c:forEach>
										</tr>
                                     </tbody>
                                 </table>
                             </div>
                             <footer style="display: block;" class="panel-footer">
                                 <button class="btn btn-primary" type="submit" id="submit" name="submit">Submit </button>
                                 <button type="reset" class="btn btn-default">Reset</button>
                             </footer>
                             <input type="hidden" id="status" name="status" value="INSERT">
                         </section>
					</div>
                </form>
			</div>
		</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/assignAmountToStudentFeesTemplate.js"></script>
</body>
</html>