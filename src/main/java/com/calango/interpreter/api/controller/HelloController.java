package com.calango.interpreter.api.controller;

import com.calango.interpreter.api.model.InterpreterIn;
import com.calango.interpreter.api.model.InterpreterOut;
import com.calango.interpreter.api.model.Submission;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.ucb.calango.api.publica.CalangoAPI;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String getHello(){
        InterpreterIn in = new InterpreterIn();
        InterpreterOut out = new InterpreterOut();
        CalangoAPI.setIn(in);
        CalangoAPI.setOut(out);
        CalangoAPI.interpretar("algoritmo fibonacci;\nprincipal\nfimPrincipal asjdh");
        return out.erro + out.mensagem;
    }

    @PostMapping("/judge")
    public String getJudgeResult(@RequestBody Submission submission){
//        submission.getCases().stream()
//                .forEach(c -> c.setMiles(c.getMiles() + 100));
        InterpreterIn in = new InterpreterIn();
        InterpreterOut out = new InterpreterOut();
        CalangoAPI.setIn(in);
        CalangoAPI.setOut(out);
        CalangoAPI.interpretar(submission.getCode());

        return out.erro + out.mensagem;

    }
}
