package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table5;
import com.cwj.datasource.mysql.service.Table5Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table5)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@RestController
@RequestMapping("table5")
public class Table5Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table5Service table5Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Table5 selectOne(String id) {
        return this.table5Service.queryById(id);
    }

}