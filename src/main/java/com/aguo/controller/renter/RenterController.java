package com.aguo.controller.renter;

import com.aguo.dao.URoleDao;
import com.aguo.entity.URole;
import com.aguo.entity.UUser;
import com.aguo.entity.vol.RenterItemVol;
import com.aguo.entity.vol.UUserVol;
import com.aguo.service.RenterService;
import com.aguo.service.RentingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.PageParam;
import com.aguo.vo.params.RenterParam;
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

//    @RequestMapping("/listRenterItemVol")
//    public List<RenterItemVol> listRenterItemVol(PageParam pageParams){
//        return renterService.listRenterItemVol( pageParams);
//    }

    /** 分页查询租客的所有信息
     * 尝试统一返回格式
     * @return
     */
    @GetMapping("/renterItemVolList")
    public ApiResponse listRenterItemVolv2(@RequestParam("page") Integer page,
                                           @RequestParam("pageSize") Integer pageSize,
                                           RenterParam renterParam){
        return renterService.listRenterItemVol( page,pageSize,renterParam);
    }
    @RequestMapping("/listRenter")
    public List<UUserVol> listUUser(){
        return renterService.listUUser();
    }

    /**
     * 查询租客总数量
     * @return
     */
    @GetMapping("/totalRenter")
    public ApiResponse getTotalRenter(){
        return ApiResponse.success(renterService.getTotalRenter());
    }

    /**
     * 新增租客
     * @param uUser
     * @return
     */
    @PostMapping("/renter")
    public ApiResponse addRenter(UUser uUser){
        System.out.println(uUser);
        return renterService.addRenter(uUser);
    }

    @GetMapping("/listRole")
    public Collection<URole> listRole(){
        return uRoleDao.selectList(new QueryWrapper<>());
    }

    /**
     * 删除租客.
     * 通过用户id UserId
     * @param uUser
     * @return
     */
    @DeleteMapping("/renter")
    public ApiResponse deleteRenter(@RequestBody UUser uUser){
        return ApiResponse.success(renterService.deleteRenterById(uUser.getUserId()));
    }
}
