package com.springmvc.service.impl;

import com.springmvc.mapper.*;
import com.springmvc.po.CurriculumInfo;
import com.springmvc.po.TeacherCourse;
import com.springmvc.po.Timetable;
import com.springmvc.po.TimetableExample;
import com.springmvc.service.CurriculumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurriculumServiceImpl implements CurriculumService {

    @Resource(name = "timetableMapper")
    private TimetableMapper timetableMapper;

    @Resource(name = "teacherCourseMapper")
    private TeacherCourseMapper teacherCourseMapper;

    @Resource(name = "teacherMapper")
    private TeacherMapper teacherMapper;

    @Resource(name = "courseMapper")
    private CourseMapper courseMapper;

    @Resource(name = "classroomMapper")
    private ClassroomMapper classroomMapper;

    @Override
    public List<Timetable> getCurriculum(String specialty_YearID, String term) throws Exception {
        TimetableExample timetableExample = new TimetableExample();
        TimetableExample.Criteria criteria = timetableExample.createCriteria();
        criteria.andSpecialty_YearEqualTo(specialty_YearID);
        criteria.andTermEqualTo(term);

         return timetableMapper.selectByExample(timetableExample);
    }

    @Override
    public List<CurriculumInfo> getDetailCurriculum(List<Timetable> timetableList) throws Exception {
        List<CurriculumInfo> curriculumInfoList = new ArrayList<>();
        for (Timetable timetable:timetableList){
            CurriculumInfo curriculumInfo = new CurriculumInfo();
            String teacherCourseID = timetable.getTeacher_CourseID();
            TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(teacherCourseID);
            //Set curriculum info
            curriculumInfo.setTeacherID(teacherCourse.getTeacherID());
            curriculumInfo.setTeacherName(teacherMapper.getTeacherNameByPrimaryKey(teacherCourse.getTeacherID()));
            curriculumInfo.setCourseID(teacherCourse.getCourseID());
            curriculumInfo.setCourseName(courseMapper.getCourseNameByPrimaryKey(teacherCourse.getCourseID()));
            curriculumInfo.setSpecialty_YearID(timetable.getSpecialty_Year());
            curriculumInfo.setTime(timetable.getTime());
            curriculumInfo.setTerm(timetable.getTerm());
            curriculumInfo.setClassroom(timetable.getClassroom());
            curriculumInfo.setClassroomName(classroomMapper.getClassroomNameByPrimaryKey(timetable.getClassroom()));
            //Add to list
            curriculumInfoList.add(curriculumInfo);
        }
        return curriculumInfoList;
    }
}
