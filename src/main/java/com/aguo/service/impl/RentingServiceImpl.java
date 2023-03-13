package com.aguo.service.impl;

import com.aguo.entity.UUser;
import com.aguo.service.UUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aguo.dao.RentingDao;
import com.aguo.entity.Renting;
import com.aguo.service.RentingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 租房关系表(Renting)表服务实现类
 *
 * @author makejava
 * @since 2021-11-20 16:27:43
 */
@Service("rentingService")
public class RentingServiceImpl extends ServiceImpl<RentingDao, Renting> implements RentingService {
    @Override
    public Renting houseState(int userId, int roomId){
        QueryWrapper<Renting> wrapper = new QueryWrapper<>();
        if (userId!=-1) {
            wrapper.eq("USER_ID",userId);
        }
        if(roomId!=-1){
            wrapper.eq("ROOM_ID",roomId);
        }
        List<Renting> list = list(wrapper);
        if(list.size()==0){
            Renting renting = new Renting();
            renting.setRentState(false);
            return renting;
        }else{
            if (list.size()==1){
                Renting renting = list.get(0);
                renting.setRentState(renting.getStopTime() == null);
                return renting;
            }else {
                for (Renting renting : list) {
                    if(renting.getStopTime()==null) {
                        renting.setRentState(true);
                        return renting;
                    }
                }
                Renting renting = list.get(list.size()-1);
                renting.setRentState(false);
                return renting;
            }
        }
    }

    @Override
    public Renting houseOneState(int roomId) {
        return houseState(-1,roomId);
    }
    @Override
    public Boolean renterRentState(int userId){
        return houseState(userId, -1).getRentState();
    }
}

