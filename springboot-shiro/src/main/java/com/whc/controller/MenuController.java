package com.whc.controller;

import com.whc.domain.entity.Menu;
import com.whc.service.MenuService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单控制器
 *
 * @author Administrator
 * @date 2019/1/12 9:50
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/selectAll")
    public ApiResponseVO<Object> selectAll() {
        return new ApiResponseVO<>(200, "查询成功！", menuService.selectAll());
    }

    @GetMapping("/selectOne")
    public ApiResponseVO<Object> selectOne(@RequestParam Long menuId) {
        Menu menu = menuService.selectByPrimaryKey(menuId);
        if (menu != null) {
            return new ApiResponseVO<>(200, "查询成功！", menu);
        } else {
            return new ApiResponseVO<>(500, "未找到符合条件的记录!", menuId);
        }
    }

    @PostMapping("/add")
    public ApiResponseVO<Object> addMenu(@RequestParam Menu menu) {
        return new ApiResponseVO<>(200, "添加成功！", null);
    }

    @PostMapping("/update")
    public ApiResponseVO<Object> updateMenu(@RequestParam Menu menu) {
        menuService.updateByPrimaryKeySelective(menu);
        return new ApiResponseVO<>(200, "更新成功！", null);
    }

    @PostMapping("/delete")
    public ApiResponseVO<Object> delete(@RequestParam Long menuId) {
        menuService.deleteByPrimaryKey(menuId);
        return new ApiResponseVO<>(200, "删除成功！", null);
    }

}
