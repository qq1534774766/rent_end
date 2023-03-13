package com.aguo.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * 房屋表(ArchiRRoom)表实体类
 *
 * @author makejava
 * @since 2021-11-20 14:52:00
 */
@SuppressWarnings("serial")
public class ArchiRRoom extends Model<ArchiRRoom> {
    //房屋ID
    @TableId(type = IdType.AUTO)
    private Integer roomId;
    //楼房ID
    private Integer buildingId;
    //门牌号
    private String houseNumber;
    //创建时间
    private Date createdTime;
    //更新时间
    private Date updateTime;


    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.roomId;
    }

    @Override
    public String toString() {
        return "ArchiRRoom{" +
                "roomId=" + roomId +
                ", buildingId=" + buildingId +
                ", houseNumber='" + houseNumber + '\'' +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArchiRRoom that = (ArchiRRoom) o;
        return Objects.equals(roomId, that.roomId) &&
                Objects.equals(buildingId, that.buildingId) &&
                Objects.equals(houseNumber, that.houseNumber) &&
                Objects.equals(createdTime, that.createdTime) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, buildingId, houseNumber, createdTime, updateTime);
    }
}

