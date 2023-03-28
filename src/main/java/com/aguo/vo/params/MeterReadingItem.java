package com.aguo.vo.params;

import lombok.Data;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/28 17:52
 * @Description: TODO
 */
@Data
public class MeterReadingItem {
    private Integer buildingId;
    private String buildingName;
    private Integer roomId;
    private String houseNumber;
    private Double waterReading;
    private Double electricityReading;
}
