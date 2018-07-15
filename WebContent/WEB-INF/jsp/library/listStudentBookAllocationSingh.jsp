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
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("newCommodityName"+rowId).removeAttribute("disabled");
	document.getElementById("commodityDesc"+rowId).removeAttribute("disabled");
	document.getElementById("threshold"+rowId).removeAttribute("disabled");
	document.getElementById("commodityType"+rowId).removeAttribute("disabled");
	document.getElementById("commoditySubType"+rowId).removeAttribute("disabled");
	
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	alert("In saveData :: "+rowId);
	document.getElementById("saveId").value=rowId;
	//window.location="editHostel.html?saveId="+rowId;
	document.editCommodity.submit();
};
</script>
</head>
<body>


					<!-- start: page -->
					<div class="row">
					
					
					<c:choose>
							<c:when test="${bookRequestList == null}" >
								<div class="btnsarea01" style="visibility: visible;">
									<div class="errorbox" id="errorbox" >
										<span id="errormsg">No Allocated Books Found</span>	
									</div>
								</div>
							</c:when>
					<c:otherwise>
					<c:forEach var="bookRequestList" items="${bookRequestList}">
						<div class="col-md-4 col-md-offset-4">
							<section class="panel">
								<header class="panel-heading">
									<div class="panel-actions">
										<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
									</div>

									<h2 class="panel-title">List Student Book Allocation</h2>										
								</header>
								<div style="display: block;" class="panel-body">
                                      
                                    <div class="form-group">
                                        <label class="control-label">Requested ID</label>
                                        ${bookRequestList.bookRequestCode}
										<input type="hidden" name="bookRequestedId" value="${bookRequestList.bookRequestCode}"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">User ID</label>
                                        ${bookRequestList.bookRequestFor.userId}
										<input type="hidden" name="userId" value="${bookRequestList.bookRequestFor.userId}"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label">Name</label>
                                        ${bookRequestList.bookRequestFor.firstName} ${bookRequestList.bookRequestFor.middleName} ${bookRequestList.bookRequestFor.lastName}
                                    </div>
                                    
								</div>
							</section>
						</div>
						<div class="col-md-12">	
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Request Details</h2>
                                </header>
		                                <div class="panel-body">
		
		                                    <table class="table table-bordered table-striped mb-none">
		                                        <thead>
		                                            <tr>
		                                                <th>Book Code</th>					
														<th>Book Name</th>
														<th>Book ID</th>
														<th>Open Date</th>
														<th>Close Date</th>
		                                            </tr>
		                                        </thead>
		                                        <tbody>
		                                        
		                                        	<c:forEach var="bookRequestResults" items="${bookRequestList.bookRequestDetailsList}">
													<tr class="gradeX">
														<td>${bookRequestResults.bookCode}</td>	
														<td>${bookRequestResults.bookName}</td>	
														<td>${bookRequestResults.bookId}</td>				
														<td>${bookRequestList.bookRequestOpenDate}</td>
														<td>${bookRequestList.bookRequestCloseDate}</td>
													</tr>
													</c:forEach>
		                                        
		                                        </tbody>
		                                    </table>
		                                </div>
                            </section>
						</div>
						
	</c:forEach>

						</c:otherwise>
						</c:choose>
						
						
						
					</div>
                   
					<!-- end: page -->


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>