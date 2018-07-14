function validRadio(rd)
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
	alert("Please select an option");
	 return false;
 }
 else
	 return true;
}