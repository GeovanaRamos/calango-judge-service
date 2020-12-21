package com.calango.interpreter.api.service;

import br.ucb.calango.api.publica.CalangoAPI;
import com.calango.interpreter.api.model.JudgeCase;
import com.calango.interpreter.api.model.Submission;
import com.calango.interpreter.api.model.SubmissionResult;
import org.springframework.stereotype.Component;

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


    public SubmissionResult judgeSubmission(Submission submission){
        SubmissionResult submissionResult = new SubmissionResult(ACCEPTED, ACCEPTED_MESSAGE);

        for(JudgeCase judgeCase : submission.getCases()){

            InterpreterIn in = new InterpreterIn(judgeCase);
            InterpreterOut out = new InterpreterOut();
            CalangoAPI.setIn(in);
            CalangoAPI.setOut(out);

            CalangoAPI.interpretar(submission.getCode());

            if (out.getError() != null){
                this.parseError(out.getError(), submissionResult);
                return submissionResult;
            }

            if (!out.getMessage().equals(judgeCase.getOutput())){
                this.parseMessage(out.getMessage(), judgeCase.getOutput(), submissionResult);
                return submissionResult;
            }
        }

        return submissionResult;
    }

    public void parseError(String error, SubmissionResult submissionResult) {
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
        } else {
            submissionResult.setCode(RUNTIME_ERROR);
            submissionResult.setMessage(RUNTIME_ERROR_MESSAGE);
        }
    }

    public void parseMessage(String message, String expectedOutput,
                             SubmissionResult submissionResult) {
        // either WRONG ANSWER or PRESENTATION ERROR
        if (expectedOutput.equalsIgnoreCase(message.trim()) ||
                expectedOutput.trim().equalsIgnoreCase(message)) {
            submissionResult.setCode(PRESENTATION_ERROR);
            submissionResult.setMessage(PRESENTATION_ERROR_MESSAGE);
        } else {
            submissionResult.setCode(WRONG_ANSWER);
            submissionResult.setMessage(WRONG_ANSWER_MESSAGE);
        }
    }
}
