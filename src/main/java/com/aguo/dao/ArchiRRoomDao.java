package com.aguo.dao;

import com.aguo.entity.ArchiRRoom;
import com.aguo.entity.vol.RoomItemVolV2;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 房屋表(ArchiRRRoom)表数据库访问层
 *
 * @author makejava
 * @since 2021-11-20 14:52:00
 */
@Mapper
@Repository
public interface ArchiRRoomDao extends BaseMapper<ArchiRRoom> {
    /**
     * 列出所有房屋对应的楼盘信息
     *
     * @return
     */
    List<RoomItemVolV2> queryRoomIncludeBuilding();

    /**
     * 通过楼盘名与门牌号查询楼盘信息
     *
     * @param buildingName
     * @param houseNumber
     * @param page         务必计算后才传值
     * @param pageSize
     * @return
     */
    List<RoomItemVolV2> queryRoomByBuildingNameOrHouseNumber(@Param("buildingName") String buildingName,
                                                             @Param("houseNumber") String houseNumber,
                                                             @Param("page") Integer page,
                                                             @Param("pageSize") Integer pageSize);

}

