package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.VillageTable;
import com.cwj.datasource.mysql.service.VillageTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (VillageTable)表控制层
 *
 * @author makejava
 * @since 2021-08-02 21:08:15
 */
@RestController
@RequestMapping("/villageTable")
@Api(tags = "MySQL/村、社区Village表操作 RestApi", position = 5)
public class VillageTableController {
    /**
     * 服务对象
     */
    @Resource
    private VillageTableService villageTableService;

    /**
     * 通过主键查询单条数据
     *
     * @param villageCode 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation(value = "byCode查询village表", notes = "通过code值查询")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "villageCode", value = "village表中villageCode值", required = true, dataType = "String",defaultValue = "110101002009", paramType = "query")
    )
    public VillageTable selectOne(String villageCode) {
        return this.villageTableService.queryById(villageCode);
    }

}