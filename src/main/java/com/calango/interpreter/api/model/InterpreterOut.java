package com.calango.interpreter.api.model;

import br.ucb.calango.api.publica.CalangoOut;

public class InterpreterOut implements CalangoOut{
    private static final String BARRA_N = "\n";
    public String erro;
    public String mensagem;

    public InterpreterOut(){
    }

    public void print(Object o) {
        mensagem = o.toString();
    }

    public void printErro(Object o) {
        erro = BARRA_N+o.toString();
    }

    public void novaLinha() {

    }

    public void limpatela() {

    }

}