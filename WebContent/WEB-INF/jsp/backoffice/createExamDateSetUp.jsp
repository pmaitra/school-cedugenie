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
						<div class="btnsarea01">
							<c:if test="${insertUpdateStatus ne null}">
								<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
									<span id="infomsg1">${insertUpdateStatus}</span>	
								</div>
							</c:if>
						</div> 			  
						<form name="examForm" id="examForm" method="POST" action="addExamDateSetUp.html" >
							<c:choose>
								<c:when test="${termList eq null || empty termList
								 				|| examList eq null || empty examList}">
									<div class="btnsarea01" >
										<div class="errorbox" id="errorbox" style="visibility: visible;">
											<c:if test="${termList eq null || empty termList}">
												<span id="errormsg">Term is not assigned</span>	
											</c:if>
											<c:if test="${examList eq null || empty examList}">
												<span id="errormsg">Exams not found</span>	
											</c:if>
										</div>
									</div>
								</c:when>
								<c:otherwise>			
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Exam Date Set Up</h2>
							</header>
							<div class="panel-body">
                                <div class="row">
                                <c:forEach var="termObj" items="${termList}" varStatus="j">
									<div class="col-md-6">
	                                    <div class="well well-sm info no-margin">
											<div>${termObj.term}</div>
										</div>
	                                    <table class="table table-bordered table-striped mb-none">
	                                        <thead>
	                                            <tr>
	                                                <th>Exam Name</th>
	                                                <th>Start Date</th>
	                                                <th>End Date</th>
	                                            </tr>
	                                        </thead>
	                                        <tbody>
	                                        <c:forEach var="examObj" items="${examList}" varStatus="k">
	                                        	<c:if test="${termObj.term eq examObj.term}">
			                                            <tr>
			                                                <td>${examObj.examName}
			                                             	   <input type="hidden" id="${examObj.examCode}" name="examCode" value="${examObj.examCode}" class="textfield1"  />
			                                                 </td>
			                                                <td>
			                                                    <div class="input-group">
			                                                        <span class="input-group-addon">
			                                                            <i class="fa fa-calendar"></i>
			                                                        </span>
			                                                        <input type="text" id="${examObj.examCode}startDate" name="examStartDate" value="${examObj.examStartDate}" readonly="readonly" data-plugin-datepicker="" class="form-control" style="width:120px;">
			                                                    </div>
			                                                </td>
			                                                <td>
			                                                    <div class="input-group">
			                                                        <span class="input-group-addon">
			                                                            <i class="fa fa-calendar"></i>
			                                                        </span>
			                                                        <input type="text" id="${examObj.examCode}endDate" name="examEndDate" value="${examObj.examEndDate}"  readonly="readonly"  data-plugin-datepicker="" class="form-control" style="width:120px;">
			                                                    </div>
			                                                </td>
			                                            </tr>
	                                            	</c:if>
	                                            </c:forEach>
	                                        </tbody>
	                                    </table>	
	                                    <hr>							    
	                                </div>
	                                
	                              </c:forEach>
	                              
<!--                                 <div class="col-md-6"> -->
<!--                                     <div class="well well-sm info no-margin"> -->
<!-- 										<div>Pre Board</div> -->
<!-- 									</div> -->
<!--                                     <table class="table table-bordered table-striped mb-none"> -->
<!--                                         <thead> -->
<!--                                             <tr> -->
<!--                                                 <th>Exam Name</th> -->
<!--                                                 <th>Start Date</th> -->
<!--                                                 <th>End Date</th> -->
<!--                                             </tr> -->
<!--                                         </thead> -->
<!--                                         <tbody> -->
<!--                                             <tr> -->
<!--                                                 <td>Pre-Board </td> -->
<!--                                                 <td> -->
<!--                                                     <div class="input-group"> -->
<!--                                                         <span class="input-group-addon"> -->
<!--                                                             <i class="fa fa-calendar"></i> -->
<!--                                                         </span> -->
<!--                                                         <input type="text" data-plugin-datepicker="" class="form-control" style="width:120px;"> -->
<!--                                                     </div> -->
<!--                                                 </td> -->
<!--                                                 <td> -->
<!--                                                     <div class="input-group"> -->
<!--                                                         <span class="input-group-addon"> -->
<!--                                                             <i class="fa fa-calendar"></i> -->
<!--                                                         </span> -->
<!--                                                         <input type="text" data-plugin-datepicker="" class="form-control" style="width:120px;"> -->
<!--                                                     </div> -->
<!--                                                 </td> -->
<!--                                             </tr> -->
<!--                                         </tbody> -->
<!--                                     </table>								     -->
<!--                                 </div> -->
                                </div>
                                
                              
                                

                                
							</div>
                            <footer style="display: block;" class="panel-footer">
                                <button class="btn btn-primary">Submit </button>
                                <button type="reset" class="btn btn-default">Reset</button>
                            </footer>    
						    </section>   
						   </c:otherwise>
						   </c:choose>
                            </form>    
						</div>
					</div>	


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>