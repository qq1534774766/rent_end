package com.aguo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aguo.entity.UUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

/**
 * 用户名表(UUser)表服务接口
 *
 * @author makejava
 * @since 2021-11-11 13:40:00
 */
public interface UUserService extends IService<UUser>, UserDetailsService {
    UUser queryUserById(Integer id);
    UUser queryUserByUsername(String username);
    /**
     *
     * @param uUser 等待注册的User
     * @return true 注册成功，false 表示用户名已存在
     */
    Boolean addUser(UUser uUser);

}

