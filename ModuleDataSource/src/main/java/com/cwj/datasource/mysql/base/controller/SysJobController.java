package com.cwj.datasource.mysql.base.controller;

import com.cwj.common.result.ResultBase;
import com.cwj.common.result.ResultUtils;
import com.cwj.datasource.mysql.base.entity.SysJob;
import com.cwj.datasource.mysql.base.service.SysJobService;
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
 * 定时任务调度表(SysJob)表控制层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Api(tags = "基础库-定时任务调度表")
@RestController
@RequestMapping("/sysJob")
public class SysJobController {

    /**
     * 服务对象
     */
    @Autowired
    private SysJobService sysJobService;


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
            @ApiImplicitParam(dataType = "Long", name = "id", value = "主键", required = true, defaultValue = "1")
    })
    public ResultBase<SysJob> findById(@RequestParam(name = "id") Long id) {
        ResultBase<SysJob> baseResult = null;
        boolean present = sysJobService.findById(id).isPresent();
        if (present) {
            baseResult = ResultUtils.success(sysJobService.findById(id).get());
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
    public ResultBase<List<SysJob>> findAll() {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<SysJob> sysJobs = sysJobService.findAll(sort);
        return ResultUtils.success(sysJobs);
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
    public ResultBase<Page<SysJob>> findByPage(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SysJob> sysJobs = sysJobService.findByPage(pageable);
        return ResultUtils.success(sysJobs);
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
    public ResultBase<SysJob> saveOne(@RequestBody SysJob sysJob) {
        SysJob sysJobs = sysJobService.saveOne(sysJob);
        return ResultUtils.success(sysJobs);
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
    public ResultBase<SysJob> updateByObj(@RequestBody SysJob sysJob) {
        SysJob sysJobs = sysJobService.updateByObj(sysJob);
        return ResultUtils.success(sysJobs);
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
            @ApiImplicitParam(dataType = "Long", name = "id", value = "主键", required = true, defaultValue = "1")
    })
    public ResultBase<SysJob> deleteById(@RequestParam(name = "id") Long id) {
        sysJobService.deleteById(id);
        return ResultUtils.success();
    }

}