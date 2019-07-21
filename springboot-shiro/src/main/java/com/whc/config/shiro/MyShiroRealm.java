package com.whc.config.shiro;

import com.whc.domain.entity.Permission;
import com.whc.domain.entity.User;
import com.whc.domain.entity.UserRole;
import com.whc.service.*;
import com.whc.util.JwtToken;
import com.whc.util.JwtUtil;
import org.apache.shiro.authc.*;
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
 * @author Administrator
 * @date 2018/12/22 23:08
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
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        String username = JwtUtil.getUsername(principal.toString());
        User user = userService.findByName(username);

        if (user == null){
            throw new UnknownAccountException();
        }

        //查询用户具有的角色
        List<UserRole> userRoleList = userRoleService.selectByUserId(user.getId());
        List<Permission> permissionList = new ArrayList<>();

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (UserRole userRole : userRoleList){
            info.addRole(roleService.selectByPrimaryKey(userRole.getRoleId()).getName());
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
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {

        String token = (String) auth.getCredentials();
        //解密获取用户信息,用于和数据库进行比对
        String username = JwtUtil.getUsername(token);
        if (username == null){
            throw new AuthenticationException("token invalid!");
        }
        User user = userService.findByName(username);
        if (user == null){
            throw new AuthenticationException("user is not existed");
        }
        if (!JwtUtil.verify(token, username, user.getPassword())){
            throw new AuthenticationException("username or password error");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
