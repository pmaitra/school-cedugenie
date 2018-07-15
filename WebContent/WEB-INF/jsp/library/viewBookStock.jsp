<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>View Book Stock</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Book Stock And Profile</h2>
</header>
<div class="content-padding">
	<div class="row">
	<c:choose>
		<c:when test="${bookListFromDB==null}">
			<div id="errorbox" style="visibility:visible;">
				<span class="errormsg">Book Stock Not Available</span>
			</div>
		</c:when>
		<c:otherwise>
		<div class="btnsarea01">
			<div class="warningbox" id="warningbox" >
				<span id="warningmsg"></span>	
			</div>
		</div>
			<div class = "row" id = "viewBookStock">
				<section role="main" class="content-body">
					<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								<h2 class="panel-title">View Book Stock</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" data-swf-path="/cedugenie/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Book Code</th>
											<th>Book Name</th>
											<th>Available</th>
											<th>Lended</th>
											<th>Reserved</th>
											<th>Total Stock</th>
											<th>Rating Transparency</th>
											 <th>Book Profile</th> 
											<th>Create Requisition</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="book" items="${bookListFromDB}">
											<tr class="gradeC">
												<td><c:out value="${book.bookCode}"></c:out></td>
												<td><c:out value="${book.bookName}"></c:out></td>
												<td><c:out value="${book.totalNumberOfBookCopiesAvailable}"></c:out></td>
												<td><c:out value="${book.totalNumberOfBookCopiesLended}"></c:out></td>
												<td><c:out value="${book.totalNumberOfBookCopiesReserved}"></c:out></td>
												<td><c:out value="${book.totalNumberOfBookCopies}"></c:out></td>
												<td>
													<a class="modal-basic alertLink" href="#popup_box" id="${book.averageBookRating}~${book.userBookRating}~${book.bookCode}~${book.bookName}" >Transparency</a>
												</td>
												<td>
													<a class="profile" href="viewLendingHistory.html?bookCode=${book.bookCode}">Profile</a>
												</td>
												<td><a class="profile" href="createRequisition.html?bookCode=${book.bookCode}">Create Requisition</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>			
					</section>
					
					<div id="popup_box" class="modal-block modal-header-color modal-block-info mfp-hide" style="max-width: 500px;">  
						<section class="panel"> 
							<header class="panel-heading">
	                            <h2 class="panel-title">Transparancy</h2>
	                        </header>
	                        <div class="panel-body">
	                        	<div class= "col-md-6">
	                        		<div class="form-group">
                                        <label class="control-label"><b>Book Name:</b></label>
                                        <p id="libraryRatingTransparancyBookName"></p>
                                    </div>
	                        	</div>
	                        	<div class= "col-md-6">
	                        		<div class="form-group">
                                        <label class="control-label"><b>Book Code:</b></label>
                                        <p id="libraryRatingTransparancyBookCode"></p>
                                    </div>
	                        	</div>
	                        	<div id="libraryRatingTransparancyDialog">
								   	<table class="table table-bordered table-striped mb-none" id = "popUpTable">
										<thead>
											<tr>
												<th>Rating Type</th>
												<!-- <th>Policy</th> -->
												<th>Average Rating On 10</th>
											</tr>
										</thead>
										<tbody id="libraryRatingTransparancyDialogTable">	
										</tbody>
									</table>
								</div>
							</div>
							<footer class="panel-footer">
								<div class="row">
									<div class="col-md-12 text-right">
										<button class="btn btn-info modal-dismiss" id="popupBoxYes">Close</button>
									</div>
								</div>
	 						</footer> 
						</section>
					</div>
				</div>
			</c:otherwise>
		</c:choose>		
	</div>
</div>




<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script> 
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
<script type="text/javascript" src="/cedugenie/js/library/viewBookStock.js"></script>
<script type="text/javascript">
var buttonName = "";
$(".alertLink").each(function(){
	$(this).click(function(){
		buttonName = $(this);
		var ratingDesc = this.id;
		var rating = ratingDesc.split('~');
		var bookName = rating[3];
		var bookCode = rating[2];
		var systemRating = rating[1];
		var userRating = rating[0];
		
		$('#libraryRatingTransparancyBookName').html(bookName);
		$('#libraryRatingTransparancyBookCode').html(bookCode);
		
      	$('#libraryRatingTransparancyDialog').css('display','block');
      	$('#libraryRatingTransparancyDialog').css('margin-top','15px');
		$('#libraryRatingTransparancyDialogTable').empty();
		
        var row1 = $('<tr>');
        row1.append($('<td>System Rating</td>'));
        /* row1.append($('<td>Rating from "Book Rating Policy" for lending frequency</td>')); */
        row1.append($('<td>'+systemRating+'</td>'));  
        var row2 = $('<tr>');
        row2.append($('<td>User Rating</td>'));
       /*  row2.append($('<td><value="(Sum Of Rating/Sum Of Rating Frequency)"></td>')); */
        row2.append($('<td>'+userRating+'</td>'));
        
   	    $('#libraryRatingTransparancyDialogTable').append(row1);
   	 	$('#libraryRatingTransparancyDialogTable').append(row2);
   	 	
       	$('#popup_box').fadeIn("fast");
	});
	
	$("#popupBoxYes").click(function(){
		$('#popup_box').fadeOut("fast");
	});	
});
</script>
</body>
</html>