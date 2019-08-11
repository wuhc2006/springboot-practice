package com.whc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @GetMapping("/list")
    public ApiResponseVO<Object> selectAll(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "100") int pageSize, Menu menu) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Menu> pageInfo = new PageInfo<>(menuService.list(menu));
        return new ApiResponseVO<>(200, "查询成功！", pageInfo.getList(), (int)pageInfo.getTotal());
    }

    @GetMapping("/selectOne/{id}")
    public ApiResponseVO<Object> selectOne(@PathVariable Long id) {
        Menu menu = menuService.selectByPrimaryKey(id);
        if (menu != null) {
            return new ApiResponseVO<>(200, "查询成功！", menu);
        } else {
            return new ApiResponseVO<>(500, "未找到符合条件的记录!", id);
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

    @PostMapping("/delete/{id}")
    public ApiResponseVO<Object> delete(@PathVariable Long id) {
        menuService.deleteByPrimaryKey(id);
        return new ApiResponseVO<>(200, "删除成功！", null);
    }

}
