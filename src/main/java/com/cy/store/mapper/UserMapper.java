package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.springframework.stereotype.Repository;

/** 用户模块的接口 */
@Repository
public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return  返回受影响的行数（增删改都有返回值，判断是否执行成功）
     */
    Integer insert(User user);

    /**
     * 根据用户名来查询用户的数据
     * @param username  用户名
     * @return  找到对应用户，返回用户数据，如果没有找到，则返回null
     */
    User findByUsername(String username);
}
