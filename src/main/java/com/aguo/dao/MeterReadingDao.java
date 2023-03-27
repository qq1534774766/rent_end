package com.aguo.dao;

import com.aguo.entity.MeterReading;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @description 针对表【meter_reading】的数据库操作Mapper
 * @createDate 2023-03-27 10:18:09
 * @Entity com.aguo.com.aguo.MeterReading
 */
@Repository
@Mapper
public interface MeterReadingDao extends BaseMapper<MeterReading> {


    List<MeterReading> queryMeterReading(Integer page, Integer pageSize, String buildingName, String houseNumber, Date readingDate);
}
