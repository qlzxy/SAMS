package com.ly.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.mapper.SelectMapper;
import com.ly.service.ExcelListener;
import com.ly.utils.EmailUtils;
import com.ly.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private SelectMapper selectMapper;
    @Autowired
    private SelectService selectService;
    @Autowired
    private ExcelListener excelListener;
    @Autowired
    private EmailUtils myUtil;
    //========================导入excel=====================//
    @RequestMapping("/scoreImport")
    public String scoreImport(HttpServletRequest request,Model model, @RequestParam("file") MultipartFile file) {
        ExcelReaderBuilder read = null;
        try {
            read = EasyExcel.read(file.getInputStream(), StudentScoreCourse.class, excelListener);
            ExcelReaderSheetBuilder sheet = read.sheet();
            sheet.doRead();
            model.addAttribute("pageInfo",selectService.findScore(1, 6));
            return "student";
        } catch (IOException e) {
            request.getSession().setAttribute("message","导入失败");
            return "student";
        }
    }
    @RequestMapping("/stuImport")
    public String stuImport(HttpServletRequest request,Model model, @RequestParam("file") MultipartFile file){
        ExcelReaderBuilder read = null;
        try {
            read = EasyExcel.read(file.getInputStream(), Student.class, excelListener);
            ExcelReaderSheetBuilder sheet = read.sheet();
            sheet.doRead();
            model.addAttribute("pageInfo",selectService.findAllStudent(1, 6));
            return "stu";
        } catch (IOException e) {
            request.getSession().setAttribute("message","导入失败");
            return "stu";
        }
    }
    @RequestMapping("/teacherImport")
    public String teacherImport(HttpServletRequest request,Model model, @RequestParam("file") MultipartFile file){
        ExcelReaderBuilder read = null;
        try {
            read = EasyExcel.read(file.getInputStream(), Teacher.class, excelListener);
            ExcelReaderSheetBuilder sheet = read.sheet();
            sheet.doRead();
            model.addAttribute("pageInfo",selectService.findTeacherAll(1, 6));
            return "teacher";
        } catch (IOException e) {
            request.getSession().setAttribute("message","导入失败");
            return "teacher";
        }
    }
    @RequestMapping("/classImport")
    public String classImport(HttpServletRequest request,Model model, @RequestParam("file") MultipartFile file){
        ExcelReaderBuilder read = null;
        try {
            read = EasyExcel.read(file.getInputStream(), Class.class, excelListener);
            ExcelReaderSheetBuilder sheet = read.sheet();
            sheet.doRead();
            model.addAttribute("pageInfo",selectService.findClassAll(1, 6));
            return "class";
        } catch (IOException e) {
            request.getSession().setAttribute("message","导入失败");
            return "class";
        }
    }
    @RequestMapping("/courseImport")
    public String courseImport(HttpServletRequest request,Model model, @RequestParam("file") MultipartFile file){
        ExcelReaderBuilder read = null;
        try {
            read = EasyExcel.read(file.getInputStream(), Course.class, excelListener);
            ExcelReaderSheetBuilder sheet = read.sheet();
            sheet.doRead();
            model.addAttribute("pageInfo",selectService.findCourseAll(1, 6));
            return "course";
        } catch (IOException e) {
            request.getSession().setAttribute("message","导入失败");
            return "course";
        }
    }
    //========================导出excel=====================//
    @RequestMapping("/scoreExport")
    @ResponseBody
    public String scoreExport(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            response.setHeader("Content-Disposition","attachment; filename="+format+".xlsx");
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(),StudentScoreCourse.class);
            ExcelWriterSheetBuilder sheet = write.sheet();
            List list = EmailUtils.listByListHashMap(selectMapper.findScore());
            sheet.doWrite(list);
            return "导出成功";
        }catch (Exception e){
            return "导出失败";
        }
    }
    @RequestMapping("/stuExport")
    @ResponseBody
    public String stuExport(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            response.setHeader("Content-Disposition","attachment; filename=student"+format+".xlsx");
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(),Student.class);
            ExcelWriterSheetBuilder sheet = write.sheet();
            List<Student> allStudent = selectMapper.findAllStudent();
            sheet.doWrite(allStudent);
            return "导出成功";
        }catch (Exception e){
            return "导出失败";
        }
    }
    @RequestMapping("/teacherExport")
    @ResponseBody
    public String teacherExport(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            response.setHeader("Content-Disposition","attachment; filename=teacher"+format+".xlsx");
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(),Teacher.class);
            ExcelWriterSheetBuilder sheet = write.sheet();
            List<Teacher> teacherAll = selectMapper.findTeacherAll();
            sheet.doWrite(teacherAll);
            return "导出成功";
        }catch (Exception e){
            return "导出失败";
        }
    }
    @RequestMapping("/classExport")
    @ResponseBody
    public String classExport(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            response.setHeader("Content-Disposition","attachment; filename=class"+format+".xlsx");
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(),Class.class);
            ExcelWriterSheetBuilder sheet = write.sheet();
            List<Class> classAll = selectMapper.findClassAll();
            sheet.doWrite(classAll);
            return "导出成功";
        }catch (Exception e){
            return "导出失败";
        }
    }
    @RequestMapping("/courseExport")
    @ResponseBody
    public String courseExport(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String format = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            response.setHeader("Content-Disposition","attachment; filename=course"+format+".xlsx");
            ExcelWriterBuilder write = EasyExcel.write(response.getOutputStream(),Course.class);
            ExcelWriterSheetBuilder sheet = write.sheet();
            List<Course> courseAll = selectMapper.findCourseAll();
            sheet.doWrite(courseAll);
            return "导出成功";
        }catch (Exception e){
            return "导出失败";
        }
    }



    /**
     * email参数是收信人的邮箱 ,emailMsg是发送的信息内容
     * @throws Exception
     */
    @RequestMapping("/Emain")
    public String sendMail2(HttpServletRequest request, Model model) throws Exception{
        String answer = request.getParameter("answer");
        String question = request.getParameter("question");
        String id = request.getParameter("id");
        String e_mail=request.getParameter("email");
        String password="";
        Student byStudent = selectService.findByStudent(Integer.parseInt(id));
        Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(id));
        Admin byAdmin = selectService.findByAdmin(Integer.parseInt(id));

        if(byStudent!=null){
            if (byStudent.getStudent_email().equals(e_mail)){
                Security securityByEmail = selectService.findSecurityByEmail(Integer.parseInt(id));
                if(securityByEmail.getSecurity_answer().equals(answer)&&securityByEmail.getSecurity_question().equals(question)){
                    password=byStudent.getStudent_password();
                }else {
                    model.addAttribute("message","密保问题错误");
                }
            }else {
                model.addAttribute("message","邮箱不正确");
            }
        }else if(byTeacher!=null){
            if (byTeacher.getTeacher_email().equals(e_mail)){
                Security securityByEmail = selectService.findSecurityByEmail(Integer.parseInt(id));
                if(securityByEmail.getSecurity_answer().equals(answer)&&securityByEmail.getSecurity_question().equals(question)){
                    password=byTeacher.getTeacher_password();
                }else {
                    model.addAttribute("message","密保问题错误");
                }
            }else {
                model.addAttribute("message","邮箱不正确");
            }
        }else if(byAdmin!=null){
            if (byAdmin.getAdmin_email().equals(e_mail)){
                Security securityByEmail = selectService.findSecurityByEmail(Integer.parseInt(id));
                if(securityByEmail.getSecurity_answer().equals(answer)&&securityByEmail.getSecurity_question().equals(question)){
                    password=byAdmin.getAdmin_password();
                }else {
                    model.addAttribute("message","密保问题错误");
                }
            }else {
                model.addAttribute("message","邮箱不正确");
            }
        }else {
            model.addAttribute("message","学工号不正确");
        }
        if(!"".equals(password)){
            myUtil.sendSimpleMail(e_mail,"学生成绩管理系统回复","尊敬的"+e_mail+"用户:\n您的密码已经找回,记得及时保存密码噢!\n密码为:"+password);
            model.addAttribute("message","成功");
        }
       return "return";
    }

}
