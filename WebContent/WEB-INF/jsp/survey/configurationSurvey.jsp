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
<title>Create Subject Group</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }
       .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       }
</style>
<link href="/icam/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet"> 
</head>
<body>
		<c:if test="${status eq 'success'}">
			<div class="alert alert-success"  >
				<strong>Survey Configured SuccessFully</strong>	
			</div>
		</c:if>
		<c:if test="${status eq 'failed'}">
			<div class="alert alert-danger" >
				<strong>Failed To Configure Survey.</strong>	
			</div>
		</c:if>
		<c:if test="${status eq 'exists'}">
			<div class="alert alert-success" >
				<strong>Survey Configuration is already done!!.</strong>	
			</div>
		</c:if>			
					
<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>




</body>
</html>

