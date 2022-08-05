package com.cwj.datasource.mysql.fnclass.controller;

import com.cwj.common.result.ResultBase;
import com.cwj.common.result.ResultUtils;
import com.cwj.datasource.mysql.fnclass.entity.Table0;
import com.cwj.datasource.mysql.fnclass.service.Table0Service;
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
 * 行政1班级表(Table0)表控制层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:41
 */
@Api(tags = "49级-行政1班级表")
@RestController
@RequestMapping("/table0")
public class Table0Controller {

    /**
     * 服务对象
     */
    @Autowired
    private Table0Service table0Service;


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
    public ResultBase<Table0> findById(@RequestParam(name = "id") Integer id) {
        ResultBase<Table0> baseResult = null;
        boolean present = table0Service.findById(id).isPresent();
        if (present) {
            baseResult = ResultUtils.success(table0Service.findById(id).get());
        } else {
            baseResult = ResultUtils.errorData();
        }
        return baseResult;
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @GetMapping("/findByIdCard")
    @ApiOperation(value = "findByIdCard", notes = "通过 idcard_stu：身份证号 查询单条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true),
            @ApiImplicitParam(dataType = "String", name = "idCard", value = "身份证号", required = true, defaultValue = "370784199211276833")
    })
    public ResultBase<Table0> findByIdCard(@RequestParam(name = "idCard") String idCard) {
        ResultBase<Table0> baseResult = null;
        boolean present = table0Service.findByIdCard(idCard).isPresent();
        if (present) {
            baseResult = ResultUtils.success(table0Service.findByIdCard(idCard).get());
        } else {
            baseResult = ResultUtils.errorData();
        }
        return baseResult;
    }

    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @GetMapping("/findByStuId")
    @ApiOperation(value = "findByStuId", notes = "通过 stu_id：学号 查询单条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true),
            @ApiImplicitParam(dataType = "String", name = "stuId", value = "学号", required = true, defaultValue = "2009370301000530037")
    })
    public ResultBase<Table0> findByStuId(@RequestParam(name = "stuId") String stuId) {
        ResultBase<Table0> baseResult = null;
        boolean present = table0Service.findByStuId(stuId).isPresent();
        if (present) {
            baseResult = ResultUtils.success(table0Service.findByStuId(stuId).get());
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
    public ResultBase<List<Table0>> findAll() {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<Table0> table0s = table0Service.findAll(sort);
        return ResultUtils.success(table0s);
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
    public ResultBase<Page<Table0>> findByPage(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Table0> table0s = table0Service.findByPage(pageable);
        return ResultUtils.success(table0s);
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
    public ResultBase<Table0> saveOne(@RequestBody Table0 table0) {
        Table0 table0s = table0Service.saveOne(table0);
        return ResultUtils.success(table0s);
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
    public ResultBase<Table0> updateByObj(@RequestBody Table0 table0) {
        Table0 table0s = table0Service.updateByObj(table0);
        return ResultUtils.success(table0s);
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
    public ResultBase<Table0> deleteById(@RequestParam(name = "id") Integer id) {
        table0Service.deleteById(id);
        return ResultUtils.success();
    }

}