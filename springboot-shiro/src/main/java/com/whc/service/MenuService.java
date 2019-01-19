package com.whc.service;

import com.whc.domain.entity.Menu;

import java.util.List;

/**
 * @ClassName MenuService
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/12 9:43
 * @Version 1.0
 */
public interface MenuService {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> selectAll();
}
