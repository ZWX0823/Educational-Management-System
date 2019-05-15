package com.springmvc.GA.Course;

import java.util.HashMap;
import java.util.List;

public class Timetable {
    private final HashMap<String,Room> rooms;
    private final HashMap<String,Professor> professors;
    private final HashMap<String,Module> modules;
    private final HashMap<String,Group> groups;
    private final HashMap<String,Timeslot> timeslots;
    private Class classes[];

    private int numClasses = 0;

    /**
     * Initialize new Timetable
     *
     */
    public Timetable(){
        this.rooms = new HashMap<String, Room>();
        this.professors = new HashMap<String, Professor>();
        this.modules = new HashMap<String, Module>();
        this.groups = new HashMap<String, Group>();
        this.timeslots = new HashMap<String, Timeslot>();
    }

    public Timetable(Timetable cloneable){
        this.rooms = cloneable.getRooms();
        this.professors = cloneable.getProfessors();
        this.modules = cloneable.getModules();
        this.groups = cloneable.getGroups();
        this.timeslots = cloneable.getTimeslots();
    }

    private HashMap<String,Group> getGroups(){
        return this.groups;
    }

    private HashMap<String,Timeslot> getTimeslots(){
        return this.timeslots;
    }

    private HashMap<String,Module> getModules(){
        return this.modules;
    }

    private HashMap<String,Professor> getProfessors(){
        return this.professors;
    }

    /**
     * Add new rom
     *
     * @param roomId
     * @param roomName
     * @param capacity
     */
    public void addRoom(String roomId,String roomName,int capacity){
        this.rooms.put(roomId,new Room(roomId,roomName,capacity));
    }

    /**
     * Add new professor
     *
     * @param professorId
     * @param professorName
     */
    public void addProfessor(String professorId,String professorName){
        this.professors.put(professorId,new Professor(professorId,professorName));
    }

    /**
     * Add new module
     *
     * @param moduleId
     * @param moduleCode
     * @param module
     * @param professorIds
     */
    public void addModule(String moduleId,String moduleCode,String module,List<String> professorIds){
        this.modules.put(moduleId,new Module(moduleId,moduleCode,module,professorIds));
    }

    public void addGroup(String groupId,int groupSize,List<String> moduleIds){
        this.groups.put(groupId,new Group(groupId,groupSize,moduleIds));
        this.numClasses = 0;
    }

    public void addTimeslot(String timeslotId,String timeslot){
        this.timeslots.put(timeslotId,new Timeslot(timeslotId,timeslot));
    }

    /**
     * Create classes using individual's chromosome
     *
     * @param individual
     */
    public void createClasses(Individual individual){
        //Init classes
        Class classes[] = new Class[this.getNumClasses()];

        //Get individual's chromosome
        List<String> chromosome = individual.getChromosome();
        int chromosomePos = 0;
        int classesIndex = 0;

        for (Group group:this.getGroupsAsArray()){
            List<String> moduleIds = group.getModuleIds();
            for (String moduleId:moduleIds){
                classes[classesIndex] = new Class(classesIndex,group.getGroupId(),moduleId);

                //Add timeslot

                classes[classesIndex].addTimeslot(chromosome.get(chromosomePos));
                chromosomePos++;

                //Add room
                classes[classesIndex].setRoomId(chromosome.get(chromosomePos));
                chromosomePos++;

                //Add professor
                classes[classesIndex].addProfessor(chromosome.get(chromosomePos));
                chromosomePos++;

                classesIndex++;
            }
        }
        this.classes = classes;
    }

    /**
     * Get room from roomId
     *
     * @param roomId
     * @return
     */
    public Room getRoom(String roomId){
        if (!this.rooms.containsKey(roomId)){
            System.out.println("Rooms doesn't contain key " + roomId);
        }
        return (Room) this.rooms.get(roomId);
    }

    public HashMap<String,Room> getRooms(){
        return this.rooms;
    }

    /**
     * Get random room
     *
     * @return
     */
    public Room getRandomRoom(){
        Object[] roomsArray = this.rooms.values().toArray();
        Room room = (Room) roomsArray[(int)(roomsArray.length * Math.random())];
        return room;
    }

    /**
     * Get professor from professorId
     *
     * @param professorId
     * @return
     */
    public Professor getProfessor(String professorId){
        return (Professor)this.professors.get(professorId);
    }

    /**
     * Get module from moduleId
     *
     * @param moduleId
     * @return
     */
    public Module getModule(String moduleId){
        return this.modules.get(moduleId);
    }

    /**
     * Get moduleIds of student group
     *
     * @param groupId
     * @return
     */
    public List<String> getGroupModules(String groupId){
        Group group = (Group)this.groups.get(groupId);
        return group.getModuleIds();
    }

    /**
     * Get group from groupId
     *
     * @param groupId
     * @return
     */
    public Group getGroup(String groupId){
        return (Group)this.groups.get(groupId);
    }

    /**
     * Get all student groups
     *
     * @return
     */
    public Group[] getGroupsAsArray(){
        return (Group[])this.groups.values().toArray(new Group[this.groups.size()]);
    }

    /**
     * Get timeslot by timeslotId
     *
     * @param timeslotId
     * @return
     */
    public Timeslot getTimeslot(String timeslotId){
        return (Timeslot)this.timeslots.get(timeslotId);
    }

    /**
     * Get random timeslotId
     *
     * @return
     */
    public Timeslot getRandomTimeslot(){
        Object[] timeslotArray = this.timeslots.values().toArray();
        Timeslot timeslot = (Timeslot)timeslotArray[(int)(timeslotArray.length * Math.random())];
        return timeslot;
    }

    /**
     * Get classes
     *
     * @return
     */
    public Class[] getClasses(){
        return this.classes;
    }

    /**
     * Get number of classes that need scheduling
     *
     * @return
     */
    public int getNumClasses(){
        if (this.numClasses > 0){
            return this.numClasses;
        }
        int numClasses = 0;
        Group groups[] = (Group[])this.groups.values().toArray(new Group[this.groups.size()]);
        for (Group group:groups){
            numClasses += group.getModuleIds().size();
        }
        this.numClasses = numClasses;

        return this.numClasses;
    }

    /**
     * Calculate the number of clashes(冲突)
     *
     * @return
     */
    public int calcClashes(){
        int clashes = 0;

        for (Class classA:this.classes){
            //Check room capacity
            int roomCapacity = this.getRoom(classA.getRoomId()).getCapacity();
            int groupSize = this.getGroup(classA.getGroupId()).getGroupSize();
            if (roomCapacity < groupSize){
                clashes++;
            }

            //Check if room is taken
            for (Class classB:this.classes){
                if (classA.getRoomId() == classB.getRoomId() && classA.getTimeslotId() == classB.getTimeslotId() &&
                classA.getClassId() != classB.getClassId()){
                    clashes++;
                    break;
                }
            }

            //Check if professor is available
            for (Class classB:this.classes){
                if (classA.getProfessorId() == classB.getProfessorId() && classA.getTimeslotId() == classB.getTimeslotId() && classA.getClassId() != classB.getClassId()){
                    clashes++;
                    break;
                }
            }
        }
        return clashes;
    }
}
