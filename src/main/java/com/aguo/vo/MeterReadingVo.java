package com.aguo.vo;

import com.aguo.entity.MeterReading;
import lombok.Data;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/27 19:24
 * @Description: TODO
 */
@Data
public class MeterReadingVo extends MeterReading {
    private String buildingName;
    private String houseNumber;
}
