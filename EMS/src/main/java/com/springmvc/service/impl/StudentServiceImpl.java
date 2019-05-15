package com.springmvc.service.impl;

import com.springmvc.exception.CustomException;
import com.springmvc.mapper.*;
import com.springmvc.po.*;
import com.springmvc.po.Class;
import com.springmvc.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource(name = "studentMapper")
    private StudentMapper studentMapper;

    @Resource(name = "classMapper")
    private ClassMapper classMapper;

    @Resource(name = "deptMapper")
    private DeptMapper deptMapper;

    @Resource(name = "specialtyMapper")
    private SpecialtyMapper specialtyMapper;

    @Resource(name = "studentCourseMapper")
    private StudentCourseMapper studentCourseMapper;

    @Resource(name = "studentMapperExpand")
    private StudentMapperExpand studentMapperExpand;

    @Resource(name = "loginMapper")
    private LoginMapper loginMapper;

    @Resource(name = "specialtyYearMapper")
    private SpecialtyYearMapper specialtyYearMapper;

    @Resource(name = "teacherCourseMapper")
    private TeacherCourseMapper teacherCourseMapper;

    @Override
    public Student findByAccountNumber(String accountNumber) throws Exception {
        return studentMapper.selectByAccountNumber(accountNumber);
    }

    @Override
    public List<StudentExpand> findStudentByName(String studentName,String deptID,String entranceYear,String specialty,String classID) throws Exception {

        List<StudentExpand> list = new ArrayList<>();
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentNameLike("%"+studentName+"%");
        if (!deptID.equals("")){
            criteria.andDeptEqualTo(deptID);
        }
        if (!entranceYear.equals("")){
            criteria.andEntranceYearEqualTo(entranceYear);
        }
        if (!specialty.equals("")){
            criteria.andSpecialtyEqualTo(specialty);
        }
        if (!classID.equals("")){
            criteria.andClassIDEqualTo(classID);
        }
        List<Student> studentList = studentMapper.selectByExample(studentExample);

        if (studentList!=null && studentList.size()>0){

            for (Student student:studentList){
                //获取班级名
                String className = classMapper.getClassName(student.getClassID());
                //获取专业名
                String specialtyName = specialtyMapper.getSpecialtyName(student.getSpecialty());
                //获取院系名
                String deptName = deptMapper.getDeptName(student.getDept());
                //获取选课列表
//                StudentCourseExample studentCourseExample = new StudentCourseExample();
//                StudentCourseExample.Criteria criteria1 = studentCourseExample.createCriteria();
//                criteria1.andStudentIDEqualTo(student.getStudentID());
//
//                List<StudentCourse> studentCourseList = studentCourseMapper.selectByExample(studentCourseExample);

                StudentExpand studentExpand = new StudentExpand();

                BeanUtils.copyProperties(student,studentExpand);
//                BeanUtils.copyProperties(studentCourseList,studentExpand);
                studentExpand.setClassName(className);
                studentExpand.setSpecialtyName(specialtyName);
                studentExpand.setDeptName(deptName);

                list.add(studentExpand);
            }
        }
        return list;
    }

    @Override
    public Student findStudentByID(String studentID) throws Exception {

        return studentMapper.selectByPrimaryKey(studentID);
    }

    @Override
    public Boolean deleteStudent(String studentID) throws Exception {

        Student student =  studentMapper.selectByPrimaryKey(studentID);
        if (student!=null){
            StudentCourseExample studentCourseExample = new StudentCourseExample();
            StudentCourseExample.Criteria criteria = studentCourseExample.createCriteria();
            criteria.andStudentIDEqualTo(studentID);

            List<StudentCourse> list = studentCourseMapper.selectByExample(studentCourseExample);
            //删除选课信息
            if (list.size()>0){
                for (StudentCourse studentCourse:list){
                    //The number of teacher_course reduce 1
                    TeacherCourse teacherCourse = teacherCourseMapper.selectByPrimaryKey(studentCourse.getTeacher_CourseID());
                    int n = teacherCourse.getNumber();
                    teacherCourse.setNumber(n + 1);
                    try{
                        teacherCourseMapper.updateByPrimaryKeySelective(teacherCourse);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                //Delete all student_course
                studentCourseMapper.deleteByExample(studentCourseExample);
            }
            //The number of class reduce 1
            Class c = classMapper.selectByPrimaryKey(student.getClassID());
            int n1 = c.getNumber();
            c.setNumber(n1 - 1);
            try{
                classMapper.updateByPrimaryKeySelective(c);
            }catch (Exception e){
                e.printStackTrace();
            }
            //The number of specialty_Year reduce 1
            String specialty_YearID = student.getEntranceYear()+student.getSpecialty();
            SpecialtyYear specialtyYear = specialtyYearMapper.selectByPrimaryKey(specialty_YearID);
            int n2 = specialtyYear.getNumber();
            specialtyYear.setNumber(n2 - 1);
            try{
                specialtyYearMapper.updateByPrimaryKeySelective(specialtyYear);
            }catch (Exception e){
                e.printStackTrace();
            }
            //The number of dept reduce 1
            Dept dept = deptMapper.selectByPrimaryKey(student.getDept());
            int n3 = dept.getNumber();
            dept.setNumber(n3 - 1);
            try{
                deptMapper.updateByPrimaryKeySelective(dept);
            }catch (Exception e){
                e.printStackTrace();
            }
            //Delete student in student table
            try{
                studentMapper.deleteByPrimaryKey(studentID);
            }catch (Exception e){
                e.printStackTrace();
            }
            //Last delete the login info of student
            if (!student.getAccountNumber().equals("")){
                try{
                    loginMapper.deleteByPrimaryKey(student.getAccountNumber());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void updateStudent(Student student) throws Exception {
        Student s = studentMapper.selectByPrimaryKey(student.getStudentID());
        try{
            studentMapper.updateByPrimaryKey(student);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (!s.getClassID().equals(student.getClassID())){
            //The number of original class reduce 1 and the number of targeted class add 1
            Class c1 = classMapper.selectByPrimaryKey(s.getClassID());
            c1.setNumber(c1.getNumber() - 1);
            Class c2 = classMapper.selectByPrimaryKey(student.getClassID());
            c2.setNumber(c2.getNumber() + 1);
            try{
                classMapper.updateByPrimaryKeySelective(c1);
                classMapper.updateByPrimaryKeySelective(c2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (!s.getSpecialty().equals(student.getSpecialty())){
            //The number of original specialty_year reduce 1 and the number of targeted specialty_year add 1
            String specialty_YearID1 = s.getEntranceYear() + s.getSpecialty();
            SpecialtyYear s1 = specialtyYearMapper.selectByPrimaryKey(specialty_YearID1);
            s1.setNumber(s1.getNumber() - 1);
            String specialty_YearID2 = student.getEntranceYear() + student.getSpecialty();
            SpecialtyYear s2 = specialtyYearMapper.selectByPrimaryKey(specialty_YearID2);
            s2.setNumber(s2.getNumber() + 1);
            try{
                specialtyYearMapper.updateByPrimaryKeySelective(s1);
                specialtyYearMapper.updateByPrimaryKeySelective(s2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (!s.getDept().equals(student.getDept())){
            //The number of original dept reduce 1 and the number of targeted dept add 1
            Dept d1 = deptMapper.selectByPrimaryKey(s.getDept());
            d1.setNumber(d1.getNumber() - 1);
            Dept d2 = deptMapper.selectByPrimaryKey(student.getDept());
            d2.setNumber(d2.getNumber() + 1);
            try{
                deptMapper.updateByPrimaryKeySelective(d1);
                deptMapper.updateByPrimaryKeySelective(d2);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getCountOfStudent() throws Exception {

        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andStudentIDIsNotNull();

        return studentMapper.countByExample(studentExample);
    }

    @Override
    public int getCountOfStudentOfDept(String deptID) throws Exception {
        StudentExample studentExample = new StudentExample();
        StudentExample.Criteria criteria = studentExample.createCriteria();
        criteria.andDeptEqualTo(deptID);
        return studentMapper.countByExample(studentExample);
    }

    @Override
    public List<StudentExpand> findByPaging(Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return studentMapperExpand.findByPaging(pagingVO);
//        List<Student> studentList = new ArrayList<>();
//        try{
//            studentList = studentMapperExpand.findByPaging(pagingVO);
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        List<StudentExpand> studentExpandList = new ArrayList<>();
//        for (Student student:studentList){
//            StudentExpand studentExpand = new StudentExpand();
//            studentExpand.setDeptName(deptMapper.getDeptName(student.getDept()));
//            studentExpand.setSpecialtyName(specialtyMapper.getSpecialtyName(student.getSpecialty().substring(4,8)));
//            studentExpand.setClassName(classMapper.getClassName(student.getClassID()));
//            BeanUtils.copyProperties(student,studentExpand);
//
//            studentExpandList.add(studentExpand);
//        }
//        return studentExpandList;
    }

    @Override
    public List<StudentExpand> getStudentOfDeptByPaging(String deptID, Integer toPageNo) throws Exception {
        PagingVO pagingVO = new PagingVO();
        pagingVO.setToPageNo(toPageNo);

        return studentMapperExpand.getStudentOfDeptByPaging(deptID,pagingVO);
    }

    @Override
    public Boolean addStudent(Student student) throws Exception {

        Student s = studentMapper.selectByPrimaryKey(student.getStudentID());
        if (s == null){
            studentMapper.insert(student);
            Student s1 = studentMapper.selectByPrimaryKey(student.getStudentID());
            if (s1 != null){
                //The number of class add 1
                Class c = classMapper.selectByPrimaryKey(student.getClassID());
                int n1 = c.getNumber();
                c.setNumber(n1 + 1);
                try{
                    classMapper.updateByPrimaryKeySelective(c);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //The number of specialty_Year add 1
                String specialty_YearID = student.getEntranceYear()+student.getSpecialty();
                SpecialtyYear specialtyYear = specialtyYearMapper.selectByPrimaryKey(specialty_YearID);
                int n2 = specialtyYear.getNumber();
                specialtyYear.setNumber(n2 + 1);
                try{
                    specialtyYearMapper.updateByPrimaryKeySelective(specialtyYear);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //The number of dept add 1
                Dept dept = deptMapper.selectByPrimaryKey(student.getDept());
                int n3 = dept.getNumber();
                dept.setNumber(n3 + 1);
                try{
                    deptMapper.updateByPrimaryKeySelective(dept);
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
}
