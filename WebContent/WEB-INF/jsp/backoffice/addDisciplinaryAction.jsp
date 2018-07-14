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
<title>Add Disciplinary Action</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
</style>

</head>
<body>

		<header class="page-header">
			<h2>Disciplinary Action</h2>
		</header>
		<div class="content-padding">
			 <c:if test="${status eq 'success'}">
				<div class="alert alert-success">
					<strong>Successfully Inserted</strong>
				</div>
			</c:if> 
			
			 <c:if test="${status eq 'fail'}">
				<div class="alert alert-danger">
					<strong>Insert Fail</strong>
				</div>
			</c:if>
			<div id= "dangerDiv" name= "dangerDiv" class= "alert alert-danger"" style= "display: none">
			
			</div>
			<%-- <c:choose>
				<c:when test="${standardList eq null || empty standardList}">
					<div class="btnsarea01" >
						<div class="errorbox" id="errorbox" style="visibility: visible;">
							<c:if test="${standardList eq null || empty standardList}">
								<span id="errormsg">Standard Not Found</span>	
							</c:if>
						</div>
					</div>
				</c:when>
			<c:otherwise> --%>
			<div class="row">	
				<form action="insertDisciplinaryAction.html" method="post">
					<div class="col-md-8 col-md-offset-2">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
								</div>

								<h2 class="panel-title">Disciplinary Action</h2>										
							</header>
							
							<div style="display: block;" class="panel-body">
							<div class= "row">
								<div class= "col-md-4">
									<div class="form-group">
										<label class="control-label">Standard<span class="required" aria-required="true">*</span></label>
										<div class="">
											<select class="form-control" id="standard" name="standard" required>
                                                  <option value="">Select...</option>
                                                  <c:forEach var="standards" items="${standardList}" varStatus="i">
														<option value="${standards.standardCode}">${standards.standardName}</option>
													</c:forEach>
                                              </select>
										</div>
									</div>
								</div>
								<div class= "col-md-4">
									<div class="form-group">
									<label class="control-label">Section<span class="required" aria-required="true">*</span> </label>
									<div class="">
										<select class="form-control" id="section" name="section" required> 
												<option value="">Select...</option>
                                         </select>
									</div>
								</div>
								</div>
								<div class= "col-md-4">
									<div class="form-group">
									<label class="control-label">Roll Number<span class="required" aria-required="true">*</span> </label>
									<div class="">
										<select class="form-control" id="rollNumber" name="rollNumber" required> 
											<option value="">Select...</option>
                                        </select>
									</div>
								</div>
								</div>
							</div>
							<br>
							<div class= "row">
									<div class= "col-md-4">
										<div class="form-group">
											<label class="control-label">Disciplinary Code<span class="required" aria-required="true">*</span> </label>
											<div class="">
												<select class="form-control" id="disciplinaryCode" name="disciplinaryCode" required> 
													<option value="">Select...</option>
													<c:forEach var="disciplinaryCode" items="${disciplinaryActionList}">
														<option value="${ disciplinaryCode.disciplinaryCode}">${disciplinaryCode.disciplinaryCode}</option>
													</c:forEach>
		                                        </select>
											</div>
										</div>
									</div>
									<div class= "col-md-8">
										<div class="form-group">
											<label class="control-label">Description<span class="required" aria-required="true">*</span> </label>
											
												<textarea class= "form-control" id= "description" name= "description" required readonly></textarea>
											
										</div>
									</div>
							</div>
							<div class= "row">
								<div class= "col-md-10">
									<div class="form-group">
										<label class="control-label">Comment<span class="required" aria-required="true">*</span></label>
										<textarea class= "form-control" id= "complaint" name= "complaint" required></textarea>
									</div>
								</div>
							</div>
							</div>
						<footer style="display: block;" class="panel-footer">
								<button type="submit" class="btn btn-primary">Submit </button>
								<button type="reset" class="btn btn-default">Reset</button>
						</footer>
						</section>	
				</div>
           	</form>
		 </div> 
		 <%-- <c:choose>
			<c:when test="${studentDisciplinaryActionList.size() eq 0}">			
				<div class="errorbox" id="errorbox" style="visibility:visible;">
					<span id="errormsg">No Disciplinary Action Found</span>
				</div>	 	
			</c:when>
			<c:otherwise> --%>
                <div class="row">
                <div class="col-md-12">
					<form name = "disciplinaryAction" id = "disciplinaryAction">
						<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
							</div>
							<h2 class="panel-title">Disciplinary Action List</h2>										
						</header>
						<div style="display: block;" class="panel-body" id="previousCategoryDetail">   
								<div class="row">
                                   <div class="col-md-12">
                                      <table class="table table-bordered table-striped mb-none dataTable" id="datatable-tabletools">
                                          <thead>
												<tr>
													<th>Standard</th>
													<th>Section</th>
													<th>Roll Number</th>
													<th>Student Name</th>
													<th>Disciplinary Code</th>
													<th>Comment</th>
												</tr>
										  </thead>
                                          <tbody>
                                                <c:forEach var="disciplinary" items="${studentDisciplinaryActionList}" varStatus="status" >	
	                                                <tr>
	                                                   <td>
	                                                        <input type="text" class="form-control" name="standard" id="standard" value="${disciplinary.standard}" readonly="readonly" >
                                                      </td>
	                                                  <td>
	                                                        <input type="text" class="form-control" name="section" id="section" readonly="readonly" value="${disciplinary.section}">
                                                      </td>
	                                                  <td>
	                                                        <input type="text" class="form-control" name="rollNumber" id="rollNumber" value="${disciplinary.roll}" readonly="readonly" >
                                                      </td>
                                                      <td>
	                                                        <input type="text" class="form-control" name="studentName" id="studentName" value="${disciplinary.studentName}" readonly="readonly" >
                                                      </td>
                                                      <td>
	                                                        <input type="text" class="form-control" name="disciplinaryCode" id="disciplinaryCode" value="${disciplinary.disciplinaryCode}" readonly="readonly" >
                                                      </td>
	                                                  <td>
	                                                         <input type="text" class="form-control" name="comment" id="comment"  value="${disciplinary.house}" readonly="readonly">
                                                      </td>
	                                               </tr>
                                                 </c:forEach>
                                          </tbody>
                                      </table>
                                   </div>
                               </div>
							</div>
						</section>
                     </form>
					</div>
                 </div>
 				<%-- </c:otherwise>
 			</c:choose> --%>
	<%-- </c:otherwise>
	</c:choose>	 --%>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/cedugenie/js/hostel/createHostel.editable.js"></script>
<script src="/cedugenie/js/backoffice/addDisciplinaryAction.js"></script>
</body>
</html>