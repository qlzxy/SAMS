package com.ly.service;

import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.SelectMapper;
import com.ly.mapper.UpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    @Autowired
    private UpdateMapper um;
    @Autowired
    private SelectMapper sm;
    @Autowired
    private RedisTemplate<String,Object> rt;
    public void updateStudent(Student s){
        um.updateStudent(s);
        String key = "student_"+s.getStudent_id();
        ValueOperations o = rt.opsForValue();
        if (rt.hasKey(key)) {
            o.set(key, sm.findByStudent(s.getStudent_id()));
        }
    }
    public void updateScore(Score s){
        um.updateScore(s);
        String key = "score_"+s.getStudent_id();;
        ValueOperations o = rt.opsForValue();
        if (rt.hasKey(key)) {
            o.set(key, sm.findScoreByIdStudentId(s.getScore_id()));
        }
    }
    public void updateCourse(Course c){
        um.updateCourse(c);
        String key = "course_"+c.getCourse_id();
        ValueOperations o = rt.opsForValue();
        if (rt.hasKey(key)) {
            o.set(key, sm.findByCourse(c.getCourse_id()));
        }
    }
    public void updateClass(Class c){
        um.updateClass(c);
        String key = "class_"+c.getClass_id();
        ValueOperations o = rt.opsForValue();
        if (rt.hasKey(key)) {
            o.set(key, sm.findByClass(c.getClass_id()));
        }
    }
    public void updateTeacher(Teacher t){
        um.updateTeacher(t);
        String key = "teacher_"+t.getTeacher_id();
        ValueOperations o = rt.opsForValue();
        if (rt.hasKey(key)) {
            o.set(key, sm.findByTeacher(t.getTeacher_id()));
        }
    }
    public void updateAdmin(Admin a){
        um.updateAdmin(a);
        String key = "admin_"+a.getAdmin_id();
        ValueOperations o = rt.opsForValue();
        if (rt.hasKey(key)) {
            o.set(key, sm.findByAdmin(a.getAdmin_id()));
        }
    }
}
