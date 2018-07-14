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
<title>Assign Fees To Template</title>
<%@ include file="/include/include.jsp" %>

<style type="text/css">
       .scroll-to-top{
           display: none !important;
       }.mb-md{
       	   display: none;
       }
       #profileMatrixTable{
       font-size:11px !important;
       }
</style>
</head>
<body>
			<header class="page-header">
				<h2>Profile Matrix</h2>
			</header>
			<div class="content-padding">
		
			<div class="row">
				<div class="col-md-12">
					<form name="form1" action="submitProfileMatrix.html" method="POST">
						<div class="row" style="display:block;" id="profileMatrixDiv">
	                        <section class="panel">
	                             <header class="panel-heading">
	                                 <div class="panel-actions">
	                                     <a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
	                                 </div>
	                                 <h2 class="panel-title">Profile Matrix</h2>
	                             </header>
	                             <div class="panel-body">
	                             	<input type="hidden" id="moduleSize" value="${moduleList.size()}">
	                             	<div class="table-responsive">
	                             		<table class="table table-bordered table-striped mb-none" id="profileMatrixTable">
	                                     	<thead>
		                                         <tr>
		                                         	<th>Role</th>
		                                             <c:forEach var= "module" items="${moduleList}">
														<th>
															${module.moduleName}
															<input type="hidden" name = "moduleCode" id = "moduleCode" value = "${module.moduleCode}">
															<input type="hidden" name = "moduleName" id = "moduleName" value = "${module.moduleName}">
														</th>
													</c:forEach>
			                                     </tr>
		                                     </thead>
		                                     <tbody id="profileMatrixBody">
		                                     </tbody>
	                                 	</table>
	                             	</div>
	                             </div>
	                             <footer style="display: block;" class="panel-footer">
	                                 <button class="btn btn-primary" type="submit" id="submit" name="submit">Submit </button>
	                                 <button type="reset" class="btn btn-default">Reset</button>
	                             </footer>
	                         </section>
						</div>
	                </form>
				</div>
			</div>
		</div>
