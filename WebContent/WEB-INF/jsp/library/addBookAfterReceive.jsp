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
<title>Add Book(s) To Library</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Book Details</h2>
</header>
<div class="content-padding">
	<div class="row">
		<div class="col-md-12">
		  	<form:form method="POST" id="addNewBook" name="addNewBook" action="addNewBookToCat.html">
		  		<input type="hidden" name="bookObjectId" id="bookObjectId" value="${book.bookObjectId}"/>
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Book Details</h2>										
					</header>
					<div style="display: block;" class="panel-body">
                    	<div class="row" id = "booktable">       
                        	<div class="col-md-4">
	                            <div class="form-group">
	                                <label class="control-label">Category</label>
	                                <input type="text" class = "form-control" readonly name="bookType" id="bookType" value="Book"/>
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label">Name</label>
	                                <input type="text" class="form-control" readonly id="bookName" name="bookName" value="${book.bookName}" />
	                            </div>
                                <div class="form-group">
                                	<label class="control-label">Code<span aria-required="true" class="required">*</span></label>
                                   	<c:choose>
										<c:when test="${(book.bookCode ne null) && (not empty book.bookCode)}">
											<input type="text" readonly="readonly" class="form-control" id="bookCode" name="bookCode" value="${book.bookCode}" />
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control" id="bookCode" name="bookCode" />
										</c:otherwise>
									</c:choose>
                             	</div>
                                <div class="form-group">
                                	<label class="control-label">Author</label>
                                	<c:forEach var="author" items="${book.bookAuthorList}">
                                       	<input type="text" readonly class="form-control" name="authorFullName" id="authorFullName" value="${author.authorFullName}" />
									</c:forEach>
                                </div>
                                <div class="form-group">
                                	<label class="control-label">Publisher</label>
                                    <input type="text" readonly class = "form-control" id="bookPublisherName" name="bookPublisherName" value="${book.bookPublisher.bookPublisherName}" />
								</div>
                           	 </div>
                             <div class="col-md-4"> 
                             	<div class="form-group">
                                    <label class="control-label">Description</label>
                                    <textarea class="form-control" rows="5" data-plugin-maxlength="" maxlength="140" id="description" name="bookDesc">${book.bookDesc}</textarea>
                                </div>
                                <div class="form-group">
                                	<label class="control-label">Type<span aria-required="true" class="required">*</span></label>
                                    	<select class="form-control" name="bookMediumName" id="bookMediumName">
                                        	<option value="">Select...</option>
											<option value="audio" ${book.bookMedium.bookMediumName eq 'audio'?'selected':value}>Audio</option>
											<option value="video" ${book.bookMedium.bookMediumName eq 'video'?'selected':value}>Video</option>
											<option value="printed" ${book.bookMedium.bookMediumName eq 'printed'?'selected':value}>Printed</option>
											<option value="ebook" ${book.bookMedium.bookMediumName eq 'ebook'?'selected':value}>E-Book</option>
                                       </select>
                               </div>
                               <div class="form-group">
                                   <label class="control-label">ISBN<span aria-required="true" class="required">*</span></label>
                                   <input type="text" class="form-control" id="bookIsbn" name="bookIsbn" value = "${book.bookIsbn}">
                               </div>
                               <div class="form-group">
                                   <label class="control-label">Genre</label>
                                   <input class="form-control" name="genreName" id="genreName" required readonly="readonly" value = "${book.genreId}">
                                   <%-- <select class="form-control" name="genreName" id="genreName" required>
                                       <option value="">Select...</option>
										<c:forEach var="genre" items="${genreList}">
											<option value="${genre.genreName}"${genre.genreName eq book.genre.genreName?'selected':value}>${genre.genreName}</option>
										</c:forEach>
                                   </select> --%>
                          		</div>
                       		</div>
                            <div class="col-md-4"> 
	                            <div class="form-group">
	                                <label class="control-label">Edition</label>
	                                <input type="text" readonly class = "form-control" id="bookEdition" name="bookEdition" value="${book.bookEdition}" />
	                            </div>
                                <div class="form-group">
                                    <label class="control-label">Language<span aria-required="true" class="required">*</span></label>
                                    <select class="form-control" name="bookLanguageName" id="bookLanguageName">
                                        <option value="">Select...</option>
										<c:forEach var="bookLan" items="${bookLanguageList}">
											<option value="${bookLan.bookLanguageName}" ${bookLan.bookLanguageName eq book.bookLanguage.bookLanguageName?'selected':value}>${bookLan.bookLanguageName}</option>
										</c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label class="control-label">No. Of Copies</label>
                                    <input type="text" class="form-control" readonly id="totalNumberOfBookCopies" name="totalNumberOfBookCopies" value="${book.totalNumberOfBookCopies}">
                                </div>
                                <div class="form-group">
                                    <label class="control-label">Price</label>
                                    <input type="text" readonly class = "form-control" id="price" name="price" value="${book.price}" />
                                </div>
                          	</div> 
                       	</div>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" value="SUBMIT" id="submitForm">Submit </button>
					</footer>
				</section>
        	</form:form>
		</div>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/addBookAfterReceive.js"></script>
</body>
</html>