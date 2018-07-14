function valradio(rd,erb,er)
{
var x=document.getElementsByName(rd);
var a=0;

for(var i=0;i<x.length;i++)
{
if(x[i].checked)
{
	a=1;
}
}
 if(a==0)
 {
	 document.getElementById(erb).style.visibility='visible';
	 document.getElementById(er).innerHTML="Please Select An Option";
	 return false;
 }
 else
	 return true;
}


function validateradio(roleCode){
	alert("roleCode==="+roleCode);
	var x=document.getElementsByName("roleName");
	var a=0;

	for(var i=0;i<x.length;i++)
	{
	if(x[i].checked)
	{
		a=1;
	}
	}
	 if(a==0)
	 {
		 document.getElementById("validateMsg").style.display='block';
		 //document.getElementById(er).innerHTML="Please Select An Option";
		 return false;
	 }
	 else
		 return true;
}