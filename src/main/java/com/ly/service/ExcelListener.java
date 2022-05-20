package com.ly.service;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.InsertMapper;
import com.ly.mapper.SelectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-16 15:42
 */
@Component
public class ExcelListener extends AnalysisEventListener{

    @Autowired
    private SelectMapper selectMapper;

    @Autowired
    private InsertMapper insertMapper;

    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        if (o instanceof Student) {
            Student s=(Student) o;
            insertMapper.insertStudent(s);
        }else if(o instanceof Teacher){
            Teacher t=(Teacher) o;
            insertMapper.insertTeacher(t);
        }else if(o instanceof Course){
            Course c=(Course) o;
            insertMapper.insertCourse(c);
        }else if(o instanceof Class){
            Class c=(Class) o;
            insertMapper.insertClass(c);
        }else if(o instanceof StudentScoreCourse){
            StudentScoreCourse ssc=((StudentScoreCourse) o);
            Score score = new Score();
            for (int i = 1; i < 10; i++) {
                if (i == 1 && !"".equals(ssc.getYu_wen())) {
                    score.setCourse_id(1);
                    score.setScore(ssc.getYu_wen());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 2 && !"".equals(ssc.getShu_xue())) {
                    score.setCourse_id(2);
                    score.setScore(ssc.getShu_xue());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 3 && !"".equals(ssc.getYing_yu())) {
                    score.setCourse_id(3);
                    score.setScore(ssc.getYing_yu());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 4 && !"".equals(ssc.getWu_li())) {
                    score.setCourse_id(4);
                    score.setScore(ssc.getWu_li());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 5 && !"".equals(ssc.getHua_xue())) {
                    score.setCourse_id(5);
                    score.setScore(ssc.getHua_xue());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 6 && !"".equals(ssc.getSheng_wu())) {
                    score.setCourse_id(6);
                    score.setScore(ssc.getSheng_wu());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 7 && !"".equals(ssc.getZheng_zhi())) {
                    score.setCourse_id(7);
                    score.setScore(ssc.getZheng_zhi());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 8 && !"".equals(ssc.getLi_shi())) {
                    score.setCourse_id(8);
                    score.setScore(ssc.getLi_shi());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
                if (i == 9 && !"".equals(ssc.getDi_li())) {
                    score.setCourse_id(9);
                    score.setScore(ssc.getDi_li());
                    score.setStudent_id(Integer.parseInt(ssc.getStudent_id()));
                    insertMapper.insertScore(score);
                }
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
