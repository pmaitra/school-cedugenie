/*var y=1;
function addNewAuthor()
{
	var table = document.getElementById("book");
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount-1);

	var r= "<th>New Author"+y+" :: </th><td><input type='text' name='authorFullName' class='textfield'><img src='/sms/images/minus_icon.png' onclick='deleteBookAuthor(this)'></td>";
    row.innerHTML=r;
    
    y++;
}*/

var index = 1;
function addNewAuthor()  
{
	var div = document.getElementById("authorDiv");
	var newId = "authorFullName"+index;
	var box = document.getElementById("authorFullName"+index).cloneNode(true);
	box.id = newId;
	$("#authorDiv").append(box);
	index++;
}

function deleteBookAuthor(btn)
{
	var table = document.getElementById("book");
	var rowCount = table.rows.length;
	if(rowCount>3){
		var p = btn.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
};

function deleteNoteAuthor(btn)
{
	var table = document.getElementById("note");
	var rowCount = table.rows.length;
	if(rowCount>2){
		var p = btn.parentNode.parentNode;
		p.parentNode.removeChild(p);
	}else{
		document.getElementById("warningbox").style.visibility='visible';
		document.getElementById("warningmsg").innerHTML="Atleast One Row Required";
	}
};

var v=1;
function addNewNoteAuthor()
{		
	var table = document.getElementById("note");
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount-1);
    
	var r= "<th>New Author"+v+" :: </th><td><input type='text' name='noteAuthorFullName' class='textfield'><img src='/sms/images/minus_icon.png' onclick='deleteNoteAuthor(this)'></td>";
    row.innerHTML=r;
	
    v++;
}

function activeForm(){
	document.getElementById("bookName").removeAttribute('readonly');
	document.getElementById("bookEntryDate").removeAttribute('readonly');
	document.getElementById("publishingYear").removeAttribute('readonly');
	document.getElementById("source").removeAttribute('readonly');
	document.getElementById("cost").removeAttribute('readonly');	
	document.getElementById("bookPlace").removeAttribute('readonly');
	document.getElementById("pages").removeAttribute('readonly');
	document.getElementById("billNo").removeAttribute('readonly');
	document.getElementById("classNo").removeAttribute('readonly');
	document.getElementById("withdrawalDate").removeAttribute('readonly');
	document.getElementById("withdrawalNo").removeAttribute('readonly');
	document.getElementById("bookPublisher.bookPublisherName").removeAttribute('readonly');	
	document.getElementById("volume").removeAttribute('readonly');
	document.getElementById("billDate").removeAttribute('readonly');
	document.getElementById("bookNo").removeAttribute('readonly');
	document.getElementById("remarks").removeAttribute('readonly');
	document.getElementById("submitForm").removeAttribute('disabled');
	
	/*var bookType = document.getElementById("bookType").value;	 
	if(bookType == "book"){	 
		var deleteBookAuthor = document.getElementsByName("deleteBookAuthor");			
		for(var i=0;i<deleteBookAuthor.length;i++){			
			deleteBookAuthor[i].removeAttribute("disabled");
		}
		document.getElementById("bookPublisherName").removeAttribute('readonly');
		document.getElementById("addBookAuthor").removeAttribute('disabled');
	}
	if(bookType == "periodicals"){			 
		document.getElementById("bookPlace").removeAttribute('readonly');
		document.getElementById("bookPeriodicity").removeAttribute('readonly');
	}
	if(bookType == "note"){		 
		var deleteNoteAuthor = document.getElementsByName("deleteNoteAuthor");			
		for(var i=0;i<deleteNoteAuthor.length;i++){			
			deleteNoteAuthor[i].removeAttribute("disabled");
		}		
		document.getElementById("addNoteAuthor").removeAttribute('disabled');	
	}*/
	
	document.getElementById("infomsgbox").style.visibility="visible";
	document.getElementById("infomsg").innerHTML="Fields Are Now Editable";
}

function getBookName(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getBookName.html'
	});		
};

function getAuthorName(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getAuthorName.html'
	});		
};

function getEdition(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getEdition.html'
	});		
};

function getPublisherName(thisBookName){	
	$(thisBookName).autocomplete({
		source: '/sms/getPublisherName.html'
	});		
};


function getLoggedData(){
	var bookCode= document.getElementById("bookCode").value;
	 $.ajax({
		    url: '/sms/getUpdateBookLogDetails.html',
		    dataType: 'json',
		    data: "bookCode="+bookCode,
		    success: function(data) {
		    	if(data!=''){
		    		createTable(data);
			    }
		    }
	 });
}



function validateEditForm(){
	var year = $("#publishingYear").val();
	var current_year = new Date().getFullYear();
	if(year>current_year){
		alert("You cannot provide a year greater than the current year!!");
		return false;
	}else{
		return true;
	}	
}