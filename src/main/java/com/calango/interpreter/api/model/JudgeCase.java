package com.calango.interpreter.api.model;

public class JudgeCase {
    private String[] input;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    private String output;

    public void setInput(String[] input) {
        this.input = input;
    }

    public String[] getInput() {
        return input;
    }

    public String getInputAt(int i){
        return input[i];
    }
}
