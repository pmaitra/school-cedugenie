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
<title>TAX Setup</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>TAX Setup</h2>
	</header>
	<div class="content-padding">
		<c:if test="${insertResponse ne null}">				
			<c:if test="${insertResponse eq 'Success'}">
				<div class="alert alert-success">
					<strong>TAX Percentage Stored Successfully.</strong>	
				</div>
			</c:if>
			<c:if test="${insertResponse eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>TAX Percentage Insertion Failed.</strong>
				</div>
			</c:if>
		</c:if>
		
		<c:if test="${updateStatus ne null}">				
			<c:if test="${updateStatus eq 'Success'}">
				<div class="alert alert-success">
					<strong>TAX Percentage Updated Successfully.</strong>	
				</div>
			</c:if>
			<c:if test="${updateStatus eq 'Fail'}">
				<div class="alert alert-danger">
					<strong>TAX Percentage Updation Failed.</strong>
				</div>
			</c:if>
		</c:if>
			<c:if test="${submitResponse ne null}">				
			<c:if test="${submitResponse eq 'success'}">
				<div class="alert alert-success">
					<strong>TAX Percentage Deleted Successfully.</strong>	
				</div>
			</c:if>
			<c:if test="${submitResponse eq 'fail'}">
				<div class="alert alert-danger">
					<strong>TAX Percentage Deletion Failed.</strong>
				</div>
			</c:if>
		</c:if>	
		<div class="row">
			<form:form method="POST" id="submitTaxPercentages" name="submitTaxPercentages"  action="submitTaxPercentages.html" >
                 <div class="col-md-6">						  
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">
								
							</h2> 
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="taxtable">
									<thead>
										<tr>
                                            <th>TAX Name</th>
                                            <th>Percentage(%)</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
										<tr>
                                            <td>
                                            	<input type="text" class="form-control" id="taxName" name="taxName" pattern="^[a-zA-Z ']+$" title="Please Enter Proper Tax Name" placeholder="eg.: Service TAX" required>
                                           	</td>
                                            <td>
                                                <input type="text" class="form-control" id="percentage" name="percentage" pattern ="^([0-9]*[.])?[0-9]+$" title="Only Numeric" placeholder="eg.: 10" required>
                                            </td>
											<td>
												<select class="form-control" id="taxStatus" name="taxStatus" required>
													<option value="">Select..</option>
													<option value="ACTIVE">Active</option>
													<option value="INACTIVE">Inactive</option>
												</select>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
                            <footer style="display: block;" class="panel-footer">
                                <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary" value="SUBMIT">Submit</button>
                                <button type="reset" class="mb-xs mt-xs mr-xs btn btn-danger" >Cancel</button>
                            </footer>
						</section>
					</div>
                </form:form>
                
				
                <div class="col-md-6">	
					<form name="editTaxPercentages" id="editTaxPercentages" action="editTaxPercentages.html" method="post">
						<input type="hidden" name="saveId" id="saveId">
						<input type="hidden" name="getTaxName" id="getTaxName">
						<input type="hidden" name="getPercentage" id="getPercentage">
						<input type="hidden" name="getStatus" id="getStatus">
                        <section class="panel">
                            <header class="panel-heading">
                                <div class="panel-actions">
                                    <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                </div>
								<h2 class="panel-title">View / Edit TAX</h2>
                            </header>
                            <div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
                                    <thead>
                                        <tr>
                                            <th>Tax Name</th>
                                            <th>Percentage(%)</th>
                                            <th>Status</th>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="tax" items="${taxList}" varStatus="i">
                                    		<tr class="gradeX">
												<td>
													<input type="hidden" name="taxCode${i.index}" id=taxCode${i.index}" value="${tax.taxCode}">
													<input type="hidden" name="taxName${i.index}" id="taxName${i.index}" value="${tax.taxName}">
													${tax.taxName}
												</td>
												<td>
													<input type="hidden" name="percentage${i.index}" id="percentage${i.index}" value="${tax.percentage}">
													${tax.percentage}
												</td>
												<td>
													<input type="hidden" name="taxStatus${i.index}" id="taxStatus${i.index}" value="${tax.taxStatus}">
													${tax.taxStatus}
												</td>
												<td class="actions">
			                                        <a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic" onclick="updateTax('${i.index}')"><i class="fa fa-pencil"></i></a>
													 <a class="on-default remove-row"  href="inactiveTaxDetails.html?taxCode=${tax.taxCode}" ><i class="fa fa-trash-o"></i></a> 
			                                    </td>
											</tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </section>
                        
                        <!-- popup Window code -->
								<div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
									<section class="panel">
										<header class="panel-heading">
											<!-- <h2 class="panel-title">Approver Group Name - PO_Approver</h2> -->
										</header>
										<div class="panel-body">
											<table class="table table-bordered table-striped mb-none" id="updateTaxDetails">
												<thead>
													<tr>
														 <th>Tax Name</th>
		                                                <th>Percentage(%)</th>
		                                                <th>Status</th>
													</tr>
												</thead>
												<tbody>
								
												</tbody>
											</table>
											<div class="alert alert-warning" id="warningmsg" style="display: none">
												<span></span>	
											</div>
										</div>
										<footer class="panel-footer">
											<div class="row">
												<div class="col-md-12 text-right">
													<button id="updateTaxButton" class="btn btn-success">Update</button>
													<button class="btn btn-danger modal-dismiss" onclick="closeWarning()">Cancel</button>
												</div>
											</div>
										</footer>
									</section>
								</div>
					</form>
				</div>
            </div>	
		</div>			
		<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
		<%@ include file="/include/js-include.jsp" %>
		<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
		<script src="/cedugenie/js/finance/setUpTaxPercentage.js"></script> 
		<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
	</body>
</html>