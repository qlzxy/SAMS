package com.ly.bean;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-12 9:21
 */
public class Course implements Serializable {
    @ExcelProperty(value = "课程编号",index = 0)
    private int course_id;
    @ExcelProperty(value = "课程时长",index = 1)
    private String course_hour;
    @ExcelProperty(value = "课程名称",index = 2)
    private String course_name;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }


    public String getCourse_hour() {
        return course_hour;
    }

    public void setCourse_hour(String course_hour) {
        this.course_hour = course_hour;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", course_hour='" + course_hour + '\'' +
                ", course_name='" + course_name + '\'' +
                '}';
    }
}
