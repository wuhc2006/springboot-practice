package com.whc.service;

import com.whc.domain.entity.Supplier;

import java.util.Collections;
import java.util.List;

public interface SupplierService {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    int insertSelective(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Supplier record);

    int updateByPrimaryKey(Supplier record);

    default List<Supplier> list(Supplier supplier) {
        return Collections.emptyList();
    }
}
