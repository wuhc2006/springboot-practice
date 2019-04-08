package com.whc.sender;

import com.whc.domain.entity.Menu;
import com.whc.service.MenuService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName MenuController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/12 9:50
 * @Version 1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/selectAll")
    public ApiResponseVO<Object> selectAll(){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        List<Menu> menuList = menuService.selectAll();
        apiResponseVO.setCode(200);
        apiResponseVO.setMsg("查询成功！");
        apiResponseVO.setData(menuList);
        return apiResponseVO;
    }

    @GetMapping("/selectOne")
    public ApiResponseVO<Object> selectOne(Long menuId){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (menuId != null){
            Menu menu = menuService.selectByPrimaryKey(menuId);
            apiResponseVO.setCode(200);
            apiResponseVO.setMsg("查询成功！");
            apiResponseVO.setData(menu);
        }else{
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("入参为空");
        }
        return apiResponseVO;
    }

    @PostMapping("/add")
    public ApiResponseVO<Object> addMenu(Menu menu){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (menu != null){
            menuService.insertSelective(menu);
            apiResponseVO.setCode(200);
            apiResponseVO.setMsg("添加成功！");
        }else{
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("入参为空");
        }
        return apiResponseVO;
    }

    @PostMapping("/update")
    public ApiResponseVO<Object> updateMenu(Menu menu){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (menu!=null){
            menuService.updateByPrimaryKeySelective(menu);
            apiResponseVO.setCode(200);
            apiResponseVO.setMsg("添加成功！");
        }else{
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("入参为空");
        }
        return apiResponseVO;
    }

    @PostMapping("/delete")
    public ApiResponseVO<Object> delete(Long menuId){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (menuId!=null){
            menuService.deleteByPrimaryKey(menuId);
            apiResponseVO.setCode(200);
            apiResponseVO.setMsg("添加成功！");
        }else{
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("入参为空");
        }
        return apiResponseVO;
    }

}
