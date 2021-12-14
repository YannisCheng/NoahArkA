package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table8;
import com.cwj.datasource.mysql.service.Table8Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table8)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@RestController
@RequestMapping("/table8")
public class Table8Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table8Service table8Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Table8 selectOne(String id) {
        return this.table8Service.queryById(id);
    }

}