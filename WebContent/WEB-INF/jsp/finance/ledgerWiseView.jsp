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
<title>Ledger Wise Details</title>
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
			<div class="col-md-4" id="passbookTable">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Date & Ledger</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class="row">
							<div class="col-md-12">
		                  		<div class="form-group">
		                    		<label class="control-label">From Date: </label>
	                        		<div class="input-group">
		                            	<span class="input-group-addon">
	                     					<i class="fa fa-calendar"></i>
		                                </span>
		                                <input type="text" class="form-control" name="fromDate" id="fromDate" data-plugin-datepicker=""/>
		                            </div>                            
		                       	</div>   
		               			<div class="form-group">
		                           <label class="control-label">To Date: </label>
		                           <div class="input-group">
		                               <span class="input-group-addon">
		                                   <i class="fa fa-calendar"></i>
		                               </span>
		                               <input type="text" class="form-control" name="toDate" id="toDate" data-plugin-datepicker=""/>
		                           </div>
		                      	</div>
		                      	<div class="form-group">
		                           <label class="control-label">Ledger: </label>
		                           <select name="ledger" id="ledger" class="form-control" >
										<option value="">Select..</option>
										<c:if test="${ledgerList ne null}">
											<c:forEach var="ledger" items="${ledgerList}">
												<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
											</c:forEach>
										</c:if>
									</select>
		                      	</div>
		                  	</div>
	                 	</div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button type="button" id="get" class="btn btn-primary submitbtn">Get Details</button>
					</footer>
				</section>
			</div>
			<div class="col-md-8" id="dayBookDetailsDiv" style="display: none">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Ledger Wise Details</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div id="tbDiv"></div>
					</div>
				</section>
			</div>
		</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/finance/ledgerWiseView.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/nullValidation.js"></script>
</body>
</html>