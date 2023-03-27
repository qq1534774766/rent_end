package com.aguo.service.impl;

import com.aguo.dao.MeterReadingDao;
import com.aguo.entity.ArchiRRoom;
import com.aguo.entity.MeterReading;
import com.aguo.service.ArchiRRoomService;
import com.aguo.service.MeterReadingService;
import com.aguo.vo.ApiResponse;
import com.aguo.vo.MeterReadingVo;
import com.aguo.vo.params.MeterReadingParam;
import com.aguo.vo.params.PageParam;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private ArchiRRoomService archiRRoomService;

    @Override
    public ApiResponse queryMeterReading(PageParam pageParam, MeterReadingParam meterReadingParam) {
        Integer year = null, month = null;
        if (ObjectUtils.isNotEmpty(meterReadingParam.getReadingDate())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(meterReadingParam.getReadingDate());
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
        }
        List<MeterReadingVo> list = meterReadingDao.queryMeterReading(
                (pageParam.getPage() - 1) * pageParam.getPageSize(),
                pageParam.getPageSize(),
                meterReadingParam.getBuildingName(), meterReadingParam.getHouseNumber(),
                year, month
        );
        Long count = meterReadingDao.countQueryMeterReading(meterReadingParam.getBuildingName(), meterReadingParam.getHouseNumber(),
                year, month);

        HashMap<String, Object> map = new HashMap<>(100);
        map.put("list", list);
        map.put("count", count);
        return ApiResponse.success(map);
    }

    @Override
    public ApiResponse addMeterReading(MeterReading meterReading) {
        try {
            ArchiRRoom room = archiRRoomService.getById(meterReading.getRoomId());
            if (room == null) {
                throw new RuntimeException("房屋不存在");
            }
            if (ObjectUtils.isEmpty(meterReading.getReadingDate())) {
                meterReading.setReadingDate(new Date());
            }
            if (ObjectUtils.isEmpty(meterReading.getWaterReading()) || ObjectUtils.isEmpty(meterReading.getElectricityReading())) {
                return ApiResponse.error("房屋水电记录不能为空");

            }
        } catch (Exception e) {
            return ApiResponse.error("房屋不存在");
        }
        int insert = meterReadingDao.insert(meterReading);
        return ApiResponse.booleanResponse(insert > 0, "水电记录插入失败");
    }

    @Override
    public ApiResponse updateMeterReading(MeterReading meterReading) {
        int i = meterReadingDao.updateById(meterReading);
        return ApiResponse.booleanResponse(i > 0, "水电记录更新失败");
    }

    @Override
    public ApiResponse deleteMeterReading(Integer meterReadingId) {
        int i = meterReadingDao.deleteById(meterReadingId);
        return ApiResponse.booleanResponse(i > 0, "水电记录删除失败");
    }
}
