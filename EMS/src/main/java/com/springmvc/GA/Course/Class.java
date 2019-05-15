package com.springmvc.GA.Course;

/**
 * One specialty study course of one professor in one classroom at one time
 *
 */
public class Class {
    private final int classId;
    private final String groupId;
    private final String moduleId;
    private String professorId;
    private String timeslotId;
    private String roomId;

    public Class(int classId,String groupId,String moduleId){
        this.classId = classId;
        this.groupId = groupId;
        this.moduleId = moduleId;
    }

    public void addProfessor(String professorId){
        this.professorId = professorId;
    }

    public void addTimeslot(String timeslotId){
        this.timeslotId = timeslotId;
    }

    public void setRoomId(String roomId){
        this.roomId = roomId;
    }

    public int getClassId() {
        return classId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public String getTimeslotId() {
        return timeslotId;
    }

    public String getRoomId() {
        return roomId;
    }
}
