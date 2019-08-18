package com.whc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whc.domain.entity.Supplier;
import com.whc.service.SupplierService;
import com.whc.vo.ApiResponseVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Administrator
 * @date 2019/8/17 16:12
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping("/list")
    public ApiResponseVO<Object> list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "100") int pageSize, Supplier supplier) {
        PageHelper.startPage(page, pageSize);
        PageInfo<Supplier> pageInfo = new PageInfo<>(supplierService.list(supplier));
        return ApiResponseVO.success("查找成功", pageInfo.getList(), (int) pageInfo.getTotal());
    }

    @ApiOperation(value = "查找供应商", tags = "查找供应商")
    @GetMapping("/select/{id}")
    public ApiResponseVO<Object> selectOne(@PathVariable Long id) {
        return ApiResponseVO.success("查找成功", supplierService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "新增供应商", tags = "新增供应商")
    @PostMapping("/insert")
    public ApiResponseVO<Object> insertOne(@NotNull Supplier supplier) {
        supplier.setAddTime(new Date());
        this.supplierService.insert(supplier);
        return ApiResponseVO.success("新增成功", supplier);
    }

    @ApiOperation(value = "修改供应商", tags = "修改供应商")
    @PostMapping("/update")
    public ApiResponseVO<Object> update(@NotNull Supplier supplier) {
        if (supplier.getId() == null) {
            return new ApiResponseVO<>(500, "不合法的供应商!", supplier);
        }
        supplier.setUpdateTime(new Date());
        this.supplierService.updateByPrimaryKey(supplier);
        return ApiResponseVO.success("修改成功", supplier);
    }

    @ApiOperation(value = "删除供应商", tags = "删除供应商")
    @DeleteMapping("/delete/{id}")
    public ApiResponseVO<Object> deleteById(@PathVariable Long id) {
        this.supplierService.deleteByPrimaryKey(id);
        return ApiResponseVO.success("删除成功", id);
    }
}
