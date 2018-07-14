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
					 <div class="row">
						<div class="col-md-12">
							<c:if test="${successMessage ne null}">
								<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
									<span>${successMessage}</span>	
								</div>
							</c:if>
		
							<c:if test="${errorMessage ne null}">
									<div class="errorbox" id="errormsgbox" style="visibility: visible;">
										<span>${errorMessage}</span>	
									</div>
							</c:if>
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
                                                <input type="text" class="form-control" name="mailServerIp" value="${serverConfiguration.mailServerIp}" placeholder="">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Server Port <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailServerPort" value="${serverConfiguration.mailServerPort}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL User Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailUserName" value="${serverConfiguration.mailUserName}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Password <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailPassword" value="${serverConfiguration.mailPassword}" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Transport Protocol <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailTransportProtocol" value="${serverConfiguration.mailTransportProtocol}" placeholder="">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">EMAIL SMTP Authorization <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailSmtpAuth" value="${serverConfiguration.mailSmtpAuth}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL SMTP Starttls <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailSmtpStarttlsEnable" value="${serverConfiguration.mailSmtpStarttlsEnable}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">EMAIL Debug <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="mailDebug" value="${serverConfiguration.mailDebug}" placeholder="">
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
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>