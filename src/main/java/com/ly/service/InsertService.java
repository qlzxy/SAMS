package com.ly.service;

import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.InsertMapper;
import com.ly.mapper.SelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-10 16:45
 */
@Service
public class InsertService {
    @Autowired
    private InsertMapper insertMapper;

    public void insertStudent(Student student){
        insertMapper.insertStudent(student);
    }
    public void insertTeacher(Teacher teacher){
        insertMapper.insertTeacher(teacher);
    }
    public void insertClass(Class c){
        insertMapper.insertClass(c);
    }
    public void insertCourse(Course c){
        insertMapper.insertCourse(c);
    }
    public void insertAdmin(Admin admin){
        insertMapper.insertAdmin(admin);
    }

    public void insertAdminSecurity(Security security){
        insertMapper.insertAdminSecurity(security);
    }
    public void insertTeacherSecurity(Security security){
        insertMapper.insertTeacherSecurity(security);
    }
    public void insertStudentSecurity(Security security){
        insertMapper.insertStudentSecurity(security);
    }

    public void insertScore(Score score){
        insertMapper.insertScore(score);
    }


}
