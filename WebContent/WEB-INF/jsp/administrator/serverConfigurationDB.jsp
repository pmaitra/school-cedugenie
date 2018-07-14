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
		`<header class="page-header">
			<h2>DB Server Configuration</h2>	<!--  ADDED BY SAIF 28-03-2018 -->
		</header>
		<div class = "content-padding">
			<c:if test="${errorMessage ne null}">
			<div class="alert alert-danger" id="errormsgbox">
				<strong>${errorMessage}</strong>	
			</div>
		</c:if>

		<c:if test="${successMessage ne null}">
			<div class="alert alert-success" id="successboxmsgbox">
				<strong>${successMessage}</strong>	
			</div>
		</c:if>
	 <div class="row">
						<div class="col-md-12">
							
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
                                                <label class="control-label">Database Server URL<span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcURL" value="${serverConfiguration.jdbcURL}" placeholder="" required>
                                                <input type="hidden" name="oldfjdbcURL" value="${serverConfiguration.jdbcURL}">	<!-- Hidden fields are added by saif 28-03-2018 -->
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Database Server Port <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcPort" value="${serverConfiguration.jdbcPort}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcPort" value="${serverConfiguration.jdbcPort}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database User Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcUserName" value="${serverConfiguration.jdbcUserName}" placeholder="" required>
                                           		<input type="hidden" name="oldjdbcUserName" value="${serverConfiguration.jdbcUserName}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Password <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcPassword" value="${serverConfiguration.jdbcPassword}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcPassword" value="${serverConfiguration.jdbcPassword}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Database Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcDatabaseName" value="${serverConfiguration.jdbcDatabaseName}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcDatabaseName" value="${serverConfiguration.jdbcDatabaseName}">
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Database Max Statement <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcMaxStatement" value="${serverConfiguration.jdbcMaxStatement}" placeholder="" required>
                                           		<input type="hidden" name="oldjdbcMaxStatement" value="${serverConfiguration.jdbcMaxStatement}">
                                            </div>                                           
                                            <div class="form-group">
                                                <label class="control-label">Database Statement Cache Deferred Closed Thread Number <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcStatementCacheNumDeferredCloseThread" value="${serverConfiguration.jdbcStatementCacheNumDeferredCloseThread}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcStatementCacheNumDeferredCloseThread" value="${serverConfiguration.jdbcStatementCacheNumDeferredCloseThread}">
                                            </div>
                                        </div>
                                        <div class="col-md-6"> 
                                            <div class="form-group">
                                                <label class="control-label">Database Max Idle Time (in ) <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcMaxIdleTime" value="${serverConfiguration.jdbcMaxIdleTime}" placeholder="" required> 
                                            	<input type="hidden" name="oldjdbcMaxIdleTime" value="${serverConfiguration.jdbcMaxIdleTime}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Driver Class Name <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcDriverClassName" value="${serverConfiguration.jdbcDriverClassName}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcDriverClassName" value="${serverConfiguration.jdbcDriverClassName}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Database Max Active Connection <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcMaxActive" value="${serverConfiguration.jdbcMaxActive}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcMaxActive" value="${serverConfiguration.jdbcMaxActive}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">JDBC Dialect <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcDialect" value="${serverConfiguration.jdbcDialect}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcDialect" value="${serverConfiguration.jdbcDialect}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">JDBC Initial Size <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcInitialSize" value="${serverConfiguration.jdbcInitialSize}" placeholder="" required>
                                            	<input type="hidden" name="oldjdbcInitialSize" value="${serverConfiguration.jdbcInitialSize}">
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">JDBC Acquire Increment Size <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="jdbcAcquireIncrement" value="${serverConfiguration.jdbcAcquireIncrement}" placeholder="" required>
                                           		<input type="hidden" name="oldjdbcAcquireIncrement" value="${serverConfiguration.jdbcAcquireIncrement}">
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