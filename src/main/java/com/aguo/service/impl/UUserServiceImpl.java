package com.aguo.service.impl;

import com.aguo.dao.URoleDao;
import com.aguo.entity.URole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aguo.dao.UUserDao;
import com.aguo.entity.UUser;
import com.aguo.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//import javax.management.relation.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 用户名表(UUser)表服务实现类
 *
 * @author makejava
 * @since 2021-11-11 13:40:00
 */
@Service("uUserService")
public class UUserServiceImpl extends ServiceImpl<UUserDao, UUser> implements UUserService {

    @Autowired
    private URoleDao uRoleDao;
    @Override
    public UUser queryUserById(Integer id) {
        UUser UUser = getById(id);
        UUser.setRoles(getRoles(UUser.getRoleId()));
        return UUser;
    }
    @Override
    public UUser queryUserByUsername(String username) {
        UUser UUser = getOne(new QueryWrapper<UUser>().eq("username",username));
        if (UUser!=null&&UUser.getRoleId() != null)
        UUser.setRoles(getRoles(UUser.getRoleId()));
        return UUser;
    }

    @Override
    public Boolean addUser(UUser uUser) {
        UUser tempUUser = queryUserByUsername(uUser.getUsername());
        if (tempUUser != null) {
            //表明用户名已被注册
            return false;
        }
        //合理性检验
        boolean matches = Pattern.matches("^[\\u4E00-\\u9FA5A-Za-z0-9]+$", uUser.getUsername());
        boolean matches1 = Pattern.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\\\W]{6,18}$", uUser.getPassword());
        boolean matches2 = Pattern.matches("^1\\d{10}", uUser.getPhoneNumber());
        if (!matches || !matches1 || !matches2){
        //      表示有人篡改了前端js代码的表单检验机制，直接返回用户已存在即可
            return false;
        }
        //正式注册
        //对传输过来的密码进行BCrypt算法加密
        uUser.setPassword(new BCryptPasswordEncoder().encode(uUser.getPassword()));
        if ("".equals(uUser.getName())||uUser.getName()==null)
        uUser.setName(uUser.getUsername());
        //对数据进行持久化
        save(uUser);
        return true;
    }

    private List<URole> getRoles(Integer roleId) {
        //        注入roles
        QueryWrapper<URole> wrapper = new QueryWrapper<>();
        wrapper
                .eq("ROLE_ID", roleId);
        return uRoleDao.selectList(wrapper);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UUser user = queryUserByUsername(username);
        if (null==user) throw new UsernameNotFoundException("用户名不存在");
        return user;
    }

}

