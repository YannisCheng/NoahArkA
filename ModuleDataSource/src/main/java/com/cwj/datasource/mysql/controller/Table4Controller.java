package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table4;
import com.cwj.datasource.mysql.service.Table4Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table4)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:27:30
 */
@RestController
@RequestMapping("table4")
public class Table4Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table4Service table4Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Table4 selectOne(String id) {
        return this.table4Service.queryById(id);
    }

}