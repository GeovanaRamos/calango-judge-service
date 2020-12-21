package com.calango.interpreter.api.controller;

import com.calango.interpreter.api.model.*;
import com.calango.interpreter.api.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JudgeController {

    private SubmissionService submissionService;

    @Autowired
    public void setSubmissionService(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping(value="/judge", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubmissionResult> getJudgeResult(@RequestBody Submission submission){
        return new ResponseEntity<>(submissionService.judgeSubmission(submission), HttpStatus.OK);
    }
}
