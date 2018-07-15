<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Create Purchase Order</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
        } 

</style>
<link rel="stylesheet" type="text/css" href="/cedugenie/css/common/jquery-ui.css" />
</head>
<body>

	<c:choose>
		<c:when test="${RequisitionDetails==null}">
			<div class="btnsarea01">
				<div class="errorbox" id="errorbox" style="visibility:visible;" >
					<span id="errormsg">Requisition List Not Found</span>	
				</div>
			</div>
		</c:when>
	<c:otherwise>
		<div class="row" id="container">
			<form id="purchaseOrder" action="submitBookPurchaseOrder.html" method="post">
				<form:form method="POST" id="sflcontents" name="sflcontents" action="" >
					<div id="popup_box" class="modal-block modal-header-color modal-block-info mfp-hide">  
						<section class="panel"> 
						<header class="panel-heading">
                            <h2 class="panel-title">Vendor Price History</h2>
                        </header>
                        <div class="panel-body">
                        	<div id="popUpTableDIV">
						   	<table class="table table-bordered table-striped mb-none" id = "popUpTable">
								<thead>
									<tr>
										<th>Select</th>
										<th>VendorName</th>
										<th>Price</th>				
									</tr>
								</thead>	
								<tbody id="vendorPriceTable">	
								</tbody>
							</table>
							</div>
							<div id="bodyPopUP">
								
							</div>
						</div>	
						<footer class="panel-footer">
							<div class="row">
								<div class="col-md-12 text-right">
									<button class="btn btn-info modal-dismiss" id="popupBoxYes">OK</button>
									<button class="btn btn-info modal-dismiss" id="popupBoxNo">Cancel</button>
								</div>
							</div>
 						</footer> 
