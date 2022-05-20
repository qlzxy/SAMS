package com.ly.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.InsertMapper;
import com.ly.mapper.SelectMapper;
import com.ly.mapper.UpdateMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-11 8:58
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    private SelectMapper selectMapper;
    @Autowired
    private SelectService selectService;
    @Autowired
    private UpdateMapper updateMapper;
    @Autowired
    private InsertMapper insertMapper;
    @Test
    public void t(){
        List<Student> allStudent = selectMapper.findAllStudent();
        System.out.println(allStudent);
    }
    @Test
    public void tt(){
        Score s=new Score();
        s.setStudent_id(5);
        s.setCourse_id(1);
        s.setScore("100");
        insertMapper.insertScore(s);
    }
    @Test
    public void ttt()  {
        String s="id.2010101512";
        String[] split = s.split("\\.");
            System.out.println("split = " + split[0]);
            System.out.println("split = " + split[1]);
    }

    @Test
    public void t4(){
        List<Student> allStudent = selectMapper.findAllStudent();
        System.out.println("classes = " + allStudent);
    }
}
