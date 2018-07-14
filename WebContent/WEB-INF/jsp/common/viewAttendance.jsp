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
<script type="text/javascript" src="/cedugenie/js/ticketing/editTicket.js"></script>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<script>
function showdaydiff() {
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	 if(document.getElementById("startDate").value==""){
			alert("Please Provide Start Date");
			return false;		
		}
		
		if(document.getElementById("endDate").value==""){
			alert("Please Provide End Date");
			return false;		
		}
		
		/* if ((new Date(startDate).getTime()) > (new Date(endDate).getTime())) {
		    alert("Start Date can not be greater than End date");
		    return false;
		}  */
		if (startDate > endDate){
		    alert("Start Date can not be greater than End date");
		    return false;
		} 
		return true;
}
</script>

</head>
<body>
 <div class="row">
						<div class="col-md-5">
						 <form:form action="viewAttendanceDetails.html" method="POST" class = "">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Year</h2>										
									</header>
									<div style="display: block;" class="panel-body">    
                                        
										<div class="form-group">
                                            <label class="col-md-5 control-label">Start Date :<span class="required" aria-required="true">*</span></label>
                                            <div class="col-md-7">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" class="form-control" data-plugin-datepicker="" name="startDate" id="startDate">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-5 control-label">End Date :<span class="required" aria-required="true">*</span></label>
                                            <div class="col-md-7">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" class="form-control" data-plugin-datepicker="" name="endDate" id="endDate">
                                                </div>
                                            </div>
                                        </div>
                                            
									</div>
									<footer style="display: block;" class="panel-footer">
										<button class="btn btn-primary" id="submit" name="submit" onclick = "return showdaydiff()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                           </form:form>
						</div>
						<div class="col-md-7">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
                                    </div>

                                    <h2 class="panel-title">View Attendance Details</h2>										
                                </header>
                                <div style="display: block;" class="panel-body">    

                                    <div class="form-group">
                                        <label class="col-md-6 control-label">Name</label>
                                        <label class="col-md-6 control-label"><c:out value="${attendance.resourceId}"/></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6 control-label">Start Date</label>
                                        <label class="col-md-6 control-label"><c:out value="${attendance.startDate}"/></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6 control-label">End Date</label>
                                        <label class="col-md-6 control-label"><c:out value="${attendance.endDate}"/></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6 control-label">Total Working Number of Days</label>
                                        <label class="col-md-6 control-label"><c:out value="${attendance.totalDays}"/></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6 control-label">Total Days Present</label>
                                        <label class="col-md-6 control-label"><c:out value="${attendance.presentDays}"/></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-6 control-label">Total Days Absent</label>
                                        <label class="col-md-6 control-label"><c:out value="${attendance.totalDays-attendance.presentDays}"/></label>
                                    </div>

                                </div>
                            </section>
                        </div>
					</div>


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script src="/cedugenie/assets/javascripts/tables/accessTypeContactMapping.editable.js"></script>
<script src="/cedugenie/assets/javascripts/tables/examples.datatables.editable.js"></script> -->
</body>
</html>