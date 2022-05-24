package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.service.SelectService;
import com.ly.service.UpdateService;
import com.ly.utils.FtpUtils;
import com.ly.utils.DataTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private UpdateService updateService;
    @Autowired
    private SelectService selectService;
    @Autowired
    private Ftp ftp ;
    @Autowired
    private DataTypeUtils dtu;
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

    //==========================用户信息 修改密码 个人中心===========================//
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

    @RequestMapping("/person/{role}")
    public String person(@PathVariable("role") String role, @RequestParam("id") String id ,
                         @RequestParam("name") String name , @RequestParam("sex") String sex ,
                         @RequestParam("email") String email , ModelMap model,HttpServletRequest request) throws NoSuchMethodException {
        String birth = request.getParameter("birth");
        String phone = request.getParameter("phone");
        Map<String,String> m=new HashMap();
        m.put("role",role);
        m.put("name",name);
        m.put("id",id);
        m.put("email",email);
        m.put("birth",birth);
        m.put("phone",phone);
        m.put("sex",sex);
        Map<String, String> ssm = dtu.savePerson(role, m);
        model.addAttribute("map",ssm);
        return "person";
    }

    @RequestMapping("/saveimg/{id}")
    public String searchMember(ModelMap model,@PathVariable("id") String id,@RequestParam("file") MultipartFile file){
        try {
            String oldFileName="";
            String newFileName = "";
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
                Map<String, Object> m = dtu.objectToMap(byAdmin, "admin");
                model.addAttribute("map",m);
            }else if(byStudent!=null){
                byStudent.setStudent_img("http://"+ftp.getHostname()+"/image/"+newFileName);
                updateService.updateStudent(byStudent);
                Map<String, Object> m = dtu.objectToMap(byStudent, "student");
                model.addAttribute("map",m);
            }else if(byTeacher!=null){
                byTeacher.setTeacher_img("http://"+ftp.getHostname()+"/image/"+newFileName);
                updateService.updateTeacher(byTeacher);
                Map<String, Object> m = dtu.objectToMap(byTeacher, "teacher");
                model.addAttribute("map",m);
            }else{
                model.addAttribute("message","头像上传失败");
            }
        }catch (Exception e){
            model.addAttribute("message","头像上传失败");
        }
        return "person";
    }
}
