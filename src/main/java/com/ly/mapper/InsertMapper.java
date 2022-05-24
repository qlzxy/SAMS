package com.ly.mapper;

import com.ly.bean.*;
import com.ly.bean.Class;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InsertMapper {

    @Insert("insert into t_student values(#{student_id},#{class_id},#{student_sex},#{student_name},#{student_birth},#{student_phone},#{student_password},#{student_email},#{student_img},#{student_role})")
    public void insertStudent(Student student);
    @Insert("insert into t_teacher values(" +
            "  #{teacher_id}" +
            ", #{teacher_sex}" +
            ", #{teacher_name}" +
            ", #{teacher_phone}"+
            ", #{teacher_email}"+
            ", #{teacher_birth}"+
            ", #{teacher_img}" +
            ", #{teacher_password}"+
            ", #{course_id}"+
            ", #{teacher_role}"+
            ")")
    public void insertTeacher(Teacher teacher);
    @Insert("insert into t_admin values(" +
            "  #{admin_id}" +
            ", #{admin_sex}" +
            ", #{admin_name}" +
            ", #{admin_password}"+
            ", #{admin_email}" +
            ", #{admin_img}" +
            ", #{admin_role}" +
            ")")
    public void insertAdmin(Admin admin);
    @Insert("insert into t_course values(" +
            "  #{course_id}" +
            ", #{course_hour}" +
            ", #{course_name}" +
            ")")
    public void insertCourse(Course course);
    @Insert("insert into t_score(student_id,course_id,score) values(" +
            "  #{student_id}" +
            ", #{course_id}" +
            ", #{score}" +
            ")")
    public void insertScore(Score score);
    @Insert("insert into t_class values(" +
            "   #{class_id}" +
            ",  #{student_num}" +
            ",  #{class_name}" +
            ",  #{teacher_num}" +
            ")")
    public void insertClass(Class c);
    @Insert("insert into t_security(admin_id,security_question,security_answer) values(" +
            " #{admin_id}" +
            ", #{security_question}"+
            ", #{security_answer}" +
            ")")
    public void insertAdminSecurity(Security security);
    @Insert("insert into t_security(teacher_id,security_question,security_answer) values(" +
            " #{teacher_id}" +
            ", #{security_question}"+
            ", #{security_answer}" +
            ")")
    public void insertTeacherSecurity(Security security);
    @Insert("insert into t_security(student_id,security_question,security_answer) values(" +
            " #{student_id}" +
            ", #{security_question}"+
            ", #{security_answer}" +
            ")")
    public void insertStudentSecurity(Security security);
}
