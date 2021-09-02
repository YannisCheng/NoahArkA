package com.cwj.datasource.mysql.controller;

import com.cwj.common.test.CommonMainTest;
import com.cwj.datasource.configuration.*;
import com.cwj.datasource.mysql.entity.CityTable;
import com.cwj.datasource.mysql.service.CityTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "MySQL/市city表操作 RestApi", position = 2)
public class CityTableController {
    /**
     * 服务对象
     */
    @Resource
    private CityTableService cityTableService;

    /**
     * Common Module 中的测试入口主类
     */
    @Resource
    CommonMainTest commonMainTest;

    @Resource
    MySQL49ClassDsConfigBean mySQL49ClassDsConfigBean;

    @Resource
    MySQLAreaDsConfigBean mySQLAreaDsConfigBean;

    @Resource
    MySQLMiddleDsConfigBean mySQLMiddleDsConfigBean;

    @Resource
    RedisIndex0DsConfigBean redisIndex0DsConfigBean;

    @Resource
    RedisIndex1DsConfigBean redisIndex1DsConfigBean;

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
        // 验证 @Data 注解功能
        System.out.println(cityTable.toString());
        // 验证 Common Module中的yml配置文件映射的CommonConfiguration java文件类
        commonMainTest.showCommonConfiguration();
        testDataSourceConfiguration();
        return cityTable;
    }

    /**
     * 测试 application-data-dev.yml 文件中的配置项是否能够被 Java bean 获取
     */
    private void testDataSourceConfiguration(){
        System.out.println("MySQL 49clas 配置结果：" + mySQL49ClassDsConfigBean.toString());
        System.out.println("MySQL area   配置结果：" + mySQLAreaDsConfigBean.toString());
        System.out.println("MySQL middle 配置结果：" + mySQLMiddleDsConfigBean.toString());
        System.out.println("Redis redis0 配置结果：" + redisIndex0DsConfigBean.toString());
        System.out.println("Redis redis1 配置结果：" + redisIndex1DsConfigBean.toString());
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

    @PostMapping("/updateByBody")
    @ApiOperation(value = "更新一条记录",notes = "请求体")
    /*@ApiImplicitParams(
            @ApiImplicitParam(name = "cityTable", value = "一条json字符串记录", required = true, dataType = "String",defaultValue = "",paramType = "body")
    )*/
    public CityTable updateByBody(@RequestBody CityTable cityTable){
        return cityTableService.update(cityTable);
    }

    @PostMapping("/insertByBody")
    @ApiOperation(value = "插入一条数据",notes = "请求体")
    /*@ApiImplicitParams(
            @ApiImplicitParam(name = "cityTable", value = "一条json字符串记录", required = true, dataType = "String",defaultValue = "",paramType = "body")
    )*/
    public CityTable insertByBody(@RequestBody CityTable cityTable){
        return cityTableService.insert(cityTable);
    }


}