package com.aguo.vo.params;

import lombok.Data;

import java.util.Date;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/27 15:26
 * @Description: TODO
 */
@Data
public class MeterReadingParam {
    private String buildingName;
    private String houseNumber;
    private Date readingDate;
}
