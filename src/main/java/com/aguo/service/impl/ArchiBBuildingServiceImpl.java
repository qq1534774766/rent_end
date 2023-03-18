package com.aguo.service.impl;

import com.aguo.dao.ArchiBBuildingDao;
import com.aguo.entity.ArchiBBuilding;
import com.aguo.service.ArchiBBuildingService;
import com.aguo.vo.ApiResponse;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 楼房表(ArchiBBuilding)表服务实现类
 *
 * @author makejava
 * @since 2021-11-20 14:02:07
 */
@Service("archiBBuildingService")
public class ArchiBBuildingServiceImpl extends ServiceImpl<ArchiBBuildingDao, ArchiBBuilding> implements ArchiBBuildingService {

    @Autowired
    private ArchiBBuildingDao archiBBuildingDao;

    @Override
    public ApiResponse addBuilding(ArchiBBuilding archibBuilding) {
        archibBuilding.setName(archibBuilding.getName().trim());
        archibBuilding.setAddress(archibBuilding.getAddress().trim());
        if (StringUtils.isAnyBlank(archibBuilding.getName(), archibBuilding.getAddress())) {
            return ApiResponse.error("信息不完整,创建失败！");
        }
        return ApiResponse.booleanResponse(archiBBuildingDao.insert(archibBuilding) > 0);
    }
}

