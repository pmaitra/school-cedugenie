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
<title>Student Details Form</title>
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
		<header class="page-header">	<!-- Added by Saif 29-03-2018 -->
				<h2>Commodity Allotment History</h2>
		</header>
			<div class = "content-padding">
				<!-- start: page -->
					<div class="row">
						<c:choose>
						<c:when test="${commodityList eq null && commodityList.size() eq 0}">
							<span>Asset Commodities Not Found.</span>
						</c:when>
						<c:otherwise>
							<div class="col-md-4 col-md-offset-4">
								<section class="panel">
									<header class="panel-heading">
										<div class="panel-actions">
											<a href="#" class="panel-action panel-action-toggle" data-panel-toggle=""></a>
										</div>

										<h2 class="panel-title">Commodity Allotment History</h2>										
									</header>
									<div style="display: block;" class="panel-body">
										<div class="form-group">
											<label class="control-label">Commodity</label>
											<select class="form-control" id="commodityCode">
												<option value="">Select</option>
												<c:forEach var="commodity" items="${commodityList}">
													<option value="${commodity.commodityCode}">${commodity.commodityName}</option>				
												</c:forEach>
											</select>
										</div>
										<div class="form-group">
											<label class="control-label">Commodity</label>
											<select class="form-control" id="individualCommodity">												
											</select>
										</div>
 									</div>									
								</section>
							</div>
							<div class="col-md-12" id="commodityDetailsTable" style="display: none;">
	                            <section class="panel">
	                                <header class="panel-heading">
	                                    <h2 class="panel-title">Individual Commodity List</h2>
	                                </header>
			                                <div class="panel-body">
			                                    <table class="table table-bordered table-striped mb-none">
			                                        <thead>
			                                            <tr>
			                                                <th>Alloted To</th>
															<th>Alloted On</th>
															<th>Alloted By</th>
															<th>Returned To</th>
															<th>Returned On</th>
															<th>Comments</th>
			                                            </tr>
			                                        </thead>
			                                        <tbody id="tableBody">
			                                        
			                                        </tbody>
			                                    </table>
			                                </div>
	                            </section>
							</div>
						</c:otherwise>
						</c:choose>
					</div>
                   
                   
					<!-- end: page -->
			</div>
		
					


<script src="/icam/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
$("#commodityCode").change(function(){
	var commodityCode=$("#commodityCode").val();
	document.getElementById("commodityDetailsTable").style.display="none";
	var table = document.getElementById('tableBody');	
	if(commodityCode=="")
	{
		table.innerHTML="";
	}
	else
	{
		table.innerHTML="";
	$.ajax({
    	url: '/icam/getIndividualCommodityList.html',
    	dataType: 'json',
    	data: "commodityCode=" + ($(this).val()),
    	success: function(data) {
    		var individualCommodity = document.getElementById("individualCommodity");
    		for(var i=individualCommodity.length;i>=0;i--)
			{
    			individualCommodity.remove(i);
			}
    		
    		if(data != "")
    		{
    			data=data.split("~*~");
    			individualCommodity.add(new Option("Select...", ""),null);
	        	for(var j=0;j<data.length-1;j++)
	        	{
	        		individualCommodity.add(new Option(data[j], data[j]),null);
	        	}
    		}else{
    			alert("Individual Commodity Not Found");
    		}
    	}
    
    	});
	}
});



$("#individualCommodity").change(function(){
	var yearval=$("#individualAsset").val();
	document.getElementById("commodityDetailsTable").style.display="none";
	var table = document.getElementById('tableBody');
	if(yearval=="")
	{		
		table.innerHTML="";
	}
	else
	{
		table.innerHTML="";
	$.ajax({
    	url: '/icam/getCommodityAllotmentHistory.html',
    	dataType: 'json',
    	data: "individualCommodity=" + ($(this).val()),
    	success: function(data) {
    		
    		var table = document.getElementById("tableBody");
    		if(data != "")
    		{
    			data=data.split("~*~");
    			for(var i=0;i<data.length-1;i++)
    			{
    				var indvData=data[i].split("*-*");
    				for(var j=0;j<indvData.length;j++)
    					if(indvData[j]=='null' || indvData[j]==null)
    						indvData[j]="";
    				
    				var rowCount = table.rows.length;          
        			var row = table.insertRow(rowCount);
        			
        			var cell0 = row.insertCell(0);
        	        var element0 = document.createTextNode(indvData[0]);
        	        cell0.appendChild(element0);
        	        
        	        var cell1 = row.insertCell(1);
        	        var element1 = document.createTextNode(indvData[1]);
        	        cell1.appendChild(element1);
        	        
        	        var cell2 = row.insertCell(2);
        	        var element2 = document.createTextNode(indvData[2]);
        	        cell2.appendChild(element2);
        	        
        	        var cell3 = row.insertCell(3);
        	        var element3 = document.createTextNode(indvData[3]);
        	        cell3.appendChild(element3);
        	        
        	        var cell4 = row.insertCell(4);
        	        var element4 = document.createTextNode(indvData[4]);
        	        cell4.appendChild(element4);
        	        
        	        var cell5 = row.insertCell(5);
        	        var element5 = document.createTextNode(indvData[5]);
        	        cell5.appendChild(element5);
    			}
    			document.getElementById("commodityDetailsTable").style.display="block";
    		}else{
    			alert("This Commodity Was Never Alloted To Any One");
    		}
    	}
    	}); //ajax ends
	}//else ends
});

});

</script>
</body>
</html>