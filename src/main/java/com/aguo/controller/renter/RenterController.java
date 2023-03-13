package com.aguo.controller.renter;

import com.aguo.dao.URoleDao;
import com.aguo.entity.URole;
import com.aguo.entity.UUser;
import com.aguo.entity.vol.RenterItemVol;
import com.aguo.entity.vol.UUserVol;
import com.aguo.service.RenterService;
import com.aguo.service.RentingService;
import com.aguo.vo.PageParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/manage")
public class RenterController {
    @Autowired
    private URoleDao uRoleDao;
    @Autowired
    private RenterService renterService;
    @Autowired
    private RentingService rentingService;

    @RequestMapping("/listRenterItemVol")
    public List<RenterItemVol> listRenterItemVol(PageParams pageParams){
        System.out.println(pageParams);
        return renterService.listRenterItemVol( pageParams);
    }
    @RequestMapping("/listRenter")
    public List<UUserVol> listUUser(){
        return renterService.listUUser();
    }

    @RequestMapping("getTotalRenter")
    public Integer getTotalRenter(){
        return renterService.getTotalRenter();
    }

    @PostMapping("addRenter")
    public Boolean addRenter(UUser uUser){
        return renterService.addRenter(uUser);
    }
    @GetMapping("/listRole")
    public Collection<URole> listRole(){
        return uRoleDao.selectList(new QueryWrapper<>());
    }

    @GetMapping("/deleteRenter")
    public Boolean deleteRenter(Integer userId){
        return renterService.deleteRenterById(userId);
    }
}
