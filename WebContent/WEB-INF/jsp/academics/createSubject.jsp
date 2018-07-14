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
       }.mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript">
function makeEditable(rowId){
	rowId=rowId.replace("edit","");
	document.getElementById("subjectName"+rowId).removeAttribute("readonly");
	document.getElementById("subjectGroup"+rowId).removeAttribute("readonly");
	
	
};
function saveData(rowId){
	rowId=rowId.replace("save","");
	var status = editSubject(rowId);
	if(status == true){
		document.getElementById("saveId").value=rowId;
		document.editSubject.submit();
	}
	if(status == false){
		//document.getElementById("subjectName"+rowId).setAttribute("readonly","readonly");
		//document.getElementById("subjectGroup"+rowId).setAttribute("readonly","readonly");
	}
};
</script>
</head>
<body>
			<c:if test="${insertUpdateStatus ne null}">
				<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
					<span id="infomsg1">${insertUpdateStatus}</span>	
				</div>
			</c:if>
			<c:choose>
				<c:when test="${subjectGroupList eq null || empty subjectGroupList}">
					<div class="btnsarea01" >
						<div class="errorbox" id="errorbox" style="visibility: visible;">
							<c:if test="${subjectGroupList eq null || empty subjectGroupList}">
								<span id="errormsg">Subject Group Not Found</span>	
							</c:if>
						</div>
					</div>
				</c:when>
			<c:otherwise>

					<div class="row">
						<div class="col-md-4">
							<form action="addSubject.html" method="post">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Create Subject</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="col-sm-4 control-label">Subject Name: <span class="required" aria-required="true">*</span></label>
											<div class="col-sm-7">
												<input id="subjectName" name="subjectName" class="form-control" type="text" required>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label">Subject Group:<span class="required" aria-required="true">*</span> </label>
											<div class="col-sm-7">
												<select class="form-control" id="subjectGroup" name="subjectGroup" required>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="i">
														<option value="${subjectGroup.subjectGroupCode}">${subjectGroup.subjectGroupName}</option>
													</c:forEach>
                                                </select>
											</div>
										</div>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" class="btn btn-primary" onclick = "return addSubject()">Submit </button>
										<button type="reset" class="btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
						
						<c:choose>
							<c:when test="${subjectList eq null || empty subjectList}">
								<div class="btnsarea01" >
									<div class="errorbox" id="errorbox" style="visibility: visible;">
										<span id="errormsg">Subject Not Not Created Yet</span>	
									</div>
								</div>
							</c:when>
						<c:otherwise>
						
						<div class="col-md-8">						  
							<form name="editSubject" id="editSubject" action="editSubject.html" method="post">
								<input type="hidden" name="saveId" id="saveId">	
                            <section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
								</div>
						
								<h2 class="panel-title">Existing Subject</h2>
							</header>
							<div class="panel-body">
								<c:forEach var="subject" items="${subjectList}" varStatus="i">
									<input type="hidden" name="oldSubjectNames" value="${subject.subjectName}">
									</c:forEach>
								<table class="table table-bordered table-striped mb-none" id="datatable-editable">
									<thead>
										<tr>
											<th>Subject Name</th>
											<th>Subject Group</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="subject" items="${subjectList}" varStatus="i">
										<tr>
                                            
											<td>
												<input type="hidden" name="subjectId${i.index}" value="${subject.subjectId}">
												<input id="subjectName${i.index}" name="subjectName${i.index}" readonly class="form-control" type="text" value="${subject.subjectName}">
											</td>
											<td>
                                                <select class="form-control" id="subjectGroup${i.index}" name="subjectGroup${i.index}" readonly>
                                                    <option value="">Select...</option>
                                                    <c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="j">
														<c:choose>						
															<c:when test="${subjectGroup.subjectGroupCode eq subject.subjectGroup}">						
																<option value="${subjectGroup.subjectGroupCode}" selected="selected">${subjectGroup.subjectGroupCode}</option>
															</c:when><c:otherwise>						
																<option value="${subjectGroup.subjectGroupCode}">${subjectGroup.subjectGroupCode}</option>
															</c:otherwise>
														</c:choose>
													</c:forEach>
                                                </select>
                                            </td>
                                            <td class="actions">
												<a href="#" class="hidden on-editing save-row" id="save${i.index}"><i class="fa fa-save"></i></a>
												<a href="#" class="hidden on-editing cancel-row"><i class="fa fa-times"></i></a>
												<a href="#" class="on-default edit-row" id="edit${i.index}"><i class="fa fa-pencil"></i></a>
											</td>
										</tr>
										</c:forEach>
										
									</tbody>
								</table>
							</div>
						</section>
						</form>
						</div>
					</c:otherwise>
					</c:choose>	
					</div>
				</c:otherwise>
				</c:choose>	
                    


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/hostel/createHostel.editable.js"></script>
<script src="/cedugenie/js/academics/createSubject.js"></script>
</body>
</html>