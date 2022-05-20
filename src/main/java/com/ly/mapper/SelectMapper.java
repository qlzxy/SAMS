package com.ly.mapper;

import com.ly.bean.*;
import com.ly.bean.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 16:38
 */
@Repository
@Mapper
public interface SelectMapper {

    /**
     * 查询个人成绩
     * @param student_id
     * @return
     */
    @Select("select t_score.student_id,score,course_name,t_student.student_name from " +
            "t_score inner join t_course on t_score.course_id=t_course.course_id inner join t_student on t_student.student_id=t_score.student_id where t_score.student_id=#{student_id}")
    public List<HashMap<String,Object>> findById(@Param("student_id") int student_id);

    /**
     * 通过id查询，判断来自那个表
     * @param id
     * @return
     */
    @Select("select *from t_student where student_id=#{id}")
    public Student findByStudent(@Param("id") int id);
    @Select("select *from t_teacher where teacher_id=#{id}")
    public Teacher findByTeacher(@Param("id") int id);
    @Select("select *from t_class where class_id=#{id}")
    public Class findByClass(@Param("id") int id);
    @Select("select *from t_course where course_id=#{id}")
    public Course findByCourse(@Param("id") int id);
    @Select("select *from t_admin where admin_id=#{id}")
    public Admin findByAdmin(@Param("id") int id);

    @Select("select *from t_student")
    public List<Student> findAllStudent();

    @Select("select t_score.student_id,t_student.student_name," +
            "max(case course_name when '语文' then score else 0 end) yu_wen," +
            "max(case course_name when '数学' then score else 0 end) shu_xue," +
            "max(case course_name when '英语' then score else 0 end) ying_yu," +
            "max(case course_name when '物理' then score else 0 end) wu_li," +
            "max(case course_name when '化学' then score else 0 end) hua_xue," +
            "max(case course_name when '生物' then score else 0 end) sheng_wu, " +
            "max(case course_name when '政治' then score else 0 end) zheng_zhi, " +
            "max(case course_name when '历史' then score else 0 end) li_shi, " +
            "max(case course_name when '地理' then score else 0 end) di_li, " +
            "sum(score) sum_score, " +
            "row_number() over(order by sum(score) desc) rank_score "+
            "from t_score inner join t_course on t_score.course_id=t_course.course_id " +
            "inner join t_student on t_student.student_id=t_score.student_id group by " +
            "t_score.student_id order by sum_score desc")
    public List<HashMap<String,Object>> findScore();

    @Select("select t_score.student_id,t_student.student_name," +
            "max(case course_name when '语文' then score else 0 end) yu_wen," +
            "max(case course_name when '数学' then score else 0 end) shu_xue," +
            "max(case course_name when '英语' then score else 0 end) ying_yu," +
            "max(case course_name when '物理' then score else 0 end) wu_li," +
            "max(case course_name when '化学' then score else 0 end) hua_xue," +
            "max(case course_name when '生物' then score else 0 end) sheng_wu, " +
            "max(case course_name when '政治' then score else 0 end) zheng_zhi, " +
            "max(case course_name when '历史' then score else 0 end) li_shi, " +
            "max(case course_name when '地理' then score else 0 end) di_li, " +
            "sum(score) sum_score, " +
            "row_number() over(order by sum(score) desc) rank_score "+
            "from t_score inner join t_course on t_score.course_id=t_course.course_id " +
            "inner join t_student on t_student.student_id=t_score.student_id  where t_student.student_id=#{student_id} group by t_score.student_id order by sum_score desc")
    public List<HashMap<String,Object>> findScoreById(@Param("student_id") int student_id);

    @Select("select t_score.student_id,t_student.student_name," +
            "max(case course_name when '语文' then score else 0 end) yu_wen," +
            "max(case course_name when '数学' then score else 0 end) shu_xue," +
            "max(case course_name when '英语' then score else 0 end) ying_yu," +
            "max(case course_name when '物理' then score else 0 end) wu_li," +
            "max(case course_name when '化学' then score else 0 end) hua_xue," +
            "max(case course_name when '生物' then score else 0 end) sheng_wu, " +
            "max(case course_name when '政治' then score else 0 end) zheng_zhi, " +
            "max(case course_name when '历史' then score else 0 end) li_shi, " +
            "max(case course_name when '地理' then score else 0 end) di_li, " +
            "sum(score) sum_score, " +
            "row_number() over(order by sum(score) desc) rank_score "+
            "from t_score inner join t_course on t_score.course_id=t_course.course_id " +
            "inner join t_student on t_student.student_id=t_score.student_id  " +
            "where t_student.student_id like #{value} or student_name like #{value} " +
            "group by t_score.student_id order by sum_score desc")
    public List<HashMap<String,Object>> findScoreByLike(@Param("value") String value);

