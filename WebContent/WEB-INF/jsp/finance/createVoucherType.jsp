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
<title>Create Voucher Type</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }/* .mb-md{
       	   display: none;
       } */
</style>
</head>
<body>

	<header class="page-header">
		<h2>Create Voucher Type</h2>
	</header>
	<div class="content-padding">
		<c:if test="${submitResponse ne null}">
			<div class="alert alert-success"  >
				<strong>${submitResponse}</strong>	
			</div>
		</c:if>			
		 <div class="alert alert-danger" id="javascriptmsg1" style="display: none">
			<span> </span>
		</div>			
		
		<div class="row">
						<div class="col-md-12">
						 <form:form name="" action="submitVoucherType.html" method="POST" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Voucher Type</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-6 col-md-offset-3">
                                            <div class="form-group">
                                                <label class="control-label">Voucher Type Name </label>
                                                <input type="text" name="voucherTypeName" id="voucherTypeName" class="form-control" onblur=""  placeholder="e.g: Journal">
                                            </div> 
                                        </div>
                                        <div class="col-md-6 col-md-offset-3">
                                        	<label class="control-label">Configuration</label>
                                        	<table class="table table-bordered table-striped mb-none">
                                        		<thead>
													<tr>
														<th>Property Name</th>
			                                            <th>Check</th>
			                                            <th>Property Name</th>
			                                            <th>Check</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Department</td>
														<td><input type="checkbox" id="department" name="department"></td>
														<td>Income/Expence</td>
														<td><input type="checkbox" id="incExp" name="incExp"></td>
													</tr>
													<tr>
														<td>Ticket No.</td>
														<td><input type="checkbox" id="ticketNo" name="ticketNo"></td>
														<td>Multiple Debit Ledger</td>
														<td><input type="checkbox" id="multiDebit" name="multiDebit"></td>
													</tr>
												</tbody>
                                        	</table>
                                        	
                                        </div>
                                    </div>                                             
									
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" onclick="">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
								
                            </form:form>
                            </div>
						</div>
						
						<div class="col-md-12">						  
							 <form:form id="UpdateVoucherType" name="UpdateVoucherType" action="updateVoucherType.html" method="POST"> 
	 							<input type="hidden" id="saveId" name="saveId">	
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Voucher Type List </h2>
							</header>
							<div class="panel-body">
								<div id="oldLedgerName">
										
									</div>
								
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Voucher Type Name</th>
                                            <th>Department</th>
                                            <th>Income/Expence</th>
											<th>Ticket No.</th>
                                            <th>Multiple Debit</th>
                                            <th>Actions</th> 
										</tr>
									</thead>
									<tbody>
										<c:forEach var="vt" items="${voucherTypeList}" varStatus="i">
											<tr>
												
												<td>
													<input type="hidden" name="voucherTypeCode${i.index}" id="voucherTypeCode${i.index}" class="form-control" value = "${vt.voucherTypeCode}">
													${vt.voucherTypeName}
												</td>
												<td>
													<c:choose>
														<c:when test="${vt.department eq true}">
															<input type="checkbox" checked="checked" disabled="disabled">
														</c:when>
														<c:otherwise>
															<input type="checkbox" disabled="disabled">
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${vt.incExp eq true}">
															<input type="checkbox" checked="checked" disabled="disabled">
														</c:when>
														<c:otherwise>
															<input type="checkbox" disabled="disabled">
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${vt.ticketNo eq true}">
															<input type="checkbox" checked="checked" disabled="disabled">
														</c:when>
														<c:otherwise>
															<input type="checkbox" disabled="disabled">
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${vt.multipleDebitLedger eq true}">
															<input type="checkbox" checked="checked" disabled="disabled">
														</c:when>
														<c:otherwise>
															<input type="checkbox" disabled="disabled">
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<button type="button" id="edit${i.index}" class="btn btn-primary" onClick="functionEditable('${i.index}')">Edit </button>
													<button type="submit" id="update${i.index}" class="btn btn-success" style="display: none">Update</button>
													<button type="button" id="cancel${i.index}" class="btn btn-danger" style="display: none" onClick="cancelEditable('${i.index}');">Cancel</button>
                                                    
                                            	</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
							</div>
						</section>
						</form:form> 
						</div>
						
					<c:if test="${submitResponse ne null}">				
						<c:if test="${submitResponse eq 'Insertion successful'}">
							<div class="successbox" id="successbox" style="visibility:visible;">
								<span id="successmsg" style="visibility:visible;">Successfully Created</span>	
							</div>
						</c:if>
						<c:if test="${submitResponse eq 'Insertion Failed'}">
							<div class="errorbox" id="errorbox" style="visibility:visible;">
								<span id="errormsg" style="visibility:visible;">Creation Failed</span>	
							</div>
						</c:if>		
					</c:if>
					<c:if test="${updateResponse ne null}">				
						<c:if test="${updateResponse eq 'Success'}">
							<div class="successbox" id="successbox" style="visibility:visible;">
								<span id="successmsg" style="visibility:visible;">Successfully Updated</span>	
							</div>
						</c:if>
						<c:if test="${updateResponse eq 'Fail'}">
							<div class="errorbox" id="errorbox" style="visibility:visible;">
								<span id="errormsg" style="visibility:visible;">Updatation Failed</span>	
							</div>
						</c:if>		
					</c:if>	
					</div>	



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/finance/groupAndLedgerCreate.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/nullValidation.js"></script>
<script>
	function functionEditable(rowId){
		document.getElementById("saveId").value=rowId;
		document.getElementById("ledgerSpan"+rowId).style.display="none"; /* added by sourav.bhadra on 22-11-2017 */
		document.getElementById("ledgerName"+rowId).style.display="block"; /* modified by sourav.bhadra on 22-11-2017 */
		document.getElementById("openingBal"+rowId).removeAttribute("readonly");
		document.getElementById("edit"+rowId).style.display="none";
		document.getElementById("update"+rowId).style.display="block";
		document.getElementById("cancel"+rowId).style.display="block";
	}
	
	/* added by sourav.bhadra on 21-08-2017 for cancel button */
	function cancelEditable(rowId){
		document.getElementById("ledgerName"+rowId).style.display="none"; /* modified by sourav.bhadra on 22-11-2017 */
		document.getElementById("ledgerSpan"+rowId).style.display="block"; /* added by sourav.bhadra on 22-11-2017 */
		document.getElementById("openingBal"+rowId).setAttribute("readonly", "readonly");
		document.getElementById("edit"+rowId).style.display="block";
		document.getElementById("update"+rowId).style.display="none";
		document.getElementById("cancel"+rowId).style.display="none";
	}

</script>
</body>
</html>   