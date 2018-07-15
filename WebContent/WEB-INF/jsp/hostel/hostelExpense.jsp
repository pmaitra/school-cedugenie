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
<title>Save Hostel Expenses</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
</head>
<body>	
		<div class="row">
			<c:if test="${status eq 'success'}">
					<div class="alert alert-success">
						<strong>Consumption submission successful.</strong>	
					</div>
				</c:if>
				<c:if test="${status eq 'fail'}">
					<div class="alert alert-danger">
						<strong>Consumption submission failed.</strong>	
					</div>
				</c:if>
			<form:form method="POST" id="" name=""  action="saveHostelExpense.html" >
                 <div class="col-md-12">
					<section class="panel">
						<header class="panel-heading">
							<div class="panel-actions">
								<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							</div>
							<h2 class="panel-title">
								Save Hostel Expenses
							</h2> 
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="hostelExpense">
									<thead>
										<tr>
                                            <th>Commodity</th>
											<th>Stock</th>
											<th>Morning</th>
											<th>Noon</th>
											<th>Evening</th>
											<th>Night</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<tr>
                                            <td>
                                            	<input type="text" class="form-control commodityNameClass" id="commodity0" name="commodity">
                                           	</td>
                                            <td>
                                                <input type="text" id="stock0" name="stock" class="form-control" readonly="readonly"/>
                                            </td>
											<td>
												<input type="text" id="morning0" name="morning" class="form-control" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}"/>
											</td>
                                            <td>
                                            	<input type="text" id="noon0" name="noon" class="form-control" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}" />
                                           	</td>
											<td>
												<input type="text" id="evening0" name="evening" class="form-control" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}"/>
											</td>
                                            <td>
                                            	<input type="text" id="night0" name="night" class="form-control" value="0.00" onfocus="this.value='';" onblur="javascript: if(this.value==''){this.value='0.00';}"/>
                                           	</td>
                                           	<td>
                                           		<a class="on-default remove-row addbtn" href="#"><i class="fa fa-plus-square"></i></a>
                                           	</td>
										</tr>
									</tbody>
								</table>
							</div>
                            <footer style="display: block;" class="panel-footer">
                                <button type="submit" class="mb-xs mt-xs mr-xs btn btn-primary">Submit</button>
                                <button type="button" class="btn btn-default">Reset</button>
                            </footer>
						</section>
					</div>
                </form:form>
            </div>
	
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/cedugenie/js/common/jquery-ui.min.js"></script>
<script src="/cedugenie/js/hostel/hostelExpense.js"></script>
</body>
</html>