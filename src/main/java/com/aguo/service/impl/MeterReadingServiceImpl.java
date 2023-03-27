package com.aguo.service.impl;

import com.aguo.dao.MeterReadingDao;
import com.aguo.entity.MeterReading;
import com.aguo.service.MeterReadingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.params.MeterReadingParam;
import com.aguo.vo.params.PageParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【meter_reading】的数据库操作Service实现
 * @createDate 2023-03-27 10:18:09
 */
@Service
public class MeterReadingServiceImpl extends ServiceImpl<MeterReadingDao, MeterReading> implements MeterReadingService {

    @Autowired
    private MeterReadingDao meterReadingDao;

    @Override
    public ApiResponse queryMeterReading(PageParam page, MeterReadingParam meterReadingParam) {
        List<MeterReading> list = meterReadingDao.queryMeterReading(
                page.getPage(), page.getPageSize(),
                meterReadingParam.getBuildingName(), meterReadingParam.getHouseNumber(), meterReadingParam.getReadingDate()
        );
        return ApiResponse.success();
    }
}
