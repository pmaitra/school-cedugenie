<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<!DOCTYPE html>
<html lang="de">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<meta name="description" content="Page To Add Social Category" />
<meta name="keywords" content="Page To Add Social Category" />
<meta name="revisit-after" content="7 days" />
<meta name="robots" content="index,follow" />
<title>Create Subject</title>

<link rel="stylesheet" href="/icam/css/academics/createSubject.css" />
<link href="/icam/css/common/messagebox.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/button.css" rel="stylesheet" type="text/css" />
<link href="/icam/css/common/bodyAndHeader.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/icam/css/common/jquery-ui.css" />
<link href="/icam/fonts/font-awesome.css" rel="stylesheet" />

<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/academics/createSubject.js"></script> 
<script type="text/javascript" src="/icam/js/common/iframeHeight.js"></script>
</head>

<body>
<div class="ttlarea">	
	<h1>
		<img src="/icam/images/titleicon01.png" alt="" />&nbsp;&nbsp;Create Subject
	</h1>
</div>
	<div class="btnsarea01">
		<c:if test="${insertUpdateStatus ne null}">
			<div class="infomsgbox" id="infomsgbox1" style="visibility: visible;" >
				<span id="infomsg1">${insertUpdateStatus}</span>	
			</div>
		</c:if>
	</div>
	
	<form name="subjectForm" id="subjectForm" >
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
				<table id="subjectDetailTable" class="midsec" class="midsec" cellspacing="0" cellpadding="0">
					<tr>
						<th>
							Subject Name ::
						</th>
						<td>
							<input type="text" id="subjectName" name="subjectName" class="textfield1"  />
						</td>
					</tr>
					<tr>
						<th>
							Subject Group ::
						</th>
						<td>
							<select id="subjectGroup" name="subjectGroup" size="1" class="defaultselect">
								<c:forEach var="subjectGroup" items="${subjectGroupList}" varStatus="i">
									<option value="${subjectGroup.subjectGroupCode}">${subjectGroup.subjectGroupName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				<div class="warningbox" id="warningbox" >
					<span id="warningmsg"></span>	
				</div>
				<div class="btnsarea01">
					<input type="button" onclick="return addSubject();" value="Submit" class="submitbtn"/>
				</div>			
			</c:otherwise>	
		</c:choose>
			
		<br><br>	
				
		<c:choose>
			<c:when test="${subjectList eq null || empty subjectList}">
				<div class="btnsarea01" >
					<div class="errorbox" id="errorbox" style="visibility: visible;">
						<span id="errormsg">Subject Not Not Created Yet</span>	
					</div>
				</div>
			</c:when>
			<c:otherwise>			
				<table id="previousSubjectDetail" class="midsec1" cellspacing="0" cellpadding="0">
					<thead>
					<tr>
						<th colspan="3">
							:: Existing Subjects ::
						</th>
					</tr>
					<tr>
						<th></th>
						<th>Subject Name</th>
						<th>Subject Group</th>
					</tr>
					<c:forEach var="subject" items="${subjectList}" varStatus="i">
						<tr>
							<td>
								<input type="hidden" name="oldSubjectNames" value="${subject.subjectCode}">
								<input type="checkbox" id="code${i.index}" name="subjectCodes" value="${subject.subjectCode}"/>
							</td>
							<td>
								<input type="text" id="name${i.index}" name="${subject.subjectCode}Name" class="textfield1" value="<c:out value="${subject.subjectName}"/>" />
							</td>
							<td>
								<select id="subjectGroup${i.index}" name="${subject.subjectCode}SubjectGroup" size="1" class="defaultselect">
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
						</tr>
					</c:forEach>
					</thead>		
				</table>				
				<div class="warningbox" id="warningbox1" >
					<span id="warningmsg1"></span>
				</div>
				<div class="btnsarea01">
					<input type="button" onclick="return editSubject();" value="Update" class="submitbtn"/>
				</div>
			</c:otherwise>
		</c:choose>
	</form>
	<div class="warningbox" id="warningbox" >
		<span id="warningmsg"></span>	
	</div>
</body>
</html>