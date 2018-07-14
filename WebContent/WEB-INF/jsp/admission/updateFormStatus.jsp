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
						<div class="col-md-6 col-md-offset-3">
						<c:if test="${successStatus != null}">
							<div class="successbox" id="successbox" style="visibility:visible;">
								<span id="successmsg" style="visibility:visible;">Successfully Updated!</span>	
							</div>
						</c:if>
						<c:if test="${failStatus != null}">
								<div class="errorbox" id="errorbox" style="visibility: visible;">
									<span id="errormsg">Update Fail!</span>	
								</div>
						</c:if>
						 <form:form name="formStatus" method="POST" action="getAdmissionFormsForStatusUpdate.html">	
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Update Form Status</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Class</label>
                                                <select class="form-control" name="standard.standardName" id="standard">
                                                    <option value="">Select...</option>
                                                   	 <c:forEach var="standard" items="${standardList}">
														<option value="<c:out value="${standard.standardCode}"/>"><c:out value="${standard.standardName}"/></option>
													</c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Status</label>                                                
                                                <select class="form-control" name="status" id="status">
                                                    <option value="">Select...</option>
                                                    <option value="SUBMITTED">Submitted Form</option>
													<option value="RECEIVED">Received Form</option>
                                                </select>
                                            </div>
                                        </div>                                        
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" name="submit" onclick="return validateUpdateFormStatusForm();">Submit</button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>						
					</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>