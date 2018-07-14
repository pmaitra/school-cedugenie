<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tg"%> 
<%@ include file="/file/sessionDataForChildPages.txt"%>
<c:url value="/listBookPagination.html" var="pagedLink">
	 <c:param name="p" value="~"/>
</c:url>
<!DOCTYPE html>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<title>Closed ticket list</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<c:if test="${successMessage ne null}">
			<div class="alert alert-success" id="infomsgbox1" >
				<strong>${successMessage}</strong>	
			</div>
		</c:if>
		<div class="col-md-5">
				 <form action="applyForLeave.html" method="post">
						   <input type="hidden" class="form-control" name="title" id="title"  value = "Leave" placeholder=""  >
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Apply For Leave</h2>										
									</header>
									<div style="display: block;" class="panel-body">    
                                        
                                        <div class="form-group">
                                            <label class="col-md-5 control-label">Leave Type :<span class="required" aria-required="true">*</span></label>
                                            <div class="col-md-7">
                                                <div class="input-group">
                                                    <select class="form-control" name="leaveType" id="leaveType" required>
		                                                <option value="">Select...</option>
		                                                <c:forEach var="leave" items="${leaveDetailsList}">
															<option value="${leave.leaveType}">${leave.leaveType}</option>
														</c:forEach>
		                                            </select>
                                                </div>
                                            </div>
                                        </div>
										<div class="form-group">
                                            <label class="col-md-5 control-label">Start Date :<span class="required" aria-required="true">*</span></label>
                                            <div class="col-md-7">
                                                <div class="input-group">
                                                    <span class="input-group-addon">
                                                        <i class="fa fa-calendar"></i>
                                                    </span>
                                                    <input type="text" class="form-control" id = "startDate" name = "startDate" data-plugin-datepicker="" required>
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
                                                    <input type="text" id = "endDate" name = "endDate"  class="form-control" data-plugin-datepicker="" required>
                                                </div>
                                            </div>
                                        </div>
                                         <div class="form-group">
                                            <label class="control-label">Group Name <span class="required" aria-required="true">*</span></label>
                                            <input type="text" class="form-control" name="desc" id="desc"  value = "${approverGroupName}" placeholder=""  readonly>
                                        </div> 
                                          <div class="form-group">
                                            <label class="control-label">Description<span class="required" aria-required="true">*</span></label>
                                            <textarea maxlength="140" data-plugin-maxlength="" rows="3" class="form-control" name="remarks" id="remarks"></textarea>
                                        </div>   
									</div>
									<footer style="display: block;" class="panel-footer">
										<button  type = "submit" class="btn btn-primary">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>

  <script type="text/javascript">
  
  
  function validate(){
	  
	  if(document.getElementById("leaveType").value=="" ||document.getElementById("leaveType").value=='null'){
		  alert("Please Select Leave Type");
		/* document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Select Leave Type"; */
			return false;		
		}	
		
		if(document.getElementById("startDate").value==""||document.getElementById("startDate").value=='null'){
		/* 	document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Provide Start Date"; */
			alert("Please Provide Start Date");
			return false;		
		}
		
		if(document.getElementById("endDate").value==""||document.getElementById("endDate").value=='null'){
			/* document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Provide End Date"; */
			alert("Please Provide End Date");
			return false;		
		}
		if(document.getElementById("remarks").value==""||document.getElementById("remarks").value=='null'){
			/* document.getElementById("warningbox").style.visibility='visible';
			document.getElementById("warningbox").innerHTML="Please Provide End Date"; */
			alert("Please Provide Description");
			return false;		
		}
		return true;
  }
  </script>
</body>
</html>