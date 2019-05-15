package com.springmvc.GA.Course;

import java.util.List;

/**
 * Course
 *
 */
public class Module {
    private final String moduleId;
    private final String moduleCode;
    private final String module;
    private final List<String> professorIds;

    public Module(String moduleId,String moduleCode,String module,List<String> professorIds){
        this.moduleId = moduleId;
        this.moduleCode = moduleCode;
        this.module = module;
        this.professorIds = professorIds;
    }

    public String getModuleId() {
        return moduleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModule() {
        return module;
    }

    public String getRandomProfessorId() {
        String professorId = professorIds.get((int)(professorIds.size() * Math.random()));
        return professorId;
    }
}
