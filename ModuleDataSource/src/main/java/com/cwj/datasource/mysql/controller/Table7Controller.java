package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table7;
import com.cwj.datasource.mysql.service.Table7Service;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Table7)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@RestController
@RequestMapping("/table7")
public class Table7Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table7Service table7Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Table7 selectOne(String id) {
        return this.table7Service.queryById(id);
    }

}