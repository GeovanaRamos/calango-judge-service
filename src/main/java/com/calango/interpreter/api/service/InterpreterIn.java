package com.calango.interpreter.api.service;

import br.ucb.calango.api.publica.CalangoIn;
import com.calango.interpreter.api.model.JudgeCase;

public class InterpreterIn implements CalangoIn {
    private JudgeCase judgeCase;
    private int inputPosition;

    public InterpreterIn(JudgeCase judgeCase){
        this.judgeCase = judgeCase;
        this.inputPosition = 0;
    }

    public String read() {
        String reading = judgeCase.getInputAt(inputPosition);
        inputPosition++;
        return reading;
    }

    public Character readChar() {
        Character reading = judgeCase.getInputAt(inputPosition).charAt(0);
        inputPosition++;
        return reading;
    }

}

