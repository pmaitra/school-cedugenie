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
			
						<div class="row">
							<%-- <div class="col-md-6 col-md-offset-3">
								<form:form method="GET" id="libraryFine" name="libraryFine" action="libraryFineReceiveDetails.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Library Fine Receive Details</h2>										
									</header>
									<div style="display: block;" class="panel-body"> 
                                        <div class="col-md-8 col-md-offset-2" id ="rollNumberTable">
                                            <div class="form-group">
                                                <label class="col-md-4 control-label">User ID</label>                                                
                                                <div class="col-md-8">
                                                    <input type="text" class="form-control" name="userId" id="userId" value="">
                                                </div>
                                            </div>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" id="detailsButton" class="btn btn-primary" onclick="return checkBlank();">Get Fine Details</button>
									</footer>
									<br>
									<div class="infomsgbox" id="infomsgbox" >
										<span id="infomsg"></span>
									</div>
								</section>
								</form:form> 
						</div>  --%>
						<div class="infomsgbox" id="infomsgbox" >
										<span id="infomsg"></span>
									</div>
						<c:choose>
							<c:when test="${IssuedBookResultList == null}" >
								<c:if test="${errorMessageDisplay ne null }">
									<div class="errorbox" id="errorbox"  style="visibility:visible;">
											<span id="errormsg">${errorMessageDisplay}</span>	
									</div>	
								</c:if>
							</c:when>
						<c:otherwise>
						<div class="col-md-8 col-md-offset-2">
							<form:form method="POST" id="submitFine" name="submitFine" action="submitLibrayFine.html">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>
	
                                    <h2 class="panel-title">Penalty Details</h2>
                                </header>
                                <div class="panel-body">
                                    <table class="table table-bordered table-striped mb-none">
                                    		<tr>	
                                    			<th>
													User ID ::
												</th>
												<td>
													<input type="hidden" name="userId" readonly="readonly" value="${IssuedBookResultList[0].bookIssuedTo.userId}"/>
													${IssuedBookResultList[0].bookIssuedTo.userId}
												</td>
												<th>
													Ledger ::
												</th>
												<td>
													<select class="form-control" id="ledger" name = "ledger">
			                                           <option value="">Select...</option>
				                                          <c:forEach var="ledger" items="${ledgerList}">
															<option value="${ledger.ledgerCode}">${ledger.ledgerName}</option>
														</c:forEach>
			                                       </select>
												</td>
											</tr>
											<c:forEach var="bookAllocation" items="${IssuedBookResultList}">
											<c:set var="bookcd" value="${bookAllocation.bookAllocationCode}" />
											<tr>
												<th>Book Allocation Code :: </th>
												<td>
													<input type="hidden" id="code" name="code" readonly="readonly" value="${bookAllocation.bookAllocationCode}"/>
													${bookAllocation.bookAllocationCode}
												</td>
												<th>Issued Date :: </th>
												<td>${bookAllocation.bookIssueDate}</td>
												<th>Expected Return Date :: </th>
												<td>${bookAllocation.bookReturnDate}</td>
											</tr>
											<tr>
												<th>Book Code</th>						
												<th>Book ID</th>
												<th>Extended Days</th>		
												<th>Penalty</th>
												<th>Payment</th>
											</tr>
											<c:forEach var="bookAllocationDetails" items="${bookAllocation.bookAllocationDetails}">
											<c:if test="${bookAllocationDetails eq null}">
												<c:out value="vcg"></c:out>
											</c:if>	
											<tr>					
												<td>
													<input type="hidden" id="bookCode" name="bookCode" readonly="readonly" value="${bookAllocationDetails.bookCode}"/>
													${bookAllocationDetails.bookCode}
												</td>				
												<td>
													<input type="hidden" id="bookId" name="desc" readonly="readonly" value="${bookAllocationDetails.bookId}"/>
													${bookAllocationDetails.bookId}
												</td>
												<td>${bookAllocationDetails.noOfDaysExtend}</td>						
												<td>${bookAllocationDetails.penalty}</td>			
												<td>
													<c:if test="${bookAllocationDetails.updatedBy eq null}">
														<button type="submit" class="btn btn-primary" id="${bookcd}"  value="Submit" onclick="return submitPopupValues('${bookAllocationDetails.bookId}',this.id,${bookAllocationDetails.penalty});">Submit</button>
													</c:if>		
													<c:if test="${bookAllocationDetails.updatedBy ne null}">
														<button type="button" class="btn btn-success" id="paid"  value="${bookAllocationDetails.updatedBy}" disabled="disabled" >Paid</button>
													</c:if>	
												</td>					
											</tr>
											</c:forEach>
										</c:forEach>
                                    </table>
                                </div> 
                                <input type="hidden" name="strBookAllocation" id="strBookAllocation"/>
								<input type="hidden" name="strBookId" id="strBookId" />
								<input type="hidden" name="struId" id="struId" value="${IssuedBookResultList[0].bookIssuedTo.userId}"/>
								<input type="hidden" name="strPenalty" id="strPenalty" />
                                <div class="warningbox" id="warningbox" >
									<span id="warningmsg"></span>	
								</div>
                            </section>
                            </form:form>
						</div>
                       </c:otherwise>
                       </c:choose>
					</div>
					



<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
var bookCode="";
var bookId="";
function submitPopupValues(bookid,bookcode,penalty) {
	document.getElementById("strBookAllocation").value = bookcode;		
	document.getElementById("strBookId").value = bookid;
	document.getElementById("strPenalty").value = penalty;
	return true;
}
function checkBlank(){
	var uId = document.getElementById("userId").value;
	 if(uId == ""){
		document.getElementById("errorbox").style.visibility="collapse";
		document.getElementById("infomsgbox").style.visibility="visible";
		document.getElementById("infomsg").innerHTML="Enter the User Id.";
		return false;
	} 
	if(uId != ""){
		document.getElementById("errorbox").style.visibility="collapse";
		document.getElementById("infomsgbox").style.visibility="collapse";
		document.getElementById("infomsgbox").innerHTML="";
		return true;
	}
}
</script>
</body>
</html>