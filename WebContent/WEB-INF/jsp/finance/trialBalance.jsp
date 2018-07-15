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
<title>Trial Balance</title>
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
		<h2>Trial Balance</h2>
	</header>
	<div class="content-padding">
	<div class="row">
		<div class="col-md-5">		  	
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Trial Balance</h2>										
					</header>
					<div style="display: block;" class="panel-body">
					<div class="row">
					 <div class="col-md-12">
                  		<div class="form-group">
                            <label class="control-label">FROM</label>
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </span>
                                <input type="text" class="form-control" name="fromDate" id="fromDate" data-plugin-datepicker=""/>
                            </div>
                       	</div>   
               			<div class="form-group">
                           <label class="control-label">TO</label>
                           <div class="input-group">
                               <span class="input-group-addon">
                                   <i class="fa fa-calendar"></i>
                               </span>
                               <input type="text" class="form-control" name="toDate" id="toDate" data-plugin-datepicker=""/>
                           </div>
                      	</div>  
                      	</div>
                      </div>	                                    	
					</div>
					<footer style="display: block;" class="panel-footer">				
						<button type="button" id="get" class="btn btn-primary">Get</button>
					</footer>
				</section>        
		</div>
		<div class="col-md-7">
		  	
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>

						<h2 class="panel-title">Trial Balance</h2>										
					</header>
					<div style="display: block;" class="panel-body">
						<div id="tbDiv"></div>                               	
					</div>
					
				</section>
		</div>					
	</div>	
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript" src="/cedugenie/js/finance/trialBalance.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/nullValidation.js"></script>
</body>
</html>