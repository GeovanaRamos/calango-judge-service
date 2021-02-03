package com.calango.interpreter.api.service;

import br.ucb.calango.api.publica.CalangoAPI;
import com.calango.interpreter.api.model.JudgeCase;
import com.calango.interpreter.api.model.Submission;
import com.calango.interpreter.api.model.SubmissionResult;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

@Log
@Component
public class SubmissionService {
    public static final int ACCEPTED = 1;
    public static final int WRONG_ANSWER = 2;
    public static final int PRESENTATION_ERROR = 3;
    public static final int COMPILATION_ERROR = 4;
    public static final int RUNTIME_ERROR = 5;
    public static final int TIME_LIMIT_EXCEEDED = 6;

    public static final String ACCEPTED_MESSAGE = "ACCEPTED";
    public static final String WRONG_ANSWER_MESSAGE = "WRONG_ANSWER";
    public static final String PRESENTATION_ERROR_MESSAGE = "PRESENTATION_ERROR";
    public static final String COMPILATION_ERROR_MESSAGE = "COMPILATION_ERROR";
    public static final String RUNTIME_ERROR_MESSAGE = "RUNTIME_ERROR";
    public static final String TIME_LIMIT_EXCEEDED_MESSAGE = "TIME_LIMIT_EXCEEDED";

    public SubmissionResult judgeSubmission(Submission submission) {

        SubmissionResult submissionResult = new SubmissionResult();
        Thread thread;
        int i = 0;

        for (JudgeCase judgeCase : submission.getCases()) {
            if (submissionResult.getCode() != 0 && submissionResult.getCode() != ACCEPTED)
                break;
            thread = new Thread(() -> {
                callInterpreter(submissionResult, judgeCase, submission);
            });
            log.info("Init case: " + i);
            thread.start();
            try {
                thread.join(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (thread.isAlive()) {
                thread.stop();
                submissionResult.setCode(TIME_LIMIT_EXCEEDED);
                submissionResult.setMessage(TIME_LIMIT_EXCEEDED_MESSAGE);
                log.info("Interrupted thread " + i);
            }
            if (submissionResult.getCode() != ACCEPTED)
                break;
            i++;
        }

        return submissionResult;
    }

    private void callInterpreter(SubmissionResult submissionResult, JudgeCase judgeCase,
                                 Submission submission) {

        InterpreterIn in = new InterpreterIn(judgeCase);
        InterpreterOut out = new InterpreterOut();

        synchronized (this) {
            CalangoAPI.setIn(in);
            CalangoAPI.setOut(out);
            CalangoAPI.interpretar(submission.getCode());
        }

        if (out.getError() != null) {
            parseError(out.getError(), submissionResult);
        } else if (out.getMessage() != null) {
            parseMessage(out.getMessage(), judgeCase.getOutput(), submissionResult);
        } else {
            submissionResult.setCode(RUNTIME_ERROR);
            submissionResult.setMessage(RUNTIME_ERROR_MESSAGE);
            submissionResult.setErrorMessage("Null return");
        }

    }

    private void parseError(String error, SubmissionResult submissionResult) {
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
            submissionResult.setCode(COMPILATION_ERROR);
            submissionResult.setMessage(COMPILATION_ERROR_MESSAGE);
            submissionResult.setErrorMessage(error);
        } else {
            submissionResult.setCode(RUNTIME_ERROR);
            submissionResult.setMessage(RUNTIME_ERROR_MESSAGE);
            submissionResult.setErrorMessage(error);
        }
    }

    private void parseMessage(String message, String expectedOutput,
                              SubmissionResult submissionResult) {
        // if not ACCEPTED, either WRONG ANSWER or PRESENTATION ERROR

        if (message.equals(expectedOutput)) {
            submissionResult.setCode(ACCEPTED);
            submissionResult.setMessage(ACCEPTED_MESSAGE);
        } else if (expectedOutput.equalsIgnoreCase(message.trim()) ||
                expectedOutput.trim().equalsIgnoreCase(message)) {
            submissionResult.setCode(PRESENTATION_ERROR);
            submissionResult.setMessage(PRESENTATION_ERROR_MESSAGE);
            submissionResult.setErrorMessage("Expected: " + expectedOutput +
                    " Actual: " + message);
        } else {
            submissionResult.setCode(WRONG_ANSWER);
            submissionResult.setMessage(WRONG_ANSWER_MESSAGE);
            submissionResult.setErrorMessage("Expected: " + expectedOutput +
                    " Actual: " + message);
        }
    }

}
