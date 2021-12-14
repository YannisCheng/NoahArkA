package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table6;
import com.cwj.datasource.mysql.service.Table6Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table6)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@RestController
@RequestMapping("/table6")
public class Table6Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table6Service table6Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Table6 selectOne(String id) {
        return this.table6Service.queryById(id);
    }

}