/*addrow*/	
var y=1;
function addRow()
{
//		document.getElementById("warningbox").style.visibility='collapse';
//		document.getElementById("warningmsg").innerHTML="";
        var table = document.getElementById("tabdata"); 
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount-1);
        
        var inputDateA="inputDateA";
        var inpdtA= inputDateA + y; 

		var cell2 = row.insertCell(0);		
        var element2 = document.createElement("input");
        element2.setAttribute('readonly','readonly');
        element2.type = "text";
        element2.name="inputDateA";
        element2.className="textfield1";
		cell2.appendChild(element2);

        
        var cell1 = row.insertCell(1);
    	var element1 = document.createElement("img");
    	element1.setAttribute("src","/sms/images/minus_icon.png");		
    	element1.setAttribute("onclick", "deleteRow1(this);");		
    	cell1.appendChild(element1);
        y++;
        
        var serialNo1=document.getElementsByName("inputDateA");
        var id ;
        for(var i=0;i<serialNo1.length;i++)
    	{ id="inputDateA"+(i+1);
        	serialNo1[i].setAttribute("id",id);
    	}
        var calId = "#"+id;
//        $(calId).Zebra_DatePicker();
//		$(calId).Zebra_DatePicker({
//		 	  format: 'd/m/Y'
//		 });
        
		  var innerHeight2=document.body.scrollHeight;
		  var frame=window.parent.document.getElementById("frame");	    	
		  frame.style.height = innerHeight2+25+ 'px';
        y++;
     
}
function deleteRow(obj){
	var table = document.getElementById("tabdata");
	var rowCount = table.rows.length;	
	if(rowCount>2){
//		document.getElementById("warningbox").style.visibility='collapse';
//		document.getElementById("warningmsg").innerHTML="";
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
	}else{
		alert("Atleast One Row Required");
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
	}
} 

function deleteRow1(obj){
	var table = document.getElementById("tabdata");
	var rowCount = table.rows.length;	
	if(rowCount>2){
//		document.getElementById("warningbox").style.visibility='collapse';
//		document.getElementById("warningmsg").innerHTML="";
		var p = obj.parentNode.parentNode;
		p.parentNode.removeChild(p);
		var innerHeight2=document.body.scrollHeight;
	    var frame=window.parent.document.getElementById("frame");	    	
	    frame.style.height = innerHeight2+25+ 'px';
	}else{
		alert("Atleast One Row Required");
		var innerHeight2=document.body.scrollHeight;
    	var frame=window.parent.document.getElementById("frame");	    	
    	frame.style.height = innerHeight2+25+ 'px';
	}
} 
