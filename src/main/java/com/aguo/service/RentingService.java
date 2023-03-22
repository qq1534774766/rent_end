package com.aguo.service;

import com.aguo.entity.Renting;
import com.aguo.entity.vol.RentingVol;
import com.aguo.vo.ApiResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 租房关系表(Renting)表服务接口
 *
 * @author makejava
 * @since 2021-11-20 16:27:43
 */
public interface RentingService extends IService<Renting> {
    Renting houseState(int userId, int roomId);

    Renting houseOneState(int roomId);

    Boolean renterRentState(int userId);

    /**
     * 根据房屋ID查是否租用中
     *
     * @param roomId
     * @return
     */
    RentingVol roomRentState(Integer roomId);

    /**
     * 通过房屋名删除所有记录
     *
     * @param roomId
     * @return
     */
    Boolean deleteRentingByRoomId(Integer roomId);

    /**
     * 更新房屋信息时，处理房屋
     *
     * @param rentingVol
     * @return
     */
    ApiResponse handleRenting(RentingVol rentingVol);
}

