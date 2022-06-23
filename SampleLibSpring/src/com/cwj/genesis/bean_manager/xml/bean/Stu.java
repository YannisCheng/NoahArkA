package com.cwj.genesis.bean_manager.xml.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * com.cwj.genesis.mult
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-25 18:40
 */
public class Stu {

    private String[] courses;
    private List<String> list;
    private Map<String, String> maps;
    private Set<String> sets;
    private List<Course> coursesList;

    public void setCoursesList(List<Course> coursesList) {
        this.coursesList = coursesList;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMaps(Map<String, String> maps) {
        this.maps = maps;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "courses=" + Arrays.toString(courses) +
                ", list=" + list +
                ", maps=" + maps +
                ", sets=" + sets +
                ", coursesList=" + coursesList +
                '}';
    }
}
