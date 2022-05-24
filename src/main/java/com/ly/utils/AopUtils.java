package com.ly.utils;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Aspect
@Component
public class AopUtils {

    @Autowired
    private RedisTemplate<String,Object> rt;

    @Pointcut("execution(*public com.ly.service.DeleteService.deleteScore(..))"+
            "&& execution(*public com.ly.service.DeleteService.deleteStudent(..))"+
            "&& execution(*public com.ly.service.DeleteService.deleteCourse(..))")
    public void f(){}

    @After("f()")
    public void deleteKeys() {
        Set<String> keys = rt.keys("scorecoursestudent_*");
        if (keys.size()>0) {
            rt.delete(keys);
        }
        System.out.println("aop...");
    }
}
