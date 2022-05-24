package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.service.InsertService;
import com.ly.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/insert")
public class InsertController {

    @Autowired
    private InsertService insertService;
    @Autowired
    private SelectService selectService;

    @RequestMapping("/admin")
    public String insertAdmin(HttpServletRequest request, HttpServletResponse response, Model model) {
        String admin_id = request.getParameter("admin_id");
        String admin_sex = request.getParameter("admin_sex");
        String admin_email = request.getParameter("admin_email");
        String security_answer = request.getParameter("security_answer");
        String security_question = request.getParameter("security_question");
        String admin_password = request.getParameter("admin_password");
        try {
            Admin admin = new Admin();
            admin.setAdmin_email(admin_email);
            admin.setAdmin_id(Integer.parseInt(admin_id));
            admin.setAdmin_password(admin_password);
            admin.setAdmin_sex(admin_sex);
            admin.setAdmin_role("admin");
            insertService.insertAdmin(admin);
            Security security = new Security();
            security.setSecurity_question(security_question);
            security.setSecurity_answer(security_answer);
            security.setAdmin_id(Integer.parseInt(admin_id));
            insertService.insertAdminSecurity(security);
            model.addAttribute("message","注册成功");
        } catch (Exception e) {
            model.addAttribute("message","注册失败");
        }
        return "register";
    }

    @RequestMapping("/addStudentScore")
    public String studentEdit(HttpServletRequest request, ModelMap model)  {
        String add_student_id = request.getParameter("add_student_id");
        String add_YuWen = request.getParameter("add_YuWen");
        String add_ShuXue = request.getParameter("add_ShuXue");
        String add_YingYu = request.getParameter("add_YingYu");
        String add_WuLi = request.getParameter("add_WuLi");
        String add_HuaXue = request.getParameter("add_HuaXue");
        String add_ShengWu = request.getParameter("add_ShengWu");
        String add_ZhengZhi = request.getParameter("add_ZhengZhi");
        String add_LiShi = request.getParameter("add_LiShi");
        String add_DiLi = request.getParameter("add_DiLi");
        try {
            Score s=new Score();
            if(selectService.findScoreByIdStudentId(Integer.parseInt(add_student_id)).size()!=0){
                model.addAttribute("message","该同学已有成绩，如有需要请点编辑");
            }else {
                for(int i=1;i<10;i++){
                    if(i==1&&!"".equals(add_YuWen)){
                        s.setCourse_id(1);
                        s.setScore(add_YuWen);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==2&&!"".equals(add_ShuXue)){
                        s.setCourse_id(2);
                        s.setScore(add_ShuXue);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==3&&!"".equals(add_YingYu)){
                        s.setCourse_id(3);
                        s.setScore(add_YingYu);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==4&&!"".equals(add_WuLi)){
                        s.setCourse_id(4);
                        s.setScore(add_WuLi);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==5&&!"".equals(add_HuaXue)){
                        s.setCourse_id(5);
                        s.setScore(add_HuaXue);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==6&&!"".equals(add_ShengWu)){
                        s.setCourse_id(6);
                        s.setScore(add_ShengWu);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==7&&!"".equals(add_ZhengZhi)){
                        s.setCourse_id(7);
                        s.setScore(add_ZhengZhi);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==8&&!"".equals(add_LiShi)){
                        s.setCourse_id(8);
                        s.setScore(add_LiShi);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }
                    if(i==9&&!"".equals(add_DiLi)){
                        s.setCourse_id(9);
                        s.setScore(add_DiLi);
                        s.setStudent_id(Integer.parseInt(add_student_id));
                        insertService.insertScore(s);
                    }

                }
            }
        }catch (Exception e){
            System.out.println("添加成绩出错"+e.getMessage());
        }
        PageInfo<HashMap<String, Object>> score = selectService.findScore(1, 6);
        model.addAttribute("pageInfo",score);
        return "student";
    }