<!-- 							<a id="popupBoxYes"><button type=button class="submitbtn"  >Ok</button></a> -->
<!-- 							<a id="popupBoxNo"><button type=button class="clearbtn" >Cancel</button></a>   -->
						</section>
					</div>
					<div class="col-md-6 col-md-offset-3">
	                     <section class="panel">
	                         <header class="panel-heading">
	                             <div class="panel-actions">
	                                 <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
	                             </div>
								 <h2 class="panel-title">Create Purchase Order</h2>										
	                         </header>
	                         <div style="display: block;" class="panel-body">                                       
	                             <div class="row">
	                                 <div class="col-md-6">
	                                     <div class="form-group">
	                                         <label class="control-label">Requisition ID</label>
	                                         <input type="hidden" name="bookRequisitionCode" value="${RequisitionDetails.bookRequisitionCode}"/>
	                                         <input type="text" class="form-control" name="" value="${RequisitionDetails.bookRequisitionCode}">
	                                     </div>
	                                 </div>
	                                 <div class="col-md-6">
	                                     <div class="form-group">
	                                         <label class="control-label">Open Date</label>
	                                         <input type="text" class="form-control" name=""  value="${RequisitionDetails.bookRequisitionOpenDate}">
	                                     </div>
	                                 </div>
	                                 </div>
                                 	<div class = "row"><br>
		                                 <div class="col-md-12">
		                                     <div class="form-group">
		                                         <label class="control-label">Department</label>
		                                         <input type="text" class="form-control" id="department" name="department"  value="LIBRARY DEPARTMENT" readonly>
		                                     </div>
		                                 </div>
                                   	</div>
	                               <hr>
										<table class="table table-bordered table-striped mb-none" id="departmentBudgetTable">
											<thead>
												<th>Budget Amount</th>
												<th>Total Expense</th>
												<th>Remaining Balance</th>
											</thead>
											<tbody>
											<tr class="gradeX">
												<td>
													<input type="text" class= "form-control" id="budgetAmount" readonly="readonly">
												</td>
												<td>
													<input type="text" class= "form-control" id="totalExpence" readonly="readonly">
												</td>
												<td>
													<input type="text" class= "form-control" id="balance" readonly="readonly">
												</td>
											</tr>
											</tbody>
										</table>
	                         </div>
	                     </section>
	                 </div>
	                 
	                 <div class="col-md-6 col-md-offset-3">
	                 <!-- added by ranita.sur on 08092017 -->
	                  <div class="alert alert-danger" id="warningmsg1" style="display: none">
												<span></span>	
											</div>
	                 </div>
	                 
                 <div class="col-md-12">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Create Purchase Order</h2>										
					</header>
					<div style="display: block;" class="panel-body">
	                     <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                             <thead>
                                 <tr>
                                  <!--  /* Added By ranita.sur on 03082017 for deleting row */ -->
                                     <th>Action</th>
                                    <th>Book Name</th>
									<th>Author Name</th>
									<th>Book Edition</th>
									<th>Publisher Name</th>
									<th>Total Order</th>
									<th>Selection</th>
									<th>Vendor Code</th>
									<th>Vendor</th>
									<th>Rate</th>
									<th>Discount</th>
									<th>Tax</th>
									<th>Total Price</th>
                                 </tr>
                             </thead>
                             <tbody id="tbDiv">
                             	<c:forEach var="bookrRequisitionDetails" items="${RequisitionDetails.bookRequisitionDetailsList}" varStatus="status">
                                  <tr >
                                 <!--  /* Added By ranita.sur on 03082017 for deleting row */ -->
                                  	<td>
									 <a class="on-default" href="#">
											   <i class="fa fa-minus-square delRow" disabled></i>
									</a>
									</td>
	                                  <td>
	                                  	<input type="text" class="form-control" name="bookName" readonly="readonly" value=" ${bookrRequisitionDetails.bookName}" style="width:70px;">
	                                  	<input type="hidden" class="form-control" name="genre" value="${bookrRequisitionDetails.genre}">
	                                  </td>
	                                  <td>
		                                  <input class="textfield" type="hidden" name="bookAuthorName" readonly="readonly" value=" ${bookrRequisitionDetails.bookAuthor}"/>
		                                  <input type="text" class="form-control" readonly="readonly" value="${fn:replace(bookrRequisitionDetails.bookAuthor,'~', ',')}" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<input type="text" class="form-control" name="bookEdition" readonly="readonly" value=" ${bookrRequisitionDetails.bookEdition}" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<input type="text" class="form-control" name="bookPublisher" readonly="readonly" value=" ${bookrRequisitionDetails.bookPublisher}" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<input type="text" class="form-control" id="individualTotOrder${status.index}" name="individualTotOrder" readonly="readonly" value=<c:out value="${bookrRequisitionDetails.numberOfBooksRequisitioned}"/> onblur="calculate('${status.index}');" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<a class="mb-xs mt-xs mr-xs modal-basic btn btn-info pull-right editbtn" href="#popup_box" type="button" id="vendorMapping" name="vendorMapping">Mapping</a>
	                                  </td>
	                                  <td>
	                                  	<input type="text" class="form-control" id="vendorCode${status.index}" readonly="readonly" name="vendorCode" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<input type="text" class="form-control vendorNameClass" id="vendorName" readonly="readonly" name="vendorName" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<input type="text" class="form-control" id="individualPrice${status.index}" name="individualPrice" readonly="readonly" onblur="calculate('${status.index}');" value="" onfocus="this.value='';" style="width:70px;">
	                                  </td>
	                                  <td>
                                  		<input type="text" class="form-control" id="individualDiscount${status.index}" name="individualDiscount" value="0.0" onblur="calculate('${status.index}');" onfocus="this.value='';" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<select class="form-control" id="taxStatus${status.index}" name="taxStatus" onchange="getTaxPercentages('${status.index}');">
	                                  		<option value="">Select</option>
	                                  		<c:forEach var="tax" items="${taxList}">
	                                  			<c:if test="${tax.taxStatus eq 'ACTIVE' }">
	                                  				<option value="${tax.taxCode}">${tax.taxName}</option>
	                                  			</c:if>
	                                  		</c:forEach>
	                                  	</select>
	                                  	<input type="text" class="form-control" id="tax${status.index}" name="tax" value="0.0" readonly="readonly" style="width:70px;">
	                                  </td>
	                                  <td>
	                                  	<input type="text" class="form-control" id="total${status.index}" name="totalPrice" value="0.0" readonly="readonly" style="width:70px;">
	                                  </td>
                                  </tr>
								</c:forEach>
                             </tbody>
                         </table>
                    </div>
	                <footer style="display: block;" class="panel-footer">
                         <button type="submit" id="submit" class="btn btn-primary" onclick="return validateReceivedRequisition();" value="SUBMIT">Submit </button>
                         <button type="button" class="btn btn-warning" onclick="window.location='viewRequisition.html'" value="BACK">Back</button>
	                </footer>
					</section>
	             </div> 
             </form:form>   
         </form>
	</div>	
