package com.lm.chat.config;

import com.lm.chat.model.Mensagem;
import com.lm.chat.model.TipoDaMensagem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations templateDaMensagem;

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");

        if (username != null) {
            log.info("Usuario desconectou: {} ", username);
            var mensagem = Mensagem.builder()
                    .tipo(TipoDaMensagem.SAIU)
                    .remetente(username)
                    .build();
            templateDaMensagem.convertAndSend("/topic/public", mensagem);
        }
    }
}
