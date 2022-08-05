package com.cwj.datasource.mysql.area.controller;

import com.cwj.common.result.ResultBase;
import com.cwj.common.result.ResultUtils;
import com.cwj.datasource.mysql.area.entity.ProvinceTable;
import com.cwj.datasource.mysql.area.service.ProvinceTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 省份表(ProvinceTable)表控制层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:45:03
 */
@Api(tags = "行政区划-省份表")
@RestController
@RequestMapping("/provinceTable")
public class ProvinceTableController {

    /**
     * 服务对象
     */
    @Autowired
    private ProvinceTableService provinceTableService;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @GetMapping("/findById")
    @ApiOperation(value = "findById", notes = "通过主键Id进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true),
            @ApiImplicitParam(dataType = "Integer", name = "id", value = "主键", required = true, defaultValue = "1")
    })
    public ResultBase<ProvinceTable> findById(@RequestParam(name = "id") Integer id) {
        ResultBase<ProvinceTable> baseResult = null;
        boolean present = provinceTableService.findById(id).isPresent();
        if (present) {
            baseResult = ResultUtils.success(provinceTableService.findById(id).get());
        } else {
            baseResult = ResultUtils.errorData();
        }
        return baseResult;
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/findAll")
    @ApiOperation(value = "findAll", notes = "查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)
    })
    public ResultBase<List<ProvinceTable>> findAll() {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<ProvinceTable> provinceTables = provinceTableService.findAll(sort);
        return ResultUtils.success(provinceTables);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param page 页码
     * @param size 每页数量
     * @return 返回当前页的数据
     */
    @GetMapping("/findByPage")
    @ApiOperation(value = "findByPage", notes = "通过分页进行查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true),
            @ApiImplicitParam(dataType = "int", name = "page", value = "页码", defaultValue = "1", required = true),
            @ApiImplicitParam(dataType = "int", name = "size", value = "每页数量", defaultValue = "20", required = true)
    })
    public ResultBase<Page<ProvinceTable>> findByPage(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProvinceTable> provinceTables = provinceTableService.findByPage(pageable);
        return ResultUtils.success(provinceTables);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @PostMapping("/saveOne")
    @ApiOperation(value = "saveOne", notes = "保存单个对象")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)
    })
    public ResultBase<ProvinceTable> saveOne(@RequestBody ProvinceTable provinceTable) {
        ProvinceTable provinceTables = provinceTableService.saveOne(provinceTable);
        return ResultUtils.success(provinceTables);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 更新后的对象
     */
    @PostMapping("/updateByObj")
    @ApiOperation(value = "updateByObj", notes = "根据Obj保存单个对象")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)
    })
    public ResultBase<ProvinceTable> updateByObj(@RequestBody ProvinceTable provinceTable) {
        ProvinceTable provinceTables = provinceTableService.updateByObj(provinceTable);
        return ResultUtils.success(provinceTables);
    }


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/deleteById")
    @ApiOperation(value = "deleteById", notes = "通过主键Id进行删除")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true),
            @ApiImplicitParam(dataType = "Integer", name = "id", value = "主键", required = true, defaultValue = "1")
    })
    public ResultBase<ProvinceTable> deleteById(@RequestParam(name = "id") Integer id) {
        provinceTableService.deleteById(id);
        return ResultUtils.success();
    }

}