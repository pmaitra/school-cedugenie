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
<title>Salary Break Up</title>
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
		<h2>Map Salary BreakUp With Ledger</h2>
	</header>
<div class="content-padding">
	<c:if test="${submitResponse eq 'Success'}">
		<div class="alert alert-success">
			<strong> Mapped Successfully !!!</strong>
		</div>
	</c:if>
	<c:if test="${submitResponse eq 'Fail'}">
		<div class="alert alert-danger">
			<strong>Failed To Map!!!</strong>
		</div>
	</c:if>
	<div class="row">
		<div class="col-md-5">
		  	<form:form name="salaryBreakUpLedgerMapping" id="salaryBreakUpLedgerMapping" action="insertSalaryBreakUpLedgerMapping.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
	
						<h2 class="panel-title">Map Salary BreakUp With Ledger</h2>										
					</header>
					<div style="display: block;" class="panel-body">
	                    <div class="form-group">
                            <label class="control-label">Salary Breakup</label>
                            <select class="form-control" id="salaryBreakUpCode" name="salaryBreakUpCode" required="required">
	                           <option value="">Please select</option>						
	                           <c:forEach var="breakup" items="${salaryBreakUpList}" >
									<option value="${breakup.salaryBreakUpCode}">${breakup.salaryBreakUpName}</option>
								</c:forEach> 
							</select>                                
                       </div>                 
                       										
                       <div class="form-group">
                            <label class="control-label">Ledger</label>
                            <select class="form-control" id="salaryBreakUpType" name="salaryBreakUpType" required="required">
	                           <option value="">Please select</option>						
	                           <c:forEach var="ledger" items="${ledgerList}" >
									<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
								</c:forEach> 
							</select>                                
                       </div>  
                     </div> 
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>											
					</footer>
				</section>
	     	 </form:form>
		</div>							
		 										
		<%-- <c:choose>
		<c:when test="${salaryBreakUpListForCreateUpdate eq null || empty salaryBreakUpListForCreateUpdate}">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Salary Head Found</span>	
				</div>
		</c:when>	
		<c:otherwise>	 --%>					
		<div class="col-md-7">	
			<input type="hidden" name="saveId" id="saveId">
                <section class="panel">
                     <header class="panel-heading">
                         <div class="panel-actions">
                             <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                         </div>

                         <h2 class="panel-title">Salary Breakup Ledger Mapping </h2>
                     </header>
                   <div class="panel-body">

                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                             <thead>
                                 <tr>
                                     <th>Salary BreakUp Name</th>
                                     <th>Ledger</th> 	
                                 </tr>
                             </thead>
                             <tbody>
                             	<c:forEach var="salaryBreakUp" items="${salaryBreakUpLedgerMappingList}">
	                                 <tr class="gradeX">
										<td>
											${salaryBreakUp.salaryBreakUpName}
										</td>	
										<td>
											${salaryBreakUp.salaryBreakUpDesc}
								        </td>													
									</tr>
                              	</c:forEach>
                             </tbody>
                         </table>
                     </div>
               </section>
		</div>
		<%-- </c:otherwise>
		</c:choose> --%>
	</div>	
</div>				



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/erp/salaryBreakUp.js"></script>
</body>
</html>