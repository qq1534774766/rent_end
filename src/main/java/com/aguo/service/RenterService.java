package com.aguo.service;

import com.aguo.entity.UUser;
import com.aguo.entity.vol.RenterItemVol;
import com.aguo.entity.vol.UUserVol;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.PageParam;
import com.aguo.vo.params.RenterParam;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface RenterService {
    /**
     * 分页获取租客列表
     * @param pageParams
     * @return
     */
//    List<RenterItemVol> listRenterItemVol(PageParam pageParams);

    /**
     * 新增租客
     * @param uUser
     * @return T新增成功，F新增失败
     */
    ApiResponse addRenter(UUser uUser);

    /**
     * 删除用户
     * @param userId
     * @return t成功 f失败
     */
    ApiResponse deleteRenterById(Integer userId);

    /**
     * 获取租客的总数量
     * @return
     */
    Integer getTotalRenter();

    /**
     * 列出所有的用户信息
     * @return
     */
    List<UUserVol> listUUser();

    /**
     * 分页列出租客信息以及租房的信息，版本2
     *
     * @param page
     * @param pageSize
     * @param pageParams
     * @return
     */
    ApiResponse listRenterItemVol(Integer page, Integer pageSize, RenterParam renterParam);

    /**
     * 更新租客
     * @param uUser
     * @return
     */
    ApiResponse updateRenter(UUser uUser);
}
