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
							<c:if test="${errorMessage ne null}">
								<div class="errorbox" id="errormsgbox" style="visibility: visible;">
									<span>${errorMessage}</span>	
								</div>
							</c:if>
			
							<c:if test="${successMessage ne null}">
								<div class="successbox" id="successboxmsgbox" style="visibility: visible;">
									<span>${successMessage}</span>	
								</div>
							</c:if>
						  	<form action="insertServerConfigurationDB.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">DB Server Configuration</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Database Server URL</label>
                                                <input type="text" class="form-control" name="jdbcURL" value="${serverConfiguration.jdbcURL}" placeholder="">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Database Server Port <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcPort" value="${serverConfiguration.jdbcPort}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database User Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcUserName" value="${serverConfiguration.jdbcUserName}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Password <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcPassword" value="${serverConfiguration.jdbcPassword}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Database Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcDatabaseName" value="${serverConfiguration.jdbcDatabaseName}" placeholder="">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Database Max Statement <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcMaxStatement" value="${serverConfiguration.jdbcMaxStatement}" placeholder="">
                                            </div>                                           
                                            <div class="form-group">
                                                <label class="control-label">Database Statement Cache Deferred Closed Thread Number <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcStatementCacheNumDeferredCloseThread" value="${serverConfiguration.jdbcStatementCacheNumDeferredCloseThread}" placeholder="">
                                            </div>
                                        </div>
                                        <div class="col-md-6"> 
                                            <div class="form-group">
                                                <label class="control-label">Database Max Idle Time (in ) <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcMaxIdleTime" value="${serverConfiguration.jdbcMaxIdleTime}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Driver Class Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcDriverClassName" value="${serverConfiguration.jdbcDriverClassName}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Max Active Connection <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcMaxActive" value="${serverConfiguration.jdbcMaxActive}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">JDBC Dialect <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcDialect" value="${serverConfiguration.jdbcDialect}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">JDBC Initial Size <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcInitialSize" value="${serverConfiguration.jdbcInitialSize}" placeholder="">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">JDBC Acquire Increment Size <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcAcquireIncrement" value="${serverConfiguration.jdbcAcquireIncrement}" placeholder="">
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