package com.whc.sender;

import com.whc.domain.entity.UserRole;
import com.whc.service.UserRoleService;
import com.whc.vo.ApiResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserRoleController
 * @Description TODO
 * @Author Administrator
 * @Date 2018/12/26 22:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 通过user获取角色
     * @return
     */
    @RequestMapping("/getRoleByUser")
    public ApiResponseVO<Object> getRoleByUser(Long userId){
        ApiResponseVO<Object> apiResponseVO = new ApiResponseVO<>();
        if (null == userId){
            apiResponseVO.setCode(500);
            apiResponseVO.setMsg("输入的参数不合法");
            return apiResponseVO;
        }

        List<UserRole> list = userRoleService.selectByUserId(userId);
        apiResponseVO.setCode(200);
        apiResponseVO.setMsg("查询成功");
        apiResponseVO.setTotal(list.size());
        apiResponseVO.setData(list);

        return apiResponseVO;
    }
}
