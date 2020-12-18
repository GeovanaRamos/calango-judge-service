package com.calango.interpreter.api.model;

public class SubmissionResult {
    public static final int ACCEPTED = 0;
    public static final int WRONG_ANSWER = 1;
    public static final int PRESENTATION_ERROR = 2;
    public static final int COMPILATION_ERROR = 3;
    public static final int RUNTIME_ERROR = 4;
    public static final int TIME_LIMIT_EXCEEDED = 5;

    public static final String ACCEPTED_MESSAGE = "Accepted";
    public static final String WRONG_ANSWER_MESSAGE = "Wrong Answer";
    public static final String PRESENTATION_ERROR_MESSAGE = "Presentation Error";
    public static final String COMPILATION_ERROR_MESSAGE = "Compilation Error";
    public static final String RUNTIME_ERROR_MESSAGE = "Runtime Error";
    public static final String TIME_LIMIT_EXCEEDED_MESSAGE = "Time Limit Exceeded";

    private int code;
    private String message;

    public SubmissionResult() {
        this.code = ACCEPTED;
        this.message = ACCEPTED_MESSAGE;
    }

    public void parseError(String error) {
        // either COMPILATION or RUNTIME error
        if (error.contains("Não foi encontrado o símbolo esperado") ||
                error.contains("Foi encontrado o símbolo") ||
                error.contains("não pode ser utilizado aqui") ||
                error.contains("Early Exit") ||
                error.contains("Comando fora do conerror") ||
                error.contains("Token inesperado") ||
                error.contains("já foi definido") ||
                error.contains("não existe") ||
                error.contains("não é uma função ou procedimento")
        ) {
            this.code = COMPILATION_ERROR;
            this.message = COMPILATION_ERROR_MESSAGE;
        } else {
            this.code = RUNTIME_ERROR;
            this.message = RUNTIME_ERROR_MESSAGE;
        }
    }

    public void parseMessage(String message, String expectedOutput) {
        if (expectedOutput.equalsIgnoreCase(message.trim()) ||
                expectedOutput.trim().equalsIgnoreCase(message)) {
            this.code = PRESENTATION_ERROR;
            this.message = PRESENTATION_ERROR_MESSAGE;
        } else {
            this.code = WRONG_ANSWER;
            this.message = WRONG_ANSWER_MESSAGE;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
