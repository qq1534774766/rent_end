package com.aguo.service.impl;

import com.aguo.dao.RentingDao;
import com.aguo.entity.ArchiRRoom;
import com.aguo.entity.Renting;
import com.aguo.entity.UUser;
import com.aguo.entity.vol.RentingVol;
import com.aguo.enums.ApiStatueCode;
import com.aguo.service.ArchiRRoomService;
import com.aguo.service.RentingService;
import com.aguo.service.UUserService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.RentingWeek;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.aguo.enums.ApiStatueCode.*;

/**
 * 租房关系表(Renting)表服务实现类
 *
 * @author makejava
 * @since 2021-11-20 16:27:43
 */
@Service("rentingService")
public class RentingServiceImpl extends ServiceImpl<RentingDao, Renting> implements RentingService {
    @Autowired
    private RentingDao rentingDao;

    @Autowired
    private UUserService uUserService;

    @Autowired
    private ArchiRRoomService rRoomService;

    @Override
    public Renting houseState(int userId, int roomId) {
        QueryWrapper<Renting> wrapper = new QueryWrapper<>();
        if (userId != -1) {
            wrapper.eq("USER_ID", userId);
        }
        if (roomId != -1) {
            wrapper.eq("ROOM_ID", roomId);
        }
        List<Renting> list = list(wrapper);
        if (list.size() == 0) {
            Renting renting = new Renting();
            renting.setRentState(false);
            return renting;
        } else {
            if (list.size() == 1) {
                Renting renting = list.get(0);
                renting.setRentState(renting.getStopTime() == null);
                return renting;
            } else {
                for (Renting renting : list) {
                    if (renting.getStopTime() == null) {
                        renting.setRentState(true);
                        return renting;
                    }
                }
                Renting renting = list.get(list.size() - 1);
                renting.setRentState(false);
                return renting;
            }
        }
    }

    @Override
    public Renting houseOneState(int roomId) {
        return houseState(-1, roomId);
    }

    @Override
    public Boolean renterRentState(int userId) {
        return houseState(userId, -1).getRentState();
    }

    @Override
    public RentingVol roomRentState(Integer roomId) {
        return rentingDao.queryRentingVol(roomId);
    }

    @Override
    public Boolean deleteRentingByRoomId(Integer roomId) {
        LambdaQueryWrapper<Renting> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Renting::getRoomId, roomId);
        int delete = rentingDao.delete(queryWrapper);
        return delete > 0;
    }

    @Override
    public ApiResponse handleRenting(RentingVol rentingVol) {
        //合法性校验
        ApiStatueCode type = validateRenting(rentingVol);
        if (ObjectUtils.isNotEmpty(type)) {
            return ApiResponse.error(type);
        }
        //存在性校验
        LambdaQueryWrapper<ArchiRRoom> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ArchiRRoom::getRoomId, rentingVol.getRoomId());
        if (rRoomService.count(lqw) <= 0) {
            return ApiResponse.error(ROOM_ID_ERROR);
        }
        if (null != rentingVol.getUserId()) {
            UUser renter = uUserService.getById(rentingVol.getUserId());
            if (ObjectUtils.isEmpty(renter)) {
                return ApiResponse.error(USER_NOT_EXIST);
            }
            if ("1".equals(renter.getLocked()) || "0".equals(renter.getEnable())) {
                return ApiResponse.error(USER_DISABLE);
            }
        }
        //可更改检查
        Date createTime = ObjectUtils.isEmpty(rentingVol.getCreatedTime()) ? null : rentingVol.getCreatedTime();
        Date stopTime = ObjectUtils.isEmpty(rentingVol.getStopTime()) ? null : rentingVol.getStopTime();
        Renting renting = new Renting();
        renting.setCreatedTime(createTime);
        renting.setStopTime(stopTime);
        renting.setUserId(rentingVol.getUserId());
        renting.setRoomId(rentingVol.getRoomId());
        if (ObjectUtils.isNotEmpty(rentingVol.getRentingId())) {
            //该房屋必然处于出租状态。
            if (ObjectUtils.isEmpty(rentingDao.selectById(rentingVol.getRentingId())))
                return ApiResponse.error(RENTING_ID_NOT_EXIST);
            renting.setRentingId(rentingVol.getRentingId());
            renting.setUpdateTime(new Date());
            //这里必须使用sql，因为有限期停租 转为 无限期的时候 stop_time为null，该值也应被设置到数据库中
            int insert = 0;
            try {
                insert = rentingDao.updateByPrimaryKey(renting);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ApiResponse.booleanResponse(insert > 0, UNABLE_UPDATE_RENTING);
        } else {
            //房屋未出租过
            //录入
            int insert = 0;
            try {
                insert = rentingDao.insert(renting);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ApiResponse.booleanResponse(insert > 0, INSERT_RENTING_INFO_ERROR);
        }

    }

    private ApiStatueCode validateRenting(RentingVol rentingVol) {
        ApiStatueCode type = null;
        if (rentingVol.getRoomId() == null) {
            type = ROOM_ID_ERROR;
        } else if (ObjectUtils.isNotEmpty(rentingVol.getCreatedTime())
                && ObjectUtils.isNotEmpty(rentingVol.getStopTime())
                && (rentingVol.getCreatedTime().after(rentingVol.getStopTime()))) {
            type = CREATE_TIME_AND_STOP_TIME_BOTH_ERROR;
        }
        return type;
    }

    @Override
    public ApiResponse rentingWeek() {
        List<RentingWeek> list = rentingDao.rentingWeek();
        return ApiResponse.success(list);
    }
}

