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
<title>Rating Policy</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
			<c:choose>
			<c:when test="${currentAcademicYear eq null}">	
					<div class="errorbox" id="errorbox" style="visibility:visible;">
						<span id="errormsg">No Academic Year Found</span>
					</div>
				</c:when>
			<c:otherwise>
				
					 <div class="row">
						<div class="col-md-6 col-md-offset-3">
							<form:form name="libraryPolicy"  method="POST" action="saveRatingPolicy.html">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <div class="panel-actions">
	                                        <a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
	                                    </div>
	
	                                    <h2 class="panel-title">Book Rating Policy</h2>										
	                                </header>
	                                <div class="panel-body">
	                                <c:forEach var="year" items="${currentAcademicYear}">
										<input class="textfield1" type="hidden" readonly="readonly" name="academicYear" id="academicYear" value='<c:out value="${year.academicYearCode}"/>' />
									</c:forEach>
	                                    <table class="table table-bordered table-striped mb-none">
	                                        <thead>
	                                            <tr>
	                                                <th>Rating</th>
	                                                <th>Minimum Lenders</th>
	                                                <th>Maximum Lenders</th>
	                                            </tr>
	                                        </thead>
	                                        <c:choose>
											<c:when test="${ratingList eq null && ratingList.size() eq 0}">
												<div class="errorbox" id="errorbox" style="visibility:visible;">
													<span id="errormsg">No Rating List Found</span>
												</div>	
											</c:when>
											<c:otherwise>
	                                        <tbody>
	                                        	<c:forEach var="rat" items="${ratingList}" varStatus="p">
	                                            <tr>
	                                                <td><input class="form-control" type="text" name="rating" id="rating${p.index}" value="${rat.rating}" readonly="readonly"></td>
	                                                <td><input class="form-control" type="text" name="minLendingsFrom" id="minLendingsFrom${p.index}"  value="${rat.minLendingsFrom}"></td>
	                                                <td><input type="text" class="form-control" name="minLendingsTo" id="minLendingsTo${p.index}"  value="${rat.minLendingsTo}"></td>
	                                            </tr>  
	                                            </c:forEach>
	                                        </tbody>
	                                        </c:otherwise>
	                                      </c:choose>
	                                    </table>                                      
	                                </div>
	                                <footer style="display: block;" class="panel-footer">
	                                    <button class="btn btn-primary" type= "submit" id="submitButton" name="submit">Submit </button>
	                                    <button type="reset" name="reset" class="btn btn-default">Reset</button>
	                                </footer>
	                            </section>
	                            <div class=warningbox id="warningbox" >
									<span id="waringmsg"></span>	
								</div>
	                        </form:form>	
						</div>
					</div>	
					</c:otherwise>
					</c:choose>


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/icam/js/common/jquery-ui.min.js"></script>
<script type="text/javascript" src="/icam/js/backoffice/ratingPolicy.js"></script>
</body>
</html>