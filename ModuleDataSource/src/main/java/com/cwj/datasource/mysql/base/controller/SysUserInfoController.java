package com.cwj.datasource.mysql.base.controller;

import com.cwj.common.base.result.ResultBase;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.service.SysUserInfoService;
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
 * 用户信息表(SysUser)表控制层
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Api(tags = "基础库-用户信息表")
@RestController
@RequestMapping("/sysUser")
public class SysUserInfoController {

    /**
     * 服务对象
     */
    @Autowired
    private SysUserInfoService sysUserService;


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
    public ResultBase<SysUser> findById(@RequestParam(name = "id") Long id) {
        ResultBase<SysUser> baseResult = null;
        boolean present = sysUserService.findById(id).isPresent();
        if (present) {
            baseResult = ResultUtils.success(sysUserService.findById(id).get());
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
    public ResultBase<List<SysUser>> findAll() {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        List<SysUser> sysUsers = sysUserService.findAll(sort);
        return ResultUtils.success(sysUsers);
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
    public ResultBase<Page<SysUser>> findByPage(@RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
        // 通过主键进行排序
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<SysUser> sysUsers = sysUserService.findByPage(pageable);
        return ResultUtils.success(sysUsers);
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
    public ResultBase<SysUser> saveOne(@RequestBody SysUser sysUser) {
        SysUser sysUsers = sysUserService.saveOne(sysUser);
        return ResultUtils.success(sysUsers);
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
    public ResultBase<SysUser> updateByObj(@RequestBody SysUser sysUser) {
        SysUser sysUsers = sysUserService.updateByObj(sysUser);
        return ResultUtils.success(sysUsers);
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
    public ResultBase<SysUser> deleteById(@RequestParam(name = "id") Long id) {
        sysUserService.deleteById(id);
        return ResultUtils.success();
    }

}