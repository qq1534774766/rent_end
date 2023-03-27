package com.aguo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName meter_reading
 */
@TableName(value = "meter_reading")
@Data
public class MeterReading implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 仪表读数 ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 房屋ID
     */
    private Integer roomId;
    /**
     * 读取时间
     */
    private Date readingDate;
    /**
     * 水记录值
     */
    private Double waterReading;
    /**
     * 电记录值
     */
    private Double electricityReading;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        MeterReading other = (MeterReading) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRoomId() == null ? other.getRoomId() == null : this.getRoomId().equals(other.getRoomId()))
                && (this.getReadingDate() == null ? other.getReadingDate() == null : this.getReadingDate().equals(other.getReadingDate()))
                && (this.getWaterReading() == null ? other.getWaterReading() == null : this.getWaterReading().equals(other.getWaterReading()))
                && (this.getElectricityReading() == null ? other.getElectricityReading() == null : this.getElectricityReading().equals(other.getElectricityReading()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRoomId() == null) ? 0 : getRoomId().hashCode());
        result = prime * result + ((getReadingDate() == null) ? 0 : getReadingDate().hashCode());
        result = prime * result + ((getWaterReading() == null) ? 0 : getWaterReading().hashCode());
        result = prime * result + ((getElectricityReading() == null) ? 0 : getElectricityReading().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", roomId=").append(roomId);
        sb.append(", readingDate=").append(readingDate);
        sb.append(", waterReading=").append(waterReading);
        sb.append(", electricityReading=").append(electricityReading);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}