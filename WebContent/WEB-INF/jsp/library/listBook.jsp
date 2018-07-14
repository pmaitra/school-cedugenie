<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>List Books</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Book List</h2>
</header>
<div class="content-padding">
	<div class="col-md-6 col-md-offset-3">
		<section class="panel">
			<header class="panel-heading">
				<div class="panel-actions">
					<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
				</div>
				<h2 class="panel-title">Category</h2>
			</header>
			<div style="display:block" class="panel-body">
				<div>
					<div class="form-group">
               			<label class="control-label">Category<span aria-required="true" class="required">*</span></label>
                       	<select class="form-control" name="bookCategoryCode" id="bookCategoryCode" onchange="categoryWiseList(this.value);">
                   		<option value="">Select...</option>
						<c:forEach var = "category" items="${categoryList}">
							<option value="${category.bookCategoryCode}">${category.bookCategoryName}</option>
						</c:forEach>
                       	</select>
               		</div>
				</div>
			</div>
		</section>
	</div>
	<div class="col-md-12" id="bookEditDiv" style="display:none;">
		<form id="editBookForm" method="post" action="editBook.html" >
		<c:choose>
			<c:when test="${listOfBooks eq null}">
				<div class="btnsarea01" style="visibility: visible;">
					<div class="alert alert-danger" id="infomsgbox" style="visibility: visible;">
						<span id="infomsgbox">No books are available now.</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="bookType" name="bookType">
				<input type="hidden" id="bookCode" name="bookCode">
				<input type="hidden" id="bookName" name="bookName">
				<input type="hidden" id="accessionNumber" name="accessionNumber">
				<input type="hidden" id="bookPlace" name="bookPlace">
				<input type="hidden" id="publishingYear" name="publishingYear">
				<input type="hidden" id="pages" name="pages">
				<input type="hidden" id="volume" name="volume">
				<input type="hidden" id="source" name="source">
				<input type="hidden" id="billNo" name="billNo">
				<input type="hidden" id="billDate" name="billDate">
				<input type="hidden" id="cost" name="cost">
				<input type="hidden" id="classNo" name="classNo">
				<input type="hidden" id="bookNo" name="bookNo">
				<input type="hidden" id="bookEntryDate" name="bookEntryDate">
				<input type="hidden" id="bookPublisher.bookPublisherName" name="bookPublisher.bookPublisherName">
				<input type="hidden" id="withdrawalNo" name="withdrawalNo">
				<input type="hidden" id="withdrawalDate" name="withdrawalDate">
				<input type="hidden" id="remarks" name="remarks">
				<input type="hidden" id="totalNumberOfBookCopiesAvailable" name="totalNumberOfBookCopiesAvailable">
				<section role="main" class="content-body">
					<section class="panel" id="bookPanel" style="display: none;">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
							</div>
					
							<h2 class="panel-title">Book List</h2>
						</header>
						<div class="panel-body">
							<table class="table table-bordered table-striped mb-none display" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
								<thead>
									<tr>
										<th>Select</th>
										<th>Category</th>
										<!-- <th>Book Code</th> -->
										<th>Book Name</th>
										<th>Accession No.</th>
										<th>Place</th>
										<th>Year</th>
										<th>Date of Entry</th>
										<th>Publisher</th>
										<th>Copies Available</th>
										<th>Pages</th>
										<th>Volume</th>
										<th>Source</th>
										<th>Bill No.</th>
										<th>Bill Date</th>
										<th>Cost</th>
										<th>Class No.</th>
										<th>Book No.</th>
										<th>Withdrawal No.</th>
										<th>Withdrawal Date</th>
										<th>Remarks</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="bookList" items="${listOfBooks}">
										<tr class="gradeC">
											<td><input type="radio" value="${bookList.bookCode}" onclick="submitBookEditForm('${bookList.bookType}','${bookList.bookCode}','${bookList.bookName}','${bookList.accessionNumber}','${bookList.bookPlace}','${bookList.publishingYear}','${bookList.pages}','${bookList.volume}','${bookList.source}','${bookList.billNo}','${bookList.billDate}','${bookList.cost}','${bookList.classNo}','${bookList.bookNo}','${bookList.bookEntryDate}','${bookList.bookPublisherId}','${bookList.withdrawalNo}','${bookList.withdrawalDate}','${bookList.remarks}');"/></td>
											<td>${bookList.bookType}</td>
											<%-- <td>${bookList.bookCode}</td> --%>
											<td>${bookList.bookName}</td>
											<td>${bookList.accessionNumber}</td>
											<td>${bookList.bookPlace}</td>
											<td>${bookList.publishingYear}</td>
											<td>${bookList.bookEntryDate}</td>
											<td>${bookList.bookPublisherId}</td>
											<td>${bookList.totalNumberOfBookCopiesAvailable}</td>
											<td>${bookList.pages}</td>
											<td>${bookList.volume}</td>
											<td>${bookList.source}</td>
											<td>${bookList.billNo}</td>
											<td>${bookList.billDate}</td>
											<td>${bookList.cost}</td>
											<td>${bookList.classNo}</td>
											<td>${bookList.bookNo}</td>
											<td>${bookList.withdrawalNo}</td>
											<td>${bookList.withdrawalDate}</td>
											<td>${bookList.remarks}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</section>
				</section>
			</c:otherwise>
		</c:choose>
		</form>
		</div>
		<div class="col-md-12" id="errorDiv" style="display:none;">
			<div class="alert alert-danger" id="errMsgBox"></div>
		</div>
		<div class="col-md-12" id="magEditDiv" style="display:none;">
		<form id="editMagazineForm" method="post" action="editMagazine.html">
			<c:choose>
				<c:when test="${listOfMagazine eq null}">
					<div class="btnsarea01" style="visibility: visible;">
						<div class="alert alert-danger" id="infomsgbox" style="visibility: visible;">
							<span id="infomsgbox">No Magazines are available now.</span>	
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<input type="hidden" id="magazineCategory" name="magazineCategory">
					<input type="hidden" id="magazineName" name="magazineName">
					<input type="hidden" id="magazinePeriod" name="magazinePeriod">
					<input type="hidden" id="magazineCost" name="magazineCost">
					<input type="hidden" id="magazinePublisher.magazinePublisherName" name="magazinePublisher.magazinePublisherName">
					<input type="hidden" id="magazineEntryDate" name="magazineEntryDate">
					<input type="hidden" id="magazineReceiveDate" name="magazineReceiveDate">
					<input type="hidden" id="magazineBillNo" name="magazineBillNo">
					<input type="hidden" id="magazineBillDate" name="magazineBillDate">
					<section role="main" class="content-body">
						<section class="panel" id="magazinePanel" style="display: none;">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Magazine List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none display" id="datatable-tabletools" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
									<thead>
										<tr>
											<th>Select</th>
											<th>Category</th>
											<th>Name</th>
											<th>Period(Days)</th>
											<th>Price</th>
											<th>Publisher</th>
											<th>Entry Date</th>
											<th>Receive Date</th>
											<th>Bill No</th>
											<th>Bill Date</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="magList" items="${listOfMagazine}">
											<tr class="gradeC">
												<td><input type="radio" name="magazineCode" value="${magList.magazineCode}" onclick="submitMagazineEditForm('${magList.magazineCode}','${magList.magazineObjectId}','${magList.magazineName}','${magList.magazinePeriod}','${magList.magazineCost}','${magList.updatedBy}','${magList.magazineEntryDate}','${magList.magazineReceiveDate}','${magList.magazineBillNo}','${magList.magazineBillDate}');"/></td>
												<td>${magList.magazineObjectId}</td>
												<td>${magList.magazineName}</td>
												<td>${magList.magazinePeriod}</td>
												<td>${magList.magazineCost}</td>
												<td>${magList.updatedBy}</td>
												<td>${magList.magazineEntryDate}</td>
												<td>${magList.magazineReceiveDate}</td>
												<td>${magList.magazineBillNo}</td>
												<td>${magList.magazineBillDate}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
					</section>
				</c:otherwise>
			</c:choose>
		</form>
	</div>
</div>	
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
/* $(document).ready(function() { 
   /* $('input[name=bookCode]').change(function(){
        $('#editBookForm').submit();
   }); 
   $('input[name=magazineCode]').change(function(){
       $('#editMagazineForm').submit();
  }); 
   $('table.display').DataTable();
});*/
$('#bookCategoryCode').change(function(){
	var category = $(this).val();
	if(category == ''){
		$('#errorDiv').css("display","block");
		$('#errMsgBox').html("Please select a category");
		$('#magEditDiv').css ("display","none");
		$('#bookEditDiv').css ("display","none");
	}
	if(category == 'BOOK_CATEGORY_1'){
		$('#errorDiv').css("display","none");
		$('#magEditDiv').css ("display","none");
		$('#bookEditDiv').css ("display","block");
	}
	if(category == 'BOOK_CATEGORY_2'){
		$('#errorDiv').css("display","none");
		$('#bookEditDiv').css ("display","none");
		$('#magEditDiv').css ("display","block");
	}
});
</script>
<script type="text/javascript" src="/icam/js/library/listBook.js"></script>
</body>
</html>