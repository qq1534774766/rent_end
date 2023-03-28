package com.aguo.vo;

import lombok.Data;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/28 13:36
 * @Description: TODO
 */
@Data
public class MeterReadingExcelVo {
    private Integer buildingId;
    private String buildingName;
    private Integer roomId;
    private String houseNumber;
    private Double waterReading;
    private Double electricityReading;
}
