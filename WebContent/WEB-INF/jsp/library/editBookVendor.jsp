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
	<header class= "page-header">		<!-- added by Saif 29-03-2018 -->
		<h2>Edit Book Vendor Details</h2>
	</header>
			<div class = "content-padding">
				<div class="row" id="wrap">
						<c:if test="${message ne null}">
							<div class="alert alert-danger">
								<strong>${message}</strong>	
							</div>
						</c:if>
						<c:choose>
							<c:when test="${vendor == null}">
								<div class="btnsarea01" style="visibility: visible;">
<!-- 									<div class="errorbox" id="errorbox" style="visibility: visible;"> -->
<!-- 										<span id="errormsg">Vendor not found</span>	 -->
<!-- 									</div> -->
									<div class="alert alert-danger">
										<strong>Vendor not found</strong>
									</div>
								</div>
							</c:when>
						<c:otherwise>
						<form:form name="editVendor"  method="POST" action="updateBookVendorMaping.html">
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Vendor Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Vendor Name</label>
											<input type="text" class="form-control" name="vendorName" id="vendorName" readonly value="${vendor.vendorName}"/>
										</div>
										<div class="form-group">
											<label class="control-label">Vendor Code</label>
											<input type="text" class="form-control" name="vendorCode" id="vendorCode" readonly value="${vendor.vendorCode}" />
										</div>
 									</div>									
								</section>
							</div>
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Edit Book Vendor</h2>
	                                </header>
	                                
	                                <c:choose>
										<c:when test="${vendor == null}">
											<div class="btnsarea01" style="visibility: visible;">
<!-- 												<div class="errorbox" id="errorbox" style="visibility: visible;"> -->
<!-- 													<span id="errormsg">Books  Not Found</span>	 -->
<!-- 												</div> -->
												<div class="alert alert-danger">
													<strong>Books not found</strong>
												</div>
											</div>
										</c:when>
									<c:otherwise>
			                                <div class="panel-body">
			                                    <table class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Select</th>	
															<th>Book Code</th>
															<th>Book Name</th>	
															<th>Book Rate</th>	
															<th>Price History</th>	
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        
			                                        	<c:forEach var="item" items="${itemList}" varStatus="indexVal">
														<tr class="gradeX">
															<c:choose>
																<c:when test="${item.purchaseRate == 0}">
																	<td>
																		<input id="check${indexVal.index+1}" type="checkbox" name="vendorItems[${indexVal.index}].itemCode" value="${item.itemCode}" />
																	</td>
																</c:when>
																<c:otherwise>
																	<td>
																		<input id="check${indexVal.index+1}" checked="checked" type="checkbox" name="vendorItems[${indexVal.index}].itemCode" value="${item.itemCode}" />
																	</td>
																</c:otherwise>
															</c:choose>
															<td>
																${item.itemCode}
															</td>
															<td>
																${item.itemName}
															</td>
															<td>
																<input type="text" class="form-control" name="vendorItems[${indexVal.index}].sellingRate"  id="txt${indexVal.index+1}" value="${item.purchaseRate}" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.0';}"/>
																
															</td>
															<td>
																<a href="#modalInfo" class="mb-xs mt-xs mr-xs modal-basic btn btn-info" id="${item.itemCode}"  onclick = "return showBookPriceHistoryPopUp(this.id)">History</a>
															</td>
														</tr>
														</c:forEach>
			                                        </tbody>
			                                    </table>
			                                    <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
					                            <section class="panel">
					                                <header class="panel-heading">
					                                    <h2 class="panel-title">Book Rate History</h2>
					                                </header>
					                                <div class="panel-body">
					                                    <table class="table table-bordered table-striped mb-none" id = "priceHistoryPopUp">
					                                        <thead>
					                                            <tr>
					                                                <th>Date</th>
					                                                <th>Price</th>
					                                            </tr>
					                                        </thead>
					                                        <tbody>
					                                        
					                                        </tbody>
					                                    </table>
					                                </div>
														<footer class="panel-footer">
															<div class="row">
																<div class="col-md-12 text-right">
																	<button class="btn btn-info modal-dismiss">OK</button>
																</div>
															</div>
														</footer>
													</section>
												</div>
			                                </div>
	                                </c:otherwise>
	                                </c:choose>
	                                <footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submitButton" value="SUBMIT" >Submit</button>
									</footer>
	                            </section>
							</div>
						</form:form>
						</c:otherwise>
						</c:choose>
					</div>
                   
                   
                   
					<!-- end: page -->
			</div>

					


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript">
function showBookPriceHistoryPopUp(id){
	var bookCode=id;
	var vendorCode=document.getElementById("vendorCode").value;
	$.ajax({
		url:'/cedugenie/getVendorBookPriceHistory.html',
		dataType: 'json',
		data:"vendorCode=" + vendorCode+ "&bookCode=" +bookCode,
		success: function(data){
			$('#priceHistoryPopUp > tbody').empty();
			if(null!=data && data!=""){
				dataArray=data.split("#");
				var row = "<tbody>";
				for(var i=0;i<dataArray.length-1;i++){
					var dateAndRate = dataArray[i].split("*");
					row+= "<tr><td>"+dateAndRate[0]+"</td><td>"+dateAndRate[1]+"</td></tr>";
				}
				$("#priceHistoryPopUp").append(row);
 			}
			$('#modalInfo').fadeIn("fast");
		}
	});
}
</script>
<script type="text/javascript" src="/cedugenie/js/library/editBookVendor.js"></script>
</body>
</html>