var pre, post;
var xmlDoc; /*= new ActiveXObject("Microsoft.XMLDOM"); */
  var xmlObj;
  var root;
 // var nTotalQuestions = 0;
  //var nTotalQuestions = document.getElementById("nTotalQuestions").value;
  //alert("nTotalQuestions=="+nTotalQuestions);
  $(document).ready(function() {
	  if (window.DOMParser)
	  { // Firefox, Chrome, Opera, etc.
	      parser=new DOMParser();
	      xmlDoc=parser.parseFromString(xml,"text/xml");
	  }
	  else // Internet Explorer
	  {
	      xmlDoc=new ActiveXObject("Microsoft.XMLDOM");
	      xmlDoc.async=false;
	      xmlDoc.loadXML(xml); 
	  }
  });
 

  function loadXML(xmlFile) 
  { 
	xmlDoc.async="false"; 
	xmlDoc.load(xmlFile); 
	xmlObj=xmlDoc.documentElement; 
	root = xmlObj.getElementsByTagName('questions')[0];
  }
  
  function onload()
  {
	loadXML("S_1.xml");
	
	var items = root.getElementsByTagName("question");
	for (var i = 0 ; i < items.length ; i++) {
		var item = items[i];
		var questiondesc = item.getElementsByTagName("qid")[0].text;
		var parentquestion = item.getElementsByTagName("parentquestion")[0].text;
		if ( parentquestion == '' )
			showdivDisplay("DIV" + questiondesc);
	}  
	
  }
    
  function BrowserType()
  {
	 
     if (document.layers){
        //Netscape 4 specific code
    	
        browser = 'NN4'
        pre = 'document.' ;
        post = '';
     }
     if (document.getElementById){
        //Netscape 6 specific code
    	
        browser = 'NN6'
        pre = 'document.getElementById("';
        post = '")';
     }
     if (document.all){
        //IE4+ specific code
    	
        browser = 'IE4+'
        pre = 'document.all.';
        post = '';
     }
  }

 function getElementValue(formElement, name)
 {
    if(formElement.length != null) var type = formElement.type;
    if((typeof(type) == 'undefined') || (type == 0)) var type = formElement.type;
    switch(type)
    {
      case 'undefined': return;
    	 case 'radio':
    	   /*
    	   var formElements = document.getElementsByName(name);
    		 for(var x=0; x < formElements.length; x++)
    		   if (formElements[x].checked == true)
    			   return formElements[x].value;
			*/
			
			 var myArray1 = new Array();
			 
	    	 var formElements = document.getElementsByName(name);
    		 for(var x=0; x < formElements.length; x++)
    		 {
    		   if (formElements[x].checked == true)
    				myArray1[myArray1.length] = formElement[x].value
    		 }
    	     return myArray1;	
    	     	
    	 case 'select-multiple':
    		 var myArray = new Array();
    		 for(var x=0; x < formElement.length; x++)
    		   if(formElement[x].selected == true)
    			   myArray[myArray.length] = formElement[x].value;
    			 return myArray;
    	 case 'checkbox':
    		 var myArray = new Array();
	    	 var formElements = document.getElementsByName(name);
    		 for(var x=0; x < formElements.length; x++)
    		 {
    		   if (formElements[x].checked == true)
    				myArray[myArray.length] = formElement[x].value
    		 }
    	     return myArray;
    	 default: return formElement.value;
    }
  }

  function FindItem(name)
  {
	
    BrowserType();
//    alert(name);
    if (browser == 'IE4+')
      return getElementValue(document.forms[0].item(name), name);
  	 else
    {
      for (i = 0; i < document.forms[0].length; i++) {
        if (document.forms[0].elements[i].name == name)
        {
          return getElementValue(document.forms[0].elements[i], name);
        }
  	   }
   }
  }    


  function showdivDisplay(item)
  {
     var mydiv = document.getElementById(item);
     mydiv.style.visibility="";
     mydiv.style.display="";
  }
  
  function hidedivDisplay(item)
  {
     var mydiv = document.getElementById(item);
     mydiv.style.visibility="";
     mydiv.style.display="none";
  }

  function Control_Click(fieldname)
  {
	var currentfield = fieldname.name;
	var currentquestion = currentfield.substring(7,currentfield.length);
	var querypath = "question[@qid='" + currentquestion + "']";
	var item ;
	var qid;
	var parentquestion ;
	
	var items = root.getElementsByTagName(querypath);
	if ( items != null && items.length == 1)
	{
		//alert("question found");
		item = items[0];
		querypath = "question[@parentquestion='" + currentquestion + "' and @valuecondition='" + FindItem(fieldname.name) +  "']";
		var items = root.getElementsByTagName(querypath);
		//alert(querypath);
		if ( items != null && items.length >= 1 )
		{
			//alert("next question found");

			var nextquestion = currentquestion * 1;
			nextquestion++;
			var strdiv ;
			for(var i=nextquestion; i<= nTotalQuestions; i++)
			{
				strdiv = "DIV" + i;
				hidedivDisplay(strdiv);
			}
			for(var j=0;j< items.length;j++)
			{
				item = items[j];
				qid = item.getElementsByTagName("@qid")[0].text;
				showdivDisplay("DIV" + qid);
			}
		}
		else
		{
			//alert("next question not found");
			/*
			var nextquestion = currentquestion * 1;
			nextquestion++;
			var strdiv ;
			
			for(var i=nextquestion; i<= nTotalQuestions; i++)
			{
				strdiv = "DIV" + i;
				hidedivDisplay(strdiv);
			}*/
		}
	}


  }

  function submit_click()
  {

	var questionAnswer = document.getElementById("SURVEYID").value;;
	for(i=1; i< nTotalQuestions+1; i++)
	{
		//alert("i===="+i);
		var id = document.getElementById("CONTROLHIDDEN"+i).value;

		var type = document.getElementById("CONTROLTYPE"+i).value;
		
		
		
		if(type==1){
			var textAnswerValue = document.getElementById("CONTROL"+id).value;
			if(textAnswerValue == "" ||textAnswerValue == null){
				var div = document.getElementById('ERROR1');
				div.innerHTML = "Please Enter Some Value.";
				div.style.color = "Red";
				div.style.visibility='visible';
				return false;
			}else{
				var div = document.getElementById('ERROR1');
				div.style.visibility='hidden';
				questionAnswer = questionAnswer + ";"+id+"*"+textAnswerValue;
			}
			
		}else if(type==3){
			var textareaAnswerValue = document.getElementById("CONTROL"+id).value;
			
			if(textareaAnswerValue == "" ||textareaAnswerValue == null){
				var div = document.getElementById('ERROR1');
				div.innerHTML = "Please Enter  Value.";
				div.style.color = "Red";
				div.style.visibility='visible';
				return false;
			}else{
				var div = document.getElementById('ERROR1');
				div.style.visibility='hidden';
				questionAnswer = questionAnswer + ";"+id+"*"+textareaAnswerValue;
			}
			
			 
		}else if(type == 4){
			var answers = document.getElementsByName("CONTROL"+id);
			var informer = null;
			var bln_knowus = false;
			for(var k = 0; k < answers.length; k++){
				if(answers[k].checked){
					bln_knowus = true;
					break;
				}
			}
			if(bln_knowus == false){
				var div = document.getElementById('ERROR1');
				div.innerHTML = "Please select at least one.";
				div.style.color = "Red";
				div.style.visibility='visible';
				return false;
			}else{
				var div = document.getElementById('ERROR1');
				div.style.visibility='hidden';
				for(var j = 0; j < answers.length; j++){
					if(answers[j].checked){
						informer = informer+"@"+document.getElementsByName("CONTROL"+id)[j].value;
						//i = i++;
					}
					
				}
			
				questionAnswer = questionAnswer + ";"+id+"*"+informer;
				
			}
			
		}else if(type == 6){
			var radioAnswer = document.getElementsByName("CONTROL"+id);
		
			
			var bln_Rating = false;
			for(var k = 0; k < radioAnswer.length; k++){
				if(radioAnswer[k].checked){
					var ratingValue = document.getElementsByName("CONTROL"+id)[k].value;
				
					bln_Rating = true;
					
					break;
					
				}
			}
			if(bln_Rating == false){
				var div = document.getElementById('ERROR1');
				div.innerHTML = "Please  Select One.";
				div.style.color = "Red";
				div.style.visibility='visible';
				return false;
			}else{
				var div = document.getElementById('ERROR1');
				div.style.visibility='hidden';
				questionAnswer = questionAnswer + ";"+id+"*"+ratingValue;
			}
			
		}
		
	}
	
	var questionsANDAnswers = questionAnswer;
	
	window.location="submitSurvey.html?questionsANDAnswers="+questionsANDAnswers;
  }
  
  function submit_xml_click()
  {
	var strtemp = '', str = '', strresult = '' , x , stranswers ='';

	for(i=1; i< nTotalQuestions+1; i++)
	{
		
		str = FindItem('CONTROLQ' + i);
		strtemp = '\t<qid>' + i + '</qid>' + '\r\n' + '\t<answers>\r\n\t\t\t<answer>' + str;
		strresult = strresult + strtemp;
		if ( str == "Other" )
		{	
			str = FindItem('CONTROLQ' + i + 'other');
			strtemp = '<other>' + ' Other - ' + str +  '</other>';
			strresult = strresult + str;
		}
		strresult += '</answer>\r\n\t</answers>' + '\r\n' ;
	}
	
  }
