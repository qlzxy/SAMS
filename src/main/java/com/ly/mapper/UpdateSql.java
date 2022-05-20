package com.ly.mapper;

import com.ly.bean.*;
import com.ly.bean.Class;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-14 15:33
 */
public class UpdateSql {
    public String updateStudent(Student student) {
        return new SQL() {{
            UPDATE("t_student");
            if (student.getStudent_password() != null) {
                SET("student_password = #{student_password}");
            }
            if ("".equals(String.valueOf(student.getClass_id()))) {
                SET("class_id = #{class_id}");
            }
            if (student.getStudent_birth() != null) {
                SET("student_birth = #{student_birth}");
            }
            if (student.getStudent_email() != null) {
                SET("student_email = #{student_email}");
            }
            if (student.getStudent_img() != null) {
                SET("student_img = #{student_img}");
            }
            if (student.getStudent_name() != null) {
                SET("student_name = #{student_name}");
            }
            if (student.getStudent_phone() != null) {
                SET("student_phone = #{student_phone}");
            }
            if (student.getStudent_sex() != null) {
                SET("student_sex = #{student_sex}");
            }
            if (student.getStudent_role() != null) {
                SET("student_role = #{student_role}");
            }
            WHERE("student_id = #{student_id}");
        }}.toString();
    }
    public String updateTeacher(Teacher teacher) {
        return new SQL() {{
            UPDATE("t_teacher");
            if (teacher.getTeacher_password() != null) {
                SET("teacher_password = #{teacher_password}");
            }
            if (teacher.getTeacher_birth() != null) {
                SET("teacher_birth = #{teacher_birth}");
            }
            if (teacher.getTeacher_email() != null) {
                SET("teacher_email = #{teacher_email}");
            }
            if (teacher.getTeacher_img() != null) {
                SET("teacher_img = #{teacher_img}");
            }
            if (teacher.getTeacher_name() != null) {
                SET("teacher_name = #{teacher_name}");
            }
            if (teacher.getTeacher_phone() != null) {
                SET("teacher_phone = #{teacher_phone}");
            }
            if (teacher.getTeacher_sex() != null) {
                SET("teacher_sex = #{teacher_sex}");
            }
            if (teacher.getTeacher_role() != null) {
                SET("teacher_role = #{teacher_role}");
            }
            WHERE("teacher_id = #{teacher_id}");
        }}.toString();
    }
    public String updateAdmin(Admin admin) {
        return new SQL() {{
            UPDATE("t_admin");
            if (admin.getAdmin_password() != null) {
                SET("admin_password = #{admin_password}");
            }
            if (admin.getAdmin_email() != null) {
                SET("admin_email = #{admin_email}");
            }
            if (admin.getAdmin_img() != null) {
                SET("admin_img = #{admin_img}");
            }
            if (admin.getAdmin_name() != null) {
                SET("admin_name = #{admin_name}");
            }
            if (admin.getAdmin_sex() != null) {
                SET("admin_sex = #{admin_sex}");
            }
            if (admin.getAdmin_role() != null) {
                SET("admin_role = #{admin_role}");
            }
            WHERE("admin_id = #{admin_id}");
        }}.toString();
    }
    public String updateScore(Score score) {
        return new SQL() {{
            UPDATE("t_score");
            if ("".equals(String.valueOf(score.getCourse_id()))) {
                SET("course_id = #{course_id}");
            }
            if ("".equals(String.valueOf(score.getStudent_id()))) {
                SET("student_id = #{student_id}");
            }
            if (score.getScore() != null) {
                SET("score = #{score}");
            }
            WHERE("score_id = #{score_id}");
        }}.toString();
    }
    public String updateCourse(Course course) {
        return new SQL() {{
            UPDATE("t_course");
            if (course.getCourse_hour() != null) {
                SET("course_hour = #{course_hour}");
            }
            if (course.getCourse_name() != null) {
                SET("course_name = #{course_name}");
            }
            WHERE("course_id = #{course_id}");
        }}.toString();
    }
    public String updateClass(Class c) {
        return new SQL() {{
            UPDATE("t_class");
            if (c.getClass_name() != null) {
                SET("class_name = #{class_name}");
            }
            if (""+c.getStudent_num()!= null) {
                SET("student_num = #{student_num}");
            }
            if (""+c.getTeacher_num()!= null) {
                SET("teacher_num = #{teacher_num}");
            }
            WHERE("class_id = #{class_id}");
        }}.toString();
    }
}