<script src="/cedugenie/assets/vendor/autosize/iframeResizer.contentWindow.min.js"></script>
<%@ include file="/include/js-include.jsp" %>
<script type="text/javascript">
	$(document).ready( function(){
    	$.ajax({
	        url: '/cedugenie/getRolesForProfileMatrix.html',
	        dataType: 'json',
	        success: function(dataDB) {
	        	if(dataDB != null){
	        		var arr = dataDB.split("%");
	        		var tabSearchOccurance = arr[0].indexOf("#");
	        		if(tabSearchOccurance == "-1"){
	        			for (var i=0; i<arr.length - 1; i++){
							var role = arr[i].split(":");
		                    var table = document.getElementById("profileMatrixTable");
			        	    var rowCount = table.rows.length;
			        	    var row = table.insertRow(rowCount);
			        	    
			        	    var cell1 = row.insertCell(0);
			        	    var element1 = document.createTextNode(role[1]);
			        	    cell1.appendChild(element1);
			        	    
			        	    var element2 = document.createElement("input");
			        	    element2.type = "hidden";
			        	    element2.value = role[0];
			        	    element2.name = "roleHead";
			        	    cell1.appendChild(element2);

		        	    	var cnt = 0;
		        	    	var moduleCodes = document.getElementsByName("moduleCode");
			        	    var moduleNames = document.getElementsByName("moduleName");
			        	    var countModuleRows = document.getElementById("moduleSize").value;
			        	    for(var j = 1 ; j <= countModuleRows ; j++){
			        	    	var cell = row.insertCell(j);
			        	        
			        	    	var nodeTab = document.createElement('div');        
			        	        nodeTab.innerHTML = '<label for="'+role[0]+"##"+moduleCodes[cnt].value+'"><input type="checkbox" name="'+role[0]+"##"+moduleCodes[cnt].value+'">Tab</label>';    
			        	      
			        	        var nodeSearch = document.createElement('div');        
			        	        nodeSearch.innerHTML = '<label for="'+role[0]+"##"+moduleCodes[cnt].value+"$"+'"><input type="checkbox" name="'+role[0]+"##"+moduleCodes[cnt].value+"##"+moduleCodes[cnt].value+'">Search</label>';
			        	        
			        	        cnt++;
			        	        
			        	        cell.appendChild(nodeTab);
			        	        cell.appendChild(nodeSearch);
			        	    }
						}
	        		}else{
	        			for (var i=0; i<arr.length - 1; i++){
							var individualRoleModTabSearchArray = arr[i].split("*");
							var role = individualRoleModTabSearchArray[individualRoleModTabSearchArray.length-1].split(":");
		                    var table = document.getElementById("profileMatrixTable");
			        	    var rowCount = table.rows.length;
			        	    var row = table.insertRow(rowCount);
			        	    
			        	    var cell1 = row.insertCell(0);
			        	    var element1 = document.createTextNode(role[1]);
			        	    cell1.appendChild(element1);
			        	    
			        	    var element2 = document.createElement("input");
			        	    element2.type = "hidden";
			        	    element2.value = role[0];
			        	    element2.name = "roleHead";
			        	    cell1.appendChild(element2);
			        	    
			        	    for(var k = 0; k <= individualRoleModTabSearchArray.length-2 ; k++){
			        	    	var modTabSearchArr = individualRoleModTabSearchArray[k].split("#");
			        	    	var module = modTabSearchArr[0];
			        	    	var tabCheckBox = modTabSearchArr[1];
			        	    	var searchCheckBox = modTabSearchArr[2];
			        	    	var cell = row.insertCell(1+k);
			        	        if(tabCheckBox == "true"){
			        	        	var nodeTab = document.createElement('div');
				        	        nodeTab.innerHTML = '<label for="'+role[0]+"##"+module+'"><input type="checkbox" checked name="'+role[0]+"##"+module+'">Tab</label>';    
				        	    }else{
				        	    	var nodeTab = document.createElement('div');
				        	        nodeTab.innerHTML = '<label for="'+role[0]+"##"+module+'"><input type="checkbox" name="'+role[0]+"##"+module+'">Tab</label>';
				        	    }
			        	    	if(searchCheckBox == "true"){
			        	    		var nodeSearch = document.createElement('div');
				        	        nodeSearch.innerHTML = '<label for="'+role[0]+"##"+module+"$"+'"><input type="checkbox" checked name="'+role[0]+"##"+module+"##"+module+'">Search</label>';
			        	    	}else{
			        	    		var nodeSearch = document.createElement('div');
				        	        nodeSearch.innerHTML = '<label for="'+role[0]+"##"+module+"$"+'"><input type="checkbox" name="'+role[0]+"##"+module+"##"+module+'">Search</label>';
			        	    	}
			        	    	cell.appendChild(nodeTab);
			        	        cell.appendChild(nodeSearch);
			        	    }
							/* var tabAtFirstRow = individualRoleModTabSearchArray[0].split("#");
			        	    if(null!= tabAtFirstRow[0]){
			        	    	
			        	    } */
			        	    /* else{
			        	    	var cnt = 0;
			        	    	var moduleCodes = document.getElementsByName("moduleCode");
				        	    var moduleNames = document.getElementsByName("moduleName");
				        	    var countModuleRows = document.getElementById("moduleSize").value;
				        	    
				        	    for(var j = 1 ; j <= countModuleRows ; j++){
				        	    	var cell = row.insertCell(j);
				        	    	
			        	        	var nodeTab = document.createElement('div');        
				        	        nodeTab.innerHTML = '<label for="'+role[0]+"##"+moduleCodes[cnt].value+'"><input type="checkbox" name="'+role[0]+"##"+moduleCodes[cnt].value+'">Tab</label>';
				        	    
			        	    		var nodeSearch = document.createElement('div');        
				        	        nodeSearch.innerHTML = '<label for="'+role[0]+"##"+moduleCodes[cnt].value+"$"+'"><input type="checkbox" name="'+role[0]+"##"+moduleCodes[cnt].value+"##"+moduleCodes[cnt].value+'">Search</label>';
				        	    	cnt++;
				        	        cell.appendChild(nodeTab);
				        	        cell.appendChild(nodeSearch);
				        	    }
			        	    } */
						}
	        		}					
				}
	        }
		});
	});
</script>
</body>
</html>