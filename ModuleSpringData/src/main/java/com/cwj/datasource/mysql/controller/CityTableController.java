package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.CityTable;
import com.cwj.datasource.mysql.service.CityTableService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (CityTable)表控制层
 *
 * @author makejava
 * @since 2021-08-02 11:04:50
 */
@RestController
@RequestMapping("/cityTable")
@Api(tags = "city表 Rest Api")
public class CityTableController {
    /**
     * 服务对象
     */
    @Resource
    private CityTableService cityTableService;

    /**
     * 通过主键查询单条数据
     *
     * @param cityId 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation(value = "by id查询city表", notes = "通过id值查询")
    // API文档：参数描述
    // 方式1：通过时使用 @ApiImplicitParams 来表示
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cityId", value = "city表中cityId值", required = true, dataType = "integer",defaultValue = "1301", paramType = "query")
    )
    // 方式2：通过使用Spring中的注解来描述API文档中的参数说明
    // public CityTable selectOne(@RequestParam(value = "cityId", defaultValue = "1301") String cityId) {
    // 方式3：Spring+Swagger 组合
    //public CityTable selectOne(@RequestParam(value = "cityId", defaultValue = "1301") @ApiParam(value = "city表中cityId值", required = true, type = "Integer") String cityId) {
    public CityTable selectOne(String cityId) {
        CityTable cityTable = this.cityTableService.queryById(cityId);
        System.out.println(cityTable.toString());
        return cityTable;
    }

}