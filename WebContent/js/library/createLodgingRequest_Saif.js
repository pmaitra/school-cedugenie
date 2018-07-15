window.onload = function(){
	//var checkbox = getElementsByClassName("listShowHide");
	for(var i=0;i<checkbox.length;i++){
		ShowHideField(checkbox[i].value, 'clr', checkbox[i]);
	}
};


	function onSearchLodgingRequest()
	{
		if(validateSearch('query','data','warningbox','warningmsg')){
			document.createLodgingRequest.action = "searchLodgingRequest.html";
	   		document.createLodgingRequest.submit();             // Submit the page
	        return true;
		}
		else{
			return false;
		}
     }
	function onSubmitLodgingRequest(){	
		document.createLodgingRequest.method="POST";
		document.createLodgingRequest.action = "submitLodgingRequest.html";
		document.createLodgingRequest.submit();             // back from the page
		return true;
	}
	
	function go(box){
		/*document.getElementById("errormsg").innerHTML = "";
		document.getElementById("errorbox").style.display = 'none';
		document.getElementById("infomsgbox").style.display = 'none';
		document.getElementById("warningbox").style.display = 'none';*/
			if(box.checked){
				if(checkMaxBooksRequest()){
					var x=document.getElementById("names");
					var newLI = document.createElement("li");
					newLI.setAttribute("id", box.value);
					newLI.setAttribute("title", "Click To Dis-select");
					newLI.setAttribute("onclick", "come(this.id);");
					var newText = document.createTextNode(box.value);
					newLI.appendChild(newText);
					x.appendChild(newLI);
				}else{
					box.checked=false;
				}
			}else{
					var x=document.getElementById("names");
					li = document.getElementById(box.value),
					x.removeChild(li);
			}
		
	}
	
	function checkMaxBooksRequest(){
		var countCheckd=0;
		$(".checkClass").each(function(){
			//$("#bookRequestBookCode").prop("readonly", false);
			if ($(this).is(':checked')){			
				countCheckd=countCheckd+1;
			}
    	});
		if(countCheckd  > parseInt(document.getElementById("maxNoOfBooksPerRequest").value)){
			/*document.getElementById("errorbox").style.visibility='visible';
			document.getElementById("errormsg").innerHTML = "Not more than "+document.getElementById("maxNoOfBooksPerRequest").value+" books are allowed per request.";
			*/return false;
		}else{
			return true;
		}
	}
	
	function come(d)
	{
		var x=document.getElementById("names");
		li = document.getElementById(d),
		x.removeChild(li);
		
		var inputElems = document.getElementsByTagName("input");
		
		for (var i=0; i<inputElems.length; i++)
		{
			if (inputElems[i].type === "checkbox" && inputElems[i].value === d)
			{
			inputElems[i].checked = false;
			}
		}
	}
	
	var retVal = null;
	$(document).ready(function(){
		$("#submitForm").click(function(){
			var userTextVal = $("#userId").val();
			var role= document.getElementById("role").value;
			if(role== 'SUPER ADMIN')
			{
				var newUserTextVal=$("#bookRequestedFor").val();
				if(newUserTextVal == "")
					{
						alert("Please Provide Id for Book Requested For!!!")
					}
				if(newUserTextVal != "")
				{
					userTextVal= newUserTextVal;
					document.getElementById("newAppliedBy").value=userTextVal;
				}
			}
			if(role == 'LIBRARY ADMINISTRATOR')
			{	
				var newUserTextVal=$("#bookRequestedFor").val();
				if(newUserTextVal == "")
					{
						document.getElementById("newAppliedBy").value=userTextVal;
					}
				if(newUserTextVal != "")
					{
						userTextVal= newUserTextVal;
						document.getElementById("newAppliedBy").value=userTextVal;
					}
			}
			var countCheckd=0;
			$(".checkClass").each(function(){	
				if ($(this).is(':checked')){			
					countCheckd=1;
				}
	    	});
			if(countCheckd  == 0){
//				document.getElementById("warningbox").style.visibility='visible';
//				document.getElementById("warningmsg").innerHTML = "Please check One Book";
				alert("Please check one book.");
				return false;
			}
			/*if(userTextVal == ""){
				$("#warningbox").css('visibility','visible');
				$("#warningmsg").text('Enter the User Id.');
				return false;
				retVal = false;
			}*/
			
			if(userTextVal != ""){
				onSubmitLodgingRequest();
			}
			return retVal;
		});
	});
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	$(document).ready(function() {	
		 $("#bookRequestedFor").autocomplete({
		 	source: '/cedugenie/getUserIdList.html',
		 	 select: function (event, ui) {
		 		 getmaxbook(ui.item.value);
		 		removedisabled(ui.item.value);
                 }
		 });
	});
	


		
	function getmaxbook(bookObj) {
					$.ajax({
				    	url: '/cedugenie/getMaximunNumberOfBook.html',
				        dataType: 'json',
				        data: "bookRequestedFor=" + bookObj,
				        success: function(dataDB) {
				        	document.getElementById("maxNoOfBooksPerRequest").value= dataDB;
				        }
			}); 
	}
		
	function removedisabled(bookObj)
	{
		var checkboxes = document.getElementsByName("bookRequestBookCode");
		var bookRequestedFor = $("#bookRequestedFor").val();
		if(bookRequestedFor != ""){
			for (var i = 0; i < checkboxes.length; i++) {
					checkboxes[i].disabled = false;
				}
			}
		}
	