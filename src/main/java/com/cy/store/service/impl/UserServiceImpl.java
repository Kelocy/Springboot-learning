package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/** 用户模块业务层的实现类 */
@Service    // @Service注解：将当前类的对象交给Spring来管理，自动创建对象以及对象的维护
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        // 通过user参数来获取传递过来的username
        String username = user.getUsername();
        // 调用findByUsername(username) 判断用户是否被注册过
        User result = userMapper.findByUsername(username);
        // 判断结果集是否为null，不为null则抛出用户名被占用的异常
        if (result != null) {
            // 抛出异常
            throw new UsernameDuplicateException("用户名被占用");
        }

        // 密码加密处理的实现：md5
        // 串 + password + 串 ---- md5进行加密，连续加载三次
        // salt + password + salt ---- 盐值是随机的字符串
        String oldPassword = user.getPassword();
        // 获取盐值（随机生成一个盐值）
        String salt = UUID.randomUUID().toString().toUpperCase();
        // 补全数据，盐值的记录
        user.setSalt(salt);
        // 将密码和盐值作为整体进行加密处理，忽略原密码的强度，提升了安全性
        String md5Password = getMD5Password(oldPassword, salt);
        // 将加密之后的密码重新补全设置到user对象中
        user.setPassword(md5Password);

        // 补全数据：is_delete设置为0
        user.setIsDelete(0);
        // 补全数据：4个日志字段信息
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        // 执行注册业务功能的实现
        Integer rows = userMapper.insert(user);
        if (rows != 1) {
            throw new InsertException("在用户注册过程中产生了未知的异常");
        }
    }

    @Override
    public User login(String username, String password) {
        // 根据用户名称来查询用户的数据是否存在，如果不存在则抛出异常
        User result = userMapper.findByUsername(username);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        // 判断is_delete字段的值是否为1表示被标记为删除
        /*
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
         */

        // 检测用户的密码是否匹配
        // 1. 先获取到数据库中加密后的密码
        String oldPassword = result.getPassword();
        // 2. 和用户传递过来的密码进行比较
        // 2.1 先获取盐值：上一次在注册时自动生成的盐值
        String salt = result.getSalt();
        // 2.2 将用户的密码按照相同的md5算法规则进行加密
        String newMd5Password = getMD5Password(password, salt);
        // 3. 将密码进行比较
        if (!newMd5Password.equals(oldPassword)) {
            throw new PasswordNotMatchException("用户密码错误");
        }

        // 调用mapper层的findByUsername来查询用户的数据，提升系统性能
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());

        // 将当前的用户数据返回
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        // 原始密码和数据库中的密码进行比较
        String oldMD5Password = getMD5Password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(oldMD5Password)) {
            throw new PasswordNotMatchException("密码错误");
        }
        // 将新密码是指到数据库中，将新密码进行加密再去更新
        String newMD5Password = getMD5Password(newPassword, result.getSalt());
        Integer rows = userMapper.updatePasswordByUid(uid, newMD5Password, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public User getByUid(Integer uid) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        User user = new User();
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());
        return user;
    }


    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        user.setUid(uid);
        // user.setUsername(username);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer rows = userMapper.updateInfoByUid(user);
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {
        // 查询当前的用户数据是否存在
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新数据时产生未知的异常");
        }
    }

    /** 定义一个md5算法的加密处理 */
    private String getMD5Password(String password, String salt) {
        // md5加密算法的调用(进行三次)
        for (int i = 0; i < 3; i ++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        // 返回加密之后的密码
        return password;
    }
}
