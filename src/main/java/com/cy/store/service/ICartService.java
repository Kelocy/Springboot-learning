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

    /**
     * 更新用户的购物车数据的数量
     * @param cid
     * @param uid
     * @param username
     * @return  增加成功后新的数量
     */
    Integer addNum(Integer cid, Integer uid, String username);

    Integer reduceNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCid(Integer uid, Integer[] cids);
}
