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
<title>Edit Fees Template</title>
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
		<form name="feesTemplateForm" id="feesTemplateForm" method="POST" action="editFeesTemplate.html">
			<div class="col-md-3">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Edit Fees Template</h2>										
					</header>
					<div style="display: block;" class="panel-body" id="feesDetail">
                       <div class="form-group">
                            <label class="control-label">Template Name</label>
                            <input type = "text" class = "form-control" id="templateName" name="templateName" value="${feesTemplateDetails.templateName}" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Template name can consist of alphabetical characters and spaces only" required >
                       	 	<input type="hidden" id="templateCode" name="templateCode" value="${feesTemplateDetails.templateCode}" />
	 					 	<input type="hidden" id="serialId" name="serialId" value="${feesTemplateDetails.serialId}" />
                        </div> 
                        <div class="form-group">
                            <label class="control-label">Standard</label>
                            <select class = "form-control" id="standard" name="standard" title="Please select at least one standard" required>
                          		<option value="">Select...</option>
                                  	<c:forEach var="standard" items="${standardList}" varStatus="i">
										<c:choose>
											<c:when test="${feesTemplateDetails.standard eq standard.standardCode}">
												<option value="${standard.standardCode}" selected="selected">${standard.standardName}</option>
											</c:when>
											<c:otherwise>
												<option value="${standard.standardCode}">${standard.standardName}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
                            </select>
                        </div> 
                        <div class="form-group">
                            <label class="control-label">Applied</label>
                            <div style="margin-top: 5px;" class="form-group">
                              	<c:choose>
									<c:when test="${feesTemplateDetails.applied eq true}">
										<input type="radio" name="applied" value="TRUE" checked="checked" />Yes &nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="applied" value="FALSE" /> No
									</c:when>
									<c:otherwise>
										<input type="radio" name="applied" value="TRUE" />Yes &nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="applied" value="FALSE" checked="checked" /> No
									</c:otherwise>
								</c:choose>
                            </div>
                        </div>                 
					</div>
				</section>
			</div>
			<div class="col-md-9" >
                 <section class="panel">
                     <header class="panel-heading">
                         <div class="panel-actions">
                             <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                         </div>

                         <h2 class="panel-title">View Fees Template</h2>
                     </header>
                     <div class="panel-body">
                         <table class="table table-bordered table-striped mb-none" id="templateCharge">
                             <thead>
                                 <tr>
                                     <th>
                                     	FEES
                               		 </th>
                                     <c:forEach var="socialCategory" items="${feesTemplateDetails.feesList[0].socialCategoryList}" varStatus="i">
										<th>
											${socialCategory.socialCategoryName}
											<input type="hidden" name="socialCategoryNames" value="${socialCategory.socialCategoryCode}">
										</th>
									</c:forEach>
                                 </tr>
                             </thead>
                             <tbody>   
                             	<c:forEach var="fees" items="${feesTemplateDetails.feesList}" varStatus="i">                                         
                                 <tr>                                                
                                     <td>
                                     	${fees.feesName}
                                        <input type="hidden" name="feesNames" value="${fees.feesCode}">
                                     </td>
                                     <c:forEach var="socialCategory" items="${fees.socialCategoryList}" varStatus="i">
										<td>
											<input type="text" id="" name="${fees.feesCode}${socialCategory.socialCategoryCode}" value="${socialCategory.amount}" class="form-control" required/>
										</td>
									</c:forEach>
                               	 </tr>
                                 </c:forEach>
                             </tbody>
                         </table>
                     </div>
                     <c:forEach var="feesTemplate" items="${feesTemplateList}" varStatus="i">
						<input type="hidden" name="oldFeesTemplateName" value="${feesTemplate.templateCode}">
					 </c:forEach>
					 <footer style="display: block;" class="panel-footer">
	                       	<button class="btn btn-primary" type="submit" onclick="return validate();" value="Submit">Submit</button>
	                 		<button class="btn btn-warning" type="button" onclick="window.location='getFeesTemplateList.html'">Back</button>
	                 </footer>
                     </section>
					 </div>
                 </form>
			 </div>
			
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/editFeesTemplate.js"></script>
</body>
</html>