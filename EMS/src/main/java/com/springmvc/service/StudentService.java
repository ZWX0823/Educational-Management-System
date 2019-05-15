package com.springmvc.service;

import com.springmvc.po.Student;
import com.springmvc.po.StudentExpand;

import java.util.List;

public interface StudentService {

    //根据账号查询学生信息
    Student findByAccountNumber(String accountNumber) throws Exception;

    /**
     * 根据学生姓名 院系 专业 班级模糊查找学生信息
     * @param studentName
     * @return
     * @throws Exception
     */
    List<StudentExpand> findStudentByName(String studentName,String deptID,String entranceYear,String specialtyID,String classID) throws Exception;

    /**
     * 根据学生ID查询学生
     * @param studentID
     * @return
     * @throws Exception
     */
    Student findStudentByID(String studentID) throws Exception;

    /**
     * 删除学生
     * @param studentID
     * @return
     * @throws Exception
     */
    Boolean deleteStudent(String studentID) throws Exception;

    //Update student
    void updateStudent(Student student) throws Exception;

    /**
     * 返回学生总数
     * @return
     * @throws Exception
     */
    int getCountOfStudent() throws Exception;

    //Get the count of students of one dept
    int getCountOfStudentOfDept(String deptID)throws Exception;
    /**
     * 根据分页查询学生信息
     * @param toPageNo
     * @return
     * @throws Exception
     */
    List<StudentExpand> findByPaging(Integer toPageNo) throws Exception;

    //Get students'info of one dept by paging
    List<StudentExpand> getStudentOfDeptByPaging(String deptID,Integer toPageNo)throws Exception;

    //添加学生
    Boolean addStudent(Student student)throws Exception;
}
