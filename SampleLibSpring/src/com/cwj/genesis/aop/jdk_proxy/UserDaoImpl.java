package com.cwj.genesis.aop.jdk_proxy;

/**
 * com.cwj.genesis.aop
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-05-26 13:53
 */
public class UserDaoImpl implements UserDao{

    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public String update(String data) {
        return data;
    }
}
