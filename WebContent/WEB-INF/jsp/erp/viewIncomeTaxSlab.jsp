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
<title>Income tax Salary Slab</title>
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
	<div class="row">
		<div class="col-md-5">
		  	<form:form name="viewParameterOfIncomeTaxSalarySlab" id="viewParameterOfIncomeTaxSalarySlab" action="viewParameterOfIncomeTaxSalarySlab.html" method="POST">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>	
						<h2 class="panel-title">Income tax Salary Slab</h2>										
					</header>
					<div style="display: block;" class="panel-body">                     										
                        <div class="form-group">
	                        <c:forEach var="incomeAge" items="${incomeAgeList}">
	                            <input type="radio" name="incomeAge" value='${incomeAge.incomeAgeCode}' id="citizen" />${incomeAge.incomeAgeName}<br>
	                        </c:forEach>
                        </div> 	                                       
					</div>
					<footer style="display: block;" class="panel-footer">
						<button class="btn btn-primary" type="submit" id="submitButton" onclick="return validateForm();">Submit </button>
						<button type="reset" class="btn btn-default">Reset</button>
					</footer>
				</section>
				<input type="hidden" id="hidVal" name="hidVal" value='<c:out value="${calCulationFor}"/>'/>
	         </form:form>
		</div>
	</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/erp/commonIncomeTaxSalarySlab.js"></script>
</body>
</html>