<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
	
<head>

	<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
	<title>Standard Subject Mapping</title>
	<%@ include file="/include/include.jsp" %>
	<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
	<style type="text/css">
	       .scroll-to-top{
	           display: none !important;
	       }.mb-md{
	       	   display: none;
	       }
	</style>
</head>
<body>
		<c:if test="${status ne null}">
			<div class="alert alert-success">
				<strong>${status}</strong>	
			</div>
		</c:if>
		<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form action="saveDelarDetails.html" method="post" class="form-horizontal">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title"><b>Certificate </b></h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-4 control-label"><b>Vendor : </b></label>
											<div class="col-sm-8">
												<select name="vendor" id="vendor" class="form-control" onchange="createRefNo(this);">
													<option value="">Select</option>
													<c:forEach var="vendor" items="${vendorList}">
														<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
													</c:forEach>
												</select>
											</div>
										</div> 
                                        <div class="form-group">
											<label class="col-sm-4 control-label"><b>VAT TIN : </b></label>
											<div class="col-sm-8">
												<input type="text" name="vatTin" id="vatTin" class="form-control" >
											</div>
										</div> 
                                        <div class="form-group">
											<label class="col-sm-4 control-label"><b>Central Sales Tax (CST) No. : </b> </label>
											<div class="col-sm-8">
												<input type="text" name="cstNo" id="cstNo" class="form-control" >
											</div>
										</div> 
                                        <div class="form-group">
											<label class="col-sm-4 control-label"><b>Excise Reg. No. :</b></label>
											<div class="col-sm-8">
												<input type="text" name="exciseNo" id="exciseNo" class="form-control" >
											</div>
										</div> 
                                        
										<div class="form-group">
											<label class="col-sm-4 control-label"><b>Range :</b></label>
											<div class="col-sm-8">	
												<input type="text" name="rangeCategory" id="rangeCategory" class="form-control" >
											</div>
										</div>
                                         <div class="form-group">
											<label class="col-sm-4 control-label"><b>Division No. :</b></label>
											<div class="col-sm-8">	
												<input type="text" name="divisionNo" id="divisionNo" class="form-control" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label"><b>Commission Rate :</b></label>
											<div class="col-sm-8">	
												<input type="text" name="commissionRate" id="commissionRate" class="form-control" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label"><b>Ref No. :</b></label>
											<div class="col-sm-8">	
												<input type="text" name="refNo" id="refNo" class="form-control" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label"><b>Buyer/Supplier : </b></label>
											<div class="col-sm-8">
												<select name="delarType" id="delarType" class="form-control">
													<option value="">Select</option>
													<option value="BUYER">Buyer</option>
													<option value="SUPPLIER">Supplier</option>
												</select>
											</div>
										</div> 
										<div class="form-group">
											<label class="col-sm-4 control-label"><b>Default Nature Of Purchase :</b></label>
											<div class="col-sm-8">	
												<input type="text" name="natureOfPurchase" id="natureOfPurchase" class="form-control" >
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label"><b>Company Name :</b></label>
											<div class="col-sm-8">	
												<input type="text" name="companyName" id="companyName" class="form-control" >
											</div>
										</div>
										<div class="form-group">
		                                    <label class="col-sm-4 control-label"><b>Date Of Registration :<span class="required" aria-required="true">*</span></b></label>
		                                        <label class="col-sm-8 control-label">
		                                            <div class="input-group">
		                                                <span class="input-group-addon">
		                                                    <i class="fa fa-calendar"></i>
		                                                </span>
		                                                <input type="text" class="form-control" name="dtOfRegistration" id="dtOfRegistration" data-plugin-datepicker="" required/>
		                                            </div>
		                                     </label>
	                                    </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button  class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						
					</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/report/userExamMarkStatement.js"></script>

<script>
/* $(document).ready(function() {
	 $('#dtOfRegistration').Zebra_DatePicker();
	 
	 $('#dtOfRegistration').Zebra_DatePicker({
	 	  format: 'd/m/Y'
	 	});
 }); */

function createRefNo(ven){
	document.getElementById("refNo").value=ven.value;
}
</script>
</body>


</html>