package com.cy.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {
    @Autowired
    private ICartService cartService;

    @Test
    public void addToCart() {
        // 已存在的一件购物车中数据的更新
        cartService.addToCart(9, 100000421, 5, "Administrator");
    }

    @Test
    public void delete() {
        cartService.delete(10, 9, "test03");
    }
}
