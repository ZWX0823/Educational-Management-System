package com.springmvc.service;

import com.springmvc.po.CurriculumInfo;
import com.springmvc.po.Timetable;

import java.util.List;

public interface CurriculumService {

    //Get curriculum info of one specialty_Year and one term
    List<Timetable> getCurriculum(String specialty_YearID,String term)throws Exception;

    //Make simple curriculum info turn into detail curriculum
    List<CurriculumInfo> getDetailCurriculum(List<Timetable> timetableList)throws Exception;
}
