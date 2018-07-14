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
<title>List Configure Working Days</title>

<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class="page-header">
		<h2>List Configure Working Days</h2>
	</header>
	<div class="content-padding">
	<form name="editholiday">
		<c:choose>
			<c:when test="${listTermHolidays eq null}">
				<div class="errorbox" id="errorbox" style="visibility: visible;">
					<span id="errormsg">No Holidays Found</span>	
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
									<th>Year</th>
									<th>Month And Year</th>
									<th>Public Holidays</th>
									<th>Working Days</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="termholiday" items="${listTermHolidays}" varStatus="i">
									<tr class="gradeC">
										<td>
											<input type="hidden" id="acaYear${i.index}" name="acaYear" value="${termholiday.academicYear}">
											<c:out value="${termholiday.academicYear}"/>
										</td>
										<td>
											<input type="hidden" id="month${i.index}" name="month" value="${termholiday.termStartDate}">
											<input type="hidden" id="year${i.index}" name="year" value="${termholiday.termEndDate}">
											<c:if test="${termholiday.termStartDate eq '01'}">
												<c:out value="January"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '02'}">
												<c:out value="February"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '03'}">
												<c:out value="March"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '04'}">
												<c:out value="April"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '05'}">
												<c:out value="May"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '06'}">
												<c:out value="June"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '07'}">
												<c:out value="July"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '08'}">
												<c:out value="August"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '09'}">
												<c:out value="September"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '10'}">
												<c:out value="October"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '11'}">
												<c:out value="November"/>
											</c:if>
											<c:if test="${termholiday.termStartDate eq '12'}">
												<c:out value="December"/>
											</c:if>
											 - <c:out value="${termholiday.termEndDate}"/>
										</td>
										<td>
										<c:forEach var="holiday" items="${termholiday.holiday}">
											<input type="radio"  id="holidaycode" name="holidaycode" onclick="setIndex('${i.index}');" value="<c:out value="${holiday.holidayCode}"/>" readonly="readonly"/>
											<c:out value="${holiday.specialHoliday}"/>
											<br>
										</c:forEach>
										</td>
										<td>
											<c:out value="${termholiday.noOfWorkingDays}"/>
											<input type="hidden"  id="working${i.index}" size="5"  class="textfield1" value="<c:out value="${termholiday.noOfWorkingDays}"/>" readonly="readonly"/>
											<input type="hidden" id="radio2" size="1" name="yesno" value="<c:out value="${termholiday.termCode}"/>">
										</td>		
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<footer style="display: block;" class="panel-footer">
							<button class="btn btn-primary" type="submit"  id="edit" value="Edit">Edit</button>
							<button class="btn btn-danger" type="button" value="Back" onclick="showone();">Back</button>
						</footer>
					</div>
				</section>			
			</section>
		</c:otherwise>
		</c:choose>
		<br>
		<br>
		<div class="warningbox" id="warningbox" >
			<span id="warningmsg"></span>	
		</div>
		<input type="hidden" name="hiddenAcaYear" id ="hiddenAcaYear"/>
		<input type="hidden" name="hiddenYear" id ="hiddenYear"/>
		<input type="hidden" name="hiddenMonth" id ="hiddenMonth"/>
		<input type="hidden" name="hiddenDays" id="hiddenDays"/>
		<input type="hidden" name="holidayCode" id="holidayCode"/>
		<input type="hidden" name="saveID" id="saveID"/>
	</form>
	</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
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
		$('input:radio').each(function(){	
			if ($(this).is(':checked')){
				document.getElementById("warningbox").style.visibility='collapse';
				document.getElementById("warningmsg").innerHTML="";
    			var code = $(this).val();
    			
    			var rowid = document.getElementById("saveID").value;
    			var academicYear = document.getElementById("acaYear"+rowid).value;
    			var year = document.getElementById("year"+rowid).value;
    			var month = document.getElementById("month"+rowid).value;
    			var workingdays = document.getElementById("working"+rowid).value;
				
    			$('#holidayCode').val(code);
				$('#hiddenAcaYear').val(academicYear);
    			$('#hiddenYear').val(year);
    			$('#hiddenMonth').val(month);
    			$('#hiddenDays').val(workingdays);
    			showtwo();
			}
		});
		
		$('input:radio').each(function(){	
			if ($(this).not(':checked')){
				document.getElementById("warningbox").style.visibility='visible';
				document.getElementById("warningmsg").innerHTML="Must Check Any Option.";
			 	valid=false;
				return false;
			}
		});
		return valid;
});
});
</script>
<script>
function showone(){
	 document.editholiday.method="get";
	 document.editholiday.action="configureWorkingDays.html";
	 document.editholiday.submit();             // back from the page
	 return true;
}		

function showtwo(){
	 document.editholiday.method="get";
	 document.editholiday.action="editConfigureWorkingDays.html";
	 document.editholiday.submit();             // back from the page
	 return true;
}

/* added by sourav.bhadra on 21-07-2017 */
function setIndex(rowID){
	$('#saveID').val(rowID);
}
</script>
</body>
</html>