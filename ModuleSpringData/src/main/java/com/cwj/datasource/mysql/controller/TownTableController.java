package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.TownTable;
import com.cwj.datasource.mysql.service.TownTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (TownTable)表控制层
 *
 * @author makejava
 * @since 2021-08-02 20:55:28
 */
@RestController
@RequestMapping("/townTable")
@Api(tags = "乡、镇Town表操作 RestApi")
public class TownTableController {
    /**
     * 服务对象
     */
    @Resource
    private TownTableService townTableService;

    /**
     * 通过主键查询单条数据
     *
     * @param townCode 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation(value = "byCode查询town表", notes = "通过code值查询")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "townCode", value = "province表中townCode值", required = true, dataType = "String",defaultValue = "110102020", paramType = "query")
    )
    public TownTable selectOne(String townCode) {
        return this.townTableService.queryById(townCode);
    }

}