
	
	
	
	var wsocket;
	var serviceLocation = '';
	var port = document.location.port;
	var $nickName;
	var $message;
	var room = '';
	var status=true;
	var  userArray = '';
	 
	function onMessageReceived(evt) {
		
		var msg = JSON.parse(evt.data); // native API	
		//alert("msg=="+msg);
		var d = document.createElement('div');
        var suser = document.createElement('span');
        var smessage = document.createElement('span');	
       // alert("smessage=="+smessage);
        $(suser).addClass("username").text(msg.sender+" : ").appendTo($(d));
        $(suser).attr("style","color:#ff0000");
        $(smessage).attr("style","color:#333");
        $(smessage).text(msg.message).appendTo($(d));
        $(d).appendTo("#chat_div1");
        $("#chat_div1").scrollTop(50);
	}
	
	function sendMessage(id, msg, from) {	
	//	alert("room====="+room);
		var roomArr = room.split("~");
		//alert("from=="+from);
		
		if(from == undefined){
			//alert("un");
			for(var i=0;i<roomArr.length ;i++){
				if(id.toUpperCase() != roomArr[i]){
					from = roomArr[i];
				}
			}
		}
		var msgToServer = '{"message":"' + msg + '", "sender":"'
				+ id + '", "received":"'+from+'"}';
		//alert("msgToServer"+msgToServer);
		wsocket.send(msgToServer);				
		$message.val('').focus();
		
		$.ajax({
	        url: '/icam/storeChatDetails.html',
	        data:{
	        	From:id,
	        	To:from,
	        	msg :msg
	        },
	        success:function(data){
	        	        }
		});
		
		//alert("userArray=="+userArray);
	}	
	 
	function connectToChatserver(IP) {
		//alert("IP!===="+IP);
		serviceLocation = "ws://" + IP + ":" + port + "/icam/chatRoom/";
		//alert(serviceLocation);
		wsocket = new WebSocket(serviceLocation + room);
		//alert("wsocket==="+wsocket);
	//	alert("onMessageReceived===="+onMessageReceived);
		wsocket.onmessage = onMessageReceived;		
	}

	function leaveRoom() {	
		
		user=$("#nickname").val();
		//alert("roomArr======="+roomArr);
		//alert("user=="+user);
		/*wsocket.close(user);*/
		wsocket.close();
		room = '';
		$('.chat-wrapper').hide();
		$('.chat-signin').show();			
		$nickName.focus();
		box = null;
		$.ajax({
	        url: '/icam/markChatRead.html',
	        data:{
	        	user:user
	        },
	        success:function(data){
	       }
		});
	}


	
	function removeOption()	{
		var x=document.getElementById("chatroom");
		x.innerHTML="";
		var option=document.createElement("option");
		option.text="Please Select";
		option.value="";
		try  {
		  // for IE earlier than version 8
		  x.add(option,x.options[0]);
		  }
		catch (e)	  {
		  x.add(option,0);
		  }
	}
	
	$(document).ready(function() {
		$('.minibtn').click(function() {
			if(status){
				//alert("hii");
			$('.chat-wrapper').hide();
			$('.chat-signin').show();
				$('#modalInfo').show();
				//alert("hello");
			$.ajax({
		        url: '/icam/individualChat.html',
		        success: function(data){
		        	if(data != ""){
		        		//alert(data);
			    		removeOption();			    		
		        		var arr = data.split(";");
		        		for (var i=0;i<arr.length-1;i++){
		        		  		$("#chatroom").append($("<option></option>").val(arr[i]).html(arr[i]));
		        			}			        		
		        		}
		        	else{
			    			removeOption();	
			    			$("#pop").fadeOut('-1');
			    	        $("#mask").fadeOut('-1');
			    			alert("No User Present For Chat");
			    			
		    			}
		        }			        
			});
			
	    // Show pop-up and disable background using #mask
	    $("#pop").fadeIn('slow');
	    $("#mask").fadeIn('slow');
		}else{
				alert("You allowed only one Personal chat at a time");
			}
	    });
	      
		// Hide pop-up and mask
	    $("#mask").hide();
	    $("#pop").hide();
	    
	    // Size pop-up
	    var img_w = 500;
	    var img_h = 350;

	    // Get values ​​from the browser window
	    var w = $(this).width();
	    var h = $(this).height();

	    // Centers the popup  
	    w = (w / 2) - (img_w / 2)+90;
	    h = (h / 2) - (img_h / 2);
	    $("#pop").css("left", w + "px");
	    $("#pop").css("top", h + "px");

	    // Function to close the pop-up
	    $("#close").click(function() {
	        $("#pop").fadeOut('slow');
	        $("#mask").fadeOut('slow');
	    });
	   
		var box = null;
		$nickName = $('#nickname');
		$message = $('#message');		
		$nickName.focus();
		$(".chat-wrapper").hide();
		$('#enterRoom').click(function(evt) {
			//alert("hello");
			if($('#chatroom option:selected').val()!=""){
			userArray = $("#nickname").val() +"_"+ $('#chatroom option:selected').val();
					
				
			$("#pop").fadeOut('slow');
	        $("#mask").fadeOut('slow'); 
	    	//alert("hello11");
			evt.preventDefault();
			//alert("hello12");
			//room = $('#chatroom option:selected').val();
			$.ajax({
		        url: '/icam/notifyChat.html',
		        data:{
		        	From:$("#nickname").val(),
		        	To:$('#chatroom option:selected').val()			        	
		        },
		        success:function(data){
		        	//alert("data=="+data);
		        	var arr = data.split(";");
					room = arr[0];
					//alert("arr[0]=="+arr[0]);
					var IP = arr[1];
					connectToChatserver(IP);
					//alert("room==="+room);
					//alert("IP==="+IP);
		        }
			});
			
			//alert("BOX=="+box);
		
			if(box)	{	
				//alert("within box");
				box.individualChatbox("option", "boxManager").toggleBox();
				leaveRoom();
				$("#chat_div1").html('');
			}
			else{
				status=false;
				//alert("status==="+status);
				box = $("#chat_div1").individualChatbox({
					offset: 0,
					id:"chat_div1", 
                    user:{key : "value"},
					title : "Personal Chat ",						
					messageSent : function(id, user, msg){						
						id=$("#nickname").val(); 
						//alert("id==="+id);
						from = $('#chatroom option:selected').val();		
						$("#chat_div1").individualChatbox("option", "boxManager").addMsg(id, msg);
						//alert("sendMessage")
						
						sendMessage(id, msg, from) ;
						
                    },
                    boxClosed: function(id) {
                    	leaveRoom();
                    	$("#chat_div1").html('');
                    }
				});
				
				
			}
		
			$('.chat-signin').hide();
			$('.chat-wrapper').show();
			$message.focus();
			}else{
				alert("Please Select");
			}
		});			
		
		 $('#message').keypress(function(e){
			// alert("Hello");
		      if (e.keyCode == 13) {
		    	//  alert("Hiii");
		    	  e.preventDefault();
		    	  var id= $("#nickname").val();
		    	  var msg= $('#message').val();
		    	  sendMessage(id, msg);
		      }
		    });	
		
	});	
	
