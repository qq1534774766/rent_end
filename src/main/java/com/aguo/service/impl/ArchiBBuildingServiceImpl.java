package com.aguo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aguo.dao.ArchiBBuildingDao;
import com.aguo.entity.ArchiBBuilding;
import com.aguo.service.ArchiBBuildingService;
import org.springframework.stereotype.Service;

/**
 * 楼房表(ArchiBBuilding)表服务实现类
 *
 * @author makejava
 * @since 2021-11-20 14:02:07
 */
@Service("archiBBuildingService")
public class ArchiBBuildingServiceImpl extends ServiceImpl<ArchiBBuildingDao, ArchiBBuilding> implements ArchiBBuildingService {

}

