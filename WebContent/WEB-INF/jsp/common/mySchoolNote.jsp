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
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
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
	<h2>School Notes</h2>
</header>
<div class="content-padding">
		<div class="row">				
			<%-- <c:if test="${ticket != null}">		 --%>	
				<c:if test="${status == 'success'}">
					<div class="alert alert-success">
						<strong>${msg}</strong>	
					</div>					
				</c:if>
				<c:if test="${status == 'fail'}">
					<div class="alert alert-danger">
						<strong>${msg}</strong>	
					</div>					
				</c:if>
			<%-- </c:if> --%>
		
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form modelAttribute="FORM" method="POST" id="mySchoolNote" name="mySchoolNote" action="mySchoolNote.html">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">School Notes</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										 <div class="row">
	                                        <div class="col-md-6">
												 <div class="form-group">
		                                            <label class="control-label">Notes :<span class="required" aria-required="true">*</span></label>
		                                            <input type="text" class="form-control"  id="note" name="note" placeholder=""  required>
		                                        </div>
		                                     </div>
		                                     
                                        </div>
                                        <div class="row">
                                        	 <div class="col-md-10">
		                                        <div class="form-group">
		                                            <label class="control-label">Description:<span class="required" aria-required="true">*</span></label>
		                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control"  id = "description"  name = "description"  required></textarea>
		                                        </div>
		                                      </div>
                                        </div>
                                        <div class="row">
                                        	 <div class="col-md-6">
		                                        <div class="form-group">
		                                            <label class="control-label">Recipients:<span class="required" aria-required="true">*</span></label>
		                                         	 <select class="form-control" name="recipients" id="recipients" required>
                                                        <option value="">Select...</option>
														<option value="standard">Standard</option>
														<option value="roll">Roll</option>
														<option value="all">All</option>
                                                    </select>
		                                         </div>
		                                      </div>
		                                     
                                        </div>
                                        <br>
                                        <div id="standardDiv" style="display:none">
                                         	<div class="col-md-6" >
                                         		<table class="table table-bordered table-striped mb-none" >
                                         			<thead>
                                         				<tr>
	                                         				<th>Standard</th>
	                                         				<th>Section</th>
                                         				</tr>
                                         			</thead>
                                         			<tbody id="standardtableBody">
                                         			</tbody>
                                         		</table>
		                                    </div>
                                      	</div>
                                      	<div id="rollnumberDiv" style="display:none">
                                      		<div class="col-md-6">
		                                      	<div class="form-group" >
													<label class="col-md-6">RollNumber: <span class="required" aria-required="true">*</span> </label>
													<div class="col-sm-7">
														<input id="rollNumber" name="rollNumber" class="form-control" type="text" placeholder="Comma separated values" pattern = "{1,}" title = "Batch is a Comma Separrated Field" >
													</div>
												</div>
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary"  type="submit">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>	
                        
					</div>	
					<div class="row">
						<div class="col-md-8 col-md-offset-2">						  
							<form>
	                        	<section class="panel">
		                        	<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
										</div>
								
										<h2 class="panel-title">School Notes List</h2>
									</header>
							
								<div class="panel-body">
									<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
										<thead>
											<tr>
	                                            <th>Note</th>
												<th>Description</th>
												<th>Sender</th>
												<th>Date</th>
	   											<th>Recipients</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="schoolNote" items="${schoolNoteList}" varStatus="i">
												<tr>											
													<td>
														${schoolNote.note}
													</td>
													<td>
														${schoolNote.description}
													</td>
													<td>
														${schoolNote.sender}
													</td>
													<td>
														${schoolNote.dateString}
													</td>
													<td>
														 <c:if test="${schoolNote.standardList != null}">
														 	<c:forEach var="standard" items="${schoolNote.standardList}" varStatus="i">
														 		<div>
														 			${standard.standardName} -
														 			<c:forEach var="section" items="${standard.sectionList}" varStatus="i">
														 				${section.sectionName},
														 			</c:forEach>
														 		</div>
														 	</c:forEach>
														 </c:if>
														  <c:if test="${schoolNote.rollList != null}">
														 	<c:forEach var="roll" items="${schoolNote.rollList}" varStatus="i">
														 		${roll},
														 	</c:forEach>
														 </c:if>
														 <c:if test="${schoolNote.general != null}">
														 	${schoolNote.general}
														 </c:if>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								
	                        </section>
	                       </form>
                    	</div>
                    </div>
				</div>
			</div>


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/mySchoolNote.js"></script>
</body>
</html>