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
        if (inputPosition == judgeCase.getInput().length)
            return null;
        String reading = judgeCase.getInputAt(inputPosition);
        inputPosition++;
        return reading;
    }

    public Character readChar() {
        if (inputPosition == judgeCase.getInput().length)
            return null;
        Character reading = judgeCase.getInputAt(inputPosition).charAt(0);
        inputPosition++;
        return reading;
    }

}

