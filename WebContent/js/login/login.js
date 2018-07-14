/*
 * login.js - This javascript is responsible for validating login related information.
 * @author vinod.sharma and binod.sharma
 * @version 1.0
*/

	$(document).ready(function() {			
	/* script for pop up box */
  	$(".noticeLink").each(function(){		
		$(this).click(function(){
			var noticeCodeDesc=this.id;
			var noticeDesc = noticeCodeDesc.split('~');
			//alert(noticeDesc[1]);
			$("#dialog1").dialog({
				autoOpen: false,
				modal: true,
				resizable: false,
				minWidth:600,
				width:800,
				minHeight:400,
				height:400,			
				dialogClass: "dlg-no-close",
				buttons: {
					"Close": function() {
						$(this).dialog("close");
					}
				}
			});
			noticeDesc[1]=noticeDesc[1].replace(/\n/g, "<br/>");
			document.getElementById("dialog1").innerHTML = noticeDesc[1];
		//	$("#dialog1").html(noticeDesc[1]);
			$("#dialog1").dialog("open");
	});		
	});
});

	

//Validate Login
function val()
{
	userId=document.getElementById("userId").value;
	password=document.getElementById("password").value;
	//User Name Validation
	if(userId=="")
	{
		$('#warningbox').css('visibility','visible');
		document.getElementById("warningmsg").innerHTML="Please Enter User Name";
		return false;
	}

	//Password Validation
	if(password=="")
	{
		$('#warningbox').css('visibility','visible');
		document.getElementById("warningmsg").innerHTML="Please Enter Password";
		return false;
	}
}

 
 

	