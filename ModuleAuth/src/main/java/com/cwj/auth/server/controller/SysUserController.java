package com.cwj.auth.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.cwj.auth.server.service.SysEmailService;
import com.cwj.auth.server.service.SysUserService;
import com.cwj.auth.server.vo.login.LoginBody;
import com.cwj.auth.server.vo.login.RegisterBody;
import com.cwj.auth.server.vo.login.ResetPwdBody;
import com.cwj.auth.utils.SecurityUtil;
import com.cwj.common.base.result.ResultBase;
import com.cwj.common.base.result.ResultUtils;
import com.cwj.datasource.mysql.base.entity.SysUser;
import com.cwj.datasource.mysql.base.service.SysMenuService;
import com.cwj.datasource.mysql.base.service.SysRoleMenuService;
import com.cwj.datasource.mysql.base.service.SysUserInfoService;
import com.cwj.datasource.redis.RedisCacheUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

/**
 * SysUserInfoController  系统层级操作用户
 *
 * @author ChengWenjia
 * @date 2022/2/10 13:36
 */
@RestController
@Api(tags = "登录、注册相关")
public class SysUserController{

    @Autowired
    SysUserService sysUserService;

    @Resource
    SysUserInfoService sysUserInfoService;

    @Autowired
    SysEmailService sysEmailService;

    @Resource
    private RedisCacheUtil redisCacheUtil;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 后门验证码
     */
    @Value("${email.universalCode}")
    private String universalCode;


    @PostMapping("/register")
    @ApiOperation(value = "register", notes = "注册新用户。1：邮箱注册；2：手机注册")
    public ResultBase register(@RequestBody RegisterBody user) {
        return sysUserService.registryUser(user);
    }


    @GetMapping("/emailExists")
    @ApiOperation(value = "emailExists", notes = "邮箱是否已经存在")
    public ResultBase emailExists(@RequestParam String mail) {
        return sysEmailService.emailExists(mail);
    }


    @GetMapping("/verifyEmailCode")
    @ApiOperation(value = "verifyEmailCode", notes = "在指定时间内验证邮箱验证码")
    public ResultBase verifyEmailCode(@RequestParam String mail, @RequestParam String verifyCode) {
        return sysEmailService.verifyEmailCode(mail, verifyCode);
    }


    @GetMapping("/getEmailVerifyCode")
    @ApiOperation(value = "getEmailVerifyCode", notes = "用户注册时：生成邮箱验证码、确定邮箱验证码有效时长")
    public ResultBase getEmailVerifyCode(@RequestParam String mail) {
        return sysEmailService.sendEmailVerifyCode(mail);
    }

    @PostMapping("/login")
    // @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)})
    @ApiOperation(value = "login", notes = "用户登录。1：邮箱登录；2：手机登录")
    public ResultBase login(@RequestBody LoginBody loginBody) {
        return sysUserService.login(loginBody);
    }


    @GetMapping("/userLogout")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)})
    @ApiOperation(value = "userLogout", notes = "用户登出")
    public ResultBase logout() {
        return sysUserService.logout();
    }


    @PostMapping("/resetPwd")
    @ApiOperation(value = "resetPwd", notes = "修改密码")
    public ResultBase resetPwd(@RequestBody ResetPwdBody resetPwdBody) {
        ResultBase resultMap = null;
        SysUser user = sysUserInfoService.selectUserByUserEmail(resetPwdBody.getMail());
        if (user == null) {
            resultMap = ResultUtils.errorData("当前邮箱不存在，请确认后再输入。");
        } else {
            // 校验码
            if (resetPwdBody.getVerifyCode().equals(universalCode)) {
                // 后门验证码
                resultMap = doResetPwd(user, resetPwdBody.getNewPassword());
            } else {
                String currentVerifyCode = redisCacheUtil.getCacheObject(resetPwdBody.getMail());
                if (currentVerifyCode == null) {
                    resultMap = ResultUtils.errorData("邮箱验证码已经超时。");
                } else {
                    if (currentVerifyCode.equals(resetPwdBody.getVerifyCode())) {
                        resultMap = doResetPwd(user, resetPwdBody.getNewPassword());
                    } else {
                        resultMap = ResultUtils.errorData("邮箱验证码错误。");
                    }
                }
            }
        }

        return resultMap;
    }

    private ResultBase doResetPwd(SysUser user, String newPassword) {
        user.setPassword(SecurityUtil.encryptPassword(newPassword));
        SysUser metaUser = sysUserInfoService.updateByObj(user);
        return metaUser == null ? ResultUtils.errorData("密码修改失败。") : ResultUtils.success("密码修改成功。");
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)})
    @ApiOperation(value = "getInfo", notes = "获取用户个人信息")
    public ResultBase getUserInfo() {
        ResultBase resultMap = null;
        JSONObject object = new JSONObject();
        SysUser user = SecurityUtil.getLoginUser().getSysUser();
        // 角色集合
        Set<String> roles = sysRoleMenuService.getRole(user);
        // 权限集合
        Set<String> permissions = sysRoleMenuService.getMenu(user);

        object.put("user", user);
        object.put("roles", roles);
        object.put("permissions", permissions);
        resultMap = ResultUtils.success(object);
        return resultMap;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    //@GetMapping("/getRouters")
    //@ApiImplicitParams({@ApiImplicitParam(paramType = "header", dataType = "String", name = "Authorization", value = "token标记", required = true)})
    //@ApiOperation(value = "getRouters", notes = "获取当前用户对应的Web管理端路由信息")
    //public ResultBase getUserRouters() {
    //    Long userId = SecurityUtil.getUserId();
    //    List<SysUser> menus = sysMenuService.selectMenuTreeByUserId(userId);
    //    List<RouterVo> routerVos = sysMenuService.buildMenus(menus);
    //    return ResultUtils.success(routerVos);
    //}
}
