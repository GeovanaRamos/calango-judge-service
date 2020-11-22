package com.calango.interpreter.api.controller;

import com.calango.interpreter.api.model.InterpreterIn;
import com.calango.interpreter.api.model.InterpreterOut;
import com.calango.interpreter.api.model.JudgeCase;
import com.calango.interpreter.api.model.Submission;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.ucb.calango.api.publica.CalangoAPI;

@RestController
public class HelloController {

    @PostMapping("/judge")
    public String getJudgeResult(@RequestBody Submission submission){

        for(JudgeCase judgeCase : submission.getCases()){
            InterpreterIn in = new InterpreterIn(judgeCase);
            InterpreterOut out = new InterpreterOut();
            CalangoAPI.setIn(in);
            CalangoAPI.setOut(out);
            CalangoAPI.interpretar(submission.getCode());

            if (out.erro != null)
                return "erro " + judgeCase.getInput();

            if (!out.mensagem.equals(judgeCase.getOutput()))
                return "errou";
        }

        return "acertou";

    }
}
