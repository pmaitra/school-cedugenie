<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Form Sale Details</title>
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
					<h2>Form Sale Details</h2>
				</header>
				<div class="content-padding">
				<c:if test="${insertStatus eq 'success'}">
					<div class="alert alert-success"  >
						<strong>${statusMessage}</strong>
					</div>
				</c:if>
				<c:if test="${insertStatus eq 'fail'}">
					<div class="alert alert-danger" >
						<strong>${statusMessage}</strong>	
					</div>
				</c:if>
	 				<div class="row">
						<div class="col-md-4 col-md-offset-4">
							<c:choose>
								<c:when test="${academicYearClass == null}">
									<div class="errorbox" id="errorbox" style="visibility:visible;" >
										<span id="errormsg">Academic Year Or Course Not Found</span>	
									</div>
								</c:when>
							<c:otherwise>
							<form:form method="POST"  name="saleFormDetails" action="submitSaleFormDetails.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>
										<h2 class="panel-title">Form Sale Details</h2>										
									</header>
									<div style="display: block;" class="panel-body">  
                                        <div class="form-group">
                                            <label class="control-label">Academic Year</label>
                                            <input type="text" class="form-control" value="${academicYearClass.academicYearName}" readonly="readonly">
											<input type="hidden" name="admissionFormYear" id="year" value="${academicYearClass.academicYearName}">
                                        </div>
									</div>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
                                            <label class="control-label">Standard<span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" id="klass" name="courseClass" required>
                                                <option value="">Select...</option>
                                                <c:forEach var="cls" items="${academicYearClass.classList}" >
													<OPTION value="${cls.standardCode}">${cls.standardName}</option>
												</c:forEach>	
                                            </select>
	                                     </div>
                                     </div>
                                     <div style="display: block;" class="panel-body">  
										<div class="form-group">
                                            <label class="control-label">Drive Name<span class="required" aria-required="true">*</span></label>
                                            <select class="form-control" id="driveName" name="admissionDriveName" required>
                                                <option value="">Select...</option>	
                                            </select>
	                                     </div>
                                     </div>
                                     <div style="display: block;" class="panel-body">  
	                                     <div class="form-group">
	                                            <label class="control-label">Total No Of Form Generated<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="noOfOpenings" id="noOfOpenings" placeholder="" pattern="^[1-9]\d*$" required >
	                                      </div> 
	                                  </div>
	                                  <div style="display: block;" class="panel-body">  
	                                      <div class="form-group">
	                                            <label class="control-label">Form Price<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="formFees" id="formFees" placeholder="" required>
	                                      </div> 
	                                  </div>
	                                  <div style="display: block;" class="panel-body">  
	                                      <div class="form-group">
	                                            <label class="control-label">No of Form Sold<span class="required" aria-required="true">*</span></label>
	                                            <input type="text" class="form-control" name="noOfFormSold" id="noOfFormSold" placeholder="" pattern="^[1-9]\d*$" required>
	                                      </div> 
	                                   </div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" type="submit" id="submit" name="submit" onclick = "return validate()">Submit</button>
										<!-- <button type="reset" class="btn btn-default">Reset</button> -->
									</footer>
								</section>
                            </form:form>
                         </c:otherwise>
                         </c:choose>
					</div>						
				</div>
			</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/js/admission/saleFormDetails.js"></script>
</body>
</html>