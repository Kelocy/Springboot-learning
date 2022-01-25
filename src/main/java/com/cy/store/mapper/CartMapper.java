package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart  购物车数据
     * @return  受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 更新购物车某件商品的数量
     * @param cid   购物车数据id
     * @param num   更新的数量
     * @param modifiedUser  修改者
     * @param modifiedTime  修改时间
     * @return  受影响的行数
     */
    Integer updateNumByCid(Integer cid, Integer num, String modifiedUser, Date modifiedTime);

    /**
     * 根据用户的id和商品的id来查询购物车钟的数据
     * @param uid   用户id
     * @param pid   商品id
     * @return
     */
    Cart findByUidAndPid(Integer uid, Integer pid);
}
