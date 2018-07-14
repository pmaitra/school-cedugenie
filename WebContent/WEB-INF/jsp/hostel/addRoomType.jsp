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
		<h2>Add Room Type</h2>
	</header>
		<div class = "content-padding">
			<div class="row">
						<div class="col-md-6 col-md-offset-3">
						  <form id="addRoomTypeNew" name="addRoomTypeNew" action="addRoomTypeNew.html" class="form-horizontal" method="POST">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title" data-toggle="tooltip" data-placement="left" title="" data-original-title="Tooltip Data">Add Room Type </h2>	
									</header>
									<div style="display: block;" class="panel-body">
                                        
										<div class="form-group">
											<label class="col-sm-4 control-label">Room Type </label>
											<div class="col-sm-8">
												<input type="text" id ="roomTypeName" name ="roomTypeName" class="form-control" placeholder="" required>
											</div>
										</div>
                                        <hr>
                                        <h3 class="text-center">Charges</h3>
                                        <hr>
                                        <c:forEach var="sc" items="${socialCategoryList}">
	                                        <div class="form-group">
												<label class="col-sm-4 control-label">${sc.socialCategoryName}</label>
												<input type="hidden" name="sc" value="${sc.socialCategoryCode}">
												<div class="col-sm-8">
													<input type="text" class="form-control" name="${sc.socialCategoryCode}" placeholder="" value=0.00>
												</div>
											</div>
										</c:forEach>
									</div>
									<footer style="display: block;" class="panel-footer">
										<button type="submit" id="submit" name="submit" class="submitbtn btn btn-primary" onclick="return checkRoomTypeName();">Submit </button>
										<button type="reset" class="clearbtn btn btn-default">Reset</button>
									</footer>
								</section>
                            </form>
						</div>
					</div>
		</div>

						

<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script src="/icam/js/hostel/addRoomType.js"></script>
</body>
</html>