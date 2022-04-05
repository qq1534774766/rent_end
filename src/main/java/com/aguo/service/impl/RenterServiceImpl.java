package com.aguo.service.impl;

import com.aguo.dao.URoleDao;
import com.aguo.dao.UUserVolDao;
import com.aguo.entity.Renting;
import com.aguo.entity.URole;
import com.aguo.entity.UUser;
import com.aguo.entity.vol.RenterItemVol;
import com.aguo.entity.vol.UUserVol;
import com.aguo.service.RenterService;
import com.aguo.service.UUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class RenterServiceImpl implements RenterService {

    @Autowired
    private UUserService uUserService;
    @Autowired
    private URoleDao uRoleDao;
    @Autowired
    private RentingServiceImpl rentingService;
    @Autowired
    private UUserVolDao uUserVolDao;

    @Override
    public List<RenterItemVol> listRenterItemVol() {
        List<RenterItemVol> list = new ArrayList<>();
        QueryWrapper<UUserVol> wrapper = new QueryWrapper<>();
        List<UUserVol> list1 = uUserVolDao.selectList(wrapper);
        for (UUserVol uUserVol : list1) {
            RenterItemVol renterItemVol = new RenterItemVol();
            renterItemVol.setuUserVol(uUserVol);
            URole uRole = uRoleDao.selectById(uUserVol.getRoleId());
            renterItemVol.setuRole(uRole);
            Boolean rentState = rentingService.renterRentState(uUserVol.getUserId());
            renterItemVol.setRentState(rentState);
            list.add(renterItemVol);
        }
        return list;
    }

    /**
     *
     * @param uUser
     * @return false:身份证格式错误或用户名已存在
     */
    @Override
    public Boolean addRenter(UUser uUser) {
        if (uUser.getIdentity()!=null&&!"".equals(uUser.getIdentity())) {
            boolean matches = Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$", uUser.getIdentity());
            if (!matches) {
                return false;//身份证格式错误
            }
        }
        return uUserService.addUser(uUser);
    }

    @Override
    @Transactional
    public Boolean deleteRenterById(Integer userId) {
//        删除所有租用关系
        QueryWrapper<Renting> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_ID",userId);
        rentingService.remove(wrapper);
//      删除用户
        return uUserService.removeById(userId);
    }
}
