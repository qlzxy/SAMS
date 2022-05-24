package com.ly.bean;

import java.io.Serializable;

public class Security implements Serializable {
    private int security_id;
    private int student_id;
    private int admin_id;
    private int teacher_id;
    private String security_question;
    private String security_answer;

    public int getSecurity_id() {
        return security_id;
    }

    public void setSecurity_id(int security_id) {
        this.security_id = security_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getSecurity_question() {
        return security_question;
    }

    public void setSecurity_question(String security_question) {
        this.security_question = security_question;
    }

    public String getSecurity_answer() {
        return security_answer;
    }

    public void setSecurity_answer(String security_answer) {
        this.security_answer = security_answer;
    }

    @Override
    public String toString() {
        return "Security{" +
                "security_id=" + security_id +
                ", student_id=" + student_id +
                ", admin_id=" + admin_id +
                ", teacher_id=" + teacher_id +
                ", security_question='" + security_question + '\'' +
                ", security_answer='" + security_answer + '\'' +
                '}';
    }
}
