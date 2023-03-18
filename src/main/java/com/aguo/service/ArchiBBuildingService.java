package com.aguo.service;

import com.aguo.entity.ArchiBBuilding;
import com.aguo.vo.ApiResponse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 楼房表(ArchiBBuilding)表服务接口
 *
 * @author makejava
 * @since 2021-11-20 14:02:07
 */
public interface ArchiBBuildingService extends IService<ArchiBBuilding> {

    /**
     * 新增楼盘
     *
     * @param archibBuilding
     * @return
     */
    ApiResponse addBuilding(ArchiBBuilding archibBuilding);
}

