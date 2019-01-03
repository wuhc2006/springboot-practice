package com.whc.config.shiro;

import com.whc.domain.entity.Permission;
import com.whc.domain.entity.Role;
import com.whc.domain.entity.User;
import com.whc.domain.entity.UserRole;
import com.whc.service.*;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName MyShiroRealm
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/22 23:08
 * @Version 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String username = principalCollection.getPrimaryPrincipal().toString();
        User user = userService.findByName(username);

        //查询用户具有的角色
        List<UserRole> userRoleList = userRoleService.selectByUserId(user.getId());
        List<Permission> permissionList = new ArrayList<>();
        for (UserRole userRole : userRoleList){
            info.addRole(roleService.selectByPrimaryKey(userRole.getRoleId()).getRoleName());
            permissionList.addAll(permissionService.selectPermissionsByRole(userRole.getRoleId()));
        }

        //权限
        Set<String> permissions = new HashSet<>();
        for (Permission permission : permissionList){
            permissions.add(permission.getName());
        }
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        //获取用户信息
        String username = authenticationToken.getPrincipal().toString();
        User user = userService.findByName(username);
        if (user == null){
            return null;
        }else{
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
