<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Hostel Room Type</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
	<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
		<h2>Room Type List</h2>
	</header>
		<div class = "content-padding">
			<c:choose>
		<c:when test="${roomTypeList eq null}">
			<div class="alert alert-danger">
				<strong>No room type created yet.</strong>
			</div>
		</c:when>
	<c:otherwise>
		<form:form method="POST" id="editRoomType" name="editRoomType" action="editRoomType.html">
			<section role="main" class="content-body">
				<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								<h2 class="panel-title">Room Type List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" >
									<thead>
										<tr>
											<th>Select</th>
											<th>Room Type Name</th>
											<c:forEach var="sc" items="${roomTypeList}" end="0">
												<c:forEach var = "socialCat" items = "${sc.socialCategoryList}">
													<th>${socialCat.socialCategoryName}</th>
												</c:forEach>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="rt" items="${roomTypeList}">
											<tr class="gradeC">
												<td><input type="radio" name="roomType" value="${rt.roomTypeCode}"/></td>
												<td>${rt.roomTypeName}</td>
												<c:forEach var="ch" items="${rt.socialCategoryList}" varStatus="ind">
													<td>${ch.amount}</td>
												</c:forEach>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
					</section>
				</form:form>
			</c:otherwise>
			</c:choose>
		</div>
		
		
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
   $('input[name=roomType]').change(function(){
        $('form').submit();
   });
});
</script>
</body>
</html>