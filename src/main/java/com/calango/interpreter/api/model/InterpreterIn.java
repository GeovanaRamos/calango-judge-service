package com.calango.interpreter.api.model;

import br.ucb.calango.api.publica.CalangoIn;

public class InterpreterIn implements CalangoIn {

    public InterpreterIn(){
    }

    public String read() {
        return "teste";
    }

    public Character readChar() {
        return 'a';
    }

}

