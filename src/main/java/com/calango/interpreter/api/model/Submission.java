package com.calango.interpreter.api.model;

import java.util.List;

public class Submission {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public List<JudgeCase> getCases() {
        return cases;
    }

    public void setCases(List<JudgeCase> cases) {
        this.cases = cases;
    }

    private List<JudgeCase> cases;
}
