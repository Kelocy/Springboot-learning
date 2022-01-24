package com.cy.store.mapper;

import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/** 表示收货地址持久层的接口 */
public interface AddressMapper {
    /**
     * 插入用户的收货地址数据
     * @param address   收货地址数据
     * @return  受影响的行数
     */
    Integer insert(Address address);

    /**
     * 根据用户的id统计收货地址数据
     * @param uid   用户的id
     * @return  当前用户收货地址的总数
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户的收货地址数据
     * @param uid   用户id
     * @return  收货地址数据
     */
    List<Address> findByUid(Integer uid);
}