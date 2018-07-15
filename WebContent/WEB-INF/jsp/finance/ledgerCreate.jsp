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
       }/* .mb-md{
       	   display: none;
       } */
</style>
</head>
<body>

	<header class="page-header">
		<h2>Create Ledger</h2>
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
						 <form:form name="" action="createLedger.html" method="POST" >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Ledger</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Ledger Name </label>
                                                <input type="text" name="ledgerName" id="ledgerName" class="form-control" onblur="checkLedgerName(this);"  placeholder="">
                                            </div> 
                                            <!-- modified by sourav.bhadra on 14-08-2017 to remove parent ledgers -->
                                            <div class="form-group">
                                                <label class="control-label">Opening Balance</label>                                                
                                                <input type="text" class="form-control" id="openingBal" name="openingBal" placeholder="" pattern = "^[0-9]\d*$"  title="Enter valid opening Balance" required>
                                            </div>
                                            <!-- added by sourav.bhadra on 24-04-2018 for -->
                                            <div class="form-group">
	                                          	<label class="control-label">Ledger Type</label>
		                                        <select class="form-control" id="ledgerType" name="ledgerType">
		                                        	<option value="">Select</option>
		                                        	<option value="CASH">Cash</option>
		                                        	<option value="BANK">Bank</option>
		                                        </select>
	                                      	</div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Parent Group</label>
                                                <select class="form-control" name="parentGroupCode" id="parentGroupCode">
                                                    <option value="">Select...</option>
                                                    <c:if test="${total ne null}">
													<c:forEach var="gfl" items="${total}">
														<option value="${gfl.childCode}">${gfl.childName}</option>
													</c:forEach>
												</c:if>
                                                </select>
                                            </div>
                                            <div class="form-group">
	                                          	<label class="control-label">Sub Group</label>
		                                        <select class="form-control" id="subGroup" name="subGroup">
		                                        	<option value="0">Select</option>
		                                        </select>
	                                      	</div>
                                        </div>
                                    </div>                                             
									
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" onclick="return validateLedger();">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
								
                            </form:form>
                            </div>
						</div>
						
						<c:choose>
								<c:when test="${parentLedgerList eq null}">
									<div class="btnsarea01" >
										<div class="errorbox" id="errorbox" style="visibility: visible;">
											<span id="errormsg">Ledger List Not Found</span>	
										</div>
									</div>
								</c:when>
								<c:otherwise>
						
						
						<div class="col-md-12">						  
							 <form:form id="EdiLedgertName" name="EdiLedgertName" action="updateLedgerName.html" method="POST"> 
	 							<input type="hidden" id="saveId" name="saveId">	
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Ledger List </h2>
							</header>
							<div class="panel-body">
								<div id="oldLedgerName">
										<c:forEach var="ledger" items="${parentLedgerList}" varStatus="i">
											<input type="hidden" name="oldLedgerName" id="oldLedgerName" value="${ledger.ledgerName}">
										</c:forEach>
									</div>
								
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Select</th>
                                            <th>Ledger Name</th>
                                            <!-- <th>Parent Ledger Name</th> -->
											<th>Parent Group Name</th>
											<th>Sub Group Name</th>
                                            <th>Opening balance</th>
                                            <th>Current balance</th>
                                            <th>Actions</th> 
										</tr>
									</thead>
									<tbody>
										<c:forEach var="ledger" items="${parentLedgerList}" varStatus="i">
											<tr>
												<td>
													<input type="hidden" name="ledgerCode${i.index}" id="ledgerCode${i.index}" class="form-control" value = "${ledger.ledgerCode}">
											    	<input type="radio" name="ledgerCode" value="${ledger.ledgerCode}" onClick="window.open('getLedgerDetailsToEdit.html?ledgerCode=${ledger.ledgerCode}&ledgerOpeningBalance=${ledger.openingBal}&ledgerCurrentBalance=${ledger.currentBal}&ledgerGroup=${ledger.parentLedgerCode}','_self')" style="cursor:pointer;"/>
												</td>
												<td>
													<input type="hidden" name="ledgerId${i.index}" id="ledgerId${i.index}" class="form-control" value = "${ledger.ledgerCode}">
													<!--  modified by sourav.bhadra on 22-11-2017  -->
													<input type="text" class = "form-control" name="ledgerName${i.index}" id="ledgerName${i.index}" value="${ledger.ledgerName}" style="display: none;"/>
													<!--  added by sourav.bhadra on 22-11-2017  -->
													<span id="ledgerSpan${i.index}">${ledger.ledgerName}</span>
												</td>
												<!-- modified by sourav.bhadra on 14-08-2017 to remove parent ledgers -->
												<%-- <td>
													${ledger.parentLedgerCode}
												</td> --%>
												<td>
													${ledger.parentGroupCode}
												</td>
												<td>
													${ledger.subGroupDesc}
												</td>
												<td>
												<!-- 	modified by ranita.sur on 23/08/2017 for updating opening balance in ledger -->
												<input type="text" class = "form-control" name="openingBal${i.index}" id="openingBal${i.index}" value="${ledger.openingBal}" readonly/> 
												</td>
												<td >
												<!-- 	modified by ranita.sur on 23/08/2017 for updating opening balance in ledger -->
												<input type="hidden" class = "form-control" name="currentBal${i.index}" id="currentBal${i.index}" value="${ledger.currentBal}" /> 
												<!-- added By Ranita on 10.08.2017 for ledgerBalance Credit and debit -->
												<c:choose>
													<c:when test="${ledger.parentLedgerCode eq 'ASSETS'}">
														<c:if test="${ledger.currentBal lt 0}">
															${- ledger.currentBal}(Cr)
														</c:if>
														<c:if test="${ledger.currentBal gt 0}"> 
															${ledger.currentBal}(Dr)
														</c:if>
														
														<!-- added by sourav.bhadra on 21-08-2017 --> 
														<c:if test="${ledger.currentBal eq 0}">
															${ledger.currentBal}
														</c:if> 
													</c:when>
													<c:when test="${ledger.parentLedgerCode eq 'EQUITY AND LIABILITIES'}">
														<c:if test="${ledger.currentBal lt 0}">
															${- ledger.currentBal}(Dr)
														</c:if>
														<c:if test="${ledger.currentBal gt 0}">
														 ${ledger.currentBal}(Cr)
														</c:if>
														
														<!-- added by sourav.bhadra on 21-08-2017 --> 
														<c:if test="${ledger.currentBal eq 0}">
															${ledger.currentBal}
														</c:if>
													</c:when>
													<c:otherwise>
														<!-- added by sourav.bhadra on 21-08-2017 --> 
														<!-- modified by sourav.bhadra on 28-03-2018 --> 
														<c:if test="${ledger.currentBal lt 0}">
															${- ledger.currentBal}
														</c:if>
														<c:if test="${ledger.currentBal gt 0}">
															${ledger.currentBal}
														</c:if>
														<c:if test="${ledger.currentBal eq 0}">
															${ledger.currentBal}
														</c:if>
											   		</c:otherwise>
												</c:choose>	 
												
											  <%--   <c:if test="${ledger.currentBal lt 0}"> ${- ledger.currentBal}(Dr)</c:if>
												
												<c:if test="${ledger.currentBal gt 0}"> ${ledger.currentBal}(Cr)</c:if>  --%>
												 
												</td>
												<td>
													<button type="button" id="edit${i.index}" class="btn btn-primary" onClick="functionEditable('${i.index}')">Edit </button>
													<button type="submit" id="update${i.index}" class="btn btn-success" style="display: none">Update</button>
													<!-- added by sourav.bhadra on 21-08-2017 for cancel operation -->
													<button type="button" id="cancel${i.index}" class="btn btn-danger" style="display: none" onClick="cancelEditable('${i.index}');">Cancel</button>
                                                    <%--  <a class="on-default remove-row" href="inactiveLegdger.html?ledgerCode=${ledger.ledgerCode}"><i class="fa fa-trash-o"></i></a>  --%>
                                            	</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
							</div>
						</section>
						</form:form> 
						</div>
						</c:otherwise>
					</c:choose>
					
					
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