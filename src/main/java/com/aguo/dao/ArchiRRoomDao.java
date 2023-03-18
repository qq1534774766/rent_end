package com.aguo.dao;

import com.aguo.entity.ArchiRRoom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 房屋表(ArchiRRoom)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-20 14:52:00
 */
@Mapper
@Repository
public interface ArchiRRoomDao extends BaseMapper<ArchiRRoom> {

}

