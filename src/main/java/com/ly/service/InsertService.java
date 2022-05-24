package com.ly.service;

import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.InsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InsertService {
    @Autowired
    private InsertMapper insertMapper;
    @Autowired
    private RedisTemplate<String,Object> rt;
    public void insertStudent(Student student){
        insertMapper.insertStudent(student);
        Set<String> keys = rt.keys("student_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertTeacher(Teacher teacher){
        insertMapper.insertTeacher(teacher);
        Set<String> keys = rt.keys("teacher_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertClass(Class c){
        insertMapper.insertClass(c);
        Set<String> keys = rt.keys("class_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertCourse(Course c){
        insertMapper.insertCourse(c);
        Set<String> keys = rt.keys("course_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertAdmin(Admin a){
        insertMapper.insertAdmin(a);
        Set<String> keys = rt.keys("admin_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertAdminSecurity(Security security){
        insertMapper.insertAdminSecurity(security);
        Set<String> keys = rt.keys("security_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertTeacherSecurity(Security security){
        insertMapper.insertTeacherSecurity(security);
        Set<String> keys = rt.keys("security_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertStudentSecurity(Security security){
        insertMapper.insertStudentSecurity(security);
        Set<String> keys = rt.keys("security_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void insertScore(Score score){
        insertMapper.insertScore(score);
        Set<String> keys = rt.keys("score_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }


}
