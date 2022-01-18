package com.cy.store.mapper;


import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

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

    /**
     * 根据用户的uid来修改用户的密码
     * @param uid   用户的id
     * @param password  用户输入的新密码
     * @param modifiedUser  表示修改的执行者
     * @param modifiedTime  表示修改数据的时间
     * @return  返回值为受影响的行数
     */
    Integer updatePasswordByUid(Integer uid,
                                String password,
                                String modifiedUser,
                                Date modifiedTime);

    /**
     * 根据用户的id查询用户的数据
     * @param uid   用户的id
     * @return  如果找到则返回对象，否则返回null值
     */
    User findByUid(Integer uid);

    /**
     * 更新用户的数据信息
     * @param user  用户的数据
     * @return  返回值为受影响的行数
     */
    Integer updateInfoByUid(User user);

    /**
     * @Param("SQL映射文件中#{}占位符的变量名")：解决的问题：
     * 当SQL语句的占位符和映射的接口方法的参数名不一致时，需要将某个参数强行注入到某个占位符变量上时，
     * 可以使用@Param这个注解来标注映射的关系
     *
     * 根据用户的uid值修改用户的头像
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
