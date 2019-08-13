package com.whc.service.impl;

import com.whc.dao.MenuMapper;
import com.whc.dao.RoleMenuMapper;
import com.whc.domain.entity.Menu;
import com.whc.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/1/12 9:44
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long menuId) {
        roleMenuMapper.deleteByMenuId(menuId);
        return menuMapper.deleteByPrimaryKey(menuId);
    }

    /**
     * 判断是否存在子菜单
     * @param menuId
     * @return
     */
    @Override
    public boolean hasChildMenu(Long menuId){
        Menu condition = new Menu();
        condition.setParentId(menuId);
        List<Menu> childMenus = menuMapper.list(condition);
        return childMenus != null && !childMenus.isEmpty();
    }


    @Override
    public int insert(Menu record) {
        return menuMapper.insert(record);
    }

    @Override
    public int insertSelective(Menu record) {
        return menuMapper.insertSelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(Long menuId) {
        return menuMapper.selectByPrimaryKey(menuId);
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return menuMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> list(Menu menu) {
        return menuMapper.list(menu);
    }

    @Override
    public List<Menu> selectByUserId(Long userId) {
        return menuMapper.selectByUserId(userId);
    }
}
