package com.lm.chat.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mensagem {

    private String conteudo;
    private String remetente;
    private TipoDaMensagem tipo;
}
