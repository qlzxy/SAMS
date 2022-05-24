package com.ly.controller;

import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class InitController {

    @Autowired
    private SelectService selectService;

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @GetMapping("/classstudentteacher")
    public String classstudentteacher(){
        return "classstudentteacher";
    }
    @GetMapping("/courseaveragescore")
    public String courseaveragescore(){
        return "courseaveragescore";
    }
    @GetMapping("/about")
    public String about(){
        return "about";
    }
    @GetMapping("/loginout")
    public String loginout(HttpServletRequest request){
        if(request.getSession().getAttribute("role")!=null){
            request.removeAttribute("role");
        }
        return "index";
    }
    @GetMapping("/main/{id}")
    public String main(@PathVariable("id") String id, ModelMap model,HttpServletRequest request){
        Admin byAdmin = selectService.findByAdmin(Integer.parseInt(id));
        Student byStudent = selectService.findByStudent(Integer.parseInt(id));
        Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(id));
        if(byAdmin!=null){
            model.addAttribute("img",byAdmin.getAdmin_img());
            request.getSession().setAttribute("username",byAdmin.getAdmin_name());
            return "main";
        }else if(byTeacher!=null){
            model.addAttribute("img",byTeacher.getTeacher_img());
            request.getSession().setAttribute("username",byTeacher.getTeacher_name());
            return "main";
        }else if(byStudent!=null){
            model.addAttribute("img",byStudent.getStudent_img());
            request.getSession().setAttribute("username",byStudent.getStudent_name());
            return "main";
        }else {
            return "index";
        }
    }
    @GetMapping("/studentScore/{data}")
    public String studentScore(@PathVariable("data") String data, Model model,HttpServletRequest request){
        PageInfo<HashMap<String, Object>> score = selectService.findScore(1, 6);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",score);
        return "student";
    }
    @GetMapping("/class/{data}")
    public String Myclass(@PathVariable("data") String data,Model model,HttpServletRequest request){
        PageInfo<Class> classAll = selectService.findClassAll(1, 6);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",classAll);
        return "class";
    }
    @GetMapping("/course/{data}")
    public String course(@PathVariable("data") String data,Model model,HttpServletRequest request){
        PageInfo<Course> courseAll = selectService.findCourseAll(1, 6);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",courseAll);
        return "course";
    }
    @GetMapping("/student/{data}")
    public String student(@PathVariable("data") String data,Model model,HttpServletRequest request){
        PageInfo<Student> allStudent = selectService.findAllStudent(1, 6);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",allStudent);
        return "stu";
    }
    @GetMapping("/teacher/{data}")
    public String teacher(@PathVariable("data") String data,Model model,HttpServletRequest request){
        PageInfo<Teacher> teacherAll = selectService.findTeacherAll(1, 6);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",teacherAll);
        return "teacher";
    }
    @GetMapping("/return")
    public String returnPassword(){
        return "return";
    }
    @RequestMapping("/changePassword/{id}/{role}")
    public String changePassword(@PathVariable("id") String id,@PathVariable("role") String role,Model model){
        model.addAttribute("id",id);
        model.addAttribute("role",role);
        model.addAttribute("message","");
        return "changePassword";
    }
}
