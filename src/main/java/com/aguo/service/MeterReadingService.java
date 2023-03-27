package com.aguo.service;

import com.aguo.entity.MeterReading;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.MeterReadingParam;
import com.aguo.vo.params.PageParam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Administrator
 * @description 针对表【meter_reading】的数据库操作Service
 * @createDate 2023-03-27 10:18:09
 */
public interface MeterReadingService extends IService<MeterReading> {

    /**
     * 分页查询房屋及其水电的记录数
     *
     * @param page
     * @param meterReadingParam
     * @return
     */
    ApiResponse queryMeterReading(PageParam page, MeterReadingParam meterReadingParam);

    /**
     * 新增
     *
     * @param meterReading
     * @return
     */
    ApiResponse addMeterReading(MeterReading meterReading);

    /**
     * 通过id更新记录
     *
     * @param meterReading
     * @return
     */
    ApiResponse updateMeterReading(MeterReading meterReading);

    /**
     * 通过id删除记录
     *
     * @param meterReadingId
     * @return
     */
    ApiResponse deleteMeterReading(Integer meterReadingId);
}
