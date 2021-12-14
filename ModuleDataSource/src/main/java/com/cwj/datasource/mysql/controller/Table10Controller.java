package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table10;
import com.cwj.datasource.mysql.service.Table10Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table10)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:24:40
 */
@RestController
@RequestMapping("/table10")
public class Table10Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table10Service table10Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Table10 selectOne(String id) {
        return this.table10Service.queryById(id);
    }

}