package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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

    Integer deleteByCid(Integer cid);

    // VO：Value Object，值对象，当进行SELECT查询时，查询的结果数据是多张表中的内容，此时发现结果集不能直接使用某个POJO实体类来接收，
    // POJO实体类不能包含多表查询出来的结果。
    // 解决方式是重新去构建一个新的对象，这个对象用户存储所查询出来的结果集对应的映射，把这个对象称之为值对象
    List<CartVO> findVOByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCid(Integer[] cids);
}
