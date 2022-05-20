package com.ly.bean;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-12 9:17
 */
public class Class implements Serializable {
    @ExcelProperty(value = "班级编号",index = 0)
    private int class_id;
    @ExcelProperty(value = "班级名称",index = 1)
    private String class_name;
    @ExcelProperty(value = "学生人数",index = 2)
    private int student_num;
    @ExcelProperty(value = "教师人数",index = 3)
    private int teacher_num;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getStudent_num() {
        return student_num;
    }

    public void setStudent_num(int student_num) {
        this.student_num = student_num;
    }

    public int getTeacher_num() {
        return teacher_num;
    }

    public void setTeacher_num(int teacher_num) {
        this.teacher_num = teacher_num;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return "Class{" +
                "class_id=" + class_id +
                ", student_num=" + student_num +
                ", teacher_num=" + teacher_num +
                ", class_name='" + class_name + '\'' +
                '}';
    }
}
