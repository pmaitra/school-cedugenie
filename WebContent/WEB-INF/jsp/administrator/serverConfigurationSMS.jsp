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
<script>
$( document ).ready(function() {
	 var status=$("input[type=radio][name='proxy']:checked").val();
	 if(status =="NO"){
		 $( "#proxyTable" ).hide();
	 }
	 $("input[name=proxy]:radio").change(function () {
		 var status=$("input[type=radio][name='proxy']:checked").val();
		 if(status=="NO"){
			 $( "#proxyTable").hide();
		 }else{
			 $( "#proxyTable").show();
		 }
	 });
	});
</script>
</head>
<body>
	<header class="page-header">
			<h2>SMS Server Configuration</h2>	<!--  ADDED BY SAIF 29-03-2018 -->
	</header>
		<div class = "content-padding">
			<c:if test="${successMessage ne null}">
		<div class="alert alert-success" id="successboxmsgbox">
			<strong>${successMessage}</strong>	
		</div>
	</c:if>

	<c:if test="${errorMessage ne null}">
			<div class="alert alert-danger" id="errormsgbox">
				<strong>${errorMessage}</strong>	
			</div>
	</c:if>
	
 				<div class="row">
						<div class="col-md-12">
						
						<form action="insertSMSServerConfiguration.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">SMS Server Configuration</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Authentication Key <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="authkey" value="${serverConfiguration.authkey}" placeholder="" required>
												<input type="hidden" name="oldauthkey" value="${serverConfiguration.authkey}">	<!-- hidden fields are added by Saif 29-03-2018 -->
											</div> 
                                            <div class="form-group">
                                                <label class="control-label">Sender ID <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="senderId" value="${serverConfiguration.senderId}" placeholder="" required>
                                            	<input type="hidden" name="oldsenderId" value="${serverConfiguration.senderId}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Route <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="route" value="${serverConfiguration.route}" placeholder="" required>
                                            	<input type="hidden" name="oldroute" value="${serverConfiguration.route}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">SMS URL <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control"  name="smsURL" value="${serverConfiguration.smsURL}" placeholder="" required>
                                            	<input type="hidden" name="oldsmsURL" value="${serverConfiguration.smsURL}">
                                            	<input type="hidden" name="oldproxy" value="${serverConfiguration.proxy}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Proxy Enabled <span aria-required="true" class="required">*</span></label>
                                                <div style="margin-top: 5px;" class="form-group">
                                                	<c:choose>
                                                		 <c:when test="${serverConfiguration.proxy =='YES'}">
			                                                 <label class="radio-inline radio-primary"> 
			                                                      <input type="radio" checked="checked" value="YES" id="male" name="proxy"> Yes 
			                                                 </label>
			                                                  <label class="radio-inline radio-primary"> 
			                                                     <input type="radio" value="NO" id="female" name="proxy"> No 
			                                                  </label>
			                                              </c:when>
			                                              <c:otherwise>
			                                                <label class="radio-inline radio-primary"> 
			                                                      <input type="radio" value="YES" id="male" name="proxy"> Yes 
			                                                 </label>
			                                                  <label class="radio-inline radio-primary"> 
			                                                     <input type="radio" value="NO" id="female" name="proxy" checked="checked" > No 
			                                                  </label>    
			                                              </c:otherwise>
			                                           </c:choose>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Proxy UserName <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxyUser" value="${serverConfiguration.proxyUser}" placeholder="" required>
                                            	<input type="hidden" name="oldproxyUser" value="${serverConfiguration.proxyUser}">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Proxy Password <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxyPassword" value="${serverConfiguration.proxyPassword}" placeholder="" required>
                                           		<input type="hidden" name="oldproxyPassword" value="${serverConfiguration.proxyPassword}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Proxy Set <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxySet" value="TRUE" placeholder="" required>
                                            	<input type="hidden" name="oldproxySet" value="TRUE">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Proxy Host <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxyHost" value="${serverConfiguration.proxyHost}" placeholder="" required>
                                            	<input type="hidden" name="oldproxyHost" value="${serverConfiguration.proxyHost}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Proxy Port <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxyPort" value="${serverConfiguration.proxyPort}" placeholder="" required>
                                            	<input type="hidden" name="oldproxyPort" value="${serverConfiguration.proxyPort}">
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