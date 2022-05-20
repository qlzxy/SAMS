package com.ly.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.SelectMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 16:45
 */
@Service
public class SelectService {
    @Autowired
    private SelectMapper selectMapper;

    public List<HashMap<String,Object>> findById(int student_id){
        return selectMapper.findById(student_id);
    }

    public Student findByStudent(int id){
        return selectMapper.findByStudent(id);
    }
    public Teacher findByTeacher( int id){
        return selectMapper.findByTeacher(id);
    }
    public Admin findByAdmin(int id){
        return selectMapper.findByAdmin(id);
    }
    public Class findByClass( int id){
        return selectMapper.findByClass(id);
    }
    public Course findByCourse( int id){
        return selectMapper.findByCourse(id);
    }
    public PageInfo<HashMap<String,Object>> findScore(int num,int size){
        PageHelper.startPage(num,size);
        List<HashMap<String, Object>> score=selectMapper.findScore();
        PageInfo<HashMap<String, Object>> pageInfo=new PageInfo<HashMap<String, Object>>(score);
        return pageInfo;
    }

    public List<HashMap<String,Object>> findScoreById(int student_id){
        return selectMapper.findScoreById(student_id);
    }

    /**
     * 通过id和name模糊查询学生成绩
     * @param num
     * @param size
     * @param student_id
     * @param student_name
     * @return
     */
    public PageInfo<HashMap<String,Object>> findScoreByIdName(int num,int size,String student_id,String student_name){
        PageHelper.startPage(num,size);
        List<HashMap<String, Object>> scoreByIdName = selectMapper.findScoreByIdName("%" + student_id + "%", "%" + student_name + "%");
        PageInfo<HashMap<String, Object>> pageInfo=new PageInfo<HashMap<String, Object>>(scoreByIdName);
        return pageInfo;
    }

    /**
     * 通过id或者name模糊查询学生成绩
     * @param num
     * @param size
     * @param value
     * @return
     */
    public PageInfo<HashMap<String,Object>> findScoreByLike(int num,int size,String value){
        PageHelper.startPage(num,size);
        List<HashMap<String, Object>> scoreByIdName = selectMapper.findScoreByLike("%" + value + "%");
        PageInfo<HashMap<String, Object>> pageInfo=new PageInfo<HashMap<String, Object>>(scoreByIdName);
        return pageInfo;
    }

    /**
     * 通过学生id查询学生成绩
     * @param student_id
     * @return
     */
    public List<Score> findScoreByIdStudentId(int student_id){
        return  selectMapper.findScoreByIdStudentId(student_id);
    }

    /**
     * 查询所有学生信息
     * @return
     */
    public PageInfo<Student> findAllStudent(int num,int size){
        PageHelper.startPage(num,size);
        List<Student> allStudent = selectMapper.findAllStudent();
        PageInfo<Student> pageInfo=new PageInfo<Student>(allStudent);
        return pageInfo;
    }

    /**
     * 查询还没有成绩的学生id和name
     * @return
     */
    public List<Student> findNoScoreStudent(){
      return  selectMapper.findNoScoreStudent();
    }

    /**
     * 通过课程id查出课程的平均成绩
     * @param course_id
     * @return
     */
    public String findAverageScoreByCourse_id(int course_id){
        return selectMapper.findAverageScoreByCourse_id(course_id);
    }

    /**
     * 查询班级教师和学生人数
     * @return
     */
    public List<Class> teacherStudentNumber(){
        return selectMapper.teacherStudentNumber();
    }
    /**
     * 查询所有班级
     * @param num
     * @param size
     * @return
     */
    public PageInfo<Class> findClassAll(int num ,int size){
        PageHelper.startPage(num,size);
        List<Class> classAll = selectMapper.findClassAll();
        PageInfo<Class> pageInfo=new PageInfo<Class>(classAll);
        return pageInfo;
    }

    /**
     * 查询所有课程
     * @param num
     * @param size
     * @return
     */
    public  PageInfo<Course> findCourseAll(int num ,int size){
        PageHelper.startPage(num,size);
        List<Course> courseAll = selectMapper.findCourseAll();
        PageInfo<Course> pageInfo=new PageInfo<Course>(courseAll);
        return pageInfo;
    }
    /**
     * 查询所有老师
     * @param num
     * @param size
     * @return
     */
    public PageInfo<Teacher> findTeacherAll(int num ,int size){
        PageHelper.startPage(num,size);
        List<Teacher> teacherAll = selectMapper.findTeacherAll();
        PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teacherAll);
        return pageInfo;
    }

    public Security findSecurityByEmail(int id){
        return selectMapper.findSecurityByEmail(id);
    }

    //=================================课程、班级、教师======================================//
    public PageInfo<Course> findCourseByIdName(int num,int size,String course_id,String course_name){
        PageHelper.startPage(num,size);
        List<Course> courseByIdName = selectMapper.findCourseByIdName("%" + course_id + "%", "%" + course_name + "%");
        PageInfo<Course> pageInfo=new PageInfo<Course>(courseByIdName);
        return pageInfo;
    }
    public PageInfo<Course> findCourseByLike(int num,int size,String value){
        PageHelper.startPage(num,size);
        List<Course> courseByLike = selectMapper.findCourseByLike("%" + value + "%");
        PageInfo<Course> pageInfo=new PageInfo<Course>(courseByLike);
        return pageInfo;
    }

    public PageInfo<Teacher> findTeacherByIdName(int num,int size,String teacher_id,String teacher_name){
        PageHelper.startPage(num,size);
        List<Teacher> teacherByIdName = selectMapper.findTeacherByIdName("%" + teacher_id + "%", "%" + teacher_name + "%");
        PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teacherByIdName);
        return pageInfo;
    }
    public PageInfo<Teacher> findTeacherByLike(int num,int size,String value){
        PageHelper.startPage(num,size);
        List<Teacher> teacherByLike = selectMapper.findTeacherByLike("%" + value + "%");
        PageInfo<Teacher> pageInfo=new PageInfo<Teacher>(teacherByLike);
        return pageInfo;
    }

    public PageInfo<Class> findClassByIdName(int num,int size,String class_id,String class_name){
        PageHelper.startPage(num,size);
        List<Class> classByIdName = selectMapper.findClassByIdName("%" + class_id + "%", "%" + class_name + "%");
        PageInfo<Class> pageInfo=new PageInfo<Class>(classByIdName);
        return pageInfo;
    }
    public PageInfo<Class> findClassByLike(int num,int size,String value){
        PageHelper.startPage(num,size);
        List<Class> classByLike = selectMapper.findClassByLike("%" + value + "%");
        PageInfo<Class> pageInfo=new PageInfo<Class>(classByLike);
        return pageInfo;
    }

    public PageInfo<Student> findStudentByIdName(int num,int size,String student_id,String student_name){
        PageHelper.startPage(num,size);
        List<Student> studentByIdName = selectMapper.findStudentByIdName("%" + student_id + "%", "%" + student_name + "%");
        PageInfo<Student> pageInfo=new PageInfo<Student>(studentByIdName);
        return pageInfo;
    }
    public PageInfo<Student> findStudentByLike(int num,int size,String value){
        PageHelper.startPage(num,size);
        List<Student> studentByLike = selectMapper.findStudentByLike("%" + value + "%");
        PageInfo<Student> pageInfo=new PageInfo<Student>(studentByLike);
        return pageInfo;
    }


}
