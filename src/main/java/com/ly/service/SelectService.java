package com.ly.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.SelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public class SelectService {
    @Autowired
    private SelectMapper selectMapper;
    @Autowired
    private RedisTemplate<String, Object> rt;
    public List<HashMap<String,Object>> findById(int student_id){
        String key = "scorecoursestudent_" + student_id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (List<HashMap<String,Object>>) o.get(key) ;
        } else {
            List<HashMap<String, Object>> l = selectMapper.findById(student_id);
            o.set(key,l);
            return l;
        }
    }
    public Student findByStudent(int id){
        String key = "student_" + id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (Student) o.get(key) ;
        } else {
            Student s = selectMapper.findByStudent(id);
            o.set(key,s);
            return s;
        }
    }
    public Teacher findByTeacher( int id){
        String key = "teacher_" + id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (Teacher) o.get(key) ;
        } else {
            Teacher t = selectMapper.findByTeacher(id);
            o.set(key,t);
            return t;
        }
    }
    public  Admin findByAdmin(int id){
        String key = "admin_" + id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (Admin) o.get(key) ;
        } else {
            Admin a = selectMapper.findByAdmin(id);
            o.set(key,a);
            return a;
        }
    }
    public Class findByClass( int id){
        String key = "class_" + id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (Class) o.get(key) ;
        } else {
            Class c = selectMapper.findByClass(id);
            o.set(key,c);
            return c;
        }
    }
    public Course findByCourse( int id){
        String key = "course_" + id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (Course) o.get(key) ;
        } else {
            Course c = selectMapper.findByCourse(id);
            o.set(key,c);
            return c;
        }
    }
    public PageInfo<HashMap<String,Object>> findScore(int num,int size){
        String key = "scorecoursestudent_"+num+size;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<HashMap<String, Object>> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<HashMap<String, Object>>) o.get(key) ;
        } else {
            List<HashMap<String, Object>> l = selectMapper.findScore();
            pageInfo=new PageInfo<HashMap<String, Object>>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public List<HashMap<String,Object>> findScoreById(int student_id){
        String key = "scorecoursestudent_" + student_id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (List<HashMap<String,Object>>) o.get(key) ;
        } else {
            List<HashMap<String, Object>> l = selectMapper.findScoreById(student_id);
            o.set(key,l);
            return l;
        }
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
        String key = "scorecoursestudent_id_name"+num+size+student_id+student_name;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<HashMap<String, Object>> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<HashMap<String,Object>>) o.get(key) ;
        } else {
            List<HashMap<String, Object>> l = selectMapper.findScoreByIdName("%" + student_id + "%", "%" + student_name + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
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
        String key = "scorecoursestudent_like"+num+size+value;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<HashMap<String,Object>> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<HashMap<String,Object>>) o.get(key) ;
        } else {
            List<HashMap<String, Object>> l = selectMapper.findScoreByLike("%" + value + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    /**
     * 通过学生id查询学生成绩
     * @param student_id
     * @return
     */
    public List<Score> findScoreByIdStudentId(int student_id){
        String key = "score_id_student_id" + student_id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return  (List<Score>) o.get(key) ;
        } else {
            List<Score> l = selectMapper.findScoreByIdStudentId(student_id);
            o.set(key,l);
            return l;
        }
    }
    /**
     * 查询所有学生信息
     * @return
     */
    public PageInfo<Student> findAllStudent(int num,int size){
        String key = "student_all"+num+size;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Student> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Student>) o.get(key) ;
        } else {
            List<Student> l = selectMapper.findAllStudent();
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    /**
     * 查询还没有成绩的学生id和name
     * @return
     */
    public List<Student> findNoScoreStudent(){
        String key = "student_no_score";
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return  (List<Student>) o.get(key) ;
        } else {
            List<Student> l = selectMapper.findNoScoreStudent();
            o.set(key,l);
            return l;
        }
    }
    /**
     * 通过课程id查出课程的平均成绩
     * @param course_id
     * @return
     */
    public String findAverageScoreByCourse_id(int course_id){
        String key = "score_course_average_id"+course_id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return  (String) o.get(key) ;
        } else {
            String l = selectMapper.findAverageScoreByCourse_id(course_id);
            o.set(key,l);
            return l;
        }
    }
    /**
     * 查询班级教师和学生人数
     * @return
     */
    public List<Class> teacherStudentNumber(){
        String key = "class_teacher_student_number";
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return  (List<Class>) o.get(key) ;
        } else {
            List<Class> l = selectMapper.teacherStudentNumber();
            o.set(key,l);
            return l;
        }
    }
    /**
     * 查询所有班级
     * @param num
     * @param size
     * @return
     */
    public PageInfo<Class> findClassAll(int num ,int size){
        String key = "class_all"+num+size;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Class> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Class>) o.get(key) ;
        } else {
            List<Class> l = selectMapper.findClassAll();
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    /**
     * 查询所有课程
     * @param num
     * @param size
     * @return
     */
    public  PageInfo<Course> findCourseAll(int num ,int size){
        String key = "course_all"+num+size;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Course> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Course>) o.get(key) ;
        } else {
            List<Course> l = selectMapper.findCourseAll();
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    /**
     * 查询所有老师
     * @param num
     * @param size
     * @return
     */
    public PageInfo<Teacher> findTeacherAll(int num ,int size){
        String key = "teacher_all"+num+size;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Teacher> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=( PageInfo<Teacher> ) o.get(key) ;
        } else {
            List<Teacher> l = selectMapper.findTeacherAll();
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public Security findSecurityByEmail(int id){
        String key = "security_email"+id;
        ValueOperations<String, Object> o = rt.opsForValue();
        if (rt.hasKey(key)) {
            return (Security) o.get(key) ;
        } else {
            Security s = selectMapper.findSecurityByEmail(id);
            o.set(key,s);
            return s;
        }
    }
    //=================================课程、班级、教师======================================//
    public PageInfo<Course> findCourseByIdName(int num,int size,String course_id,String course_name){
        String key = "course_id_name"+num+size+course_id+course_name;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Course> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Course>) o.get(key) ;
        } else {
            List<Course> l = selectMapper.findCourseByIdName("%" + course_id + "%", "%" + course_name + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public PageInfo<Course> findCourseByLike(int num,int size,String value){
        String key = "course_like"+num+size+value;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Course> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Course>) o.get(key) ;
        } else {
            List<Course> l = selectMapper.findCourseByLike("%" + value + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public PageInfo<Teacher> findTeacherByIdName(int num,int size,String teacher_id,String teacher_name){
        String key = "teacher_id_name"+num+size+teacher_id+teacher_name;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();

        PageInfo<Teacher> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=( PageInfo<Teacher>) o.get(key) ;
        } else {
            List<Teacher> l = selectMapper.findTeacherByIdName("%" + teacher_id + "%", "%" + teacher_name + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public PageInfo<Teacher> findTeacherByLike(int num,int size,String value){
        String key = "teacher_like"+num+size+value;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Teacher> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=( PageInfo<Teacher>) o.get(key) ;
        } else {
            List<Teacher> l = selectMapper.findTeacherByLike("%" + value + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public PageInfo<Class> findClassByIdName(int num,int size,String class_id,String class_name){
        String key = "class_like"+num+size+class_id+class_name;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Class> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Class>) o.get(key) ;
        } else {
            List<Class> l= selectMapper.findClassByIdName("%" + class_id + "%", "%" + class_name + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public PageInfo<Class> findClassByLike(int num,int size,String value){
        String key = "class_like"+num+size+value;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Class> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Class>) o.get(key) ;
        } else {
            List<Class> l= selectMapper.findClassByLike("%" + value + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public PageInfo<Student> findStudentByIdName(int num,int size,String student_id,String student_name){
        String key = "student_id_name"+num+size+student_id+student_name;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Student> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Student>) o.get(key) ;
        } else {
            List<Student> l = selectMapper.findStudentByIdName("%" + student_id + "%", "%" + student_name + "%");
            pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
    public PageInfo<Student> findStudentByLike(int num,int size,String value){
        String key = "student_id_name"+num+size+value;
        PageHelper.startPage(num,size);
        ValueOperations<String, Object> o = rt.opsForValue();
        PageInfo<Student> pageInfo;
        if (rt.hasKey(key)) {
            pageInfo=(PageInfo<Student>) o.get(key) ;
        } else {
            List<Student> l = selectMapper.findStudentByLike("%" + value + "%");
             pageInfo=new PageInfo<>(l);
            o.set(key,pageInfo);
        }
        return pageInfo;
    }
}
