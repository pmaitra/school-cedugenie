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
<title>Edit Book</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

			<header class="page-header">
				<h2>Edit Book</h2>
			</header>
			<div class="content-padding">
					<div class="row">
						<div class="col-md-12">
						  	<form:form method="POST" action="updateBook.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Edit Book</h2>										
									</header>
									<div style="display: block;" class="panel-body">
                                       <div class="row">
                                       		<div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Category</label>
                                                    <input type="text" class="form-control" name="bookType" id="bookType" readonly="readonly" value="${book.bookType}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Code</label>
                                                    <input type="text" class="form-control" id="bookCode" name="bookCode" readonly="readonly" value="${book.bookCode}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Book Title <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="bookName" name="bookName" readonly="readonly" value="${book.bookName}" pattern="^[a-zA-Z ]*$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Date of Entry</label>
                                                    <label class="control-label">
			                                       		<div class="input-group">
			                                            	<span class="input-group-addon">
			                                                	<i class="fa fa-calendar"></i>
			                                           		</span>
			                                            	<input type="text" class="form-control" data-plugin-datepicker="" id="bookEntryDate" name="bookEntryDate" readonly="readonly" value="${book.bookEntryDate}" required>
			                                          	</div>
			                                    	</label>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Year <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="publishingYear" name="publishingYear" readonly="readonly" value="${book.publishingYear}" pattern="^\d{4}$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Source</label>
                                                    <input type="text" class="form-control" id="source" name="source" readonly="readonly" value="${book.source}" pattern="^[a-zA-Z0-9 ]*$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Cost <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="cost" name="cost" readonly="readonly" value="${book.cost}" pattern="^[+]?[0-9]+(\.[0-9]+)?$" required>
                                                </div>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Accession No. <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="accessionNumber" name="accessionNumber" readonly="readonly" value="${book.accessionNumber}">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Place</label>
                                                    <input type="text" class="form-control" id="bookPlace" name="bookPlace" readonly="readonly" value="${book.bookPlace}" pattern="^[a-zA-Z0-9 ]*$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Pages <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="pages" name="pages" readonly="readonly" value="${book.pages}" pattern="^[0-9]+$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Bill No.</label>
                                                    <input type="text" class="form-control" id="billNo" name="billNo" readonly="readonly" value="${book.billNo}" pattern="^[a-zA-Z0-9 -/]*$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Class No. <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="classNo" name="classNo" readonly="readonly" value="${book.classNo}" pattern="^[0-9]+$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Withdrawal Date</label>
                                                    <label class="control-label">
			                                       		<div class="input-group">
			                                            	<span class="input-group-addon">
			                                                	<i class="fa fa-calendar"></i>
			                                           		</span>
			                                            	<input type="text" class="form-control" data-plugin-datepicker="" id="withdrawalDate" name="withdrawalDate" readonly="readonly" value="${book.withdrawalDate}" required>
			                                          	</div>
			                                    	</label>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Withdrawal No.</label>
                                                    <input type="text" class="form-control" id="withdrawalNo" name="withdrawalNo" readonly="readonly" value="${book.withdrawalNo}" pattern="^[a-zA-Z0-9 -/]*$" required>
                                                </div>
                                             </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Author <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="" name="" readonly="readonly" value="">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Publisher</label>
                                                    <input type="text" class="form-control" id="bookPublisher.bookPublisherName" name="bookPublisher.bookPublisherName" pattern="^[a-zA-Z ]*$" readonly="readonly" value="${book.bookPublisher.bookPublisherName}" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Volume <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="volume" name="volume" readonly="readonly" value="${book.volume}" pattern="^[0-9]+$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Bill Date</label>
                                                    <label class="control-label">
			                                       		<div class="input-group">
			                                            	<span class="input-group-addon">
			                                                	<i class="fa fa-calendar"></i>
			                                           		</span>
			                                            	<input type="text" class="form-control" data-plugin-datepicker="" id="billDate" name="billDate" readonly="readonly" value="${book.billDate}" required>
			                                          	</div>
			                                    	</label>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Book No. <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" id="bookNo" name="bookNo" readonly="readonly" value="${book.bookNo}" pattern="^[0-9]+$" required>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label">Remarks</label>
                                                    <input type="text" class="form-control" id="remarks" name="remarks" readonly="readonly" value="${book.remarks}" pattern="^[a-zA-Z0-9 ]*$">
                                                </div>
                                            </div>
                                            </div>
                                        </div>
									
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" value="SUBMIT" id="submitForm" disabled="disabled" onclick="return validateEditForm();">Submit </button>
										<button class="btn btn-primary" type="button" id="Edit" name="Edit" value="EDIT" onClick="activeForm();">Edit</button>
									</footer>
								</section>
                            </form:form>
						</div>
					</div>
				</div>
				
	

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/editBook.js"></script>
</body>
</html>