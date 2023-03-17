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
import com.aguo.untils.NumberUntil;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.PageParam;
import com.aguo.vo.params.RenterParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Administrator
 */
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
    public ApiResponse listRenterItemVol(Integer pageNo, Integer pageSize, RenterParam renterParam) {
        List<RenterItemVol> list = new ArrayList<>();
        //分页查询
        //新建一个page对象
        Page<UUserVol> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<UUserVol> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(renterParam.getUsername()),UUserVol::getUsername,renterParam.getUsername())
                .eq(NumberUntil.isNumber(renterParam.getName()),UUserVol::getName,renterParam.getName())
                .eq(NumberUntil.isNumber(renterParam.getPhoneNumber()),UUserVol::getPhoneNumber,renterParam.getPhoneNumber());
        uUserVolDao.selectPage(page,queryWrapper);
        List<UUserVol> list1 = page.getRecords();
        for (UUserVol uUserVol : list1) {
            RenterItemVol renterItemVol = new RenterItemVol();
            Boolean rentState = rentingService.renterRentState(uUserVol.getUserId());
            //筛选是否租房的前端的条件。
            if(renterParam.getRentState()!=null&&
                    !renterParam.getRentState().equals(rentState)){
                //有筛选时，与筛选不同则过滤掉不要
                continue;
            }
            renterItemVol.setRentState(rentState);
            renterItemVol.setuUserVol(uUserVol);
            URole uRole = uRoleDao.selectById(uUserVol.getRoleId());
            renterItemVol.setuRole(uRole);
            list.add(renterItemVol);
        }
        return ApiResponse.success(list);
    }

    /**
     *
     * @param uUser
     * @return false:身份证格式错误或用户名已存在
     */
    @Override
    public ApiResponse addRenter(UUser uUser) {
        if (uUser.getIdentity()!=null&&!"".equals(uUser.getIdentity())) {
            boolean matches = Pattern.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$", uUser.getIdentity());
            if (!matches) {
                return ApiResponse.error("身份证格式错误");
            }
        }
        return uUserService.addUser(uUser)?ApiResponse.success(null):ApiResponse.error("用户名已存在");
    }

    @Override
    @Transactional
    public ApiResponse deleteRenterById(Integer userId) {
//        删除所有租用关系
        QueryWrapper<Renting> wrapper = new QueryWrapper<>();
        wrapper.eq("USER_ID",userId);
        rentingService.remove(wrapper);
//      删除用户
        return uUserService.removeById(userId)?ApiResponse.success():ApiResponse.error("删除失败");
    }

    @Override
    public Integer getTotalRenter() {
        return uUserVolDao.selectCount(null);
    }

    @Override
    public List<UUserVol> listUUser() {
        return uUserVolDao.selectList(null);
    }

}
