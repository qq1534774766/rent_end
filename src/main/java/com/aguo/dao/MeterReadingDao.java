package com.aguo.dao;

import com.aguo.entity.MeterReading;
import com.aguo.vo.MeterReadingExcelVo;
import com.aguo.vo.MeterReadingVo;
import com.aguo.vo.WaterAndElectricityRankVo;
import com.aguo.vo.params.MeterReadingItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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


    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param buildingName
     * @param houseNumber
     * @param year
     * @param month
     * @return
     */
    List<MeterReadingVo> queryMeterReading(Integer page, Integer pageSize,
                                           String buildingName, String houseNumber,
                                           Integer year, Integer month);

    /**
     * 计数功能
     *
     * @param buildingName
     * @param houseNumber
     * @param year
     * @param month
     * @return
     */
    Long countQueryMeterReading(String buildingName, String houseNumber, Integer year, Integer month);

    /**
     * 根据楼盘ID查出所有房屋信息
     *
     * @param buildingId
     * @return
     */
    List<MeterReadingExcelVo> fetchMeterReadingExcelVo(Integer buildingId, Integer year, Integer month, Integer day);

    /**
     * 根据roomID和时间来确定有没重复记录
     *
     * @param list
     * @param readingDate
     * @return
     */
    Integer insertOrUpdateMeterReading(@Param("list") List<MeterReadingItem> list, @Param("readingDate") Date readingDate);

    /**
     * 获取每个月的用水电量
     *
     * @param date
     * @return
     */
    List<WaterAndElectricityRankVo> waterAndElectricityRank(@Param("date") Date date);
}
