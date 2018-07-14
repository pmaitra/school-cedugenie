<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ include file="/file/sessionDataForChildPages.txt"%>
<html lang="de">
<head>
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<title>Add Book</title>
<%@ include file="/include/include.jsp" %>
<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
</style>
<script>


</script>
</head>
<body>
	<c:if test="${failuremsg ne null}">
			<div class="alert alert-danger">
				<strong>${failuremsg}</strong>	
			</div>
		</c:if>
		
		<c:if test="${successmsg ne null}">
			<div class="alert alert-success">
				<strong>${successmsg}</strong>
			</div>
		</c:if>
<div class="row">
	<div class="col-md-4">
	  <form:form method="POST" action="createNewFinancialYear.html">
			<section class="panel">
				<header class="panel-heading">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
					</div>
					<h2 class="panel-title">Configure Financial Year</h2>										
				</header>
				<c:forEach var="financialYear" items="${financialYearList}" varStatus="indexVal" >
                    <td>
                    	<input type="hidden" class="form-control"  name="financialYearNames" value="${financialYear.financialYearName}" >
                   	</td>
				 </c:forEach>
				<div style="display: block;" class="panel-body">
                   <div class="row">
                      <div class="form-group">
						<label class="col-sm-5 control-label">Financial Year Start Date</label>
						<div class="col-sm-7">
							<div class="input-group">
                                 <span class="input-group-addon">
                                     <i class="fa fa-calendar"></i>
                                 </span>
                                 <input type="text" name="sessionStartDate" id="sessionStartDate" class="form-control" data-plugin-datepicker="" required>
                            </div>
						</div>
					</div>
                    <div class="form-group">
						<label class="col-sm-5 control-label">Financial Year End Date</label>
						<div class="col-sm-7">
							<div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input type="text" class="form-control" data-plugin-datepicker="" name="sessionEndDate" id="sessionEndDate" required>
                            </div>
						</div>
					</div>
                    <div class="form-group">
						<label class="col-sm-5 control-label">Financial Year Name</label>
						<div class="col-sm-7">
							<input type="text" class="form-control" name="financialYearName" id="financialYearName" placeholder="eg: 2016-2017" pattern="^[0-9- ]*$"  title="Financial Name Contain Only Number " required>
						</div>
					</div>
                   </div>
				</div>
				<footer style="display: block;" class="panel-footer">
					<button class="btn btn-primary" type="Submit" name="submit" value="submit" onclick="return validateFrom();">Submit </button>
					<button type="reset" class="btn btn-default" >Reset</button>
				</footer>
			</section>
        </form:form>
        <!-- <div class="alert-" id="infomsgbox1" style="visibility:visible;">
			<span id="infomsg"></span>	
		</div> -->
		<blockquote class="danger">
			<p>Financial Year once created can't be deleted and edited(except name).</p>
			<small>By Administrator</small>
		</blockquote>
		<br/>
		
	</div>
	<c:choose>
		<c:when test="${financialYearList eq null || fn:length(financialYearList) lt 1 }">
			<div class="infomsgbox" id="infomsgbox" style="visibility:visible;">
				<span id="infomsg">No Financial Year Created Yet.</span>	
			</div>					
		</c:when>
	<c:otherwise>
	<div class="col-md-8">
		<form:form method="POST" action="updateFinancialYear.html">	
			<section class="panel">
			    <header class="panel-heading">
			        <div class="panel-actions">
			            <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
			        </div>
			
			        <h2 class="panel-title">Financial Year</h2>
			    </header>
			    <div class="panel-body">
					<table class="table table-bordered table-striped mb-none">
			            <thead>
			                <tr>
			                    <th>Select</th>
			                    <th>Financial Year Name</th>
			                    <th>Financial Year Start Date</th>
			                    <th>Financial Year End Date</th>
			                    <th>Financial Year Status</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<c:forEach var="financialYear" items="${financialYearList}" varStatus="indexVal" >
				                <tr>
				                    <td><input type="radio" name="financialYearCode" id="${indexVal.index}" value="${financialYear.financialYearCode}"></td>
				                    <td><input type="text" class="form-control" id="financialYearName${indexVal.index}" name="financialYearName" value="${financialYear.financialYearName}" pattern ="^[0-9- ]*$" title="Financial Name Contain Only Number and -"disabled="disabled"></td>
				                    <td><input type="text" class="form-control" id=""  value="${financialYear.sessionStartDate}" readonly="readonly"></td>
				                    <td><input type="text" class="form-control" id=""  value="${financialYear.sessionEndDate}" readonly="readonly"></td>
				                    <td><input type="text" class="form-control" id=""  value="${financialYear.yearStatus}" readonly="readonly"></td>
				                </tr>
				                <input type="hidden" id="oldFinancialYearName${indexVal.index}" name="oldFinancialYearName" value="${financialYear.financialYearName}">	<!-- added by Saif -->
			                </c:forEach>
			            </tbody>
			        </table>
			        <div class="warningbox" id="warningbox1">
						<span id="warningmsg1"></span>	
					</div>
			    </div>
			    <footer style="display: block;" class="panel-footer">
			        <button type="button" name="edit"  id="edit" class="btn btn-primary">Edit</button>
			        <button class="btn btn-danger" type="Submit" name="submit" value="submit" id="submitButton" disabled="disabled" onclick="return validateEdit();">Submit </button>
			        <button class="btn btn-default" type="reset" id="clearButton" disabled="disabled">Reset</button>
			    </footer>
			</section>
		</form:form>	
	</div>
	</c:otherwise>
	</c:choose>
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/backoffice/configureFinancialYear.js"></script>
</body>
</html>