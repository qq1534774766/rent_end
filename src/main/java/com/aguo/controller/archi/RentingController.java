package com.aguo.controller.archi;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/22 12:03
 * @Description: TODO
 */

import com.aguo.entity.vol.RentingVol;
import com.aguo.service.RentingService;
import com.aguo.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 租用关系
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/manage")
public class RentingController {
    @Autowired
    private RentingService rentingService;

    @PutMapping("/renting")
    public ApiResponse handleRenting(@RequestBody RentingVol rentingVol) {
        return rentingService.handleRenting(rentingVol);
    }

    @GetMapping("/rentingWeek")
    public ApiResponse rentingWeek() {
        return rentingService.rentingWeek();
    }

}
