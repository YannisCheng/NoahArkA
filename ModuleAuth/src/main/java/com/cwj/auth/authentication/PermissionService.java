package com.cwj.auth.authentication;

import com.cwj.auth.utils.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * PermissionService  自定义权限校验实现，ps取自PermissionService 首字母
 * 参考：https://www.cnblogs.com/dxiaodang/p/14793984.html
 *
 * @author ChengWenjia
 * @date 2022/3/8 14:25
 */
@Slf4j
@Service("ps")
public class PermissionService {


    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        LoginUserDetails loginUser = SecurityUtil.getLoginUser();
        log.warn("hasPermi loginUser: {}", loginUser);
        if (loginUser == null || CollectionUtils.isEmpty(loginUser.getPermissions())) {
            return false;
        }
        return hasPermissions(loginUser.getPermissions(), permission);
    }


    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission  权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission) {
        log.warn("hasPermi hasPermissions permissions: {}, permission: {}", permissions, permission);
        return permissions.contains(ALL_PERMISSION) || permissions.contains(trim(permission));
    }

    private String trim(String str) {
        return (str == null ? "" : str.trim());
    }
}
