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
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/common/createASTV.js"> </script>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

		<c:if test="${successMessage ne null}">
				<div class="successbox" id="successbox" style="visibility:visible;">
					<span id="successmsg" style="visibility:visible;">${successMessage}</span>	
				</div>
			</c:if>
			<c:if test="${errorMessage ne null}">
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg" style="visibility:visible;">${errorMessage}</span>	
				</div>
			</c:if>
			
				<c:choose>
				<c:when test="${annualStockList eq null}">
					<div class="btnsarea01" style="visibility: visible;">
						<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
							<span id="infomsgbox">No Stock Record Found for this Department.</span>	
						</div>
					</div>
				</c:when>
				<c:otherwise>


					<div class="row">
						<div class="col-md-12">
						    <form:form  action="submitASTV.html" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">ANNUAL STOCK TAKING / BOARD FOR ${academicYear.academicYearName}</h2>	
										<input type="hidden" name="department" value="${department}">
										<input type="hidden" name="submitType" value="${submitType}">									
									</header>
									<div style="display: block;" class="panel-body">
                                        
                                            <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                                <thead>
                                                    <tr>
                                                        <th>Ledger No.</th>
                                                        <th>Page No.</th>
                                                        <th>Items</th>
                                                        <th>Ledger Bal.</th>
                                                        <th>Ground Bal.</th>
                                                        <th>Surplus</th>
                                                        <th>Deficient</th>
                                                        <th>DOP</th>
                                                        <th>Cost/Unit</th>
                                                        <th>Serviceable</th>
                                                        <th>Repairable</th>
                                                        <th>Condemnation</th>
                                                        <th>Remarks</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="annualStock" items="${annualStockList}" varStatus="i">
                                                    <tr>											
                                                        <td>
                                                        	${annualStock.asset.ledgerNumber}
                                                        </td>
														<td>
															${annualStock.asset.pageNumber}
														</td>
														<td>
															<input type="hidden" name="listAnnualStock[${i.index}].annualStockId" value="${annualStock.annualStockId}">
															<input type="hidden" name="listAnnualStock[${i.index}].asset.assetName" value="${annualStock.asset.assetName}"/>
															${annualStock.asset.assetName}
														</td>
                                                        <td>
                                                        	<input type="text" class="form-control" id="lBal${i.index}" name="listAnnualStock[${i.index}].asset.ledgerBalance" readonly="readonly" value="${annualStock.asset.ledgerBalance}" placeholder="" style="width:70px;">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" id="gBal${i.index}" name="listAnnualStock[${i.index}].groundBalance" value="${annualStock.groundBalance}" onblur="calculateStock(this);" placeholder="" style="width:70px;">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" id="surp${i.index}" name="listAnnualStock[${i.index}].surplus" value="${annualStock.surplus}" placeholder="" style="width:70px;">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" id="defi${i.index}" name="listAnnualStock[${i.index}].deficient" value="${annualStock.deficient}" placeholder="" style="width:70px;">
                                                        </td>
                                                        <td>
                                                        	${annualStock.asset.purchaseDate}
                                                        </td>
                                                        <td>
                                                        	${annualStock.asset.assetPrice}
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" id="serv${i.index}" name="listAnnualStock[${i.index}].serviceable" value="${annualStock.serviceable}" placeholder="" style="width:70px;" onblur="calculateStock(this);">
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" id="repa${i.index}" name="listAnnualStock[${i.index}].repairable" value="${annualStock.repairable}"  placeholder="" style="width:70px;" onblur="calculateStock(this);">
                                                        </td>
                                                        <td>
	                                                        <select class="form-control" name="listAnnualStock[${i.index}].condemnation">
	                                                            <option value="">Select...</option>
	                                                            <option value="true" ${annualStock.condemnation eq true ? 'selected=selected' : ''}>Yes</option>
																<option value="false" ${annualStock.condemnation eq false ? 'selected=selected' : ''}>No</option>
	                                                        </select>
                                                        </td>
                                                        <td>
                                                        	<input type="text" class="form-control" name="listAnnualStock[${i.index}].remarks" value="${annualStock.remarks}" placeholder="" style="width:100px;">
                                                        </td>
                                                    </tr>
                                                    
												</c:forEach>
                                                </tbody>
                                            </table>  
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" id="submitButton">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form:form>
						</div>
						
					</div>	
				</c:otherwise>
			</c:choose>		








<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>