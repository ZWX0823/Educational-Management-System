package com.springmvc.GA.Course;

import java.util.List;

/**
 * 学生分组/专业
 */
public class Group {
    private final String groupId;
    private final int groupSize;
    private final List<String> moduleIds;

    public Group(String groupId,int groupSize,List<String> moduleIds){
        this.groupId = groupId;
        this.groupSize = groupSize;
        this.moduleIds = moduleIds;
    }

    public String getGroupId() {
        return groupId;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public List<String> getModuleIds() {
        return moduleIds;
    }
}
