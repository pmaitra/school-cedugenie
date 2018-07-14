<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Security Deposit</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>Security Deposit</h2>
	</header>
	<div class="content-padding">
	<c:choose>
		<c:when test="${studentList == null}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">Students Not Found</span>
			</div>
		</c:when>
	<c:otherwise>
		<form:form  name="" action="updateSecurityDeposit.html" method="POST">
			<section role="main" class="content-body">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>						
								<h2 class="panel-title">Security Deposit</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Roll Number</th>
											<th>Student Name</th>
											<th>Security Deposit</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="resource" items="${studentList}" varStatus="i">
											<tr>
												<td>
													<input type="hidden" name="userId" value="${resource.userId}" />
													${resource.userId}
												</td>
												<td>
													<input type="hidden" value="${resource.name}" />
													${resource.name}
												</td>
												<td>
													<input type="text" name="${resource.userId}" value="${resource.securityDeposit}" class="form-control textfield" />
												</td>
											</tr>
										</c:forEach>				
									</tbody>									
								</table>
							</div>
							<footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" type="submit" value="SUBMIT" onclick="return validate();">Submit </button>
								<button type="reset" class="btn btn-default">Reset</button>
							</footer>
						</section>			
					</section>			
		</form:form>
	</c:otherwise>
</c:choose>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/finance/securityDeposit.js"></script>
<script type="text/javascript" src="/icam/js/finance/nullValidation.js"></script>
</body>
</html>