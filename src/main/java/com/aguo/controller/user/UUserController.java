package com.aguo.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aguo.entity.UUser;
import com.aguo.service.UUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户名表(UUser)表控制层
 *
 * @author makejava
 * @since 2021-11-11 13:40:00
 */
@RestController
@RequestMapping("uUser")
public class UUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private UUserService uUserService;
//    公开
    @PostMapping("/register")
    public Map<String,String> addUser(UUser uUser){
        Map<String, String> map = new HashMap<>();
        if (!uUserService.addUser(uUser)) {
            //表明用户名已被注册
            map.put("exist","1");
            return map;
        }
        map.put("exist","0");
//        成功注册
        return map;
    }

}

