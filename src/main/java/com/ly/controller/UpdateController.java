package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.service.SelectService;
import com.ly.service.UpdateService;
import com.ly.utils.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-11 15:44
 */
@Controller
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private UpdateService updateService;
    @Autowired
    private SelectService selectService;

    @Autowired
    private Ftp ftp ;
    /**
     * 编辑学生成绩
     * @param request
     * @param model
     * @return
     */
    //==========================学生\教师\课程\班级修改===========================//
    @RequestMapping("/studentEdit/{role}")
    public String studentEdit(@PathVariable("role") String role, HttpServletRequest request, Model model)  {
        String edit_student_id = request.getParameter("edit_student_id");
        String edit_student_name = request.getParameter("edit_student_name");
        String edit_YuWen = request.getParameter("edit_YuWen");
        String edit_ShuXue = request.getParameter("edit_ShuXue");
        String edit_YingYu = request.getParameter("edit_YingYu");
        String edit_WuLi = request.getParameter("edit_WuLi");
        String edit_HuaXue = request.getParameter("edit_HuaXue");
        String edit_ShengWu = request.getParameter("edit_ShengWu");
        String edit_ZhengZhi = request.getParameter("edit_ZhengZhi");
        String edit_LiShi = request.getParameter("edit_LiShi");
        String edit_DiLi = request.getParameter("edit_DiLi");
        Student byStudent = selectService.findByStudent(Integer.parseInt(edit_student_id));
        byStudent.setStudent_name(edit_student_name);
        updateService.updateStudent(byStudent);
        List<Score> scoreByIdStudentId = selectService.findScoreByIdStudentId(Integer.parseInt(edit_student_id));
        for(Score s:scoreByIdStudentId){
            if(s.getCourse_id()==1){
                s.setScore(edit_YuWen);
            }
            if(s.getCourse_id()==2){
                s.setScore(edit_ShuXue);
            }
            if(s.getCourse_id()==3){
                s.setScore(edit_YingYu);
            }
            if(s.getCourse_id()==4){
                s.setScore(edit_WuLi);
            }
            if(s.getCourse_id()==5){
                s.setScore(edit_HuaXue);
            }
            if(s.getCourse_id()==6){
                s.setScore(edit_ShengWu);
            }
            if(s.getCourse_id()==7){
                s.setScore(edit_ZhengZhi);
            }
            if(s.getCourse_id()==8){
                s.setScore(edit_LiShi);
            }
            if(s.getCourse_id()==9){
                s.setScore(edit_DiLi);
            }
            updateService.updateScore(s);
        }
        PageInfo<HashMap<String, Object>> score = selectService.findScore(1, 6);
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",score);
        return "student";
    }
    @RequestMapping("/editstu/{role}")
    public String editstu(@PathVariable("role") String role, HttpServletRequest request, Model model)  {
        String edit_student_id = request.getParameter("edit_stu_id");
        String edit_student_name = request.getParameter("edit_stu_name");
        String edit_student_sex = request.getParameter("edit_stu_sex");
        String edit_student_birth = request.getParameter("edit_stu_birth");
        String edit_student_phone = request.getParameter("edit_stu_phone");
        String edit_student_email = request.getParameter("edit_stu_email");
        String edit_student_password = request.getParameter("edit_stu_password");
        String edit_stu_role = request.getParameter("edit_stu_role");

        Student byStudent = selectService.findByStudent(Integer.parseInt(edit_student_id));
        byStudent.setStudent_name(edit_student_name);
        byStudent.setStudent_sex(edit_student_sex);
        byStudent.setStudent_birth(edit_student_birth);
        byStudent.setStudent_phone(edit_student_phone);
        byStudent.setStudent_password(edit_student_password);
        byStudent.setStudent_email(edit_student_email);
        byStudent.setStudent_role(edit_stu_role);
        updateService.updateStudent(byStudent);
        PageInfo<Student> allStudent = selectService.findAllStudent(1, 6);
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",allStudent);
        return "stu";
    }
    @RequestMapping("/editteacher/{role}")
    public String editteacher(@PathVariable("role") String role, HttpServletRequest request, Model model)  {
        String edit_teacher_id = request.getParameter("edit_teacher_id");
        String edit_teacher_name = request.getParameter("edit_teacher_name");
        String edit_teacher_sex = request.getParameter("edit_teacher_sex");
        String edit_teacher_birth = request.getParameter("edit_teacher_birth");
        String edit_teacher_phone = request.getParameter("edit_teacher_phone");
        String edit_teacher_email = request.getParameter("edit_teacher_email");
        String edit_teacher_password = request.getParameter("edit_teacher_password");
        String edit_teacher_role = request.getParameter("edit_teacher_role");
        Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(edit_teacher_id));
        byTeacher.setTeacher_id(Integer.parseInt(edit_teacher_id));
        byTeacher.setTeacher_name(edit_teacher_name);
        byTeacher.setTeacher_sex(edit_teacher_sex);
        byTeacher.setTeacher_birth(edit_teacher_birth);
        byTeacher.setTeacher_phone(edit_teacher_phone);
        byTeacher.setTeacher_password(edit_teacher_password);
        byTeacher.setTeacher_email(edit_teacher_email);
        byTeacher.setTeacher_role(edit_teacher_role);
        updateService.updateTeacher(byTeacher);
        PageInfo<Teacher> teacherAll = selectService.findTeacherAll(1, 6);
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",teacherAll);
        return "teacher";
    }
    @RequestMapping("/editclass/{role}")
    public String editclass(@PathVariable("role") String role, HttpServletRequest request, Model model)  {
        String edit_class_id = request.getParameter("edit_class_id");
        String edit_class_name = request.getParameter("edit_class_name");
        String edit_teacher_num = request.getParameter("edit_teacher_num");
        String edit_student_num = request.getParameter("edit_student_num");
        Class byClass = selectService.findByClass(Integer.parseInt(edit_class_id));
        byClass.setClass_id(Integer.parseInt(edit_class_id));
        byClass.setClass_name(edit_class_name);
        byClass.setTeacher_num(Integer.parseInt(edit_teacher_num));
        byClass.setStudent_num(Integer.parseInt(edit_student_num));
        updateService.updateClass(byClass);
        PageInfo<Class> classAll = selectService.findClassAll(1, 6);
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",classAll);
        return "class";
    }
    @RequestMapping("/editcourse/{role}")
    public String editcourse(@PathVariable("role") String role, HttpServletRequest request, Model model)  {
        String edit_course_id = request.getParameter("edit_course_id");
        String edit_course_name = request.getParameter("edit_course_name");
        String edit_course_hour = request.getParameter("edit_course_hour");
        Course byCourse = selectService.findByCourse(Integer.parseInt(edit_course_id));
        byCourse.setCourse_id(Integer.parseInt(edit_course_id));
        byCourse.setCourse_name(edit_course_name);
        byCourse.setCourse_hour(edit_course_hour);
        updateService.updateCourse(byCourse);
        PageInfo<Course> courseAll = selectService.findCourseAll(1, 6);
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",courseAll);
        return "course";
    }




    @RequestMapping("/person/{role}")
    public String person(@PathVariable("role") String role, @RequestParam("id") String id ,
                         @RequestParam("name") String name , @RequestParam("sex") String sex ,
                         @RequestParam("email") String email , ModelMap model,HttpServletRequest request){
        String birth = request.getParameter("birth");
        String phone = request.getParameter("phone");
        if("admin".equals(role)){
            Admin admin=new Admin();
            admin.setAdmin_id(Integer.parseInt(id));
            admin.setAdmin_sex(sex);
            admin.setAdmin_email(email);
            admin.setAdmin_name(name);
            updateService.updateAdmin(admin);
            model.addAttribute("modelrole","管理员");
            model.addAttribute("img",selectService.findByAdmin(Integer.parseInt(id)).getAdmin_img());
        }
        if("teacher".equals(role)){
            Teacher teacher=new Teacher();
            teacher.setTeacher_name(name);
            teacher.setTeacher_id(Integer.parseInt(id));
            teacher.setTeacher_email(email);
            teacher.setTeacher_birth(birth);
            teacher.setTeacher_phone(phone);
            teacher.setTeacher_sex(sex);
            updateService.updateTeacher(teacher);
            model.addAttribute("modelrole","教师");
            model.addAttribute("img",selectService.findByTeacher(Integer.parseInt(id)).getTeacher_img());
        }
        if("student".equals(role)){
            Student student=new Student();
            student.setStudent_id(Integer.parseInt(id));
            student.setStudent_name(name);
            student.setStudent_email(email);
            student.setStudent_birth(birth);
            student.setStudent_phone(phone);
            student.setStudent_sex(sex);
            updateService.updateStudent(student);
            model.addAttribute("modelrole","学生");
            model.addAttribute("img",selectService.findByStudent(Integer.parseInt(id)).getStudent_img());
        }
        model.addAttribute("role",role);
        model.addAttribute("id",Integer.parseInt(id));
        model.addAttribute("name",name);
        model.addAttribute("sex",sex);
        model.addAttribute("email",email);
        model.addAttribute("birth",birth);
        model.addAttribute("phone",phone);
        return "person";
    }

    @RequestMapping("/changePassword/{role}/{id}")
    public String changePassword(ModelMap model,@PathVariable("role") String role,@PathVariable("id") String id,@RequestParam("password")String password,@RequestParam("newpassword")String newpassword){
        if ("admin".equals(role)){
            Admin byAdmin = selectService.findByAdmin(Integer.parseInt(id));
            if(byAdmin.getAdmin_password().equals(password)){
                byAdmin.setAdmin_password(newpassword);
                updateService.updateAdmin(byAdmin);
                model.addAttribute("role",role);
                model.addAttribute("message","修改成功");
            }else {
                model.addAttribute("message","原密码错误");
            }
        }else if ("student".equals(role)){
            Student byStudent = selectService.findByStudent(Integer.parseInt(id));
            if(byStudent.getStudent_password().equals(password)){
                byStudent.setStudent_password(newpassword);
                updateService.updateStudent(byStudent);
                model.addAttribute("role",role);
                model.addAttribute("message","修改成功");
            }else {
                model.addAttribute("message","原密码错误");
            }
        }else if ("teacher".equals(role)){
            Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(id));
            if(byTeacher.getTeacher_password().equals(password)){
                byTeacher.setTeacher_password(newpassword);
                updateService.updateTeacher(byTeacher);
                model.addAttribute("role",role);
                model.addAttribute("message","修改成功");
            }else {
                model.addAttribute("message","原密码错误");
            }
        }else {
            model.addAttribute("message","修改失败");
        }
        return "changePassword";
    }

    @RequestMapping("/saveimg/{id}")
    public String searchMember(ModelMap model,@PathVariable("id") String id,@RequestParam("file") MultipartFile file,HttpServletResponse response,HttpServletRequest request){
        try {
            String oldFileName="";
            String newFileName="";
            if (!file.isEmpty()) {
                InputStream inputStream = file.getInputStream();
                oldFileName = file.getOriginalFilename();
                String[] split = oldFileName.split("\\.");
                String date=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                newFileName=date+"."+split[1];
                FtpUtils.uploadFile(newFileName,inputStream);
            }
            Admin byAdmin = selectService.findByAdmin(Integer.parseInt(id));
            Student byStudent = selectService.findByStudent(Integer.parseInt(id));
            Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(id));

            if(byAdmin!=null){
                byAdmin.setAdmin_img("http://"+ftp.getHostname()+"/image/"+newFileName);
                updateService.updateAdmin(byAdmin);
                model.addAttribute("img",byAdmin.getAdmin_img());
                model.addAttribute("id",Integer.parseInt(id));
                model.addAttribute("name",byAdmin.getAdmin_name());
                model.addAttribute("sex",byAdmin.getAdmin_sex());
                model.addAttribute("email",byAdmin.getAdmin_email());
                model.addAttribute("modelrole","管理员");
            }else if(byStudent!=null){
                byStudent.setStudent_img("http://"+ftp.getHostname()+"/image/"+newFileName);
                updateService.updateStudent(byStudent);
                model.addAttribute("id",Integer.parseInt(id));
                model.addAttribute("name",byStudent.getStudent_name());
                model.addAttribute("sex",byStudent.getStudent_sex());
                model.addAttribute("email",byStudent.getStudent_email());
                model.addAttribute("birth",byStudent.getStudent_birth());
                model.addAttribute("phone",byStudent.getStudent_phone());
                model.addAttribute("img",byStudent.getStudent_img());
                model.addAttribute("modelrole","学生");
            }else if(byTeacher!=null){
                byTeacher.setTeacher_img("http://"+ftp.getHostname()+"/image/"+newFileName);
                updateService.updateTeacher(byTeacher);
                model.addAttribute("id",Integer.parseInt(id));
                model.addAttribute("name",byTeacher.getTeacher_name());
                model.addAttribute("sex",byTeacher.getTeacher_sex());
                model.addAttribute("email",byTeacher.getTeacher_email());
                model.addAttribute("birth",byTeacher.getTeacher_birth());
                model.addAttribute("phone",byTeacher.getTeacher_phone());
                model.addAttribute("img",byTeacher.getTeacher_img());
                model.addAttribute("modelrole","教师");
            }else{
                model.addAttribute("message","修改失败");
            }
        }catch (Exception e){
            model.addAttribute("message","修改失败");
        }
        return "person";
    }
}
