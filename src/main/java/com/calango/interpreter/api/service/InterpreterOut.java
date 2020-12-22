package com.calango.interpreter.api.service;

import br.ucb.calango.api.publica.CalangoOut;

public class InterpreterOut implements CalangoOut{
    private String error;
    private String message;

    public InterpreterOut(){
        this.error = "";
        this.message = "";
    }

    public void print(Object o) {
        System.out.print(o.toString());
        message = message + o.toString();
    }

    public void printErro(Object o) {
        error = error + o.toString() + "\n";
    }

    public void novaLinha() {
        message = message + "\n";
    }

    public void limpatela() {
    }

    public String getMessage() {
        if (message.isEmpty())
            return null;
        return message;
    }

    public String getError() {
        if (error.isEmpty())
            return null;
        return error;
    }
}