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
<title>Configure Repository Directory</title>
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
						<h2>Repository</h2>
					</header>
					<div class="content-padding">
						<div class="row">
						<c:if test="${successMessage ne null}">
							<div class="alert alert-success" id="successboxmsgbox">
								<strong>${successMessage}</strong>	
							</div>
						</c:if>
						<c:if test="${errorMessage ne null}">
							<div class="alert alert-danger" id="errormsgbox" >
								<strong>${errorMessage}</strong>	
							</div>
						</c:if>
							<input type = "hidden" id = "checkDataExistanceFromDb" value="${repoStructure.os}"/>
								<div class="col-md-6 col-md-offset-3" id = "savedConfiguration" style="display:none">
								  	<section class="panel">
										<header class="panel-heading">
											<div class="panel-actions">
												<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
											</div>
											<h2 class="panel-title" data-toggle="tooltip" data-placement="left" title="" data-original-title="Tooltip Data">The Current Repository is:</h2>	
										</header>
										<div style="display: block;" class="panel-body"> 
											<table class="table table-striped">
												<tr>
													<td>
														<div class="radio-custom radio-primary">
															<input type="radio" id="" name="" value="repoStructure.os" checked="" disabled>
															<label for="os">${repoStructure.os}</label>
														</div>
													</td>
													<td>
														<input type="text" class="form-control" id="" name="" value="${repoStructure.repositoryPathName}" disabled>
													</td>
												</tr>
											</table>
										</div>
										<footer style="display: block;" class="panel-footer">
											<button type="button" class="btn btn-primary" id="editbtn">Change repository</button>
										</footer>
									</section>
								</div>
								<div class="col-md-6 col-md-offset-3" id = "newConfiguration" style="display:none">
								  	<form id="submitRepositoryDirectory" class="form-horizontal" action="submitRepositoryDirectory.html" method="POST">
										<section class="panel">
											<header class="panel-heading">
												<div class="panel-actions">
													<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
												</div>
												<h2 class="panel-title" data-toggle="tooltip" data-placement="left" title="" data-original-title="Tooltip Data">OS Type </h2>	
											</header>
											<div style="display: block;" class="panel-body"> 
												<table class="table table-striped">
													<tr>
														<td>
															<div class="radio-custom radio-primary">
																<input type="radio" id="winOS" name="os" value="Windows" checked="">
																<label for="os">Windows</label>
															</div>
														</td>
														<td>
															<input type="text" class="form-control" id="windowsPath" name="repositoryPathName" placeholder="drive:/folder/subfolder">
														</td>
													</tr>
													<tr>
														<td>
															<div class="radio-custom radio-primary">
																<input type="radio" id="linuxOS" name="os" value="Linux">
																<label for="os">Linux</label>
															</div>
														</td>
														<td>
															<input type="text" class="form-control" id="linuxPath" name="repositoryPathName" disabled="" placeholder="drive:/folder/subfolder">
														</td>
													</tr>
													<tr>
														<td>
															<div class="radio-custom radio-primary">
																<input type="radio" id="macOS" name="os" value="Mac">
																<label for="os">MAC</label>
															</div>
														</td>
														<td>
															<input type="text" class="form-control" id="macPath" name="repositoryPathName" disabled="" placeholder="drive:/folder/subfolder">
														</td>
													</tr>
												</table>
											</div>
											<footer style="display: block;" class="panel-footer">
												<button type="submit" class="btn btn-primary" >Submit </button>
												<button type="reset" class="btn btn-default">Reset</button>
											</footer>
										</section>
		                            </form>
								</div>
							</div>
						</div>	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
	$('#editbtn').click(function(){
		$('#savedConfiguration').css('display','none');
		$('#newConfiguration').css('display','block');
	});
    $(document).ready(function() {
    	var dataExistanceFromDb = $("#checkDataExistanceFromDb").val();
    	if(dataExistanceFromDb != ""){
    		$('#savedConfiguration').css('display','block');
    	}else{
    		$('#newConfiguration').css('display','block');
    	}
        $('input[name=os]').click(function () {
            if (this.id == "winOS") {
                $("#windowsPath").removeAttr('disabled');
                $("#linuxPath").attr('disabled','disabled');
            	$("#macPath").attr('disabled','disabled');
            }
            if (this.id == "linuxOS") {
                $("#linuxPath").removeAttr('disabled');
                $("#windowsPath").attr('disabled','disabled');
            	$("#macPath").attr('disabled','disabled');
            }
            if (this.id == "macOS") {
                $("#macPath").removeAttr('disabled');
                $("#windowsPath").attr('disabled','disabled');
            	$("#linuxPath").attr('disabled','disabled');
            }
        });
    });
</script>
</body>
</html>