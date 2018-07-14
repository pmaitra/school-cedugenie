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
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	
};
function showBankDetails(bankName,branchName,accountNo,bankIfscCode,bankBranchCode,bankLocation,index)
{
	$('#bankDetails> tbody').empty();
 	if(bankName != null && bankName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='bankName' id='bankName' class = 'form-control'  value='"+bankName+"'> </td><td><input type='text' name='branchName'id='branchName' class = 'form-control' value='"+branchName+"'></td><td><input type='text' name='accountNo'id='accountNo' class = 'form-control' value='"+accountNo+"'></td> <td><input type='text' name='bankIfscCode' id='bankIfscCode' class = 'form-control'  value='"+bankIfscCode+"'></td><td><input type='text' name='bankBranchCode' id='bankBranchCode' class = 'form-control'  value='"+bankBranchCode+"'></td><td><input type='text' name='bankLocation' id='bankLocation' class = 'form-control'  value='"+bankLocation+"'></td></tr>";
 		$("#bankDetails").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateBank");
 	btn.setAttribute("onclick","saveData('"+index+"','"+bankName+"','"+branchName+"','"+accountNo+"','"+bankIfscCode+"','"+bankBranchCode+"','"+bankLocation+"');");
	
	}
function saveData(rowId,bankName,branchName,accountNo,bankIfscCode,bankBranchCode,bankLocation){
	var bankname=document.getElementById("bankName").value;
	var branchname=document.getElementById("branchName").value;
	var accno=document.getElementById("accountNo").value;
	var ifsccode=document.getElementById("bankIfscCode").value;
	var branchcode=document.getElementById("bankBranchCode").value;
	var location=document.getElementById("bankLocation").value;
	
	//rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	document.getElementById("getBankName").value = bankname;
	document.getElementById("getBranchName").value = branchname;
	document.getElementById("getAccountNo").value = accno;
	document.getElementById("getBankIfscCode").value = ifsccode;
	document.getElementById("getBranchCode").value = branchcode;
	document.getElementById("getBankLocation").value = location;
	if(bankname ==""|| bankname==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Name"; 
		return false;
	}else if(branchname ==""|| branchname==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Branch Name"; 
		return false;
	
	}else if(accno ==""|| accno==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Account No"; 
		return false;
	}else if(ifsccode ==""|| ifsccode==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank IFSC Code"; 
		return false;
	}
	else if(location ==""|| location==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Location"; 
		return false;
	} else{
	document.editBankDetails.submit();
	}
};
</script>
</head>
<body>
				<header class="page-header">
				<h2>Add Bank Details</h2>
			</header>
			<div class="content-padding">
				<c:if test="${successStatus != null}">
					<!-- <div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
					</div> -->
					<div class="alert alert-success">
						<strong>Bank Details Successfully Inserted</strong>
					</div>
				</c:if>
				<c:if test="${failStatus != null}">
					<!-- <div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">Update Fail!</span>	
					</div> -->
					<div class="alert alert-danger">
						<strong>Sorry!Insertion Failed.</strong>
					</div>
				</c:if>
					<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form:form name="getBankDetails"  method="POST" action="submitAddBank.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title"></h2>										
									</header>
									<div style="display: block;" class="panel-body">  
										<div class="col-md-6">
											<div class="form-group">
	                                            <label class="control-label">Bank Name:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="bankName" id="bankName" pattern="^[a-zA-Z '.,-/]+$" title="Please Enter The Bank Name"  placeholder="eg: SBI" required>
	                                        </div>
	                                       <div class="form-group">
	                                            <label class="control-label">Branch Name:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="branchName" id="branchName" pattern="^[a-zA-Z '.,-/]+$" title="Please Enter The Branch Name" placeholder="eg: Entally" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Bank Account No:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="accountNo" id="accountNo"  pattern="^[0-9]\d*$" title="Please Enter Account No" placeholder="eg: 123456" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Bank IFSC Code:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="bankIfscCode" id="bankIfscCode" pattern="^[a-zA-Z0-9]{11,11}+$" title="Please Enter 11 digit IFSC COde" placeholder="eg: ABCD1234589" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Branch Code: <span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="bankBranchCode" id="bankBranchCode"  pattern="^[0-9]\d*$" title="Please Enter  Bramch Code" placeholder="eg: 12345" required>
	                                        </div>
	                                        
	                                        <div class="form-group">
	                                            <label class="control-label">Address: <span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="bankLocation" id="bankLocation" pattern="^[a-zA-Z0-9 '.,-/]+$" title="Please Enter Bank Address" placeholder="eg: Entally" required>
	                                        </div>
	                                        
											
										</div> 
										<div class="col-md-6">
											
	                                       <%--  <div class="form-group">
                                                <label class="control-label">Parent Group</label>
                                                <select class="form-control" name="parentGroupCode" id="parentGroupCode">
                                                    <option value="">Select...</option>
                                                    <c:if test="${total ne null}">
													<c:forEach var="gfl" items="${total}">
														<option value="${gfl.childCode}">${gfl.childName}</option>
													</c:forEach>
												</c:if>
                                                </select>
                                            </div> --%>
                                           <!--  <div class="form-group">
                                              	 <label class="control-label">Sub Group</label>
		                                            <select class="form-control" id="subGroup" name="subGroup">
		                                            	<option value="0">Select</option>
		                                            </select>
		                                        </div> -->
	                                         <div class="form-group">
                                                <label class="control-label">Opening Balance: <span class="required" aria-required="true">*</span></label>                                                
                                                <input type="text" class="form-control" id="openingBal" name="openingBal" placeholder="eg: 1000"  pattern = "^[0-9]\d*$"  title="Enter valid opening Balance" required>
                                            </div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type="submit" >Submit </button>
										<button type="reset" class="btn btn-default" id="clear">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						
						<c:choose>
							<c:when test="${bankDetailList eq null}">
								<div class="alert alert-danger">
									<strong>No Bank(s) Found.</strong>
								</div>
							</c:when>
						<c:otherwise>
						
						
						<div class="col-md-12">
							<form name="editBankDetails" id="editBankDetails" action="editBankDetails.html" method="POST">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="getBankName" id="getBankName">
							<input type="hidden" name="getBranchName" id="getBranchName">
							<input type="hidden" name="getAccountNo" id="getAccountNo">
							<input type="hidden" name="getBankIfscCode" id="getBankIfscCode">
							<input type="hidden" name="getBranchCode" id="getBranchCode">
							<input type="hidden" name="getBankLocation" id="getBankLocation">
							
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Bank List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
											
                                            <th>BankName</th>
                                            <th>Branch Name</th>
                                            <th>AccountNo</th>
                                            <th>IFSC Code</th>
                                            <th>Branch Code</th>
                                           	<th>Bank Address</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="vendor" items="${bankDetailList}" varStatus="i">
																					
											<%-- <td>
												<input type="hidden" id="oldVendorCode${i.index}" name="oldVendorCode${i.index}" value="${vendor.vendorCode}" />
												<input type="text" class="form-control" id="newVendorName${i.index}" name="newVendorName${i.index}" value="${vendor.vendorName}" disabled/>
											</td> --%>
											<tr>
											<td>
											<input type="hidden" id="oldBankCode${i.index}" name="oldBankCode${i.index}" value="${vendor.bankCode}" />
												<input type="hidden" class="form-control" id="bankName${i.index}" name="bankName${i.index}" value="${vendor.bankName}" />
												${vendor.bankName}
											</td>
											<td>
												<input type="hidden" class="form-control" id="branchName${i.index}" name="branchName${i.index}" value="${vendor.branchName}" />
												${vendor.branchName}
											</td>
											<td>
												<input type="hidden" class="form-control" id="accountNo${i.index}" name="accountNo${i.index}" value="${vendor.accountNo}" />
												${vendor.accountNo}
											</td>
											<td>
												<input type="hidden" class="form-control" id="bankIfscCode${i.index}" name="bankIfscCode${i.index}" value="${vendor.bankIfscCode}" />
												${vendor.bankIfscCode}
											</td>
											<td>
												<input type="hidden" class="form-control" id="bankBranchCode${i.index}" name="bankBranchCode${i.index}" value="${vendor.bankBranchCode}" />
												${vendor.bankBranchCode}
											</td>
											
											<td>
												<input type="hidden" class="form-control" id="bankLocation${i.index}" name="bankLocation${i.index}" value="${vendor.bankLocation}" />
												${vendor.bankLocation}
											</td>
											
											<td class="actions">
												<%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a> --%>
												<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showBankDetails('${vendor.bankName}','${vendor.branchName}','${vendor.accountNo}','${vendor.bankIfscCode}','${vendor.bankBranchCode}','${vendor.bankLocation}','${i.index}')"><i class="fa fa-pencil"></i></a>
												<a href="inactiveBankDetails.html?oldBankCode=${vendor.bankCode}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
					</form>
				</div>
				<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 800px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Bank Details</h2> 	<!-- changed by Saif 19-03-2018 -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "bankDetails">
												<thead>
													<tr>
				                                        <th>BankName</th>
				                                        <th>Branch Name</th>
                                            			<th>AccountNo</th>
                                            			<th>IFSC Code</th>
                                            			<th>Branch Code</th>
                                            			<th>Bank Address</th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg1" style="display: none">
												<span></span>	
											</div>
										</div>
										
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateBank" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
				</c:otherwise>
				</c:choose>
			</div>
			</div>
					






<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script type="text/javascript" src="/cedugenie/js/backoffice/addBank.js"></script> -->
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/cedugenie/js/common/addVendor.editable.js"></script>
</body>
</html>