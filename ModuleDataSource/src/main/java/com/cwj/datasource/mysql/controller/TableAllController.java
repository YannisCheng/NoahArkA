package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.TableAll;
import com.cwj.datasource.mysql.service.TableAllService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TableAll)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@RestController
@RequestMapping("tableAll")
public class TableAllController {
    /**
     * 服务对象
     */
    @Resource
    private TableAllService tableAllService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TableAll selectOne(String id) {
        return this.tableAllService.queryById(id);
    }

}