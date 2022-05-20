package com.ly.service;

import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.SelectMapper;
import com.ly.mapper.UpdateMapper;
import com.ly.mapper.UpdateSql;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 16:45
 */
@Service
public class UpdateService {
    @Autowired
    private UpdateMapper updateMapper;

    public void updateStudent(Student student){
        updateMapper.updateStudent(student);
    }
    public void updateScore(Score score){
        updateMapper.updateScore(score);
    }
    public void updateCourse(Course course){
        updateMapper.updateCourse(course);
    }
    public void updateClass(Class c){
        updateMapper.updateClass(c);
    }
    public void updateTeacher(Teacher teacher){
        updateMapper.updateTeacher(teacher);
    }
    public void updateAdmin(Admin admin){
        updateMapper.updateAdmin(admin);
    }
}
