$(document).ready(function(){
         var box = null;
         $("#activeUsers").hide();
         $("#groupChat").click(function(event, ui) {
        	 if(box) {
        		 box.chatbox("option", "boxManager").toggleBox();
             }
             else { 
            	 startClient();
            	 $("#activeUsers").show();
                 box = $("#chat_div").chatbox({id:"chat_div", 
                                               user:{key : "value"},
                                               title : "Group Chat Box",
                                               messageSent : function(id, user, msg) {
                                               	id=$("#nickname").val();                                               	 
                                                   $("#chat_div").chatbox("option", "boxManager").addMsg(id.trim(), msg);                                                   
                                               }});
             }
         });
     });
 		  var socket;
		  var registered = false;
		  
		  function startClient() {
		    console.log("opening socket");
		    //on http server use document.domain instead od "localhost"
		    //Start the websocket client
		    socket = new WebSocket("ws://" +document.domain + ":8080/cedugenie/chat");
		    
		    //When the connection is opened, login.
		    socket.onopen = function() { 		      
		      //register the user
		      var nickname = $("#nickname").val();
		      console.log("Opened socket For"+nickname.trim());
		      socket.send(nickname.trim());
		    };
		    var arr=new Array();
		    //When received a message, parse it and either add/remove user or post message.
		    socket.onmessage = function(a) {
		      //process the message here
		     // console.log("received message: " + a.data);		     
		      var message = JSON.parse(a.data);
		      var count=0;
		      if (message.addUser) {		          
		        if(arr.length!=0){		    		 
		    		  for(var name=0;name<arr.length;name++){
			    		  if(arr[name]!=message.addUser){
			    			  count++;
			    		  }
		    		  }
		    		  if(count>=arr.length){
			    		  var d = document.createElement('div');
		  		          $(d).addClass("username user").text(message.addUser).attr("data-user", message.addUser).appendTo("#nicknamesBox");
		  		          console.log("Added "+message.addUser);
		  		          arr.push(message.addUser.trim());
		  		          count=0;
		    		  }
		    	  }	else{
		    		  	var d = document.createElement('div');
		    		  	$(d).addClass("username user").text(message.addUser).attr("data-user", message.addUser).appendTo("#nicknamesBox");
				        arr.push((message.addUser).trim());
		    	  } 
		        
		      } else if (message.removeUser) {
		    	  if(arr.length!=0){
		    		  $("#nicknamesBox").empty();
		    		  for(var name=0;name<arr.length;name++){
		    		  if(arr[name]!=message.removeUser){
		    			  	var d = document.createElement('div');
		  		        	$(d).addClass("username user").text(arr[name]).attr("data-user", arr[name]).appendTo("#nicknamesBox");
		  		        	 console.log("Removed All & added "+arr[name]);
		    		  }
		    		  else{
		    			  console.log("Removed"+arr[name]);
		    		  }
		    		  }
		    	  }	
		    	  arr.splice($.inArray(message.removeUser,arr),1);
		      } else if (message.message) {
		        var d = document.createElement('div');
		        var suser = document.createElement('span');
		        var smessage = document.createElement('span');		        
		        $(suser).addClass("username").text(message.nickname+" : ").appendTo($(d));
		        $(suser).attr("style","color:orange");
		        $(smessage).attr("style","color:#333");
		        $(smessage).text(message.message).appendTo($(d));
		        $(d).appendTo("#chatBox");
		        $("#chatBox").scrollTop($("#chatBox")[0].scrollHeight);
		      }      
		    }	
			socket.onclose = function() { socket.close();arr.slice(0, arr.length); };
		    socket.onerror = function() { document.write("<h3>Chat Server Closed...</h3>");arr.slice(0, arr.length);$("form").submit(); };
		    
		    $('#txtMessage').keyup(function(e){
		      if (e.keyCode == 13) {
		        sendMessageInGroupChat();
		      }
		    });		       
		  }
		  function sendMessageInGroupChat() {
		    if ($("#txtMessage").val()) {
		      socket.send($("#txtMessage").val());
		      $("#txtMessage").val("");
		    }
		  }		