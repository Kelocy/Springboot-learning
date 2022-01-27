package com.cy.store.mapper;


import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

// @SpringBootTest：表示标注当前的类是一个测试类，不会随项目一块打包
@SpringBootTest
// @RunWith: 表示启动这个单元测试类(单元测试类是不能够运行的)， 需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    // idea有检测功能，接口是不能直接创建Bean的(动态代理技术解决)
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(9);
        order.setRecvName("DIO");
        order.setRecvPhone("68248743");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10000010);
        orderItem.setTitle("戴尔Dell 燃700R1605学习版银色");
        orderMapper.insertOrderItem(orderItem);
    }
}
