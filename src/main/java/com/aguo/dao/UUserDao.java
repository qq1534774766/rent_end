package com.aguo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.aguo.entity.UUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户名表(UUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-11 13:40:00
 */
@Mapper
@Repository
public interface UUserDao extends BaseMapper<UUser> {

}

