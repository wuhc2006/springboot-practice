package com.whc.service.impl;

import com.whc.dao.RoleMenuMapper;
import com.whc.domain.entity.Menu;
import com.whc.domain.entity.RoleMenu;
import com.whc.service.RoleMenuService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/1/12 9:48
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public int deleteByPrimaryKey(Long roleMenuId) {
        return roleMenuMapper.deleteByPrimaryKey(roleMenuId);
    }

    @Override
    public int insert(RoleMenu record) {
        return roleMenuMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleMenu record) {
        return roleMenuMapper.insertSelective(record);
    }

    @Override
    public RoleMenu selectByPrimaryKey(Long roleMenuId) {
        return roleMenuMapper.selectByPrimaryKey(roleMenuId);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleMenu record) {
        return roleMenuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleMenu record) {
        return roleMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Menu> selectMenuByRoleId(Long roleId) {
        return roleMenuMapper.selectMenuByRoleId(roleId);
    }

    @Transactional
    @Override
    public ApiResponseVO<Object> addMenu2Role(Long roleId, List<Long> menuIds) {

        //先清除role-menu关系
        deleteMenuByRoleId(roleId);

        //添加角色关系
        for (Long menuId : menuIds){
            RoleMenu roleMenu = new RoleMenu(roleId, menuId);
            roleMenuMapper.insert(roleMenu);
        }
        return new ApiResponseVO<Object>(200,"成功添加菜单", null);
    }

    @Override
    public void deleteMenuByRoleId(Long roleId) {
        roleMenuMapper.deleteMenuByRoleId(roleId);
    }
}
