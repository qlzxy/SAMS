package com.ly.mapper;

import com.ly.bean.*;
import com.ly.bean.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-12 10:51
 */
@Repository
@Mapper
public interface UpdateMapper {

    @UpdateProvider(type = UpdateSql.class,method = "updateStudent")
    public void updateStudent(Student student);

    @UpdateProvider(type = UpdateSql.class,method = "updateTeacher")
    public void updateTeacher(Teacher teacher);

    @UpdateProvider(type = UpdateSql.class,method = "updateAdmin")
    public void updateAdmin(Admin admin);

    @UpdateProvider(type = UpdateSql.class,method = "updateScore")
    public void updateScore(Score score);

    @UpdateProvider(type = UpdateSql.class,method = "updateCourse")
    public void updateCourse(Course course);

    @UpdateProvider(type = UpdateSql.class,method = "updateClass")
    public void updateClass(Class c);





}
