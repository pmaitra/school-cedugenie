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
	/* document.getElementById("newVendorName"+rowId).removeAttribute("disabled");
	//document.getElementById("vendorType"+rowId).removeAttribute("disabled");
	document.getElementById("vendorContact1"+rowId).removeAttribute("disabled");
	document.getElementById("vendorContact2"+rowId).removeAttribute("disabled");
	document.getElementById("vendorAddress"+rowId).removeAttribute("disabled"); */
};
function showVendorDetails(vendorName,vendorContactNo1,vendorContactNo2,emailId,address,bankName,accountNo,bankCode,bankLocation,branchCode,index)
{
	
	$('#vendorDetails> tbody').empty();
 	if(vendorName != null && vendorName!=""){
 		var row = "<tbody>";
 		row += "<tr><td><input type='text' name='vendorName' id='vendorName' class = 'form-control'  value='"+vendorName+"'> </td><td><input type='text' name='vendorContactNo1'id='vendorContactNo1' class = 'form-control' value='"+vendorContactNo1+"'></td><td><input type='text' name='vendorContactNo2'id='vendorContactNo2' class = 'form-control' value='"+vendorContactNo2+"'></td> <td><input type='text' name='emailId' id='emailId' class = 'form-control'  value='"+emailId+"' readonly></td><td><input type='text' name='address' id='address' class = 'form-control'  value='"+address+"' readonly></td><td><input type='text' name='bankName' id='bankName' class = 'form-control'  value='"+bankName+"' readonly></td><td><input type='text' name='accountNo' id='accountNo' class = 'form-control'  value='"+accountNo+"' readonly></td><td><input type='text' name='bankCode' id='bankCode' class = 'form-control'  value='"+bankCode+"' readonly></td><td><input type='text' name='bankLocation' id='bankLocation' class = 'form-control'  value='"+bankLocation+"' readonly></td><td><input type='text' name='branchCode' id='branchCode' class = 'form-control'  value='"+branchCode+"' readonly></td></tr>";
		
 		$("#vendorDetails").append(row);
 	}
 	
 	$('#modalInfo').fadeIn("fast");
 	var btn = document.getElementById("updateVendor");
 	btn.setAttribute("onclick","saveData('"+index+"','"+vendorName+"','"+vendorContactNo1+"','"+vendorContactNo2+"','"+emailId+"','"+address+"','"+bankName+"','"+accountNo+"','"+bankCode+"','"+bankLocation+"','"+branchCode+"');");
	
	}
