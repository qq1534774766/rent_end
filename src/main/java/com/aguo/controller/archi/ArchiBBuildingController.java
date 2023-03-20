package com.aguo.controller.archi;

import com.aguo.entity.ArchiBBuilding;
import com.aguo.service.ArchiBBuildingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.BuildingParam;
import com.aguo.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 楼盘管理
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/manage")
public class ArchiBBuildingController {
    @Autowired
    private ArchiBBuildingService archiBBuildingService;

    /**
     * 分页查询楼盘
     *
     * @param pageparam
     * @param buildingParam
     * @return
     */
    @GetMapping("/building")
    public ApiResponse listBuilding(PageParam pageparam, BuildingParam buildingParam) {
        return archiBBuildingService.listBuilding(pageparam, buildingParam);
    }

    /**
     * 新增楼房
     *
     * @param archibBuilding
     * @return
     */
    @PostMapping("/building")
    public ApiResponse addBuilding(@RequestBody ArchiBBuilding archibBuilding) {
        return archiBBuildingService.addBuilding(archibBuilding);
    }

    /**
     * 删除楼盘
     *
     * @param archibBuildingd
     * @return
     */
    @DeleteMapping("/building")
    public ApiResponse deleteBuilding(@RequestBody ArchiBBuilding archibBuilding) {
        return archiBBuildingService.deleteBuilding(archibBuilding);
    }

    @PutMapping("/building")
    public ApiResponse updateBuilding(@RequestBody ArchiBBuilding archibBuilding) {
        return archiBBuildingService.updateBuilding(archibBuilding);
    }
}
