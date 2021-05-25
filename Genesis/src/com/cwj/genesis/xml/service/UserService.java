package com.cwj.genesis.xml.service;

import com.cwj.genesis.xml.dao.UserDao;

/**
 * com.cwj.genesis.xml.service
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-05-25 17:06
 */
public class UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add......");
        userDao.update();
    }
}
