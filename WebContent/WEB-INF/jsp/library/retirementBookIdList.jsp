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
<title>Individual Book List</title>
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
			<header class="page-header">
				<h2>Book Id's for Retire</h2>
			</header>
			<div class="content-padding">
				<c:choose>
					<c:when test="${ViewBookIdList==null}">
						<div class="btnsarea01" style="visibility: visible;">
							<div class="infomsgbox" id="infomsgbox" style="visibility: visible;">
								<span id="infomsg">Books not available</span>
							</div>
						</div>
					</c:when>
				<c:otherwise>
					<div class="row" >
						<form:form method="POST" id="sflcontents" name="sflcontents" action="retiredBookList.html" >
							<div class="col-md-12" >
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Book Details</h2>
	                                </header>
	                                	<div class="panel-body">
		                                    <table id="rbidl" class="table table-bordered table-striped mb-none">
		                                        <thead>
		                                            <tr>
		                                                <th>Book Code</th>
														<th>Book Name</th>
														<th>Author</th>
														<th>Publisher</th>
														<th>Edition</th>
														<th>ISBN</th>
		                                            </tr>
		                                        </thead>
		                                        <tbody>
			                                        <tr class="gradeX">
														<td><c:out value="${ViewBookIdList.bookCode}"/><input readonly="readonly" type="hidden" id ="bookCode" name="bookCode"  value="${ViewBookIdList.bookCode}" /></td>
														<td><c:out value="${ViewBookIdList.bookName}"/><input readonly="readonly" type="hidden" id ="bookName" name="bookName"  value="${ViewBookIdList.bookName}" /></td>
														<td>
															<c:forEach var="bookAuthor" items="${ViewBookIdList.bookAuthorList}">
																<c:out value="${bookAuthor.authorFullName}"/> ; 
															</c:forEach>
														</td>
														<td><c:out value="${ViewBookIdList.bookPublisher.bookPublisherName}"/></td>
														<td><c:out value="${ViewBookIdList.bookEdition}"/></td>
														<td><c:out value="${ViewBookIdList.bookIsbn}"/></td>
													</tr>
		                                        </tbody>
		                                    </table>
		                               </div>
	                               </section>
							</div>
							<c:choose>
								<c:when test="${bookIdListFromDB==null}">
									<div class="alert alert-danger">
										<strong>No copy of this book exist.</strong>
									</div>
								</c:when>
							<c:otherwise>
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Book ID(s) To Retire</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
			                                        <thead>
			                                            <tr>
			                                                <th><div class="checkbox-custom checjbox-default">
			                                                <input type ="checkbox" id="selectedChecked" name="selectedChecked">
			                                                <label for="selectedChecked">Selected All</label>
			                                                </div>
			                                                </th>
															<th>Book ID</th>
															<th>Entry Date</th>
															<th>price</th>
															<th>Comment</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        	<c:forEach var="bookIdListFromDB" items="${bookIdListFromDB}" >
														<tr class="gradeX">
															<td>
																<input  type="checkbox" class="bookId" name="bookIdentity" value="${bookIdListFromDB.bookId}"/>
															</td>
															<td><c:out value="${bookIdListFromDB.bookId}"/></td>
															<td><c:out value="${bookIdListFromDB.newBookEntryDate}"/></td>
															<td>
																<c:choose>
																	<c:when test="${bookIdListFromDB.price ne null}">
																		${bookIdListFromDB.price}
																	</c:when>
																	<c:otherwise>
																		<input class="form-control" type="text" id="bookId" name="${bookIdListFromDB.bookId}"  value="" />
																	</c:otherwise>
																</c:choose>
															</td>
															<td>
															<div class="form-group">
                                        						 <textarea class="form-control" rows="4" data-plugin-maxlength="" maxlength="140" name="comment_${bookIdListFromDB.bookId}" ></textarea>
                                    				       </div>
															</td>
														</tr>
														</c:forEach>
			                                        </tbody>
			                                    </table>
			                                </div>
	                                <footer style="display: block;" class="panel-footer">
										<button class="mb-xs mt-xs mr-xs btn btn-primary" type="submit" id="Retire" value="retire" onclick = "return validateCheck();">Retire</button>
										<button type="button" class="mb-xs mt-xs mr-xs btn btn-warning" id="Back" value="back" onclick="window.location='retirementBookCodeList.html' ">Back</button>
									</footer>
	                            </section>
							</div>
						</c:otherwise>
					</c:choose>
				</form:form>
			</div>
         </c:otherwise>
     </c:choose>
 </div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/retirementBookIdList.js"></script>
<script type="text/javascript">

$(document).ready(function () {
	 
	 $("#selectedChecked").click(function () {
	        $(".bookId").prop('checked', $(this).prop('checked'));
	    });
	    
	    $(".bookId").change(function(){
	        if (!$(this).prop("checked")){
	            $("#selectedChecked").prop("checked",false);
	        }
	    });
});

</script>
</body>
</html>