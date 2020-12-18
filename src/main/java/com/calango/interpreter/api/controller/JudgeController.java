package com.calango.interpreter.api.controller;

import com.calango.interpreter.api.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.ucb.calango.api.publica.CalangoAPI;

@RestController
public class JudgeController {


    @PostMapping(value="/judge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubmissionResult> getJudgeResult(@RequestBody Submission submission){

        SubmissionResult submissionResult = new SubmissionResult();

        for(JudgeCase judgeCase : submission.getCases()){
            InterpreterIn in = new InterpreterIn(judgeCase);
            InterpreterOut out = new InterpreterOut();
            CalangoAPI.setIn(in);
            CalangoAPI.setOut(out);
            CalangoAPI.interpretar(submission.getCode());

            if (out.erro != null){
                submissionResult.parseError(out.erro);
                return new ResponseEntity<>(submissionResult, HttpStatus.OK);
            }

            if (!out.mensagem.equals(judgeCase.getOutput())){
                submissionResult.parseMessage(out.mensagem, judgeCase.getOutput());
                return new ResponseEntity<>(submissionResult, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(submissionResult, HttpStatus.OK);

    }
}
