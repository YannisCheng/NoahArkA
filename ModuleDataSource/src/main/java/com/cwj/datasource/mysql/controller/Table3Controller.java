package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table3;
import com.cwj.datasource.mysql.service.Table3Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table3)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:26:43
 */
@RestController
@RequestMapping("table3")
public class Table3Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table3Service table3Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Table3 selectOne(String id) {
        return this.table3Service.queryById(id);
    }

}