<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Current Openings</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       } .mb-md{
       	   display: none;
       }
</style>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/admission/validateAdmissionRadioButton.js"></script>
</head>
<body>

				<header class= "page-header">
					<h2>Current Openings</h2>
				</header>
				<div class="content-padding">
					<div class= "row">
						<c:if test="${msg ne null}">
							<div class="alert alert-danger"  >
								<strong>${msg}</strong>	
							</div>
						</c:if>	
							<div class="col-md-12">
	                            <section class="panel">
	                            	<form:form method="POST" id="newAdmissionForm" name="newAdmissionForm" action="newAdmissionForm.html">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Available Drive</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="datatable-tabletools" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Select</th>
															<th>Standard</th>
															<th>Course Type</th>
															<th>Openings</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        	<c:forEach var="item" items="${admissionFormList}">
															<tr>
																<td>
																	<input type="radio" name="courseCode" id="admissionFormObjId" value="${item.courseCode}"/>
																	<input type="hidden" name="${item.courseCode}"  value="${item.courseClass}"/>
																	<input type="hidden" name="${item.courseClass}"  value="${item.admissionFormYear}"/>
																</td>
																<td><c:out value="${item.courseName}"></c:out></td>
																<td><c:out value="${item.courseType}"></c:out></td>
																<td><c:out value="${item.noOfOpenings}"></c:out></td>
															</tr>
														</c:forEach>
			                                        </tbody>
			                                    </table>
			                                </div>
			                                 <footer style="display: block;" class="panel-footer">
			                                    <button type="submit" id="submitForm" class="btn btn-primary" onclick = "return validRadio('courseCode')">Submit </button>
			                                </footer>
			                          </form:form>	
	                            </section>
							</div>
						</div>
					</div>		
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>