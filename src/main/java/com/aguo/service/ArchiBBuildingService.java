package com.aguo.service;

import com.aguo.entity.ArchiBBuilding;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.BuildingParam;
import com.aguo.vo.params.PageParam;
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

    /**
     * 分页查询楼盘
     *
     * @param pageparam
     * @param buildingParam
     * @return
     */
    ApiResponse listBuilding(PageParam pageparam,
                             BuildingParam buildingParam);

    /**
     * 统计楼盘的总数
     *
     * @return
     */
    @Deprecated
    ApiResponse getBuildingTotal();

    /**
     * 删除楼盘
     *
     * @param archibBuildingd
     * @return
     */
    ApiResponse deleteBuilding(ArchiBBuilding archibBuilding);

    /**
     * 更新楼盘
     *
     * @param archibBuilding
     * @return
     */
    ApiResponse updateBuilding(ArchiBBuilding archibBuilding);
}

