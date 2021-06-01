package com.bazalytskyi.finalProject.entity;

import java.sql.Time;

public class Test {
    private int id;
    private String name;
    private String complexity;
    private String decisionTime;
    private int subjectId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public String getDecisionTime() {
        return decisionTime;
    }

    public void setDecisionTime(String decisionTime) {
        this.decisionTime = decisionTime;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Test() {
    }
}
