package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/** 收货地址业务层的接口 */
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    /**
     * 修改某个用户的某条收货地址数据为默认收货地址
     * @param aid   收货地址的id
     * @param uid   用户的id
     * @param username  表示修改执行的人
     */
    void setDefault(Integer aid, Integer uid, String username);
}
