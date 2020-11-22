package com.calango.interpreter.api.controller;

import com.calango.interpreter.api.model.InterpreterIn;
import com.calango.interpreter.api.model.InterpreterOut;
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
}
