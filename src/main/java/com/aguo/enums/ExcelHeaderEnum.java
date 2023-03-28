package com.aguo.enums;

/**
 * @Author: wenqiaogang
 * @DateTime: 2023/3/28 13:29
 * @Description: Excel表头转化
 */
public enum ExcelHeaderEnum {
    /**
     *
     */
    BUILDING_ID("buildingId", "楼盘编号"),
    BUILDING_NAME("buildingName", "楼盘名"),
    ROOM_ID("roomId", "房屋编号"),
    HOUSE_NUMBER("houseNumber", "门牌号"),
    WATER_READING("waterReading", "水记录值"),
    ELECTRICITY_READING("electricityReading", "电记录值");
    /**
     * 数据库值
     */
    private final String value;
    /**
     * 描述
     */
    private final String description;

    ExcelHeaderEnum(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
