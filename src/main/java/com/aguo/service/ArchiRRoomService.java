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
    ApiResponse listRoom(PageParam pageParam, RoomParam roomParam);
}

