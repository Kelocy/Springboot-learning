package com.cy.store.mapper;


import com.cy.store.entity.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {
    // idea有检测功能，接口是不能直接创建Bean的(动态代理技术解决)
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(9);
        cart.setPid(10000039);
        cart.setPrice(1000L);
        cart.setNum(2);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, "Administrator", new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(9, 10000039);
        System.out.println(cart);
    }

    @Test
    public void deleteByCid() {
        cartMapper.deleteByCid(11);
    }

    @Test
    public void findByUid() {
        System.out.println(cartMapper.findVOByUid(9));
    }

    @Test
    public void findByCid() {
        System.out.println(cartMapper.findByCid(2));
    }

    @Test
    public void findVOByCid() {
        Integer[] cids = {1, 2, 3, 4, 5};
        System.out.println(cartMapper.findVOByCid(cids));
    }
}
