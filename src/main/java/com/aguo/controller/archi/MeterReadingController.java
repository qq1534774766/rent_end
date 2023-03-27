package com.aguo.controller.archi;

import com.aguo.service.MeterReadingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.MeterReadingParam;
import com.aguo.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/27 11:04
 * @Description: TODO
 */
@RestController
@RequestMapping("/manage/meterReading")
public class MeterReadingController {
    @Autowired
    private MeterReadingService meterReadingService;

    @GetMapping
    public ApiResponse queryMeterReading(PageParam page, MeterReadingParam meterReadingParam) {
        return meterReadingService.queryMeterReading(page, meterReadingParam);

    }

}
