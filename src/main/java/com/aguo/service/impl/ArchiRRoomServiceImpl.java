package com.aguo.service.impl;

import com.aguo.entity.*;
import com.aguo.entity.vol.RoomItemVol;
import com.aguo.service.ArchiBBuildingService;
import com.aguo.service.RentingService;
import com.aguo.service.UUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aguo.dao.ArchiRRoomDao;
import com.aguo.service.ArchiRRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 房屋表(ArchiRRoom)表服务实现类
 *
 * @author makejava
 * @since 2021-11-20 14:52:00
 */
@Service("archiRRoomService")
public class ArchiRRoomServiceImpl extends ServiceImpl<ArchiRRoomDao, ArchiRRoom> implements ArchiRRoomService {
    @Autowired
    private ArchiBBuildingService archiBBuildingService;
    @Autowired
    private RentingService rentingService;
    @Autowired
    private UUserService uUserService;

    @Override
    public List<RoomItemVol> listRoomItemVol(){
        List<RoomItemVol> list = new ArrayList<>();
//            查询所有楼房信息
        List<ArchiBBuilding> buildings = archiBBuildingService.list();
//        查询所有楼房以下的房屋信息
        QueryWrapper<ArchiRRoom> wrapper;
        for (ArchiBBuilding building : buildings) {
            wrapper = new QueryWrapper<>();
            wrapper.eq("BUILDING_ID",building.getBuildingId());
            List<ArchiRRoom> list2 = list(wrapper);
            for (ArchiRRoom archiRRoom : list2) {
                RoomItemVol roomItemVol = new RoomItemVol();
                roomItemVol.setArchiBBuilding(building);
                roomItemVol.setArchiRRoom(archiRRoom);
                Renting renting = rentingService.houseOneState(archiRRoom.getRoomId());
                if (renting.getUserId() != null) {
                    UUser uUser = uUserService.getById(renting.getUserId());
                    renting.setUserRealName( uUser.getName());
                    renting.setUsername( uUser.getUsername());
                }
                roomItemVol.setRenting(renting);
                list.add(roomItemVol);
            }
        }
        return  list;
    }

    @Override
    public Boolean addRoom(ArchiRRoom archiRRoom) {
        QueryWrapper<ArchiRRoom> wrapper = new QueryWrapper<>();
        wrapper.eq("HOUSE_NUMBER",archiRRoom.getHouseNumber())
                .eq("BUILDING_ID",archiRRoom.getBuildingId());
        ArchiRRoom one = getOne(wrapper);
        if (one == null) {
            return save(archiRRoom);
        }else {
//            表明房屋门牌号在该楼房已存在
            return false;
        }
    }
}

