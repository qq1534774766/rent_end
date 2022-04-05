package com.aguo.controller.archi;

import com.aguo.entity.ArchiBBuilding;
import com.aguo.service.ArchiBBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/manage")
public class ArchiBBuildingController {
    @Autowired
    private ArchiBBuildingService archiBBuildingService;

    @RequestMapping("/listBuilding")
    public List<ArchiBBuilding> listBuilding(){
        return archiBBuildingService.list();
    }
    @RequestMapping("/addBuilding")
    public Boolean addBuilding(ArchiBBuilding archiBBuilding){
        archiBBuildingService.save(archiBBuilding);
        return true;
    }
}
