package com.springmvc.service.impl;

import com.springmvc.exception.CustomException;
import com.springmvc.mapper.*;

import com.springmvc.po.*;
import com.springmvc.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 教师管理业务逻辑层实现
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource(name = "teacherMapper")
    private TeacherMapper teacherMapper;

   @Resource(name = "teacherCourseMapper")
    private TeacherCourseMapper teacherCourseMapper;

   @Resource(name = "teacherMapperExpand")
   private TeacherMapperExpand teacherMapperExpand;

   @Resource(name = "deptMapper")
   private DeptMapper deptMapper;

   @Resource(name = "timetableMapper")
   private TimetableMapper timetableMapper;

   @Resource(name = "loginMapper")
   private LoginMapper loginMapper;

   @Resource(name = "studentCourseMapper")
   private StudentCourseMapper studentCourseMapper;

    @Override
    public Teacher getTeacherById(String teacherID) throws Exception {
        return teacherMapper.selectByPrimaryKey(teacherID);
    }

    @Override
    public Boolean addTeacher(Teacher teacher) throws Exception {

        Teacher t = teacherMapper.selectByPrimaryKey(teacher.getTeacherID());
        if (t == null){
            try{
                teacherMapper.insert(teacher);
            }catch (Exception e){
                e.printStackTrace();
            }
            Teacher t1 = teacherMapper.selectByPrimaryKey(teacher.getTeacherID());
            if (t1 != null){
                Dept dept = deptMapper.selectByPrimaryKey(teacher.getDeptID());
                dept.setNumber(dept.getNumber() + 1);
                try{
                    deptMapper.updateByPrimaryKey(dept);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public Boolean deleteTeacher(String teacherId) throws Exception {

        Teacher teacher =  teacherMapper.selectByPrimaryKey(teacherId);
        if (teacher!=null){
            TeacherCourseExample teacherCourseExample = new TeacherCourseExample();
            TeacherCourseExample.Criteria criteria = teacherCourseExample.createCriteria();
            criteria.andTeacherIDEqualTo(teacherId);

            List<TeacherCourse> teacherCoursesList = teacherCourseMapper.selectByExample(teacherCourseExample);
            if (teacherCoursesList.size()>0){
                for (TeacherCourse teacherCourse:teacherCoursesList){
                    //若该老师有教授的课程且该课程学生未修完
                    StudentCourseExample studentCourseExample = new StudentCourseExample();
                    StudentCourseExample.Criteria criteria1 = studentCourseExample.createCriteria();
                    criteria1.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());
                    criteria1.andGradeIsNull();
                    int n = studentCourseMapper.countByExample(studentCourseExample);
                    if (n > 10){
                        throw new CustomException("该教师还有未完成的课程且选择该课的学生人数达到10人以上，若要删除该教师信息，请通知选择该课程的同学退选该课程");
                    }
                    else if(n > 0){
                        studentCourseMapper.deleteByExample(studentCourseExample);
                    }
                    //删除排课信息
                    TimetableExample timetableExample = new TimetableExample();
                    TimetableExample.Criteria criteria2 = timetableExample.createCriteria();
                    criteria2.andTeacher_CourseIDEqualTo(teacherCourse.getTeacher_CourseID());
                    List<Timetable> timetableList = timetableMapper.selectByExample(timetableExample);
                    if (timetableList.size() > 0){
                        timetableMapper.deleteByExample(timetableExample);
                    }
                }
                //删除教师课程信息
                teacherCourseMapper.deleteByExample(teacherCourseExample);
            }
            //删除教师
            teacherMapper.deleteByPrimaryKey(teacherId);
            //最后删除账号信息
            if (!teacher.getAccountNumber().equals("")){
                loginMapper.deleteByPrimaryKey(teacher.getAccountNumber());
            }
            //The number of dept delete 1
            Dept dept = deptMapper.selectByPrimaryKey(teacher.getDeptID());
            dept.setNumber(dept.getNumber() - 1);
            try {
                deptMapper.updateByPrimaryKey(dept);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    @Override
    public void updateTeacher(Teacher teacher) throws Exception {
        try{
            teacherMapper.updateByPrimaryKey(teacher);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //查询单个老师信息
    @Override
    public TeacherExpand findTeacherByID(String teacherId) throws Exception {

        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        TeacherExpand teacherExpand = null;
        if (teacher != null){
            teacherExpand = new TeacherExpand();
            BeanUtils.copyProperties(teacher,teacherExpand);

            String deptName = deptMapper.getDeptName(teacher.getDeptID());
            teacherExpand.setDeptName(deptName);
        }
        return teacherExpand;
    }

    @Override
    public Teacher findByAccountNumber(String accountNumber) throws Exception {
        return teacherMapper.selectByAccountNumber(accountNumber);
    }

    @Override
    public int getCountOfTeacher() throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andTeacherIDIsNotNull();

        return teacherMapper.countByExample(teacherExample);
    }

    @Override
    public int getCountOfTeacherOfDept(String deptID) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andDeptIDEqualTo(deptID);
        criteria.andTeacherIDIsNotNull();
        return teacherMapper.countByExample(teacherExample);
    }

    @Override
    public List<TeacherExpand> findByPaging(Integer toPageNo) throws Exception {

        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return teacherMapperExpand.findByPaging(pagingVO);
    }


    @Override
    public List<TeacherExpand> findTeacherByName(String teacherName, String deptID) throws Exception {

        List<TeacherExpand> list = new ArrayList<TeacherExpand>();
        if (!deptID.equals("")){
            TeacherExample teacherExample = new TeacherExample();
            TeacherExample.Criteria criteria = teacherExample.createCriteria();

            criteria.andTeacherNameLike("%"+teacherName+"%");
            criteria.andDeptIDEqualTo(deptID);

            List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
            if (teacherList!=null && teacherList.size()>0){
                for (Teacher teacher:teacherList){
                    //获取院系名称
                    String deptName = deptMapper.getDeptName(teacher.getDeptID());

                    TeacherExpand teacherExpand = new TeacherExpand();

                    BeanUtils.copyProperties(teacher,teacherExpand);
                    teacherExpand.setDeptName(deptName);

                    list.add(teacherExpand);
                }
            }
        }
        else {
            TeacherExample teacherExample = new TeacherExample();
            TeacherExample.Criteria criteria = teacherExample.createCriteria();

            criteria.andTeacherNameLike("%"+teacherName+"%");

            List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
            if (teacherList!=null && teacherList.size()>0){
                for (Teacher teacher:teacherList){
                    //获取院系名称
                    String deptName = deptMapper.getDeptName(teacher.getDeptID());

                    TeacherExpand teacherExpand = new TeacherExpand();

                    BeanUtils.copyProperties(teacher,teacherExpand);
                    teacherExpand.setDeptName(deptName);

                    list.add(teacherExpand);
                }
            }
        }
        return list;
    }

    @Override
    public List<String> getAllTeacherIDByDept(String deptID) throws Exception {
        TeacherExample teacherExample = new TeacherExample();
        TeacherExample.Criteria criteria = teacherExample.createCriteria();
        criteria.andDeptIDEqualTo(deptID);

        return teacherMapper.getAllTeacherIDByDept(teacherExample);
    }

    @Override
    public List<TeacherExpand> getAllTeacherOfDept(String deptID,Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return teacherMapperExpand.getTeacherOfDeptByPaging(pagingVO,deptID);
    }

    @Override
    public void commentTeacher(String teacherID, String comment) throws Exception {
        if (teacherID.equals("")){
            throw new CustomException("未知学生或教师课程");
        }
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherID);
        teacher.setComment(comment);

        try{
            teacherMapper.updateByPrimaryKeySelective(teacher);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
