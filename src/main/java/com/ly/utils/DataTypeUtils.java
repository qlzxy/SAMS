package com.ly.utils;
import com.ly.bean.Admin;
import com.ly.bean.Student;
import com.ly.bean.Teacher;
import com.ly.mapper.SelectMapper;
import com.ly.mapper.UpdateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataTypeUtils {
    @Autowired
    private  SelectMapper sm;
    @Autowired
    private  UpdateMapper um;
    public  Map<String ,Object> objectToMap(Object o,String n)  {
        Map<String ,Object> map=new HashMap<>();
        if("admin".equals(n)){
            Admin a = (Admin) o;
            map.put("id",a.getAdmin_id());
            map.put("name",a.getAdmin_name());
            map.put("img",a.getAdmin_img());
            map.put("sex",a.getAdmin_sex());
            map.put("email",a.getAdmin_email());
            map.put("role",a.getAdmin_role());
        }else if("student".equals(n)){
            Student a = (Student) o;
            map.put("id",a.getStudent_id());
            map.put("name",a.getStudent_name());
            map.put("img",a.getStudent_img());
            map.put("sex",a.getStudent_sex());
            map.put("email",a.getStudent_email());
            map.put("role",a.getStudent_role());
            map.put("phone",a.getStudent_phone());
            map.put("birth",a.getStudent_birth());
        }else {
            Teacher a = (Teacher) o;
            map.put("id",a.getTeacher_id());
            map.put("name",a.getTeacher_name());
            map.put("img",a.getTeacher_img());
            map.put("sex",a.getTeacher_sex());
            map.put("email",a.getTeacher_email());
            map.put("role",a.getTeacher_role());
            map.put("birth",a.getTeacher_birth());
        }
        return map;
    }
    public Map<String,String> savePerson(String n, Map<String,String> m)  {
        if("admin".equals(n)){
            Admin a = sm.findByAdmin(Integer.parseInt(m.get("id")));
            a.setAdmin_role(m.get("role"));
            a.setAdmin_name(m.get("name"));
            a.setAdmin_sex(m.get("sex"));
            a.setAdmin_email(m.get("email"));
            System.out.println(a.toString());
            um.updateAdmin(a);
            m.put("img",a.getAdmin_img());
            return m;
        }else if("teacher".equals(n)){
            Teacher t = sm.findByTeacher(Integer.parseInt(  m.get("id")));
            t.setTeacher_role(  m.get("role"));
            t.setTeacher_name(  m.get("name"));
            t.setTeacher_sex(  m.get("sex"));
            t.setTeacher_email(  m.get("email"));
            t.setTeacher_phone(  m.get("phone"));
            t.setTeacher_birth(  m.get("birth"));
            um.updateTeacher(t);
            m.put("img",t.getTeacher_img());
            return m;
        }else{
            Student s = sm.findByStudent(Integer.parseInt(  m.get("id")));
            s.setStudent_role(  m.get("role"));
            s.setStudent_name(  m.get("name"));
            s.setStudent_sex(  m.get("sex"));
            s.setStudent_email(  m.get("email"));
            s.setStudent_phone(  m.get("phone"));
            s.setStudent_birth(  m.get("birth"));
            um.updateStudent(s);
            m.put("img",s.getStudent_img());
            return m;
        }
    }
}
