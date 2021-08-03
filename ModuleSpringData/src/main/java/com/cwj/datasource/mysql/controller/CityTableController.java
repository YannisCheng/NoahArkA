package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.CityTable;
import com.cwj.datasource.mysql.service.CityTableService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CityTable)表控制层
 *
 * @author makejava
 * @since 2021-08-02 11:04:50
 */
@RestController
@RequestMapping("/cityTable")
@Api(tags = "城市city表操作 RestApi")
public class CityTableController {
    /**
     * 服务对象
     */
    @Resource
    private CityTableService cityTableService;

    /**
     * 通过主键查询单条数据
     *
     * @param cityCode 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    @ApiOperation(value = "byCode查询city表", notes = "通过Code值查询")
    // API文档：参数描述
    // 方式1：通过时使用 @ApiImplicitParams 来表示
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cityCode", value = "city表中cityCode值", required = true, dataType = "String",defaultValue = "1301", paramType = "query")
    )
    // 方式2：通过使用Spring中的注解来描述API文档中的参数说明
    // public CityTable selectOne(@RequestParam(value = "cityId", defaultValue = "1301") String cityId) {
    // 方式3：Spring+Swagger 组合
    //public CityTable selectOne(@RequestParam(value = "cityId", defaultValue = "1301") @ApiParam(value = "city表中cityId值", required = true, type = "Integer") String cityId) {
    public CityTable selectOneByCityCode(String cityCode) {
        CityTable cityTable = this.cityTableService.queryById(cityCode);
        System.out.println(cityTable.toString());
        return cityTable;
    }

    @GetMapping("/queryAllByLimit")
    @ApiOperation(value = "分页查询所有结果",notes = "分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "页码", required = true, dataType = "int",defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "每页返回数据数量", required = true, dataType = "int",defaultValue = "20")
        }
    )
    public List<CityTable> queryAllByLimit(int offset, int limit){
        return cityTableService.queryAllByLimit(offset,limit);
    }

    @GetMapping("/deleteByCityCode")
    @ApiOperation(value = "通过body删除一条记录",notes = "请求体为json串")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "cityCode", value = "城市编码", required = true, dataType = "String",defaultValue = "")
    )
    public boolean deleteByCityCode(String cityCode){
        return cityTableService.deleteById(cityCode);
    }

    @GetMapping("/updateByBody")
    @ApiOperation(value = "更新一条记录",notes = "请求体")
    /*@ApiImplicitParams(
            @ApiImplicitParam(name = "cityTable", value = "一条json字符串记录", required = true, dataType = "String",defaultValue = "",paramType = "body")
    )*/
    public CityTable updateByBody(CityTable cityTable){
        return cityTableService.update(cityTable);
    }

    @GetMapping("/insertByBody")
    @ApiOperation(value = "插入一条数据",notes = "请求体")
    /*@ApiImplicitParams(
            @ApiImplicitParam(name = "cityTable", value = "一条json字符串记录", required = true, dataType = "String",defaultValue = "",paramType = "body")
    )*/
    public CityTable insertByBody(CityTable cityTable){
        return cityTableService.insert(cityTable);
    }


}