    @Select("select t_score.student_id,t_student.student_name," +
            "max(case course_name when '语文' then score else 0 end) yu_wen," +
            "max(case course_name when '数学' then score else 0 end) shu_xue," +
            "max(case course_name when '英语' then score else 0 end) ying_yu," +
            "max(case course_name when '物理' then score else 0 end) wu_li," +
            "max(case course_name when '化学' then score else 0 end) hua_xue," +
            "max(case course_name when '生物' then score else 0 end) sheng_wu, " +
            "max(case course_name when '政治' then score else 0 end) zheng_zhi, " +
            "max(case course_name when '历史' then score else 0 end) li_shi, " +
            "max(case course_name when '地理' then score else 0 end) di_li, " +
            "sum(score) sum_score, " +
            "row_number() over(order by sum(score) desc) rank_score "+
            "from t_score inner join t_course on t_score.course_id=t_course.course_id " +
            "inner join t_student on t_student.student_id=t_score.student_id  where t_student.student_id like #{student_id} and student_name like #{student_name} group by t_score.student_id order by sum_score desc")
    public List<HashMap<String,Object>> findScoreByIdName(@Param("student_id") String student_id,@Param("student_name") String student_name);

    @Select("select *from t_score where student_id=#{student_id}")
    public List<Score> findScoreByIdStudentId(@Param("student_id") int student_id);



    /**
     * 查询还没有成绩的学生id
     * @return
     */
    @Select("select student_id,student_name from t_student where student_id not in(select t_score.student_id from t_score)")
    public List<Student> findNoScoreStudent();

    /**
     * 通过课程id查出课程的平均成绩
     * @param course_id
     * @return
     */
    @Select("select cast(avg(score) as decimal(10,2)) avg_score from t_score where course_id=#{course_id} ")
    public String findAverageScoreByCourse_id(@Param("course_id") int course_id);

    @Select("select student_num,class_name,teacher_num from t_class group by class_name ")
    public List<Class> teacherStudentNumber();
    //    =====================================教师，班级，课程==========================//

    @Select("select *from t_class")
    public List<Class> findClassAll();

    @Select("select *from t_course")
    public List<Course> findCourseAll();

    @Select("select *from t_teacher")
    public List<Teacher> findTeacherAll();

    @Select("select *from t_security where student_id=#{id} or admin_id=#{id} or teacher_id=#{id}")
    public Security findSecurityByEmail(@Param("id") int id);

    @Select("select *from t_class where class_id like #{value} or class_name like #{value}")
    public List<Class> findClassByLike(@Param("value") String value);
    @Select("select *from t_class where class_id like #{class_id} and class_name like #{class_name}")
    public List<Class> findClassByIdName(@Param("class_id") String class_id,@Param("class_name") String class_name);

    @Select("select *from t_course where course_id like #{value} or course_name like #{value}")
    public List<Course> findCourseByLike(@Param("value") String value);
    @Select("select *from t_course where course_id like #{course_id} and course_name like #{course_name}")
    public List<Course> findCourseByIdName(@Param("course_id") String course_id,@Param("course_name") String course_name);

    @Select("select *from t_teacher where teacher_id like #{value} or teacher_name like #{value}")
    public List<Teacher> findTeacherByLike(@Param("value") String value);
    @Select("select *from t_teacher where teacher_id like #{teacher_id} and teacher_name like #{teacher_name}")
    public List<Teacher> findTeacherByIdName(@Param("teacher_id") String teacher_id,@Param("teacher_name") String teacher_name);

    @Select("select *from t_student where student_id like #{value} or student_name like #{value}")
    public List<Student> findStudentByLike(@Param("value") String value);
    @Select("select *from t_student where student_id like #{student_id} and student_name like #{student_name}")
    public List<Student> findStudentByIdName(@Param("student_id") String student_id,@Param("student_name") String student_name);

}
