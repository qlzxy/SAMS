package com.ly.bean;


import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 15:58
 */
public class Student implements Serializable {
    @ExcelProperty(value = "学生编号",index = 0)
    private int student_id;
    @ExcelProperty(value = "学生性别",index = 1)
    private String student_sex;
    @ExcelProperty(value = "学生姓名",index = 2)
    private String student_name;
    @ExcelProperty(value = "出生日期",index = 3)
    private String student_birth;
    @ExcelProperty(value = "学生电话",index = 4)
    private String student_phone;
    @ExcelProperty(value = "学生密码",index = 5)
    private String student_password;
    @ExcelProperty(value = "学生邮箱",index = 6)
    private String student_email;

    @ExcelProperty(value = "学生头像",index = 7)
    private String student_img;
    @ExcelProperty(value = "班级编号",index = 8)
    private int class_id;
    @ExcelProperty(value = "角色",index = 9)
    private String student_role;

    public String getStudent_role() {
        return student_role;
    }

    public void setStudent_role(String student_role) {
        this.student_role = student_role;
    }
    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getStudent_sex() {
        return student_sex;
    }

    public void setStudent_sex(String student_sex) {
        this.student_sex = student_sex;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_birth() {
        return student_birth;
    }

    public void setStudent_birth(String student_birth) {
        this.student_birth = student_birth;
    }

    public String getStudent_phone() {
        return student_phone;
    }

    public void setStudent_phone(String student_phone) {
        this.student_phone = student_phone;
    }

    public String getStudent_password() {
        return student_password;
    }

    public void setStudent_password(String student_password) {
        this.student_password = student_password;
    }

    public String getStudent_email() {
        return student_email;
    }

    public void setStudent_email(String student_email) {
        this.student_email = student_email;
    }

    public String getStudent_img() {
        return student_img;
    }

    public void setStudent_img(String student_img) {
        this.student_img = student_img;
    }

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_sex='" + student_sex + '\'' +
                ", student_name='" + student_name + '\'' +
                ", student_birth='" + student_birth + '\'' +
                ", student_phone='" + student_phone + '\'' +
                ", student_password='" + student_password + '\'' +
                ", student_email='" + student_email + '\'' +
                ", student_img='" + student_img + '\'' +
                ", class_id=" + class_id +
                ", student_role='" + student_role + '\'' +
                '}';
    }
}
