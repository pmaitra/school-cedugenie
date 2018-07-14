/*addrow*/	
var y=1;
function addRow()
{		
	   /* var leaveType = document.getElementsByName("leaveType"); 
	    for(var ii=0;ii<leaveType.length;ii++)
		{
			if(leaveType[ii].value=="select"){
				document.getElementById("mistake").innerHTML="Select Leave Type.";
			}
		}*/
		document.getElementById("warningbox").style.visibility='collapse';
		document.getElementById("warningmsg").innerHTML="";
        var table = document.getElementById("categoryDetail"); 
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount-1);
        
        row.innerHTML="<th>Leave Type :: </th>";
        var cell3 = row.insertCell(1);		
        var element3 = document.createElement("input");
        element3.type = "text";
        element3.name="leaveType";
        element3.id="leaveType"+y; 
        element3.className="textfield1";
        element3.setAttribute("onkeyup","validText(this.id);");
        cell3.appendChild(element3);
        
        var cell1 = row.insertCell(2);
    	var element1 = document.createElement("img");
    	element1.setAttribute("src","/cedugenie/images/minus_icon.png");		
    	element1.setAttribute("onclick", "deleteRow(this);");		
    	cell1.appendChild(element1);
    	
    	var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
        y++;
        
		
}
function deleteRow(obj){
	var table = document.getElementById("categoryDetail");
	var rowCount = table.rows.length;	
	if(rowCount>2){
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
	}
} 