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
<title>Hostel Room List</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
		<header class= "page-header">	<!-- Added by Saif 29-03-2018 -->
			<h2>Room List</h2>
		</header>
		<div class = "content-padding">
			<c:choose>
				<c:when test="${roomList eq null}">
					<div class="alert alert-danger">
						<strong>No room has created yet.</strong>
					</div>
				</c:when>
			<c:otherwise>
				<form:form name="listRoomForm" id="listRoomForm" action="editRoomDetails.html" method="post">
					<section role="main" class="content-body">
						<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
								<h2 class="panel-title">Hostel Room List</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools" >
									<thead>
										<tr>
											<th>Select</th>
											<th>Room Name</th>
											<th>Hostel Name</th>
											<th>Total Bed</th>
											<th>Vacant</th>
											<th>Occupied</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="roomDetails" items="${roomList}">
											<tr class="gradeC">
												<td>
													<input type="radio" id="roomCode" name="roomCode" value="${roomDetails.roomCode}"/>
												</td>
												<td>
													${roomDetails.roomName}
												</td>
												<td>
													<input type="hidden" value="${roomDetails.hostelName}" name="hostelName"/>${roomDetails.hostelName}
												</td>
												<td>
													${roomDetails.bedTotal}
												</td>
												<td>
													${roomDetails.bedVaccent}
												</td>
												<td>
													${roomDetails.bedOccupied}
												</td>
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
			


<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
   $('input[name=roomCode]').change(function(){
        $('form').submit();
   });
});
</script>
</body>
</html>