    //===========================学生\老师\课程\班级===========================//
    @RequestMapping("/addstu/{role}")
    public String addstu(@PathVariable("role") String role, HttpServletRequest request, ModelMap model){
        String add_stu_id = request.getParameter("add_stu_id");
        String add_stu_name = request.getParameter("add_stu_name");
        String add_stu_sex = request.getParameter("add_stu_sex");
        String add_stu_birth = request.getParameter("add_stu_birth");
        String add_stu_email = request.getParameter("add_stu_email");
        String add_stu_phone = request.getParameter("add_stu_phone");
        String add_stu_password = request.getParameter("add_stu_password");
        String add_stu_classid = request.getParameter("add_stu_classid");
        String add_stu_role = request.getParameter("add_stu_role");

        Student s=new Student();
        s.setStudent_name(add_stu_name);
        s.setStudent_password(add_stu_password);
        s.setStudent_phone(add_stu_phone);
        s.setStudent_email(add_stu_email);
        s.setStudent_birth(add_stu_birth);
        s.setStudent_id(Integer.parseInt(add_stu_id));
        s.setStudent_sex(add_stu_sex);
        s.setStudent_role(add_stu_role);
        s.setClass_id(Integer.parseInt(add_stu_classid));

        insertService.insertStudent(s);
        request.getSession().setAttribute("role",role);
        PageInfo<Student> allStudent = selectService.findAllStudent(1, 6);
        model.addAttribute("pageInfo",allStudent);
        return "stu";
    }
    @RequestMapping("/addteacher/{role}")
    public String addteacher(@PathVariable("role") String role, HttpServletRequest request,ModelMap model){
        String add_teacher_id = request.getParameter("add_teacher_id");
        String add_teacher_name = request.getParameter("add_teacher_name");
        String add_teacher_sex = request.getParameter("add_teacher_sex");
        String add_teacher_birth = request.getParameter("add_teacher_birth");
        String add_teacher_email = request.getParameter("add_teacher_email");
        String add_teacher_phone = request.getParameter("add_teacher_phone");
        String add_teacher_password = request.getParameter("add_teacher_password");
        String add_teacher_courseid = request.getParameter("add_teacher_courseid");
        String add_teacher_role = request.getParameter("add_teacher_role");

        Teacher t=new Teacher();
        t.setTeacher_name(add_teacher_name);
        t.setTeacher_password(add_teacher_password);
        t.setTeacher_phone(add_teacher_phone);
        t.setTeacher_email(add_teacher_email);
        t.setTeacher_birth(add_teacher_birth);
        t.setTeacher_id(Integer.parseInt(add_teacher_id));
        t.setTeacher_sex(add_teacher_sex);
        t.setCourse_id(Integer.parseInt(add_teacher_courseid));
        t.setTeacher_role(add_teacher_role);

        insertService.insertTeacher(t);
        request.getSession().setAttribute("role",role);
        PageInfo<Teacher> teacherAll = selectService.findTeacherAll(1, 6);
        model.addAttribute("pageInfo",teacherAll);
        return "teacher";
    }

    @RequestMapping("/addcourse/{role}")
    public String addcourse(@PathVariable("role") String role,HttpServletRequest request, ModelMap model){
        String add_course_id = request.getParameter("add_course_id");
        String add_course_name = request.getParameter("add_course_name");
        String add_course_hour = request.getParameter("add_course_hour");
        Course c=new Course();
        c.setCourse_id(Integer.parseInt(add_course_id));
        c.setCourse_name(add_course_name);
        c.setCourse_hour(add_course_hour);
        insertService.insertCourse(c);
        request.getSession().setAttribute("role",role);
        PageInfo<Course> courseAll = selectService.findCourseAll(1, 6);
        model.addAttribute("pageInfo",courseAll);
        return "course";
    }
    @RequestMapping("/addclass/{role}")
    public String addclass(@PathVariable("role") String role,HttpServletRequest request, ModelMap model){
        String add_class_id = request.getParameter("add_class_id");
        String add_class_name = request.getParameter("add_class_name");
        String add_student_num = request.getParameter("add_student_num");
        String add_teacher_num = request.getParameter("add_teacher_num");
        Class c=new Class();
        c.setClass_id(Integer.parseInt(add_class_id));
        c.setClass_name(add_class_name);
        c.setStudent_num(Integer.parseInt(add_student_num));
        c.setTeacher_num(Integer.parseInt(add_teacher_num));
        insertService.insertClass(c);
        request.getSession().setAttribute("role",role);
        PageInfo<Class> classAll = selectService.findClassAll(1, 6);
        model.addAttribute("pageInfo",classAll);
        return "class";
    }


}
