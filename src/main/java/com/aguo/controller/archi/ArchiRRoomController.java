package com.aguo.controller.archi;


import com.aguo.entity.vol.RoomItemVol;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.aguo.entity.ArchiRRoom;
import com.aguo.service.ArchiRRoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 房屋表(ArchiRRoom)表控制层
 *
 * @author makejava
 * @since 2021-11-20 14:52:00
 */
@RestController
@RequestMapping("/manage")
public class ArchiRRoomController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private ArchiRRoomService archiRRoomService;

    @RequestMapping("/addRoom")
    public Boolean addRoom(ArchiRRoom archiRRoom){
        return archiRRoomService.addRoom(archiRRoom);
    }

    @RequestMapping("/listRoomItemVol")
    public List<RoomItemVol> listRoomItemVol(){
        return archiRRoomService.listRoomItemVol();
    }
}

