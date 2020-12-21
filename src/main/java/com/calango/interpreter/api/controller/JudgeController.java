package com.calango.interpreter.api.controller;

import com.calango.interpreter.api.model.*;
import com.calango.interpreter.api.service.InterpreterInService;
import com.calango.interpreter.api.service.InterpreterOutService;
import com.calango.interpreter.api.service.SubmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.ucb.calango.api.publica.CalangoAPI;

@RestController
public class JudgeController {


    @PostMapping(value="/judge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubmissionService> getJudgeResult(@RequestBody Submission submission){

        SubmissionService submissionService = new SubmissionService();

        for(JudgeCase judgeCase : submission.getCases()){
            InterpreterInService in = new InterpreterInService(judgeCase);
            InterpreterOutService out = new InterpreterOutService();
            CalangoAPI.setIn(in);
            CalangoAPI.setOut(out);
            CalangoAPI.interpretar(submission.getCode());

            if (out.getError() != null){
                submissionService.parseError(out.getError());
                return new ResponseEntity<>(submissionService, HttpStatus.OK);
            }

            if (!out.getMessage().equals(judgeCase.getOutput())){
                submissionService.parseMessage(out.getMessage(), judgeCase.getOutput());
                return new ResponseEntity<>(submissionService, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(submissionService, HttpStatus.OK);

    }
}
