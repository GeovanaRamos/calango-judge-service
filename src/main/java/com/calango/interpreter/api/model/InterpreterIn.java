package com.calango.interpreter.api.model;

import br.ucb.calango.api.publica.CalangoIn;
import java.util.List;

public class InterpreterIn implements CalangoIn {
    private JudgeCase judgeCase;
    private int i = 0;

    public InterpreterIn(JudgeCase judgeCase){
        this.judgeCase = judgeCase;
    }

    public String read() {
        return judgeCase.getInput();
    }

    public Character readChar() {
        return judgeCase.getInput().charAt(0);
    }

}

