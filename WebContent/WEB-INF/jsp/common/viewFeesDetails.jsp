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
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
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
						<div class="col-md-8 col-md-offset-2">
						  <form:form >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<!-- <h2 class="panel-title">Fees Details</h2>	 -->									
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            <div class="col-md-8">
                                                <div class="form-group">
                                                    <label class="control-label"><b>Program Name  ::  </b></label>
                                                    <input type="hidden" class="form-control" name="coursecode" id="coursecode" value="${course.courseCode}">                                                    
                                               		${course.courseName}
                                                </div>
                                            </div>
                                         </div>
                                         <div class="row">
                                         <div class="col-md-12">
				                            <section class="panel">
				                                <!-- <header class="panel-heading">
				                                    <h2 class="panel-title"></h2>
				                                </header> -->
				                                	<div class="panel-body">
						                                    <table class="table table-bordered table-striped mb-none">
						                                        <thead>
						                                            <tr>
						                                            	<th>Fees Structure</th>
																		<th>Fees Amount</th>
																		<th>Status</th>
						                                            </tr>
						                                        </thead>
						                                        <tbody>
						                                        
						                                        	<c:forEach var="fees" items="${feesCategoryList}" varStatus="i">
						                                        		
																				<tr>
																					<td>
																						${fees.feesCategoryCode}
																					</td>
																					<td>${fees.fees}</td>
																					<td></td>
																				</tr>
																				
																	</c:forEach>
						                                        
						                                        </tbody>
						                                    </table>
						                                </div>
				                            </section>
							</div>
                                             
                                        </div>
                                       
                                      
                                       
                                       
									</div>
								</section>
                           </form:form>
						</div>	
                        
					</div>	


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script src="/icam/assets/javascripts/tables/accessTypeContactMapping.editable.js"></script>
<script src="/icam/assets/javascripts/tables/examples.datatables.editable.js"></script> -->
</body>
</html>