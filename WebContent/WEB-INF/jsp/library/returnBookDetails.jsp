<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de" class="fixed header-dark">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Return Book</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
	.scroll-to-top{
	    display: none !important;
	}.mb-md{
		   display: none;
	}
</style>
<script type="text/javascript">
</script>
</head>
<body>

				
		 <div class="row">
<%-- 		 	<c:set var="count" value="${count + 1}" scope="page"/> --%>
<%-- 		 	<c:set var="count" value="0" scope="page" /> --%>
					 	
         <div class= "col-md-6 col-md-offset-3" id = "ratingBox" style="display: none;">
					<table id = "login-box" style="display: block;">
					 	<form:form method = "POST" class="signin" name="submitPopup">
							<div class="" id="login-box">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Book Rating</h2>										
									</header>
									
									<div style="display: block;" class="panel-body">                                       
										
                                        <div class="form-group">
                                            <label class="control-label">Book Code</label>
                                            <input id="bookCode1" name="bookCode" readonly class="form-control" type="text">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label">Comment</label>
                                            <textarea cols="23" rows="1" class="form-control" id="comment" name="comment" placeholder="Comment"> </textarea>
                                        </div>
                                        
                                        <div class="form-group">
                                            <label class="control-label">Rating</label>
                                            <select class="form-control" name='rating' id="rating">
                                            	<option value="">Rating...</option>	
												<option value="0">0</option>
												<option value="1">1</option> 
												<option value="2">2</option> 
												<option value="3">3</option> 
												<option value="4">4</option> 
												<option value="5">5</option> 
												<option value="6">6</option> 
												<option value="7">7</option> 
												<option value="8">8</option> 
												<option value="9">9</option> 
												<option value="10">10</option> 
                                            </select>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="sub1" type="submit" onclick="return submitPopupValues();">Submit</button>
		                                <button class="btn btn-warning" id="sub1" type="submit" onclick="return hidePopup();" >Cancel</button>
		                             </footer>
								</section>
							</div>
                       </form:form>
                    </table>
				</div>               
                 
               <%-- <form:form method="POST" name="userIdForReturnBook" action="submitUserIdForReturnBook.html">
					<div class="col-md-4 col-md-offset-4">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
								<h2 class="panel-title">User Details For Book Return</h2>
							</header>
							<div style="display: block;" class="panel-body">
				                <div class="form-group">
				                    <label class="control-label">User ID</label>
				                    <input type="text" class="form-control" name="userId" id="userId" value=""/>
				                </div>
							</div>
							<footer style="display: block;" class="panel-footer">
			                    <button class="btn btn-primary" type="submit" onclick = "return validateId()">Submit</button>
			                </footer>
						</section>
					</div>
				</form:form> --%>  
                                   
		 <c:choose>
			<c:when test="${bookAllocationList == null}" >
<!-- 					<div class="infomsgbox" id="infomsgbox"  style="visibility: visible;"> -->
<!-- 						<span id="infomsg"style="visibility: visible;">No books available</span>	 -->
<!-- 					</div> -->
				<div class="alert alert-danger" id = "errorBox" style="display: block;">
					<strong>No book found.</strong>
				</div>
			</c:when>
			<c:otherwise> 
			<div class="col-md-12" id = "mainForm" style="display: block;">
 			<form:form method="POST" id="sflcontents" name="bookAllocation" action = "submitReturnBookDetails.html"> 
				
				<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
					</div>
				
					<h2 class="panel-title">
						Issued Book Details For User ID :: <strong>${bookAllocationList[0].bookIssuedTo.userId}</strong>
						<input type="hidden" name="userId" value="${bookAllocationList[0].bookIssuedTo.userId}"/>
					</h2>
				</header>
				<div class="panel-body">
					<table class="table table-bordered table-striped mb-none">
						<thead>
							<tr>
								<th>Book Allocation Code</th>	
								<th>Book Code</th>						
								<th>Book ID</th>
								<th>Issued Date</th>
								<th>Expected Return Date</th>
								<th>Actual Return Date</th>	
								<th>Extended Days</th>		
								<th>Penalty</th>
								<th>Status</th>
								<th>Rating</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bookAllocation" items="${bookAllocationList}">
							<c:forEach var="bookAllocationDetails" items="${bookAllocation.bookAllocationDetails}">
							<tr class="gradeX">
							
								<td>
									${bookAllocation.bookAllocationCode}
								</td>	
								<td>
									${bookAllocationDetails.bookCode}
									<input type="hidden" id="bookCode" name="bookCode" value="${bookAllocationDetails.bookCode}"/>
								</td>				
								<td>
									${bookAllocationDetails.bookId}
								</td>
								<td>
									${bookAllocationDetails.bookIssueDate}
								</td>
								<td>
									${bookAllocationDetails.bookReturnDate}
								</td>
								<td>
									${bookAllocationDetails.actualReturnDate}
								</td>
								<td>
									${bookAllocationDetails.noOfDaysExtend}
								</td>						
								<td>
									${bookAllocationDetails.penalty}
								</td>
								<td>
									${bookAllocationDetails.status}
								</td>
								<c:choose>
									<c:when test="${bookAllocationDetails.actualReturnDate != null}">
										<td>
											<input type="checkbox" name="allocatedBookId" disabled="disabled" />
											Rating
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<input type="checkbox" name="allocatedBookId" value="${bookAllocationDetails.bookId}#${bookAllocation.bookAllocationCode}"/>
											<a href="#" name="${bookAllocationDetails.bookCode}" onclick="showHide(this);" class="returnBookDetails-popupWindow" >Rating</a>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							</c:forEach>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<footer style="display: block;" class="panel-footer">
<%-- 					<input type="hidden" id="cnt" value="${count}"/> --%>
					<input type="hidden" name="strRating" id="strRating" />
					<input type="hidden" name="strComment" id="strComment" />
					<input type="hidden" name="strBookCode" id="strBookCode" />
					<button class="btn btn-primary" type="submit" id = "submitButton">Submit</button>
				</footer>
				</section>
				
         	</form:form> 
         	</div>
          </c:otherwise>
          </c:choose>              
      </div>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/library/returnBookDetails.js"></script>
</body>
</html>