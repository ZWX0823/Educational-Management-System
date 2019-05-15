package com.springmvc.service;

import com.springmvc.po.Teacher;
import com.springmvc.po.TeacherExpand;

import java.util.List;

/**
 * 教师信息管理的业务逻辑层
 * @author ZWX
 */
public interface TeacherService {

    //Get teacher by teacherID
    Teacher getTeacherById(String teacherID)throws Exception;
    /**
     * 添加老师信息
     * @param teacher
     * @return true为成功 false为失败
     */
    Boolean addTeacher(Teacher teacher) throws Exception;

    /**
     * 删除老师信息
     * @param teacherId
     * @return
     * @throws Exception
     */
    Boolean deleteTeacher(String teacherId) throws Exception;
    /**
     * 修改教师信息
     * @param teacher
     * @return true为成功 false为失败
     * @throws Exception
     */
    void updateTeacher(Teacher teacher) throws Exception;

    /**
     * 根据教师编号查询教师信息
     * @param teacherId
     * @return
     * @throws Exception
     */
    TeacherExpand findTeacherByID(String teacherId) throws Exception;

    /**
     * 根据教师账号（唯一）查询教师信息
     * @param accountNumber
     * @return
     * @throws Exception
     */
    Teacher findByAccountNumber(String accountNumber) throws Exception;

    /**
     * 返回教师数量
     * @return
     * @throws Exception
     */
    int getCountOfTeacher()throws Exception;

    //Get count of teacher of dept
    int getCountOfTeacherOfDept(String deptID)throws Exception;

    /**
     * 分页查询教师
     * @param toPageNo
     * @return
     * @throws Exception
     */
    List<TeacherExpand> findByPaging(Integer toPageNo) throws Exception;


    /**
     * 根据名字院系模糊查询教师
     * @param teacherName
     * @param deptID
     * @return
     * @throws Exception
     */
    List<TeacherExpand> findTeacherByName(String teacherName,String deptID)throws Exception;

    //Get all teacherIDs by deptID
    List<String> getAllTeacherIDByDept(String deptID)throws Exception;

    //Get all teacher by deptID
    List<TeacherExpand> getAllTeacherOfDept(String deptID,Integer toPageNo)throws Exception;

    //Comment teacher by admin of dept
    void commentTeacher(String teacherID,String comment)throws Exception;

}
