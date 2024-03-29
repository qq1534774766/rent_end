package com.aguo.controller.archi;


import com.aguo.entity.vol.RoomItemVol;
import com.aguo.service.ArchiRRoomService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.PageParam;
import com.aguo.vo.params.RoomParam;
import com.baomidou.mybatisplus.extension.api.ApiController;
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

    @PostMapping("/room")
    public ApiResponse addRoom(@RequestBody RoomParam roomParam) {
        return archiRRoomService.addRoom(roomParam);
    }

    @RequestMapping("/listRoomItemVol")
    public List<RoomItemVol> listRoomItemVol() {
        return archiRRoomService.listRoomItemVol();
    }

    /**
     * 查询房屋，关联查询房屋的租房状态
     *
     * @param page
     * @param roomParam
     * @return
     */
    @GetMapping("/roomv1")
    @Deprecated
    public ApiResponse listRoom(PageParam page, RoomParam roomParam) {
        return archiRRoomService.listRoom(page, roomParam);
    }

    /**
     * 查询房屋，关联查询房屋的租房状态
     *
     * @param page
     * @param roomParam
     * @return
     */
    @GetMapping("/room")
    public ApiResponse listRoomV2(PageParam page, RoomParam roomParam) {
        return archiRRoomService.listRoomV2(page, roomParam);
    }

    /**
     * 删除房屋 包含所有租用关系
     *
     * @param roomId
     * @return
     */
    @DeleteMapping("/room")
    public ApiResponse deleteRoom(Integer roomId) {
        return archiRRoomService.deleteRoom(roomId);
    }

    @GetMapping("/listRoomByBuildingId")
    public ApiResponse listRoomByBuildingId(Integer buildingId) {
        return archiRRoomService.listRoomByBuildingId(buildingId);
    }

    @GetMapping("/roomCountInBuilding")
    public ApiResponse roomCountInBuilding() {
        return archiRRoomService.roomCountInBuilding();
    }


}

