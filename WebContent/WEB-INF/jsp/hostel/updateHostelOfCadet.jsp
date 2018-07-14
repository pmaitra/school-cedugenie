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
<title>Update House For Cadet</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>
<header class="page-header">
	<h2>Update House For Cadet</h2>
</header>
<div class="content-padding">
	<c:if test="${updateStatus eq 'success'}">
		<div class="alert alert-success">
			<strong>Update of house for cadet done successfully.</strong>	
		</div>
	</c:if>
	<c:if test="${updateStatus eq 'fail'}">
		<div class="alert alert-danger">
			<strong>Update of house for cadet failed.</strong>	
		</div>
	</c:if>
	<div class="row">
		<form name="mapHostelToStandard" action="submitUpdateOfHouseForCadet.html" method="POST">
	  		<div class="col-md-5">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Student Information</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div class="form-group">
							<label class="col-sm-5 control-label">Student RollNumber:</label>
							<div class="col-sm-7">
								<input type="text" name="userId" id="userId" class="form-control" required>
							</div>
						</div>
					</div>
				</section>
			</div>
			<div class="col-md-7" id="errorBox" style="display:none">
				<div class="alert alert-danger">
					<strong>No Data Found</strong>
				</div>
			</div>
			<div class="col-md-7">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						</div>
						<h2 class="panel-title">Student Info</h2>
					</header>
					<div class="panel-body">
						<table id="datatable-tabletools" class="table table-bordered table-striped mb-none" data-swf-path="/icam/assets/vendor/jquery-datatables/extras/TableTools/swf/copy_csv_xls_pdf.swf">
							<thead>
								<tr>
									<th>Roll Number</th>
									<th>Name</th>
									<th>House</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="text" class="form-control" id="rollNumber" disabled>
									</td>
									<td>
										<input type="text" class="form-control" id="resourceName" disabled>
									</td>
									<td>
										<input type="text" id="house" style="display:block" name="house.houseCode" class="form-control" disabled>
										<select id="houseList" style="display:none" name = "house.houseName" class="form-control" required></select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<footer style="display: block;" class="panel-footer">
						<button type="button" id="editButton" class="btn btn-primary" style="display:block">Edit</button>
						<button type="submit" id="submitButton" class="btn btn-primary" style="display:none">Submit</button>
					</footer>
				</section>
			</div>
		</form>
	</div>
</div>
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
	$("#userId").change(function(){
		var userId = $(this).val();
		if(userId == ""){
			$("#errorBox").css("display" , "block");
		}
		$.ajax({
			url:'/icam/getHouseAndInfoOfCadet.html',
			data: "userId="+userId,
			success : function(data){
				if(data != ""){
					$("#errorBox").css("display" , "none");
					var arr = data.split("#");
					$("#rollNumber").val(arr[0]);
					$("#resourceName").val(arr[1]);
					$("#house").val(arr[2]);
				}else{
					$("#errorBox").css("display" , "block");
				}
			}
		});
	});
	
	$("#editButton").click(function(){
		$("#editButton").css("display","none");
		$("#submitButton").css("display","block");
		$("#house").css("display","none");
		$("#houseList").css("display","block");
		$.ajax({
			url:'/icam/getAllHostels.html',
			success: function(data){
				var options ='<option value="">Select..</option>';
				if(data != ""){
					var arr = data.split("^");
					for(var i=0; i<arr.length; i++){
						var splitedArr = arr[i].split("#");
						options = options+'<option value="'+splitedArr[0]+'">'+splitedArr[1]+'</option>';
					}
				}
				$("#houseList").html(options);
			}
		});
	});
</script>
</body>
</html>