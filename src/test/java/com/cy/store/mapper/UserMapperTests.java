package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;
import java.util.Date;

// @SpringBootTest：表示标注当前的类是一个测试类，不会随项目一块打包
@SpringBootTest
// @RunWith: 表示启动这个单元测试类(单元测试类是不能够运行的)， 需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class UserMapperTests {
    // idea有检测功能，接口是不能直接创建Bean的(动态代理技术解决)
    @Autowired
    private UserMapper userMapper;
    /**
     * 单元测试方法：就可以单独独立运行，不用启动整个项目，可以做单元测试，提升代码的测试效率
     * 1. 必须被Test注解修饰
     * 2. 返回值类型必须是void
     * 3. 方法的参数列表不指定任何类型
     * 4. 方法的访问修饰符必须为public
     */
    @Test
    public void insert() {
        User user = new User();
        user.setUsername("tim");
        user.setPassword("123");
        Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }

    @Test
    public void findByUsername() {
        User user = userMapper.findByUsername("tim");
        System.out.println(user);
    }

    @Test
    public void updatePasswordByUid() {
       Integer rows = userMapper.updatePasswordByUid(7, "321", "Administrator", new Date());
       System.out.println(rows);
    }

    @Test
    public void findByUid() {
        System.out.println(userMapper.findByUid(7));
    }

}
