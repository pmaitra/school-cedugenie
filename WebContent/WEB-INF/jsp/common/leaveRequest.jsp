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
<title>Employee Leave Information</title>
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
	<div class="row">						
		<div class="col-md-6 col-md-offset-3">	
			<c:choose>
				<c:when test="${staffLeaveDetails==null  || staffLeaveDetails.size()== 0}">
					<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
						<span id="infomsg">No Leave Type Present</span>
					</div>					
				</c:when>
			<c:otherwise>
	            <form>
	                <section class="panel">
	                    <header class="panel-heading">
	                        <div class="panel-actions">
	                            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                        </div>
	
	                        <h2 class="panel-title">My Leave Details</h2>
	                    </header>
	                    <div class="panel-body">
	                        <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
	                            <thead>
	                                <tr>
	                                    <th>Leave Type</th>                                                   
	                                    <th>Alloted leave</th>
	                                    <th>Remaining Leave</th>                                                    
	                                    <th>Lapsed Leave</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <c:if test= "${staffLeaveDetails ne null && staffLeaveDetails.size() ne 0}">
									<c:forEach var="leave" items="${staffLeaveDetails}">
									<tr>
									<td>${leave.leaveType}</td>															
									<td>${leave.totalAvailLeave}</td>									
									<td>${leave.usedLeave}</td>	
									<td>${leave.remainingLeave}</td>		
									</tr>						
									</c:forEach>
									</c:if>	                               
	                            </tbody>
	                        </table>
	                    </div> 
	                </section>
	            </form>
             </c:otherwise>
         </c:choose>
	</div>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>