package com.aguo.dao;

import com.aguo.entity.vol.UUserVol;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UUserVolDao extends BaseMapper<UUserVol> {
}
