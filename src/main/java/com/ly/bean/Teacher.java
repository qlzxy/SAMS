package com.ly.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 15:58
 */
public class Teacher implements Serializable {
    @ExcelProperty(value = "教师编号",index = 0)
    private int teacher_id;
    @ExcelProperty(value = "教师性别",index = 1)
    private String teacher_sex;
    @ExcelProperty(value = "教师姓名",index = 2)
    private String teacher_name;
    @ExcelProperty(value = "教师电话",index = 3)
    private String teacher_phone;
    @ExcelProperty(value = "教师邮箱",index = 4)
    private String teacher_email;
    @ExcelProperty(value = "出生日期",index = 5)
    private String teacher_birth;
    @ExcelProperty(value = "教师密码",index = 6)
    private String teacher_password;

    @ExcelProperty(value = "课程编号",index = 7)
    private int course_id;
    @ExcelProperty(value = "教师头像",index = 8)
    private String teacher_img;
    @ExcelProperty(value = "角色",index = 9)
    private String teacher_role;

    public String getTeacher_role() {
        return teacher_role;
    }

    public void setTeacher_role(String teacher_role) {
        this.teacher_role = teacher_role;
    }
    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_sex() {
        return teacher_sex;
    }

    public void setTeacher_sex(String teacher_sex) {
        this.teacher_sex = teacher_sex;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getTeacher_phone() {
        return teacher_phone;
    }

    public void setTeacher_phone(String teacher_phone) {
        this.teacher_phone = teacher_phone;
    }

    public String getTeacher_email() {
        return teacher_email;
    }

    public void setTeacher_email(String teacher_email) {
        this.teacher_email = teacher_email;
    }

    public String getTeacher_birth() {
        return teacher_birth;
    }

    public void setTeacher_birth(String teacher_birth) {
        this.teacher_birth = teacher_birth;
    }

    public String getTeacher_img() {
        return teacher_img;
    }

    public void setTeacher_img(String teacher_img) {
        this.teacher_img = teacher_img;
    }

    public String getTeacher_password() {
        return teacher_password;
    }

    public void setTeacher_password(String teacher_password) {
        this.teacher_password = teacher_password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id=" + teacher_id +
                ", teacher_sex='" + teacher_sex + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_phone='" + teacher_phone + '\'' +
                ", teacher_email='" + teacher_email + '\'' +
                ", teacher_birth='" + teacher_birth + '\'' +
                ", teacher_password='" + teacher_password + '\'' +
                ", course_id=" + course_id +
                ", teacher_img='" + teacher_img + '\'' +
                ", teacher_role='" + teacher_role + '\'' +
                '}';
    }
}
