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

    public SubmissionResult judgeSubmission(Submission submission) {

        SubmissionResult submissionResult = new SubmissionResult(ACCEPTED, ACCEPTED_MESSAGE);
        //Thread[] threads = new Thread[submission.getCases().size()];
        int i = 0;

        for(JudgeCase judgeCase : submission.getCases()){
            //threads[i] = new Thread(() -> {
                callInterpreter(submissionResult, judgeCase, submission);
            //});
            log.info("Init case: " + i);
            //threads[i].start();
            if (submissionResult.getCode() !=ACCEPTED)
                break;
            i++;
        }

//        try {
//            log.info("Main thread waiting first case...");
//            threads[0].join(5000);
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            log.info("Exception on join");
//        }

        //interruptAllThreads(threads, submissionResult);

        return submissionResult;
    }

    private void callInterpreter(SubmissionResult submissionResult, JudgeCase judgeCase,
                                 Submission submission){
        if (submissionResult.getCode() != ACCEPTED)
            return;

        InterpreterIn in = new InterpreterIn(judgeCase);
        InterpreterOut out = new InterpreterOut();
        CalangoAPI.setIn(in);
        CalangoAPI.setOut(out);
        CalangoAPI.interpretar(submission.getCode());

        if (submissionResult.getCode() != ACCEPTED)
            return;

        if (out.getError() != null) {
            parseError(out.getError(), submissionResult);
        } else if (out.getMessage() != null &&
                !out.getMessage().equals(judgeCase.getOutput())) {
            parseMessage(out.getMessage(), judgeCase.getOutput(), submissionResult);
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
            log.info(COMPILATION_ERROR_MESSAGE + ": " + error);
        } else {
            submissionResult.setCode(RUNTIME_ERROR);
            submissionResult.setMessage(RUNTIME_ERROR_MESSAGE);
            log.info(RUNTIME_ERROR_MESSAGE + ": " + error);
        }
    }

    private void parseMessage(String message, String expectedOutput,
                             SubmissionResult submissionResult) {
        // either WRONG ANSWER or PRESENTATION ERROR
        if (expectedOutput.equalsIgnoreCase(message.trim()) ||
                expectedOutput.trim().equalsIgnoreCase(message)) {
            submissionResult.setCode(PRESENTATION_ERROR);
            submissionResult.setMessage(PRESENTATION_ERROR_MESSAGE);
            log.info(PRESENTATION_ERROR_MESSAGE + " Expected: " + expectedOutput +
                    " Actual: " + message);
        } else {
            submissionResult.setCode(WRONG_ANSWER);
            submissionResult.setMessage(WRONG_ANSWER_MESSAGE);
            log.info(WRONG_ANSWER_MESSAGE + " Expected: " + expectedOutput +
                    " Actual: " + message);
        }
    }

//    private void interruptAllThreads(Thread[] threads, SubmissionResult submissionResult){
//        for (Thread th : threads ){
//            if (th.isAlive()) {
//                th.stop();
//                submissionResult.setCode(TIME_LIMIT_EXCEEDED);
//                submissionResult.setMessage(TIME_LIMIT_EXCEEDED_MESSAGE);
//                log.info("Interrupted thread " + th.getName());
//            }
//        }
//    }

}
