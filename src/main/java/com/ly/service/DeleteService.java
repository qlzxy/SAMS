package com.ly.service;

import com.ly.mapper.DeleteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class DeleteService {
    @Autowired
    private DeleteMapper dm;
    @Autowired
    private RedisTemplate<String,Object> rt;
    public void deleteScore(int student_id){
        dm.deleteScore(student_id);
        Set<String> keys = rt.keys("score_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void deleteStudent(int student_id){
        dm.deleteStudent(student_id);
        Set<String> keys = rt.keys("student_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void deleteTeacher(int teacher_id){
        dm.deleteTeacher(teacher_id);
        Set<String> keys = rt.keys("teacher_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void deleteCourse(int course_id){
        dm.deleteCourse(course_id);
        Set<String> keys = rt.keys("average_*");
        System.out.println(keys);
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
    public void deleteClass(int class_id){
        dm.deleteClass(class_id);
        Set<String> keys = rt.keys("class_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
    }
}
