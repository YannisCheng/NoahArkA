package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.ProvinceTable;
import com.cwj.datasource.mysql.service.ProvinceTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (ProvinceTable)表控制层
 *
 * @author makejava
 * @since 2021-08-02 20:53:07
 */
@RestController
@RequestMapping("/provinceTable")
@Api(tags = "省province表操作 RestApi")
public class ProvinceTableController {
    /**
     * 服务对象
     */
    @Resource
    private ProvinceTableService provinceTableService;

    /**
     * 通过主键查询单条数据
     *
     * @param provinceCode 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation(value = "byCode查询province表", notes = "通过code值查询")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "provinceCode", value = "province表中provinceCode值", required = true, dataType = "String",defaultValue = "45", paramType = "query")
    )
    public ProvinceTable selectOne(String provinceCode) {
        return this.provinceTableService.queryById(provinceCode);
    }

}