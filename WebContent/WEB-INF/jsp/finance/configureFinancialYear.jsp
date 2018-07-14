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
	<div class="row">
		<div class="col-md-5">
		  	<form:form method="POST" id="configureFinancialYear" name="configureFinancialYear" action="createNewFinancialYear.html" >
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>

						<h2 class="panel-title">Create New Designation</h2>										
					</header>
					<div style="display: block;" class="panel-body">                               
                    	<c:forEach var="financialYear" items="${financialYearList}">
							<c:if test="${financialYear.yearStatus eq 'CURRENT'}">
								<div class="form-group">
                                     <label class="control-label">Current Session</label>
                                     <input type="hidden" name="financialYearCode" value="${financialYear.financialYearCode}">
                                     ${financialYear.financialYearName}
                                 </div>
                                 
                                 <div class="form-group">
                                     <label class="control-label">Start Date</label>		                                        
                                     <input class="form-control" name="sessionStartDate" id="sessionStartDate"  value="${financialYear.sessionStartDate}">
                                 </div>
                                 
                                 <div class="form-group">
                                     <label class="control-label">End Date</label>
                                     <input class="form-control" name="sessionEndDate" id="sessionEndDate"  value="${financialYear.sessionEndDate}">
                                 </div> 										
								<c:set var="i" value="0" scope="page" />
								<c:if test="${financialYear.sessionStartDate ne null && financialYear.sessionEndDate ne null}">
									<c:set var="i" value="1" scope="page" />
								</c:if>
							</c:if>
						</c:forEach>
						<c:forEach var="financialYear" items="${financialYearList}">
							<c:if test="${financialYear.yearStatus eq 'NEXT'}">											
								<label class="control-label">Next Financial Session Is <b>${financialYear.financialYearName}</b></label>
							</c:if>
						</c:forEach>
                                        
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" >Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
        	</form:form>
		</div>	
		<div class="btnsarea01">
		<div class="infomsgbox" id="infomsgbox0" style="visibility: visible;" >
			<span id="infomsg0">Session Start And End Date Once Entered Cannot Be Edited</span>	
		</div>
		<c:if test="${updateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${updateStatus}</span>	
			</div>
		</c:if>
	</div>
	</div>	
					



<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/erp/designation.editable.js"></script>
<script type="text/javascript" src="/icam/js/finance/configureFinancialYear.js"></script>
</body>
</html>