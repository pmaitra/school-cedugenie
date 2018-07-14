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
       }.mb-md{
       	   display: none;
       }
      .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       }
</style>
<link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet"> 

</head>
<body>
<header class="page-header">
	<h2>Create Event</h2>
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
						  <form:form modelAttribute="FORM" method="POST" id="submitEvent" name="submitEvent" action="submitEvent.html" enctype="multipart/form-data">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Event</h2>										
									</header>
									<div style="display: block;" class="panel-body">                                       
										<div class="row">
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Event Name <span aria-required="true" class="required">*</span></label>
                                                    <input type="text" class="form-control" name="eventName" id="eventName" value=""   placeholder="" required>                                                    
                                               		
                                                </div>
                                            </div>
                                         </div>
                                        <div class="row">
                                           <div class="col-md-12">
                                           <div class="form-group">
                                                <label class="control-label">Event Description <span aria-required="true" class="required">*</span></label>
                                                <textarea class="form-control" rows="3" data-plugin-maxlength="" maxlength="500" name="eventDescription" id="eventDescription" required></textarea>                                                    
                                            </div>
                                           </div>
                                       </div>
                                       
                                        
                                        <div class="row">
                                        	 
                                            <div class="col-md-4" >
	                                            <div class="form-group">
				                                        <label class="control-label">Start Date<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
				                                            </span>
				                                            <input type = "text" class="form-control" name="eventStartDate" id="eventStartDate" data-plugin-datepicker="" data-date-end-date="0d" required>
				                                        </div>
				                                    </div>
                                            </div>
                                            
                                           <div class="col-md-4" >
	                                            <div class="form-group">
				                                        <label class="control-label">End Date<span class="required" aria-required="true">*</span></label>
				                                        <div class="input-group">
				                                            <span class="input-group-addon">
				                                                <i class="fa fa-calendar"></i>
				                                            </span>
				                                            <input type = "text" class="form-control" name="eventEndDate" id="eventEndDate" data-plugin-datepicker="" data-date-end-date="0d"  disabled required>
				                                        </div>
				                                 </div>
                                            </div>
                                            
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label class="control-label">Event Incharge <span aria-required="true" class="required">*</span></label>                                                
                                                   <input type="text" class="form-control" name="eventIncharge" id="eventIncharge">
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
						
                        <section class="panel">
                        	
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Event List</h2>
							</header>
							
							<div class="panel-body">
								
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Event Name</th>
   											<th>Description</th>
   											<th>Start Date</th>                                         
											<th>End Date</th>
											<th>In Charge</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="schoolEvent"  items="${schoolEventList}"> 
                                        	<tr>
                                            	<td>${schoolEvent.eventName }</td>
												<td>${schoolEvent.eventDescription}</td>
												<td>${schoolEvent.eventStartDate}</td>
												<td>${schoolEvent.eventEndDate}</td>
												<td>${schoolEvent.eventIncharge}</td>
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
<script type="text/javascript" src="/cedugenie/js/ticketing/generateTicket.js"></script>
<script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="/cedugenie/js/academics/createStudentAchievement.js"></script>
<script type="text/javascript">

/* $(function(){
	   var formIssuanceDate = document.getElementById("formIssuanceDate").value;
	   alert("formIssuanceDate===="+formIssuanceDate);
	     */
	    $("#eventStartDate").datepicker({
	        minDate: 0,
	        maxDate: '+1Y+6M',
			 dateFormat: 'dd/mm/yy',
	        onSelect: function (dateStr) {
	            var min = $(this).datepicker('getDate'); // Get selected date
	            $("#eventEndDate").datepicker('option', 'minDate', min || '0'); // Set other min, default to today
	            $("#eventEndDate").removeAttr('disabled','disabled');
	            //$("#eventEndDate").datepicker.dateFormat( 'dd/mm/yy');
	        }
	    });
	     
	     $("#eventEndDate").datepicker({
		         dateFormat: 'dd/mm/yy',
		        
		 }); 
</script>	    

</body>
</html>