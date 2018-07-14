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
<title>Email Template For Edit</title>
<%@ include file="/include/include.jsp" %>
<link rel="stylesheet" href="/cedugenie/assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.css" />		
<link rel="stylesheet" href="/cedugenie/assets/vendor/summernote/summernote.css" />		
<link rel="stylesheet" href="/cedugenie/assets/vendor/summernote/summernote-bs3.css" />


<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .inner-body{
       padding:0px !important;
       }
       .mailbox-compose{
       	padding:0px !important;
       }
       .mailbox-compose .compose{
       	margin:0px !important;
       }
       .note-editor .note-editing-area .note-editable{
       	border: 1px solid #ccc;
       }
</style>
</head>
<body>

		<header class="page-header">
			<h2>Template For Email  </h2>
		</header>
		<div class="content-padding">
					<c:if test="${status eq 'fail'}">
						<div class="alert alert-danger" id="errormsgbox">
							<strong>Problem in Template Creation!!</strong>	
						</div>
					</c:if>
					<c:if test="${status eq 'success'}">
						<div class="alert alert-success" id="successboxmsgbox">
							<strong>Template is created Successfully!!</strong>	
						</div>
					</c:if>
					<c:if test="${status eq 'updatefail'}">
						<div class="alert alert-danger" id="errormsgbox">
							<strong>Problem in updating templates!!</strong>	
						</div>
					</c:if>
					<c:if test="${status eq 'updatesuccess'}">
						<div class="alert alert-success" id="successboxmsgbox">
							<strong>Template is updated Successfully!!</strong>	
						</div>
					</c:if>
					<div class="row">
						<div class="col-md-12">
							<form action="editTemplateForEvent.html" method="post">
								<section class="panel panel-primary">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Template For Email</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class= "row">
											<div class="col-md-12">
												<div class="form-group">
											 			<label class="control-label col-md-2">Template For:<span aria-required="true" class="required">*</span></label>
									         			<div class= "col-md-10">
									         				<select class="form-control" id= "templateFor" name= "templateFor" disabled required >
									         					<!-- <option value= "${emailEvent.templateFor eq 'email' || emailEvent.templateFor eq 'alert' || emailEvent.templateFor eq 'notification' || emailEvent.templateFor eq 'sms'}">${emailEvent.templateFor}</option> -->
									         					<option value= "email" ${emailEvent.templateFor eq "Email"?'selected':value} >Email</option>
									         					<option value= "alert" ${emailEvent.templateFor eq "Alert"?'selected':value}>Alert</option>
									         					<option value= "notification" ${emailEvent.templateFor eq "Notification"?'selected':value}>Notification</option>
									         					<option value= "sms" ${emailEvent.templateFor eq "SMS"?'selected':value}>SMS</option>
									         				</select>
														</div>
												</div>
											</div>
										</div><br>
										<div class= "row">
											<div class="col-md-12">
												<div class="form-group">
											 			<label class="control-label col-md-2">Subject<span aria-required="true" class="required">*</span></label>
									         			<div class= "col-md-10">
															<input type="text" class="form-control" name="emailSubject"  id= "emailSubject" value="${emailEvent.emailSubject}" required>
															<input type= "hidden" name = "oldTemplateFor" id= "oldTemplateFor" value= "${emailEvent.templateFor}">
															<input type= "hidden" name = "oldEmailSubject" id= "oldEmailSubject" value= "${emailEvent.emailSubject}">
															<input type= "hidden" name = "oldEmailBody" id= "oldEmailBody" value= "${emailEvent.emailBody}">
															<input type= "hidden" name = "oldEmailFooter" id= "oldEmailFooter" value= "${emailEvent.emailFooter}">
														</div>
												</div>
											</div>
										</div>
										
										<div class= "row">
										<div class="col-md-12">
										<div class="form-group">
										<label for="email_body" class="control-label col-md-2" style="padding-top: 25px;">Body<span aria-required="true" class="required">*</span></label>
										<div class= "col-md-10">
										<div>
											<div class="content-with-menu-container" data-mailbox data-mailbox-view="compose">
									         	<div class="inner-body">								
													<div class="mailbox-compose">
														<form class="form-horizontal form-bordered form-bordered">
															<div class="form-group">
																<div class="compose">
																	<div id="compose-field" class="compose-control">
																	${emailEvent.emailBody}
																	</div>
																</div>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
										</div>
										</div>
										</div>
										</div>
										
										<div class= "row">
											<div class="col-md-12">
												<div class="form-group">
													 <label class="control-label col-md-2">Footer<span aria-required="true" class="required">*</span></label>
											         <div class= "col-md-10">
												         <input type="text" class="form-control" name="emailFooter"  id= "emailFooter" value="${emailEvent.emailFooter}">
														<input type="hidden" class="form-control" name="emailBody"  id= "emailBody"/>
														<input type="hidden" id="templateCode" name="templateCode" value="${emailEvent.templateCode}">
													</div>
												</div>
											</div>
										</div>
									</div>
									 <div class="alert alert-warning" id="message1" style="display: none">
							  							<span></span>	
									</div>	
									<footer style="display: block;" class="panel-footer">
										<button type="submit" id="submit" class="btn btn-primary" onclick = "submitButtonCall()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/assets/vendor/bootstrap-tagsinput/bootstrap-tagsinput.js"></script>		
<script src="/cedugenie/assets/vendor/summernote/summernote.js"></script>
<script src="/cedugenie/js/academics/createStandard.editable.js"></script>
 <script src="/cedugenie/assets/javascripts/ui-elements/examples.modals.js"></script>
 <script type="text/javascript">
				function submitButtonCall()
				{
					var emailBody = $('.note-editable').html();
					document.getElementById("emailBody").value= emailBody;
				}
</script>
</body>
</html>