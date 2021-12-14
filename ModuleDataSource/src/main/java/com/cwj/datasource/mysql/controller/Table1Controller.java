package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table1;
import com.cwj.datasource.mysql.service.Table1Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table1)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:21:13
 */
@RestController
@RequestMapping("/table1")
public class Table1Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table1Service table1Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Table1 selectOne(String id) {
        return this.table1Service.queryById(id);
    }

}