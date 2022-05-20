package com.ly.bean;

import com.alibaba.excel.annotation.ExcelProperty;

import java.io.Serializable;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-16 19:48
 */
public class StudentScoreCourse implements Serializable {
    @ExcelProperty(value = "学号",index = 0)
    private String student_id;
    @ExcelProperty(value = "姓名",index = 1)
    private String student_name;
    @ExcelProperty(value = "语文",index = 2)
    private String yu_wen;
    @ExcelProperty(value = "数学",index = 3)
    private String shu_xue;
    @ExcelProperty(value = "英语",index = 4)
    private String ying_yu;
    @ExcelProperty(value = "物理",index = 5)
    private String wu_li;
    @ExcelProperty(value = "化学",index = 6)
    private String hua_xue;
    @ExcelProperty(value = "生物",index = 7)
    private String sheng_wu;
    @ExcelProperty(value = "政治",index = 8)
    private String zheng_zhi;
    @ExcelProperty(value = "历史",index = 9)
    private String li_shi;
    @ExcelProperty(value = "地理",index = 10)
    private String di_li;
    @ExcelProperty(value = "总成绩",index = 11)
    private String sum_score;
    @ExcelProperty(value = "总排名",index = 12)
    private String rank_score;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getYu_wen() {
        return yu_wen;
    }

    public void setYu_wen(String yu_wen) {
        this.yu_wen = yu_wen;
    }

    public String getShu_xue() {
        return shu_xue;
    }

    public void setShu_xue(String shu_xue) {
        this.shu_xue = shu_xue;
    }

    public String getYing_yu() {
        return ying_yu;
    }

    public void setYing_yu(String ying_yu) {
        this.ying_yu = ying_yu;
    }

    public String getWu_li() {
        return wu_li;
    }

    public void setWu_li(String wu_li) {
        this.wu_li = wu_li;
    }

    public String getHua_xue() {
        return hua_xue;
    }

    public void setHua_xue(String hua_xue) {
        this.hua_xue = hua_xue;
    }

    public String getSheng_wu() {
        return sheng_wu;
    }

    public void setSheng_wu(String sheng_wu) {
        this.sheng_wu = sheng_wu;
    }

    public String getZheng_zhi() {
        return zheng_zhi;
    }

    public void setZheng_zhi(String zheng_zhi) {
        this.zheng_zhi = zheng_zhi;
    }

    public String getLi_shi() {
        return li_shi;
    }

    public void setLi_shi(String li_shi) {
        this.li_shi = li_shi;
    }

    public String getDi_li() {
        return di_li;
    }

    public void setDi_li(String di_li) {
        this.di_li = di_li;
    }

    public String getSum_score() {
        return sum_score;
    }

    public void setSum_score(String sum_score) {
        this.sum_score = sum_score;
    }

    public String getRank_score() {
        return rank_score;
    }

    public void setRank_score(String rank_score) {
        this.rank_score = rank_score;
    }

    @Override
    public String toString() {
        return "StudentScoreCourse{" +
                "student_id='" + student_id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", yu_wen='" + yu_wen + '\'' +
                ", shu_xue='" + shu_xue + '\'' +
                ", ying_yu='" + ying_yu + '\'' +
                ", wu_li='" + wu_li + '\'' +
                ", hua_xue='" + hua_xue + '\'' +
                ", sheng_wu='" + sheng_wu + '\'' +
                ", zheng_zhi='" + zheng_zhi + '\'' +
                ", li_shi='" + li_shi + '\'' +
                ", di_li='" + di_li + '\'' +
                ", sum_score='" + sum_score + '\'' +
                ", rank_score='" + rank_score + '\'' +
                '}';
    }
}
