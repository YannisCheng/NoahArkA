package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table0;
import com.cwj.datasource.mysql.service.Table0Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table0)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:16:31
 */
@RestController
@RequestMapping("table0")
public class Table0Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table0Service table0Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Table0 selectOne(String id) {
        return this.table0Service.queryById(id);
    }

}