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
<title>Email</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .classEmailDetailsUnread{
       		color: #000 !important;
       		font-weight: bold !important;
       }
</style>
</head>
<body>
	<header class="page-header">
			<h2>INBOX</h2>
		</header>
		<c:if test="${updateResponse eq 'success'}">
			<div class="alert alert-success"  >
				<strong>Mails Deleted</strong>	
			</div>
		</c:if>
		<c:if test="${updateResponse eq 'fail'}">
			<div class="alert alert-danger" >
				<strong>Failed To Delete Mails.</strong>	
			</div>
		</c:if>
				<c:if test="${emailItem eq 'Inbox'}">
				<c:url value="/getEmailDetailsPaging.html" var="pagedLink">
				 	<c:param name="p" value="~"/>
				</c:url>
				<div class="row">
					<div class="col-md-12">
						<form method="POST" action="inactiveForMailInBox.html">
						 <section class="panel">
							<header class="panel-heading">
                                <h2 class="panel-title">Inbox(Unread: ${emailNotification.newNotification})</h2>
							</header>
							<div class="panel-body">
								<table class="table mb-none" id="datatable-tabletools ">
                                    <thead>
                                        <tr>
                                            <th><input  type="checkbox"  name="select_all" id="select_all">All</th> 
                                            <th>From</th>
                                            <th>Subject</th>
                                            <th>Date & Time</th>
                                        </tr>
                                    </thead>
									<tbody>
										<c:forEach var="emailDetails" items="${emailDetailsList}">			
										<tr>
											 <td>
												<input type="checkbox" name="emailDetailsCode" id="emailDetailsCode" value="${emailDetails.emailDetailsCode}" >
											</td> 
											<td>
												<c:choose>
													<c:when test="${emailDetails.status eq 'f'}">
														<%-- <a class="classEmailDetailsUnread" href="emailContent.html?emailId=${emailDetails.emailDetailsCode}">${emailDetails.emailDetailsSender}</a> --%>
														<a href="#" class="mb-xs mt-xs mr-xs modal-basic classEmailDetailsUnread" onclick = "showEmailDetails('${emailDetails.emailDetailsCode}')">${emailDetails.emailDetailsSender}</a>
													</c:when>
													<c:otherwise>
														<%-- <a class="classEmailDetails" href="emailContent.html?emailId=${emailDetails.emailDetailsCode}">${emailDetails.emailDetailsSender}</a> --%>
														<a href="#" class="mb-xs mt-xs mr-xs modal-basic" onclick = "showEmailDetails('${emailDetails.emailDetailsCode}')">${emailDetails.emailDetailsSender}</a>
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${emailDetails.status eq 'f'}">
														<%-- <a class="classEmailDetailsUnread" href="emailContent.html?emailId=${emailDetails.emailDetailsCode}">${emailDetails.emailDetailsSubject}</a> --%>
														<a href="#"  class="mb-xs mt-xs mr-xs modal-basic classEmailDetailsUnread" onclick = "showEmailDetails('${emailDetails.emailDetailsCode}')">${emailDetails.emailDetailsSubject}</a>
													</c:when>
													<c:otherwise>
														<%-- <a class="classEmailDetails" href="emailContent.html?emailId=${emailDetails.emailDetailsCode}">${emailDetails.emailDetailsSubject}</a> --%>
														<a href="#"  class="mb-xs mt-xs mr-xs modal-basic" onclick = "showEmailDetails('${emailDetails.emailDetailsCode}')">${emailDetails.emailDetailsSubject}</a>
	
													</c:otherwise>
												</c:choose>
											</td>
											<td>
												<c:choose>
													<c:when test="${emailDetails.status eq 'f'}">
														<%-- <a class="classEmailDetailsUnread" href="emailContent.html?emailId=${emailDetails.emailDetailsCode}">${emailDetails.time}</a> --%>
														<a href="#"  class="mb-xs mt-xs mr-xs modal-basic classEmailDetailsUnread" onclick = "showEmailDetails('${emailDetails.emailDetailsCode}')">${emailDetails.time}</a>
													</c:when>
													<c:otherwise>
														<%-- <a class="classEmailDetails" href="emailContent.html?emailId=${emailDetails.emailDetailsCode}">${emailDetails.time}</a> --%>
														<a href="#"  class="mb-xs mt-xs mr-xs modal-basic" onclick = "showEmailDetails('${emailDetails.emailDetailsCode}')">${emailDetails.time}</a>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<footer style="display: block;" class="panel-footer">
								<button class="btn btn-primary" id = "submit" >Delete </button>
							</footer>
							<!-- <div id="modalInfo" class="modal-block modal-header-color modal-block-info mfp-hide">
                                <section class="panel">
                                    <header class="panel-heading">
                                        <h2 class="panel-title">Approver Group Name - PO_Approver</h2>
                                    </header>
                                    <div class="panel-body">
                                        <table class="table table-bordered table-striped mb-none" id = "emailDetails">
                                            <thead>
                                                <tr>
                                                    <th>Message Content</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                                    <footer class="panel-footer">
                                        <div class="row">
                                            <div class="col-md-12 text-right">
                                                <a href="getEmailDetails.html" target="_blank" class="btn btn-info modal-dismiss" id="modalDismiss">OK</a>
                                            </div>
                                        </div>
                                    </footer>
                                </section>
                            </div> -->
						</section>
					</form>
					</div>
				</div>
			</c:if>

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!--  <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script> -->
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">

$('#select_all').click(function(event) {
	  if(this.checked) {
	      // Iterate each checkbox
	      $(':checkbox').each(function() {
	          this.checked = true;
	      });
	  }
	  else {
	    $(':checkbox').each(function() {
	          this.checked = false;
	      });
	  }
	});

function showEmailDetails(emailCode){

	window.location="emailContent.html?emailId="+emailCode;
	
}

</script>
</body>
</html>