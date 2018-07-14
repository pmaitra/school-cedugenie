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
<title>Configure Rest APIs</title>
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
			<h2> Configure Rest APIs</h2>
		</header>
		` <c:if test="${errorMessage ne null}">
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
							
						  	<form action="insertConfigureRestAPIs.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Configure Rest APIs</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                        
										<div class="col-md-6">
                                            <div class="form-group">
                                                <label class="control-label">Send Programme Details<span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="programDetails"   value="${URIForSendingProgrammeDetails}"  placeholder="" required>
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">New Candidates <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="newCandidate"  value="${URIForSendLocationDetails}" placeholder="" required>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Scrutinized Candidates <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="scrutinizedCandidate" value="${URIForNewCandidates}" placeholder="" required>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Send Location Details <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="locationDetails"  value="${URIForScrutinizedCandidates}" placeholder="" required>
                                            </div>
                                        </div>
                                         <div class="col-md-6"> 
                                           <div class="form-group">
                                                <label class="control-label">Selected Candidates <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="selectedCandidates"  value="${URIForSelectedCandidates}"  placeholder="" required>
                                            </div> 
                                            <div class="form-group">
                                                <label class="control-label">Admitted Candidates <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="admittedCandidates"  value="${URIForAdmittedCandidates}"  placeholder="" required>
                                            </div>                                           
                                            <div class="form-group">
                                                <label class="control-label">Alumni Data <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="alumniData"  value="${URIForSendingAlumniDetails}"  placeholder="" required>
                                            </div>
                                             <div class="form-group">
                                                <label class="control-label">Display Notice <span aria-required="true" class="required">*</span></label>
                                                <input type="text" class="form-control" name="displayNotice"  value="${URIForPublishNotice}" placeholder="" required>
                                            </div>
                                        </div>
									</div>
									<!-- <footer style="display: block;" class="panel-footer">
										<button type="submit" id="submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer> -->
								</section>
                            </form>
						</div>
                         
					</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>