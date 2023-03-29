package com.aguo.service.impl;

import com.aguo.dao.ArchiRRoomDao;
import com.aguo.entity.ArchiBBuilding;
import com.aguo.entity.ArchiRRoom;
import com.aguo.entity.Renting;
import com.aguo.entity.UUser;
import com.aguo.entity.vol.RentingVol;
import com.aguo.entity.vol.RoomItemVol;
import com.aguo.entity.vol.RoomItemVolV2;
import com.aguo.service.ArchiBBuildingService;
import com.aguo.service.ArchiRRoomService;
import com.aguo.service.RentingService;
import com.aguo.service.UUserService;
import com.aguo.untils.code.ParamUntil;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.BuildingRoomVo;
import com.aguo.vo.params.PageParam;
import com.aguo.vo.params.RoomParam;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 房屋表(ArchiRRoom)表服务实现类
 *
 * @author makejava
 * @since 2021-11-20 14:52:00
 */
@Service("archiRRoomService")
public class ArchiRRoomServiceImpl
        extends ServiceImpl<ArchiRRoomDao, ArchiRRoom>
        implements ArchiRRoomService {
    @Autowired
    private ArchiBBuildingService archiBBuildingService;
    @Autowired
    private RentingService rentingService;
    @Autowired
    private UUserService uUserService;
    @Autowired
    private ArchiRRoomDao archiRRoomDao;

    @Override
    public List<RoomItemVol> listRoomItemVol() {
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
        wrapper.eq("HOUSE_NUMBER", archiRRoom.getHouseNumber())
                .eq("BUILDING_ID", archiRRoom.getBuildingId());
        ArchiRRoom one = getOne(wrapper);
        if (one == null) {
            return save(archiRRoom);
        } else {
//            表明房屋门牌号在该楼房已存在
            return false;
        }
    }

    @Override
    public ApiResponse addRoom(RoomParam roomParam) {
        ArchiRRoom archiRRoom = new ArchiRRoom();
        archiRRoom.setBuildingId(Integer.valueOf(roomParam.getBuildingId()));
        archiRRoom.setHouseNumber(roomParam.getHouseNumber());
        int insert = 0;
        try {
            insert = archiRRoomDao.insert(archiRRoom);
        } catch (Exception e) {
            //重复添加房屋
        }

        String errorMsg = String.format("房间号【%s】已存在于该楼盘", roomParam.getHouseNumber());
        return ApiResponse.booleanResponse(insert > 0, errorMsg);
    }

    @Override
    public ApiResponse listRoom(PageParam pageParam, RoomParam roomParam) {
        //过滤掉条件
        List<RoomItemVolV2> list = archiRRoomDao.queryRoomByBuildingNameOrHouseNumber(
                ParamUntil.trimStringOrEmpty(roomParam.getBuildingName()),
                ParamUntil.trimStringOrEmpty(roomParam.getHouseNumber()),
                (pageParam.getPage() - 1) * pageParam.getPageSize(),
                pageParam.getPageSize());
        long count = list.stream().peek(room -> {
            RentingVol rentingVol = rentingService.roomRentState(room.getRoomId());
            if (ObjectUtils.isNotEmpty(rentingVol) && rentingVol.getRentState()) {
                //租房中，查询用户信息
                UUser uUser = uUserService.queryUserById(rentingVol.getUserId());
                room.setUserId(uUser.getUserId());
                room.setName(uUser.getName());
                room.setUsername(uUser.getUsername());
                room.setPhoneNumber(uUser.getPhoneNumber());

                room.setRentState(true);
                room.setCreatedTime(rentingVol.getCreatedTime());
                room.setStopTime(rentingVol.getStopTime());
            } else {
                room.setRentState(false);
            }
        }).count();
        HashMap<String, Object> map = new HashMap<>(100);
        map.put("list", list);
        map.put("count", count);
        return ApiResponse.success(map);
    }


    @Override
    public ApiResponse listRoomV2(PageParam pageParam, RoomParam roomParam) {

        List<RoomItemVolV2> list = archiRRoomDao.queryAllRoomInfo(
                (pageParam.getPage() - 1) * pageParam.getPageSize(),
                pageParam.getPageSize(),
                ParamUntil.trimStringOrEmpty(roomParam.getBuildingName()),
                ParamUntil.trimStringOrEmpty(roomParam.getHouseNumber()),
                ParamUntil.handleBooleanOrEmpty(roomParam.getRentState()));
        Integer count = archiRRoomDao.queryAllRoomInfoCount(
                ParamUntil.trimStringOrEmpty(roomParam.getBuildingName()),
                ParamUntil.trimStringOrEmpty(roomParam.getHouseNumber()),
                ParamUntil.handleBooleanOrEmpty(roomParam.getRentState()));
        HashMap<String, Object> map = new HashMap<>(100);
        map.put("list", list);
        map.put("count", count);
        return ApiResponse.success(map);
    }

    @Override
    public ApiResponse deleteRoom(Integer roomId) {
        //删除记录表相关记录
        rentingService.deleteRentingByRoomId(roomId);
        //删除房屋自身记录
        removeById(roomId);
        return ApiResponse.success();
    }

    @Override
    public ApiResponse listRoomByBuildingId(Integer buildingId) {
        LambdaQueryWrapper<ArchiRRoom> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ArchiRRoom::getBuildingId, buildingId);
        List<ArchiRRoom> list = archiRRoomDao.selectList(lqw);
        return ApiResponse.success(list);
    }

    @Override
    public ApiResponse roomCountInBuilding() {
        List<BuildingRoomVo> list = archiRRoomDao.roomCountInBuilding();
        return ApiResponse.success(list);
    }
}

