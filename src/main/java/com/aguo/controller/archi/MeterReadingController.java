package com.aguo.controller.archi;

import com.aguo.entity.MeterReading;
import com.aguo.service.MeterReadingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.MeterReadingExcelParam;
import com.aguo.vo.params.MeterReadingParam;
import com.aguo.vo.params.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

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

    @PostMapping
    public ApiResponse addMeterReading(@RequestBody MeterReading meterReading) {
        return meterReadingService.addMeterReading(meterReading);

    }

    @PutMapping
    public ApiResponse updateMeterReading(@RequestBody MeterReading meterReading) {
        return meterReadingService.updateMeterReading(meterReading);
    }

    @DeleteMapping
    public ApiResponse deleteMeterReading(Integer id) {
        return meterReadingService.deleteMeterReading(id);
    }

    /**
     * 获取Excel批量录入模板
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/downloadExcel")
    public void downloadExcelTemplate(Integer buildingId, Date readingDate, HttpServletResponse response) throws IOException {
        meterReadingService.downloadExcelTemplate(buildingId, readingDate, response);
    }

    @PostMapping("/handleList")
    public ApiResponse handleList(@RequestBody MeterReadingExcelParam meterReadingExcelParam) {
        return meterReadingService.handleList(meterReadingExcelParam);
    }

    @GetMapping("/waterAndElectricityRank")
    public ApiResponse waterAndElectricityRank(@RequestParam("date") Date date) {
        System.out.println(date);
        return meterReadingService.waterAndElectricityRank(date);
    }
}
