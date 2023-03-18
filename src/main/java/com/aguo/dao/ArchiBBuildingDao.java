package com.aguo.dao;

import com.aguo.entity.ArchiBBuilding;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 楼房表(ArchiBBuilding)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-20 14:02:07
 */
@Mapper
@Repository
public interface ArchiBBuildingDao extends BaseMapper<ArchiBBuilding> {

}

