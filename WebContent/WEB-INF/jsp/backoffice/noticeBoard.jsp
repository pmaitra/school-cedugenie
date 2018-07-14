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

			<%-- <c:if test="${noticeBoard eq null}">
			Create Notice
			</c:if>
			<c:if test="${noticeBoard ne null}">
				Update Notice
			</c:if> --%>
			
			<c:if test="${ErrorMessage ne null}">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					${ErrorMessage}
				</div>
			</c:if>
			<c:if test="${SuccessMessage ne null}">
				<div class="successbox" id="successbox" style="visibility: visible;">
					${SuccessMessage}
				</div>
			</c:if>

					<div class="row">						
						<div class="col-md-8 col-md-offset-2">						  
							<form:form id="create" name="create" action="createNotice.html" method="POST">
                                <section class="panel">
                                    <header class="panel-heading">
                                        <div class="panel-actions">
                                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                        </div>

                                        <h2 class="panel-title">Create Notice</h2>
                                    </header>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-4 control-label">Notice Name</label>
                                            <div class="col-md-5">
                                            	<input type="hidden" class="noticeCode" name="noticeCode" value="${noticeBoard.noticeCode}" />
                                                <input type="text" class="form-control" id ="noticeName" name="noticeName" placeholder="" value = "${noticeBoard.noticeName}">
                                            </div>
                                            <div class="col-md-3">
                                                <select class="form-control" id="notificationType" name="notificationType">
                                                    <option value="">Select...</option>
                                                    <option>Notification</option>
													<option>Alert</option>
													<option>Both</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-4 control-label">Notice Description</label>
                                            <div class="col-md-8">
                                                <textarea class="form-control" rows="3" data-plugin-maxlength="" maxlength="240" id ="noticeDesc" name="noticeDesc">${noticeBoard.noticeDesc}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <footer style="display: block;" class="panel-footer">
                                        <button type="submit" id="submit" name="submit" class="btn btn-primary">Submit </button>
                                        <button id="clear" type="reset" class="btn btn-default">Cancel</button>
                                    </footer>    
                                </section>
                            </form:form>
						</div>
					</div>	
					


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>