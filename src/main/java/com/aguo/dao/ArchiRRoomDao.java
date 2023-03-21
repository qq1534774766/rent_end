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

    /**
     * 查询所有房屋信息包括楼盘、用户以及租用情况。一句sql搞定
     *
     * @param page
     * @param pageSize
     * @param buildingName
     * @param houseNumber
     * @param rentState
     * @return
     */
    List<RoomItemVolV2> queryAllRoomInfo(@Param("page") Integer page,
                                         @Param("pageSize") Integer pageSize,
                                         @Param("buildingName") String buildingName,
                                         @Param("houseNumber") String houseNumber,
                                         @Param("rentState") String rentState);

    /**
     * 查询条数
     *
     * @param buildingName
     * @param houseNumber
     * @return
     */
    Integer queryAllRoomInfoCount(@Param("buildingName") String buildingName,
                                  @Param("houseNumber") String houseNumber,
                                  @Param("rentState") String rentState);
}

