package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

public interface ICartService {
    /**
     * 将商品添加到购物车中
     * @param uid   用户id
     * @param pid   商品id
     * @param amount    新增数量
     * @param username  用户名(修改者)
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    List<CartVO> getVOByUid(Integer uid);
}
