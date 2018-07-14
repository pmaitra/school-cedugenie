<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
<title>List Special Days</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>

	<form name="special">
		<c:choose>
			<c:when test="${listTermHolidays eq null}">		
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Special Holidays  Found</span>	
				</div>		
			</c:when>
		<c:otherwise>
			<section role="main" class="content-body">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
						</div>
						<h2 class="panel-title">List Working Days</h2>
					</header>
					<div class="panel-body">
						<table class="table table-bordered table-striped mb-none" id="spclDys">
							<thead>
								<tr>
									<th>Academic Year</th>
									<th>Course Name</th>
									<th>Term Name</th>
									<th>Holiday</th>
									<th>Compensatory Days</th>
									<th>Reason</th>
									<th>Working Days</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="termholiday" items="${listTermHolidays}">
									<tr class="gradeC">
										<td>
											<c:out value="${termholiday.academicYear}"/>
										</td>
										<td>
											<c:out value="${termholiday.course.courseName}"/>
										</td>
										<td>
											<c:out value="${termholiday.termName}"/>
										</td>
										<td>
											<c:forEach var="holiday" items="${termholiday.holiday}">
												<input type="radio"  id="holidaycode" name="holidaycode" value="<c:out value="${holiday.holidayDetailsId}"/>" readonly="readonly"/>
												<c:out value="${holiday.specialHoliday}"/>
												<input type="hidden" class="textfield1" id="holiday" name="holidayText" value="<c:out value="${holiday.specialHoliday}"/>" readonly="readonly"/><br>
											</c:forEach>
										</td>
										<td>
											<c:forEach var="holiday" items="${termholiday.holiday}">
												<c:out value="${holiday.compensatory}"/><br>
											</c:forEach>
										</td>
										<td>
											<c:forEach var="holiday" items="${termholiday.holiday}">
												<c:out value="${holiday.holidayDesc}"/><br>
											</c:forEach>
										</td>
										<td>
											<c:out value="${termholiday.noOfWorkingDays}"/>
											<input type="hidden" class="textfield1" id="working" value="<c:out value="${termholiday.noOfWorkingDays}"/>" readonly="readonly"/>
											<input type="hidden" id="radio2" name="yesno" value="<c:out value="${termholiday.termCode}"/>">
										</td>		
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" id="edit" name="edit" value="Edit">Edit</button>
							<button class="btn btn-danger" type="button" value="Back" onclick="showone();">Back</button>
						</footer>
					</div>
				</section>			
			</section>
		</c:otherwise>
		</c:choose>
		<input type="hidden" name="hiddencode" id ="hiddencode" />
		<input type="hidden" name="hiddenstart" id ="hiddenstart" />
		<!-- <input type="hidden" name="hiddenend" id ="hiddenend" /> -->
		<input type="hidden" name="hiddenterm" id ="hiddenterm" />
		<input type="hidden" name="hiddendays" id="hiddendays"/>
		<!-- <input type="hidden" name="hiddencompen" id="hiddencompen"/> -->
		<br>
		<br>
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
	</form>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript">
	window.onload=function(){
		var checkbox=getElementsByClassName("listShowHide");
		for(var i=0;i<checkbox.length;i++){
			ShowHideField(checkbox[i].value, 'spclDys', checkbox[i]);
		}
	};
	
	function ShowAll(cb){
		if(cb.checked){
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=true;
				ShowHideField(checkbox[i].value, 'spclDys', checkbox[i]);
			}
		}
		else{
			var checkbox=getElementsByClassName("listShowHide");
			for(var i=0;i<checkbox.length;i++){
				checkbox[i].checked=false;
				ShowHideField(checkbox[i].value, 'spclDys', checkbox[i]);
			}
		}
		
	}
	
	
$(document).ready(function(){
	var valid= null;
	$('#edit').click(function(){
		$('input:radio').each(function()
				{	
					
					if ($(this).is(':checked'))
					{					
		    			var code = $(this).val();
		    			var startdate = $(this).next().val();
		    			var workingdays = $(this).parent().next().next().next().find('input:hidden').val();
						var term = $(this).parent().next().next().next().find('input:hidden').next().val();
						$('#hiddencode').val(code);
						$('#hiddenstart').val(startdate);
		    			$('#hiddenterm').val(term);
		    			$('#hiddendays').val(workingdays);
		    			showtwo();
		    			
					}
				});
		$('input:radio').each(function()
				{	
					if ($(this).not(':checked')){
						document.getElementById("warningbox").style.visibility='visible';
						document.getElementById("warningmsg").innerHTML="Must check some option!";
					 	valid=false;
						return false;
					}
					else{
						document.getElementById("warningbox").style.visibility='collapse';
						document.getElementById("warningmsg").innerHTML="";
					}
				});
		return valid;
});
});
</script>
<script>
function showone(){
	 document.special.method="get";
	 document.special.action="configureSpecialDays.html";
	 document.special.submit();             // back from the page
	 return true;
}		

function showtwo(){
	 document.special.method="get";
	 document.special.action="editConfigureSpecialDays.html";
	 document.special.submit();             // back from the page
	 return true;
}

</script>
</body>
</html>