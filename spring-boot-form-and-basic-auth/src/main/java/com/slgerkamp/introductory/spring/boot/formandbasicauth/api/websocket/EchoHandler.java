package com.slgerkamp.introductory.spring.boot.formandbasicauth.api.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class EchoHandler extends TextWebSocketHandler {

	// ログ
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// webSocket通信が行われているセッションを保持する。
	private Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		logger.info("Connection Established= {}", session.getId());
		sessionMap.put(session.getId(), session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus status) throws Exception {
		logger.info("Connection Closed= {}", session.getId());
		sessionMap.remove(session.getId());
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session,
			TextMessage message) throws Exception {
		
		logger.info("message(from={}): {}", session.getId(), message.getPayload());
		for (WebSocketSession sess : sessionMap.values()) {
			sess.sendMessage(message);
		}
	}
}

