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
       }.mb-md{
       	   display: none;
       }
</style>
</head>
<body>

	<c:choose>
		<c:when test="${feesList eq null || empty feesList || socialCategoryList eq null || empty socialCategoryList || standardList eq null || empty standardList}">
			<div class="alert alert-danger">
				<c:if test="${feesList eq null || empty feesList}">
					<strong>Fees Not Created Yet.</strong>	
				</c:if>
				<c:if test="${socialCategoryList eq null || empty socialCategoryList}">
					<strong>Social Category Not Created Yet.</strong>	
				</c:if>
				<c:if test="${standardList eq null || empty standardList}">
					<strong>Standard Not Created Yet.</strong>	
				</c:if>
			</div>
		</c:when>
	<c:otherwise>

	<div class="row">
		<form name="feesTemplateForm" id="feesTemplateForm" method="post" action="addFeesTemplate.html">
			<div class="col-md-3">
				<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>

							<h2 class="panel-title">Create Fees Template</h2>										
						</header>
						<div style="display: block;" class="panel-body">
                            <div class="form-group">
                                 <label class="control-label">Template Name</label>
                                 <input type = "text" class = "form-control" id="templateName" name="templateName" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Template name can consist of alphabetical characters and spaces only" required>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">Standard</label>
                                 <select class = "form-control" id="standard" name="standard" title="Please select at least one standard" required>
                               		<option value="">Select...</option>
                                       	<c:forEach var="standard" items="${standardList}" varStatus="i">
											<option value="${standard.standardCode}">${standard.standardName}</option>
										</c:forEach>
                                 </select>
                             </div>
                             <div class="form-group">
                                 <label class="control-label">Applied</label>
                                 <div style="margin-top: 5px;" class="form-group">
                                      <label class="radio-inline radio-primary">
                                          <input type="radio" value="TRUE" id="applied" name="applied" checked="checked"> Yes 
                                      </label>
                                      <label class="radio-inline radio-primary"> 
                                          <input type="radio" value="FALSE" id="applied" name="applied"> No 
                                      </label>
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
                         <table class="table table-bordered table-striped mb-none">
                             <thead>
                                 <tr>
                                     <th>
                                     	FEES
                               		 </th>
                                     <c:forEach var="socialCategory" items="${socialCategoryList}" varStatus="i">
                                     <th>
										${socialCategory.socialCategoryName}
										<input type="hidden" name="socialCategoryNames" value="${socialCategory.socialCategoryCode}">	
									 </th>
									 </c:forEach>
                                 </tr>
                             </thead>
                             <tbody>   
                             	<c:forEach var="fees" items="${feesList}" varStatus="i">                                         
                                 <tr>
                                     <td>
                                     	${fees.feesName}
                                        <input type="hidden" name="feesNames" value="${fees.feesCode}">
                                     </td>
                                     <c:forEach var="socialCategory" items="${socialCategoryList}" varStatus="i">
                                     <td>
                                     	<input type="text" id="feesname" name="${fees.feesCode}${socialCategory.socialCategoryCode}" value="0.00" class="form-control textfield2" required />
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
	                       <button class="btn btn-primary" type="submit" onclick="return validate();">Submit </button>
	                       <button type="reset" class="btn btn-default">Reset</button>
                    </footer>
                    </section>
					</div>
                </form>
			</div>
	</c:otherwise>
	</c:choose>
					


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/getElementsByClassName.js"></script>
<script type="text/javascript" src="/cedugenie/js/backoffice/createFeesTemplate.js"></script> 
</body>
</html>