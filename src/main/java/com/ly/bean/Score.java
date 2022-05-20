package com.ly.bean;

import java.io.Serializable;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 16:34
 */
public class Score implements Serializable {
    private int score_id;
    private int  student_id;
    private int  course_id;
    private String  score;

    public int getScore_id() {
        return score_id;
    }

    public void setScore_id(int score_id) {
        this.score_id = score_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "score_id=" + score_id +
                ", student_id=" + student_id +
                ", course_id=" + course_id +
                ", score='" + score + '\'' +
                '}';
    }
}
