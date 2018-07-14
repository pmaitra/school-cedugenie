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
<title>Display Exam Seating</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
			<h2>Display Exam Seating</h2>
		</header>
	 <div class="row">
	 	<div class="col-md-8 col-md-offset-2">
	 	<section class="panel">
              <header class="panel-heading">
                  <div class="panel-actions">
                      <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                  </div>

                  <h2 class="panel-title">Display Exam Seating</h2>										
              </header>
              <div style="display: block;" class="panel-body">
				 	<div class = "form-group">
					 	
					 		<div class="col-sm-6">	
								<label class="control-label"><b>Exam Name: <span class="required" aria-required="true">*</span></b></label>
								<select class="form-control" name="exam" id="exam" required>
				                      <option value="">Select...</option>
				                      <c:forEach var="exam" items="${examList}" varStatus="i">
											<option value="${exam.examCode}">${exam.examName}</option>
									</c:forEach>
				                 </select>
							
							</div>
							<div class="col-sm-6">	
								<label class="control-label"><b>Algorithm: <span class="required" aria-required="true">*</span></b></label>
								
								<input type = "text" id = "algorithm" name = "algorithm" class = "form-control" value = "" required readonly>											
							</div>
						</div>
				 	</div>
				
			</section>
			</div>
	 </div>
	 <div class="row">
						
						<div class="col-md-8 col-md-offset-2">
				            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                                    </div>

                                    <h2 class="panel-title">Seating</h2>										
                                </header>
                                <div style="display: block;" class="panel-body">
                                	<table class="table table-bordered table-condensed mb-none rowtable_border" id = "seatingArrangements">
                                		<!-- <tr>
                                			<td>
                                				<span class="desk">56235</span>
                                				<span class="desk">56235</span>
                                				<span class="desk"></span>
                                			</td>
                                			<td><span class="desk"></span></td>
                                			<td><span class="desk"></span></td>
                                		</tr>
                                		<tr>
                                			<td><span class="desk"></span></td>
                                			<td><span class="desk"></span></td>
                                			<td><span class="desk"></span></td>
                                		</tr>
                                		<tr>
                                			<td><span class="desk"></span></td>
                                			<td><span class="desk"></span></td>
                                			<td><span class="desk"></span></td>
                                		</tr> -->
                                	</table>
                                    
                                    
                                </div>
                            </section>
						</div>
					</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %> 
<script src="/cedugenie/js/academics/createExternalExam.js"></script>
</body>
</html>