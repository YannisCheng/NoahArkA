package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.Table0;
import com.cwj.datasource.mysql.service.Table0Service;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (Table0)表控制层
 *
 * @author makejava
 * @since 2021-09-02 21:16:31
 */
@RestController
@RequestMapping("/table0")
public class Table0Controller {
    /**
     * 服务对象
     */
    @Resource
    private Table0Service table0Service;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "id值", required = true, dataType = "String",defaultValue = "1", paramType = "query")
    )
    public Table0 selectOne(String id) {
        return this.table0Service.queryById(id);
    }

}