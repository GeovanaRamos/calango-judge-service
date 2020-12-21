package com.calango.interpreter.api.service;

import br.ucb.calango.api.publica.CalangoOut;

public class InterpreterOutService implements CalangoOut{
    private static final String BARRA_N = "\n";
    private String error;
    private String message;

    public InterpreterOutService(){
    }

    public void print(Object o) {
        message = o.toString();
    }

    public void printErro(Object o) {
        error = BARRA_N+o.toString();
    }

    public void novaLinha() {

    }

    public void limpatela() {

    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}