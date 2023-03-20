package com.aguo.service.impl;

import com.aguo.dao.ArchiBBuildingDao;
import com.aguo.entity.ArchiBBuilding;
import com.aguo.service.ArchiBBuildingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.BuildingParam;
import com.aguo.vo.params.PageParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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

    @Override
    public ApiResponse listBuilding(PageParam pageparam, BuildingParam buildingParam) {
        //分页
        Page<ArchiBBuilding> page = new Page<>(pageparam.getPage(), pageparam.getPageSize());
        //条件
        LambdaQueryWrapper<ArchiBBuilding> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(buildingParam.getName()), ArchiBBuilding::getName, buildingParam.getName().trim());
        queryWrapper.like(StringUtils.isNotBlank(buildingParam.getAddress()), ArchiBBuilding::getAddress, buildingParam.getAddress().trim());
        Page<ArchiBBuilding> pageResult = archiBBuildingDao.selectPage(page, queryWrapper);
        List<ArchiBBuilding> list = pageResult.getRecords();
        Integer count = archiBBuildingDao.selectCount(queryWrapper);
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("list", list);
        map.put("count", count);
        return ApiResponse.success(map);
    }

    @Override
    public ApiResponse getBuildingTotal() {
        Integer count = archiBBuildingDao.selectCount(null);
        return ApiResponse.success(count);
    }

    @Override
    public ApiResponse deleteBuilding(ArchiBBuilding archibBuilding) {
        int i = archiBBuildingDao.deleteById(archibBuilding.getBuildingId());
        return ApiResponse.booleanResponse(i > 0, "删除失败");
    }

    @Override
    public ApiResponse updateBuilding(ArchiBBuilding archibBuilding) {
        int i = archiBBuildingDao.updateById(archibBuilding);
        return ApiResponse.booleanResponse(i > 0, "更新失败");

    }
}

