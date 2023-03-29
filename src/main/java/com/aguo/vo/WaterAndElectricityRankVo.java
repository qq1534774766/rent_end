package com.aguo.vo;

import lombok.Data;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/29 16:37
 * @Description: TODO
 */
@Data
public class WaterAndElectricityRankVo {
    private String buildingName;
    private String roomId;
    private String houseNumber;
    private Double waterReading;
    private Double electricityReading;
}
