package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table9;
import com.cwj.datasource.mysql.service.Table9Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table9)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@RestController
@RequestMapping("/table9")
public class Table9Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table9Service table9Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Table9 selectOne(String id) {
        return this.table9Service.queryById(id);
    }

}