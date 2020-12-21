package com.calango.interpreter.api.service;

import br.ucb.calango.api.publica.CalangoIn;
import com.calango.interpreter.api.model.JudgeCase;

import java.util.List;

public class InterpreterInService implements CalangoIn {
    private JudgeCase judgeCase;

    public InterpreterInService(JudgeCase judgeCase){
        this.judgeCase = judgeCase;
    }

    public String read() {
        return judgeCase.getInput();
    }

    public Character readChar() {
        return judgeCase.getInput().charAt(0);
    }

}

