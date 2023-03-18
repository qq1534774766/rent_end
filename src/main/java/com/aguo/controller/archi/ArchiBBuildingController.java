package com.aguo.controller.archi;

import com.aguo.entity.ArchiBBuilding;
import com.aguo.service.ArchiBBuildingService;
import com.aguo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/listBuilding")
    public List<ArchiBBuilding> listBuilding() {
        return archiBBuildingService.list();
    }
    @RequestMapping("/addBuilding")
    public Boolean addBuilding(ArchiBBuilding archiBBuilding){
        archiBBuildingService.save(archiBBuilding);
        return true;
    }
}
