<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Add Book</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }/* 
       .datepicker-dropdown{
        display: none !important;
       } */
        #ui-datepicker-div{
       	z-index:99999 !important;
       }
       .ui-autocomplete{
       	overflow-y: scroll;
		height: 80px !important;
       } 
</style>
<link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet">
</head>
<body>
	<header class="page-header">
		<h2>Add Book/Magazines To Catalogue</h2>
	</header>
	<div class="content-padding">
		<c:if test="${uploadErrorMessage ne null }">
			<div class= "alert alert-danger">
				<strong>${uploadErrorMessage}</strong>
			</div>
		</c:if>
		<c:if test="${uploadSuccessMessage ne null }">
			<div class= "alert alert-success">
				<strong>${uploadSuccessMessage}</strong>
			</div>
		</c:if>
		<c:if test="${Message != null}">
			<div class="btnsarea01">
				<c:if test="${Message eq 'success'}">
					<div class= "alert alert-success">
						<strong>Book successfully inserted into catalogue.</strong>
					</div>
				</c:if>
				<c:if test="${Message eq 'fail'}">
					<div class= "alert alert-danger">
						<strong>Problem occured while saving into catalogue.</strong>
					</div>
				</c:if>
			</div>			
		</c:if>
		<div class= "alert alert-danger" id= "alertDiv" style= "display: none;"></div>		
		<div class="row">
			<form:form method="POST" id="addNewBook" name="addNewBook" action="addNewBook.html">
				<div class="col-md-3">
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
	                               	<select class="form-control" name="category" id="category" onchange="resourceDetails(this.value);">
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
				<div class="col-md-9">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Details</h2>
						</header>
						<div style="display: none;" class="panel-body" id="bookDiv">
                        	<div class="row">
                            	<div class="col-md-4">
                            		<div class="form-group">
                                    	<label class="control-label">Book Title<span aria-required="true" class="required">*</span></label>
                                   		<select class="form-control" id="bookName" name="bookName">
                                   			<%-- <option value="">Select..</option>
                                   			<c:forEach var="book" items="${booksList}">
                                   			<option value="${book.bookCode}">${book.bookName}</option>
                                   			</c:forEach> --%>
                                   		</select>
                                  	</div>
                            		<div class="form-group">
                                   		<label class="control-label">Date of Entry<span class="required" aria-required="true">*</span></label>
                                   		<label class="control-label">
                                       		<div class="input-group">
                                            	<span class="input-group-addon">
                                                	<i class="fa fa-calendar"></i>
                                           		</span>
                                            	<input type="text" class="form-control" name="bookEntryDate" id="bookEntryDate" readonly placeholder= "Please Enter Date.."/> 
                                          	</div>
                                    	</label>
                                  	</div>
                                	<div class="form-group">
                                    	<label class="control-label">Accession No.<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="accessionNumber" name="accessionNumber" value = "${nextAccessionNoForBook}" readonly>
                                  	</div>
                                  	<div class="form-group">
										<label class="control-label">Author<span aria-required="true" class="required">*</span></label>
										<!-- <input type="text" class="form-control" id="author" name = "author" data-role="tagsinput" data-tag-class="label label-primary"  placeholder= "Enter character value only.."> -->
										<input type="text" class="form-control" id="author" name = "author" disabled="disabled">
									</div>
	                               	<div class="form-group">
	                                   	<label class="control-label">Place<span aria-required="true" class="required">*</span></label>
	                                   	<input type="text" class="form-control" id="bookPlace" name="bookPlace" readonly placeholder= "Enter character value only">
	                               	</div>
                           			<div class="form-group">
                               			<label class="control-label">Publisher<span aria-required="true" class="required">*</span></label>
                                       	<input type="text" class="form-control" id="bookPublisherName" name="bookPublisherName" readonly onclick="getPublisherName();" placeholder= "Choose Publisher.."><!-- value="${BookStatus.bookRequisitionDetails.bookPublisher}" -->
                                   	</div>
                                </div>
                                <div class="col-md-4">
                                	<div class="form-group">
                                    	<label class="control-label">Year<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="publishingYear" name="publishingYear" readonly placeholder= "Year in 4 digit only">
                                  	</div>
                                  	<div class="form-group">
                                    	<label class="control-label">Pages<span aria-required="true" class="required">*</span></label>
                                      	<input type="text" class="form-control" name="pages" id="pages" readonly placeholder= "Enter Integer value only">
                                  	</div>
                                  	<div class="form-group">
                                    	<label class="control-label">Volume</label>
                                   		<input type="text" class="form-control" id="volume" name="volume" readonly placeholder= "Enter Volume">
                                  	</div>
	                               	<div class="form-group">
                                  		<label class="control-label">Source<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="source" name="source" readonly placeholder= "Enter character and space only">
                               		</div>
                               		<div class="form-group">
                                  		<label class="control-label">Bill No.<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="billNo" name="billNo" readonly placeholder= "Enter Bill Number">
                               		</div>
                               		<div class="form-group">
                                   		<label class="control-label">Bill Date<span class="required" aria-required="true">*</span></label>
                                   		<label class="control-label">
                                       		<div class="input-group">
                                            	<span class="input-group-addon">
                                                	<i class="fa fa-calendar"></i>
                                           		</span>
                                            	<input type="text" class="form-control" name="billDate" id="billDate" readonly placeholder= "Enter Bill Date"/> 
                                          	</div>
                                    	</label>
                                  	</div>
                                </div>
                                <div class="col-md-4">
                                	<div class="form-group">
                                    	<label class="control-label">Cost<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="cost" name="cost" readonly placeholder= "Enter Cost" value="0.00">
                                  	</div>
                                  	<div class="form-group">
                                    	<label class="control-label">Class No.<span aria-required="true" class="required">*</span></label>
                                      	<input type="text" class="form-control" name="classNo" id="classNo" readonly placeholder= "Enter Class Number">
                                  	</div>
                                  	<div class="form-group">
                                    	<label class="control-label">Book No.<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="bookNo" name="bookNo" readonly placeholder= "Enter Book Number">
                                  	</div>
	                               	<div class="form-group">
                                  		<label class="control-label">Withdrawal No.<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="withdrawalNo" name="withdrawalNo" readonly placeholder= "Enter Withdrawal Number">
                               		</div>
                               		<div class="form-group">
                                  		<label class="control-label">Withdrawal Date<span aria-required="true" class="required">*</span></label>
                                   		<label class="control-label">
                                       		<div class="input-group">
                                            	<span class="input-group-addon">
                                                	<i class="fa fa-calendar"></i>
                                           		</span>
                                            	<input type="text" class="form-control" id="withdrawalDate" name="withdrawalDate" readonly data-plugin-datepicker="" data-date-end-date="0d" placeholder= "Enter Withdrawal Date"/> 
                                          	</div>
                                    	</label>
                               		</div>
                               		<!-- <div class="form-group">
                                  		<label class="control-label">Remarks<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="remarks" name="remarks" readonly placeholder = "Only character value">
                               		</div> -->
                               		<div class="form-group">
                               			<label class="control-label">Status<span aria-required = "true" class="required">*</span></label>
                               			<select class="form-control" id="statusOfItemName" name = "statusOfBook">
                               				<option value="">Select..</option>
                               				<c:forEach var="status" items="${statusList}">
                               				<option value="${status.statusOfItemCode}">${status.statusOfItemName}</option>
                               				</c:forEach>
                               			</select>
                               		</div>
                                </div>
                         	</div>
              			</div>
              			<div id="magazineDiv" style="display:none" class="panel-body">
              				<div class="row">
              					<div class="col-md-4">
              						<div class="form-group">
                                    	<label class="control-label">Title<span aria-required="true" class="required">*</span></label>
                                   		<select class="form-control" id="magazineName" name="magazineName">
                                   			<%-- <option value="">Select..</option>
                                   			<c:forEach var="mag" items="${magazineList}">
                                   			<option value="${mag.magazineCode}">${mag.magazineName}</option>
                                   			</c:forEach> --%>
                                   		</select>
                                  	</div>
              						<div class="form-group">
                                   		<label class="control-label">Date of Entry<span class="required" aria-required="true">*</span></label>
                                   		<label class="control-label">
                                       		<div class="input-group">
                                            	<span class="input-group-addon">
                                                	<i class="fa fa-calendar"></i>
                                           		</span>
                                            	<input type="text" class="form-control" name="magazineEntryDate" id="magazineEntryDate" readonly data-plugin-datepicker="" data-date-end-date="0d"/> 
                                          	</div>
                                    	</label>
                                  	</div>
                                  	<div class="form-group">
                                    	<label class="control-label">Publisher<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="magazinePublisherName" name="magazinePublisherName" readonly>
                                  	</div>
              					</div>
              					<div class="col-md-4">
              						<div class="form-group">
                                    	<label class="control-label">Period(Days)<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="magazinePeriod" name="magazinePeriod" readonly value="0">
                                  	</div>
                                  	<div class="form-group">
                                  		<label class="control-label">Bill No.<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="magazineBillNo" name="magazineBillNo" readonly>
                               		</div>
                               		<div class="form-group">
                                   		<label class="control-label">Bill Date<span class="required" aria-required="true">*</span></label>
                                   		<label class="control-label">
                                       		<div class="input-group">
                                            	<span class="input-group-addon">
                                                	<i class="fa fa-calendar"></i>
                                           		</span>
                                            	<input type="text" class="form-control" name="magazineBillDate" id="magazineBillDate" readonly data-plugin-datepicker="" data-date-end-date="0d" /> 
                                          	</div>
                                    	</label>
                                  	</div>
              					</div>
              					<div class="col-md-4">
              						<div class="form-group">
                                   		<label class="control-label">Date of Receive<span class="required" aria-required="true">*</span></label>
                                   		<label class="control-label">
                                       		<div class="input-group">
                                            	<span class="input-group-addon">
                                                	<i class="fa fa-calendar"></i>
                                           		</span>
                                            	<input type="text" class="form-control" name="magazineReceiveDate" id="magazineReceiveDate" readonly data-plugin-datepicker="" data-date-end-date="0d"/> 
                                          	</div>
                                    	</label>
                                  	</div>
                                  	<div class="form-group">
                                    	<label class="control-label">Cost<span aria-required="true" class="required">*</span></label>
                                   		<input type="text" class="form-control" id="magazineCost" name="magazineCost" readonly value="0.00">
                                  	</div>
                                  	<div class="form-group">
                               			<label class="control-label">Status<span aria-required = "true" class="required">*</span></label>
                               			<select class="form-control" id="statusOfItemName" name = "statusOfMag">
                               				<option value="">Select..</option>
                               				<c:forEach var="status" items="${statusList}">
                               				<option value="${status.statusOfItemCode}">${status.statusOfItemName}</option>
                               				</c:forEach>
                               			</select>
                               		</div>
              					</div>
              				</div>
              			</div>
						<footer id="footerDiv" style="display: none;" class="panel-footer">
							<button class="btn btn-primary" type="submit" value="SUBMIT" onclick="return ValidateForm()" >Submit </button>
						</footer>
					</section>	
				</div>
        	</form:form>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="/cedugenie/js/library/addBook.js"></script>
<script type="text/javascript">
$("#bookEntryDate").datepicker({
	dateFormat: 'dd/mm/yy',
	maxDate: 0
});
$("#billDate").datepicker({
	dateFormat: 'dd/mm/yy',
	maxDate: 0
});
$("#withdrawalDate").datepicker({
	dateFormat: 'dd/mm/yy',
	maxDate: 0
});
$("#magazineEntryDate").datepicker({
	dateFormat: 'dd/mm/yy',
	maxDate: 0
});
$("#magazineReceiveDate").datepicker({
	dateFormat: 'dd/mm/yy',
	maxDate: 0
});
$("#magazineBillDate").datepicker({
	dateFormat: 'dd/mm/yy',
	maxDate: 0
});

</script>
</body>
</html>