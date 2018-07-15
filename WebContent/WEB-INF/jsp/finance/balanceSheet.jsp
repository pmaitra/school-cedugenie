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
<title>Balance Sheet</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
       .total_show{
       	    display: block;
		    padding: 5px 0;
		    font-weight: bold;
		    border-top: 1px solid #ccc;
		    border-bottom: 1px solid #ccc;
		    margin-bottom: 10px;
		    text-align:right;
       }
       .hide_data{
       	display: block;
       	text-align:right;
       	border-bottom: 1px solid #ccc;
       	margin-bottom: 10px;
       }
       .hide_data_rs{
       	line-height: 32px;
       }
       .data_show{
       text-align:right;
       }
       
      /*   .scroll-to-top{
           display: none !important;
       }
       .datepicker-dropdown{
        display: none !important;
       }
       #ui-datepicker-div{
       	z-index:99999 !important;
       } */
</style>
<!-- <link href="/cedugenie/assets/custom-caleder/jquery-ui.css" type="text/css" rel="stylesheet">  -->
</head>
<body>
	<header class="page-header">
		<h2>Balance Sheet</h2>
	</header>
	<div class="content-padding">
	  <div class="row">
		<div class="col-md-5">
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
						</div>
						<h2 class="panel-title">Balance Sheet</h2>										
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
                                <input type="text" class="form-control" name="fromDate" id="fromDate" data-plugin-datepicker="" required/>
                            </div>                            
                       	</div>   
               			<div class="form-group">
                           <label class="control-label">TO</label>
                           <div class="input-group">
                               <span class="input-group-addon">
                                   <i class="fa fa-calendar"></i>
                               </span>
                               <input type="text" class="form-control" name="toDate" id="toDate" data-plugin-datepicker="" />
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
		
	</div>	
	
	<section role="main" class="content-body">		
				<section class="panel">
					<header class="panel-heading">
						<div class="panel-actions">
							<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
							<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
						</div>						
						<h2 class="panel-title">BalanceSheet</h2>
					</header>
					 <c:forEach var="template" items="${templateList}" varStatus="i">
					 	<input type = "hidden" id = "total${i.index}" value = "${template.totalAmount}">
					 </c:forEach>
					<div class="panel-body" id="blncSheet" style="display: block">
						
					</div>					
				</section>			
			</section>
			<%-- </c:otherwise>
</c:choose> --%>	
</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<!-- <script src="/cedugenie/assets/custom-caleder/jquery-ui.js" type="text/javascript"></script> -->
<script type="text/javascript" src="/cedugenie/js/finance/balanceSheet.js"></script>
<script type="text/javascript" src="/cedugenie/js/finance/nullValidation.js"></script>
<script type="text/javascript">
		
		/* $(document).ready( function(){
			var liabilityValue = document.getElementById("total0").value;
			var assetValue = document.getElementById("total1").value;
			var difference;
			if(liabilityValue>assetValue){
				difference = liabilityValue - assetValue;
				 document.getElementById("diffrence0").style.display = "block";
				 document.getElementById("diffrence0").innerHTML = difference;
			}
			if(assetValue>liabilityValue){
				difference = assetValue - liabilityValue;
				 document.getElementById("difference1").style.display = "block";
				 document.getElementById("difference1").innerHTML = difference;
			}
			alert("difference=="+difference);
		}); */
		
</script>
</body>
</html>