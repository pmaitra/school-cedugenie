package com.qts.icam.controller.common.chat;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.CloseReason;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;

import com.qts.icam.model.common.ChatMessage;
import com.qts.icam.utility.chat.ChatMessageDecoder;
import com.qts.icam.utility.chat.ChatMessageEncoder;

@Controller
@ServerEndpoint(value = "/chatRoom/{room}", encoders = ChatMessageEncoder.class, decoders = ChatMessageDecoder.class)
public class IndividualChat {
	private final Logger log = Logger.getLogger(getClass().getName());

	@OnOpen
	public void open(final Session session, @PathParam("room") final  String room) {
		String names[]=room.split("~");
		String to=names[0];
		String from=names[1];
		log.info(room);
		session.getUserProperties().put("from", from);
		session.getUserProperties().put("to", to);
		session.getUserProperties().put("room", room);
	}

	@OnMessage
	public void onMessage(final Session session, final ChatMessage chatMessage) {
		String room = (String) session.getUserProperties().get("room");
		String names[]=room.split("~");
		String to=names[0];
		String from=names[1];
		try {
			for (Session s : session.getOpenSessions()) {				
				if (s.isOpen() && to.equals(s.getUserProperties().get("to")) && from.equals(s.getUserProperties().get("from"))) {
					log.info("message: " + chatMessage);
					s.getBasicRemote().sendObject(chatMessage);
				}
				else{
					log.info(" Session Status: " +s.isOpen());
					log.info(s.getUserProperties().get("from")+" Session: " + s.getUserProperties().get("to"));
					log.info(from+" Room: " +to);
					//s.getBasicRemote().sendObject(chatMessage);
					//System.out.println("Session Closed Or User Not Found To Chat");
				}
			}
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
			log.log(Level.WARNING, "onMessage failed", e);
		}
	}
	
	@OnClose
	public void onClose (Session session, CloseReason reason) {
	    try{	    	
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    	log.info(e.toString());
	    }
	  }	
}
