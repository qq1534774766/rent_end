package com.aguo.service;

import com.aguo.entity.ArchiRRoom;
import com.aguo.entity.vol.RoomItemVol;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.PageParam;
import com.aguo.vo.params.RoomParam;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 房屋表(ArchiRRoom)表服务接口
 *
 * @author makejava
 * @since 2021-11-20 14:52:00
 */
public interface ArchiRRoomService extends IService<ArchiRRoom> {
    List<RoomItemVol> listRoomItemVol();

    Boolean addRoom(ArchiRRoom archiRRoom);

    /**
     * 分页查询，顺便关联查询租房状态
     *
     * @param page      页面参数
     * @param roomParam 查询房屋的条件
     * @return
     */
    @Deprecated
    ApiResponse listRoom(PageParam pageParam, RoomParam roomParam);

    /**
     * V2,一句sql搞定。
     * 分页查询，顺便关联查询租房状态
     *
     * @param page      页面参数
     * @param roomParam 查询房屋的条件
     * @return
     */
    ApiResponse listRoomV2(PageParam page, RoomParam roomParam);

    /**
     * 创建房屋
     *
     * @param roomParam
     * @return
     */
    ApiResponse addRoom(RoomParam roomParam);

    /**
     * 删除房屋
     *
     * @param roomId
     * @return
     */
    ApiResponse deleteRoom(Integer roomId);

    /**
     * 通过楼盘ID查询所有的房屋
     *
     * @param buildingId
     * @return
     */
    ApiResponse listRoomByBuildingId(Integer buildingId);

    /**
     * 获取所有的楼房一下的房屋数量
     *
     * @return
     */
    ApiResponse roomCountInBuilding();
}

