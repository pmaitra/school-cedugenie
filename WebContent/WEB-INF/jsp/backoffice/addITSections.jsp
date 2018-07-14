<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Student Details Form</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("itSectionName"+rowId).removeAttribute("readonly");
	document.getElementById("itSectionDesc"+rowId).removeAttribute("readonly");
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	document.getElementById("saveId").value=rowId;
	var status = editValidate(rowId);
	if(status == true){
		document.editAndUpdateITSection.submit();
	}
	
};
</script>
</head>
<body>

<div class="row">
		<div class="col-md-5">
		  	<form:form method="POST" action="createITSections.html">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
	
						<h2 class="panel-title">Add I.T Sections</h2>										
					</header>
						<c:forEach var="ITSection" items="${itSectionList}" varStatus="indexVal" >
						<input type="hidden" class="form-control" id="itSectionNames" name="ItSectionNames" value="${ITSection.itSectionName}"  />
						</c:forEach>
					<div style="display: block;" class="panel-body">
	                                   
                        <div class="form-group">
                            <label class="control-label">Add I.T Section</label>
                            <input class="form-control" name="itSectionName" id="itSectionName" pattern = "[a-zA-Z][a-zA-Z ]+[a-zA-Z]$" required/>
                        </div>
                        <div class="form-group">
                            <label class="control-label">Add I.T Section Description</label>
                            <textarea class="form-control" rows="4" data-plugin-maxlength="" maxlength="140" name="itSectionDesc" id="itSectionDesc" required></textarea>
                        </div> 
	                                       
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick = "return validateFrom()">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
	         </form:form>
		</div>
		
		<c:if test="${failuremsg ne null}">
			<div class="alert alert-danger">
				<strong>${failuremsg}</strong>
			</div>					
		</c:if>		
		<c:if test="${successmsg ne null}">
			<div class="alert alert-success">
				<strong>${successmsg}</strong>
			</div>					
		</c:if>	
		
		<c:choose>
			<c:when test="${itSectionList eq null || fn:length(itSectionList) lt 1 }">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No I.T Sections Added Yet.</span>	
			</div>
			</c:when>
		<c:otherwise>
			<form:form name="editAndUpdateITSection" id="editAndUpdateITSection" method="POST" action="updateITSection.html">
			<input type="hidden" name="saveId" id="saveId">	
			<div class="col-md-7">	
	                       <section class="panel">
	                           <header class="panel-heading">
	                               <div class="panel-actions">
	                                   <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                               </div>
	
	                               <h2 class="panel-title">View / Edit I.T Sections</h2>
	                           </header>
	                           <div class="panel-body">
	
	                               <table class="table table-bordered table-striped mb-none" id="datatable-editable">
	                                   <thead>
	                                       <tr>
	                                       <th>I.T Section Name</th>
										   <th>I.T Section Description</th>	
	                                       <th>Actionss</th>
	                                       </tr>
	                                   </thead>
	                                   <tbody>
	                                   	<c:forEach var="ITSection" items="${itSectionList}" varStatus="indexVal" >
	                                       <tr class="gradeX">
												<td>
													<input type="hidden" name="oldItSectionCode${indexVal.index}" value="${ITSection.itSectionCode}">
													<input type="text" class="form-control" id="itSectionName${indexVal.index}" name="ItSectionName${indexVal.index}" value="${ITSection.itSectionName}" readonly />
												</td>
												<td>
													<textarea wrap="hard" class="form-control" name="ItSectionDesc${indexVal.index}" id="itSectionDesc${indexVal.index}"  readonly rows="4" data-plugin-maxlength="" maxlength="140">
														<c:out value="${ITSection.itSectionDesc}" />
													</textarea>
												</td>
												<td class="actions">
			                                         <a class="on-default edit-row" href="#" id="edit${indexVal.index}"><i class="fa fa-pencil"></i></a>
			                                         <a class="hidden on-editing save-row" href="#" id="save${indexVal.index}"><i class="fa fa-save"></i></a>
			                                     	 <a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
			                                     </td>
											</tr>
	                                    </c:forEach>
	                                   </tbody>
	                               </table>
	                           </div>
	                       </section>
						</div>
					</form:form>
		
					
						</c:otherwise>
						</c:choose>
					</div>	
					
					
					

<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
function validateFrom(){
	var itSectionName = document.getElementById("itSectionName").value;
	itSectionName = itSectionName.trim();
	itSectionName = itSectionName.toUpperCase();
	var ItSectionNames = document.getElementsByName("ItSectionNames");
	for(var i=0; i<ItSectionNames.length;i++){
		if(ItSectionNames[i].value == itSectionName){
			alert("IT SEction With This Name Already Exixts");
			return false;
		}
	}
	return true;
}

function editValidate(rowId){
	var ItSectionNames = document.getElementsByName("ItSectionNames");
	var itSectionName = document.getElementById("itSectionName"+rowId).value;
	for(var i=0; i<ItSectionNames.length;i++){
		if(ItSectionNames[i].value == itSectionName){
			alert("IT SEction With This Name Already Exixts");
			return false;
		}
	}
	return true;
}
</script>
<script src="/cedugenie/js/backoffice/addITSections.editable.js"></script>
</body>
</html>