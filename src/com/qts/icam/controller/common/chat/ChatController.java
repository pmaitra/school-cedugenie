package com.qts.icam.controller.common.chat;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;


@ServerEndpoint("/chat")
public class ChatController {
  private final Logger log = Logger.getLogger(getClass().getName());
  private static Set<Session> conns = java.util.Collections.synchronizedSet(new HashSet<Session>());
  private static Map<Session, String> nickNames = new ConcurrentHashMap<Session, String>();
  
  private Session currentSession;
  public ChatController() {
    
  }
  
  @OnOpen
  public void onOpen (Session session) {
   //  System.out.println ("WebSocket opened: "+session.getId());
     conns.add(session);
     this.currentSession = session;
  }

  @OnMessage
  public void onMessage (String message) {
//    System.out.println("Received: " + message);
    if (!nickNames.containsKey(currentSession)) {
      //No nickname has been assigned by now
      //the first message is the nickname
      //escape the " character first
      message = message.replace("\"", "\\\"");
      
      //broadcast all the nicknames to him
      for (String nick : nickNames.values()) {
        try {
          currentSession.getBasicRemote().sendText("{\"addUser\":\"" + nick + "\"}");
        } catch (IOException e) {
          log.error(e);
        }
      }
      
      //Register the nickname with the 
      nickNames.put(currentSession, message);
      
      //broadcast him to everyone now
      String messageToSend = "{\"addUser\":\"" + message + "\"}";
      for (Session sock : conns) {
        try {
          sock.getBasicRemote().sendText(messageToSend);
        } catch (IOException e) {
          log.error(e);
        }
      }
    } else {
      //Broadcast the message
      String messageToSend = "{\"nickname\":\"" + nickNames.get(currentSession)
          + "\", \"message\":\"" + message.replace("\"", "\\\"") +"\"}";
      for (Session sock : conns) {
        try {
          sock.getBasicRemote().sendText(messageToSend);
        } catch (IOException e) {
          log.error(e);
        }
      }
    }
  } 
  
  @OnClose
  public void onClose (Session session, CloseReason reason) {
    try{
    	String nick = nickNames.get(session);	        
	    conns.remove(session);
	    nickNames.remove(session);
	    if (nick!= null) {
	        removeUser(nick);
	      } 
	    //System.out.println ("Closing a WebSocket due to "+reason.getReasonPhrase());
    }catch(Exception e){
    	
    	log.error(e);
    }
  }
  
  @OnError
  public void onError (Session session, Throwable throwable) {
    try{
		String nick = nickNames.get(session);
	    conns.remove(session);
	    nickNames.remove(session);
	    if (nick!= null) {
	      removeUser(nick);
	    }
    }catch(Exception e){
    	log.error(e);
    }
  }
  
  public void removeUser(String username) {
    String messageToSend = "{\"removeUser\":\"" + username + "\"}";    
    for (Session sock : conns) {
      try {
        sock.getBasicRemote().sendText(messageToSend);
      } catch (IOException e) {    	  
        log.error(e);
      }
    }
  }
}
