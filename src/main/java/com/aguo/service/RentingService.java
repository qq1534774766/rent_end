package com.aguo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.aguo.entity.Renting;

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
}

