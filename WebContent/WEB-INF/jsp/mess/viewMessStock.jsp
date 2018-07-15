<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Mess Commodity Stock</title>
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
		<h2>Mess Commodity Stock</h2>
	</header>
	<div class="content-padding">
		<div class="row">
			<c:choose>
				<c:when test="${commodityList eq null || empty commodityList}">
					<div class="alert alert-danger">
						<strong>No Commodity Found In Mess.</strong>
					</div>
				</c:when>
				<c:otherwise>
					<div class="col-md-12">
						<section class="panel">
							<header class="panel-heading">
							    <h2 class="panel-title">Non Perishable Items List</h2>
							</header>
	                        <div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-tabletools">
									<thead>
										<tr>
											<th>Commodity</th>
											<th>Commodity A/U</th>
											<th>Quantity in Stock</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="commodity" items="${commodityList}">
											<tr class="gradeC">
												<td>
													${commodity.commodityName}
												</td>
												<td>
													${commodity.commodityDesc}
												</td>
												<td>
													${commodity.inStock}
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</section>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
</body>
</html>