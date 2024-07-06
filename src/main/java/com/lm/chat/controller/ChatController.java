package com.lm.chat.controller;

import com.lm.chat.model.Mensagem;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.enviarMensagem")
    @SendTo("/topic/public")
    public Mensagem enviarMensagem(@Payload Mensagem mensagem) {
        return mensagem;
    }

    @MessageMapping("/chat.addUsuario")
    @SendTo("/topic/public")
    public Mensagem addUsuario(@Payload Mensagem mensagem, SimpMessageHeaderAccessor headerAccessor) {
        //Adiciona username na sessão websocket
        headerAccessor.getSessionAttributes().put("username", mensagem.getRemetente());
        return mensagem;
    }
}
