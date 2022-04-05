package com.aguo.controller.renter;

import com.aguo.dao.URoleDao;
import com.aguo.entity.Renting;
import com.aguo.entity.URole;
import com.aguo.entity.UUser;
import com.aguo.entity.vol.RenterItemVol;
import com.aguo.service.RenterService;
import com.aguo.service.RentingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<RenterItemVol> listRenterItemVol(){
        return renterService.listRenterItemVol();
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
