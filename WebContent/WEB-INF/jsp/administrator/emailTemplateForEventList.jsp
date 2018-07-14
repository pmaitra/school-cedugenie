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
<title>Email Event For Template</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .mb-md{
       	   display: none;
       }
</style>
</head>
<body>

		<header class="page-header">
			<h2> Template List</h2>
		</header>
				<div class="content-padding">
					<div class="row">
						 <c:choose>
							<c:when test="${emailEventTemplateList eq null || empty emailEventTemplateList}">
								<div class="btnsarea01" >
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">No Template is created Yet!!</span>	
									</div>
								</div>
							</c:when>
						<c:otherwise>
						<div class="col-md-12">
							<form name="editEmailTemplateForEvent" id="editEmailTemplateForEvent" action="editEmailTemplateForEvent.html" method="post">
								<input type="hidden" name="saveId" id="saveId">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										</div>
										<h2 class="panel-title">Existing Templates</h2>
									</header>
									<div class="panel-body" id="datatable-editable">
										<table class="table table-bordered table-striped mb-none"  id="datatable-tabletools">
											<thead>
												<tr>
													<th>Serial No.</th>
													<th>Email Subject</th>
													<th>Email Body</th>
													<th>Email Footer</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="template" items="${emailEventTemplateList}" varStatus="i" >
													<tr>
														<td>
															${i.index+1}
														</td>
														<td>
															<input type="hidden" id="emailSubject${i.index}" name="emailSubject${i.index}" value="${template.emailSubject}">
															${template.emailSubject}
														</td>
														<td>
															${template.emailBody}
														</td>
														<td>
															${template.emailFooter}
														</td>
														 <td class="actions">
														 		<%-- <input type="hidden" id="serialId${i.index}" name="serialId${i.index}" value="${template.serialId}"> --%>
																<input type="hidden" id="templateCode${i.index}" name="templateCode${i.index}" value="${template.templateCode}">
																<a href="editEmailTemplate.html?templateCode=${template.templateCode}" name = "pencil"><i class="fa fa-pencil"></i></a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</section>
							</form>
						</div>
					</c:otherwise>
					</c:choose>	
					</div>
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/academics/createStandard.editable.js"></script>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
</body>
</html>