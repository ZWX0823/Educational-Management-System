package com.springmvc.service.GA.impl;

import com.springmvc.GA.Course.GeneticAlgorithm;
import com.springmvc.GA.Course.Population;
import com.springmvc.GA.Course.Timetable;
import com.springmvc.mapper.*;
import com.springmvc.po.*;
import com.springmvc.service.GA.ScheduleService;
import com.springmvc.util.TimeCompare;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Resource(name = "classroomMapper")
    private ClassroomMapper classroomMapper;

    @Resource(name = "timeMapper")
    private TimeMapper timeMapper;

    @Resource(name = "teacherMapper")
    private TeacherMapper teacherMapper;

    @Resource(name = "courseMapper")
    private CourseMapper courseMapper;

    @Resource(name = "teacherCourseMapper")
    private TeacherCourseMapper teacherCourseMapper;

    @Resource(name = "specialtyYearMapper")
    private SpecialtyYearMapper specialtyYearMapper;

    @Resource(name = "timetableMapper")
    private TimetableMapper timetableMapper;

    @Resource(name = "specialtyYearCourseMapper")
    private SpecialtyYearCourseMapper specialtyYearCourseMapper;

    @Override
    public Timetable initializeTimetable() throws Exception {

        //Create timetable
        Timetable timetable = new Timetable();

        //Set up rooms
        ClassroomExample classroomExample = new ClassroomExample();
        ClassroomExample.Criteria criteria = classroomExample.createCriteria();
        criteria.andClassroomIDIsNotNull();
        List<Classroom> classroomList = classroomMapper.selectByExample(classroomExample);
        for (Classroom classroom:classroomList){
            timetable.addRoom(classroom.getClassroomID(),classroom.getClassroomName(),classroom.getSize());
        }


        //Set up timeslots
        TimeExample timeExample = new TimeExample();
        TimeExample.Criteria criteria1 = timeExample.createCriteria();
        criteria1.andTimeIDIsNotNull();
        //Classes are not scheduled on Saturdays and Sundays
        criteria1.andTimeIDLessThan("60");
        List<Time> timeList = timeMapper.selectByExample(timeExample);
        for (Time time:timeList){
            timetable.addTimeslot(time.getTimeID(),time.getTimeName());
        }


        //Set up professors
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria2 = teacherExample.createCriteria();
        criteria2.andTeacherIDIsNotNull();
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        for (Teacher teacher:teacherList){
            timetable.addProfessor(teacher.getTeacherID(),teacher.getTeacherName());
        }

        //Set up modules and define the professors that teach them
        CourseExample courseExample = new CourseExample();
        CourseExample.Criteria criteria3 = courseExample.createCriteria();
        criteria3.andCourseIDIsNotNull();
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        for (Course course:courseList){
            TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
            TeacherCourseExample.Criteria criteria4 = teacherCourseExample.createCriteria();
            criteria4.andCourseIDEqualTo(course.getCourseID());

            //List of teacherID
            List<String> professorList = teacherCourseMapper.getTeacherOfCourse(teacherCourseExample);
            //Just select the course that will be taught by one teacher
            if (professorList.size() > 0){
//                String[] professorArray = professorList.toArray(new String[0]);
                timetable.addModule(course.getCourseID(),course.getCourseName(),course.getCourseName(),professorList);
            }
        }

        //Set up student groups and the modules they take
        SpecialtyYearExample specialtyYearExample = new SpecialtyYearExample();
        SpecialtyYearExample.Criteria criteria5 = specialtyYearExample.createCriteria();
        criteria5.andSpecialty_YearIDIsNotNull();
        criteria5.andFinishEqualTo("0");
        List<SpecialtyYear> specialtyYearList = specialtyYearMapper.selectByExample(specialtyYearExample);
        for (SpecialtyYear specialtyYear:specialtyYearList){
            //Get term of specialty
            Calendar now = Calendar.getInstance();
            Calendar past = Calendar.getInstance();
            past.setTime(specialtyYear.getAdmissionDate());
            double t=0.0;
            try{
                t = (TimeCompare.timeCompare(past,now));
            }catch (NumberFormatException e){
                e.printStackTrace();
            }

            SpecialtyYearCourseExample specialtyYearCourseExample = new SpecialtyYearCourseExample();
            SpecialtyYearCourseExample.Criteria criteria6 = specialtyYearCourseExample.createCriteria();
            criteria6.andCourseIDIsNotNull();
            criteria6.andSpecialty_YearIDEqualTo(specialtyYear.getSpecialty_YearID());
            if (t >= 0.0 && t <= 0.5){
                criteria6.andTimeEqualTo("10");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
//                    String[] coursesArray = courseIDList.toArray(new String[0]);
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            else if (t > 0.5 && t <= 1.0){
                criteria6.andTimeEqualTo("11");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            else if (t > 1.0 && t <= 1.5){
                criteria6.andTimeEqualTo("20");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            else if (t > 1.5 && t <= 2.0){
                criteria6.andTimeEqualTo("21");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            else if (t > 2.0 && t <= 2.5){
                criteria6.andTimeEqualTo("30");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            else if (t > 2.5 && t <= 3.0){
                criteria6.andTimeEqualTo("31");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            else if (t > 3.0 && t <= 3.5){
                criteria6.andTimeEqualTo("40");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            else if (t > 3.5 && t <= 4.0){
                criteria6.andTimeEqualTo("41");
                //List of courseID
                List<String> courseIDList = specialtyYearCourseMapper.getCourseIDListByExample(specialtyYearCourseExample);
                if (courseIDList.size()>0){
                    try{
                        timetable.addGroup(specialtyYear.getSpecialty_YearID(),specialtyYear.getNumber(),courseIDList);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        }

        return timetable;
    }

    @Override
    public void storeScheduleInfo(Timetable timetable) throws Exception {

        //First delete already present curriculum in timetable database
        com.springmvc.po.TimetableExample timetableExample = new TimetableExample();
        TimetableExample.Criteria criteria1 = timetableExample.createCriteria();
        criteria1.andTeacher_CourseIDIsNotNull();
        try{
            timetableMapper.deleteByExample(timetableExample);
        }catch (Exception e){
            e.printStackTrace();
        }

        com.springmvc.GA.Course.Class classes[] = timetable.getClasses();
        for (com.springmvc.GA.Course.Class bestClass:classes){

            //CourseID
            String courseID = timetable.getModule(bestClass.getModuleId()).getModuleId();
            //TeacherID
            String teacherID = timetable.getProfessor(bestClass.getProfessorId()).getProfessorId();
            //TimeID
            String time = timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslotId();
            //RoomID
            String classroomID = timetable.getRoom(bestClass.getRoomId()).getRoomId();
            //Specialty_YearID
            String specialty_YearID = timetable.getGroup(bestClass.getGroupId()).getGroupId();
            //TeacherCourseID
            TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
            TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
            criteria.andCourseIDEqualTo(courseID);
            criteria.andTeacherIDEqualTo(teacherID);
            String teacher_CourseID = teacherCourseMapper.getTeacherCourseIDByExample(teacherCourseExample);
            //TermID
            Calendar now = Calendar.getInstance();
            int nowYear = now.get(Calendar.YEAR);
            int nowMonth = now.get(Calendar.MONTH);
            String termID = "";
            if (nowMonth >= 1 && nowMonth < 8){
                //For example:20181 express : second semester of the academic year 2018-2019
                int sub = (nowYear - 1) * 10 + 1;
                termID = String.valueOf(sub);
            }
            else if ((nowMonth >= 8 && nowMonth <12) || (nowMonth >=0 && nowMonth <1)){
                int sub = (nowYear - 1) * 10;
                termID = String.valueOf(sub);
            }

            //Start storing
            com.springmvc.po.Timetable timetable1 = new com.springmvc.po.Timetable();
            timetable1.setTeacher_CourseID(teacher_CourseID);
            timetable1.setTime(time);
            timetable1.setTerm(termID);
            timetable1.setClassroom(classroomID);
            timetable1.setSpecialty_Year(specialty_YearID);
            try{
                timetableMapper.insert(timetable1);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    @Override
    public void scheduleCourse() throws Exception {
        //Create Timetable and initialize while all the available courses,rooms,timeslots,professors,modules,and groups
        com.springmvc.GA.Course.Timetable timetable = this.initializeTimetable();

        //Initialize GA
        GeneticAlgorithm ga = new GeneticAlgorithm(100,0.01,0.9,2,5);

        //Initialize population
        Population population = ga.initPopulation(timetable);

        //Evaluate population
        ga.evalPopulation(population,timetable);

        //Keep track of current generation
        int generation = 1;

        //Start evolution loop
        //Add termination condition
        while (ga.isTerminationConditionMet(generation,1000) == false
                && ga.isTerminationConditionMet(population) == false){
            //Print fitness
            System.out.println("G" + generation + "Best fitness: " + population.getFittest(0).getFitness());

            //Apply crossover
            population = ga.crossoverPopulation(population);

            //Apply mutation
            population = ga.mutatePopulation(population,timetable);

            //Evaluate population
            ga.evalPopulation(population,timetable);

            //Increment the current generation
            generation++;
        }
        //Print fitness
        timetable.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solution found in " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Clashes: " + timetable.calcClashes());

        this.storeScheduleInfo(timetable);
    }
}
