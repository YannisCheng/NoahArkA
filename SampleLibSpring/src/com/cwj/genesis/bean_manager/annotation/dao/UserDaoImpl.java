package com.cwj.genesis.bean_manager.annotation.dao;

import com.cwj.genesis.bean_manager.xml.dao.UserDao;
import org.springframework.stereotype.Repository;

/**
 * com.cwj.genesis.bean_manager.annotation.dao
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-26 08:55
 */
@Repository(value = "userDaoImpl1")
public class UserDaoImpl implements UserDao {
    @Override
    public void update() {
        System.out.println("user dao ......");
    }
}