</c:otherwise>
</c:choose>


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/library/bookRequisitionForCreatePurchaseOrder.js"></script>
<script type="text/javascript">
var buttonName = "";
$(".editbtn").each(function(){
	$(this).click(function(){
		buttonName = $(this);
		var bookName = $(this).parent().prev().prev().prev().prev().prev().find('input').val();
		var authorName = $(this).parent().prev().prev().prev().prev().find('input').val();
		var vendorObject = $(this).parent().next().next().find('input');
		var priceObject = $(this).parent().next().next().next().find('input');
		$.ajax({
	        url: '/cedugenie/getVendorsForCreatePurchaseOrder.html',
	        data: "strBookName=" + (bookName) + "&strAuthorName=" + (authorName),
	        dataType: 'json',
	        success: function(data) {
				if(data != ""){
		        	$('#bodyPopUP').css('display','none');
	        		$('#popUpTableDIV').css('display','block');
					$('#vendorPriceTable').empty();
	        		var arrSplited = data.split("@");
	        		for(var i=0;i<arrSplited.length-1;i++){
	        			var arrVendor = arrSplited[i].split(",");
	        			for(var j=0;j<arrVendor.length;j++){
	        				var vendorCode = arrVendor[0];
	        				var vendorName = arrVendor[1];
	        				var price = arrVendor[2];
	        				
	    	                var row = $('<tr>');
	    	                row.append($('<td><input type="radio" id="rad"'+i+' name="rad" value="'+vendorCode+'" /></td>'));
	    	                row.append($('<td><input class="form-control" type="text" readonly="readonly" id="vendorName"'+i+' name="vendorNames" value="'+vendorName+'"></td>'));
	    	                row.append($('<td><input class="form-control" type="text" readonly="readonly" id="price"'+i+' name="price" value="'+price+'"></td>'));  
	    	             }
	    	                $('#vendorPriceTable').append(row);
	        		}
	        		$('#popup_box').fadeIn("fast");
	       		}
	        	if(data == ""){
	        		$('#bodyPopUP').css('display','block');
	        		$('#popUpTableDIV').css('display','none');
	        		$('#vendorPriceTable').empty();
	        		//$('#bodyPopUP').empty();
					var tab = "<div class='well danger'>Vendor is not mapped yet.Enter the Vendor name at Vendor field</div>";
					$('#bodyPopUP').html(tab);
					$(vendorObject).attr('readonly',false);
	        		$(priceObject).attr('readonly',false);
	        		$('#popup_box').fadeIn("fast");
	        		
	        	}
	        }
		});
	});
	
	$("#popupBoxYes").click(function(){
		$('#vendorPriceTable input:radio').each(function(){				
			if ($(this).is(':checked')){
				var vendorCode = $(this).val();
				var vendorNames = $(this).parent().next().find('input').val();					
				var vendorPrice = $(this).parent().next().next().find('input').val();
				var vendorPriceId = $(buttonName).parent().next().next().next().find('input').attr('id');
				$(buttonName).parent().next().find('input').val(vendorCode);
				$(buttonName).parent().next().next().find('input').val(vendorNames);
				$(buttonName).parent().next().next().next().find('input').val(vendorPrice);
				
				vendorPriceId=vendorPriceId.replace('individualPrice', '');
				calculate(vendorPriceId);
			}
		});
		$('#popup_box').fadeOut("fast");
	});	
});
$("#popupBoxNo").click(function(){
	$('#popup_box').fadeOut("fast");
});
/* Added By ranita.sur on 03082017 for deleting row */
$('.delRow').on('click', function() {
	var table = document.getElementById("tbDiv");
	var rowCount = table.rows.length;
	if(rowCount>1){
	   $(this).parents('tr').remove();  
	}
	else{
		alert("At Least One Row Required");
	}
	});

</script>
</body>
</html>