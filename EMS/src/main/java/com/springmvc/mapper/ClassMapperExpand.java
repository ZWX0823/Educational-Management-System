package com.springmvc.mapper;

import com.springmvc.po.ClassExpand;
import com.springmvc.po.PagingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassMapperExpand {
    //Get classes of dept by paging
    List<ClassExpand> getClassesOfDeptByPaging(@Param("pagingVO")PagingVO pagingVO, @Param("deptID") String deptID);

    //Get classes of specialty by paging
    List<ClassExpand> getClassesOfSpecialtyByPaging(@Param("pagingVO")PagingVO pagingVO, @Param("specialty_Year") String specialty_Year);

}
