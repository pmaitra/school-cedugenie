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
<title></title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
      /*  .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       } */
</style>
<!-- <link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet"> --> 
</head>
<body>
<header class="page-header">
	<h2>Student Achievements</h2>
</header>
<div class="content-padding">
		<div class="row">				
			<%-- <c:if test="${ticket != null}">		 --%>	
				<c:if test="${status == 'success'}">
					<div class="alert alert-success">
						<strong></strong>	
					</div>					
				</c:if>
				<c:if test="${status == 'fail'}">
					<div class="alert alert-danger">
						<strong></strong>	
					</div>					
				</c:if>
			<%-- </c:if> --%>
		
					 <div class="row">
						<div class="col-md-8 col-md-offset-2">
						  <form:form modelAttribute="FORM" method="POST" id="submitStudentAchievement" name="submitStudentAchievement" action="submitStudentAchievement.html" enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Student Achievements</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            
                                             <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Event <span aria-required="true" class="required">*</span></label>                                                
                                                    <select class="form-control" name="eventName" id="eventName" required>
                                                        <option value="">Select...</option>
															<c:forEach var="schoolEvent" items="${schoolEventList}" >
																<option value="${schoolEvent.serialId}">${schoolEvent.eventName}</option> 
															</c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                           
                                        </div>
                                        <br>
                                        <div class="row">
                                          
                                          <div class="col-md-8">
                                           <div class="form-group">
                                                <label class="control-label">Description</label>
                                                <textarea class="form-control" rows="3" data-plugin-maxlength="" maxlength="500" onfocus="clearContents(this);" name="eventDescription" id="eventDescription" readonly></textarea>                                                    
                                            </div>
                                           </div>

                                        </div>
                                        <br>
                                       
                                        <hr>
                                        <div class="col-md-16 alert alert-danger" id="msgDiv" style="display:none">
                                        	<span id ="msg"></span>
                                        </div>
                                        <div class="col-md-16">
                                       		<section class="panel">
												<header class="panel-heading">
													<div class="panel-actions">
														<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
													</div>
			
													<h2 class="panel-title">Student Achievements</h2>										
												</header>
		                                       <table class="table table-bordered table-striped mb-none" id="studentAchievements">
			                                        <thead>
			                                            <tr>
			                                                <th>Event Position</th>
			                                                <th>Student</th>
			                                                <th>Image</th>
			                                                <th>Add</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody id="studentAchievementsBody">
		                                            <tr>
		                                            	<td>
		                                            		<input type="text" class="form-control" id="eventPosition0" name="eventPosition" placeholder="" >
		                                                   
		                                            	</td>
		                                                <td><input type="text" class="form-control" id="student0" name="student" placeholder="" ></td>
		                                                <td><input type="file" class = "form-control" name="uploadFile.achievementRelatedFile" ></td>
		                                                <td><a class="mb-xs mt-xs mr-xs modal-basic btn btn-info" href="javascript:addrows()" id="addrow" >Add</a></td>
		                                             
		                                             </tr>
		                                            
		                                        	</tbody>
		                                       </table>
		                                    </section>
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
						
                        <section class="panel">
                        	
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Achievement List</h2>
							</header>
							
							<div class="panel-body">
								
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
                                            <th>Event Name</th>
                                            <th>Event Start Date</th>
                                            <th>Event End Date</th>
											<th>Event Position</th>
   											<th>Student Roll Number</th>
   											<!-- <th>Image</th> -->
										</tr>
									</thead>
									<tbody>
										<c:forEach var="schoolEvent" items="${eventWithAchievementList}">
										<tr>
											<td>
												${schoolEvent.eventName}
											</td>
											<td>
												 ${schoolEvent.eventStartDate} 
											</td>
											<td>
												 ${schoolEvent.eventEndDate} 
											</td>
											<td>
												${schoolEvent.eventAchievement.eventPosition}
											</td>
											<td>
												${schoolEvent.eventAchievement.rollNumber}
											</td>	
											<%-- <td>
												${schoolEvent.eventAchievement.imageFilePath}
											</td> --%>										
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
							
						</section>
					</div>
				</div>
				</div>
			</div>


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/academics/createStudentAchievement.js"></script>


</body>
</html>