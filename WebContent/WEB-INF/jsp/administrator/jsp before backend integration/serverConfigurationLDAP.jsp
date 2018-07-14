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
						 <form action="insertServerConfigurationLDAP.html" method="post">	
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
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Directory Server Configuration</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Directory Server Type</label>
                                                <select class="form-control" name="directoryServerType" id="directoryServerType">
                                                    <option value="">Select...</option>
                                                    	<c:choose>
															<c:when  test="${serverConfiguration.directoryServerType eq 'msad'}">	
																<option value="msad" selected="selected">MS Active Directory</option>
															</c:when>					
															<c:otherwise>					
																<option value="msad">MS Active Directory</option>
															</c:otherwise>
														</c:choose>
					
														<c:choose >
															<c:when test="${serverConfiguration.directoryServerType eq 'ldap'}">
																<option value="ldap" selected="selected">Apache LDAP</option>
															</c:when>					
															<c:otherwise>
																<option value="ldap">Apache LDAP</option>
															</c:otherwise>
														</c:choose>	
                                                </select>
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Directory Server URL <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control"  name="directoryServerUrl" value="${serverConfiguration.directoryServerUrl}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Directory Server Security Authentication Type <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="directoryServerSecurityAuthenticationType" placeholder="" value="${serverConfiguration.directoryServerSecurityAuthenticationType}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Directory Server Port <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="directoryServerPort" value="${serverConfiguration.directoryServerPort}" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Directory Server User DN <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="directoryServerUserDN" value="${serverConfiguration.directoryServerUserDN}" placeholder="">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Directory Server Base DN <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="directoryServerBaseDN" value="${serverConfiguration.directoryServerBaseDN}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Directory Server Password <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="directoryServerPassword" value="${serverConfiguration.directoryServerPassword}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Directory Server Filter <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="directoryServerFilter" value="${serverConfiguration.directoryServerFilter}" placeholder="">
                                            </div>
                                        </div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" id="submit">Submit </button>
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