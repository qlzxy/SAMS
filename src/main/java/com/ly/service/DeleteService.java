package com.ly.service;

import com.ly.bean.Admin;
import com.ly.bean.Student;
import com.ly.bean.Teacher;
import com.ly.mapper.DeleteMapper;
import com.ly.mapper.SelectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 16:45
 */
@Service
public class DeleteService {
    @Autowired
    private DeleteMapper deleteMapper;

    public void deleteScore(int student_id){
        deleteMapper.deleteScore(student_id);
    }
    public void deleteStudent(int student_id){
        deleteMapper.deleteStudent(student_id);
    }
    public void deleteTeacher(int teacher_id){
        deleteMapper.deleteTeacher(teacher_id);
    }
    public void deleteCourse(int course_id){
        deleteMapper.deleteCourse(course_id);
    }
    public void deleteClass(int class_id){
        deleteMapper.deleteClass(class_id);
    }


}
