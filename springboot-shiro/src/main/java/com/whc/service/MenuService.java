package com.whc.service;

import com.whc.domain.entity.Menu;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/1/12 9:43
 */
public interface MenuService {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAll();

    List<Menu> list(Menu menu);

    boolean hasChildMenu(Long menuId);

    List<Menu> selectByUserId(Long userId);
}
