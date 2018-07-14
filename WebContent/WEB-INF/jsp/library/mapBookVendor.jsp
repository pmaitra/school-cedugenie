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
<title>Map Book-Vendor</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header"> 	<!-- Added by Saif 29-03-2018 -->
		<h2>Map Vendor With Book</h2>
	</header>
		<div class = "content-padding">
			<div class="row" id="row">
				<form:form name="addVendor"  method="POST" action="updateBookVendorMaping.html">
                	<div class="col-md-4">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>
								<h2 class="panel-title">Map Book Vendor</h2>										
							</header>
							<div style="display: block;" class="panel-body">
                                <div class="form-group">
                                    <label class="control-label">Vendor Name <span class="required" aria-required="true">*</span></label>
                                    <select class="form-control" name="vendorName" id="vendorName">
                                        <option value="">Select...</option>
                                        <c:forEach var="vendor" items="${vendorType.vendorList}">
											<option value="${vendor.vendorCode}">${vendor.vendorName}</option>
										</c:forEach>
                                    </select>
                                </div> 
								<div class="form-group">
                                    <label class="control-label">Vendor Code</label>
                                    <input type="text" class="form-control" name="vendorCode" id="vendorCode" readonly="readonly">
                                </div>     
							</div>
						</section>
                    </div>
                    <c:choose>
						<c:when test="${vendorType.itemList == null && vendorType.itemList.size()== 0}">
							<div class="btnsarea01" style="visibility: visible;">
<!-- 								<div class="errorbox" id="errorbox" > -->
<!-- 									<span id="errormsg">Item List Not Found</span>	 -->
<!-- 								</div> -->
								<div class="alert alert-danger">
									<strong>Item list not found.</strong>
								</div>
							</div>
						</c:when>
					<c:otherwise>
	                <div class="col-md-8">
                    	<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">Map Book Vendor</h2>
							</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
								<thead>
									<tr>
										<th><input type="checkbox" disabled="disabled"></th>	
										<th>Book Code</th>
										<th>Book Name</th>	
										<th>Book Rate</th>
										<th>Price History</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${vendorType.itemList}" varStatus="indexVal">
										<tr>
	                                        <td>
	                                        	<input type = "hidden" name= "saveId" id= "saveId" value= "${indexVal.index}">	<!-- Added by Saif 22-03-2018 -->
												<input id="check${indexVal.index+1}" type="checkbox" name="vendorItems[${indexVal.index}].itemCode" value="${item.itemCode}" />
											</td>
											<td>
												${item.itemCode}
											</td>
											<td>
												${item.itemName}
											</td>
											<td>
												<input type="text" class="form-control" name="vendorItems[${indexVal.index}].sellingRate" id="txt${indexVal.index+1}" value="0.0" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}" style="width:100px;">
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
                                    <h2 class="panel-title">Book Supply History</h2>
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
	                        <footer style="display: block;" class="panel-footer">
	                            <button type="submit" id="submitButton" value="SUBMIT" class="btn btn-primary">Submit </button>
	                        </footer>
						</section>
					</div>
				</c:otherwise>
			</c:choose>
		</form:form>			
	</div>
		</div>

 
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/icam/js/library/mapBookVendor.js"></script>
<script type="text/javascript">
function showBookPriceHistoryPopUp(id){
	var bookCode=id;
	var vendorCode=document.getElementById("vendorCode").value;
	$.ajax({
		url:'/icam/getVendorBookPriceHistory.html',
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
</body>
</html>