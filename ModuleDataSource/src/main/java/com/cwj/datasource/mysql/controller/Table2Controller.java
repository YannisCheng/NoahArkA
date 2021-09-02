package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table2;
import com.cwj.datasource.mysql.service.Table2Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table2)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:26:08
 */
@RestController
@RequestMapping("table2")
public class Table2Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table2Service table2Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Table2 selectOne(String id) {
        return this.table2Service.queryById(id);
    }

}