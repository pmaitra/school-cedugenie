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
                                                <input type="text" class="form-control" name="authkey" value="${serverConfiguration.authkey}" placeholder="">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Sender ID <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="senderId" value="${serverConfiguration.senderId}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Route <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="route" value="${serverConfiguration.route}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">SMS URL <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control"  name="smsURL" value="${serverConfiguration.smsURL}" placeholder="">
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
                                                <input type="text" class="form-control" name="proxyUser" value="${serverConfiguration.proxyUser}" placeholder="">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Proxy Password <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxyPassword" value="${serverConfiguration.proxyPassword}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Proxy Set <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxySet" value="TRUE" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Proxy Host <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxyHost" value="${serverConfiguration.proxyHost}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Proxy Port <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="proxyPort" value="${serverConfiguration.proxyPort}" placeholder="">
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