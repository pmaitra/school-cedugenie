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
<title>Edit assigned fees to template</title>
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

		
		<div class="row">
			<form name="form1" action="editAmountsInStudentFeesTemplate.html" method="POST">
				<div class="col-md-4">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>

							<h2 class="panel-title">Template And Course</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                            <div class="form-group">
                            	<label class="control-label">Template Name:</label>
                                <input type = "text" class="form-control" value="${templateName}" readonly>
								<input type="hidden" id="studentFeesTemplateCode" name="studentFeesTemplateCode" value="${templateCode}"/>
                             </div>
                             <div class="form-group">
                                <label class="control-label">Course Name:</label>
                                <input type = "text" class="form-control" value = "${courseName}" readonly>
								<input type="hidden" id="courseName" name="course.courseName" value="${courseName}"/>
								<input type="hidden" name = "socialCategorySize" id = "socialCategorySize" value = "${socialCategoryList.size()}">
                             </div> 
                         </div>
					</section>
	            </div>
					<div class="col-md-8" style="display: block;" id="assignAmountTableDiv">
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
													<input type="hidden" name = "socialCategoryName" id = "socialCategoryName" value = "${socialCategory.socialCategoryName}">
												</th>
											</c:forEach>
	                                     </tr>
                                     </thead>
                                     <tbody id="amountTableBody">  
                                     	<c:forEach var= "feesCategory" items="${studentFeesTemplateAmountDetailsList}">
											<tr>
												<th>
													${feesCategory.feesCategoryName}
													<input type="hidden" name="feesHead" id="feesCategoryName" value="${feesCategory.feesCategoryName}"/>
												</th>
												<c:forEach var= "socCategory" items="${feesCategory.socialCategoryList}">
													<td>
														<input class="textfield1 form-control" type="text" style="text-align: right" name="${feesCategory.feesCategoryName}##${socCategory.socialCategoryName}" value="${socCategory.amount}" disabled="disabled" onblur="setZero(this,'${socCategory.socialCategoryName}Total');" onfocus="removeZero(this);"/>
													</td>
												</c:forEach>
											</tr>
										</c:forEach>
                                     	<tr style="background:#eee">
											<th>Total</th>
											<c:forEach var= "socialCategory" items="${socialCategoryList}">
												<th>
													<input type="text" id="${socialCategory.socialCategoryName}Total" value = "0.00" readonly="readonly" class="textfield2 form-control text-right" >
												</th>							
											</c:forEach>
										</tr>
                                     </tbody>
                                 </table>
                             </div>
                             <footer style="display: block;" class="panel-footer">
                             	<button type="button" class="btn btn-primary" id="editButton" onclick="makepageEditbale();">Edit</button>
                                <button class="btn btn-primary" type="submit" id="submit" name="submit" style="display:none">Submit </button>
                                <button type="reset" class="btn btn-default" id="reset" style="display:none">Reset</button>
                             </footer>
                         </section>
					</div>
                </form>
			</div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/editAssignAmountToStudentFeesTemplate.js"></script>
</body>
</html>