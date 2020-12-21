package com.calango.interpreter.api.service;

import br.ucb.calango.api.publica.CalangoIn;
import com.calango.interpreter.api.model.JudgeCase;

import java.util.List;

public class InterpreterIn implements CalangoIn {
    private JudgeCase judgeCase;

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

