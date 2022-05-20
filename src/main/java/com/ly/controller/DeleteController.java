package com.ly.controller;

import com.ly.service.DeleteService;
import com.ly.service.SelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-11 15:44
 */
@Controller
@RequestMapping("/delete")
public class DeleteController {

    @Autowired
    private DeleteService deleteService;
    @Autowired
    private SelectService selectService;
    @RequestMapping("/studentScore/{student_ids}/{role}")
    public String studentScore(HttpServletRequest request,Model model, @PathVariable("role") String role, @PathVariable("student_ids") String student_ids){
        String[] s = student_ids.split("s");
        for(String student_id:s){
            deleteService.deleteScore(Integer.parseInt(student_id));
        }
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",selectService.findScore(1,6));
        return "student";
    }
    @RequestMapping("/stu/{student_ids}/{role}")
    public String stu(HttpServletRequest request,Model model, @PathVariable("role") String role,@PathVariable("student_ids") String student_ids){
        String[] s = student_ids.split("s");
        for(String student_id:s){
            deleteService.deleteStudent(Integer.parseInt(student_id));
        }
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",selectService.findAllStudent(1,6));
        return "stu";
    }
    @RequestMapping("/teacher/{teacher_ids}/{role}")
    public String teacher(HttpServletRequest request,Model model,@PathVariable("role") String role, @PathVariable("teacher_ids") String teacher_ids){
        String[] t = teacher_ids.split("s");
        for(String teacher_id:t){
            deleteService.deleteTeacher(Integer.parseInt(teacher_id));
        }
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",selectService.findTeacherAll(1,6));
        return "teacher";
    }
    @RequestMapping("/class/{class_ids}/{role}")
    public String Myclass(HttpServletRequest request,Model model,@PathVariable("role") String role, @PathVariable("class_ids") String class_ids){
        String[] c = class_ids.split("s");
        for(String class_id:c){
            deleteService.deleteClass(Integer.parseInt(class_id));
        }
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",selectService.findClassAll(1,6));
        return "class";
    }
    @RequestMapping("/course/{course_ids}/{role}")
    public String course(HttpServletRequest request,Model model,@PathVariable("role") String role, @PathVariable("course_ids") String course_ids){
        String[] c = course_ids.split("s");
        for(String course_id:c){
            deleteService.deleteCourse(Integer.parseInt(course_id));
        }
        request.getSession().setAttribute("role",role);
        model.addAttribute("pageInfo",selectService.findCourseAll(1,6));
        return "course";
    }
}
