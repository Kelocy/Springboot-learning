package com.cy.store.service;

import com.cy.store.entity.Address;

/** 收货地址业务层的接口 */
public interface IAddressService {
    void addNewAddress(Integer uid, String username, Address address);
}
