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
       } .mb-md{
       	   display: none;
       }
</style>

</head>
<body>
		<header class="page-header">
			<h2>In Active Course</h2>	<!--  ADDED BY SAIF 29-03-2018 -->
		</header>
				<div class = "content-padding">
					<div class="row">
						<c:if test="${updateFailStatus ne null }">
							<div class="alert alert-success" id="successboxmsgbox">
								<strong>${updateFailStatus}</strong>	
							</div>
						</c:if>
						<c:if test="${updateSuccessStatus ne null }">
							<div class="alert alert-success" id="successboxmsgbox">
								<strong>${updateSuccessStatus}</strong>	
							</div>
						</c:if>
						<div class="col-md-6 col-md-offset-3">	
                           	<input type="hidden" name="saveId" id="saveId">
                           	<input type="hidden" name="statusValue" id="statusValue">
                            <section class="panel">
                                <header class="panel-heading">
                                    <div class="panel-actions">
                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
                                    </div>

                                    <h2 class="panel-title">Inactive Subject </h2>
                                </header>
                                <div class="panel-body">

                                    <table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
                                        <thead>
                                            <tr>
												<th>Subject  Name</th>
												<th>Subject Group Name</th>	
												<th>Actions</th>					
                                            </tr>
                                        </thead>
                                        <tbody>
                                           <c:forEach var="subject"  items="${subjectList}" varStatus="i">
	                                                <tr>
	                                             		<td>${subject.subjectName}</td>
	                                             		<td>${subject.subjectGroup}</td>
 	                                                    <td class="actions">
															<a href="setInactiveSubject.html?subjectCode =${subject.subjectCode}" class="on-default remove-row" id = "delete"><i class="fa fa-trash-o"></i></a>
														 </td>
	                                                </tr>
	                                              </c:forEach> 
                                        </tbody>
                                    </table>
                                </div>
                            </section>
						</div>
					</div>
				</div>

					
					
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>