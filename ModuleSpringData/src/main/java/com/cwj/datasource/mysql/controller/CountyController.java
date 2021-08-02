package com.cwj.datasource.mysql.controller;

import com.cwj.datasource.mysql.entity.CountryTable;
import com.cwj.datasource.mysql.service.CountyTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * com.cwj.datasource.mysql.controller
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-02 17:14
 */
@RestController
@RequestMapping(value = "/countyTable")
@Api(tags = "区县乡镇表 RestAPI查询")
public class CountyController {

    @Resource
    private CountyTableService countyTableService;

    @GetMapping(value = "/queryById")
    @ApiOperation(value = "by id查询county表", notes = "通过id值查询")
    public CountryTable selectOne(String countryCode){
        return this.countyTableService.queryById(countryCode);
    }
}
