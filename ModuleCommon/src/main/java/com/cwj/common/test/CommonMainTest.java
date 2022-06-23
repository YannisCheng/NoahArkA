package com.cwj.common.test;

import com.cwj.common.configuration.Student;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * com.cwj.common.AnnoMain
 *
 * 功能验证测试类
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-08-04 09:54
 */
@Component
public class CommonMainTest {

    @Resource
    Student student;

    public void showCommonConfiguration(){
        // 测试结果：
        // Student(name=zg, isfemale=true, birth=Fri Apr 12 00:00:00 CST 2019, teachers=[zhang teacher, wang teacher], grade={English=87, Math=100}, pets=Pet(spices=Dog, spices2=wang teacher-Dog, age=1, age2=6912089))
        System.out.println(student.toString());
    }
}
