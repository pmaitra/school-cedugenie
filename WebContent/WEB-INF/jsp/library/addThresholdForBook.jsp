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
<title>Add Book's Threshold</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }/* .mb-md{
       	   display: none;
       } */
</style>
</head>
<body>

			<header class="page-header">
				<h2>Add Book Threshold</h2>
			</header>
			<div class="content-padding">
					<div class="row" >
						<c:if test="${Message != null}">
							<div class="btnsarea01">
								<c:if test="${Message eq 'success'}">
									<div class="alert alert-success">
										<strong>Data Successfully Inserted</strong>
									</div>
								</c:if>
								<c:if test="${Message eq 'fail'}">
									<div class="alert alert-danger">
										<strong>Problem occured while inserting.</strong>
									</div>
								</c:if>
							</div>			
						</c:if>
						<c:choose>
							<c:when test="${pagedListHolder==null}">
								<div class="errorbox" id="errorbox" style="visibility:visible;">
									<span class="errormsg">Data Not Found</span>
								</div>
							</c:when>
						<c:otherwise>
						<form:form id="paydonefrm" name="addThresholdDetails">
							<div class="col-md-12">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Books</h2>
	                                </header>
	                                	<div class="panel-body">
			                                    <table id="thres" class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Select</th>
															<th>Book Code</th>
															<th>Book Name</th>
															<th>Threshold</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody>
			                                        	<c:forEach var="item" items="${pagedListHolder.pageList}" varStatus="ind">
														<tr class="gradeX">
															<td>
																<input id="ch${ind.index}" type="checkbox" name="itemThresholdValue" value="${item.itemCode}" disabled="disabled" />
															</td>
															<td>
																${item.itemCode}
															</td>
															<td>
																${item.itemName}
															</td>
															<td>
																<input type="text" class="form-control" id="tx${ind.index}" readonly="readonly" name="${item.itemCode}" value="${item.threshold}"/>
																<input type="hidden" name="oldThresholdValue" id="oldThresholdValue${ind.index}" value="${item.threshold}" />
															</td>
														</tr>
														</c:forEach>
			                                        </tbody>
			                                    </table>
			                                </div>
	                                <footer style="display: block;" class="panel-footer">
										<button class="btn btn-info" type="button" name="editBut" id="editBut" onclick="edit();" >Edit</button>
										<button type="submit" class="btn btn-primary" name="submitButton" disabled="disabled" id="submitButton" onclick="return onCheckBoxSubmit();">Submit</button>
									</footer>
	                            </section>
							</div>
						</form:form>
						</c:otherwise>
						</c:choose>
					</div>
                 </div>

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/icam/js/library/addThresholdForBook.js"></script>
</body>
</html>