function saveData(rowId,vendorName,vendorContactNo1,vendorContactNo1,emailId,address,bankName,accountNo,bankCode,bankLocation,branchCode){
	var vendorname = document.getElementById("vendorName").value;
	
	var contactno=document.getElementById("vendorContactNo1").value;
	//alert(contactno);
	
	var contctno1=document.getElementById("vendorContactNo2").value;
	//alert(contctno1);
	var email=document.getElementById("emailId").value;
	//alert(email);
	var add=document.getElementById("address").value;
	var bankname=document.getElementById("bankName").value;
	var accno=document.getElementById("accountNo").value;
	var code=document.getElementById("bankCode").value;
	var location=document.getElementById("bankLocation").value;
	var branchco=document.getElementById("branchCode").value;
	/* rowId=rowId.replace("save",""); */
	document.getElementById("saveId").value=rowId;
	document.getElementById("getVendorName").value = vendorname;
	//alert(vendorname);
	document.getElementById("getContactNo1").value = contactno;
	document.getElementById("getContactNo2").value = contctno1;
	document.getElementById("getEmailId").value = email;
	document.getElementById("getAddress").value = add;
	document.getElementById("getBankName").value = bankname;
	document.getElementById("getAccountNo").value = accno;
	document.getElementById("getBankCode").value = code;
	document.getElementById("getBankLocation").value = location;
	document.getElementById("getBranchCode").value = branchco;
	var regx="^[a-zA-Z0-9]+$";
	//var nameregx="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$";
	var isNumeric=vendorname.match("[a-zA-Z][a-zA-Z ]+[a-zA-Z]$");
	var oldemailIds="";
	var oldaccountNos="";
	//alert(oldemailId);
	$("#oldemailId input[name='oldemailId']").each(function(){
		oldemailIds += $(this).val() + ";";
	});
	/* alert("LN81 : "+oldemailIds); */
	$("#oldaccountNo input[name='oldaccountNo']").each(function(){
		oldaccountNos += $(this).val() + ";";
	});
	 //alert("LN81 : "+oldaccountNos);
	var oldEmailList = oldemailIds.split(";");
	var oldAccountList = oldaccountNos.split(";");
	
	//alert("LN86 : "+oldAccountList);
	if(vendorname ==""|| vendorname==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Vendor Name should contain atleast one character."; 
		return false;
	}else if(contactno ==""|| contactno==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Contact No."; 
		return false;
	}
	else if(email ==""|| email==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Email Id"; 
		return false;
	}else if(add ==""|| add==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Address"; 
		return false;
	}else if(bankname ==""|| bankname==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Name"; 
		return false;
	}else if(accno ==""|| accno==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Account No"; 
		return false;
	}else if(location ==""|| location==null ){
		document.getElementById("warningmsg1").style.display = 'block';			
		document.getElementById("warningmsg1").innerHTML = "Enter Bank Location"; 
		return false;
	} else{
		  if(regx.match(code)==false){
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "IFSC Code  contain alphabets and spaces between words !!"; 
			return false;
		}else /* if(isNumeric) 
			{
			document.getElementById("warningmsg1").style.display = 'block';			
			document.getElementById("warningmsg1").innerHTML = "Vendor Name contain only character!!"; 
			return false;
			} *//*  if(oldEmailList.length != 0){
			 alert("LN123"); 
			for(var i=0;i<oldEmailList.length-1;i++){
				 alert("LN123 "); 
				 alert(email); 
				if(oldEmailList[i]===email){
					
					 alert("EmailId Exist"); 
					document.getElementById("warningmsg1").style.display = 'block';			
					document.getElementById("warningmsg1").innerHTML = "Email Id already exists";
					 alert("EmailId Exist"); 
					return false;
				}
			}
		}   */
		/* else if(oldAccountList.length != 0){
			alert("142");
			
			for(var i=0;i<oldAccountList.length-1;i++){
				alert( accno);
				if(oldAccountList[i]=== accno){
					document.getElementById("warningmsg1").style.display = 'block';			
					document.getElementById("warningmsg1").innerHTML = "Account No already exists";
					return false;
				}
			}
		}    */
		/* else{ */
			document.editVendorDetails.submit();
		//}
	}
};

function closeWarning()
{
	document.getElementById("warningmsg1").style.display = 'none';	
	}
