package com.calango.interpreter.api.model;

public class SubmissionResult {
    private int code;
    private String message;

    public SubmissionResult(int code, String message) {
        this.code = code;
        this.message = message;
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
