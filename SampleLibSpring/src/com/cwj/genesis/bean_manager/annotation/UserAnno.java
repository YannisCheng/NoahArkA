package com.cwj.genesis.bean_manager.annotation;

import com.cwj.genesis.bean_manager.annotation.dao.UserDaoImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * com.cwj.genesis.bean_manager.annotation
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 23:16
 */
//@Component(value = "userAnnotation")
//默认值：userAnno
@Component
public class UserAnno {

    /*@Autowired
    // 指定类型的某个名称
    @Qualifier(value = "userDaoImpl1")
    private UserDaoImpl userDao;*/

    @Resource(name = "userDaoImpl1")
    private UserDaoImpl userDao;

    /*普通数据类型*/
    @Value(value = "abc")
    private String name;

    public void showLog() {
        System.out.println("user .........name : " + name);
        userDao.update();
    }
}
