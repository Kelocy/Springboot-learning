package com.cy.store.service;


import com.cy.store.entity.Address;
import com.cy.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// @SpringBootTest：表示标注当前的类是一个测试类，不会随项目一块打包
@SpringBootTest
// @RunWith: 表示启动这个单元测试类(单元测试类是不能够运行的)， 需要传递一个参数，必须是SpringRunner的实例类型
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    // idea有检测功能，接口是不能直接创建Bean的(动态代理技术解决)
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getByParent() {
        // 86表示中国，所有省的父代号都是86
        List<District> list = districtService.getByParent("86");
        for (District d: list) {
            System.err.println(d);
        }
    }


}
