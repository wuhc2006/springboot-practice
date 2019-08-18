package com.whc.service.impl;

import com.whc.dao.SupplierMapper;
import com.whc.domain.entity.Supplier;
import com.whc.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/8/17 16:48
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return supplierMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Supplier record) {
        return supplierMapper.insert(record);
    }

    @Override
    public int insertSelective(Supplier record) {
        return supplierMapper.insertSelective(record);
    }

    @Override
    public Supplier selectByPrimaryKey(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Supplier record) {
        return supplierMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Supplier record) {
        return supplierMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Supplier> list(Supplier supplier) {
        return supplierMapper.list(supplier);
    }
}
