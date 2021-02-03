package com.calango.interpreter.api.model;

public class SubmissionResult {
    private int code;
    private String message;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public SubmissionResult() {
        this.errorMessage = "No error message";
    }

    public synchronized int getCode() {
        return code;
    }

    public synchronized void setCode(int code) {
        this.code = code;
    }

    public synchronized String getMessage() {
        return message;
    }

    public synchronized void setMessage(String message) {
        this.message = message;
    }
}
