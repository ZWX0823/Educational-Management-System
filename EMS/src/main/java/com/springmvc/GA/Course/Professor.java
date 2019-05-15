package com.springmvc.GA.Course;

/**
 * 教授
 */
public class Professor {
    private final String professorId;
    private final String professorName;

    public Professor(String professorId,String professorName){
        this.professorId = professorId;
        this.professorName = professorName;
    }

    public String getProfessorId() {
        return professorId;
    }

    public String getProfessorName() {
        return professorName;
    }
}
