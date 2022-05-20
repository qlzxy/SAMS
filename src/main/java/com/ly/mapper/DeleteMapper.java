package com.ly.mapper;

import com.ly.bean.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-12 10:51
 */
@Repository
@Mapper
public interface DeleteMapper {

    @Delete("delete from t_score where student_id=#{student_id}")
    public void deleteScore(@Param("student_id") int student_id);

    @Delete("delete from t_student where student_id=#{student_id}")
    public void deleteStudent(@Param("student_id") int student_id);

    @Delete("delete from t_teacher where teacher_id=#{teacher_id}")
    public void deleteTeacher(@Param("teacher_id") int teacher_id);

    @Delete("delete from t_course where course_id=#{course_id}")
    public void deleteCourse(@Param("course_id") int course_id);

    @Delete("delete from t_class where class_id=#{class_id}")
    public void deleteClass(@Param("class_id") int class_id);

}
