package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table11;
import com.cwj.datasource.mysql.service.Table11Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table11)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:25:26
 */
@RestController
@RequestMapping("/table11")
public class Table11Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table11Service table11Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Table11 selectOne(String id) {
        return this.table11Service.queryById(id);
    }

}