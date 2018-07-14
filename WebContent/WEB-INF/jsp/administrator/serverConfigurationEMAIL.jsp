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
       } .mb-md{
       	   display: none;
       }
</style>
</head>
<body>
	<header class="page-header">
			<h2>Email Server Configuration</h2>	<!--  ADDED BY SAIF 29-03-2018 -->
	</header>
		<div class = "content-padding">
			<c:if test="${successMessage ne null}">
		<div class="alert alert-success" id="successboxmsgbox">
			<strong>${successMessage}</strong>	
		</div>
	</c:if>

	<c:if test="${errorMessage ne null}">
			<div class="alert alert-danger" id="errormsgbox" >
				<strong>${errorMessage}</strong>	
			</div>
	</c:if>
					 <div class="row">
						<div class="col-md-12">
							
							  <form action="insertServerConfigurationEMAIL.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">EMAIL Server Configuration</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Server IP <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailServerIp" value="${serverConfiguration.mailServerIp}" placeholder="" required>
                                            	<input type="hidden" name="oldmailServerIp" value="${serverConfiguration.mailServerIp}">	<!-- Hidden fields are added by Saif 29-03-2018 -->
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Server Port <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailServerPort" value="${serverConfiguration.mailServerPort}" placeholder="" required>
                                            	<input type="hidden" name="oldmailServerPort" value="${serverConfiguration.mailServerPort}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL User Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailUserName" value="${serverConfiguration.mailUserName}" placeholder="" required>
                                            	<input type="hidden" name="oldmailUserName" value="${serverConfiguration.mailUserName}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Password <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailPassword" value="${serverConfiguration.mailPassword}" placeholder="" required>
                                            	<input type="hidden" name="oldmailPassword" value="${serverConfiguration.mailPassword}">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Transport Protocol <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailTransportProtocol" value="${serverConfiguration.mailTransportProtocol}" placeholder="" required>
                                           		<input type="hidden" name="oldmailTransportProtocol" value="${serverConfiguration.mailTransportProtocol}">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">EMAIL SMTP Authorization <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailSmtpAuth" value="${serverConfiguration.mailSmtpAuth}" placeholder="">
                                            	<input type="hidden" name="oldmailSmtpAuth" value="${serverConfiguration.mailSmtpAuth}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL SMTP Starttls <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailSmtpStarttlsEnable" value="${serverConfiguration.mailSmtpStarttlsEnable}" placeholder="" required>
                                            	<input type="hidden" name="oldmailSmtpStarttlsEnable" value="${serverConfiguration.mailSmtpStarttlsEnable}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Debug <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailDebug" value="${serverConfiguration.mailDebug}" placeholder="" required>
                                           		<input type="hidden" name="oldmailDebug" value="${serverConfiguration.mailDebug}">
                                            </div>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" id="submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
                         
					</div>
		</div>
		
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>