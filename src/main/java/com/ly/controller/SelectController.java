package com.ly.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.ly.bean.*;
import com.ly.bean.Class;
import com.ly.service.SelectService;
import com.ly.utils.DataTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/select")
public class SelectController {
    private final int pageSize=6;
    private int pageNum=1;
    @Autowired
    private SelectService selectService;
    @Autowired
    private DataTypeUtils dtu;

    /**
     * 查询个人成绩
     * @param student_id
     * @param model
     * @param response
     * @throws IOException
     */
    @RequestMapping("/{student_id}")
    public void findById(@PathVariable("student_id") int student_id , Model model, HttpServletResponse response) throws IOException {
        List<HashMap<String,Object>> list = selectService.findById(student_id);
        if (!"[]".equals(String.valueOf(list))) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(list);
            response.getWriter().print(data);
        } else {
            response.getWriter().print("error");
        }
    }

    /**
     * 登陆验证
     * @param id
     * @param password
     * @param request
     * @throws IOException
     */
    @RequestMapping("/login")
    public String findById(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request,Model model) throws IOException {
        Admin byAdmin = selectService.findByAdmin(Integer.parseInt(id));
        Student byStudent = selectService.findByStudent(Integer.parseInt(id));
        Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(id));
        if(byAdmin!=null){
            if(byAdmin.getAdmin_password().equals(password)){
                request.getSession().setAttribute("role","admin");
                request.getSession().setAttribute("username",byAdmin.getAdmin_name());
                request.getSession().setAttribute("id",Integer.parseInt(id));
                model.addAttribute("img",byAdmin.getAdmin_img());
                return "main";
            }else {
                model.addAttribute("message","密码错误");
                return "index";
            }
        }else if(byStudent!=null){
            if(byStudent.getStudent_password().equals(password)){
                request.getSession().setAttribute("role","student");
                request.getSession().setAttribute("username",byStudent.getStudent_name());
                request.getSession().setAttribute("id",Integer.parseInt(id));
                model.addAttribute("img",byStudent.getStudent_img());
                return "main";
            }else {
                model.addAttribute("message","密码错误");
                return "index";
            }
        }else if(byTeacher!=null){
            if(byTeacher.getTeacher_password().equals(password)){
                request.getSession().setAttribute("role","teacher");
                request.getSession().setAttribute("username",byTeacher.getTeacher_name());
                request.getSession().setAttribute("id",Integer.parseInt(id));
                model.addAttribute("img",byTeacher.getTeacher_img());
                return "main";
            }else {
                model.addAttribute("message","密码错误");
                return "index";
            }
        }else {
            model.addAttribute("message","用户名错误");
            return "index";
        }
    }

    /**
     * 验证admin_id是否存在
     * @param admin_id
     * @param response
     * @throws IOException
     */
    @GetMapping("/exist/{admin_id}")
    public void exist(@PathVariable("admin_id") String admin_id,HttpServletResponse response) throws IOException {
        Admin byAdmin = selectService.findByAdmin(Integer.parseInt(admin_id));
        if(byAdmin!=null){
            response.getWriter().print("exist");
        }else {
            response.getWriter().print("not-exist");
        }

    }


    //=============================分页功能=================================
    @GetMapping("/page/{pageNum}/{data}")
    public String Page(HttpServletRequest request, @PathVariable("data") String data, @PathVariable("pageNum") String pageNum, Model model){
        this.pageNum=Integer.parseInt(pageNum);
        PageInfo<HashMap<String, Object>> score = selectService.findScore(this.pageNum, pageSize);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",score);
        return "student";
    }
    @GetMapping("/pagestu/{pageNum}/{data}")
    public String pagestu(HttpServletRequest request, @PathVariable("data") String data, @PathVariable("pageNum") String pageNum, Model model){
        this.pageNum=Integer.parseInt(pageNum);
        PageInfo<Student> allStudent = selectService.findAllStudent(this.pageNum, pageSize);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",allStudent);
        return "stu";
    }
    @GetMapping("/pageteacher/{pageNum}/{data}")
    public String pageteacher(HttpServletRequest request, @PathVariable("data") String data, @PathVariable("pageNum") String pageNum, Model model){
        this.pageNum=Integer.parseInt(pageNum);
        PageInfo<Teacher> teacherAll = selectService.findTeacherAll(this.pageNum, pageSize);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",teacherAll);
        return "teacher";
    }
    @GetMapping("/pageclass/{pageNum}/{data}")
    public String pageclass(HttpServletRequest request, @PathVariable("data") String data, @PathVariable("pageNum") String pageNum, Model model){
        this.pageNum=Integer.parseInt(pageNum);
        PageInfo<Class> classAll = selectService.findClassAll(this.pageNum, pageSize);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",classAll);
        return "class";
    }
    @GetMapping("/pagecourse/{pageNum}/{data}")
    public String pagecourse(HttpServletRequest request, @PathVariable("data") String data, @PathVariable("pageNum") String pageNum, Model model){
        this.pageNum=Integer.parseInt(pageNum);
        PageInfo<Course> courseAll = selectService.findCourseAll(this.pageNum, pageSize);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",courseAll);
        return "course";
    }

    //===========================搜索=============================//
    @RequestMapping("/searchStudentIdName/{id}/{name}/{data}")
    public String searchStudentIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("id") String id,@PathVariable("name") String name){
        PageInfo<HashMap<String, Object>> scoreByIdName = selectService.findScoreByIdName(1, 6, id, name);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",scoreByIdName);
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "student";
    }
    @RequestMapping("/searchStudentIdName/{value}/{data}")
    public String searchStudentIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("value") String value){
        String[] s= value.split("\\.");
        PageInfo<HashMap<String, Object>> scoreByIdName = selectService.findScoreByLike(1, 6, s[1]);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",scoreByIdName);
        if("id".equals(s[0])){
            model.addAttribute("id",s[1]);
        }else if ("name".equals(s[0])){
            model.addAttribute("name",s[1]);
        }
        return "student";
    }
    @RequestMapping("/searchTeacherIdName/{id}/{name}/{data}")
    public String searchTeacherIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("id") String id,@PathVariable("name") String name){
        PageInfo<Teacher> teacherByIdName = selectService.findTeacherByIdName(1, 6, id, name);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",teacherByIdName);
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "teacher";
    }
    @RequestMapping("/searchTeacherIdName/{value}/{data}")
    public String searchTeacherIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("value") String value){
        String[] s= value.split("\\.");
        PageInfo<Teacher> teacherByIdName = selectService.findTeacherByLike(1, 6, s[1]);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",teacherByIdName);
        if("id".equals(s[0])){
            model.addAttribute("id",s[1]);
        }else if ("name".equals(s[0])){
            model.addAttribute("name",s[1]);
        }
        return "teacher";
    }
    @RequestMapping("/searchClassIdName/{id}/{name}/{data}")
    public String searchClassIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("id") String id,@PathVariable("name") String name){
        PageInfo<Class> classByIdName = selectService.findClassByIdName(1, 6, id, name);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",classByIdName);
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "class";
    }
    @RequestMapping("/searchClassIdName/{value}/{data}")
    public String searchClassIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("value") String value){
        String[] s= value.split("\\.");
        PageInfo<Class> classByIdName = selectService.findClassByLike(1, 6, s[1]);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",classByIdName);
        if("id".equals(s[0])){
            model.addAttribute("id",s[1]);
        }else if ("name".equals(s[0])){
            model.addAttribute("name",s[1]);
        }
        return "class";
    }
    @RequestMapping("/searchCourseIdName/{id}/{name}/{data}")
    public String searchCourseIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("id") String id,@PathVariable("name") String name){
        PageInfo<Course> courseByIdName = selectService.findCourseByIdName(1, 6, id, name);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",courseByIdName);
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "course";
    }
    @RequestMapping("/searchCourseIdName/{value}/{data}")
    public String searchCourseIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("value") String value){
        String[] s= value.split("\\.");
        PageInfo<Course> courseByIdName = selectService.findCourseByLike(1, 6, s[1]);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",courseByIdName);
        if("id".equals(s[0])){
            model.addAttribute("id",s[1]);
        }else if ("name".equals(s[0])){
            model.addAttribute("name",s[1]);
        }
        return "course";
    }
    @RequestMapping("/searchStuIdName/{id}/{name}/{data}")
    public String searchStuIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("id") String id,@PathVariable("name") String name){
        PageInfo<Student> studentByIdName = selectService.findStudentByIdName(1, 6, id, name);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",studentByIdName);
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return "stu";
    }
    @RequestMapping("/searchStuIdName/{value}/{data}")
    public String searchStuIdName(HttpServletRequest request, @PathVariable("data") String data, Model model,@PathVariable("value") String value){
        String[] s= value.split("\\.");
        PageInfo<Student> studentByIdName = selectService.findStudentByLike(1, 6, s[1]);
        request.getSession().setAttribute("role",data);
        model.addAttribute("pageInfo",studentByIdName);
        if("id".equals(s[0])){
            model.addAttribute("id",s[1]);
        }else if ("name".equals(s[0])){
            model.addAttribute("name",s[1]);
        }
        return "stu";
    }




   //===========================添加=============================//
    @GetMapping("/findNoScoreStudent")
    public void findNoScoreStudent(HttpServletResponse response) {
        try{
            List<Student> allStudent = selectService.findNoScoreStudent();
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(allStudent);
            response.getWriter().print(data);
        }catch (Exception e){
            try {
                response.getWriter().print("error");
            }catch (Exception ee){
                System.out.println("查询出错");
            }
        }
    }
    @RequestMapping("/findStudentNameByStudentId/{student_id}")
    public void findStudentNameByStudentId(HttpServletResponse response,@PathVariable("student_id") String student_id) throws IOException {
        Student byStudent = selectService.findByStudent(Integer.parseInt(student_id));
        if(!"[]".equals(byStudent)){
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(byStudent);
            response.getWriter().print(data);
        }else {
            response.getWriter().print("error");
        }
    }

    //===========================统计图标==========================//
    @GetMapping("/courseAverageScore")
    public void courseAverageScore(HttpServletResponse response){
        try{
            List l=new ArrayList();
            for(int i=1;i<10;i++){
                StudentScoreCourse ssc=new StudentScoreCourse();
                ssc.setSum_score(selectService.findAverageScoreByCourse_id(i));
                l.add(ssc);
            }
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(l);
            response.getWriter().print(data);
        }catch (Exception e){
            try {
                response.getWriter().print("error");
            }catch (Exception ee){
                System.out.println("出错了"+ee.getMessage());
            }
        }
    }
    @GetMapping("/teacherStudentNumber")
    public void teacherStudentNumber(HttpServletResponse response){
        try{
            List<Class> classes = selectService.teacherStudentNumber();
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(classes);
            response.getWriter().print(data);
        }catch (Exception e) {
            try {
                response.getWriter().print("error");
            } catch (Exception ee) {
                System.out.println("出错了" + ee.getMessage());
            }
        }

    }
    //==========================个人资料==========================//
    @RequestMapping("/person/{id}")
    public String person(@PathVariable("id") String id, ModelMap model, HttpServletRequest request,HttpServletResponse response) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Admin byAdmin = selectService.findByAdmin(Integer.parseInt(id));
        Student byStudent = selectService.findByStudent(Integer.parseInt(id));
        Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(id));
        if(byAdmin!=null){
            Map<String, Object> m = dtu.objectToMap(byAdmin, "admin");
            model.addAttribute("map",m);
        }else if(byStudent!=null){
            Map<String, Object> m = dtu.objectToMap(byStudent, "student");
            model.addAttribute("map",m);
        }else if(byTeacher!=null){
            Map<String, Object> m = dtu.objectToMap(byTeacher, "teather");
            model.addAttribute("map",m);
        }
        return "person";
    }

    //==========================学生\老师\课程\班级byid==========================//
    @GetMapping("/stuById/{id}")
    public void stuById(@PathVariable("id") String id,HttpServletResponse response) throws Exception{
        Student byStudent = selectService.findByStudent(Integer.parseInt(id));
        if(byStudent==null){
            response.getWriter().print("notexist");
        }else{
            response.getWriter().print("exist");
        }
    }
    @GetMapping("/classById/{id}")
    public void classById(@PathVariable("id") String id,HttpServletResponse response) throws Exception{
        Class byClass = selectService.findByClass(Integer.parseInt(id));
        if(byClass==null){
            response.getWriter().print("notexist");
        }else{
            response.getWriter().print("exist");
        }
    }
    @GetMapping("/teacherById/{id}")
    public void teacherById(@PathVariable("id") String id,HttpServletResponse response) throws Exception{
        Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(id));
        if(byTeacher==null){
            response.getWriter().print("notexist");
        }else{
            response.getWriter().print("exist");
        }
    }
    @GetMapping("/courseById/{id}")
    public void courseById(@PathVariable("id") String id,HttpServletResponse response) throws Exception{
        Course byCourse = selectService.findByCourse(Integer.parseInt(id));
        if(byCourse==null){
            response.getWriter().print("notexist");
        }else{
            response.getWriter().print("exist");
        }
    }

    //==========================学生\老师\课程\班级编辑查询==========================//
    @GetMapping("/studentEdit/{student_id}")
    public void findScoreById(HttpServletResponse response,@PathVariable("student_id") String student_id) throws IOException {
        List<HashMap<String, Object>> scoreById = selectService.findScoreById(Integer.parseInt(student_id));
        if (!"[]".equals(String.valueOf(scoreById))) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(scoreById);
            response.getWriter().print(data);
        } else {
            response.getWriter().print("error");
        }
    }
    @GetMapping("/stuEdit/{student_id}")
    public void stuEdit(HttpServletResponse response,@PathVariable("student_id") String student_id) throws IOException {
        Student byStudent = selectService.findByStudent(Integer.parseInt(student_id));
        if (!"".equals(byStudent)) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(byStudent);
            response.getWriter().print(data);
        } else {
            response.getWriter().print("error");
        }
    }
    @GetMapping("/teacherEdit/{teacher_id}")
    public void teacherEdit(HttpServletResponse response,@PathVariable("teacher_id") String teacher_id) throws IOException {
        Teacher byTeacher = selectService.findByTeacher(Integer.parseInt(teacher_id));
        if (!"".equals(byTeacher)) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(byTeacher);
            response.getWriter().print(data);
        } else {
            response.getWriter().print("error");
        }
    }
    @GetMapping("/classEdit/{class_id}")
    public void classEdit(HttpServletResponse response,@PathVariable("class_id") String class_id) throws IOException {
        Class byClass = selectService.findByClass(Integer.parseInt(class_id));
        if (!"".equals(byClass)) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(byClass);
            response.getWriter().print(data);
        } else {
            response.getWriter().print("error");
        }
    }
    @GetMapping("/courseEdit/{course_id}")
    public void courseEdit(HttpServletResponse response,@PathVariable("course_id") String course_id) throws IOException {
        Course byCourse = selectService.findByCourse(Integer.parseInt(course_id));
        if (!"".equals(byCourse)) {
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("text/html;charset=UTF-8");
            String data = mapper.writeValueAsString(byCourse);
            response.getWriter().print(data);
        } else {
            response.getWriter().print("error");
        }
    }

}