</script>
</head>
<body>
				<header class="page-header">
				<h2>Add Vendor Details</h2>
			</header>
			<div class="content-padding">
				<c:if test="${successStatus != null}">
					<!-- <div class="successbox" id="successbox" style="visibility:visible;">
						<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
					</div> -->
					<div class="alert alert-success">
						<strong>Vendor Details Successfully Inserted</strong>
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
				<c:if test="${insertUpdateStatus eq 'success'}">
			<div class="alert alert-success"  >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		<c:if test="${insertUpdateStatus eq 'fail'}">
			<div class="alert alert-danger" >
				<strong>${msg}</strong>	
			</div>
		</c:if>
		
				<div class="alert alert-warning" id="warningmsg" style="display: none">
												<span></span>	
											</div>
					<div class="row">
						<div class="col-md-10 col-md-offset-1">
						  <form:form name="addVendor"  method="POST" action="submitAddVendor.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Add Vendor</h2>										
									</header>
									<div style="display: block;" class="panel-body">  
										<div class="col-md-6">
											
											<div class="form-group">
	                                            <label class="control-label">Vendor Name:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="vendorName" id="vendorName" pattern="^[a-zA-Z '.,-/]+$" title="Please Enter Vendor Name" placeholder="eg: MUKESH SINHA" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Vendor Type <span class="required" aria-required="true">*</span></label>
	                                            <select class="form-control" name="vendorType" id="vendorType" required>
	                                                <option value="">Select...</option>
	                                                <c:forEach var="vendorType" items="${vendorTypeList}">
														<option value="${vendorType.vendorTypeCode}">${vendorType.vendorTypeName}</option>
													</c:forEach>
	                                            </select>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Contact No. 1:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="vendorContactNo1" id="vendorContactNo1"  pattern="^[0-9]\d*$" title="Please Enter  Contact No" placeholder="eg: 12345" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Contact No. 2</label>
	                                            <input type="text" class="form-control" name="vendorContactNo2" id="vendorContactNo2" placeholder="eg: 12345">
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Email Id :<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="emailId" id="emailId"  pattern="^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$" placeholder="eg: email@email.com" title="'email@email.com' The value is not a valid email address" required>
	                                        </div>
	                                        <div class="alert alert-danger" id="warningbox" style = "display:none">
													<!-- <span id="warningmsg"></span> -->	
							</div> 
	                                        <div class="form-group">
	                                            <label class="control-label">Address: <span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="address" id="address" pattern="^[a-zA-Z0-9 '.,-/]+$" title="Please Enter Vendor Address" placeholder="eg: Entally" required>
	                                        </div>
	                                        
											
										</div> 
										<div class="col-md-6">
											
	                                        <div class="form-group">
	                                            <label class="control-label">Bank Name:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="bankName" id="bankName" pattern="[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" title="Please Enter The Bank Name"  placeholder="eg: SBI" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Account No::<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="accountNo" id="accountNo" pattern="^[0-9]\d*$" title="Please Enter Account No" placeholder="eg: 123456" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">IFSC Code:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="bankCode" id="bankCode" pattern="^[a-zA-Z0-9]{11,11}+$" title="Please Enter 11 digit IFSC COde" placeholder="eg: ABCD1234589" required>
	                                        </div>
	                                        <div class="form-group">
	                                            <label class="control-label">Bank Location:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="bankLocation" id="bankLocation" pattern="^[a-zA-Z0-9 '.,-/]+$" title="Please Enter Bank Address" placeholder="eg: Entally" required>
	                                        </div>
	                                         <div class="form-group">
	                                            <label class="control-label">Branch Code:<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="branchCode" id="branchCode" pattern="^[0-9]\d*$" title="Please Enter  Bramch Code" placeholder="eg: 12345" required>
	                                        </div>
	                                       <!--  <div class="form-group">
                                                <label class="control-label">Opening Balance: <span class="required" aria-required="true">*</span></label>                                                
                                                <input type="text" class="form-control" id="openingBal" name="openingBal" placeholder="eg: 1000"  pattern = "^[0-9]\d*$"  title="Enter valid opening Balance" required>
                                            </div> -->
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" type="submit" onclick="return vendorDetailsValidation()" >Submit </button>
										<button type="reset" class="btn btn-default" id="clear">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						
						<c:choose>
							<c:when test="${vendorDetailsList eq null}">
								<div class="alert alert-danger">
									<strong>No Vendor(s) Found.</strong>
								</div>
							</c:when>
						<c:otherwise>
						
						
						<div class="col-md-12">
							<form name="editVendorDetails" id="editVendorDetails" action="editVendorDetails.html" method="POST">
							<input type="hidden" name="saveId" id="saveId">
							<input type="hidden" name="getVendorName" id="getVendorName">
							
							<input type="hidden" name="getContactNo1" id="getContactNo1">
							<input type="hidden" name="getContactNo2" id="getContactNo2">
							<input type="hidden" name="getEmailId" id="getEmailId">
							<input type="hidden" name="getAddress" id="getAddress">
							<input type="hidden" name="getBankName" id="getBankName">
							<input type="hidden" name="getAccountNo" id="getAccountNo">
							<input type="hidden" name="getBankCode" id="getBankCode">
							<input type="hidden" name="getBankLocation" id="getBankLocation">
							<input type="hidden" name="getBranchCode" id="getBranchCode">
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Vendor List</h2>
							</header>
							<div class="panel-body">
							<div id=oldemailId>
								<c:forEach var="vendor" items="${vendorDetailsList}" >
								<input type="hidden" class="form-control" id="oldemailId" name="oldemailId" value="${vendor.emailId}" />
								</c:forEach>
							</div>
							<div id="oldaccountNo">
							<c:forEach var="vendor" items="${vendorDetailsList}" >
								<input type="hidden" class="form-control" id="oldaccountNo" name="oldaccountNo" value="${vendor.accountNo}" />
								</c:forEach>
							
							</div>
							<%-- <c:forEach var="vendorType" items="${vendorTypeList}">
												<input type="hidden" name="vendorTypeNames" value ="${vendorType.vendorTypeName}">
											</c:forEach> --%>
							<div class="row">
								<div class="col-md-12 table-responsive">				
								<table class="table table-bordered table-striped mb-none" id="datatable-datatools">
									<thead>
										<tr>
											<th>Vendor Name</th>
                                            <th>Vendor Type</th>
											<th>Contact No 1</th>
                                            <th>Contact No 2</th>
                                            <th>Email Id</th>
                                            <th>Address</th>
                                            <th>BankName</th>
                                            <th>AccountNo</th>
                                            <th>IFSC Code</th>
                                            <th>BankLocation</th>
                                             <th>Branch Code</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="vendor" items="${vendorDetailsList}" varStatus="i">
										<tr>											
											<td>
												<input type="hidden" id="oldVendorCode${i.index}" name="oldVendorCode${i.index}" value="${vendor.vendorCode}" />
												<input type="hidden" class="form-control" id="newVendorName${i.index}" name="newVendorName${i.index}"  value="${vendor.vendorName}" />
												${vendor.vendorName}
											</td>
											<td>
												 <input type="hidden" class="form-control" id="vendorType${i.index}" name="vendorType${i.index}" value="${vendor.vendorType}" />
												${vendor.vendorType} 
												</td>
												<td>	
												<input type="hidden" class="form-control" id="vendorContact1${i.index}" name="vendorContact1${i.index}" value="${vendor.vendorContactNo1}" />
												${vendor.vendorContactNo1}
											</td>
											<td>
												<input type="hidden" class="form-control" id="vendorContact2${i.index}" name="vendorContact2${i.index}" value="${vendor.vendorContactNo2}" />
												${vendor.vendorContactNo2}
												
											</td>
											<td>	
												<input type="hidden" class="form-control" id="emailId${i.index}" name="emailId${i.index}" value="${vendor.emailId}" />
												${vendor.emailId}
											</td>
											<td>
												<input type="hidden" class="form-control" id="vendorAddress${i.index}" name="vendorAddress${i.index}" value="${vendor.address}" />
												${vendor.address}
											</td>
											<td>
												<input type="hidden" class="form-control" id="bankName${i.index}" name="bankName${i.index}" value="${vendor.bankName}" />
												${vendor.bankName}
											</td>
											<td>
												<input type="hidden" class="form-control" id="accountNo${i.index}" name="accountNo${i.index}" value="${vendor.accountNo}" />
												${vendor.accountNo}
											</td>
											<td>
												<input type="hidden" class="form-control" id="bankCode${i.index}" name="bankCode${i.index}" value="${vendor.bankCode}" />
												${vendor.bankCode}
											</td>
											<td>
												<input type="hidden" class="form-control" id="bankLocation${i.index}" name="bankLocation${i.index}" value="${vendor.bankLocation}" />
												${vendor.bankLocation}
											</td>
											<td>
												<input type="hidden" class="form-control" id="branchCode${i.index}" name="branchCode${i.index}" value="${vendor.branchCode}" />
												${vendor.branchCode}
											</td>
											<td class="actions">
												<%-- <a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a> --%>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic " onclick = "showVendorDetails('${vendor.vendorName}','${vendor.vendorContactNo1}','${vendor.vendorContactNo2}','${vendor.emailId}','${vendor.address}','${vendor.bankName}','${vendor.accountNo}','${vendor.bankCode}','${vendor.bankLocation}','${vendor.branchCode}','${i.index}')"><i class="fa fa-pencil"></i></a>
												<a href="inactiveVendorDetails.html?vendorCode=${vendor.vendorCode}" id = "delete${i.index}"><i class="fa fa-trash-o"></i></a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
							</div>
						</div>
						<!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 900px;">
									<section class="panel">
										<header class="panel-heading">
											 <h2 class="panel-title">Vendor Details</h2> 
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id = "vendorDetails">
												<thead>
													<tr>
															<th>VendorName</th>
															
															<th>Contact No 1</th>
				                                            <th>Contact No 2</th>
				                                            <th>Email Id</th>
				                                            <th>Address</th>
				                                            <th>BankName</th>
				                                            <th>AccountNo</th>
				                                            <th>IFSC Code</th>
				                                            <th>BankLocation</th>
				                                            <th> Branch Code</th>
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
													<button id="updateVendor" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
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
<script type="text/javascript" src="/cedugenie/js/common/addVendor.js"></script>
 
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script src="/cedugenie/js/backoffice/addVendor.editable.js"></script>
</body>
</html>