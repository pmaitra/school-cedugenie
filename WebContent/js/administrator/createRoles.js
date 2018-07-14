$(document).ready(function() {
	var hidden = document.getElementById("moduleCodeHidden").value;
	var existingRoles=document.getElementById("roleTableBody");
	var tableID=document.getElementById("roleTableBody");
	var jsonData=document.getElementById("jsonData");
	//var msg = document.getElementById("successboxmsgbox").value;
/*	if(hidden != "" || hidden != 'null'){
		
		$.ajax({
	        url: '/icam/getRolesForModule.html',
	        dataType: 'json',
	        data: "Module=" + ($("#moduleCodeHidden").val()),
	        success: function(data){
	        	if(data != ''){
		    		existingRoles.removeAttribute("style");
	        		existingRoles.setAttribute("style","visibility: visible;");	        		
	        		jsonData.value=data;
	        		view(tableID);	
	        		//document.getElementById("warningbox").style.visibility='collapse';
	        		
	        	}else{
	        		existingRoles.setAttribute("style","display:none;");
	        		deleteRows(tableID);
	        		//document.getElementById("warningbox").style.visibility='visible';	        		
	        		//document.getElementById("warningmsg").innerHTML = "No Role Created For This Module";
	        	}
	        }			        
		}); 
	}*/
	
	
	
	
	
	
	
	$("#moduleName").change(function() {		
		var existingRoles=document.getElementById("roleTableBody");
		var tableID=document.getElementById("roleTableBody");
		var jsonData=document.getElementById("jsonData");
		/*if($("#moduleName").val()== 'null' || $("#moduleName").val()=='' || $("#moduleName").val()==""){
			return;
		}*/
		$.ajax({
	        url: '/icam/getRolesForModule.html',
	        dataType: 'json',
	        data: "Module=" + ($("#moduleName").val()),
	        success: function(data){
	        	if(data != ''){
		    		existingRoles.removeAttribute("style");
	        		existingRoles.setAttribute("style","visibility: visible;");	        		
	        		jsonData.value=data;
	        		view(tableID);	
	        		//document.getElementById("warningbox").style.visibility='collapse';
	        		
	        	}else{
	        		existingRoles.setAttribute("style","display:none;");
	        		deleteRows(tableID);
	        		//document.getElementById("warningbox").style.visibility='visible';	        		
	        		//document.getElementById("warningmsg").innerHTML = "No Role Created For This Module";
	        	}
	        }			        
		}); 		
	});
});



var variable;
var length;
var checker;

var roleNameList=new Array();

var p = 1;
function view(tableID){
	
	var data=document.getElementById("jsonData").value;	
	//alert("data==="+data);
	var numberOfRows=data.split("#");	
	//alert("numberOfRows==="+numberOfRows);
	var table = document.getElementById("roleTableBody");		
	deleteRows(table);	
	for(var i=0;i<numberOfRows.length-1;i++){		
		var sm=numberOfRows[i].split("*");
		var roleName=sm[0];
		var roleCode=sm[1];
		var roleDescription=sm[2];
		//alert(roleCode);
		var rowCount = table.rows.length;
		var row = table.insertRow(rowCount);
		
	//	var cell0 = row.insertCell(0);
		//var element0 = document.createElement("input");
	//	element0.type = "checkbox";
	//	element0.value = roleCode;
		//element0.name = "roleNameChk";
		//cell0.appendChild(element0);
		
		
		var cell0 = row.insertCell(0);
		cell0.innerHTML=roleName;		
		roleNameList.push(roleName);
		//element.name = "roleName";
	  //  element.id = "roleNameName"+p;
		
		var cell1 = row.insertCell(1);
		var element3 = document.createElement("textarea");
		element3.rows=1;
		element3.value=roleDescription;
		element3.name="description";
		element3.className = "form-control";
		element3.setAttribute("style","resize:none");
		element3.setAttribute("readonly","readonly");
		cell1.appendChild(element3);
		
		
		var cell2 = row.insertCell(2);
//	    var element = document.createElement("<a href='#' class = 'on-default' name='deleteButton' onclick='return deleteRow(this);'>" +
//	    										"<i class='fa fa-minus-square'></i></a>");
	    var element4 = document.createElement('a');
	    element4.value = roleCode;
	   // alert(roleCode+"value===="+element4.value);
	    //element4.setAttribute("onclick","inactiveRow(this);");
	    element4.setAttribute("class","fa fa-trash-o");
	    element4.setAttribute("href","inActiveRole.html?roleNameChk="+roleCode);
	    cell2.appendChild(element4);	

	}
		
	document.getElementById("roleDiv").style.display = "block";
}	
		

function inactiveRow(obj){
	//alert("obj=="+obj);
	var p = obj.parentNode.parentNode;
	//alert("P:"+p);
	var roleCode = obj.getAttribute("value");
	//alert("roleCode=="+roleCode);
	//var p = obj.parentNode.parentNode;
	/*$.ajax({
        url: '/icam/inActiveRole.html',
        dataType: 'json',
        data: "itSection=" + itSectionCode,
        success: function(dataDB) {
        	p.parentNode.removeChild(p);
        	}
		});*/
	}

function deleteRows(tableID){	
	var rowCount = tableID.rows.length-1;		
	//alert("rowCount===="+rowCount);
	for(var i=rowCount; i>=0; i--){	
		tableID.deleteRow(i);
//		var innerHeight2=document.body.scrollHeight;
//    	var frame=window.parent.document.getElementById("frame");	    	
//    	frame.style.height = innerHeight2+50+ 'px';
	}
	roleNameList.splice(0, roleNameList.length);	
}

function validateRole(){
	var moduleName=document.getElementById("moduleName").value.trim();
	var roleName=document.getElementById("roleName").value;
	if(moduleName=="" || moduleName=='null'){	
		alert("Please Select a Module");
		return false;
	}
	alert("roleName==="+roleName);
	if(roleName=="" || roleName=='null'){	
		alert("Please Enter Role Name");
		return false;
	}
	
	if(roleNameList.length>0){
		for(var count=0;count<roleNameList.length;count++){
			if((roleNameList[count].toUpperCase)==(roleName.toUpperCase)){
				alert("Role Name Already Exists");
				return false;
			}
		}
	}
	
	return true;
}


function deleteRole(){
	var module= document.getElementById("moduleName").value;
	var chkBox= document.getElementsByName("roleNameChk");
	if(module=="" || module=='null'){		
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select a Module";		
		return false;
	}
	var t=0;
	for(var i=0;i<chkBox.length;i++){
		if(chkBox[i].checked){
			 t=1;
			 break;
		}
	}
	if(t==0){
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Please Select a Role To Delete";		
		return false;
	}
	else{
		document.createRoles.method="post";
		document.createRoles.action="inActiveRole.html";
	}
}