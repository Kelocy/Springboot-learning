package com.cy.store.mapper;

import com.cy.store.entity.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapper {
    /**
     * 根据用户的父代号查询区域信息
     * @param parent    父代号
     * @return  某个父区域下的所有区域列表
     */
    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
