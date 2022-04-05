package com.aguo.service;

import com.aguo.entity.vol.RoomItemVol;
import com.baomidou.mybatisplus.extension.service.IService;
import com.aguo.entity.ArchiRRoom;

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

}

